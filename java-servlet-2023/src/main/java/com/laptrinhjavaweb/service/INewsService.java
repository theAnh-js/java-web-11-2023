package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface INewsService {

	List<NewsModel> findByCategoryId(Long categoryId);

	NewsModel save(NewsModel newsModel);

	NewsModel update(NewsModel updatedNews);

	void delete(long[] ids);

	// List<NewsModel> findAll(Integer offset, Integer limit, String sortName,
	// String sortBy); // Cần thêm 2 biến tham số để phân trang
	List<NewsModel> findAll(Pageble pageble);

	int getTotalItem();
	
	NewsModel findOne(long id);
}
