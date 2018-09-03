/**
 * @description 阅读页信息采集线程
 */
package com.cqu.crawl.zongheng;

import java.util.concurrent.TimeUnit;

import com.cqu.crawl.zongheng.db.ZonghengDB;
import com.cqu.crawl.zongheng.model.NovelChapterModel;
import com.cqu.crawl.zongheng.model.NovelReadModel;

public class ReadPageThread extends Thread{

	private boolean flag = false;

	public ReadPageThread(String name){
		super(name);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		flag = true;
		ZonghengDB db = new ZonghengDB();
		while(flag){
			try {
				//获取可以采集的章节信息
				NovelChapterModel chapter = db.getRandomChapter("1");
				if(chapter != null){
					ReadPage readPage = new ReadPage(chapter.getUrl());
					//获取小说阅读页信息
					NovelReadModel read = readPage.analyzer();
					if(read == null){
						continue;
					}
					//写入小说章节序号
					read.setChapterId(chapter.getChapterId());
					//写入章节发布时间
					read.setChapterTime(chapter.getChapterTime());
					//保存小说阅读页信息
					db.saveNovelRead(read);
					db.updateChapterState(chapter.getId(), "0");
					TimeUnit.MILLISECONDS.sleep(500);
				}else{
					TimeUnit.MILLISECONDS.sleep(1000);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}	
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadPageThread thread = new ReadPageThread("readPageThread");
		thread.start();
	}

}
