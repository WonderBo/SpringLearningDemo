/**
 * @Description: 索引检索
 */
package com.cqu.demo;

import java.io.File;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Searcher {
	private IndexSearcher indexSearcher = null;
	private Directory directory = null;
	private DirectoryReader directoryReader = null;
	
	/**
	 * @param
	 * @author 汪波
	 * @Decription: 创建IndexSearcher
	 */
	public void createIndexSearcher(){
		try {
			//索引硬盘存储路径
			directory = FSDirectory.open(new File("D://应用/学习应用/Lucene/Index"));
			//读取索引
			directoryReader = DirectoryReader.open(directory);
			//创建索引检索对象
			indexSearcher = new IndexSearcher(directoryReader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param queryStr(查询字符串)
	 * @author 汪波
	 * @Description: 根据字符串在索引文件中查询，打印查询结果
	 */
	public void doQuery(String queryStr){
		//分词技术
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
		//查询条件
		QueryParser parser = new QueryParser(Version.LUCENE_43, "content", analyzer);
		try {
			//创建Query
			Query query = parser.parse(queryStr);
			//检索索引，获取符合条件的前10条记录
			TopDocs topDocs = indexSearcher.search(query, 10);
			if(topDocs != null){
				System.out.println("符合条件的文档总数为：" + topDocs.totalHits);
				//循环输出符合条件的文档
				for(ScoreDoc sDoc : topDocs.scoreDocs){
					Document doc = indexSearcher.doc(sDoc.doc);
					System.out.println("name:" + doc.get("name"));
					System.out.println("content:" + doc.get("content"));
					System.out.println("pageNum:" + doc.get("pageNum"));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @param
	 * @return
	 * @author 汪波
	 * @Description: 关闭资源
	 */
	public void close(){
		try {
			//关闭资源
			directoryReader.close();
			directory.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
