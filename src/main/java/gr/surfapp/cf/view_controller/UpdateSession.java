package gr.surfapp.cf.view_controller;


import gr.surfapp.cf.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class UpdateSession extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel UpdateSession;
    private JTextArea conditionsText;
    private JTextArea OpinionsText; // Declare this only at the class level
    private JTextField surfspot_text;
    private JTextField surfboard_text;

    public UpdateSession() {
    	addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				fetchSessionFromDatabase(Main.getViewSessionsPage().getCurrentUserId());

			}
			
			@Override
			public void windowOpened(WindowEvent e) {

			}
		});
        
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Surfers Adventures");
		setBounds(100, 100, 604, 479);
		UpdateSession = new JPanel();
		UpdateSession.setBackground(new Color(0, 128, 255));
		UpdateSession.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(UpdateSession);
		UpdateSession.setLayout(null);
		
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBounds(0, 0, 594, 46);
		UpdateSession.add(header);
		
		JLabel lblUpdateSessionPage = new JLabel("Update Session Page");
		lblUpdateSessionPage.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblUpdateSessionPage.setBounds(190, 10, 317, 26);
		header.add(lblUpdateSessionPage);
		
		
		JLabel lblNewLabelSpot = new JLabel("Surfspot");
		lblNewLabelSpot.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelSpot.setBounds(74, 89, 69, 13);
		UpdateSession.add(lblNewLabelSpot);
		
		
		JLabel lblNewLabelBoard = new JLabel("Surfboard");
		lblNewLabelBoard.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelBoard.setBounds(241, 89, 69, 13);
		UpdateSession.add(lblNewLabelBoard);
		
		conditionsText = new JTextArea();
		conditionsText.setColumns(20);
		conditionsText.setBounds(75, 183, 110, 106);
		UpdateSession.add(conditionsText);
		
		conditionsText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				conditionsText.getText().replaceAll("[\\n\\r]", " ");	
			
			}
		});
		
		JLabel lblNewLabelSurfConditions = new JLabel("Conditions");
		lblNewLabelSurfConditions.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelSurfConditions.setBounds(74, 169, 83, 13);
		UpdateSession.add(lblNewLabelSurfConditions);
		
		OpinionsText = new JTextArea(); // Initialize the class-level OpinionsText
        OpinionsText.setColumns(20);
        OpinionsText.setBounds(241, 183, 262, 106);
        UpdateSession.add(OpinionsText);
		
		OpinionsText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				OpinionsText.getText().replaceAll("[\\n\\r]", " ");
			
			}
		});
		
		JLabel lblNewLabelSurfDetails = new JLabel("Surf Results");
		lblNewLabelSurfDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelSurfDetails.setBounds(241, 169, 109, 13);
		UpdateSession.add(lblNewLabelSurfDetails);
		
		JPanel footer = new JPanel();
		footer.setLayout(null);
		footer.setBounds(0, 399, 594, 43);
		UpdateSession.add(footer);
		
		JLabel lblNewLabel_6 = new JLabel("Surfers Guide");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(34, 10, 119, 23);
		footer.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1_1 = new JLabel("Surfers Questions");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(289, 10, 150, 23);
		footer.add(lblNewLabel_1_1);
		
		JButton btnNewButtonClose = new JButton("EXIT");
		btnNewButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Main.getUpdateSession().setVisible(false);
					Main.getViewSessionsPage().setEnabled(true);
				}
			});
		btnNewButtonClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButtonClose.setBounds(76, 333, 109, 29);
		UpdateSession.add(btnNewButtonClose);
		
		JButton btnNewButtonUpdate = new JButton("UPDATE");
		btnNewButtonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Data binding
				String Surfspot = surfspot_text.getText().trim();
				String Surfboard = surfboard_text.getText().trim();
				String Conditions = conditionsText.getText();
				String SurfResults = OpinionsText.getText();
				
				
				
                    

                	String sql = "UPDATE sessions SET surfspots = ?, surfboards = ?, conditions = ?, opinions = ?" 
                			+ "WHERE uuid = ?";
    				
                	Connection conn = Dashboard.getConnection();
                	
    				try (PreparedStatement ps = conn.prepareStatement(sql)) {
    					
    					ps.setString(1, Surfspot);
    					ps.setString(2, Surfboard);
    					ps.setString(3, Conditions);
    					ps.setString(4, SurfResults);
    				
    					ps.setString(5, Main.getViewSessionsPage().getCurrentUserId());
    					
    					int n = ps.executeUpdate();
    					
    					JOptionPane.showMessageDialog(null,  n + " record(s) inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);	
    				} catch (SQLException e1) {			
    					e1.printStackTrace();
    					JOptionPane.showMessageDialog(null,  "Insertion error", "Error", JOptionPane.ERROR_MESSAGE);
    				}
			}
		});
		
		
		btnNewButtonUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButtonUpdate.setBounds(394, 333, 109, 29);
		UpdateSession.add(btnNewButtonUpdate);
		
		surfspot_text = new JTextField();
		surfspot_text.setBounds(74, 112, 110, 29);
		UpdateSession.add(surfspot_text);
		surfspot_text.setColumns(10);
		
		surfspot_text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				surfspot_text.getText().trim();	
			}
		});
		
		surfboard_text = new JTextField();
		surfboard_text.setColumns(10);
		surfboard_text.setBounds(240, 112, 110, 29);
		UpdateSession.add(surfboard_text);
		
		surfboard_text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				surfboard_text.getText().trim();	
			
			}
		});
}

private void fetchSessionFromDatabase(String id) {
	String sql = "SELECT * FROM sessions WHERE uuid = ?";
	Connection conn = Dashboard.getConnection();
	
	try (PreparedStatement ps = conn.prepareStatement(sql)) {
		
		
		ps.setString(1,  id);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			surfspot_text.setText(rs.getString("surfspots"));
			surfboard_text.setText(rs.getString("surfboards"));
			conditionsText.setText(rs.getString("conditions"));
			OpinionsText.setText(rs.getString("opinions"));
			
		}	
	} catch (SQLException e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null,  "Select error in fetch session", "Error", JOptionPane.ERROR_MESSAGE);	
	}
}
}

		
		