package dataobject;

public class Product {
	private int id;
	private String productCode;
	private String productName;
	private Category category;
	private Brand brand;
	private UnitOfMeasure unitOfMeasure;
	private double unitPrice;
	
	public Product(){
		id = -1;
		productCode = null;
		productName = null;
		category = null;
		brand = null;
		unitOfMeasure = null;
		unitPrice = 0;
	}
	
	public Product(int id, String productCode, String productName, Category category, Brand brand,
			UnitOfMeasure unitOfMeasure, double unitPrice) {
		super();
		this.id = id;
		this.productCode = productCode;
		this.productName = productName;
		this.category = category;
		this.brand = brand;
		this.unitOfMeasure = unitOfMeasure;
		this.unitPrice = unitPrice;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productCode=" + productCode + ", productName=" + productName + ", category="
				+ category + ", brand=" + brand + ", unitOfMeasure=" + unitOfMeasure + ", unitPrice=" + unitPrice + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	

}
