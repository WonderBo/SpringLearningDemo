/**
 * @description 简介页信息采集
 */
package com.cqu.crawl.zongheng;

import java.util.HashMap;

import com.cqu.crawl.CrawlBase;
import com.cqu.crawl.zongheng.db.ZonghengDB;
import com.cqu.crawl.zongheng.model.NovelInfoModel;
import com.cqu.util.JsonUtil;
import com.cqu.util.ParseMD5;
import com.cqu.util.RegexUtil;

public class IntroPage extends CrawlBase{
	
	private String url;
	//请求头信息
	private static HashMap<String, String> params;
	//作者信息正则
	private static final String AUTHORReg = "<meta name=\"og:novel:author\" content=\"(.*?)\"/> ";
	//书名信息正则
	private static final String NAMEReg = "<meta name=\"og:novel:book_name\" content=\"(.*?)\"/>";
	//简介信息正则
	private static final String DESCReg = "<meta property=\"og:description\" content=\"(.*?)\"/>";
	//分类信息正则
	private static final String TYPEReg = "<meta name=\"og:novel:category\" content=\"(.*?)\"/> ";
	//最新章节信息正则
	private static final String LASTCHAPTERReg = "<a class=\"chap\" href=\".*?\">(.*?)<p>";
	//字数信息正则
	private static final String WORDCOUNTReg = "<span title=\"(\\d*?)字\">";
	//关键字html信息正则
	//提取内容较复杂时，可以进行二次提取，先提取大范围的内容，再抽取小内容
	private static final String KEYWORDStrReg = "<div class=\"keyword\">(.*?)</div>";
	//关键字信息正则
	private static final String KEYWORDReg = "<a.*?>(.*?)</a>";
	//章节列表URL信息正则
	private static final String CHAPTERLISTURLReg = "<meta name=\"og:novel:read_url\" content=\"(.*?)\"/> ";
	
	static{
		params = new HashMap<String, String>();
		params.put("Referer", "http://www.zongheng.com/");
		params.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
		params.put("Host", "book.zongheng.com");
	}
	
	public IntroPage(String url) {
		// TODO Auto-generated constructor stub
		readPageByGet(url, params, "utf-8");
		this.url = url;
	}
	
	/**
	 * @return
	 * @author 汪波 
	 * @description 获取小说书名
	 */
	private String getName(){
		return RegexUtil.getFirstString(getPageSourceCode(), NAMEReg, 1);
	}
	
	/**
	 * @return
	 * @author 汪波 
	 * @description 获取小说作者
	 */
	private String getAuthor(){
		return RegexUtil.getFirstString(getPageSourceCode(), AUTHORReg, 1);
	}
	
	/**
	 * @return
	 * @author 汪波 
	 * @description 获取小说简介
	 */
	private String getDesc(){
		return RegexUtil.getFirstString(getPageSourceCode(), DESCReg, 1);
	}
	
	/**
	 * @return
	 * @author 汪波
	 * @description 获取小说分类
	 */
	private String getType(){
		return RegexUtil.getFirstString(getPageSourceCode(), TYPEReg, 1);
	}
	
	/**
	 * @return
	 * @author 汪波
	 * @description 获取小说最新章节
	 */
	private String getLastCharpter(){
		return RegexUtil.getFirstString(getPageSourceCode(), LASTCHAPTERReg, 1);
	}
	
	/**
	 * @return
	 * @author 汪波  
	 * @description 获取小说字数
	 */
	private int getWordCount(){
		String wordCount = RegexUtil.getFirstString(getPageSourceCode(), WORDCOUNTReg, 1);
		return Integer.parseInt(wordCount);
	}
	
	/**
	 * @return
	 * @author 汪波
	 * @description 获取小说章节列表页URL
	 */
	private String getChapetrListUrl(){
		return RegexUtil.getFirstString(getPageSourceCode(), CHAPTERLISTURLReg, 1);
	}
	
	/**
	 * @return
	 * @author 汪波 
	 * @description 获取关键字html
	 */
	private String getKeyWordStr(){
		return RegexUtil.getFirstString(getPageSourceCode(), KEYWORDStrReg, 1);
	}
	
	/**
	 * @return
	 * @author 汪波 
	 * @description 获取小说关键字
	 */
	private String getKeyWord(){
		return RegexUtil.getString(getKeyWordStr(), KEYWORDReg, " ", 1);
	}
	
	/**
	 * 
	 * @return
	 * @author 汪波
	 * @description 返回小说简介信息,完成数据库与 Java 对象之间的对应（并非一一对应，可能多个对象完成对一个表的维护）
	 */
	public NovelInfoModel analyzer(){
		NovelInfoModel bean = new NovelInfoModel();
		bean.setUrl(this.url);
		bean.setId(ParseMD5.parseStrToMD5(bean.getUrl()));
		bean.setName(getName());
		bean.setAuthor(getAuthor());
		bean.setDesc(getDesc());
		bean.setType(getType());
		bean.setLastChapter(getLastCharpter());
		bean.setChapterListUrl(getChapetrListUrl());
		bean.setWordCount(getWordCount());
		bean.setKeyWords(getKeyWord());
		return bean;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntroPage introPage = new IntroPage("http://book.zongheng.com/book/635632.html");
		System.out.println(introPage.getName());
		System.out.println(introPage.getAuthor());
		System.out.println(introPage.getDesc());
		System.out.println(introPage.getType());
		System.out.println(introPage.getLastCharpter());
		System.out.println(introPage.getWordCount());
		System.out.println(introPage.getKeyWord());
		System.out.println(introPage.getChapetrListUrl());
		
		System.out.println(JsonUtil.parseJson(introPage.analyzer()));
		
		ZonghengDB db = new ZonghengDB();
		db.updateNovelInfo(introPage.analyzer());
	}

}
