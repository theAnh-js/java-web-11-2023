package DiamonShop.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import DiamonShop.Entity.MapperSlides;
import DiamonShop.Entity.Slides;

@Repository
public class SlidesDao extends BaseDao {
	

	public List<Slides> getDataSlides(){
		List<Slides> list = new ArrayList<>();	
		String sql = "SELECT * FROM slides;";
		list = jdbcTemplate.query(sql, new MapperSlides());
		return list;
	}
	
}
