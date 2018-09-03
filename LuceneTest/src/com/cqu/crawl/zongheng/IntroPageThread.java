/**
 * @description 小说简介页信息采集线程
 */
package com.cqu.crawl.zongheng;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.cqu.crawl.zongheng.db.ZonghengDB;
import com.cqu.crawl.zongheng.model.NovelChapterModel;
import com.cqu.crawl.zongheng.model.NovelInfoModel;

public class IntroPageThread extends Thread{

	private boolean flag = false;

	public IntroPageThread(String name){
		super(name);
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		flag = true;
		try {
			ZonghengDB db = new ZonghengDB();
			while(flag){
				//获取可以采集的简介页URL
				String introUrl = db.getRandomIntroPageUrl("1");
				if(introUrl != null){
					IntroPage introPage = new IntroPage(introUrl);
					//获取简介页信息
					NovelInfoModel bean = introPage.analyzer();
					if(bean != null){
						ChapterPage chapterPage = new ChapterPage(bean.getChapterListUrl());
						//获取章节列表页信息
						List<NovelChapterModel> chapters = chapterPage.analyzer();
						//写入小说章节个数
						bean.setChapterCount(chapters == null ? 0 : chapters.size());
						//保存简介页信息
						db.updateNovelInfo(bean);
						//保存章节列表页信息
						db.saveNovelChapter(chapters, bean.getId());
					}
					TimeUnit.MILLISECONDS.sleep(500);
				}else{
					TimeUnit.MILLISECONDS.sleep(1000);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntroPageThread thread = new IntroPageThread("introPageThread");
		thread.start();
	}

}
