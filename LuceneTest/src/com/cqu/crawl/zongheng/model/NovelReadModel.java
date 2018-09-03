/**
 * @description 小说阅读页信息
 */
package com.cqu.crawl.zongheng.model;

public class NovelReadModel extends NovelChapterModel{

//	`id` varchar(32) not null,
//	`url` varchar(100),
//	`title` varchar(50),
//	`wordcount` int(11),
//	`chapterid` int(11),
//	`chaptertime` bigint(20),
//	`createtime` bigint(20),
//	`content` text,
//	`updatetime` bigint(20),
	
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
