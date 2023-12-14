package DiamonShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import DiamonShop.Entity.Category;
import DiamonShop.Entity.MapperCategory;

@Repository
public class CategoryDao extends BaseDao {

	/*
	 * @Autowired private JdbcTemplate jdbcTemplate;
	 */
	
	public List<Category> getAllCategory(){
		List<Category> list = new ArrayList<>();
		String sql = "SELECT * FROM category";
		list = jdbcTemplate.query(sql, new MapperCategory());		
		return list;
	}

}
