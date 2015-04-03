package sg.edu.nus.iss.models;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class Vendor  implements Serializable{
	private String              vendorName;
	private String              vendorDescription;
	
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorDescription() {
		return vendorDescription;
	}

	public void setVendorDescription(String vendorDescription) {
		this.vendorDescription = vendorDescription;
	}
	public Vendor (String vendorName, String vendorDescription){
		this.vendorName = vendorName;
		this.vendorDescription = vendorDescription;	
	}
	
	public Vendor() {
		// TODO Auto-generated constructor stub
	}

	public void addVendor(String vendorName, String vendorDescription){
		String filename = "D:/Documents and Settings/Administrator/workshop4/Assignment5/src/Vendor.dat";
		try{
			//FileOutputStream writer=new FileOutputStream(filename, true);
			FileWriter writer =new FileWriter(filename, true);
			writer.write(vendorName);
			writer.write(", ");
			writer.write(vendorDescription);
			writer.write("\r\n");
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
			}
		}
	public String toString(){
		return(vendorName+","+vendorDescription);
	}

}
