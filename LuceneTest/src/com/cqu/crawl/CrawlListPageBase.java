/**
 * @description 更新列表采集基类
 */
package com.cqu.crawl;

import java.util.HashMap;
import java.util.List;

import com.cqu.util.RegexUtil;

public abstract class CrawlListPageBase extends CrawlBase{
	
	//当前页面URL地址
	private String pageUrl;
	
	public CrawlListPageBase(String pageUrl, String charsetName){
		readPageByGet(pageUrl, null, charsetName);
		this.pageUrl = pageUrl;
	}

	public CrawlListPageBase(String pageUrl, HashMap<String, String> params, String charsetName){
		readPageByGet(pageUrl, params, charsetName);
		this.pageUrl = pageUrl;
	}
	
	/**
	 * 
	 * @return
	 * @author 汪波
	 * @description 提取的内容正则表达式
	 */
	public abstract String getUrlRegexStr();
	
	/**
	 * 
	 * @return
	 * @author 汪波
	 * @description 提取的内容在正则中的位置
	 */
	public abstract int getUrlRegexStrNum();
	
	/**
	 * 
	 * @return
	 * @author 汪波
	 * @description 获取下一跳的地址，组装到数组中
	 */
	public List<String> getPageUrl(){
		return RegexUtil.getArrayList(getPageSourceCode(), getUrlRegexStr(), pageUrl, getUrlRegexStrNum());
	}
	
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
