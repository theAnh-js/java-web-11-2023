package DiamonShop.Service.User;

import java.util.List;

import DiamonShop.Dto.ProductDto;
import DiamonShop.Entity.Category;
import DiamonShop.Entity.Menu;
import DiamonShop.Entity.Slides;

public interface IHomeService {
	
	List<Slides> getDataSlides();
	List<Category> getAllCategory();
	List<Menu> getAllMenu();
	List<ProductDto> getAllProduct(boolean isHighlightProduct, boolean isNewProduct);
	
	List<ProductDto> getProductByCategoryId(int categoryId);
	
	List<ProductDto> getProductByCategoryIdAndPaging(int categoryId, int page, int limit);
	
	int getTotalPageByCategoryId(int totalData, int limit);
	
	ProductDto getProductById(int id);
}
