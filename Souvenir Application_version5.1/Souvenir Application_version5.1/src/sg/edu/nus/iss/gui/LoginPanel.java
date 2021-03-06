package sg.edu.nus.iss.gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;

import sg.edu.nus.iss.main.StoreApplication;
import sg.edu.nus.iss.models.Product;
import sg.edu.nus.iss.service.LoginManager;
import sg.edu.nus.iss.util.Constants;
import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JDesktopPane;

public class LoginPanel extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField usernamefield, oldpassword, newpassword;
	private JPasswordField passwordfield;
	private JPanel panel;
	private JLabel lblTitle, lblUserName, lblPassword;
	private JButton btnLogin, btnChangePassword;
	private MainMenu mainMenu;
	private JFrame changePasswordFrame;
	private JTextField usernameField;
	private StoreApplication manager;

	public LoginPanel(final StoreApplication manager) {
		this.manager = manager;
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(new MigLayout(
				"",
				"[172.00px,grow][32.00px][128.00][170.00][118.00][152.00,grow][][][]",
				"[40.00][grow][40.00px][33.00][][][63.00][16.00][][]"));

		lblTitle = new JLabel("SOUVENIR APPLICATION");
		lblTitle.setFont(new Font("Verdana", Font.BOLD, 26));
		panel.add(lblTitle, "cell 1 0 4 1,alignx center,grow");

		lblUserName = new JLabel("USER NAME");
		lblUserName.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.add(lblUserName, "cell 2 3");

		usernamefield = new JTextField();
		panel.add(usernamefield, "cell 3 3,growx");
		usernamefield.setColumns(10);

		lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel.add(lblPassword, "cell 2 5");

		passwordfield = new JPasswordField();
		panel.add(passwordfield, "cell 3 5,growx");

		btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = usernamefield.getText();
				@SuppressWarnings("deprecation")
				String pwd = passwordfield.getText();
				try {
					if (manager.getLoginManager().validatelogin(uname, pwd)) {
						mainMenu = new MainMenu(manager);
						mainMenu.setUserName(uname);
						mainMenu.pack();
						mainMenu.setSize(new Dimension(1100, 800));
						mainMenu.setLocationRelativeTo(null);
						mainMenu.setVisible(true);
						dispose();
					}
					if (!manager.getLoginManager().validatelogin(uname, pwd)) {
						JOptionPane
								.showMessageDialog(null,
										"The username or password you entered is incorrect");
						passwordfield.setText("");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		panel.add(btnLogin, "cell 2 7,growx,aligny center");
		btnChangePassword = new JButton("CHANGE  PASSWORD");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateChangePasswordFrame();

			}
		});
		panel.add(btnChangePassword,
				"cell 3 7,alignx center,aligny center,grow");

	}

	public JFrame CreateChangePasswordFrame() {
		changePasswordFrame = new JFrame();

		changePasswordFrame.setVisible(true);
		// changePasswordFrame.setLocation( (Screen.width -
		// changePasswordFrame.WIDTH) / 2,
		// (screenSize.height - frameSize.height) / 2);
		changePasswordFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		panel.add(new JLabel("Please enter username"));
		usernameField = new JTextField(15);
		panel.add(usernameField);
		panel.add(new JLabel("Please enter your old password"));
		oldpassword = new JTextField(15);
		panel.add(oldpassword);
		panel.add(new JLabel("Please enter your new password"));
		newpassword = new JTextField(15);
		panel.add(newpassword);
		JButton button1 = new JButton("Click to change your password");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = usernameField.getText();
				String oldpwd = oldpassword.getText();
				String newpwd = newpassword.getText();
				if (manager.getLoginManager().changePassword(uname, oldpwd,
						newpwd)) {
					changePasswordFrame.dispose();
					dispose();
					manager.start();
				}
			}
		});
		JButton button2 = new JButton("Cancel change password and return");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePasswordFrame.dispose();
			}
		});
		panel.add(button1);
		panel.add(button2);
		changePasswordFrame.add(panel);
		changePasswordFrame.pack();
		return changePasswordFrame;
	}

	public void disposeChangePasswordPanel() {
		dispose();
	}

}
