/**
 * @Description: 测试
 */
package com.cqu.demo;

public class Test {
	/**
	 * @param args
	 * @author 汪波
	 * @Description: 主方法
	 */
	public static void main(String[] args) {
		Indexer indexer = new Indexer();
		Searcher searcher = new Searcher();
		
		//创建IndexWriter，并写入索引
		indexer.createIndexWriter();
		indexer.doDocument("lucene", "lucene案例详见网上", 123);
		indexer.doDocument("hadoop", "lucene大数据bigdata", 233);
		indexer.doDocument("NOSQL", "lucene测试案例", 223);
		indexer.doDocument("python", "python测试", 223);
		indexer.close();
		
		//创建IndexSearcher，并搜索字符串
		searcher.createIndexSearcher();
		searcher.doQuery("Lucene案例");
		searcher.close();
	}
}
