/**
 * @description 将数据库novel信息封装成对象，以加入到索引中
 */
package com.cqu.lucene.index.model.zongheng;

public class NovelIndexBean {
	public static final String INDEX_ID = "id";
	public static final String INDEX_URL = "url";
	public static final String INDEX_NAME = "name";
	public static final String INDEX_NAME_KEY = "name_key";
	public static final String INDEX_AUTHOR = "author";
	public static final String INDEX_AUTHOR_KEY="author_key";
	public static final String INDEX_DESC = "desc";
	public static final String INDEX_TYPR = "type";
	public static final String INDEX_LASTCHAPTER = "lastChapter";
	public static final String INDEX_WORDCOUNT="wordCount";
	public static final String INDEX_KEYWORDS = "keyWords";
	public static final String INDEX_UPDATETIME = "updateTime";
	
	private String id;
	private String url;
	private String name;
	private String author;
	private String desc;
	private String type;
	private String lastChapter;
	private int wordCount;
	private String keyWords;
	private long updateTime;
	
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
	public int getWordCount() {
		return wordCount;
	}
	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
}
