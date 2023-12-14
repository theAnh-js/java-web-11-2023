package DiamonShop.Dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperProductDto implements RowMapper<ProductDto>{
	


	@Override
	public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDto p = new ProductDto();
		p.setId(rs.getInt("id"));
		p.setCategoryId(rs.getInt("categoryId"));
		p.setSize(rs.getString("size"));
		p.setName(rs.getString("name"));
		p.setPrice(rs.getDouble("price"));
		p.setSale(rs.getInt("sale"));
		p.setTitle(rs.getString("title"));
		p.setHighlight(rs.getInt("highlight"));
		p.setNewProduct(rs.getInt("new_product"));
		p.setDetail(rs.getString("detail"));
		p.setCreatedAt(rs.getTimestamp("updated_at"));
		p.setUpdatedAt(rs.getTimestamp("updated_at"));
		p.setColorId(rs.getInt("color_id")); 
		p.setColorName(rs.getString("color_name"));
		p.setColorCode(rs.getString("color_code"));
		p.setImg(rs.getString("color_img"));
		return p;
	}

}
