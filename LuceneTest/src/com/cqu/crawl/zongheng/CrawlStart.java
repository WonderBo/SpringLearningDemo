/**
 * @description 小说信息采集入口
 */
package com.cqu.crawl.zongheng;

import java.util.List;

import com.cqu.crawl.zongheng.db.ZonghengDB;
import com.cqu.crawl.zongheng.model.CrawlListInfo;

public class CrawlStart {

	//更新列表页
	private static boolean updateListFlag = false;
	//简介页
	private static boolean introPageFlag = false;
	//阅读页
	private static boolean readPageFalg = false;
	
	//简介页线程数目
	private static int introPageNum = 2;
	//阅读页线程数目
	private static int readPageNum = 10;
	
	/**
	 * @author 汪波
	 * @description 启动更新列表页采集线程
	 */
	public void startUpdateList(){
		if(updateListFlag){
			return;
		}
		updateListFlag = true;
		ZonghengDB db = new ZonghengDB();
		List<CrawlListInfo> entrances = db.getCrawlListInfos();
		if(entrances == null){
			return;
		}
		for(CrawlListInfo entrance : entrances){
			if(entrance.getUrl() == null || "".equals(entrance.getUrl())){
				continue;
			}
			UpdateListThread thread = new UpdateListThread(entrance.getInfo(), entrance.getUrl(), entrance.getFrequency());
			thread.start();
		}
	}
	
	/**
	 * @author 汪波
	 * @description 启动简介页采集线程
	 */
	public void startIntroPage(){
		if(introPageFlag){
			return;
		}
		introPageFlag = true;
		for(int i = 0; i < introPageNum; i++){
			IntroPageThread thread = new IntroPageThread("introPage" + i);
			thread.start();
		}
	}
	
	/**
	 * @author 汪波
	 * @description 启动阅读页采集线程
	 */
	public void startReadPage(){
		if(readPageFalg){
			return;
		}
		readPageFalg = true;
		for(int i = 0; i < readPageNum; i++){
			ReadPageThread thread = new ReadPageThread("redPage" + i);
			thread.start();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CrawlStart start = new CrawlStart();
		start.startUpdateList();
		start.startIntroPage();
		start.startReadPage();
	}

}
