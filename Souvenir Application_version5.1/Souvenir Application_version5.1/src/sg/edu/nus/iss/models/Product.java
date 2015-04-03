package sg.edu.nus.iss.models;

import java.io.Serializable;


public class Product implements Comparable<Product> , Serializable {
	private String productId;

	private String productName;
	private String productDescription;
	private int quantityAvaliable;
	private double productPrice;
	private int barCode;
	private int reorderThreshold;
	private int orderQuantity;
	
	public Product() {
		super();
	}

	public Product(String productId,String productName,
			String productDescription, int quantityAvaliable,
			double productPrice, int barCode, int reorderThreshold, int orderQuantity) {
		this.productId = productId;
		this.productName = productName;
		this.productDescription = productDescription;
		this.quantityAvaliable = quantityAvaliable;
		this.productPrice = productPrice;
		this.barCode = barCode;
		this.reorderThreshold = reorderThreshold;
		this.orderQuantity = orderQuantity;
	}
	public String getProductId(){
		return productId;
	}
	public String getProductName(){
		return productName;
	}
	public String getProductDescription(){
		return productDescription;
	}
	public int getQuantityAvaliable(){
		return quantityAvaliable;
	}
	public double getProductPrice(){
		return productPrice;
	}
	public int getBarCode(){
		return barCode;
	}
	public int getReorderThreshold(){
		return reorderThreshold;
	}
	public int getOrderQuantity(){
		return orderQuantity;
	}
	public void setReorderThreshold(int reorderThreshold) {
		this.reorderThreshold = reorderThreshold;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public void setQuantityAvaliable(int quantityAvaliable) {
		this.quantityAvaliable = quantityAvaliable;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public void setBarCode(int barCode) {
		this.barCode = barCode;
	}
	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public String toString(){
		return(productId+","+productName+","+productDescription+","
				+quantityAvaliable+","+productPrice+","+barCode+","
				+reorderThreshold+","+orderQuantity);
	}
	public void show(){
		System.out.println(this.toString());
	}

	public int compareTo(Product other){
		return(Integer.parseInt(getProductId().substring(4))-
				Integer.parseInt(other.getProductId().substring(4)));
	}

}
