package sg.edu.nus.iss.models;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Category implements Serializable{
	
	/*  
	 *Created by Yang Chang on 21St March2015 *
	 */
	private String categoryCode;
	private String categoryName;

	public Category(String categoryCode, String categoryName) {
		super();
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
	}
	

	public Category() {
		super();
	}


	public void modifyCategoryCode(String categorycode){
		this.categoryCode = categorycode;
	}
	public void modifyCaegoryName(String categoryname){
		this.categoryName = categoryname;
	}
	public String getCategoryCode(){
		return categoryCode;
	}
	public String getCategoryName(){
		return categoryName;
	}
	public String toString(){
		return(categoryCode+","+categoryName);
	}
	public void show(){
		System.out.println(this.toString());
	}


	public void setCategoryCode(String string) {
		this.categoryCode=string;
	}


	public void setCategoryName(String string) {
		this.categoryName=string;
	}

}