package sg.edu.nus.iss.service;



import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sg.edu.nus.iss.gui.MainMenu;
import sg.edu.nus.iss.gui.ProductPanel;
import sg.edu.nus.iss.gui.VendorPanel;
import sg.edu.nus.iss.models.Product;
import sg.edu.nus.iss.models.Vendor;

public class VendorManager {
	private MainMenu mainMenu;
	private VendorPanel vendorPanel;

	private ArrayList<Vendor> vendors;
	private ArrayList<Vendor> vendorList;
	public VendorManager(MainMenu mainMenu) throws IOException {
		this.mainMenu = mainMenu;
		vendorPanel = new VendorPanel(this);

	}

	public ArrayList<Vendor> ListVendor() {
		ArrayList<Vendor> ListVendor = new ArrayList<Vendor>();
		String filename = "./data/Vendors.dat";
		String data;
		try {
			BufferedReader Reader = new BufferedReader(new FileReader(filename));
			while ((data = Reader.readLine()) != null) {
				// Deal with the line
				String[] dataTmp = data.split(",");
				String vendorName = dataTmp[0];
				String vendorDescription = dataTmp[1];
				Vendor vendor = new Vendor(vendorName, vendorDescription);
				ListVendor.add(vendor);
			}
			Reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ListVendor;
	}
	public boolean addVendor(String vendorName, String vendorDescription)throws IOException {

		boolean valid=false;
		Vendor p = new Vendor(vendorName,vendorDescription);
		this.ListVendor().add(p);
		FileWriter w = new FileWriter("./data/Vendors.dat",true);
		PrintWriter pr = new PrintWriter(w);
		pr.write("\r"+p.toString());
		pr.close();
		valid=true;
		return valid;
	}
	
	public void refresh() throws IOException {
		mainMenu.refreshVendorPanel();
		mainMenu.displayVendorPanel();
		
	}

	public void searchDataAndDisplay(String data, String value) {
		System.out.print("searchDataAndDisplay");
	}

	public VendorPanel getVendorPanel() {
		return vendorPanel;
	}

	public ArrayList<Vendor> retrieveVendorDataFromFile(String fileName) throws IOException{
		String dataofFile=null;
		Vendor vendor=null;
		 vendorList=new ArrayList<Vendor>();
		FileReader r=null;
		BufferedReader br=null;
		try {
			r=new FileReader(fileName);
			br=new BufferedReader(r);
			while((dataofFile=br.readLine())!=null){
				System.out.println(dataofFile);
				
				List<String> vendorString = Arrays.asList(dataofFile.split(","));
				for(int z=0;z<=vendorString.size();z++)
				{
					
					vendor=new Vendor();
				
					vendor.setVendorName(vendorString.get(0));
					vendor.setVendorDescription(vendorString.get(1));
					
					
					vendorList.add(vendor);
					break;
				}


				
			}


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch b

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			br.close();
		}


		
	
		return vendorList;
	}


}
