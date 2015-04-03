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

	private CategoryManager categoryManager;
	private JTextField categoryCodeField;
	private JTextField categoryNameField;
	Category  category;
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

				category=new Category();
				category.setCategoryCode(categoryCode);
				category.setCategoryName(categoryName);
				boolean valid = false;
				try {
					valid = categoryManager.addNewCategory(category);
					if(valid){
						JOptionPane.showMessageDialog(null,"Addition new data is successful");
					}


					categoryManager.refresh();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}





			}
		}
		return true;


	}
}
