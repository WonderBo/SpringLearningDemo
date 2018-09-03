/**
 * 封装条件匹配的对象集，作为最终结果返回
 */
package com.cqu.lucene.index.model.zongheng;

import java.util.List;

public class FinalResultBean {
	//符合条件的总记录条数
	private int count;
	//查询的结果集
	private List<NovelSearchBean> beans;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<NovelSearchBean> getBeans() {
		return beans;
	}
	public void setBeans(List<NovelSearchBean> beans) {
		this.beans = beans;
	}
}
