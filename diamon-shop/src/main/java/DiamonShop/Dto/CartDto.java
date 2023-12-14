package DiamonShop.Dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {

	private List<ItemForCartDto> list;
	
	public CartDto() {
		list = new ArrayList<ItemForCartDto>();
	}

	public List<ItemForCartDto> getList() {
		return list;
	}

	public void setList(List<ItemForCartDto> list) {
		this.list = list;
	}
	
	public void addItemToList(ItemForCartDto item) {
		
		boolean isProduct = false;
		
		if(list.size() == 0) {
			list.add(item);
		}else {
			int productId = item.getProduct().getId();
			for (ItemForCartDto x : list) {
				if(x.getProduct().getId() == productId) {
					x.setTotalQuantity(item.getTotalQuantity() + x.getTotalQuantity());
					//x.setTotalPrice(item.getTotalPrice() + x.getTotalPrice());
					x.setTotalPrice(x.getTotalQuantity() * x.getProduct().getPrice());
					isProduct = true;
				}
			}
			if(isProduct == false) {
				list.add(item);
			}
		}
	}
	public double getTotalPriceOfCart(List<ItemForCartDto> items) {
		double rs = 0.0;
		for (ItemForCartDto item : items) {
			rs += item.getTotalPrice();
		}		
		return rs;
	}
	
	public ItemForCartDto getItemById(int id) {
		for(ItemForCartDto item : list) {
			if(item.getProduct().getId() == id) {
				return item;
			}
		}
		return null;
	}
	
	public void removeItemById(int id) {
		
		for(ItemForCartDto item : list) {
			if(item.getProduct().getId() == id) {
				list.remove(item);
				break; // nếu không để break -> java.util.ConcurrentModificationException
				//java.util.ConcurrentModificationException thường xuất hiện khi bạn thực hiện một lặp (iteration) 
				//qua một cấu trúc dữ liệu như List, Set, Map, ... trong khi đồng thời thực hiện thêm, xoá hoặc sửa đổi 
				//cấu trúc dữ liệu đó. Điều này gây ra xung đột (concurrent modification) và ném ra ngoại lệ.
			}
		}
	}
}
