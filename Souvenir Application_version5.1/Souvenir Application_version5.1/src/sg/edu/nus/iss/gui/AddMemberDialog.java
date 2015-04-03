package sg.edu.nus.iss.gui;

import java.awt.GridLayout;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Member;
import sg.edu.nus.iss.service.CustomerManager;
import sg.edu.nus.iss.service.ProductManager;
import sg.edu.nus.iss.util.OkCancelDialog;

public class AddMemberDialog extends OkCancelDialog{

	private CustomerManager customerManager;
	private JTextField memberNameField;
	private JTextField memberIDField;
	private JTextField memberLoyaltyPointField;
    Member	member=null;
	/**
	 * Createdt by Deepsha on 24March 2015
	 */

	public AddMemberDialog(CustomerManager customerManager) {
		super ( null, "Add Member");
        this.customerManager = customerManager;
	}

	
	private static final long serialVersionUID = 1L;

	@Override
	protected JPanel createFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0,2));
		p.add(new JLabel("Member Name"));
		memberNameField = new JTextField(20);
		p.add(memberNameField);
		p.add(new JLabel("Member ID"));
		memberIDField = new JTextField(20);
		String memberIdentity=createUniqueMemebrId();
		System.out.println("memberIdentity:"+memberIdentity);
		memberIDField.setText(memberIdentity);
		memberIDField.setEditable(false);
		p.add(memberIDField);
		p.add(new JLabel("Loyalty Points"));
		memberLoyaltyPointField = new JTextField(20);
		memberLoyaltyPointField.setText("-1");
		memberIDField.setEditable(false);
		p.add(memberLoyaltyPointField);
		return p;
		
	}

	private String createUniqueMemebrId() {

		String memberIdentity;
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    final int N = alphabet.length();

	    Random r = new Random();
	    char memberAlpha = 0;
	    for (int i = 0; i < 50; i++) {
	    	 memberAlpha=alphabet.charAt(r.nextInt(N));
	        
	    }
	    int memID=(int) TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	    int memberId=memID;
	    String memA=String.valueOf(memberAlpha);
	    System.out.println("memA"+memA);
	    String memberNum=String.valueOf(memberId).substring(0, 9);
	    System.out.println("memberNum"+memberNum);
	    memberIdentity=memA+memberNum;
	    
		 return memberIdentity;
		 
	}

	@Override
	protected boolean performOkAction() throws IOException {
		 boolean valid=false;

		 String memberName = memberNameField.getText();
	        String memberID = memberIDField.getText();
	        String memberLoyaltyPoint = memberLoyaltyPointField.getText();
	        if ((memberName.length() == 0) || (memberID.length() == 0)
	        		|| (memberLoyaltyPoint.length() == 0))
	        	
				{
					JOptionPane.showMessageDialog(null,"Fields for addition of new  entry are empty.");
					return valid;
				}
		
	        else{
	        	member=new Member();
	        	member.setCustomerName(memberName);
	        	member.setMemberId(memberID);
	        	member.setLoyaltyPoint(memberLoyaltyPoint);
	       
			try {
				valid = customerManager.addNewMemberData(member);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(valid){
				JOptionPane.showMessageDialog(null,"Addition new data is successful.");
			}
			
			
			customerManager.refresh();


	        return true;
	        }
	}

}
