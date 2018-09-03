/**
 * @description 阅读页信息采集
 */
package com.cqu.crawl.zongheng;

import java.util.HashMap;

import com.cqu.crawl.CrawlBase;
import com.cqu.crawl.zongheng.db.ZonghengDB;
import com.cqu.crawl.zongheng.model.NovelReadModel;
import com.cqu.util.JsonUtil;
import com.cqu.util.ParseMD5;
import com.cqu.util.RegexUtil;

public class ReadPage extends CrawlBase{
	
	private String url;
	//请求头信息
	private static HashMap<String, String> params;
	//章节标题正则
	private static final String TITLEReg = "chapterName=\"(.*?)\"";
	//章节字数正则
	private static final String WORDCOUNTReg = "<span itemprop=\"wordCount\">(\\d*?)</span>";
	//章节正文正则
	private static final String CONTENTReg = "<div id=\"chapterContent\" class=\"content\" itemprop=\"acticleBody\">(.*?)</div>";
	
	static{
		params = new HashMap<String, String>();
		params.put("Referer", "http://www.zongheng.com/");
		params.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
		params.put("Host", "book.zongheng.com");
	}
	
	public ReadPage(String url) {
		// TODO Auto-generated constructor stub
		readPageByGet(url, params, "utf-8");
		this.url = url;
	}
	
	/**
	 * @return
	 * @author 汪波 
	 * @description 获取章节标题
	 */
	public String getTitle(){
		return RegexUtil.getFirstString(getPageSourceCode(), TITLEReg, 1);
	}
	
	/**
	 * @return
	 * @author 汪波
	 * @description 获取章节字数
	 */
	public int getWordCount(){
		String wordCount = RegexUtil.getFirstString(getPageSourceCode(), WORDCOUNTReg, 1);
		return Integer.parseInt(wordCount);
	}
	
	/**
	 * @return
	 * @author 汪波 
	 * @description 获取章节正文
	 */
	public String getContent(){
		return RegexUtil.getFirstString(getPageSourceCode(), CONTENTReg, 1);
	}
	
	/**
	 * 
	 * @return
	 * @author 汪波
	 * @description 返回小说正阅读页信息,完成数据库与 Java 对象之间的对应（并非一一对应，可能多个对象完成对一个表的维护）
	 */
	public NovelReadModel analyzer(){
		NovelReadModel bean = new NovelReadModel();
		bean.setUrl(this.url);
		bean.setId(ParseMD5.parseStrToMD5(bean.getUrl()));
		bean.setTitle(getTitle());
		bean.setWordCount(getWordCount());
		bean.setContent(getContent());
		return bean;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadPage readPage = new ReadPage("http://book.zongheng.com/chapter/635632/35521592.html");
		System.out.println(readPage.getTitle());
		System.out.println(readPage.getWordCount());
		System.out.println(readPage.getContent());
		
		System.out.println(JsonUtil.parseJson(readPage.analyzer()));
		
		NovelReadModel bean = readPage.analyzer();
		bean.setChapterId(17);
		bean.setChapterTime(1485070098);
		ZonghengDB db = new ZonghengDB();
		db.saveNovelRead(bean);
	}

}
