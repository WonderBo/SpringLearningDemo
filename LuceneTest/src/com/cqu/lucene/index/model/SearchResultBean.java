/**
 * @description 查询结果封装类
 */
package com.cqu.lucene.index.model;

import java.util.List;

import org.apache.lucene.document.Document;

public class SearchResultBean {
	//符合条件的总记录条数
	private int count;
	//查询的结果集
	private List<Document> docs;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Document> getDocs() {
		return docs;
	}
	public void setDocs(List<Document> docs) {
		this.docs = docs;
	}
}
