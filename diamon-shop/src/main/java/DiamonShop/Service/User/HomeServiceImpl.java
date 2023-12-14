package DiamonShop.Service.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamonShop.Dao.CategoryDao;
import DiamonShop.Dao.MenuDao;
import DiamonShop.Dao.ProductDao;
import DiamonShop.Dao.SlidesDao;
import DiamonShop.Dto.ProductDto;
import DiamonShop.Entity.Category;
import DiamonShop.Entity.Menu;
import DiamonShop.Entity.Slides;

@Service
public class HomeServiceImpl implements IHomeService {

	@Autowired
	private SlidesDao slidesDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Slides> getDataSlides() {
		return slidesDao.getDataSlides();
	}

	@Override
	public List<Category> getAllCategory() {
		return categoryDao.getAllCategory();
	}

	@Override
	public List<Menu> getAllMenu() {
		return menuDao.getAllMenu();
	}

	@Override
	public List<ProductDto> getAllProduct(boolean isHighlightProduct, boolean isNewProduct) {
		return productDao.getAllProduct(isHighlightProduct, isNewProduct);
	}

	@Override
	public List<ProductDto> getProductByCategoryId(int categoryId) {
		return productDao.getProductByCategoryId(categoryId);
	}

	@Override
	public List<ProductDto> getProductByCategoryIdAndPaging(int categoryId, int page,int limit) {
		return productDao.getProductByCategoryIdAndPaging(categoryId, page, limit);
	}

	@Override
	public int getTotalPageByCategoryId(int totalData, int limit) {
		return productDao.getTotalPageByCategoryId(totalData, limit);
	}

	@Override
	public ProductDto getProductById(int id) {
		return productDao.getProductById(id);
	}

}
