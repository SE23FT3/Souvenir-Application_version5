package sg.edu.nus.iss.models;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.lang.StringBuilder;
import java.util.Date;

public class PurchaseOrderItem implements Serializable {
	
	/*
	 * created by Mukesh on 21st March 2015*
	 */
	
	
 private String productID;
 private String nameOfProduct;
 private int purchasedQuantity;
 private double price;
 private String barCode;
 private int transID;
 private int quantityAvailable;
 private int threshold;
 private int orderQuantity;
 private int discountOnItem;
 private double discountedPrice;



 public PurchaseOrderItem(){}

public PurchaseOrderItem(String productID, String nameOfProduct, String desc,int quantityAvailable,
		 double price, String barCode, int threshold,int orderQuantity, int purchasedQuantity,int transID,int discountOnItem) {
	super();
	this.productID = productID;
	this.nameOfProduct = nameOfProduct;
	this.purchasedQuantity = purchasedQuantity;
	this.price = price;
	this.barCode = barCode;
	this.transID = transID;
	this.quantityAvailable = quantityAvailable;
	this.threshold = threshold;
	this.orderQuantity = orderQuantity;
	this.discountOnItem= discountOnItem;
}



/**

public PurchaseOrderItem(String productID2, String nameOfProduct2,
		String desc2, int parseInt, double parseDouble, String barCode2,
		int parseInt2, int parseInt3, int purchasedQuantity2, int transID2) {
	// TODO Auto-generated constructor stub
} **/


public String getProductID() {
	return productID;
}
public void setProductID(String productID) {
	this.productID = productID;
}
public String getNameOfProduct() {
	return nameOfProduct;
}
public void setNameOfProduct(String nameOfProduct) {
	this.nameOfProduct = nameOfProduct;
}
public int getPurchasedQuantity() {
	return purchasedQuantity;
}
public void setPurchasedQuantity(int purchasedQuantity) {
	this.purchasedQuantity = purchasedQuantity;
}
public double getItemPrice() {
	return price;
}
public void setItemPrice(double itemPrice) {
	this.price = itemPrice;
}

public String getBarCode() {
	return barCode;
}

public void setBarCode(String barCode) {
	this.barCode = barCode;
}

public int getTransID() {
	return transID;
}
public void setTransID(int transID) {
	this.transID = transID;
}



public int getDiscountOnItem() {
	return discountOnItem;
}

public void setDiscountOnItem(int discountOnItem) {
	this.discountOnItem = discountOnItem;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}

public int getQuantityAvailable() {
	return quantityAvailable;
}

public void setQuantityAvailable(int quantityAvailable) {
	this.quantityAvailable = quantityAvailable;
}

public int getThreshold() {
	return threshold;
}

public void setThreshold(int threshold) {
	this.threshold = threshold;
}

public int getOrderQuantity() {
	return orderQuantity;
}

public void setOrderQuantity(int orderQuantity) {
	this.orderQuantity = orderQuantity;
}



public double getDiscountedPrice() {
	return discountedPrice;
}

public void setDiscountedPrice(double discountedPrice) {
	this.discountedPrice = discountedPrice;
}


}


