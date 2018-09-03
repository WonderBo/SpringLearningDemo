/**
 * @description IndexManager测试（先初始化，在做索引，搜索操作）
 */
package com.cqu.test;

import java.util.HashSet;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.util.Version;

import com.cqu.lucene.index.manager.IndexManager;
import com.cqu.lucene.index.model.IndexBean;
import com.cqu.lucene.index.model.IndexConfig;
import com.cqu.lucene.index.model.SearchResultBean;
import com.cqu.lucene.index.operation.NRTIndex;
import com.cqu.lucene.index.operation.NRTSearch;

public class IndexManagerTest {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		HashSet<IndexBean> set = new HashSet<IndexBean>();
		for(int i = 0; i < 4; i++){
			IndexBean bean = new IndexBean();
			bean.setIndexPath("D:/应用/学习应用/Lucene/IndexTest0");
			bean.setIndexName("test" + i);
			set.add(bean);
		}
		IndexConfig.setConfig(set);
		//实时索引测试
		//IndexManager indexManager0 = IndexManager.getIndexManager("test0");
		//索引操作测试
		String indexName = "test0";
		//索引添加操作
		NRTIndex nrtIndex = new NRTIndex(indexName);
		//添加第一个doc
		Document doc = new Document();
		doc.add(new StringField("id", "1", Store.YES));
		doc.add(new TextField("content", "Lucene学习", Store.YES));
		nrtIndex.addDocument(doc);
		//添加第二个doc
		doc = new Document();
		doc.add(new StringField("id", "2", Store.YES));
		doc.add(new TextField("content", "案例开发", Store.YES));
		nrtIndex.addDocument(doc);
		//将索引写入磁盘
		nrtIndex.commit();
		System.out.println("已经向索引中添加两条记录");
		//索引查询操作
		NRTSearch nrtSearch = new NRTSearch(indexName);
		QueryParser parser = new QueryParser(Version.LUCENE_43, "content", IndexManager.getIndexManager(indexName).getAnalyzer());
		Query query = parser.parse("Lucene学习案例开发测试");
		SearchResultBean bean = nrtSearch.search(query, 0, 10);
		System.out.println("第一次查询" + bean.getCount());
		//索引修改操作
		doc = new Document();
		doc.add(new StringField("id", "2", Store.YES));
		doc.add(new TextField("content", "无关内容", Store.YES));
		Term term = new Term("id", "2");
		nrtIndex.updateDocument(term, doc);
		nrtIndex.commit();
		System.out.println("第一次修改一条索引记录");
		bean = nrtSearch.search(query, 0, 10);
		System.out.println("第二次查询" + bean.getCount());
	}
}
