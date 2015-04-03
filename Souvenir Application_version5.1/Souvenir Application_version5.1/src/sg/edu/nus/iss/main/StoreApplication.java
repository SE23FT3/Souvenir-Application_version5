
package sg.edu.nus.iss.main;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import sg.edu.nus.iss.*;
import sg.edu.nus.iss.gui.MainMenu;
import sg.edu.nus.iss.gui.LoginPanel;
import sg.edu.nus.iss.gui.ProductPanel;
import sg.edu.nus.iss.models.Product;
import sg.edu.nus.iss.service.BillingManager;
import sg.edu.nus.iss.service.CategoryManager;
import sg.edu.nus.iss.service.CustomerManager;
import sg.edu.nus.iss.service.DiscountManager;
import sg.edu.nus.iss.service.LoginManager;
import sg.edu.nus.iss.service.ProductManager;
import sg.edu.nus.iss.service.TransactionManager;
import sg.edu.nus.iss.service.VendorManager;

public class StoreApplication {

	private static LoginPanel loginPanel;
	private ProductManager productManager;
	private CategoryManager categoryManager;
	private DiscountManager discountManager;
	private BillingManager billingManager;
	private CustomerManager customerManager;
	private LoginManager loginManager;
	private TransactionManager transactionManager;
	private VendorManager vendorManager;
	private static  StoreApplication manager;
	
	public StoreApplication(){
		try {
			productManager = new ProductManager();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		categoryManager = new CategoryManager();
		discountManager = new DiscountManager();
		billingManager = new BillingManager();
		customerManager=new CustomerManager();
		loginManager=new LoginManager();
		transactionManager = new TransactionManager();
		vendorManager = new VendorManager();
	}

	public static void main(String[] args) {
	     manager = new StoreApplication ();
		start();
	}

	public static  void start() {
		loginPanel = new LoginPanel(manager);
		loginPanel.pack();
		int width=loginPanel.getWidth();
		int height=loginPanel.getHeight();
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		loginPanel.setLocation(w, h);
		loginPanel.setVisible (true);
	}
	
	public ProductManager getProductManager(){
		return productManager;
	}
	public CategoryManager getCategoryManager(){
		return categoryManager;
	}
	public DiscountManager getDiscountManager(){
		return discountManager;
	}
	public LoginManager getLoginManager(){
		return loginManager;
	}
	public VendorManager getVendorManager(){
		return vendorManager;
	}
	public TransactionManager getTransactionManager(){
		return transactionManager;
	}
	public CustomerManager getCustomerManager(){
		return customerManager;
	}
	public BillingManager getBillingManager(){
		return billingManager;
	}

    public void shutdown () {
        System.exit(0);
    }
	

}
