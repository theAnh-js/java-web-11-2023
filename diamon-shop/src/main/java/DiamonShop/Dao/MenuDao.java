package DiamonShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import DiamonShop.Entity.MapperMenu;
import DiamonShop.Entity.Menu;

@Repository
public class MenuDao extends BaseDao {
	
	public List<Menu> getAllMenu() {
		List<Menu> list = new ArrayList<>();
		String sql = "SELECT * FROM menu";
		list = jdbcTemplate.query(sql, new MapperMenu());
		return list;

	}
}
