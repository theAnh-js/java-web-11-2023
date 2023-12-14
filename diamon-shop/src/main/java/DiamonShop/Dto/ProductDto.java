package DiamonShop.Dto;

import java.sql.Timestamp;

public class ProductDto {

	private int id;
	private int categoryId;
	private String size;
	private String name;
	private double price;
	private int sale;
	private String title;
	private int highlight;
	private int newProduct;
	private String detail;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	private int colorId;
	private String colorName;
	private String colorCode;
	private String img;
	
	public int getNewProduct() {
		return newProduct;
	}

	public void setNewProduct(int newProduct) {
		this.newProduct = newProduct;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public ProductDto() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getHighlight() {
		return highlight;
	}

	public void setHighlight(int highlight) {
		this.highlight = highlight;
	}

	public int getNew_product() {
		return newProduct;
	}

	public void setNew_product(int new_product) {
		this.newProduct = new_product;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Timestamp getCreated_at() {
		return createdAt;
	}

	public void setCreated_at(Timestamp created_at) {
		this.createdAt = created_at;
	}

	public Timestamp getUpdated_at() {
		return updatedAt;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updatedAt = updated_at;
	}

}
