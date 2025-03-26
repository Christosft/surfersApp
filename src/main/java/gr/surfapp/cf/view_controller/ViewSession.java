package gr.surfapp.cf.view_controller;


import gr.surfapp.cf.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewSession extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel SurfCode;
	private JLabel Surfspot;
	private JLabel Surfboard;
	private JLabel Date;
	private JLabel Conditions;
	private JLabel SurfResults;
	

	
	public ViewSession() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				
				fetchSessionFromDatabase(Main.getViewSessionsPage().getCurrentUserId());
			}
			
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
		});
		
		setTitle("Surfers Adventures");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1058, 467);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBounds(0, 0, 1044, 46);
		contentPane.add(header);
		
		JLabel lblViewSessions = new JLabel("View Sessions");
		lblViewSessions.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblViewSessions.setBounds(373, 10, 158, 26);
		header.add(lblViewSessions);
		
		JPanel footer = new JPanel();
		footer.setLayout(null);
		footer.setBounds(0, 387, 1044, 43);
		contentPane.add(footer);
		
		JLabel lblNewLabel = new JLabel("Surfers Guide");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(141, 10, 119, 23);
		footer.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Surfers Questions");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(647, 10, 150, 23);
		footer.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Surfspot");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(53, 131, 91, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Surfboard");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(53, 165, 91, 24);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Conditions");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(53, 252, 91, 24);
		contentPane.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Surf Results");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_3.setBounds(53, 296, 91, 24);
		contentPane.add(lblNewLabel_2_3);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(10, 243, 1024, 13);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton("EXIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getViewSession().setVisible(false);
				Main.getViewSessionsPage().setEnabled(true);
				}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(731, 343, 104, 24);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Date");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1_1.setBounds(53, 199, 91, 24);
		contentPane.add(lblNewLabel_2_1_1);
		
		Surfspot = new JLabel("Surfspot");
		Surfspot.setFont(new Font("Tahoma", Font.BOLD, 12));
		Surfspot.setBounds(265, 131, 610, 24);
		contentPane.add(Surfspot);
		
		Surfboard = new JLabel("Surfboard");
		Surfboard.setFont(new Font("Tahoma", Font.BOLD, 12));
		Surfboard.setBounds(265, 165, 610, 24);
		contentPane.add(Surfboard);
		
		Date = new JLabel("Date");
		Date.setFont(new Font("Tahoma", Font.BOLD, 12));
		Date.setBounds(265, 199, 620, 24);
		contentPane.add(Date);
		
		Conditions = new JLabel("Conditions");
		Conditions.setFont(new Font("Tahoma", Font.BOLD, 12));
		Conditions.setBounds(265, 252, 610, 24);
		contentPane.add(Conditions);
		
		SurfResults = new JLabel("SurfResults");
		SurfResults.setFont(new Font("Tahoma", Font.BOLD, 12));
		SurfResults.setBounds(265, 296, 769, 24);
		contentPane.add(SurfResults);
		
		JLabel lblNewLabel_2_4 = new JLabel("SurfCode");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_4.setBounds(53, 97, 91, 24);
		contentPane.add(lblNewLabel_2_4);
		
		SurfCode = new JLabel("SurfCode");
		SurfCode.setFont(new Font("Tahoma", Font.BOLD, 12));
		SurfCode.setBounds(265, 97, 610, 24);
		contentPane.add(SurfCode);
	}
		
		private void fetchSessionFromDatabase(String id) {
			String sql = "SELECT * FROM sessions WHERE uuid = ?";
			
			Connection conn = Dashboard.getConnection();
			
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					SurfCode.setText(rs.getString("uuid")); //.substring(0, 8));
					Surfspot.setText(rs.getString("surfspots"));
					Surfboard.setText(rs.getString("surfboards"));
					Date.setText(rs.getString("created_at"));
					Conditions.setText(rs.getString("conditions"));
					SurfResults.setText(rs.getString("opinions"));
					
			}
				}catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,  "Select error in fetch session", "Error", JOptionPane.ERROR_MESSAGE);	
			}
		}
	}
	
