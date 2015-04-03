package sg.edu.nus.iss.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Category;
import sg.edu.nus.iss.models.Vendor;
import sg.edu.nus.iss.service.CategoryManager;
import sg.edu.nus.iss.service.VendorManager;

public class VendorPanel extends JPanel {


	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private VendorManager vendorManager;
	private JTable vendorTable;
	private CategoryManager categoryManager;

	public VendorPanel(VendorManager vendorManager)throws IOException{
		this.vendorManager = vendorManager;
		categoryManager = new CategoryManager();
		vendorTable = new JTable();
		scrollPane = new JScrollPane(vendorTable);
		tableModel=new DefaultTableModel(0,2);
		
		setLayout(new BorderLayout());
    	add("North",createSearchPanel());
		add("East",createButtonPanel());
		add("Center",createTablePanel());
	}

	private Component createSearchPanel() {
		  JPanel panel = new JPanel (new GridLayout (1, 1));
	  		 final JComboBox searchCombo;
	  		 final DefaultComboBoxModel fieldsName = new DefaultComboBoxModel();
	  		 final DefaultComboBoxModel categoryName = new DefaultComboBoxModel();
	  		JButton searchButton;
	  		final JComboBox categoryNameCombo;
	  		final  JTextField searchField;
	  		fieldsName.addElement("Vendor Name");
	  		fieldsName.addElement("Vendor Description");
	  

	  		
	  		List<String> categoryNameList=new ArrayList<String>();
	  		List<Category> categoryList;
			try {
				categoryList = categoryManager.retrieveCategoryDataFromFile();
				Iterator<Category> iterator = categoryList.iterator();
				String categoryDesc=null;
					while (iterator.hasNext()) {							
						Category cat=iterator.next();
						categoryDesc=cat.getCategoryName();
						categoryNameList.add(categoryDesc);
					}
		  		
					System.out.println("categoryNameList"+categoryNameList.size());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	  		
	  		for(String s : categoryNameList)
	  		{
	  			categoryName.addElement	(s);
	  		}
	 
			// Create the combo box, and set 2nd item as Default
	  		searchCombo = new JComboBox(fieldsName);
		    SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);
		    
		    JLabel spacer, bridgeSet, numOfBars;
		    JSpinner deckElevation = new JSpinner(new SpinnerNumberModel(24, 0, 24, 4));
		    JSpinner archHeight = new JSpinner(new SpinnerNumberModel(4, 4, 24, 4));
		    JSpinner pierHeight = new JSpinner(new SpinnerNumberModel(0, 0, 24, 4));
		    panel.add(bridgeSet = new JLabel("Provide the Category Name :"), "wrap");
		    categoryNameCombo=new JComboBox(categoryName);
		    panel.add(categoryNameCombo);
		    
		    JButton showButton=new JButton("Show");
		    panel.add(showButton);
		    showButton.addActionListener(new ActionListener() {
	        	@SuppressWarnings("rawtypes")
				public void actionPerformed(ActionEvent e) {
	        		String data = "";
	                  if (categoryNameCombo.getSelectedIndex() != -1) {                     
	                     data = (String) categoryNameCombo.getItemAt
	                          (categoryNameCombo.getSelectedIndex());  
	                     System.out.println("Data"+data);
	                    String  categoryCode=null;
	                     ArrayList<Category> categoryList;
						try {
							categoryList = categoryManager.retrieveCategoryDataFromFile();
							Iterator<Category> iterator = categoryList.iterator();
		     				while (iterator.hasNext()) {							
		     					Category cat=iterator.next();
		     					if(cat.getCategoryName().equalsIgnoreCase(data))
		     					{
		     						categoryCode=cat.getCategoryCode();
		     						String fileName="data/Vendors"+categoryCode+".dat";
		     						
		     						System.out.println("fileName"+fileName);
		     						ArrayList<Vendor> vendorList=vendorManager.retrieveVendorDataFromFile( fileName);
		     						System.out.println("vendorList"+vendorList.size());
		     						addComponeneTable(vendorList);
		     					}
		     					
		     				}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                     
	                     System.out.println("categoryCode"+categoryCode);
	                  }
	        	}
	        });
		    panel.add(bridgeSet = new JLabel("Search Your Offers & Discount :"), "wrap");
		  
	//	    searchCombo.addItem("");
	//	    searchCombo.addItem("");
	        panel.add(spacer = new JLabel(" "),"span, grow");
	    
	     searchField = new JTextField();
	        searchButton=new JButton("Go");
	        searchButton.addActionListener(new ActionListener() {
	        	@SuppressWarnings("rawtypes")
				public void actionPerformed(ActionEvent e) {
	        		  String data = "";
	                  if (searchCombo.getSelectedIndex() != -1) {                     
	                     data = (String) searchCombo.getItemAt
	                          (searchCombo.getSelectedIndex());  
	                     if(searchField!=null)
	                     {
	                    	 System.out.println("data in combobox ::"+data+" value:"+searchField.getText());
	                    
	                    	 String value=searchField.getText();
	                    	 vendorManager.searchDataAndDisplay(data,value);
	                     }
	                    
	                  } 
	        	}
	        });
	        
	        panel.add(searchCombo );
	        panel.add(searchField);
	        panel.add(searchButton, "span, grow");
	        
			return panel;
	}

	private JScrollPane createTablePanel() throws IOException {
	    DefaultTableModel tableModel=new DefaultTableModel(0,2);
		String[] columns = {"VendorName", "VendorDescription"};
	        tableModel.setColumnIdentifiers(columns);
        vendorTable.setModel(tableModel);
		FileReader r = new FileReader("./data/Vendors.dat");
		BufferedReader br = new BufferedReader(r);
		String line = null;
		while((line=br.readLine())!=null){
			tableModel.addRow(line.split(",")); 
		}
		br.close();
		scrollPane.setViewportView(vendorTable);

		return scrollPane;
	}

	private JPanel createButtonPanel() {
		JPanel p = new JPanel(new GridLayout(0,1));
		JButton b = new JButton("Add");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddVendorDialog d;
				d = new AddVendorDialog (vendorManager);
				d.pack();
				d.setVisible (true);
			}


		});
        p.add (b);
        JPanel bp = new JPanel();
        bp.setLayout(new BorderLayout());
        bp.add("North",p);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		//
        		//
        		//
        		//
        		//
        		
        	}
        });
        p.add(btnDelete);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				//menu.setVisible(true);
				dispose();
        	}
        });
        p.add(btnBack);
        return bp;
        
	}
	protected void dispose() {
		this.setVisible(false);		
	}
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
	
	void addComponeneTable(List<Vendor> vendorList) {

		System.out.println("******************************* addComponeneTable  **********");
	    DefaultTableModel tableModel=new DefaultTableModel(0,2);
	    String[] columns = {"VendorName", "VendorDescription"};
	        tableModel.setColumnIdentifiers(columns);
	        vendorTable.setModel(tableModel);
	      
	        Iterator<Vendor> iterator = vendorList.iterator();
			while (iterator.hasNext()) {
				Vendor v=iterator.next();
				String line=v.getVendorName()+","+v.getVendorDescription();
			System.out.println(line);
				
					tableModel.addRow(line.split(",")); 
					}
	
	
		scrollPane.setViewportView(vendorTable);

		//return scrollPane;
	
	}
//	private JPanel AddVendorDialog() {
//		// TODO Auto-generated method stub
//		
//	}

}
