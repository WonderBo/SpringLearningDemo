/**
 * @description 更新列表页信息采集线程
 */
package com.cqu.crawl.zongheng;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cqu.crawl.zongheng.db.ZonghengDB;

public class UpdateListThread extends Thread{
	
	private boolean flag = false;
	private String url = "";
	private int frequency;
	
	/**
	 * 
	 * @param name 线程名
	 * @param url 更新列表页url
	 * @param frequency 采集频率
	 * @author 汪波
	 * @description 初始化线程
	 */
	public UpdateListThread(String name, String url, int frequency){
		super(name);
		this.url = url;
		this.frequency = frequency;
	}
	
	@Override
	public void run(){
		flag = true;
		ZonghengDB db = new ZonghengDB();
		while(flag){
			try {
				UpdateListPage updateListPage = new UpdateListPage(this.url);
				//获取更新列表页URL信息
				List<String> list = updateListPage.getPageUrl(true);
				db.saveNovelUrls(list);
				//线程睡眠30s
				TimeUnit.SECONDS.sleep(this.frequency);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UpdateListThread thread = new UpdateListThread("updateListThread", "http://book.zongheng.com/store.html", 30);
		thread.start();
	}

}
