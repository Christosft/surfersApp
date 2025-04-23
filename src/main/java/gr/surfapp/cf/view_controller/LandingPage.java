package gr.surfapp.cf.view_controller;


import gr.surfapp.cf.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The {@code LandingPage} class represents the main welcome screen
 * of the "Surfers Adventures" application.
 * <p>
 * This class sets up the GUI layout, including welcome text,
 * footer options, and a connect button to proceed to the login page.
 * </p>
 *
 * @author Christos Ftoulis
 */
public class LandingPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	
	public LandingPage() {
		setTitle("Surfers Adventures");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 388);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel footer = new JPanel();
		footer.setBounds(0, 308, 544, 43);
		contentPane.add(footer);
		footer.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Surfers Guide");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(34, 10, 119, 23);
		footer.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Surfers Questions");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(289, 10, 150, 23);
		footer.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome to Huppy Surfers log");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel_2.setBounds(119, 118, 325, 37);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Main.getLandingPage().setVisible(false);
					Main.getLoginPage().setVisible(true);
				}
			});
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(208, 182, 112, 37);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(64, 0, 64));
		separator.setBounds(10, 296, 524, 2);
		contentPane.add(separator);
	}
}
