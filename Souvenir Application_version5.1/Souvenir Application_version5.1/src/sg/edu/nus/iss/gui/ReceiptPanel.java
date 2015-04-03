package sg.edu.nus.iss.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;

public class ReceiptPanel extends JFrame{
	public ReceiptPanel() {
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new MigLayout("", "[173.00][186.00]", "[][216.00][101.00]"));
		
		JLabel lblNewLabel = new JLabel("StoreName");
		panel.add(lblNewLabel, "cell 1 0,alignx center");
		
	}


}

