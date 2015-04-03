package sg.edu.nus.iss.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Member;
import sg.edu.nus.iss.models.PurchaseOrderItem;
import sg.edu.nus.iss.service.BillingManager;
import sg.edu.nus.iss.service.CustomerManager;
import sg.edu.nus.iss.service.DiscountManager;

import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

import java.awt.CardLayout;
import java.awt.Font;

import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BillingPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private StoreApplication manager;
	private DefaultTableModel tableModel1;
	private BillingManager billingManager;
    private CustomerManager customerManager;
    private ArrayList<PurchaseOrderItem> itemsList;
	
	private JLabel memLabel;
	private JTextField memTextField;
	private JLabel memberLabel;
	private final JLabel lblNewLabel = new JLabel("DEFAULT");
	private final JLabel lblProductBarCode = new JLabel("Product Bar Code:");
	private final JLabel lbl_PresentProductBarCode = new JLabel("New label");
	private final JButton btnAdd = new JButton("Add");
	private final JLabel totalDiscountTxt = new JLabel("Total Discount Pric: $");
	private final JButton btnDelete = new JButton("Delete");
	private final JTable table = new JTable();
	private final JLabel lbl_ProductId = new JLabel("Product Id");
	private final JLabel lbl_ProductId2 = new JLabel("Products");
	private final JLabel lbl_ProductId3 = new JLabel("Products");
	private final JLabel lbl_ProductDiscount = new JLabel("Discount");
	private final JLabel lbl_ProductQuality = new JLabel("Quality");
	private final JLabel lbl_ProductPrice = new JLabel("Price");
	private final JLabel lbl_TotalPrice = new JLabel("Total Price: $");
	private final JLabel lbl_DisplayTotalPrice = new JLabel("Price xxx");
	private final JTextField textField = new JTextField();
	private final JLabel lblNewLabel_2 = new JLabel("Payment Method");
	private String paymentMathods[];
	private final JButton btnBack_1 = new JButton("BACK");
	private final JTextField textField_1 = new JTextField();
	private JTextField txtCustomerField;
	private JTextField txtBarCode;
	private JTextField txtDiscount;
	private DefaultTableModel tableModel;
	private JTextField txtQuantity;
	private ReceiptPanel receiptPanel;
	private JTextField txtTotalPaid;
	private JScrollPane scrollPane;
	private JTable billingTable;
	private JTextField txtRedeemPoint;
	private JRadioButton rdbtnMember;
	private JRadioButton rdbtnNonmember;
	private JLabel lblOriginalLoyaltypoint,lblMember;
	private JPanel panel;
	private ButtonGroup group;
	private JLabel lblClock;
	private JLabel lblChange_1;
	private DiscountManager discountManager;
	private PurchaseOrderItem pItem;
	double totalPriceofPurchasedItems=0.0;
	double totalDiscountedPrice=0.0;
	String discountPercentage=null;
	protected String memberID;
	public BillingPanel(final BillingManager billingManager) throws IOException{
		this.billingManager = billingManager;
		
		itemsList=new ArrayList<PurchaseOrderItem>();
		memLabel = new JLabel("Customer:");
		memTextField = new JTextField("eg:F42563743156");
		memberLabel = new JLabel("Member:");
		textField_1.setColumns(10);		
        billingTable = new JTable();
        scrollPane = new JScrollPane(billingTable);
	    tableModel=new DefaultTableModel(0,2);
	    discountManager=new DiscountManager();
	    customerManager=new CustomerManager();

        setLayout (new BorderLayout());
    	JLabel lblCheckOut = new JLabel("CHECK OUT!");
    	lblCheckOut.setFont(new Font("Lucida Grande", Font.BOLD, 20));
    	lblCheckOut.setHorizontalAlignment(SwingConstants.CENTER);
    	add("North",lblCheckOut);
    	
    	panel = new JPanel();
    	add(panel, BorderLayout.CENTER);
    	panel.setLayout(new MigLayout("", "[103.00][90.00][109.00][71.00][506.00][113.00][94.00][65.00][][-14.00][27.00]", "[20.00][][][21.00][21.00][13.00][28.00][65.00][24.00][28.00][30.00][30.00][][]"));
    	
    	rdbtnMember = new JRadioButton("Member");
    	rdbtnMember.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			txtCustomerField.setEditable(true);
    			txtCustomerField.setVisible(true);
    			lblOriginalLoyaltypoint.setText("");
    			txtCustomerField.setText("");
    		}
       	});
    	panel.add(rdbtnMember, "cell 0 0");
    	
    	rdbtnNonmember = new JRadioButton("Nonmember");
    	rdbtnNonmember.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			lblOriginalLoyaltypoint.setText("0");
    			txtCustomerField.setText("Public");
    			txtCustomerField.setEditable(false);
    		}
    	});

    	panel.add(rdbtnNonmember, "cell 1 0");
    	
    	group = new ButtonGroup();
    	group.add(rdbtnMember);
    	group.add(rdbtnNonmember);
    	
    	
    	lblClock = new JLabel();
    	billingManager.clock(lblClock);
    	panel.add(lblClock, "cell 7 0");
    	
    	lblMember = new JLabel("Customer:");
    	
    	panel.add(lblMember, "cell 0 1,alignx left,aligny center");
    	
    	txtCustomerField = new JTextField();
    	
    	txtCustomerField.setVisible(false);
    	
    	txtCustomerField.addKeyListener(new KeyAdapter()

	    {

	      public void keyPressed(KeyEvent e)

	      {

	        if (e.getKeyCode() == KeyEvent.VK_ENTER)

	        {
	        	  memberID=txtCustomerField.getText();
	          System.out.println("ENTER key pressed memberID:"+memberID);
	          
	          
			
				String loyaltyPoint=customerManager.getLoyaltyPointForMember(memberID);
				lblOriginalLoyaltypoint.setText(loyaltyPoint);
				//discountPercentage = discountManager.getDiscountApplicable(memberID);
				try {
					customerManager.updateLoyaltyPoint(memberID, 100);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtDiscount.setText(discountPercentage);
				
			
	        

	        }

	      }

	    });
    	panel.add(txtCustomerField, "cell 1 1");
    	txtCustomerField.setColumns(10);
    	
    	JLabel lblLoyaltypoint = new JLabel("LoyaltyPoint:");
    	panel.add(lblLoyaltypoint, "cell 2 1");
    	
    	lblOriginalLoyaltypoint = new JLabel();
    	panel.add(lblOriginalLoyaltypoint, "cell 3 1");

    	
    	final DefaultComboBoxModel fieldsName = new DefaultComboBoxModel();
    	fieldsName.addElement("Cash");
    	fieldsName.addElement("LoyaltyPoint");
    	fieldsName.addElement("Both");
    	
    	JScrollPane scrollPane = new JScrollPane();
    	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    	panel.add(createTablePanel(), "cell 0 7 9 2,grow");
    	

    	
    	JLabel lblProductBarCode_1 = new JLabel("Product Bar Code:");
    	panel.add(lblProductBarCode_1, "cell 0 4,alignx left");
    	
    	txtBarCode = new JTextField();
    	txtBarCode.setText("Bar Code");
    	panel.add(txtBarCode, "cell 1 4");
    	txtBarCode.setColumns(10);
    	
    	JLabel lblProductQuantity = new JLabel("Product Quantity");
    	panel.add(lblProductQuantity, "cell 2 4,alignx trailing");
    	
    	txtQuantity = new JTextField();
    	txtQuantity.setText("");
    	panel.add(txtQuantity, "cell 3 4,growx");
    	txtQuantity.setColumns(10);
    	
    	JLabel lblProductInformation = new JLabel("Product Information:");
    	panel.add(lblProductInformation, "cell 0 5");
    	
    	JLabel lblNewLabel_3 = new JLabel("Product info");
    	panel.add(lblNewLabel_3, "cell 1 5");
    	
    	JButton btnAdd_1 = new JButton("Add");
    	
    	
    	
    	btnAdd_1.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
               
				String barcode=txtBarCode.getText().toString();
				String quantity=txtQuantity.getText().toString();
				System.out.println("barcode"+barcode+"quantity"+quantity);
//			String memberID=txtMemberId.getText().toString();
//				System.out.println("barcode:"+barcode+"quantity:"+quantity+"memberID:"+memberID);
			 pItem=billingManager.addProductItemsToCart(barcode,quantity);
			System.out.println("pItem::**********:::::"+pItem.getNameOfProduct());
			itemsList.add(pItem);
			System.out.println("itemsList:"+itemsList.size());
			createTableForCart(itemsList);
				totalPriceofPurchasedItems=billingManager.calculateTotalPrice(itemsList);
			
			lbl_DisplayTotalPrice.setText(Double.toString(totalPriceofPurchasedItems));
			totalDiscountedPrice=billingManager.calculateTotalDiscountedPrice(totalPriceofPurchasedItems,discountPercentage);
			totalDiscountTxt.setText(Double.toString(totalDiscountedPrice));

            }
        });
    	
    	
    	
    	panel.add(btnAdd_1, "cell 0 6,alignx left");
	    tableModel1=new DefaultTableModel(0,2);
    	
    	
    	panel.add(lbl_TotalPrice, "cell 0 9,alignx left");
    	
    	
    	panel.add(lbl_DisplayTotalPrice, "cell 1 9");
    	
    	JLabel lblPaymentMethods = new JLabel("Payment Methods:");
    	panel.add(lblPaymentMethods, "cell 5 9,alignx left");
    	JComboBox comboBox = new JComboBox(fieldsName);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox comboBox = (JComboBox) event.getSource();

                Object selected = comboBox.getSelectedItem();
                if(selected.toString().equals("Cash")){
                	txtRedeemPoint.setText("0");
                	txtTotalPaid.setText("");
                	lblChange_1.setText("");}
                else if(selected.toString().equals("LoyaltyPoint"))
                    {txtRedeemPoint.setText("");
                    txtTotalPaid.setText("0");
                    lblChange_1.setText("0");}
                else if(selected.toString().equals("Both"))
                {txtRedeemPoint.setText("");
                txtTotalPaid.setText("");
                lblChange_1.setText("");}

            }
        });
    	panel.add(comboBox, "cell 6 9,growx");
    	
    	JLabel lblDiscount = new JLabel("Discount:");
    	panel.add(lblDiscount, "cell 0 10,alignx left");
    	
    	txtDiscount = new JTextField();
    	txtDiscount.setText("Discount %");
    	panel.add(txtDiscount, "cell 1 10");
    	txtDiscount.setColumns(10);
    	
    	JLabel lblRedeemPoint = new JLabel("Redeem Point:");
    	panel.add(lblRedeemPoint, "cell 5 10,alignx left");
    	
    	txtRedeemPoint = new JTextField();
    	panel.add(txtRedeemPoint, "cell 6 10,alignx left");
    	txtRedeemPoint.setColumns(10);
    	
    	JButton btnDelete_1 = new JButton("Delete");
    	panel.add(btnDelete_1, "cell 1 6,alignx left");
    	    	
    	    	JButton btnBack = new JButton("Back");
    	    	btnBack.addActionListener(new ActionListener(){
    	    		public void actionPerformed(ActionEvent e){
    	    			invisiable();
    	    		}
    	    	});
    	    	
    	    	JLabel lblTotalDiscountPrice = new JLabel("Total Discount Price:");
    	    	panel.add(lblTotalDiscountPrice, "cell 0 11");
    	    	
    	    	JLabel lblNewLabel_4 = new JLabel("Total Discount Price");
    	    	panel.add(totalDiscountTxt, "cell 1 11");
    	    	
    	    	JLabel lblTotalPaid = new JLabel("Total Paid:");
    	    	panel.add(lblTotalPaid, "cell 5 11,alignx left");
    	    	
    	    	txtTotalPaid = new JTextField();
    	    	panel.add(txtTotalPaid, "cell 6 11,alignx left");
    	    	txtTotalPaid.setColumns(10);
    	    	
    	    	JLabel lblChange = new JLabel("Change:");
    	    	panel.add(lblChange, "cell 7 11");
    	    	
    	    	lblChange_1 = new JLabel();
    	    	panel.add(lblChange_1, "cell 8 11");
    	    	panel.add(btnBack, "cell 0 12");
    	    	
    	    	JButton btnClear = new JButton("Clear");
    	    	panel.add(btnClear, "cell 6 12");
    	    	
    	    	JButton btnNext = new JButton("Confirm");
    	    	
    	    	    	btnNext.addActionListener(new ActionListener() {
    	    	    		public void actionPerformed(ActionEvent e) {
    	    	    			receiptPanel = new ReceiptPanel();
    	    	    			receiptPanel.setVisible(true);
    	    	    		}
    	    	    	});
    	    	    	panel.add(btnNext, "cell 8 12,aligny top");
 
	}
	public void invisiable(){
		this.setVisible(false);
	}
private Component createTablePanel() {
	    DefaultTableModel tableModel=new DefaultTableModel(0,2);
	    String[] columns = {"Serial","ProductId", "Product Name","Quantity"," Price per Item","	Total Price"};
	        tableModel.setColumnIdentifiers(columns);
	        billingTable.setModel(tableModel);
	        tableModel.setColumnIdentifiers(columns);
	        return scrollPane;
	}
	//	private JScrollPane createTablePanel() {
//	    DefaultTableModel tableModel=new DefaultTableModel(0,2);
//	}

protected void createTableForCart(ArrayList<PurchaseOrderItem> itemsList) {
	 DefaultTableModel tableModel=new DefaultTableModel(0,2);
	 
		String[] columns = {"Serial","ProductId", "Product Name","Quantity"," Price per Item","	Total Price"};
	        tableModel.setColumnIdentifiers(columns);
	        billingTable.setModel(tableModel);
	        tableModel.setColumnIdentifiers(columns);
	        int index=1;
	        
	        Iterator<PurchaseOrderItem> iterator = itemsList.iterator();
			while (iterator.hasNext()) {
				PurchaseOrderItem p=iterator.next();
				Double totalPriceItems=(new Double(p.getPurchasedQuantity())) *(new Double(p.getItemPrice()));
				String line=index+","+p.getProductID()+","+p.getNameOfProduct()+","+p.getPurchasedQuantity()+","+p.getItemPrice()+","+totalPriceItems.toString(); 
			System.out.println(line);
				
					tableModel.addRow(line.split(",")); 
					index++;
					}
			scrollPane.setViewportView(billingTable);
	       
}
	private Component createFormPanel() {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout(0, 0));
		return p;
	}

	
	protected void dispose() {
		this.setVisible(false);		
	}
	public JLabel getLblOriginalLoyaltypoint(){
		return lblOriginalLoyaltypoint;
	}
}
