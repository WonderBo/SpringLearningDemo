/**
 * @Description: 创建索引
 */
package com.cqu.demo;

import java.io.File;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {
	private IndexWriter indexWriter = null;
	private Directory directory = null;
	
	/**
	 * @param
	 * @author 汪波
	 * @Description: 创建IndexWriter
	 */
	public void createIndexWriter(){
		//指定分词技术，这里使用的是标准分词
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
		//indexWriter的配置信息
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_43, analyzer);
		//索引的打开方式：没有就创建，有就打开
		indexWriterConfig.setOpenMode(OpenMode.CREATE_OR_APPEND);
		try {
			//索引在硬盘上的存储路径
			directory = FSDirectory.open(new File("D://应用/学习应用/Lucene/Index"));
			//如果索引处于锁定状态就解锁
			if(indexWriter.isLocked(directory)){
				indexWriter.unlock(directory);
			}
			//指定索引的操作对象为indexWrite
			indexWriter = new IndexWriter(directory, indexWriterConfig);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param name(名称)
	 * @param context(内容)
	 * @param pageNum(页数)
	 * @author 汪波
	 * @Description； 创建Document并将其读入到索引
	 */
	public void doDocument(String name, String context, int pageNum){
		Document doc = new Document();
		//StringField域，当成一个整体，不会被分词
		doc.add(new StringField("name", name, Store.YES));
		//TextField域，采用指定的分词技术
		doc.add(new TextField("content", context, Store.YES));
		//IntField域
		doc.add(new IntField("pageNum", pageNum, Store.YES));
		
		try {
			//将文档写入索引中
			indexWriter.addDocument(doc);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param
	 * @author 汪波
	 * @Description: 提交并关闭资源
	 */
	public void close(){
		try {
			//将indexWrite操作提交，如果不提交，之前的操作将不会保存到硬盘
			//但是这一步很消耗系统资源，索引执行该操作需要有一定的策略
			indexWriter.commit();
			//关闭资源
			indexWriter.close();
			directory.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
