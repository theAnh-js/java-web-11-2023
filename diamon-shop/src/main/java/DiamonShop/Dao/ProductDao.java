package DiamonShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import DiamonShop.Dto.MapperProductDto;
import DiamonShop.Dto.ProductDto;
import DiamonShop.SystemContant.Contants;

@Repository
public class ProductDao extends BaseDao {

	public StringBuffer getSql() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.id, p.categoryId, p.size, p.name, p.price, p.sale, ");
		sql.append("p.title, p.highlight, p.new_product, p.detail, p.created_at, p.updated_at, c.id AS color_id, ");
		sql.append(
				"c.productId, c.img AS color_img, c.code AS color_code, c.name AS color_name FROM product AS p INNER JOIN colors AS c ON c.productId = p.id ");
		return sql;
	}

	public String getNewSql(boolean isHighlightProduct, boolean isNewProduct) {

		StringBuffer sql = getSql();
		if (isHighlightProduct) {
			sql.append("WHERE highlight = true ");
			sql.append("GROUP BY c.productId, p.id LIMIT 8 ");
		} else if (isNewProduct) {
			sql.append("WHERE new_product = true ");
			sql.append("GROUP BY c.productId, p.id LIMIT 10 ");
		}

		return sql.toString();
	}

	public List<ProductDto> getAllProduct(boolean isHighlightProduct, boolean isNewProduct) {
		List<ProductDto> list = new ArrayList<>();
		String sql = "";
		if (isHighlightProduct) {
			sql = getNewSql(Contants.YES, Contants.NO);
		}
		if (isNewProduct) {
			sql = getNewSql(Contants.NO, Contants.YES);
		}

		list = jdbcTemplate.query(sql, new MapperProductDto());
		return list;
	}

	public List<ProductDto> getProductByCategoryId(int categoryId) {
		List<ProductDto> list = new ArrayList<>();
		String sql = getSql().toString() + "WHERE categoryId = ?";
		list = jdbcTemplate.query(sql, new Object[] { categoryId }, new MapperProductDto());
		return list;
	}

	public List<ProductDto> getProductByCategoryIdAndPaging(int categoryId, int page, int limit) {
		List<ProductDto> list = new ArrayList<>();
		String sql = getSql().toString() + "WHERE categoryId = ? LIMIT ? OFFSET ?";
		
		int offset = (page - 1) * limit;
		list = jdbcTemplate.query(sql, new Object[] { categoryId, limit, offset}, new MapperProductDto());
		return list;
	}

	public int getTotalPageByCategoryId(int totalData, int limit) {
		return totalData % limit == 0 ? totalData / limit : (totalData / limit) + 1;
	}
	
	public ProductDto getProductById(int id) {
		List<ProductDto> list = new ArrayList<>();
		//ProductDto product = new ProductDto();
		String sql = getSql().toString() + "where p.id = " + id;
		//product = jdbcTemplate.queryForObject(sql, new MapperProductDto());
		list = jdbcTemplate.query(sql, new MapperProductDto());
		return list.get(0);
	}
}
