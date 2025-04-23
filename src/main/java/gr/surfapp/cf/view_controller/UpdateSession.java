package gr.surfapp.cf.view_controller;


import gr.surfapp.cf.Main;
import gr.surfapp.cf.dao.ISessionDAO;
import gr.surfapp.cf.dao.SessionDAOImpl;
import gr.surfapp.cf.dao.exceptions.SessionDaoException;
import gr.surfapp.cf.dto.SessionReadOnlyDTO;
import gr.surfapp.cf.dto.SessionUpdateDTO;
import gr.surfapp.cf.exceptions.SessionNotFoundException;
import gr.surfapp.cf.service.ISessionService;
import gr.surfapp.cf.service.SessionServiceImpl;
import gr.surfapp.cf.validator.SessionValidator;

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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.util.Map;

/**
 * The {@code UpdateSession} class provides a GUI for updating a surfing session's details.
 * <p>
 * This window allows users to:
 * <ul>
 *   <li>View and edit the details of a selected session.</li>
 *   <li>Validate input before updating.</li>
 *   <li>Submit updates to the session service.</li>
 *   <li>Navigate back to the session list or close the window.</li>
 * </ul>
 * </p>
 * This class interacts with the backend service via {@link ISessionService}.
 *
 *  @author Christos Ftoulis
 */

public class UpdateSession extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel UpdateSession;
	private JTextArea conditionsText;
	private JTextArea OpinionsText;
	private JTextField surfspot_text;
	private JTextField surfboard_text;

	private final ISessionDAO sessionDAO = new SessionDAOImpl();
	private final ISessionService sessionService = new SessionServiceImpl(sessionDAO);

	private SessionReadOnlyDTO dto;

	public UpdateSession() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				fetchSessionFromDatabase(Main.getViewSessionsPage().getSelectedId());
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
		conditionsText.setBounds(75, 183, 110, 106);
		UpdateSession.add(conditionsText);

		JLabel lblNewLabelSurfConditions = new JLabel("Conditions");
		lblNewLabelSurfConditions.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelSurfConditions.setBounds(74, 169, 83, 13);
		UpdateSession.add(lblNewLabelSurfConditions);

		OpinionsText = new JTextArea();
		OpinionsText.setBounds(241, 183, 262, 106);
		UpdateSession.add(OpinionsText);

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
				clearFields(); // optional
				Main.getViewSessionsPage().setEnabled(true);
				Main.getUpdateSession().setVisible(false);
			}
		});
		btnNewButtonClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButtonClose.setBounds(76, 333, 109, 29);
		UpdateSession.add(btnNewButtonClose);

		JButton btnNewButtonUpdate = new JButton("UPDATE");
		btnNewButtonUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionUpdateDTO updateDTO = doDataBinding();

				Map<String, String> errors = SessionValidator.validate(updateDTO);
				if (!errors.isEmpty()) {
					StringBuilder errorMessage = new StringBuilder("Please fix the following:\n");
					errors.forEach((field, msg) -> errorMessage.append("- ").append(msg).append("\n"));
					JOptionPane.showMessageDialog(null, errorMessage.toString(), "Validation Errors", JOptionPane.WARNING_MESSAGE);
					return;
				}

				try {
					SessionReadOnlyDTO sessionReadOnlyDTO = sessionService.updateSession(dto.getId(), updateDTO);
					Main.getUpdateSuccess().setUuidText(sessionReadOnlyDTO.getUuid());
					//JOptionPane.showMessageDialog(null, "Session updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					Main.getUpdateSession().setVisible(false);
					Main.getViewSessionsPage().setEnabled(true);
					Main.getUpdateSuccess().setVisible(true);
					Main.getViewSessionsPage().buildTable(); // Refresh the table
				} catch (SessionDaoException ex) {
					JOptionPane.showMessageDialog(null, "DB Error", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (SessionNotFoundException ex) {
					JOptionPane.showMessageDialog(null, "Session not found", "Error", JOptionPane.ERROR_MESSAGE);
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

		surfboard_text = new JTextField();
		surfboard_text.setColumns(10);
		surfboard_text.setBounds(240, 112, 110, 29);
		UpdateSession.add(surfboard_text);
	}

	private SessionUpdateDTO doDataBinding() {
		Integer id = dto.getId();
		String surfspot = surfspot_text.getText().trim();
		String surfboard = surfboard_text.getText().trim();
		String conditions = conditionsText.getText().trim();
		String opinions = OpinionsText.getText().trim();
		String uuid = dto.getUuid();


		return new SessionUpdateDTO(id, surfspot, surfboard, conditions, opinions, uuid);
	}

	private void fetchSessionFromDatabase(int id) {
		try {
			dto = sessionService.getSessionById(id);

			surfspot_text.setText(dto.getSurfspots());
			surfboard_text.setText(dto.getSurfboards());
			conditionsText.setText(dto.getConditions());
			OpinionsText.setText(dto.getOpinions());
		} catch (SessionDaoException | SessionNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Unable to fetch session data", "Error", JOptionPane.ERROR_MESSAGE);
			Main.getViewSessionsPage().setEnabled(true);
			Main.getUpdateSession().setVisible(false);
		}
	}

	private void clearFields() {
		surfspot_text.setText("");
		surfboard_text.setText("");
		conditionsText.setText("");
		OpinionsText.setText("");
	}
}