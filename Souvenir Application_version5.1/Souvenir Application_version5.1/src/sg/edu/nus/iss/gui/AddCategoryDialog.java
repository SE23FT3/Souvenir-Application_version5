package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.service.CategoryManager;
import sg.edu.nus.iss.util.OkCancelDialog;

public class AddCategoryDialog extends OkCancelDialog {

	private CategoryManager categoryManager;
	private JTextField categoryCodeField;
	private JTextField categoryNameField;

	public AddCategoryDialog(CategoryManager categoryManager) {
		super(null,"Add Category");
		this.categoryManager = categoryManager;
	}

	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,2));
		p.add(new JLabel("Category Code"));
		categoryCodeField = new JTextField(20);
		p.add(categoryCodeField);
		p.add(new JLabel("Category Name "));
		categoryNameField = new JTextField(20);
		p.add(categoryNameField);
		return p;
	}

	@Override
	protected boolean performOkAction() {
		String categoryCode = categoryCodeField.getText();
		String categoryName = categoryNameField.getText();
		
		if((categoryCode.length()==0)||(categoryName.length()==0)){
			return false;
		}
		try {
			
			boolean valid=categoryManager.addCategory(categoryCode,categoryName);
			
			if(valid){
				JOptionPane.showMessageDialog(null,"Addition new data is successful");
			}
			String fileName="Vendors"+categoryCode;
			File tagFile=new File("./data/",fileName+".dat");
			if(!tagFile.exists()){
			tagFile.createNewFile();
			}
			categoryManager.refresh();
		} catch (NumberFormatException | IOException e) {
						e.printStackTrace();
		}

        return true;
	}

}
