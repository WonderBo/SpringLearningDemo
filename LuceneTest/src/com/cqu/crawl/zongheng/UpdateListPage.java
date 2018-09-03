/**
 * @description 更新列表页信息采集
 */
package com.cqu.crawl.zongheng;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cqu.crawl.CrawlListPageBase;
import com.cqu.crawl.zongheng.db.ZonghengDB;

public class UpdateListPage extends CrawlListPageBase{

	//请求头信息
	private static HashMap<String, String> params;
	
	static{
		params = new HashMap<String, String>();
		params.put("Referer", "http://www.zongheng.com/");
		params.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
		params.put("Host", "book.zongheng.com");
		
	}

	public UpdateListPage(String pageUrl) {
		super(pageUrl, params, "UTF-8");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getUrlRegexStr() {
		// TODO Auto-generated method stub
		return "<a class=\"fs14\" href=\"(.*?)\"";
	}

	@Override
	public int getUrlRegexStrNum() {
		// TODO Auto-generated method stub
		return 1;
	}

	/**
	 * 
	 * @param exceptOther 是否需要排除纵横以外的网址
	 * @return ArrayList
	 * @author 汪波
	 * @description 排除纵横以外的网址
	 */
	public List<String> getPageUrl(boolean exceptOther){
		List<String> urls = getPageUrl();
		if(exceptOther){
			List<String> exceptUrls = new ArrayList<String>();
			for(String url : urls){
				if(url.indexOf("zongheng") > 0){
					exceptUrls.add(url);
				}
			}
			return exceptUrls;
		}
		return urls;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UpdateListPage updateList = new UpdateListPage("http://book.zongheng.com/store.html");
		for(String s : updateList.getPageUrl(true)){
			System.out.println(s);
		}
		
		ZonghengDB db = new ZonghengDB();
		db.saveNovelUrls(updateList.getPageUrl(true));
	}
}
