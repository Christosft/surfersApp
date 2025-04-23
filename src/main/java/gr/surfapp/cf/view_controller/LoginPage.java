package gr.surfapp.cf.view_controller;


import gr.surfapp.cf.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

/**
 * The {@code LoginPage} class represents a simple login window for the Surfers Adventures application.
 * <p>
 * It provides fields for username and password input and validates them against hardcoded credentials.
 * If authentication is successful, it redirects the user to the main dashboard.
 * </p>
 *
 *  @author Christos Ftoulis
 */
public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel LoginPage;
	private JTextField username;
	private JPasswordField password;
	
	
	public LoginPage() {
		setTitle("Surfers Adventures");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 378);
		LoginPage = new JPanel();
		LoginPage.setBackground(new Color(0, 128, 255));
		LoginPage.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(LoginPage);
		LoginPage.setLayout(null);
		
		JPanel footer = new JPanel();
		footer.setLayout(null);
		footer.setBounds(0, 298, 559, 43);
		LoginPage.add(footer);
		
		JLabel lblNewLabel = new JLabel("Surfers Guide");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(34, 10, 119, 23);
		footer.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Surfers Questions");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(289, 10, 150, 23);
		footer.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(64, 0, 64));
		separator.setBounds(10, 286, 524, 2);
		LoginPage.add(separator);
		
		JLabel lblNewLabel_2 = new JLabel("username");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(177, 99, 93, 11);
		LoginPage.add(lblNewLabel_2);
		
		username = new JTextField();
		username.setBounds(174, 111, 120, 26);
		LoginPage.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(177, 147, 93, 11);
		LoginPage.add(lblNewLabel_3);
		
		password = new JPasswordField();
		password.setBounds(177, 159, 117, 26);
		LoginPage.add(password);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(username.getText().matches("[aA]dmin") && (Arrays.equals(password.getPassword(), "12345".toCharArray()))) {
					Main.getLoginPage().setVisible(false);
					Main.getDashboard().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Wrong username or password", "connection fault", JOptionPane.ERROR_MESSAGE);
				}
					}
				});
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(177, 211, 117, 33);
		LoginPage.add(btnNewButton);
		
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBounds(0, 0, 559, 46);
		LoginPage.add(header);
		
		JLabel lblHappySurfersLogin = new JLabel("Happy Surfers Login Page");
		lblHappySurfersLogin.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblHappySurfersLogin.setBounds(147, 10, 317, 26);
		header.add(lblHappySurfersLogin);
		
	}
}
