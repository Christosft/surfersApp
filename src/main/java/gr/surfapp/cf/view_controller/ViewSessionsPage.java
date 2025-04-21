package gr.surfapp.cf.view_controller;

import gr.surfapp.cf.Main;
import gr.surfapp.cf.dao.ISessionDAO;
import gr.surfapp.cf.dao.SessionDAOImpl;
import gr.surfapp.cf.dao.exceptions.SessionDaoException;
import gr.surfapp.cf.dto.SessionReadOnlyDTO;
import gr.surfapp.cf.exceptions.SessionNotFoundException;
import gr.surfapp.cf.model.Session;
import gr.surfapp.cf.service.ISessionService;
import gr.surfapp.cf.service.SessionServiceImpl;

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
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ViewSessionsPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField surfspot_text;
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private String selectedUUID;
	private int selectedId;

	private final ISessionDAO sessionDAO = new SessionDAOImpl();
	private final ISessionService sessionService = new SessionServiceImpl(sessionDAO);

	public ViewSessionsPage() {
		addWindowListener(new WindowAdapter() {
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

		model = new DefaultTableModel(
				new Object[][]{},
				new String[]{"ID", "Uuid", "Surfspot", "Surfboard"}
		);

		table = new JTable(model);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
						selectedId = (Integer) model.getValueAt(selectedRow, 0); // ID column
						selectedUUID = (String) model.getValueAt(selectedRow, 1); // UUID column
					}
				}
			}
		});

		// Hide the UUID column by name
		TableColumnModel columnModel = table.getColumnModel();
		TableColumn uuidColumn = columnModel.getColumn(model.findColumn("Uuid"));
		uuidColumn.setMinWidth(0);
		uuidColumn.setMaxWidth(0);
		uuidColumn.setPreferredWidth(0);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(57, 192, 507, 307);
		contentPane.add(scrollPane);

		JButton viewBtn = new JButton("View");
		viewBtn.setForeground(Color.WHITE);
		viewBtn.setBackground(new Color(0, 128, 0));
		viewBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		viewBtn.setBounds(619, 229, 202, 52);
		viewBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.getViewSessionsPage().setEnabled(false);
				Main.getViewSession().setVisible(true);
			}
		});
		contentPane.add(viewBtn);

		JButton updateBtn = new JButton("Update");
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		updateBtn.setBackground(new Color(0, 128, 64));
		updateBtn.setBounds(619, 292, 202, 52);
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedId == 0) {
					JOptionPane.showMessageDialog(null, "Please select a session to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
					return;
				}

				Main.getViewSessionsPage().setEnabled(false);
				// Toggle visibility to force windowActivated event
				Main.getUpdateSession().setVisible(false);
				Main.getUpdateSession().setVisible(true);
			}
		});
		contentPane.add(updateBtn);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBackground(new Color(0, 128, 64));
		btnDelete.setBounds(619, 355, 202, 52);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Ensure there's a valid selection
				if (selectedId == 0) {
					JOptionPane.showMessageDialog(null, "Please select a session to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
					return;
				}

				int response = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					try {
						sessionService.deleteSession(selectedId);
						JOptionPane.showMessageDialog(null, "Session was deleted successfully", "Delete", JOptionPane.INFORMATION_MESSAGE);
						buildTable(); // Refresh the table after deletion
					} catch (SessionDaoException | SessionNotFoundException ex) {
						JOptionPane.showMessageDialog(null, "Error deleting session", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
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
	}

	void buildTable() {
		List<SessionReadOnlyDTO> sessions;
		try {
			model.setRowCount(0);
			selectedUUID = null;
			table.clearSelection();

			sessions = sessionService.getSessionBySurfspot(surfspot_text.getText().trim());

			for (SessionReadOnlyDTO session : sessions) {
				model.addRow(new Object[]{
						session.getId(),       // ID column
						session.getUuid(),     // UUID column
						session.getSurfspots(),// Surfspot column
						session.getSurfboards() // Surfboard column
				});
			}
		} catch (SessionDaoException e) {
			JOptionPane.showMessageDialog(null, "Error loading sessions", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public int getSelectedId() {
		return selectedId;
	}
}
