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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Discount;
import sg.edu.nus.iss.models.Product;
import sg.edu.nus.iss.service.DiscountManager;

public class DiscountPanel extends JPanel {
	private StoreApplication manager;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable discountTable;
	private DiscountManager discountManager;
	
	public DiscountPanel(DiscountManager discountManager) throws IOException{
		this.discountManager = discountManager;
		discountTable = new JTable();
        scrollPane = new JScrollPane(discountTable);
	    tableModel=new DefaultTableModel(0,2);

        setLayout (new BorderLayout());
        add("North",createSearchPanel());
    	add("East",createButtonPanel());
	    add("Center",createTablePanel());
	    

 
	}
	private Component createSearchPanel() {

		  JPanel panel = new JPanel (new GridLayout (1, 1));
		  		 final JComboBox searchCombo;
		  		 final DefaultComboBoxModel fieldsName = new DefaultComboBoxModel();
		  		JButton searchButton;
		  		final  JTextField searchField;
		  		fieldsName.addElement("Discount Code");
		  		fieldsName.addElement("Applicability");
		  		fieldsName.addElement("Discount Description");
		  		
		 
				// Create the combo box, and set 2nd item as Default
		  		searchCombo = new JComboBox(fieldsName);
			    SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);
			    
			    JLabel spacer, bridgeSet, numOfBars;
			    JSpinner deckElevation = new JSpinner(new SpinnerNumberModel(24, 0, 24, 4));
			    JSpinner archHeight = new JSpinner(new SpinnerNumberModel(4, 4, 24, 4));
			    JSpinner pierHeight = new JSpinner(new SpinnerNumberModel(0, 0, 24, 4));
			    panel.add(bridgeSet = new JLabel("Search Your Offers And Discount :"), "wrap");
			  
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
		                    	 ArrayList<Discount> discountList=discountManager.searchDataAndDisplay(data,value);
		                    	  if(discountList.size()!=0){
                    	        	  addComponeneTable(discountList);
                    	 }
                    	 else
                    	 {
                    		 JOptionPane.showMessageDialog(null, "There is no data found for this search!");
                    		 searchField.setText("");
                    	 }
		                     }
		                    
		                  } 
		        	}
		        });
		        
		        panel.add(searchCombo );
		        panel.add(searchField);
		       
		        panel.add(searchButton, "span, grow");
		        
		        JButton resetButton=new JButton("Reset");
		        
		        resetButton.addActionListener(new ActionListener() {
		        	@SuppressWarnings("rawtypes")
					public void actionPerformed(ActionEvent e) {
		        		
		        		ArrayList newdiscountList=null;
						try {
							newdiscountList = discountManager.retrieveDiscountDataFromFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
							 addComponeneTable(newdiscountList);
		        	}
		        });
		        
		        panel.add(resetButton);
				return panel;
	
	}
	private Component createTablePanel() throws IOException {
	    DefaultTableModel tableModel=new DefaultTableModel(0,2);
		String[] columns = {"Discount code", "Discount Description"," Start Date","Duration of Discount","Discount Percentage","Applicable"};
	        tableModel.setColumnIdentifiers(columns);
	        discountTable.setModel(tableModel);
	       // String oneline = null;
	        ArrayList discountList=discountManager.getDiscountList();
	        System.out.println("createTablePanel::discountList::"+discountList.size());
	        Iterator<Discount> iterator = discountList.iterator();
			while (iterator.hasNext()) {
				Discount d=iterator.next();
				String line=d.getDiscountCode()+","+d.getDiscountDescription()+","+d.getStartDate()+","+d.getDurationOfDiscount()+","+d.getDiscountPercentage()+","+d.getApplicability(); 
			
				
					tableModel.addRow(line.split(",")); 
					}
	
	
		scrollPane.setViewportView(discountTable);

		return scrollPane;
	}
	private Component createButtonPanel() {


        JPanel p = new JPanel (new GridLayout (0, 1));

        JButton b = new JButton ("Add");
        b.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                AddDiscountDialog d;
				d = new  AddDiscountDialog (discountManager);
				d.pack();
				d.setVisible (true);

            }
        });
        p.add (b);
        JButton deleteButton = new JButton ("Delete");
        deleteButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
              int i= discountTable.getSelectedRow();
            
               System.out.println("Deleted:::"+i);
              
               ArrayList discountList = null;
			try {
				discountList = discountManager.getDiscountList();
				 Discount d=(Discount) discountList.get(i);
				 System.out.println("Deleted: dscount code::::::::"+d.getDiscountCode()+d.getApplicability());
				 ArrayList  newdiscountList=discountManager.deleteDiscountData(d);
				
				 System.out.println("newdiscountLis::"+newdiscountList.size());
				 addComponeneTable(newdiscountList);
				 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
               }
   	       
           
        });
        p.add (deleteButton);
        JPanel bp = new JPanel();
        bp.setLayout(new BorderLayout());
        bp.add("North",p);
        
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
	
	void addComponeneTable(List<Discount> discountList) {

		System.out.println("******************************* addComponeneTable  **********");
	    DefaultTableModel tableModel=new DefaultTableModel(0,2);
		String[] columns = {"Discount code", "Discount Description"," Start Date","Duration of Discount","Discount Percentage","Applicable"};
	        tableModel.setColumnIdentifiers(columns);
	        discountTable.setModel(tableModel);
	      
	        Iterator<Discount> iterator = discountList.iterator();
			while (iterator.hasNext()) {
				Discount d=iterator.next();
				String line=d.getDiscountCode()+","+d.getDiscountDescription()+","+d.getStartDate()+","+d.getDurationOfDiscount()+","+d.getDiscountPercentage()+","+d.getApplicability(); 
			System.out.println(line);
				
					tableModel.addRow(line.split(",")); 
					}
	
	
		scrollPane.setViewportView(discountTable);

		//return scrollPane;
	
	}
	protected void dispose() {
		this.setVisible(false);		
	}
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
