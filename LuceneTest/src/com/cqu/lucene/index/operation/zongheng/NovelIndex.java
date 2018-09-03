/**
 * @description 小说索引操作
 */
package com.cqu.lucene.index.operation.zongheng;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;

import com.cqu.lucene.index.model.zongheng.NovelIndexBean;
import com.cqu.lucene.index.operation.NRTIndex;
import com.cqu.util.ParseBean;
import com.cqu.util.ZongHengConstant;

public class NovelIndex extends NRTIndex{

	public NovelIndex() {
		super(ZongHengConstant.INDEX_NAME);
	}
	
	/**
	 * 
	 * @param bean
	 * @return
	 * @author 汪波
	 * @description 将对象解析成document，然后更新到索引中
	 */
	public boolean addNovelIndexBean(NovelIndexBean bean){
		Document doc = ParseBean.parseNovelIndexBeanToDocument(bean);
		if(doc != null){
			//采用updateDocument避免重复导入，否则形成多余的document
			return updateDocument(new Term("id", bean.getId()), doc);
		}
		return false;
	}
}
