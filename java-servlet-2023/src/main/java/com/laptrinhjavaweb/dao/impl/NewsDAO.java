package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.laptrinhjavaweb.dao.INewsDAO;
import com.laptrinhjavaweb.mapper.NewsMapper;
import com.laptrinhjavaweb.model.NewsModel;
import com.laptrinhjavaweb.paging.Pageble;

public class NewsDAO extends AbstractDAO<NewsModel> implements INewsDAO {

	@Override
	public List<NewsModel> findByCategoryId(Long categoryId) {

		String sql = "SELECT * FROM news WHERE categoryId = ?";

		return query(sql, new NewsMapper(), categoryId);
	}

	@Override
	public Long save(NewsModel newsModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, content, ");
		sql.append("categoryid, thumbnail, shortdescription, createddate, createdby) ");
		sql.append("VALUE(?, ?, ?, ?, ?, ?, ?)");

		return insert(sql.toString(), newsModel.getTitle(), newsModel.getContent(), newsModel.getCategoryId(),
				newsModel.getThumbnail(), newsModel.getShortDescription(), newsModel.getCreatedDate(),
				newsModel.getCreatedBy());
	}

	// Hàm này là khi chưa xây dựng các hàm chung như update, insert trong
	// genericDAO.
	public Long saveVersion1(NewsModel newsModel) {

		String sql = "INSERT INTO news (title, content, categoryid) VALUES(?, ?, ?)";
		Connection connection = getConnection();
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Long id = null;

		try {
			connection.setAutoCommit(false); // chỉ khi success mới commit

			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// sử dụng key statement Statement.RETURN_GENERATED_KEYS. Điều này thông báo cho
			// JDBC rằng bạn quan tâm đến các giá trị được tạo tự động.

			// set các giá trị cho từng tham số ? theo trình tự
			preparedStatement.setString(1, newsModel.getTitle());
			preparedStatement.setString(2, newsModel.getContent());
			preparedStatement.setLong(3, newsModel.getCategoryId());

			preparedStatement.executeUpdate(); // khi thêm, sửa, xóa đều dùng được executeUpdate()

			// lấy đối tượng resultSet có key - id được tạo auto
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
//				resultSet.next(): Phương thức này di chuyển con trỏ đến dòng đầu tiên của ResultSet 
//				và trả về true nếu có dòng, hoặc false nếu không có dòng nào. 
//				Nếu có dòng, nó đưa con trỏ đến dòng đó để có thể lấy giá trị từ các cột.

				id = resultSet.getLong(1);
			}

			connection.commit(); // cần có commit() để có cả sự thay đổi dưới database
			return id;
		} catch (SQLException e) {
			if (connection != null) { // nếu connection == null thì khi gọi .roolback() -> error
				// nên ta kiểm tra trước.
				try {
					connection.rollback(); // lỗi xảy ra trong khối try thì reset về trạng thái ban đầu
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			return null;
		} finally { // khi sd xong thì đóng tài nguyên lại -> best practice
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}

	@Override
	public NewsModel findOne(Long id) {
		String sql = "SELECT * FROM news WHERE id = ?";
		List<NewsModel> news = query(sql, new NewsMapper(), id);
		if (news != null) {
			return news.get(0);
		}
		return null;
		// return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewsModel updatedNews) {
		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?, ");
		sql.append("shortdescription = ?, content = ?, categoryId = ?, ");
		sql.append("createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
		// sử dụng hàm update trong abstractDAO
		update(sql.toString(), updatedNews.getTitle(), updatedNews.getThumbnail(), updatedNews.getShortDescription(),
				updatedNews.getContent(), updatedNews.getCategoryId(), updatedNews.getCreatedDate(),
				updatedNews.getCreatedBy(), updatedNews.getId(), updatedNews.getModifiedDate(),
				updatedNews.getModifiedBy());

	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM news WHERE id = ?";
		update(sql, id);
	}

//	@Override
//	public List<NewsModel> findAll(Integer offset, Integer limit, String sortName, String sortBy) {
//		String sql = "SELECT * FROM news　LIMIT ?, ?";
		// tuy nhiên cần check xem offset và limit có null hay ko,
		// không null thì ta mới áp dụng lệnh sql limit ?, ?
		// -> dùng StringBuilder để nối chuỗi cho đơn giản, hiệu suất.
//		StringBuilder sql = new StringBuilder("SELECT * FROM news");
//		if (sortName != null && sortBy != null) {
//			sql.append(" ORDER BY" + sortName + " " + sortBy + "");
//		}
//		if (offset != null && limit != null) {
//			sql.append(" LIMIT " + offset + " " + limit + "");
//		}
//		return query(sql.toString(), new NewsMapper());
//	}
	
	// REFACTOR ham findAll
	public List<NewsModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM news");
		if (pageble.getSorter() != null) {
			sql.append(" ORDER BY" + pageble.getSorter().getSortName() + " " + pageble .getSorter().getSortBy() + "");
		}
		if (pageble.getLimit() != null && pageble.getOffset() != null) {
			sql.append(" LIMIT " + pageble.getOffset() + " " + pageble.getLimit() + "");
		}
		return query(sql.toString(), new NewsMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM news";
		return count(sql);
	}
}
