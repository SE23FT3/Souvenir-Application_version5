package sg.edu.nus.iss.service;

import java.io.IOException;

import sg.edu.nus.iss.gui.MainMenu;
import sg.edu.nus.iss.gui.MainPanel;

public class StoreManager {
	
	private MainPanel mainPanel;
	private MainMenu mainMenu;



	public void start() throws IOException {
		
		
		mainPanel = new MainPanel(this);
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
