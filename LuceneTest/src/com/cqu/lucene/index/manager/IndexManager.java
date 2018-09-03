/**
 * @description 实时索引管理
 */
package com.cqu.lucene.index.manager;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NRTManager;
import org.apache.lucene.search.NRTManager.TrackingIndexWriter;
import org.apache.lucene.search.NRTManagerReopenThread;
import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;

import com.cqu.lucene.index.model.IndexBean;
import com.cqu.lucene.index.model.IndexConfig;

public class IndexManager {
	
	private IndexWriter indexWriter;
	private TrackingIndexWriter trackingIndexWriter;
	private Analyzer analyzer;
	private NRTManager nrtManager;
	private NRTManagerReopenThread nrtManagerReopenThread;
	private IndexCommitThread indexCommitThread;
	private IndexBean bean;
	
	private IndexManager(IndexBean bean){
		this.bean = bean;
		this.analyzer = bean.getAnalyzer();
		//索引的存储路径
		String indexFile = bean.getIndexPath() + "/" + bean.getIndexName();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_43, this.analyzer);
		indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
		Directory directory = null;
		try {
			directory = NIOFSDirectory.open(new File(indexFile));
			if(IndexWriter.isLocked(directory)){
				IndexWriter.unlock(directory);
			}
			this.indexWriter = new IndexWriter(directory, indexWriterConfig);
			//将indexWriter委托给trackingIndexWriter
			this.trackingIndexWriter = new TrackingIndexWriter(indexWriter);
			//创建NRTManager
			this.nrtManager = new NRTManager(trackingIndexWriter, new SearcherFactory());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//开启系统的守护线程
		startThread();
	}
	
	private static class LazyIndexManager{
		//保存系统中的IndexManager对象
		private static HashMap<String, IndexManager> indexManagerMap = new HashMap<String, IndexManager>();
		
		static{
			for(IndexBean bean : IndexConfig.getConfig()){
				indexManagerMap.put(bean.getIndexName(), new IndexManager(bean));
			}
		}
	}
	
	/**
	 * 
	 * @param indexName
	 * @return
	 * @author 汪波
	 * @description 获取对应名称索引的IndexManager对象
	 */
	public static IndexManager getIndexManager(String indexName){
		return LazyIndexManager.indexManagerMap.get(indexName);
	}
	
	private class IndexCommitThread extends Thread{
		private boolean flag = false;
		
		public IndexCommitThread(String name){
			super(name);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			flag = true;
			while(flag){
				try {
					//内存索引提交至硬盘
					indexWriter.commit();
					System.out.println(bean.getIndexName() + "\tcommit");
					TimeUnit.SECONDS.sleep(bean.getIndexCommitSeconds());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			super.run();
		}
	}
	
	/**
	 * @author 汪波
	 * @description 设置indexManager的守护线程
	 */
	private void startThread(){
		//内存索引重读线程
		this.nrtManagerReopenThread = new NRTManagerReopenThread(nrtManager, bean.getIndexReopenMaxStaleSec(), bean.getIndexReopenMinStaleSec());
		this.nrtManagerReopenThread.setName("NRTManager reoprn thread");
		this.nrtManagerReopenThread.setDaemon(true);
		this.nrtManagerReopenThread.start();
		
		//内存索引提交线程
		this.indexCommitThread = new IndexCommitThread(bean.getIndexName() + " index commmit thread");
		this.indexCommitThread.setDaemon(true);
		this.indexCommitThread.start();
	}
	
	/**
	 * 
	 * @return
	 * @author 汪波
	 * @description 获取最新可用的indexSearcher
	 */
	public IndexSearcher getIndexSearcher(){
		try {
			return this.nrtManager.acquire();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param indexSearcher
	 * @author 汪波
	 * @description 释放indexSearcher
	 */
	public void realseIndexSearcher(IndexSearcher indexSearcher){
		try {
			this.nrtManager.release(indexSearcher);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void commit(){
		try {
			indexWriter.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public TrackingIndexWriter getTrackingIndexWriter(){
		return this.trackingIndexWriter;
	}
	
	public IndexWriter getIndexWriter(){
		return this.indexWriter;
	}
	
	public Analyzer getAnalyzer(){
		return this.analyzer;
	}
}
