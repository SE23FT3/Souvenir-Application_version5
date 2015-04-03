
package sg.edu.nus.iss.main;

import java.io.IOException;
import java.util.List;

import javax.swing.JFrame;

import sg.edu.nus.iss.*;
import sg.edu.nus.iss.gui.MainMenu;
import sg.edu.nus.iss.gui.MainPanel;
import sg.edu.nus.iss.gui.ProductPanel;
import sg.edu.nus.iss.models.Product;
import sg.edu.nus.iss.service.ProductManager;
import sg.edu.nus.iss.service.StoreManager;

public class StoreApplication {

	public static void main(String[] args)throws IOException {
	    StoreManager manager = new StoreManager ();
        manager.start ();
	}

}
