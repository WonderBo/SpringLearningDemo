/**
 * @description 爬虫程序入口地址信息：更新列表页信息
 */
package com.cqu.crawl.zongheng.model;

public class CrawlListInfo {
	
//	`id` int(20) not null AUTO_INCREMENT,
//	`url` varchar(100) not null,
//	`state` enum('1','0'),
//	`info` varchar(100),
//	`frequency` int(11) default '60',
	
	private String url;
	private String info;
	private int frequency;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
