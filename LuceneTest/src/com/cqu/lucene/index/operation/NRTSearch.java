/**
 * @description 索引查询操作
 */
package com.cqu.lucene.index.operation;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;

import com.cqu.lucene.index.manager.IndexManager;
import com.cqu.lucene.index.model.SearchResultBean;

public class NRTSearch {
	private IndexManager indexManager;
	
	public NRTSearch(String indexName){
		this.indexManager = IndexManager.getIndexManager(indexName);
	}
	
	/**
	 * 
	 * @param query 查询条件
	 * @param start 从第几条 从0 开始计数 包括start
	 * @param end 到第几条 不包括end
	 * @return
	 * @author 汪波
	 * @description 分页查询，排序采用默认的排序方式
	 */
	public SearchResultBean search(Query query, int start, int end){
		start = start > 0 ? start : 0;
		end = end >0 ? end : 0;
		if(query == null || start > end || indexManager == null){
			return null;
		}
		SearchResultBean bean = new SearchResultBean();
		List<Document> docs = new ArrayList<Document>();
		IndexSearcher searcher = indexManager.getIndexSearcher();
		try {
			TopDocs topDocs = searcher.search(query, end);
			bean.setCount(topDocs.totalHits);
			end = end > topDocs.totalHits ? topDocs.totalHits : end;
			for(int i = start; i < end; i++){
				docs.add(searcher.doc(topDocs.scoreDocs[i].doc));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			indexManager.realseIndexSearcher(searcher);
		}
		bean.setDocs(docs);
		return bean;
	}
	
	/**
	 * 
	 * @param query 查询条件
	 * @param start 从第几条 从0 开始计数 包括start
	 * @param end 到第几条 不包括end
	 * @param sort 自定义排序方式
	 * @return
	 * @author 汪波
	 * @description 采用自定义排序进行分页查询
	 */
	public SearchResultBean search(Query query, int start, int end, Sort sort){
		start = start > 0 ? start : 0;
		end = end >0 ? end : 0;
		if(query == null || start > end || indexManager == null){
			return null;
		}
		SearchResultBean bean = new SearchResultBean();
		List<Document> docs = new ArrayList<Document>();
		IndexSearcher searcher = indexManager.getIndexSearcher();
		try {
			TopDocs topDocs = searcher.search(query, end, sort);
			bean.setCount(topDocs.totalHits);
			end = end > topDocs.totalHits ? topDocs.totalHits : end;
			for(int i = start; i < end; i++){
				docs.add(searcher.doc(topDocs.scoreDocs[i].doc));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			indexManager.realseIndexSearcher(searcher);
		}
		bean.setDocs(docs);
		return bean;
	}
}
