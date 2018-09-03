/**
 * @description 初始化小说索引
 */
package com.cqu.lucene.index.init;

import java.util.HashMap;
import java.util.HashSet;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

import com.cqu.lucene.index.model.IndexBean;
import com.cqu.lucene.index.model.IndexConfig;
import com.cqu.lucene.index.model.zongheng.NovelIndexBean;
import com.cqu.util.ZongHengConstant;

public class IndexInit {
	/**
	 * @author 汪波
	 * @description 初始化小说索引
	 */
	public static void initIndex(){
		HashSet<IndexBean> beans = new HashSet<IndexBean>();
		IndexBean bean = new IndexBean();
		bean.setIndexName(ZongHengConstant.INDEX_NAME);
		bean.setIndexPath("D:/应用/学习应用/Lucene/NovelIndex");
		
		HashMap<String, Analyzer> fieldAnalyzers = new HashMap<String, Analyzer>();
		fieldAnalyzers.put(NovelIndexBean.INDEX_KEYWORDS, new WhitespaceAnalyzer(Version.LUCENE_43));
		Analyzer analyzer = new PerFieldAnalyzerWrapper(new StandardAnalyzer(Version.LUCENE_43), fieldAnalyzers);
		bean.setAnalyzer(analyzer);
		beans.add(bean);
		IndexConfig.setConfig(beans);
	}
}
