package sg.edu.nus.iss.service;

import java.io.IOException;

import sg.edu.nus.iss.gui.MainMenu;
import sg.edu.nus.iss.gui.LoginPanel;

public class StoreManager {
	
	private LoginPanel mainPanel;
	private MainMenu mainMenu;



	public void start() throws IOException {
		
		
		mainPanel = new LoginPanel(this);
		mainPanel.pack();
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
