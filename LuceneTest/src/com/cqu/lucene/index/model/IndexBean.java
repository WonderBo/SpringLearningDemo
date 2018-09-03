/**
 * @description 创建索引配置
 */
package com.cqu.lucene.index.model;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.util.Version;

public class IndexBean {
	
	//索引名
	private String indexName = "index";
	//索引硬盘路径
	private String indexPath = "/index/";
	//索引分词器
	private Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
	//索引重读频率（最大和最小重读时间）
	private double indexReopenMaxStaleSec = 10;
	private double indexReopenMinStaleSec = 0.025;
	//索引写入磁盘（索引提交）时间间隔
	private int indexCommitSeconds = 60;
	
	public String getIndexName() {
		return indexName;
	}
	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getIndexPath() {
		return indexPath;
	}
	public void setIndexPath(String indexPath) {
		this.indexPath = indexPath;
	}
	public Analyzer getAnalyzer() {
		return analyzer;
	}
	public void setAnalyzer(Analyzer analyzer) {
		this.analyzer = analyzer;
	}
	public double getIndexReopenMaxStaleSec() {
		return indexReopenMaxStaleSec;
	}
	public void setIndexReopenMaxStaleSec(double indexReopenMaxStaleSec) {
		this.indexReopenMaxStaleSec = indexReopenMaxStaleSec;
	}
	public double getIndexReopenMinStaleSec() {
		return indexReopenMinStaleSec;
	}
	public void setIndexReopenMinStaleSec(double indexReopenMinStaleSec) {
		this.indexReopenMinStaleSec = indexReopenMinStaleSec;
	}
	public int getIndexCommitSeconds() {
		return indexCommitSeconds;
	}
	public void setIndexCommitSeconds(int indexCommitSeconds) {
		this.indexCommitSeconds = indexCommitSeconds;
	}
}
