/**
 * @description 小说查询测试
 */
package com.cqu.test;

import com.cqu.crawl.zongheng.db.ZonghengDB;
import com.cqu.lucene.index.init.IndexInit;
import com.cqu.lucene.index.operation.zongheng.NovelIndex;
import com.cqu.lucene.index.operation.zongheng.NovelSearch;
import com.cqu.util.JsonUtil;

public class NovelSearchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//索引初始化
		IndexInit.initIndex();
		//数据库表的数据导入到索引文件(采用updateDocument方法避免重复导入，否则形成多余的document)
		new ZonghengDB().dataImportToIndex();
		//将内存中的索引保持到硬盘上
		new NovelIndex().commit();
		NovelSearch search = new NovelSearch();
		//在索引中进行搜索
		System.out.println(JsonUtil.parseJson(search.searchNovelName("name")));
		System.out.println(JsonUtil.parseJson(search.searchNovelName("碎界星墟", 0, 10)));
		System.out.println(JsonUtil.parseJson(search.searchNovelUrl("657023", 0, 10)));
	}
}
