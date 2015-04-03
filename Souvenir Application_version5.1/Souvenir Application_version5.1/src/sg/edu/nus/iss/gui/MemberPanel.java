package sg.edu.nus.iss.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import sg.edu.nus.iss.models.Discount;
import sg.edu.nus.iss.models.Member;
import sg.edu.nus.iss.service.CustomerManager;
import sg.edu.nus.iss.service.ProductManager;

public class MemberPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private StoreApplication manager;
	private JScrollPane scrollPane;
	private DefaultTableModel tableModel;
	private JTable memberTable;
	private CustomerManager customerManager;
	private ProductManager productManager;

	
	public MemberPanel(CustomerManager customerManager) throws IOException{
		this.customerManager = customerManager;
		memberTable = new JTable();
        scrollPane = new JScrollPane(memberTable);
	    tableModel=new DefaultTableModel(0,2);
	    memberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setLayout (new BorderLayout());
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
		  		fieldsName.addElement("Member Name");
		  		fieldsName.addElement("Member Id");
		  
		  		
		 
				// Create the combo box, and set 2nd item as Default
		  		searchCombo = new JComboBox(fieldsName);
			    SpinnerModel model = new SpinnerNumberModel(0, 0, 1000, 1);
			    
			    JLabel spacer, bridgeSet, numOfBars;
			    JSpinner deckElevation = new JSpinner(new SpinnerNumberModel(24, 0, 24, 4));
			    JSpinner archHeight = new JSpinner(new SpinnerNumberModel(4, 4, 24, 4));
			    JSpinner pierHeight = new JSpinner(new SpinnerNumberModel(0, 0, 24, 4));
			    panel.add(bridgeSet = new JLabel("Search Your Offers And Discount :"), "wrap");
			  
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
		                    	 ArrayList<Member> memberList=customerManager.searchDataAndDisplay(data, value);
		                    	 if(memberList.size()!=0){
		                    	        	  addComponeneTable(memberList);
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
		        		
		        		ArrayList newMemberList=null;
						try {
							newMemberList = customerManager.retrieveMemberDataFromFile();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}						
							 addComponeneTable(newMemberList);
		        	}
		        });
		        
		        panel.add(resetButton);
				return panel;
	
	}
	
	void addComponeneTable(List<Member> memberList) {

		System.out.println("******************************* addComponeneTable  **********");
	    DefaultTableModel tableModel=new DefaultTableModel(0,2);
	    String[] columns = {"Member Name", "Member ID"," Loyaly Points"};
	        tableModel.setColumnIdentifiers(columns);
	        memberTable.setModel(tableModel);
	      
	        Iterator<Member> iterator = memberList.iterator();
			while (iterator.hasNext()) {
				Member m=iterator.next();
				String lp=""+m.getLoyaltyPoint();
				String line=m.getCustomerName()+","+m.getMemberId()+","+lp; 
			System.out.println(line);
				
					tableModel.addRow(line.split(",")); 
					}
	
	
		scrollPane.setViewportView(memberTable);

		//return scrollPane;
	
	}
	private JPanel createButtonPanel() {
        JPanel p = new JPanel (new GridLayout (0, 1));

        JButton addButton = new JButton ("Add");
        addButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                AddMemberDialog d;
				try {
					d = new AddMemberDialog (customerManager);
					d.pack();
					d.setVisible (true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


            }
        });
        JButton deleteButton = new JButton ("Delete");
        deleteButton.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
              int i= memberTable.getSelectedRow();
            String memberId=(String)memberTable.getModel().getValueAt(i, 1);
               System.out.println("Deleted:::"+i+"  memberId:"+memberId);
               ArrayList newdiscountList=null;
               ArrayList memberList = null;
			try {
				memberList = customerManager.retrieveMemberDataFromFile();
				 System.out.println("memberList before deleteion:::"+memberList.size());
				Member removeMember=new Member();
				 Iterator<Member> iterator = memberList.iterator();
					while (iterator.hasNext()) {							
						Member member=iterator.next();
						if(member.getMemberId().equalsIgnoreCase(memberId))
						{
							
							removeMember=member;
							memberList.remove(removeMember);
							break;
						}
						
					}
					 System.out.println("memberList afteer deleteion:::"+memberList.size());
					System.out.println("member name delete::"+removeMember.getCustomerName());
					boolean valid=customerManager.writeBackToFile(memberList);
					if(valid)
					{
					System.out.println("newdiscountList::"+memberList.size());
				// System.out.println("Deleted: dscount code::::::::"+d.getDiscountCode()+d.getApplicability());
				 
				 addComponeneTable(memberList);
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
       
        p.add (addButton);
        p.add (deleteButton);
        JPanel bp = new JPanel();
        bp.setLayout(new BorderLayout());
        bp.add("North",p);
        
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				//menu.setVisible(true);
				invisiable();

        	}
        });
        
        JButton btnDelete = new JButton("Delete");
        
        p.add(btnBack);
        return bp;
	}
	public void invisiable() {
		this.setVisible(false);
	}

	private JScrollPane createTablePanel() throws IOException {
		    DefaultTableModel tableModel=new DefaultTableModel(0,2);
			String[] columns = {"Member Name", "Member ID"," Loyaly Points"};
		        tableModel.setColumnIdentifiers(columns);
		        memberTable.setModel(tableModel);
		        ArrayList memberList=customerManager.retrieveMemberDataFromFile();
		        Iterator<Member> iterator = memberList.iterator();
				while (iterator.hasNext()) {
					Member m=iterator.next();
					String lp=""+m.getLoyaltyPoint();
					String line=m.getCustomerName()+","+m.getMemberId()+","+lp;
				
					
						tableModel.addRow(line.split(",")); 
						}
			scrollPane.setViewportView(memberTable);
	
			return scrollPane;
		}


}
