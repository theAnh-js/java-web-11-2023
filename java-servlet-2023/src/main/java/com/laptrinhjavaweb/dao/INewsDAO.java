package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewsDAO extends GenericDAO<NewsModel> {

	NewsModel findOne(Long id);
	
	List<NewsModel> findByCategoryId(Long categoryId);
	
	Long save(NewsModel newsModel);  // hàm trả về id của bài viết khi bài biết đó được lưu vào database.

	void update(NewsModel updatedNews);
	
	void delete(long id);
	
//	List<NewsModel> findAll(Integer offset, Integer limit, String sortName, String sortBy);
	List<NewsModel> findAll(Pageble pageble);
	
	int getTotalItem();
}
