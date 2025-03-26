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
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;


public class InsertSession extends JFrame {

	private static final long serialVersionUID = 1L;
    private JPanel InsertSession;
    private JTextArea conditionsText;
    private JTextArea OpinionsText; // Declare this only at the class level
    private JTextField surfspot_text;
    private JTextField surfboard_text;

    public InsertSession() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                surfspot_text.setText("");
                surfboard_text.setText("");
                conditionsText.setText("");
                OpinionsText.setText(""); // This should work now
            }
        });
        
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Surfers Adventures");
		setBounds(100, 100, 604, 479);
		InsertSession = new JPanel();
		InsertSession.setBackground(new Color(0, 128, 255));
		InsertSession.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(InsertSession);
		InsertSession.setLayout(null);
		
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBounds(0, 0, 594, 46);
		InsertSession.add(header);
		
		JLabel lblInsertSessionPage = new JLabel("Insert Session Page");
		lblInsertSessionPage.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblInsertSessionPage.setBounds(190, 10, 317, 26);
		header.add(lblInsertSessionPage);
		
		
		JLabel lblNewLabelSpot = new JLabel("Surf Spot");
		lblNewLabelSpot.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelSpot.setBounds(74, 89, 69, 13);
		InsertSession.add(lblNewLabelSpot);
		
		
		JLabel lblNewLabelBoard = new JLabel("Surfboard");
		lblNewLabelBoard.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelBoard.setBounds(241, 89, 69, 13);
		InsertSession.add(lblNewLabelBoard);
		
		conditionsText = new JTextArea();
		conditionsText.setColumns(20);
		conditionsText.setBounds(75, 183, 110, 106);
		InsertSession.add(conditionsText);
		
		conditionsText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				conditionsText.setText(conditionsText.getText().trim());	
			
			}
		});
		
		JLabel lblNewLabelSurfConditions = new JLabel("Conditions");
		lblNewLabelSurfConditions.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelSurfConditions.setBounds(74, 169, 83, 13);
		InsertSession.add(lblNewLabelSurfConditions);
		
		OpinionsText = new JTextArea(); // Initialize the class-level OpinionsText
        OpinionsText.setColumns(20);
        OpinionsText.setBounds(241, 183, 262, 106);
        InsertSession.add(OpinionsText);
		
		OpinionsText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				OpinionsText.setText(OpinionsText.getText().trim());	
			
			}
		});
		
		JLabel lblNewLabelSurfDetails = new JLabel("Surf Results");
		lblNewLabelSurfDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelSurfDetails.setBounds(241, 169, 109, 13);
		InsertSession.add(lblNewLabelSurfDetails);
		
		JPanel footer = new JPanel();
		footer.setLayout(null);
		footer.setBounds(0, 399, 594, 43);
		InsertSession.add(footer);
		
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
					Main.getInsertSession().setVisible(false);
					Main.getDashboard().setEnabled(true);
				}
			});
		btnNewButtonClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButtonClose.setBounds(76, 333, 109, 29);
		InsertSession.add(btnNewButtonClose);
		
		JButton btnNewButtonSubmit = new JButton("SUBMIT");
		btnNewButtonSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Data binding
				String surfspots = surfspot_text.getText().trim();
				String surfboards = surfboard_text.getText().trim();
				String conditions = conditionsText.getText().trim();
				String opinions = OpinionsText.getText().trim();
				
				// Validation
				
				// Insert
				String sql = "INSERT INTO sessions (surfspots, surfboards, conditions, opinions, uuid) VALUES (?, ?, ?, ?, ?)";
				
            	Connection conn = Dashboard.getConnection();
            	
				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					
					ps.setString(1, surfspots);
					ps.setString(2, surfboards);
					ps.setString(3, conditions);
					ps.setString(4, opinions);
					
					String uuid = UUID.randomUUID().toString();
				    ps.setString(5, uuid);
					
					int n = ps.executeUpdate();
					
					JOptionPane.showMessageDialog(null,  n + " record(s) inserted", "INSERT", JOptionPane.PLAIN_MESSAGE);	
				} catch (SQLException e1) {			
				    e1.printStackTrace();
					JOptionPane.showMessageDialog(null,  "Insertion error", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
});
		
		btnNewButtonSubmit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButtonSubmit.setBounds(394, 333, 109, 29);
		InsertSession.add(btnNewButtonSubmit);
		
		surfspot_text = new JTextField();
		surfspot_text.setBounds(74, 112, 110, 29);
		InsertSession.add(surfspot_text);
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
		InsertSession.add(surfboard_text);
		
		surfboard_text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				surfboard_text.getText().trim();	
			
			}
		});
		
	}
}
