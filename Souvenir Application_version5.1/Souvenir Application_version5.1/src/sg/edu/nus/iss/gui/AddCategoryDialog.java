package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Category;
import sg.edu.nus.iss.service.CategoryManager;
import sg.edu.nus.iss.util.OkCancelDialog;

public class AddCategoryDialog extends OkCancelDialog {

	private JTextField categoryCodeField;
	private JTextField categoryNameField;
	private StoreApplication manager;
	private CategoryPanel categoryPanel;
	public AddCategoryDialog(CategoryPanel categoryPanel,StoreApplication manager) throws IOException {
		super(null,"Add Category");
		this.categoryPanel = categoryPanel;
		this.manager = manager;
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

		if((categoryCode.length()==0)||(categoryName.length()==0))
		{
			{
				JOptionPane.showMessageDialog(null,"Fields for addition of new  entry are empty.");
				return false;
			}
		}
		else{
			if(categoryCode.matches("[0-9]+")){
				if((categoryCode.length() > 2))
					//|| (categoryCode.matches("[0-9]+")))
				{
					JOptionPane.showMessageDialog(null,"Category code should not be more than 3 alphabets.");
					return false;
				}
				JOptionPane.showMessageDialog(null,"Category code should not contain alphabets.");
				return false;
			}
			else{

				Category category=new Category();
				category.setCategoryCode(categoryCode);
				category.setCategoryName(categoryName);
				boolean valid = false;
				try {
					valid = manager.getCategoryManager().addNewCategory(category);
					if(valid){
						JOptionPane.showMessageDialog(null,"Addition new data is successful");
					}


					categoryPanel.refreshCategoryPanel();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}





			}
		}
		return true;


	}
}
