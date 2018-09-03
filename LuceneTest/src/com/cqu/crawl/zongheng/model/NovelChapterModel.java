/**
 * @description 小说章节信息
 */
package com.cqu.crawl.zongheng.model;

public class NovelChapterModel {

//	`id` varchar(32) not null,
//	`novelid` varchar(32) not null,
//	`url` varchar(100),
//	`title` varchar(50),
//	`wordcount` int(11),
//	`chapterid` int(11),
//	`chaptertime` bigint(20),
//	`createtime` bigint(20),
//	`state` enum('1','0'),
	
	private String id;
	private String url;
	private String title;
	private int wordCount;
	private int chapterId;
	private long chapterTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public long getChapterTime() {
		return chapterTime;
	}
	public void setChapterTime(long chapterTime) {
		this.chapterTime = chapterTime;
	}
}
