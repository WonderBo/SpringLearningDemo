/**
 * @description 索引的新增，删除，修改操作
 */
package com.cqu.lucene.index.operation;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.NRTManager.TrackingIndexWriter;
import org.apache.lucene.search.Query;

import com.cqu.lucene.index.manager.IndexManager;

public class NRTIndex {
	private TrackingIndexWriter trackingIndexWriter;
	private String indexName;
	
	public NRTIndex(String indexName){
		this.indexName = indexName;
		this.trackingIndexWriter = IndexManager.getIndexManager(indexName).getTrackingIndexWriter();
	}
	
	/**
	 * 
	 * @param doc
	 * @return
	 * @author 汪波
	 * @description 向索引添加一条记录
	 */
	public boolean addDocument(Document doc){
		try {
			trackingIndexWriter.addDocument(doc);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @param query
	 * @return
	 * @author 汪波
	 * @description 删除符合条件的记录
	 */
	public boolean deleteDocument(Query query){
		try {
			trackingIndexWriter.deleteDocuments(query);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @return
	 * @author 汪波
	 * @description 清空索引中的记录
	 */
	public boolean deleteAll(){
		try {
			trackingIndexWriter.deleteAll();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @param term
	 * @param doc
	 * @return
	 * @description 更新索引中的记录
	 */
	public boolean updateDocument(Term term, Document doc){
		try {
			trackingIndexWriter.updateDocument(term, doc);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * @author 汪波
	 * @description 将内存中的内容提交到磁盘
	 */
	public void commit(){
		IndexManager.getIndexManager(indexName).commit();
	}
}
