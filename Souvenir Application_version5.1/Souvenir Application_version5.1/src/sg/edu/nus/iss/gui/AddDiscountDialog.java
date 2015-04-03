package sg.edu.nus.iss.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Discount;
import sg.edu.nus.iss.service.CustomerManager;
import sg.edu.nus.iss.service.DiscountManager;
import sg.edu.nus.iss.util.OkCancelDialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AddDiscountDialog extends OkCancelDialog {
	private StoreApplication manager;
	private JTextField discountCodeField;
	private JTextField discountNameField;
	private JTextField discountStartDateField;
	private JTextField durationOfDiscountField;
	private JTextField discountPercentageField;
	private JComboBox  discountApplicableCombo;
	private JRadioButton alwaysStartDate;
	private JRadioButton chooseStartDate;
	private JRadioButton rdbtnNonmember;
	private JDatePickerImpl datePicker;
	DiscountManager discountManager;
	Discount discount;
	
	
	
	
	public AddDiscountDialog(DiscountManager discountManager) throws IOException {
		super ( null, "Add Discount");
        this.discountManager = discountManager;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createLineBorder(Color.black));
		 final DefaultComboBoxModel fieldsName = new DefaultComboBoxModel();
		 fieldsName.addElement("All");
	  	fieldsName.addElement("Member");
		GridBagLayout gbl_p = new GridBagLayout();
		gbl_p.columnWidths = new int[]{224, 0, 224, 0};
		gbl_p.rowHeights = new int[]{39, 39, 39, 39, 39, 39, 0};
		gbl_p.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_p.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		p.setLayout(gbl_p);
		JPanel p2=new JPanel();
		JRadioButton alwaysDiscountPct=new JRadioButton("Always");
		
		
		p2.add(alwaysDiscountPct);
		durationOfDiscountField = new JTextField(6);
		durationOfDiscountField.setVisible(false);
		JRadioButton selectDiscountPct=new JRadioButton("Select Duration");
		selectDiscountPct.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			
    			durationOfDiscountField.setVisible(true);
    			pack();
    			
    		}
       	});
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel label = new JLabel("Discount Code");
		p.add(label, gbc);
		discountCodeField = new JTextField(20);
		discountCodeField.setText("");
		GridBagConstraints gbc_discountCodeField = new GridBagConstraints();
		gbc_discountCodeField.fill = GridBagConstraints.BOTH;
		gbc_discountCodeField.insets = new Insets(0, 0, 5, 0);
		gbc_discountCodeField.gridx = 2;
		gbc_discountCodeField.gridy = 0;
		p.add(discountCodeField, gbc_discountCodeField);
		JPanel p1=new JPanel();
		discountStartDateField=new   JTextField("YYYY-MM-DD");
		discountStartDateField.setVisible(false);
		JRadioButton selectStartDate=new JRadioButton("Select Date");
		selectStartDate.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			
    			discountStartDateField.setVisible(true);
    			pack();
    			
    		}
       	});
		GridBagConstraints gbc_1 = new GridBagConstraints();
		gbc_1.fill = GridBagConstraints.BOTH;
		gbc_1.insets = new Insets(0, 0, 5, 5);
		gbc_1.gridx = 0;
		gbc_1.gridy = 1;
		JLabel label_5 = new JLabel("Discount Description");
		p.add(label_5, gbc_1);
		discountNameField = new JTextField(20);
		discountNameField.setText("");
		GridBagConstraints gbc_discountNameField = new GridBagConstraints();
		gbc_discountNameField.fill = GridBagConstraints.BOTH;
		gbc_discountNameField.insets = new Insets(0, 0, 5, 0);
		gbc_discountNameField.gridx = 2;
		gbc_discountNameField.gridy = 1;
		p.add(discountNameField, gbc_discountNameField);
		GridBagConstraints gbc_2 = new GridBagConstraints();
		gbc_2.fill = GridBagConstraints.BOTH;
		gbc_2.insets = new Insets(0, 0, 5, 5);
		gbc_2.gridx = 0;
		gbc_2.gridy = 2;
		JLabel label_4 = new JLabel("Discount Starting Dates");
		p.add(label_4, gbc_2);
		alwaysStartDate=new JRadioButton("Always");
		p1.add(alwaysStartDate);
		p1.add(selectStartDate);
		p1.add(discountStartDateField);
		GridBagConstraints gbc_p1 = new GridBagConstraints();
		gbc_p1.fill = GridBagConstraints.BOTH;
		gbc_p1.insets = new Insets(0, 0, 5, 0);
		gbc_p1.gridx = 2;
		gbc_p1.gridy = 2;
		p.add(p1, gbc_p1);
		
		GridBagConstraints gbc_3 = new GridBagConstraints();
		gbc_3.fill = GridBagConstraints.BOTH;
		gbc_3.insets = new Insets(0, 0, 5, 5);
		gbc_3.gridx = 0;
		gbc_3.gridy = 3;
		JLabel label_1 = new JLabel("Discount Duration");
		p.add(label_1, gbc_3);
		p2.add(selectDiscountPct);
		p2.add(durationOfDiscountField);
		GridBagConstraints gbc_p2 = new GridBagConstraints();
		gbc_p2.fill = GridBagConstraints.BOTH;
		gbc_p2.insets = new Insets(0, 0, 5, 0);
		gbc_p2.gridx = 2;
		gbc_p2.gridy = 3;
		p.add(p2, gbc_p2);
		//durationOfDiscountField = new JTextField(20);
		//p.add(durationOfDiscountField);
		GridBagConstraints gbc_4 = new GridBagConstraints();
		gbc_4.fill = GridBagConstraints.BOTH;
		gbc_4.insets = new Insets(0, 0, 5, 5);
		gbc_4.gridx = 0;
		gbc_4.gridy = 4;
		JLabel label_2 = new JLabel("Discount Percentage");
		p.add(label_2, gbc_4);
		discountPercentageField = new JTextField(20);
		GridBagConstraints gbc_discountPercentageField = new GridBagConstraints();
		gbc_discountPercentageField.fill = GridBagConstraints.BOTH;
		gbc_discountPercentageField.insets = new Insets(0, 0, 5, 0);
		gbc_discountPercentageField.gridx = 2;
		gbc_discountPercentageField.gridy = 4;
		p.add(discountPercentageField, gbc_discountPercentageField);
		GridBagConstraints gbc_5 = new GridBagConstraints();
		gbc_5.fill = GridBagConstraints.BOTH;
		gbc_5.insets = new Insets(0, 0, 0, 5);
		gbc_5.gridx = 0;
		gbc_5.gridy = 5;
		JLabel label_3 = new JLabel("Discount Applicable");
		p.add(label_3, gbc_5);
		discountApplicableCombo = new JComboBox(fieldsName);
		//discountApplicableField = new JTextField(20);
		GridBagConstraints gbc_discountApplicableCombo = new GridBagConstraints();
		gbc_discountApplicableCombo.fill = GridBagConstraints.BOTH;
		gbc_discountApplicableCombo.gridx = 2;
		gbc_discountApplicableCombo.gridy = 5;
		p.add(discountApplicableCombo, gbc_discountApplicableCombo);
		/*discountCodeField.addKeyListener(new KeyAdapter()

	    {

	      public void keyPressed(KeyEvent e)

	      {

	        if (e.getKeyCode() == KeyEvent.VK_ENTER)

	        {
		if((null ==discountCodeField) || discountCodeField.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Fields are empty!");
		}
	        }
	      
	    }
	 });*/
		return p;
	}

	@Override
	protected boolean performOkAction() {
		boolean valid=false;
		  String discountCode = discountCodeField.getText();
	        String discountDescription = discountNameField.getText();
	        String discountStartDate =  discountStartDateField.getText();
//                    (discountStartDateCombo.getSelectedIndex());
//	        if(!discountStartDate.equalsIgnoreCase("ALways"))
//		       {
//		    	   System.out.println("Pop up a date::");
//		       }
//		       
	        String durationOfDiscount = durationOfDiscountField.getText();
	        String discountPercentage = discountPercentageField.getText();
	        String discountAppliedTo = (String) discountApplicableCombo.getItemAt
                    (discountApplicableCombo.getSelectedIndex());
	        String discountApplicable=null;
	       if(discountAppliedTo.equalsIgnoreCase("All"))
	       {
	    	   discountApplicable="A";
	       }
	       else if(discountAppliedTo.equalsIgnoreCase("Member"))
	       {
	    	   discountApplicable="M";
	       }

	        if ((discountCode.length() == 0) || (discountDescription.length() == 0)
	        		|| (discountStartDate.length() == 0)|| (durationOfDiscount.length() == 0)
	        		|| (discountPercentage.length() == 0)|| (discountApplicable.length() == 0))
	        	
	            return valid;
	        
	        discount=new Discount();
	        discount.setDiscountCode(discountCode);
	        discount.setDiscountDescription(discountDescription);
	        discount.setStartDate(discountStartDate);
	        discount.setDurationOfDiscount(durationOfDiscount);
	        discount.setDiscountPercentage(discountPercentage);
	        discount.setApplicability(discountApplicable);

			try {

				 valid=discountManager.addNewDiscountData(discount);
				
				if(valid){
					JOptionPane.showMessageDialog(null,"Addition new data is successful");
				}
				discountManager.refresh();
				//new ProductPanel(manager);
			} catch (NumberFormatException | IOException e) {
							e.printStackTrace();
			}

	        return valid;
		
	}

}
