/**
 * @description 小说查询操作
 */
package com.cqu.lucene.index.operation.zongheng;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Query;

import com.cqu.lucene.index.model.SearchResultBean;
import com.cqu.lucene.index.model.zongheng.FinalResultBean;
import com.cqu.lucene.index.model.zongheng.NovelIndexBean;
import com.cqu.lucene.index.model.zongheng.NovelSearchBean;
import com.cqu.lucene.index.operation.NRTSearch;
import com.cqu.util.ParseBean;
import com.cqu.util.ZongHengConstant;

public class NovelSearch extends NRTSearch{
	
	//查询语句Query包
	private QueryPack queryPack = new QueryPack(ZongHengConstant.INDEX_NAME);
	
	public NovelSearch() {
		super(ZongHengConstant.INDEX_NAME);
	}
	/**
	 * 
	 * @param docBean
	 * @return
	 * @author 汪波
	 * @description 将SearchResultBean转换为FinalResultBean（内部即将Document转化为NovelSearchBean）
	 */
	private FinalResultBean parse(SearchResultBean docBean){
		if(docBean == null){
			return null;
		}
		FinalResultBean finalBean = new FinalResultBean();
		finalBean.setCount(docBean.getCount());
		if(docBean.getDocs() != null){
			List<NovelSearchBean> list = new ArrayList<NovelSearchBean>();
			for(Document doc : docBean.getDocs()){
				NovelSearchBean searchBean = ParseBean.parseDocumentToNovelSearchBean(doc);
				list.add(searchBean);
			}
			finalBean.setBeans(list);
		}
		return finalBean;
	}
	
	/**
	 * 
	 * @param searchValue
	 * @param start
	 * @param end
	 * @return
	 * @author 汪波
	 * @description 单域标准查询小说名
	 */
	public FinalResultBean searchNovelName(String searchValue, int start, int end){
		if(searchValue == null){
			return null;
		}
		Query query = queryPack.getOneFieldQuery(NovelIndexBean.INDEX_NAME_KEY, searchValue);
		SearchResultBean resultBean = search(query, start, end);
		FinalResultBean finalBean = this.parse(resultBean);
		return finalBean;
	}
	
	/**
	 * 
	 * @param searchValue
	 * @return
	 * @author 汪波
	 * @description 词条查询小说名
	 */
	public FinalResultBean searchNovelName(String searchValue){
		if(searchValue == null){
			return null;
		}
		Query query = queryPack.getStringQuery(NovelIndexBean.INDEX_NAME, searchValue);
		SearchResultBean resultBean = search(query, 0, 1);
		FinalResultBean finalBean = this.parse(resultBean);
		return finalBean;
	}

	/**
	 * 
	 * @param searchValue
	 * @param start
	 * @param end
	 * @return
	 * @author 汪波
	 * @description 通配符查询小说URL
	 */
	public FinalResultBean searchNovelUrl(String searchValue, int start, int end){
		if(searchValue == null){
			return null;
		}
		Query query = queryPack.getWildcardQuery(NovelIndexBean.INDEX_URL, searchValue);
		SearchResultBean resultBean = search(query, start, end);
		FinalResultBean finalBean = this.parse(resultBean);
		return finalBean;
	}
}
