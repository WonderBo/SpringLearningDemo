/**
 * @description (1)将从数据库中映射出来的对象（ORM）解析成为Document（Document与数据库表存在对应关系）
 * @description (2)将从索引中查询出来的Document解析为对应的对象
 */
package com.cqu.util;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import com.cqu.lucene.index.model.zongheng.NovelIndexBean;
import com.cqu.lucene.index.model.zongheng.NovelSearchBean;

public class ParseBean {
	/**
	 * 
	 * @param bean 小说(待索引)对象
	 * @return doc 小说文档
	 * @author 汪波
	 * @description 将从数据库中映射出来的对象解析成为Document
	 */
	public static Document parseNovelIndexBeanToDocument(NovelIndexBean bean){
		Document doc = new Document();
		if(bean == null){
			return null;
		}
		if(bean.getId() == null){
			return null;
		}else{
			doc.add(new StringField(NovelIndexBean.INDEX_ID, bean.getId(), Store.YES));
		}
		if(bean.getUrl() != null){
			doc.add(new StringField(NovelIndexBean.INDEX_URL, bean.getUrl(), Store.YES));
		}
		if(bean.getName() != null){
			doc.add(new StringField(NovelIndexBean.INDEX_NAME, bean.getName(), Store.YES));
			doc.add(new TextField(NovelIndexBean.INDEX_NAME_KEY, bean.getName(), Store.YES));
		}
		if(bean.getAuthor() != null){
			doc.add(new StringField(NovelIndexBean.INDEX_AUTHOR, bean.getAuthor(), Store.YES));
			doc.add(new TextField(NovelIndexBean.INDEX_AUTHOR_KEY, bean.getAuthor(), Store.YES));
		}
		if(bean.getType() != null){
			doc.add(new StringField(NovelIndexBean.INDEX_TYPR, bean.getType(), Store.YES));
		}
		if(bean.getKeyWords() != null){
			doc.add(new TextField(NovelIndexBean.INDEX_KEYWORDS, bean.getKeyWords(), Store.YES));
		}
		doc.add(new LongField(NovelIndexBean.INDEX_UPDATETIME, bean.getUpdateTime(), Store.YES));
		
		return doc;
	}
	
	/**
	 * 
	 * @param doc 小说查询匹配文档
	 * @return bean 小说查询对象
	 * @author 汪波
	 * @description 从索引中查询出来的Document解析为对应的对象
	 */
	public static NovelSearchBean parseDocumentToNovelSearchBean(Document doc){
		NovelSearchBean bean = new NovelSearchBean();
		if(doc == null){
			return null;
		}
		//只获取id,name,author值
		bean.setId(doc.get(NovelIndexBean.INDEX_ID));
		bean.setName(doc.get(NovelIndexBean.INDEX_NAME));
		bean.setAuthor(doc.get(NovelIndexBean.INDEX_AUTHOR));
		return bean;
	}
}
