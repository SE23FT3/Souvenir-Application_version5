package sg.edu.nus.iss.gui;

import java.io.*;
import java.awt.*;
//import com.lowagie.text.*;
//import com.lowagie.text.pdf.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.zip.*;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.text.Document;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.report.WriteCategoryExcelFile;
import sg.edu.nus.iss.report.WriteMemberExcelFile;
import sg.edu.nus.iss.report.WriteProductExcelFile;
import sg.edu.nus.iss.service.BillingManager;
import sg.edu.nus.iss.service.CategoryManager;
import sg.edu.nus.iss.service.CustomerManager;
import sg.edu.nus.iss.service.DiscountManager;
import sg.edu.nus.iss.service.ProductManager;
import sg.edu.nus.iss.service.StoreManager;
import sg.edu.nus.iss.service.TransactionManager;
import sg.edu.nus.iss.service.VendorManager;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.Pageable;
import java.awt.CardLayout;
import java.io.IOException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;


public class MainMenu extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private StoreManager manager;
	private ProductPanel productPanel;
    final CardLayout card;
	private VendorPanel vendorPanel;
	private CategoryPanel categoryPanel;
	private ProductManager productManager;
	private CustomerManager customerManager;
	private TransactionManager transactionManager;
	private VendorManager vendorManager;
	private CategoryManager categoryManager;
	private DiscountManager discountManager;
	private BillingManager billingManager;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JFileChooser fc;

	public MainMenu(final StoreManager manager) throws IOException {
	     card=new CardLayout();
	     productManager = new ProductManager(this);
	     customerManager = new CustomerManager(this);
	     transactionManager = new TransactionManager(this);
	     vendorManager = new VendorManager(this);
	     categoryManager = new CategoryManager(this);
	     discountManager = new DiscountManager(this);
	     billingManager = new BillingManager(this);
	     

		getContentPane().setLayout(card);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, "name_9596431583344");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("MENU");
		label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 24));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(label, BorderLayout.NORTH);
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		
				GridBagLayout gbl_panel = new GridBagLayout();
				gbl_panel.columnWidths = new int[]{59, 89, 221, 249, 0};
				gbl_panel.rowHeights = new int[]{35, 15, 29, 32, 32, 208, 50, 0, 0};
				gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_panel.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panel.setLayout(gbl_panel);
				
				JLabel lblNewLabel_1 = new JLabel(new ImageIcon("./images/billing.jpg"));
				lblNewLabel_1.setBackground(new Color(255, 255, 255));
				lblNewLabel_1.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseClicked(MouseEvent e)  
				    {  
				    	try {
							displayBillingPanel();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }  
				}); 
				
				lblNewLabel_7 = new JLabel();
				lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 15));
				GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
				gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_7.gridx = 1;
				gbc_lblNewLabel_7.gridy = 0;
				panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
				
				JButton btnNewButton = new JButton("Logout");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							manager.start();
							dispose();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.anchor = GridBagConstraints.EAST;
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
				gbc_btnNewButton.gridx = 3;
				gbc_btnNewButton.gridy = 0;
				panel.add(btnNewButton, gbc_btnNewButton);
				
				
				lblNewLabel_8 = new JLabel();
				this.clock(lblNewLabel_8);
				GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
				gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_8.gridx = 1;
				gbc_lblNewLabel_8.gridy = 1;
				panel.add(lblNewLabel_8, gbc_lblNewLabel_8);
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 2;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
				
				JLabel lblNewLabel_2 = new JLabel(new ImageIcon("./images/member.jpg"));
				lblNewLabel_2.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseClicked(MouseEvent e)  
				    {  
				    	try {
							displayCustomerPanel();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }  
				}); 
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 2;
				gbc_lblNewLabel_2.gridy = 2;			
				panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
				
				JLabel lblNewLabel_3 = new JLabel(new ImageIcon("./images/products.jpg"));
				lblNewLabel_3.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseClicked(MouseEvent e)  
				    {  
				    	try {
							displayProductPanel();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				    }  
				}); 
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel_3.gridx = 3;
				gbc_lblNewLabel_3.gridy = 2;
				panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
				
		JLabel lblNewLabel_4 = new JLabel(new ImageIcon("./images/vendors.jpg"));
		lblNewLabel_4.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {
		    	try {
					displayVendorPanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }  
		}); 
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 5;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel(new ImageIcon("./images/category.jpg"));
		lblNewLabel_5.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	try {
					displayCategoryPanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }  
		}); 
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 5;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(new ImageIcon("./images/discount.jpg"));
		lblNewLabel_6.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e)  
		    {  
		    	try {
					displayDiscountPanel();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }  
		}); 
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 5;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		getContentPane().add(productManager.getProductPanel(), "productPanel");
		getContentPane().add(customerManager.getMemberPanel(),"memberPanel");
	//	getContentPane().add(transactionManager.getTransactionPanel(),"transactionPanel");
		getContentPane().add(vendorManager.getVendorPanel(),"vendorPanel");
		getContentPane().add(categoryManager.getCategoryPanel(),"categoryPanel");
		getContentPane().add(discountManager.getDiscountPanel(),"discountPanel");
		getContentPane().add(billingManager.getBillingPanel(),"billingPanel");
		

		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnPrint = new JMenu("Report");
		mnFile.add(mnPrint);
		
		JMenuItem mntmCategory = new JMenuItem("Category Report");
		mntmCategory.addActionListener(this);
		mnPrint.add(mntmCategory);
		mntmCategory.setActionCommand("Category");
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Product Report");
		mntmNewMenuItem.addActionListener(this);
		mnPrint.add(mntmNewMenuItem);
		mntmNewMenuItem.setActionCommand("Product");
		
		
		JMenuItem mntmTransactionReport = new JMenuItem("Transaction Report");
		mntmTransactionReport.addActionListener(this);
		mnPrint.add(mntmTransactionReport);
		mntmTransactionReport.setActionCommand("Transactionr");
		
		
		JMenuItem mntmMemberReport = new JMenuItem("Member Report");
		mnPrint.add(mntmMemberReport);
		mntmMemberReport.addActionListener(this);
		mntmMemberReport.setActionCommand("Member");
		
		JMenu mnTransaction = new JMenu("Transaction");
		mnFile.add(mnTransaction);
		
		JMenuItem mntmDailyRecord = new JMenuItem("Daily Record");
		mnTransaction.add(mntmDailyRecord);
		
		JMenuItem mntmMonthlyRecord = new JMenuItem("Monthly Record");
		mnTransaction.add(mntmMonthlyRecord);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mnFile.add(mntmNewMenuItem_1);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAboutUss = new JMenuItem("About USS");
		mnHelp.add(mntmAboutUss);
		

	}
	public void BackToMenu(){
		card.show(getContentPane(),"menu");
	}
	public void refreshProduct() throws IOException {
		productPanel.createTablePanel();
		
	}
	private WindowListener windowListener = new WindowAdapter () {
        public void windowClosing (WindowEvent e) {
            manager.shutdown ();
        }
    };
    


    
    public void setUserName(String uname){
    	lblNewLabel_7.setText("Storekeeper: "+uname);
    	
    }



	public void refresh() throws IOException {
		productPanel.createTablePanel();
		
	}
	public void refreshProductPanel() {
		getContentPane().remove(productManager.getProductPanel());		
	}
	public void displayProductPanel() throws IOException {
	     productManager = new ProductManager(this);
		getContentPane().add(productManager.getProductPanel(), "productPanel");
		card.show(getContentPane(),"productPanel");
		// TODO Auto-generated method stub
		
	}
	public void refreshCustomerPanel() {
		getContentPane().remove(customerManager.getMemberPanel());	
		
	}
	public void displayCustomerPanel() throws IOException {
		// TODO Auto-generated method stub
	    customerManager = new CustomerManager(this);
		getContentPane().add(customerManager.getMemberPanel(), "memberPanel");
		card.show(getContentPane(),"memberPanel");
		
	}
	public void refreshVendorPanel() {
		getContentPane().remove(vendorManager.getVendorPanel());		
		
	}
	public void displayVendorPanel() throws IOException {
	    vendorManager = new VendorManager(this);
		getContentPane().add(vendorManager.getVendorPanel(), "vendorPanel");
		card.show(getContentPane(),"vendorPanel");
		
	}
	public void refreshCategoryPanel() {
		getContentPane().remove(categoryManager.getCategoryPanel());		
		
	}
	
	public void displayCategoryPanel() throws IOException {
	    categoryManager = new CategoryManager(this);
		getContentPane().add(categoryManager.getCategoryPanel(), "categoryPanel");
		card.show(getContentPane(),"categoryPanel");
		
	}
	public void refreshDiscountPanel() {
		getContentPane().remove(discountManager.getDiscountPanel());		
		
	}
	public void displayDiscountPanel() throws IOException {
	    discountManager = new DiscountManager(this);
		getContentPane().add(discountManager.getDiscountPanel(), "discountPanel");
		card.show(getContentPane(),"discountPanel");
		
	}
	
	public void displayBillingPanel() throws IOException{
		billingManager = new BillingManager(this);
		getContentPane().add(billingManager.getBillingPanel(),"billingPanel");
		card.show(getContentPane(),"billingPanel");
	}
	public void clock(final JLabel jlabel){
		Thread clock = new Thread(){
			public void run(){
				try {
					while(true){
					Calendar cal = new GregorianCalendar();
					int day = cal.get(Calendar.DAY_OF_MONTH);
					int month = cal.get(Calendar.MONTH);
					int year = cal.get(Calendar.YEAR);
					
					int second = cal.get(Calendar.SECOND);
					int minute =cal.get(Calendar.MINUTE);
					int hour = cal.get(Calendar.HOUR);
					month = month +1;
					
					jlabel.setText("Time:"+hour+":"+minute+":"+second+" "+"Date:"+day+"/"+month+"/"+year);
					sleep(1000);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		clock.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String action=e.getActionCommand();
		if(action.equals("Product"))
		{
			//WriteProductExcelFile excel=new WriteProductExcelFile();
			//excel.write();
			 Object[] options = { "OPEN", "SAVE" };
			  int choice = JOptionPane.showOptionDialog(null, 
			      "Do you want to open or save the report?", 
			      "Report Generation", 
			      JOptionPane.YES_NO_OPTION, 
			      JOptionPane.QUESTION_MESSAGE, 
			      null, 
			      options, 
			      options[0]);
			 
			  // interpret the user's choice
			  if (choice == JOptionPane.YES_OPTION)
			  {
			    //System.exit(0);
				  WriteProductExcelFile excel1=new WriteProductExcelFile();
					excel1.write();
					fc=new JFileChooser();
				  int returnVal = fc.showOpenDialog(MainMenu.this);
				  
		            if (returnVal == JFileChooser.APPROVE_OPTION) 
		            {
		                File file = fc.getSelectedFile();
		                try
		                {
							java.awt.Desktop.getDesktop().open(file);
						}
		                catch (IOException e1) 
		                {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		            } 

			  }
			  if(choice == JOptionPane.NO_OPTION)
			  {
				    System.exit(0);
			  }
		}
			  if(action.equals("Category"))
				{
					//WriteProductExcelFile excel=new WriteProductExcelFile();
					//excel.write();
					 Object[] options1 = { "YES", "NO" };
					  int choice1 = JOptionPane.showOptionDialog(null, 
					      "Do you want to download the report?", 
					      "Report Generation", 
					      JOptionPane.YES_NO_OPTION, 
					      JOptionPane.QUESTION_MESSAGE, 
					      null, 
					      options1, 
					      options1[0]);
					 
					  // interpret the user's choice
					  if (choice1 == JOptionPane.YES_OPTION)
					  {
					    //System.exit(0);
						  WriteCategoryExcelFile excel1=new WriteCategoryExcelFile();
							excel1.write();	
							fc=new JFileChooser();
							  int returnVal = fc.showOpenDialog(MainMenu.this);
							  
					            if (returnVal == JFileChooser.APPROVE_OPTION) 
					            {
					                File file = fc.getSelectedFile();
					                try
					                {
										java.awt.Desktop.getDesktop().open(file);
									}
					                catch (IOException e1) 
					                {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
					            } 


					  }
					  if(choice1 == JOptionPane.NO_OPTION)
					  {
						    System.exit(0);
					  }
				}
			    if(action.equals("Member"))
			    {
			    	 Object[] options1 = { "YES", "NO" };
					  int choice1 = JOptionPane.showOptionDialog(null, 
					      "Do you want to download the report?", 
					      "Report Generation", 
					      JOptionPane.YES_NO_OPTION, 
					      JOptionPane.QUESTION_MESSAGE, 
					      null, 
					      options1, 
					      options1[0]);
					 
					  // interpret the user's choice
					  if (choice1 == JOptionPane.YES_OPTION)
					  {
					    //System.exit(0);
						 WriteMemberExcelFile excel1=new WriteMemberExcelFile();
							excel1.write();					
							fc=new JFileChooser();
							  int returnVal = fc.showOpenDialog(MainMenu.this);
							  
					            if (returnVal == JFileChooser.APPROVE_OPTION) 
					            {
					                File file = fc.getSelectedFile();
					                try
					                {
										java.awt.Desktop.getDesktop().open(file);
									}
					                catch (IOException e1) 
					                {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
					            } 
					  }
					  if(choice1 == JOptionPane.NO_OPTION)
					  {
						    System.exit(0);
					  }
			    }
		
		}


	}
