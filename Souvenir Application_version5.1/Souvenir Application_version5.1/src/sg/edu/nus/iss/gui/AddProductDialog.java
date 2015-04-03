package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Category;
import sg.edu.nus.iss.service.CategoryManager;
import sg.edu.nus.iss.service.ProductManager;
import sg.edu.nus.iss.util.OkCancelDialog;

import javax.swing.JComboBox;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

public class AddProductDialog extends OkCancelDialog implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5641760134521542673L;
	private StoreApplication manager;
	private ProductPanel productPanel;
	private JTextField productIdField;
	private JTextField productNameField;
	private JTextField productDescriptionField;
	private JTextField quantityAvaliableField;
	private JTextField productPriceField;
	private JTextField barCodeField;
	private JTextField reorderThresholdField;
	private JTextField orderQuantityField;
	private JComboBox comboBox;
	private List<Category> categoryList;
	private JLabel lblMessage;

	
	public AddProductDialog(ProductPanel productPanel,StoreApplication manager) {
        super ( null, "Add Product");
        this.manager= manager;	
        this.productPanel = productPanel;


	}

	@Override
	public JPanel createFormPanel()  {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,2));
		p.add(new JLabel("CategoryCode"));
		final DefaultComboBoxModel productCodeName = new DefaultComboBoxModel();

		
  		List<String> productCodeNameList=new ArrayList<String>();
  		categoryList = null;
		
		try {
			categoryList = manager.getCategoryManager().retrieveCategoryDataFromFile();
			Iterator<Category> iterator = categoryList.iterator();
			String categoryDesc=null;
				while (iterator.hasNext()) {							
					Category cat=iterator.next();
					categoryDesc=cat.getCategoryCode();
					productCodeNameList.add(categoryDesc);
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
			System.out.println("categoryNameList"+productCodeNameList.size());
  		for(String s : productCodeNameList)
  		{
  			productCodeName.addElement	(s);
  		}
		comboBox = new JComboBox(productCodeName);
		p.add(comboBox);
		p.add(new JLabel("Product Name"));
		productNameField = new JTextField(20);
		productNameField.addKeyListener(this);
		p.add(productNameField);
		p.add(new JLabel("Product Description"));
		productDescriptionField = new JTextField(20);
		productDescriptionField.addKeyListener(this);
		p.add(productDescriptionField);
		p.add(new JLabel("Product Quantity"));
		quantityAvaliableField = new JTextField(20);
		quantityAvaliableField.addKeyListener(this);
		p.add(quantityAvaliableField);
		p.add(new JLabel("Product Price"));
		productPriceField = new JTextField(20);
		productPriceField.addKeyListener(this);
		p.add(productPriceField);
		p.add(new JLabel("Bar Code"));
		barCodeField = new JTextField(20);
		barCodeField.addKeyListener(this);
		p.add(barCodeField);
		p.add(new JLabel("Reorder Threshold"));
		reorderThresholdField = new JTextField(20);
		reorderThresholdField.addKeyListener(this);
		p.add(reorderThresholdField);
		p.add(new JLabel("Order Quantity"));
		orderQuantityField = new JTextField(20);
		orderQuantityField.addKeyListener(this);
		p.add(orderQuantityField);
		
		lblMessage = new JLabel();
		lblMessage.setForeground(Color.RED);
		p.add(lblMessage);
		return p;
	}

	@Override
	protected boolean performOkAction() throws IOException {
        String productId = comboBox.getSelectedItem().toString();
        String productName = productNameField.getText();
        String productDescription = productDescriptionField.getText();
        String productPrice = productPriceField.getText();
        String quantityAvaliable = quantityAvaliableField.getText();
        String barCode = barCodeField.getText();
        String reorderThreshold = reorderThresholdField.getText();
        String orderQuantity=orderQuantityField.getText();

        if ((productId.length() == 0) || (productName.length() == 0)
        		|| (productDescription.length() == 0)|| (productDescription.length() == 0)
        		|| (productPrice.length() == 0)|| (quantityAvaliable.length() == 0)
        		|| (reorderThreshold.length() == 0)|| (orderQuantity.length() == 0)) {
            return false;
        }


		try {
			
//			manager.addProduct(productId,productName,productDescription,
//					Integer.parseInt(quantityAvaliable),Float.parseFloat(productPrice),Integer.parseInt(barCode),
//					Integer.parseInt(reorderThreshold),Integer.parseInt(orderQuantity));
			boolean valid=manager.getProductManager().addProduct(productId,productName,productDescription,
					Integer.parseInt(quantityAvaliable),Float.parseFloat(productPrice),Integer.parseInt(barCode),
					Integer.parseInt(reorderThreshold),Integer.parseInt(orderQuantity));
			if(valid){
				JOptionPane.showMessageDialog(null,"Addition new data is successful");
			}
			//manager.getProductManager().refresh();
//			productPanel.getTableModel().addRow(new Object[]{productId,productName,productDescription,productPrice,quantityAvaliable,
//					barCode,reorderThreshold,orderQuantity});
//			productPanel.getTableModel().addRow(new Object[]{"1","2"});
//			System.out.println("1");
			productPanel.refreshProductPanel();

		} catch (NumberFormatException | IOException e) {
						e.printStackTrace();
		}

        return true;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource()==productNameField){
	    	lblMessage.setText("");

			if(!Character.isLetter(e.getKeyChar()))
	    	{
	    	e.consume();
	    	lblMessage.setText("Only char allowed");
	    	}
		}
		if(e.getSource()==productDescriptionField){
	    	lblMessage.setText("");

			if(!Character.isLetter(e.getKeyChar()))
	    	{
	    	e.consume();
	    	lblMessage.setText("Only char allowed");
	    	}
		}
		if(e.getSource()==quantityAvaliableField){
	    	lblMessage.setText("");

            int keyChar = e.getKeyChar();                 
            if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                  
            }else{  
                e.consume(); //关键，屏蔽掉非法输入 
    	    	lblMessage.setText("Only number allowed");
            }  
		}
		if(e.getSource()==productPriceField){
	    	lblMessage.setText("");

            int keyChar = e.getKeyChar();                 
            if((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9) ||(keyChar==KeyEvent.VK_PERIOD)){  
                  
            }else{  
                e.consume(); //关键，屏蔽掉非法输入  
    	    	lblMessage.setText("Only char allowed");

            }  
		}
		if(e.getSource()==barCodeField){
	    	lblMessage.setText("");

            int keyChar = e.getKeyChar();                 
            if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                  
            }else{  
                e.consume(); //关键，屏蔽掉非法输入  
    	    	lblMessage.setText("Only char allowed");

            }  
		}
		if(e.getSource()==reorderThresholdField){
	    	lblMessage.setText("");

            int keyChar = e.getKeyChar();                 
            if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                  
            }else{  
                e.consume(); //关键，屏蔽掉非法输入  
    	    	lblMessage.setText("Only char allowed");

            }  
		}
		if(e.getSource()==orderQuantityField){
	    	lblMessage.setText("");

            int keyChar = e.getKeyChar();                 
            if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9){  
                  
            }else{  
                e.consume(); //关键，屏蔽掉非法输入
    	    	lblMessage.setText("Only char allowed");

            }
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
        
	
	
} 
