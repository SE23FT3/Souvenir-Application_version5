package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.service.VendorManager;
import sg.edu.nus.iss.util.OkCancelDialog;

public class AddVendorDialog extends OkCancelDialog {
	private StoreApplication manager;
	private VendorPanel vendorPanel;
	private JTextField vendorNameField;
	private JTextField vendorDescriptionField;



	public AddVendorDialog(VendorPanel vendorPanel, StoreApplication manager) {
		super(null,"Add Vendor");
		this.vendorPanel = vendorPanel;
		this.manager = manager;
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,2));
		p.add(new JLabel("Vendor Name"));
		vendorNameField = new JTextField(20);
		p.add(vendorNameField);
		p.add(new JLabel("Vendor Description"));
		vendorDescriptionField = new JTextField(20);
		p.add(vendorDescriptionField);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String vendorName = vendorNameField.getText();
		String vendorDescription = vendorDescriptionField.getText();
		
		if((vendorName.length()==0)||(vendorDescription.length()==0)){
			return false;
		}
		try {
			
//			manager.addProduct(productId,productName,productDescription,
//					Integer.parseInt(quantityAvaliable),Float.parseFloat(productPrice),Integer.parseInt(barCode),
//					Integer.parseInt(reorderThreshold),Integer.parseInt(orderQuantity));
			boolean valid=manager.getVendorManager().addVendor(vendorName,vendorDescription);
			if(valid){
				JOptionPane.showMessageDialog(null,"Addition new data is successful");
			}
			vendorPanel.refreshVendorPanel();
		} catch (NumberFormatException | IOException e) {
						e.printStackTrace();
		}

        return true;
	}

}
