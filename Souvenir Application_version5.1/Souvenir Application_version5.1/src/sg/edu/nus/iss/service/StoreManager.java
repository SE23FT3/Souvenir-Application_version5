package sg.edu.nus.iss.service;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import sg.edu.nus.iss.gui.MainMenu;
import sg.edu.nus.iss.gui.LoginPanel;

public class StoreManager {
	
	private LoginPanel mainPanel;
	private MainMenu mainMenu;



	public void start() throws IOException {
		
		
		mainPanel = new LoginPanel(this);

		mainPanel.setLocationRelativeTo(null);
		mainPanel.pack();
		int width=mainPanel.getWidth();
		int height=mainPanel.getHeight();
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
		int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
		mainPanel.setLocation(w, h);
		mainPanel.setVisible (true);	
	}
	
    public void shutdown () {
        System.exit(0);
    }
    
	public void closeStoreWindow(){
		mainPanel.setVisible(false);
		mainPanel.dispose();
	}
	
	public MainMenu getMainWindow() {
		return mainMenu;
	}
	

}
