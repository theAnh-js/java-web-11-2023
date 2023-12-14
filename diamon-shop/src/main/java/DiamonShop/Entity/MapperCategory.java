package DiamonShop.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MapperCategory implements RowMapper<Category> {

	@Override
	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Category category = new Category();	
		category.setId(rs.getInt("id"));
		category.setName(rs.getString("name"));
		category.setDescription(rs.getString("description"));
		return category;
		
	}

}
