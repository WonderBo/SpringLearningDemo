/**
 * @description 小说简介信息
 */
package com.cqu.crawl.zongheng.model;

public class NovelInfoModel {

//	`id` varchar(32) not null,
//	`url` varchar(100) not null,
//	`name` varchar(50),
//	`author` varchar(30),
//	`description` text,
//	`type` varchar(20),
//	`lastchapter` varchar(100),
//	`chapterlisturl` varchar(100),
//	`wordcount` int(11),
//	`chaptercount` int(11),
//	`keywords` varchar(100),
//	`createtime` bigint(20),
//	`updatetime` bigint(20),
//	`state` enum('1','0'),
	
	private String id;
	private String url;
	private String name;
	private String author;
	private String desc;
	private String type;
	private String lastChapter;
	private String chapterListUrl;
	private int wordCount;
	private int chapterCount;
	private String keyWords;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLastChapter() {
		return lastChapter;
	}
	public void setLastChapter(String lastChapter) {
		this.lastChapter = lastChapter;
	}
	public String getChapterListUrl() {
		return chapterListUrl;
	}
	public void setChapterListUrl(String chapterListUrl) {
		this.chapterListUrl = chapterListUrl;
	}
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	public int getChapterCount() {
		return chapterCount;
	}
	public void setChapterCount(int chapterCount) {
		this.chapterCount = chapterCount;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
}
