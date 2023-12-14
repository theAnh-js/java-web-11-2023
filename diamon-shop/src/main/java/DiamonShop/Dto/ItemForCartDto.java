package DiamonShop.Dto;

public class ItemForCartDto {
	
	private ProductDto product;
	private int totalQuantity;
	private double totalPrice;
	
	public ItemForCartDto() {
		
	}
	
	public ItemForCartDto(ProductDto product, int totalQuantity, double totalPrice) {
		this.product = product;
		this.totalQuantity = totalQuantity;
		this.totalPrice = totalPrice;
	}


	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	

}
