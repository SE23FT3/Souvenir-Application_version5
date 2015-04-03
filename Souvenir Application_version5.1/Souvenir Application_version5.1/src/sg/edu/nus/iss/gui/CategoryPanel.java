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
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Category;
import sg.edu.nus.iss.models.Member;
import sg.edu.nus.iss.models.Product;
import sg.edu.nus.iss.service.CategoryManager;

public class CategoryPanel extends JPanel {


	private StoreApplication manager;
	private JTable categoryTable;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private CategoryManager categoryManager;
	private ArrayList<Category> categoryList;

	public CategoryPanel(CategoryManager categoryManager)throws IOException{
		this.categoryManager = categoryManager;
		categoryTable = new JTable();
		scrollPane = new JScrollPane(categoryTable);
		tableModel=new DefaultTableModel(0,2);
		
		categoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setLayout(new BorderLayout());
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
		fieldsName.addElement("Category Code");
		fieldsName.addElement("Category Name");

		// Create the combo box, and set 2nd item as Default
		searchCombo = new JComboBox(fieldsName);
		SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);

		JLabel spacer, bridgeSet, numOfBars;
		JSpinner deckElevation = new JSpinner(new SpinnerNumberModel(24, 0, 24, 4));
		JSpinner archHeight = new JSpinner(new SpinnerNumberModel(4, 4, 24, 4));
		JSpinner pierHeight = new JSpinner(new SpinnerNumberModel(0, 0, 24, 4));
		panel.add(bridgeSet = new JLabel("Search Your Category:"), "wrap");

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
						ArrayList<Category> categoryList= (ArrayList<Category>) categoryManager.searchDataAndDisplay(data,value);
						if(categoryList.size()!=0){
							addComponeneTable(categoryList);
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
        		
        		ArrayList newCategoryList=null;
				try {
					newCategoryList = categoryManager.retrieveCategoryDataFromFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}						
					 addComponeneTable(newCategoryList);
        	}
        });
        
        panel.add(resetButton);
		return panel;
	}
	
	void addComponeneTable(ArrayList<Category> categoryList) {

		System.out.println("******************************* addComponeneTable  **********");
		DefaultTableModel tableModel=new DefaultTableModel(0,2);
		String[] columns = {"Category Code", "Category Name"};
		tableModel.setColumnIdentifiers(columns);
		categoryTable.setModel(tableModel);

		Iterator<Category> iterator = categoryList.iterator();
		while (iterator.hasNext()) {
			Category category=iterator.next();

			String line=category.getCategoryCode()+","
					+category.getCategoryName();
			System.out.println(line);

			tableModel.addRow(line.split(",")); 
		}


		scrollPane.setViewportView(categoryTable);

		//return scrollPane;

	}

	private JScrollPane createTablePanel() throws IOException {
	    DefaultTableModel tableModel=new DefaultTableModel(0,2);
		String[] columns = {"Category Code", "Category Name"};
	        tableModel.setColumnIdentifiers(columns);
        categoryTable.setModel(tableModel);
		FileReader r = new FileReader("./data/Category.dat");
		BufferedReader br = new BufferedReader(r);
		String line = null;
		while((line=br.readLine())!=null){
			tableModel.addRow(line.split(",")); 
		}
		br.close();
		scrollPane.setViewportView(categoryTable);

		return scrollPane;
	}

	private JPanel createButtonPanel() {
		JPanel p = new JPanel(new GridLayout(0,1));
		JButton b = new JButton("Add");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddCategoryDialog d;
				try {
					d = new AddCategoryDialog (categoryManager);
					d.pack();
					d.setVisible (true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
        p.add (b);
        JPanel bp = new JPanel();
        bp.setLayout(new BorderLayout());
        bp.add("North",p);
        
        JButton deleteButton = new JButton ("Delete");
        deleteButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                int i= categoryTable.getSelectedRow();
              String categoryCode=(String)categoryTable.getModel().getValueAt(i, 0);
                 System.out.println("Deleted:::"+i+"  categoryCode:"+categoryCode);
                 ArrayList newdcategoryListt=null;
                 ArrayList categoryList = null;
  			try {
  				categoryList = categoryManager.retrieveCategoryDataFromFile();
  				 System.out.println("categoryList before deleteion:::"+categoryList.size());
  				Category removeCategory=new Category();
  				 Iterator<Category> iterator = categoryList.iterator();
  					while (iterator.hasNext()) {							
  						Category catgory=iterator.next();
  						if(catgory.getCategoryCode().equalsIgnoreCase(categoryCode))
  						{
  							
  							removeCategory=catgory;
  							categoryList.remove(removeCategory);
  							break;
  						}
  						
  					}
  					 
  					boolean valid=categoryManager.writeBackToFile(categoryList);
  					if(valid)
  					{
  					System.out.println("categoryList::"+categoryList.size());
  				// System.out.println("Deleted: dscount code::::::::"+d.getDiscountCode()+d.getApplicability());
  				 
  				 addComponeneTable(categoryList);
  					}
  					else
  					{
  						JOptionPane.showMessageDialog(null,"Deletion is not succesful.");
  					}
  				 
  			} catch (IOException e1) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
                 }
     	       
              
          });
       
        p.add (deleteButton);
        
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

}
