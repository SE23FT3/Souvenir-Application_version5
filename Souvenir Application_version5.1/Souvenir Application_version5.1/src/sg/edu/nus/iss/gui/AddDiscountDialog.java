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
	String discountApplicable="A";
	
	
	
	public AddDiscountDialog(DiscountManager discountManager) {
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
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		p.setBorder(BorderFactory.createLineBorder(Color.black));
		 final DefaultComboBoxModel fieldsName = new DefaultComboBoxModel();
		 fieldsName.addElement("All");
	  		fieldsName.addElement("Member");
		
		
		p.setLayout(new GridLayout(0,2));
		p.add(new JLabel("Discount Code"));
		discountCodeField = new JTextField(20);
		discountCodeField.setText("");
		p.add(discountCodeField);
		p.add(new JLabel("Discount Description"));
		discountNameField = new JTextField(20);
		discountNameField.setText("");
		p.add(discountNameField);
		p.add(new JLabel("Discount Starting Dates"));
		alwaysStartDate=new JRadioButton("Always");
		
		alwaysStartDate.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			
    			discountStartDateField.setText("Always");
    			
    		}
       	});
		p1.add(alwaysStartDate);
		discountStartDateField=new   JTextField("YYYY-MM-DD");
		discountStartDateField.setVisible(false);
		JRadioButton selectStartDate=new JRadioButton("Select Date");
		selectStartDate.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			
    			discountStartDateField.setVisible(true);
    			
    		}
       	});
		p1.add(selectStartDate);
		p1.add(discountStartDateField);
		p.add(p1);
		JRadioButton alwaysDiscountPct=new JRadioButton("Always");
		alwaysDiscountPct.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			
    			durationOfDiscountField.setText("Always");
    			
    		}
       	});
		
		p2.add(alwaysDiscountPct);
		durationOfDiscountField = new JTextField(6);
		durationOfDiscountField.setVisible(false);
		JRadioButton selectDiscountPct=new JRadioButton("Select Duration");
		selectDiscountPct.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			
    			durationOfDiscountField.setVisible(true);
    			
    		}
       	});
		p2.add(selectDiscountPct);
		p2.add(durationOfDiscountField);
		
		p.add(new JLabel("Discount Duration"));
		p.add(p2);
		//durationOfDiscountField = new JTextField(20);
		//p.add(durationOfDiscountField);
		p.add(new JLabel("Discount Percentage"));
		discountPercentageField = new JTextField(20);
		p.add(discountPercentageField);
		p.add(new JLabel("Discount Applicable"));
		  		discountApplicableCombo = new JComboBox(fieldsName);
		//discountApplicableField = new JTextField(20);
		p.add(discountApplicableCombo);
		discountApplicableCombo.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			if (discountApplicableCombo.getSelectedIndex() != -1) {                     
                    String data = (String) discountApplicableCombo.getItemAt
                         (discountApplicableCombo.getSelectedIndex());
                    if(data.equalsIgnoreCase("All"))
         	       {
         	    	   discountApplicable="A";
         	       }
         	       else if(data.equalsIgnoreCase("Member"))
         	       {
         	    	   discountApplicable="M";
         	       }

    			}
    			
    		}
       	});
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
	       
	      
	        if ((discountCode.length() == 0) || (discountDescription.length() == 0)
	        		|| (discountStartDate.length() == 0)|| (durationOfDiscount.length() == 0)
	        		|| (discountPercentage.length() == 0)|| (discountApplicable.length() == 0))
	        {
	        	JOptionPane.showMessageDialog(null,"Fields for addition of new  entry are empty.");
	        	return valid;
	        }
	        {
	        	if(!discountPercentage.matches("[0-9]+"))
	        	{
	        		JOptionPane.showMessageDialog(null,"Discount percentage should not contain alphabets.");
		        	return valid;
	        	}
	        	else{
	        	
	            
	        
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
	}

}
