package sg.edu.nus.iss.service;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

import sg.edu.nus.iss.gui.BillingPanel;
import sg.edu.nus.iss.gui.DiscountPanel;
import sg.edu.nus.iss.gui.MainMenu;
import sg.edu.nus.iss.models.PurchaseOrderItem;
import sg.edu.nus.iss.util.Constants;

public class BillingManager implements Constants{

	private MainMenu mainMenu;
	private PurchaseOrderItem orderedItems;
	public BillingManager(MainMenu mainMenu) throws IOException {
		this.mainMenu = mainMenu;
		}

	public BillingManager() {
		// TODO Auto-generated constructor stub
	}



	public void clock(JLabel lblClock) {
		mainMenu.clock(lblClock);		
	}

	public PurchaseOrderItem addProductItemsToCart(String barcode,
			String purchasedQuantity) {
		try {
			orderedItems=new PurchaseOrderItem();
			BufferedReader br =new BufferedReader(new FileReader(Constants.PRODUCTTFILE));
			String dataofFile;
			while ((dataofFile = br.readLine()) != null) {System.out.println(dataofFile);
			
			List<String> memberString = Arrays.asList(dataofFile.split(","));
			System.out.println("memberString:"+memberString);
			for(int z=0;z<=memberString.size();z++)
			{
				
				//System.out.println("memberString:"+memberString);
				//int barcddeFatched=Integer.parseInt(memberString.get(5));
				//int discountOnItem=checkDiscount(memberID);
				String fetchedBarcode=memberString.get(5);
				if(barcode.equalsIgnoreCase(fetchedBarcode)){
					System.out.println("selected barcde::"+fetchedBarcode);
					System.out.println("Matched");
					orderedItems.setProductID(memberString.get(0));
					orderedItems.setPurchasedQuantity(Integer.parseInt(purchasedQuantity));					
					orderedItems.setNameOfProduct(memberString.get(1));					
					orderedItems.setItemPrice(new Double(memberString.get(4)));
					orderedItems.setBarCode(fetchedBarcode);
					orderedItems.setTransID(generateTransID());
					//orderedItems.setQuantityAvailable(Integer.parseInt(memberString.get(3)));					
					//orderedItems.setThreshold(Integer.parseInt(memberString.get(6)));
				//	orderedItems.setNameOfProduct(nameOfProduct);
				
					break;		
				}
				
				
				
			}}
		    
		    br.close();
		    //System.out.println(br.toString());
		   // System.out.println(productMap);
		    
		}
		
		catch (IOException e){
			e.printStackTrace();
		   }
		System.out.println("name of product:"+orderedItems.getNameOfProduct());
		
		
		return orderedItems;
		//generated method stub
		//return null;
	
	}

	private int generateTransID() {
		 return (int) TimeUnit.SECONDS.convert(
			        System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	public double calculateTotalPrice(ArrayList<PurchaseOrderItem> itemsList) {
		double totalprice = 0;
		Iterator<PurchaseOrderItem> iterator = itemsList.iterator();
		
		while (iterator.hasNext()) {
			
			PurchaseOrderItem d=iterator.next();
			Double totalPriceItems=(new Double(d.getPurchasedQuantity())) *(new Double(d.getPrice()));
			totalprice = totalprice + totalPriceItems;
			
		}
		
			return totalprice;
	}

	public double calculateTotalDiscountedPrice(
			double totalPriceofPurchasedItems, String discountPercentage) {
		// TODO Auto-generated method stub
		
		double discountPCT=new Double(discountPercentage);
		double totalDiscountedPrice= totalPriceofPurchasedItems * discountPCT ;
		totalDiscountedPrice=totalDiscountedPrice/ 100;
		
		
		return totalDiscountedPrice;
	}

}
