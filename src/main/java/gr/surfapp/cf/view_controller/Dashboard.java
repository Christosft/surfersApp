package gr.surfapp.cf.view_controller;

import gr.surfapp.cf.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel SurfersLog;
	private final JPanel QuotesAndSessions = new JPanel();
	private static Connection connection;

	
	public Dashboard() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String sql = "jdbc:mysql://localhost:3306/surf_Sessions_db?serverTimezone=UTC";
				String username = "surfersSes";
				String password = "";
				
				try {
					connection = DriverManager.getConnection(sql, username, password);
					System.out.println("Connection success");
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Surfers Adventures");
		setBounds(100, 100, 1359, 781);
		SurfersLog = new JPanel();
		SurfersLog.setBackground(new Color(0, 128, 255));
		SurfersLog.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(SurfersLog);
		SurfersLog.setLayout(null);
		QuotesAndSessions.setBounds(0, 0, 1345, 61);
		SurfersLog.add(QuotesAndSessions);
		QuotesAndSessions.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Huppy Surfers Log Page");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(467, 10, 317, 26);
		QuotesAndSessions.add(lblNewLabel);
		
		JPanel SessionPanel = new JPanel();
		SessionPanel.setBackground(new Color(0, 128, 192));
		SessionPanel.setBounds(0, 61, 164, 586);
		SurfersLog.add(SessionPanel);
		SessionPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Christos Ftoulis");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(10, 10, 117, 23);
		SessionPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MAIN");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setBounds(10, 73, 85, 37);
		SessionPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("New Session");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_3.setText("<html><font color='blue'>New Session</font></html>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_3.setText("New Session");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.getDashboard().setEnabled(false);
				Main.getInsertSession().setVisible(true);
			}
		});
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(47, 102, 107, 23);
		SessionPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("View Session");
		lblNewLabel_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_3_1.setText("<html><font color='blue'>View Sessions</font></html>");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_3_1.setText("View Session");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.getDashboard().setEnabled(false);
				Main.getViewSessionsPage().setVisible(true);
			}
		});
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(47, 135, 107, 23);
		SessionPanel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4 = new JLabel("Surfing Quotes");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(196, 71, 718, 25);
		SurfersLog.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("The best surfer out there is the one having the most fun.");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(196, 109, 718, 25);
		SurfersLog.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Out of the water, I am nothing.");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5_1.setBounds(196, 133, 718, 25);
		SurfersLog.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_5_2 = new JLabel("Surfing is attitude dancing.");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5_2.setBounds(196, 157, 718, 25);
		SurfersLog.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("Surfing to me is like playing music. You play different melodies \r\n");
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5_3.setBounds(196, 181, 718, 25);
		SurfersLog.add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_5_4 = new JLabel("with different boards.");
		lblNewLabel_5_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5_4.setBounds(196, 203, 718, 25);
		SurfersLog.add(lblNewLabel_5_4);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("We're all equal before a wave.");
		lblNewLabel_5_2_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5_2_1.setBounds(196, 225, 718, 25);
		SurfersLog.add(lblNewLabel_5_2_1);
		
		JPanel footer = new JPanel();
		footer.setLayout(null);
		footer.setBounds(0, 676, 1345, 68);
		SurfersLog.add(footer);
		
		JLabel lblNewLabel_6 = new JLabel("Surfers Guide");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_6.setBounds(34, 10, 217, 23);
		footer.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1_1 = new JLabel("Surfers Questions");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(561, 10, 310, 23);
		footer.add(lblNewLabel_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(64, 0, 64));
		separator.setBounds(10, 657, 1312, 9);
		SurfersLog.add(separator);
		
		JButton btnNewButton = new JButton("NEW SESSIONS");
		btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Main.getDashboard().setEnabled(false);
					Main.getInsertSession().setVisible(true);
				}
			});
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(209, 615, 164, 32);
		SurfersLog.add(btnNewButton);
		
		JButton btnNewButtonViewSes = new JButton("VIEW SESSION");
		btnNewButtonViewSes.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButtonViewSes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getDashboard().setEnabled(false);
				Main.getViewSessionsPage().setVisible(true);
			}
		});
		btnNewButtonViewSes.setBounds(561, 615, 154, 34);
		SurfersLog.add(btnNewButtonViewSes);
		}

	public static Connection getConnection() {
		return connection;
	}
}
