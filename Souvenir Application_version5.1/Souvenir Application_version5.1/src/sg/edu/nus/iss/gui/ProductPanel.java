package sg.edu.nus.iss.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Member;
import sg.edu.nus.iss.models.Product;
import sg.edu.nus.iss.service.ProductManager;
import sg.edu.nus.iss.service.StoreManager;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class ProductPanel extends JPanel{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private StoreManager manager;
	private ProductManager productManager;
	private java.util.List<Product> products;
	private JScrollPane scrollPane;
	private JTable productTable;
	private DefaultTableModel tableModel;
	private ProductPanel productPanel;
	private MainMenu menu;
    private WindowListener windowListener = new WindowAdapter () {
        public void windowClosing (WindowEvent e) {
            manager.shutdown ();
        }
    };
	
	public ProductPanel(ProductManager productManager) throws IOException{
		this.productManager = productManager;
        productTable = new JTable();
        scrollPane = new JScrollPane(productTable);
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
		fieldsName.addElement("Product ID");
		fieldsName.addElement("Product Name");
		fieldsName.addElement("Product Description");
		fieldsName.addElement("Price");

		// Create the combo box, and set 2nd item as Default
		searchCombo = new JComboBox(fieldsName);
		SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);

		JLabel spacer, bridgeSet, numOfBars;
		JSpinner deckElevation = new JSpinner(new SpinnerNumberModel(24, 0, 24, 4));
		JSpinner archHeight = new JSpinner(new SpinnerNumberModel(4, 4, 24, 4));
		JSpinner pierHeight = new JSpinner(new SpinnerNumberModel(0, 0, 24, 4));
		panel.add(bridgeSet = new JLabel("Search Your Product:"), "wrap");

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
						ArrayList<Product> productList= (ArrayList<Product>) productManager.searchDataAndDisplay(data,value);
						if(productList.size()!=0){
							addComponeneTable(productList);
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

		panel.add(searchCombo);
		panel.add(searchField);
		panel.add(searchButton, "span, grow");
		 JButton resetButton=new JButton("Reset");
	        
	        resetButton.addActionListener(new ActionListener() {
	        	@SuppressWarnings("rawtypes")
				public void actionPerformed(ActionEvent e) {
	        		
	        		ArrayList newProductList=null;
					try {
						newProductList = productManager.retrieveProductDataFromFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}						
						 addComponeneTable(newProductList);
	        	}
	        });
	        
	        panel.add(resetButton);
		return panel;
	}



	public JScrollPane createTablePanel() throws IOException {
		    DefaultTableModel tableModel=new DefaultTableModel(0,2);
			String[] columns = {"ProductId", "Product Name", "Product Description", "Quantity","Product Price","Bar Code"
		    		,"Reorder Threshold","Order Quantity"};
		        tableModel.setColumnIdentifiers(columns);
	        productTable.setModel(tableModel);
			FileReader r = new FileReader("./data/Products.dat");
			BufferedReader br = new BufferedReader(r);
			String line = null;
			while((line=br.readLine())!=null){
				tableModel.addRow(line.split(",")); 
			}
			br.close();
			scrollPane.setViewportView(productTable);
	
			return scrollPane;
		}

	private JPanel createButtonPanel() {
        JPanel p = new JPanel (new GridLayout (0, 1));

        JButton b = new JButton ("Add");
        b.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                AddProductDialog d;
				try {
					d = new AddProductDialog (productManager);
	                d.pack();
	                d.setVisible (true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

            }
        });
        p.add (b);
        JButton deleteButton = new JButton ("Delete");
        deleteButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
              int i= productTable.getSelectedRow();
            
               System.out.println("Deleted:::"+i);
               if(i>=0){
               ArrayList productList = null;
			try {
				productList = productManager.retrieveProductDataFromFile();
				 Product product=(Product) productList.get(i);
				// System.out.println("Deleted: dscount code::::::::"+d.getDiscountCode()+d.getApplicability());
				 ArrayList  newproductList=productManager.deleteProductData(product);
							 
				 addComponeneTable(newproductList);
				 
				 
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
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
				invisiable();

        	}
        });
        p.add(btnBack);
        return bp;
	}
	public void invisiable() {
		this.setVisible(false);
	}
	void addComponeneTable(ArrayList<Product> productList) {

		System.out.println("******************************* addComponeneTable  **********");
		DefaultTableModel tableModel=new DefaultTableModel(0,2);
		String[] columns = {"ProductId", "Product Name", "Product Description", "Quantity","Product Price","Bar Code"
				,"Reorder Threshold","Order Quantity"};
		tableModel.setColumnIdentifiers(columns);
		productTable.setModel(tableModel);

		Iterator<Product> iterator = productList.iterator();
		while (iterator.hasNext()) {
			Product product=iterator.next();

			String line=product.getProductId()+","
					+product.getProductName()+","+product.getProductDescription()+","+product.getQuantityAvaliable()+","+
					product.getProductPrice()+","+product.getBarCode()+","+product.getReorderThreshold()+","+product.getOrderQuantity();
			System.out.println(line);

			tableModel.addRow(line.split(",")); 
		}


		scrollPane.setViewportView(productTable);

		//return scrollPane;

	}

}
