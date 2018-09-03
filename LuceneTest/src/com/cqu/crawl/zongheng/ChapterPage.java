/**
 * @description 章节列表页信息采集
 */
package com.cqu.crawl.zongheng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cqu.crawl.CrawlBase;
import com.cqu.crawl.zongheng.db.ZonghengDB;
import com.cqu.crawl.zongheng.model.NovelChapterModel;
import com.cqu.util.JsonUtil;
import com.cqu.util.ParseMD5;
import com.cqu.util.RegexUtil;

public class ChapterPage extends CrawlBase{

	private String url;
	//请求头信息
	private static HashMap<String, String> params;
	//提取的内容在正则表达式中的位置
	private static final int[] array = {1, 2, 3, 4};
	//章节信息正则表达式
	private static final String CHAPTERReg = "<td class=\"chapterBean\" chapterid=\"\\d*\" chaptername=\"(第.*?)\" chapterlevel=\"\\d*\" wordnum=\"(\\d*)\" updatetime=\"(\\d*)\"><a href=\"(.*?)\" title=\".*?\">.*?</a></td>";
	
	static{
		params = new HashMap<String, String>();
		params.put("Referer", "http://www.zongheng.com/");
		params.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
		params.put("Host", "book.zongheng.com");
	}
	
	public ChapterPage(String url){
		readPageByGet(url, params, "utf-8");
		this.url = url;
	}
	
	/**
	 * @return
	 * @author 汪波 
	 * @description 获取章节信息：章节名、字数、更新时间、阅读页地址
	 */
	public List<String[]> getChapters(){
		return RegexUtil.getList(getPageSourceCode(), CHAPTERReg, array);
	}
	
	/**
	 * 
	 * @param array 章节其它信息所组成的数组
	 * @param i 章节id
	 * @return
	 * @author 汪波
	 * @description 返回小说章节信息
	 */
	private NovelChapterModel analyzer(String[] array, int i){
		NovelChapterModel bean = new NovelChapterModel();
		bean.setUrl(array[3]);
		bean.setId(ParseMD5.parseStrToMD5(bean.getUrl()));
		bean.setTitle(array[0]);
		bean.setWordCount(Integer.parseInt(array[1]));
		bean.setChapterTime(Long.parseLong(array[2]));
		bean.setChapterId(i);
		return bean;
	}
	
	/**
	 * 
	 * @return
	 * @author 汪波
	 * @description 返回小说章节列表页信息,完成数据库与 Java 对象之间的对应（并非一一对应，可能多个对象完成对一个表的维护）
	 */
	public List<NovelChapterModel> analyzer(){
		List<NovelChapterModel> list = new ArrayList<NovelChapterModel>();
		List<String[]> arrays = getChapters();
		int i = 0;
		for(String[] array : arrays){
			i++;
			list.add(analyzer(array, i));
		}
		return list;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChapterPage chapterPage = new ChapterPage("http://book.zongheng.com/showchapter/635632.html");
		for(String[] ss : chapterPage.getChapters()){
			for(String s : ss){
				System.out.println(s);
			}
			System.out.println("------------------------");
		}
		
		System.out.println(JsonUtil.parseJson(chapterPage.analyzer()));
		
		ZonghengDB db = new ZonghengDB();
		db.saveNovelChapter(chapterPage.analyzer(), "002aa6d3ef99da53d7d39d778d7e5976");
	}

}
