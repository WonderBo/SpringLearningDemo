/**
 * @description 数据库操作
 */
package com.cqu.crawl.zongheng.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cqu.crawl.zongheng.model.CrawlListInfo;
import com.cqu.crawl.zongheng.model.NovelChapterModel;
import com.cqu.crawl.zongheng.model.NovelInfoModel;
import com.cqu.crawl.zongheng.model.NovelReadModel;
import com.cqu.db.DBServer;
import com.cqu.lucene.index.model.zongheng.NovelIndexBean;
import com.cqu.lucene.index.operation.zongheng.NovelIndex;
import com.cqu.util.JsonUtil;
import com.cqu.util.ParseMD5;

public class ZonghengDB {
	
	private static final String POOLNAME = "proxool.zonghengnovel";
	
	//将封装的java对象或者属性映射到数据库中
	@SuppressWarnings("unused")
	private void method(){
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			//数据库操作
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}
	
	/**
	 * 
	 * @return List<CrawlListInfo>
	 * @author 汪波
	 * @description 获取更新列表页地址信息
	 */
	public List<CrawlListInfo> getCrawlListInfos(){
		List<CrawlListInfo> novels = new ArrayList<CrawlListInfo>();
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "select * from crawllist where state = \"1\"";
			ResultSet rs = dbServer.select(sql);
			while(rs.next()){
				CrawlListInfo novel = new CrawlListInfo();
				novel.setUrl(rs.getString("url"));
				novel.setInfo(rs.getString("info"));
				novel.setFrequency(rs.getInt("frequency"));
				novels.add(novel);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
		return novels;
	}
	
	/**
	 * 
	 * @param id
	 * @param url
	 * @author 汪波
	 * @description 将在更新列表页采集到的一部小说简介页的url保存到数据库中
	 */
	private void insertNovelUrl(String id, String url){
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			//SQL语句未知参数传递值，注意字段与i（从1开始）一一对应
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			int i = 1;
			params.put(i++, id);
			params.put(i++, url);
			Long now = System.currentTimeMillis();
			params.put(i++, now);
			params.put(i++, now);
			params.put(i++, "1");
			
			dbServer.insert("novelinfo", "id,url,createtime,updatetime,state", params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param state
	 * @author 汪波
	 * @description 更新小说简介信息的state值
	 */
	public void updateNovelState(String id, String state){
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "update novelinfo set state = \"" + state +"\" where id = \"" + id + "\"";
			
			dbServer.update(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @author 汪波
	 * @description 判断小说简介信息是否存在
	 */
	private boolean haveNovelInfo(String id){
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "select sum(1) as count from novelinfo where id = " + "\"" + id + "\"";
			ResultSet rs = dbServer.select(sql);
			if(rs.next()){
				int count = rs.getInt("count");
				return count > 0;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
		return true;
	}
	
	/**
	 * 
	 * @param urls
	 * @author 汪波
	 * @description 将在更新列表页采集到的所有小说的简介页url信息保存到数据库中
	 */
	public void saveNovelUrls(List<String> urls){
		if(urls == null || urls.size() < 1){
			return;
		}
		for(String url : urls){
			String id = ParseMD5.parseStrToMD5(url);
			if(haveNovelInfo(id)){
				updateNovelState(id, "1");
			}else{
				insertNovelUrl(id, url);
			}
		}
	}
	
	/**
	 * 
	 * @param bean
	 * @author 汪波
	 * @description 将小说简介页信息抓取以更新小说的简介信息
	 */
	public void updateNovelInfo(NovelInfoModel bean){
		if(bean == null){
			return;
		}
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			int i = 1;
			params.put(i++, bean.getName());
			params.put(i++, bean.getAuthor());
			params.put(i++, bean.getDesc());
			params.put(i++, bean.getType());
			params.put(i++, bean.getLastChapter());
			params.put(i++, bean.getChapterListUrl());
			params.put(i++, bean.getWordCount());
			params.put(i++, bean.getChapterCount());
			params.put(i++, bean.getKeyWords());
			Long now = System.currentTimeMillis();
			params.put(i++, now);
			params.put(i++, "0");
			
			String columns = "name,author,description,type,lastchapter,chapterlisturl,wordcount,chaptercount,keywords,updatetime,state";
			
			String condition = "id = \"" + bean.getId() + "\"";
			
			dbServer.update("novelinfo", columns, condition, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}
	
	/**
	 * 
	 * @param bean
	 * @param novelId
	 * @author 汪波
	 * @description 将章节列表页的单个章节的信息保存到数据库中
	 */
	private void insertNovelChapter(NovelChapterModel bean, String novelId){
		if(bean == null){
			return;
		}
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			int i = 1;
			params.put(i++, bean.getId());
			params.put(i++, novelId);
			params.put(i++, bean.getUrl());
			params.put(i++, bean.getTitle());
			params.put(i++, bean.getWordCount());
			params.put(i++, bean.getChapterId());
			params.put(i++, bean.getChapterTime());
			long now = System.currentTimeMillis();
			params.put(i++, now);
			params.put(i++, "1");
			String columns = "id,novelid,url,title,wordcount,chapterid,chaptertime,createtime,state";
			dbServer.insert("novelchapter", columns, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @author 汪波
	 * @description 判断章节信息时候存在
	 */
	private boolean haveNovelChapter(String id){
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "select sum(1) as count from novelchapter where id = " + "\"" + id + "\"";
			ResultSet rs = dbServer.select(sql);
			if(rs.next()){
				int count = rs.getInt("count");
				return count > 0;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
		return true;
	}
	
	/**
	 * 
	 * @param beans
	 * @param novelId
	 * @author 汪波
	 * @description 将章节列表页采集到的全部章节的信息保存到数据库中
	 */
	public void saveNovelChapter(List<NovelChapterModel> beans, String novelId){
		if(beans == null){
			return;
		}
		for(NovelChapterModel bean : beans){
			if(!haveNovelChapter(bean.getId())){
				insertNovelChapter(bean, novelId);
			}
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @author 汪波
	 * @description 判断阅读页信息是否存在
	 */
	private boolean haveNovelRead(String id){
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "select sum(1) as count from novelchapterdetail where id='" + id + "'";
			ResultSet rs = dbServer.select(sql);
			if (rs.next()) {
				int count = rs.getInt("count");
				return count > 0;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
		return true;
	}
	
	/**
	 * 
	 * @param bean
	 * @author 汪波
	 * @description 将采集到的阅读页信息保存到数据库中
	 */
	public void saveNovelRead(NovelReadModel bean){
		if(bean == null){
			return;
		}
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			if(haveNovelRead(bean.getId())){
				return;
			}
			HashMap<Integer, Object> params = new HashMap<Integer, Object>();
			int i = 1;
			params.put(i++, bean.getId());
			params.put(i++, bean.getUrl());
			params.put(i++, bean.getTitle());
			params.put(i++, bean.getWordCount());
			params.put(i++, bean.getChapterId());
			params.put(i++, bean.getContent());
			params.put(i++, bean.getChapterTime());
			long now = System.currentTimeMillis();
			params.put(i++, now);
			params.put(i++, now);
			
			String columns = "id,url,title,wordcount,chapterid,content,chaptertime,createtime,updatetime";
			
			dbServer.insert("novelchapterdetail", columns, params);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param state
	 * @author 汪波
	 * @description 更新章节信息的state值
	 */
	public void updateChapterState(String id, String state){
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "update novelchapter set state = \"" + state +"\" where id = \"" + id + "\"";
			dbServer.update(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}
	
	/**
	 * 
	 * @param state
	 * @return
	 * @author 汪波
	 * @description 随机获取小说简介页url
	 */
	public String getRandomIntroPageUrl(String state){
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "select * from novelinfo where state = \"" + state + "\" order by rand() limit 1";
			ResultSet rs = dbServer.select(sql);
			while(rs.next()){
				return rs.getString("url");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
		return null;
	}
	
	/**
	 * 
	 * @param state
	 * @return
	 * @author 汪波
	 * @description 随机获取小说某一章节信息
	 */
	public NovelChapterModel getRandomChapter(String state){
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			NovelChapterModel bean = new NovelChapterModel();
			String sql = "select * from novelchapter where state = \"" + state + "\" order by rand() limit 1";
			ResultSet rs = dbServer.select(sql);
			while(rs.next()){
				bean.setId(rs.getString("id"));
				bean.setUrl(rs.getString("url"));
				bean.setChapterId(rs.getInt("chapterid"));
				bean.setWordCount(rs.getInt("wordcount"));
				bean.setChapterTime(rs.getLong("chaptertime"));
				return bean;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
		return null;
	}
	
	/**
	 * @author 汪波
	 * @description 将数据库中表的记录导入到索引文件中，表 >> NovelIndexBean >> Document >> Index
	 * @description 数据库的（一或多个）表对应索引的index文件，表的行对应索引的document，表的列对应索引的field
	 */
	public void dataImportToIndex(){
		DBServer dbServer = new DBServer(POOLNAME);
		try {
			String sql = "select * from novelinfo";
			ResultSet rs = dbServer.select(sql);
			while(rs.next()){
				NovelIndexBean bean = new NovelIndexBean();
				bean.setId(rs.getString("id"));
				bean.setName(rs.getString("name"));
				bean.setAuthor(rs.getString("author"));
				bean.setUrl(rs.getString("url"));
				bean.setDesc(rs.getString("description"));
				bean.setType(rs.getString("type"));
				bean.setLastChapter(rs.getString("lastchapter"));
				bean.setWordCount(rs.getInt("wordcount"));
				bean.setKeyWords(rs.getString("keywords"));
				bean.setUpdateTime(Long.parseLong(rs.getString("updatetime")));
				
				NovelIndex index = new NovelIndex();
				index.addNovelIndexBean(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbServer.close();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZonghengDB db = new ZonghengDB();
		System.out.println(JsonUtil.parseJson(db.getCrawlListInfos()));
		
		System.out.println(db.getRandomIntroPageUrl("1"));
		
		System.out.println(JsonUtil.parseJson(db.getRandomChapter("1")));
	}

}
