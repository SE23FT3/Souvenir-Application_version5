package sg.edu.nus.iss.service;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.imageio.ImageIO;

import sg.edu.nus.iss.gui.MainMenu;
import sg.edu.nus.iss.gui.ProductPanel;
import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Product;
import sg.edu.nus.iss.util.Constants;



public class ProductManager  {
	private ArrayList<Product> products;
	private static int productNumber;
	private ProductPanel productPanel;
	private MainMenu mainMenu;

	private int categoryNumber;
	public ProductManager(){}
	public ProductManager(MainMenu mainMenu) throws IOException{
		this.mainMenu = mainMenu;
		productPanel = new ProductPanel(this);
		products=new ArrayList<Product>();
		FileReader r = new FileReader("./data/Products.dat");
		BufferedReader br = new BufferedReader(r);
		String line =null;
		while((line=br.readLine())!=null){
			String[] strArray=line.split(",");
			Product p = new Product(strArray[0],strArray[1],strArray[2],
					Integer.parseInt(strArray[3]),Float.parseFloat(strArray[4]),Integer.parseInt(strArray[5]),
					Integer.parseInt(strArray[6]),Integer.parseInt(strArray[7]));
			products.add(p);
		}
		br.close();
	}
	public int getProductNumber(){
		return productNumber;
	}

	public ArrayList<Product> refreshProductsList() throws IOException{
		FileReader r = new FileReader("./data/Products.dat");
		BufferedReader br = new BufferedReader(r);
		String line =null;
		while((line=br.readLine())!=null){
			String[] strArray=line.split(",");
			Product p = new Product(strArray[0],strArray[1],strArray[2],
					Integer.parseInt(strArray[3]),Float.parseFloat(strArray[4]),Integer.parseInt(strArray[5]),
					Integer.parseInt(strArray[6]),Integer.parseInt(strArray[7]));
			products.add(p);
			//System.out.println(line);
		}
		br.close();
		return products;
	}

	public String createProductId(String productCode) throws IOException{
		categoryNumber=0;

		FileReader r = new FileReader("./data/Products.dat");
		BufferedReader br=new BufferedReader(r);
		String line=null;
		String Code=null;
		while((line=br.readLine())!=null){
			if(line.length()>3){
				Code=line.split(",")[0];
				System.out.println(Code);
				System.out.println(productCode);
				if(Code.substring(0,3).equals(productCode)){
					categoryNumber++;
				}
			}
		}
		br.close();
		productNumber=categoryNumber+1;
		return (productCode+"/"+productNumber);
	}

	public boolean addProduct(String productCode, String productName,
			String productDescription, int quantityAvaliable,
			float productPrice, int barCode, int reorderThreshold, int orderQuantity)throws IOException {

		boolean valid=false;
		Product p = new Product(createProductId(productCode),productName,productDescription,
				quantityAvaliable,productPrice,barCode,reorderThreshold,orderQuantity);
		products.add(p);
		FileWriter w = new FileWriter("./data/Products.dat",true);
		PrintWriter pr = new PrintWriter(w);
		pr.write("\r"+p.toString());
		pr.close();
		valid=true;
		return valid;
	}


	public void show(){
		System.out.println(this.toString());
	}

	public void showProducts () throws IOException {
		Iterator<Product> i = products.iterator ();
		while (i.hasNext ()) {
			i.next().show ();
		}
	}

	public Product searchProduct(String ProductId) throws IOException{
		FileReader r = new FileReader("./data/Products.dat");
		BufferedReader br=new BufferedReader(r);
		String line=null;
		Product p = null;
		while((line=br.readLine())!=null){
			if(line.length()>6){
				String[] strArray=line.split(",");
				if(strArray[0].equals(ProductId)){
					p = new Product(strArray[0],strArray[1],strArray[2],
							Integer.parseInt(strArray[3]),Float.parseFloat(strArray[4]),Integer.parseInt(strArray[5]),
							Integer.parseInt(strArray[6]),Integer.parseInt(strArray[7]));
				}
			}
		}
		br.close();
		return p;
	}

	public Product getProduct(int productNum){
		//		Iterator<Product> i = products.iterator();
		//        while (i.hasNext ()) {
		//            Product p = i.next();
		//            if (p.getProductNumber() == productNum) {
		//                return p;
		//            }
		//        }
		return products.get(productNum);
	}

	public List<Product> getProducts() throws IOException{
		ArrayList<Product> result;
		result = new ArrayList<Product>(this.refreshProductsList());
		Collections.sort(result);;
		return(result);
	}

	public Product modifyProduct(int productNum, String productName,
			String productDescription, int quantityAvaliable,
			float productPrice, int reorderThreshold, int orderQuantity){
		Product p = getProduct(productNum);
		if(p!=null){
			p=new Product(p.getProductId(),productName,productDescription,quantityAvaliable,
					productPrice,p.getBarCode(),reorderThreshold,orderQuantity);
		}

		return p;
	}
	public BufferedImage getProductIcon() throws IOException{
		BufferedImage myPicture = ImageIO.read(new File("./data/product.jpeg"));
		return myPicture;
	}
	public void refresh() throws IOException {
		mainMenu.refreshProductPanel();
		mainMenu.displayProductPanel();
		
	}
	public ProductPanel getProductPanel(){
		return productPanel;
	}
	public List<Product> searchDataAndDisplay(String data, String value) {
		
		ArrayList<Product> searchedlist=new ArrayList<Product>();
		ArrayList<Product> producttList = null;
		Product searchedProduct;
		System.out.println("searchDataAndDisplay"+value+data);
		try {
			producttList = retrieveProductDataFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      Iterator<Product> iterator = producttList.iterator();
				while (iterator.hasNext()) {		
					searchedProduct=new Product();
					Product dis=iterator.next();
					if(data.equalsIgnoreCase("Product ID"))
					{
					
						if(value.equalsIgnoreCase(dis.getProductId())){
						//searcheddiscount=new Discount();
					
							searchedProduct=dis;
							searchedlist.add(searchedProduct);	
					
						
						}
					}
					else if(data.equalsIgnoreCase("Product Name"))
					{
						if(value.equalsIgnoreCase(dis.getProductName())){
						
							searchedProduct=dis;
							searchedlist.add(searchedProduct);	
						}
					}
					else if(data.equalsIgnoreCase("Product Description"))
					{
						
						if(dis.getProductDescription().contains(value)){
							searchedProduct=dis;
							searchedlist.add(searchedProduct);	}
					}
					else if(data.equalsIgnoreCase("Price"))
					{
						int p=Integer.parseInt(value);
						if((dis.getProductPrice()==p)){
							searchedProduct=dis;
							searchedlist.add(searchedProduct);	}
					}
					
				}
				System.out.println("searchedlist:"+searchedlist.size());
		return  searchedlist;
	
}
	public ArrayList<Product> retrieveProductDataFromFile() throws IOException{
		String dataofFile=null;
		Product product=null;
		ArrayList<Product> productList=new ArrayList<Product>();;
		FileReader r=null;
		BufferedReader br=null;
		try {
			r=new FileReader(Constants.PRODUCTTFILE);
			br=new BufferedReader(r);
			while((dataofFile=br.readLine())!=null){
				System.out.println(dataofFile);
				
				List<String> productString = Arrays.asList(dataofFile.split(","));
				for(int z=0;z<=productString.size();z++)
				{
					
					product=new Product();
					product.setProductId(productString.get(0));
					product.setProductName(productString.get(1));
					product.setProductDescription(productString.get(2));
					product.setQuantityAvaliable(Integer.parseInt(productString.get(3)));
					product.setProductPrice(new Double(productString.get(4)));
					product.setBarCode(Integer.parseInt(productString.get(5)));
					product.setReorderThreshold(Integer.parseInt(productString.get(6)));
					product.setOrderQuantity(Integer.parseInt(productString.get(7)));
					productList.add(product);
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
	return productList;
	}

	public  ArrayList<Product>  deleteProductData(Product product) throws IOException
	{ 
		ArrayList<Product> productList=new ArrayList<Product> ();
	
		if(product!=null)
		{
			String content=product.getProductId()+","
					+product.getProductName()+","+product.getProductDescription()+","+product.getQuantityAvaliable()+","+
					product.getProductPrice()+","+product.getBarCode()+","+product.getReorderThreshold()+","+product.getOrderQuantity();
			System.out.println("to be deleted::"+content);
			
			productList=retrieveProductDataFromFile();
		
	        System.out.println("before"+productList.size());
	             
	      //  displayDiscountFile(discountList);
	        BufferedWriter bout = new BufferedWriter(new FileWriter(Constants.PRODUCTTFILE));
	        Iterator<Product> iterator = productList.iterator();
			while (iterator.hasNext()) {
				
				
				Product dis=iterator.next();
				if(dis.getProductId().equalsIgnoreCase(product.getProductId()))
				{
					System.out.println("Removed");
					productList.remove(dis);
					break;
				}
				else
				{
					System.out.println("Data not found");
					
					
				}
				String line=product.getProductId()+","
						+product.getProductName()+","+product.getProductDescription()+","+product.getQuantityAvaliable()+","+
						product.getProductPrice()+","+product.getBarCode()+","+product.getReorderThreshold()+","+product.getOrderQuantity();
				System.out.println(line);
				bout.write(line);
				bout.newLine();
				
			}    
			 System.out.println("after deletion:"+productList.size());
			bout.close();						
		}
		return productList;
	}

}
