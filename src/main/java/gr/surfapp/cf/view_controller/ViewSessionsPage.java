package gr.surfapp.cf.view_controller;

import gr.surfapp.cf.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewSessionsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField surfspot_text;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private String selectedUUID;

	public ViewSessionsPage() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
//				buildTable();
			}
			@Override
			public void windowActivated(WindowEvent e) {
				buildTable();
			}
		});
		setTitle("Surfers Adventures");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 882, 643);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBackground(new Color(240, 240, 240));
		header.setBounds(0, 0, 875, 52);
		contentPane.add(header);
		
		JLabel lblNewLabel = new JLabel("Session View");
		lblNewLabel.setBounds(329, 15, 267, 27);
		header.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		surfspot_text = new JTextField();
		surfspot_text.setBounds(110, 130, 181, 40);
		contentPane.add(surfspot_text);
		surfspot_text.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildTable();
			}
		});
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(304, 130, 125, 40);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				surfspot_text.setText("");
				buildTable();
			}
		});
		btnNewButton_1.setForeground(new Color(192, 192, 192));
		btnNewButton_1.setBounds(439, 130, 125, 40);
		contentPane.add(btnNewButton_1);
		
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Check if the selection is still adjusting
                if (!e.getValueIsAdjusting()) {
                    // Get the selected row index
                    int selectedRow = table.getSelectedRow();

                    // Check if a row is selected
                    if (selectedRow != -1) {
                        // Get data from the selected row
                        //String selectedStr = (String) model.getValueAt(selectedRow, 0); // ID column
                        //selectedId = Integer.parseInt(selectedStr);
                        //selectedId = Integer.parseInt(selectedStr);
                    	selectedUUID = (String) model.getValueAt(selectedRow, 0);
                        
                    }
                }
            }
        });
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Uuid", "Date", "Surfspot", "Surfboard"
			}
		));
		table.setBounds(57, 192, 507, 307);
		model = (DefaultTableModel) table.getModel();
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(57, 192, 507, 307);
		contentPane.add(scrollPane);
		
		JButton viewBtn = new JButton("View");
		viewBtn.setForeground(new Color(255, 255, 255));
		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getViewSessionsPage().setEnabled(false);
				Main.getViewSession().setVisible(true);
			}
		});
		viewBtn.setBackground(new Color(0, 128, 0));
		viewBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		viewBtn.setBounds(619, 229, 202, 52);
		contentPane.add(viewBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.setForeground(new Color(255, 255, 255));
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getViewSessionsPage().setEnabled(false);
				Main.getUpdateSession().setVisible(true);
			}
		});
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		updateBtn.setBackground(new Color(0, 128, 64));
		updateBtn.setBounds(619, 292, 202, 52);
		contentPane.add(updateBtn);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//doDelete(currentUserId);
				doDelete(selectedUUID);
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBackground(new Color(0, 128, 64));
		btnDelete.setBounds(619, 355, 202, 52);
		contentPane.add(btnDelete);
		
		JSeparator lineBottom_2 = new JSeparator();
		lineBottom_2.setBackground(Color.BLUE);
		lineBottom_2.setBounds(0, 516, 875, 2);
		contentPane.add(lineBottom_2);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getViewSessionsPage().setVisible(false);
				Main.getDashboard().setEnabled(true);
			}
		});
		closeBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		closeBtn.setBackground(Color.LIGHT_GRAY);
		closeBtn.setBounds(619, 445, 202, 52);
		contentPane.add(closeBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Surfspot");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(45, 130, 57, 44);
		contentPane.add(lblNewLabel_1);
		
		JPanel footer = new JPanel();
		footer.setLayout(null);
		footer.setBounds(0, 516, 877, 90);
		contentPane.add(footer);
		
		JLabel lblNewLabel_6 = new JLabel("Surfers Guide");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(157, 30, 119, 23);
		footer.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1_1 = new JLabel("Surfers Questions");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(548, 30, 150, 23);
		footer.add(lblNewLabel_1_1);
		//contentPane.add(table);
	}
	
	
	//public int getCurrentUserId() {
	//return currentUserId;
	//}
	public String getCurrentUserId() {
		return selectedUUID;
	}



	private void buildTable() {
		String sql = "SELECT uuid, created_at, surfspots, surfboards FROM sessions WHERE surfspots LIKE ? ";
	    Connection conn = Dashboard.getConnection();
	    
	    try (
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, surfspot_text.getText().trim() + "%");
	        
	        ResultSet rs = ps.executeQuery();
	        
	        model.setRowCount(0); // Clear the table
	        while (rs.next()) {
	            Object[] row = {
	            	rs.getString("uuid"), //.substring(0, 8),
	                rs.getString("created_at"),
	                rs.getString("surfspots"),
	                rs.getString("surfboards")
	            };
	            model.addRow(row);
	        }
	    } catch (SQLException e) {
	    	e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Select error", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	
	private void doDelete(String uuid) {
		String sql = "DELETE FROM sessions WHERE uuid = ?";
		Connection conn = Dashboard.getConnection();
		
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			
			//ps.setInt(1, user_id);
			ps.setString(1, uuid);
			
			int answer = JOptionPane.showConfirmDialog(null, "Είστε σίγουρη/ος", "Διαγραφή", 
					JOptionPane.YES_NO_OPTION);
			if (answer == JOptionPane.YES_OPTION) {
				int rowsAffected = ps.executeUpdate();
				JOptionPane.showMessageDialog(null, rowsAffected + " γρααμμή/ες διαγράφηκαν", "Διαγραφή", 
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				return;
			}							
		} catch (SQLException ex) {
			//ex.printStackTrace();
			JOptionPane.showMessageDialog(null,  "Delete error", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}