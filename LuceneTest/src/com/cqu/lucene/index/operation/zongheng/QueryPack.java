/**
 * @description 查询方法
 */
package com.cqu.lucene.index.operation.zongheng;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.Version;

import com.cqu.lucene.index.manager.IndexManager;

public class QueryPack {
	private Analyzer analyzer;

	public QueryPack(String indexName){
		this.analyzer = IndexManager.getIndexManager(indexName).getAnalyzer();
	}

	/**
	 * 
	 * @param fieldName 查找域名
	 * @param searchValue 查询词
	 * @return query
	 * @author 汪波
	 * @description 单域查询
	 */
	public Query getOneFieldQuery(String fieldName, String searchValue){
		//参数验证
		if(fieldName == null || searchValue == null){
			return null;
		}
		QueryParser parser = new QueryParser(Version.LUCENE_43, fieldName, this.analyzer);
		try {
			Query query = parser.parse(searchValue);
			return query;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param fieldName 查找域名
	 * @param searchValue 查询词
	 * @return query
	 * @author 汪波
	 * @description 字符串(Term词条)查询
	 */
	public Query getStringQuery(String fieldName, String searchValue){
		//参数验证
		if(fieldName == null || searchValue == null){
			return null;
		}
		Query query = new TermQuery(new Term(fieldName, searchValue));
		return query;
	}

	/**
	 * 
	 * @param fieldName 查找域名
	 * @param searchValue 查询词
	 * @return query
	 * @author 汪波
	 * @description 通配符查询
	 */
	public Query getWildcardQuery(String fieldName, String searchValue){
		//参数验证
		if(fieldName == null || searchValue == null){
			return null;
		}
		searchValue = "*" + searchValue + "*";
		Query query = new WildcardQuery(new Term(fieldName, searchValue));
		return query;
	}
}
