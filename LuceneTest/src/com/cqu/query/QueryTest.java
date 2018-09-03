/**
 * @Description: 查询
 */
package com.cqu.query;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.Version;

public class QueryTest {
	//搜索关键词
	private static String key = "lucene";
	//搜索单个域的域名
	private static String field = "name";
	//Query创建过程中的分词技术
	private static String[] fields = {"name","content"};
	/**
	 * @param args
	 * @author 汪波
	 * @Description: 输出查询
	 */
	public static void main(String[] args){
		//Query创建过程中的分词技术
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
		Query query = null;
		
		//对单个域创建查询语句
		QueryParser queryParser = new QueryParser(Version.LUCENE_43, field, analyzer);
		try {
			query = queryParser.parse(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(QueryParser.class + query.toString());
		
		//对多个域创建查询语句
		MultiFieldQueryParser mfQueryParser = new MultiFieldQueryParser(Version.LUCENE_43, fields, analyzer);
		try {
			query = mfQueryParser.parse(key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(MultiFieldQueryParser.class + query.toString());
		
		//词条查询语句
		query = new TermQuery(new Term(field, key));
		System.out.println(TermQuery.class + query.toString());
		
		//前缀查询语句
		query = new PrefixQuery(new Term(field, key));
		System.out.println(PrefixQuery.class + query.toString());
		
		//短语查询语句(注：此处未用Query的多态是因为Query中不含有setSlop()方法)
		PhraseQuery phraseQuery = new PhraseQuery();
		//设置短语之间的最大距离
		phraseQuery.setSlop(2);
		phraseQuery.add(new Term(field, "lucene"));
		phraseQuery.add(new Term(field, "案例"));
		System.out.println(PhraseQuery.class + phraseQuery.toString());
		
		//通配符查询语句，Lucene中有 * ? 两个通配符， *表示任意多个字符，?表示一个任意字符
		query = new WildcardQuery(new Term(field, "lucene?"));
		System.out.println(WildcardQuery.class + query.toString());
		
		//字符串范围查询
		query = TermRangeQuery.newStringRange(field, "abc", "azz", false, false);
		System.out.println(TermRangeQuery.class + query.toString());
		
		//布尔查询(多种查询的组合)
		BooleanQuery booleanQuery = new BooleanQuery();
		booleanQuery.add(new TermQuery(new Term(field, "lucene")), Occur.SHOULD);
		booleanQuery.add(new TermQuery(new Term(field, "案例")), Occur.MUST);
		booleanQuery.add(new TermQuery(new Term(field, "开发")), Occur.MUST_NOT);
		System.out.println(BooleanQuery.class + booleanQuery.toString());
	}
	
}
