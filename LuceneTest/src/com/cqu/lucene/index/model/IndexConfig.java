/**
 * @description 管理多个索引配置
 */
package com.cqu.lucene.index.model;

import java.util.HashSet;

public class IndexConfig {

	//系统中配置多个索引
	private static HashSet<IndexBean> configBeans;
	
	//内部静态类
	private static class DefaultIndexConfig{
		private static final HashSet<IndexBean> defaultConfigBeans = new HashSet<IndexBean>();
		static{
			IndexBean bean = new IndexBean();
			defaultConfigBeans.add(bean);
		}
	}
	
	public static HashSet<IndexBean> getConfig(){
		if(configBeans == null){
			//如果configBeans为空，返回系统默认值
			return DefaultIndexConfig.defaultConfigBeans;
		}
		return configBeans;
	}
	
	public static void setConfig(HashSet<IndexBean> configBeans){
		//设置系统索引配置
		IndexConfig.configBeans = configBeans;
	}
}
