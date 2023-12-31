package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.dao.impl.NewsDAO;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.INewsService;

public class NewsService implements INewsService {

	private INewsDAO newDao;

	public NewsService() {
		newDao = new NewsDAO();

	}

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {
		return newDao.findByCategoryId(categoryId);
	}

	@Override
	public NewsModel save(NewsModel newsModel) { // thêm bài viết vào db
		newsModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		newsModel.setCreatedBy("");
		Long newsId = newDao.save(newsModel); // thêm và đồng thời lấy ra id được generate auto của bài viết đó.
		// lấy ra id của bài viết mới thêm vào để phòng trường hợp ta cần show ra thông
		// tin của bài viết mới đó.

		return newDao.findOne(newsId); // save xuống database rồi lại lấy ngược lên qua hàm findOne để kiểm tra đã lưu
										// ok chưa.
	}

	@Override
	public NewsModel update(NewsModel updatedNews) {

		NewsModel oldNews = newDao.findOne(updatedNews.getId());

		// vì các trường như createdDate hay createdBy không hiện trên UI
		// nên chúng không được truyền qua api,
		// nên ta cần lấy từ oldNews set lại vào cho updatedNews.
		updatedNews.setCreatedDate(oldNews.getCreatedDate());
		updatedNews.setCreatedBy(oldNews.getCreatedBy());
		updatedNews.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		updatedNews.setModifiedBy("");
		newDao.update(updatedNews); // dùng update trong NewsDAO để updated xuống database

		// update cập nhật thông tin vào database xong thì lấy ra bài viết được update
		// đó ra lại.
		return newDao.findOne(updatedNews.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id : ids) {
			newDao.delete(id);
		}
	}

//	@Override
//	public List<NewsModel> findAll(Integer offset, Integer limit, String sortName, String sortBy) {
//		return newDao.findAll(offset, limit, sortName, sortBy);
//	}
	@Override
	public List<NewsModel> findAll(Pageble pageble) {
		return newDao.findAll(pageble);
	}

	@Override
	public int getTotalItem() {
		return newDao.getTotalItem();
	}

	@Override
	public NewsModel findOne(long id) {
		return newDao.findOne(id);
	}

}
