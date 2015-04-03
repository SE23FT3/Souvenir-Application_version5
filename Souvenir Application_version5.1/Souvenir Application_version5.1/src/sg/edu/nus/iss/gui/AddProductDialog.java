 package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.service.ProductManager;
import sg.edu.nus.iss.util.OkCancelDialog;

public class AddProductDialog extends OkCancelDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5641760134521542673L;
	private ProductManager productManager;
	private JTextField productIdField;
	private JTextField productNameField;
	private JTextField productDescriptionField;
	private JTextField quantityAvaliableField;
	private JTextField productPriceField;
	private JTextField barCodeField;
	private JTextField reorderThresholdField;
	private JTextField orderQuantityField;
	
	public AddProductDialog(ProductManager productManager) throws IOException{
		
        super ( null, "Add Product");
        this.productManager= productManager;
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,2));
		p.add(new JLabel("CategoryCode"));
		productIdField = new JTextField(20);
		p.add(productIdField);
		p.add(new JLabel("Product Name"));
		productNameField = new JTextField(20);
		p.add(productNameField);
		p.add(new JLabel("Product Description"));
		productDescriptionField = new JTextField(20);
		p.add(productDescriptionField);
		p.add(new JLabel("Product Quantity"));
		quantityAvaliableField = new JTextField(20);
		p.add(quantityAvaliableField);
		p.add(new JLabel("Product Price"));
		productPriceField = new JTextField(20);
		p.add(productPriceField);
		p.add(new JLabel("Bar Code"));
		barCodeField = new JTextField(20);
		p.add(barCodeField);
		p.add(new JLabel("Reorder Threshold"));
		reorderThresholdField = new JTextField(20);
		p.add(reorderThresholdField);
		p.add(new JLabel("Order Quantity"));
		orderQuantityField = new JTextField(20);
		p.add(orderQuantityField);
		return p;
	}

	@Override
	protected boolean performOkAction() throws IOException {
        String productId = productIdField.getText();
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
			boolean valid=productManager.addProduct(productId,productName,productDescription,
					Integer.parseInt(quantityAvaliable),Float.parseFloat(productPrice),Integer.parseInt(barCode),
					Integer.parseInt(reorderThreshold),Integer.parseInt(orderQuantity));
			if(valid){
				JOptionPane.showMessageDialog(null,"Addition new data is successful");
			}
			productManager.refresh();

		} catch (NumberFormatException | IOException e) {
						e.printStackTrace();
		}

        return true;
	}
        
	
	
} 
