package gr.surfapp.cf.view_controller;

import gr.surfapp.cf.Main;
import gr.surfapp.cf.dao.ISessionDAO;
import gr.surfapp.cf.dao.SessionDAOImpl;
import gr.surfapp.cf.dao.exceptions.SessionDaoException;
import gr.surfapp.cf.dto.SessionInsertDTO;
import gr.surfapp.cf.dto.SessionReadOnlyDTO;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.io.Serial;
import java.util.Map;


/**
 * The {@code InsertSession} class represents a form where users can input session details
 * including surf spot, surfboard, surf conditions, and opinions. After entering the data,
 * users can submit the session details, which will be validated and inserted into the system.
 * The page also provides options to exit or reset the form.
 *
 * This form interacts with {@code ISessionService} for business logic and validation,
 * as well as with {@code ISessionDAO} to perform database operations.
 *
 * @author Christos Ftoulis
 */
public class InsertSession extends JFrame {

	@Serial
	private static final long serialVersionUID = 1L;
	
    private final JTextArea conditionsText;
    private final JTextArea OpinionsText; // Declare this only at the class level
    private final JTextField surfspot_text;
    private final JTextField surfboard_text;

	private final ISessionDAO sessionDAO = new SessionDAOImpl();
	private final ISessionService sessionService = new SessionServiceImpl(sessionDAO);

    public InsertSession() {
        addWindowListener(new WindowAdapter() {
        });

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Surfers Adventures");
		setBounds(100, 100, 604, 479);
        JPanel insertSession = new JPanel();
		insertSession.setBackground(new Color(0, 128, 255));
		insertSession.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(insertSession);
		insertSession.setLayout(null);

		JPanel header = new JPanel();
		header.setLayout(null);
		header.setBounds(0, 0, 594, 46);
		insertSession.add(header);

		JLabel lblInsertSessionPage = new JLabel("Insert Session Page");
		lblInsertSessionPage.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblInsertSessionPage.setBounds(190, 10, 317, 26);
		header.add(lblInsertSessionPage);


		JLabel lblNewLabelSpot = new JLabel("Surf Spot");
		lblNewLabelSpot.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelSpot.setBounds(74, 89, 69, 13);
		insertSession.add(lblNewLabelSpot);


		JLabel lblNewLabelBoard = new JLabel("Surfboard");
		lblNewLabelBoard.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelBoard.setBounds(241, 89, 69, 13);
		insertSession.add(lblNewLabelBoard);

		conditionsText = new JTextArea();
		conditionsText.setColumns(20);
		conditionsText.setBounds(75, 183, 110, 106);
		insertSession.add(conditionsText);

		conditionsText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				conditionsText.setText(conditionsText.getText().trim());

			}
		});

		JLabel lblNewLabelSurfConditions = new JLabel("Conditions");
		lblNewLabelSurfConditions.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelSurfConditions.setBounds(74, 169, 83, 13);
		insertSession.add(lblNewLabelSurfConditions);

		OpinionsText = new JTextArea(); // Initialize the class-level OpinionsText
        OpinionsText.setColumns(20);
        OpinionsText.setBounds(241, 183, 262, 106);
        insertSession.add(OpinionsText);

		OpinionsText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				OpinionsText.setText(OpinionsText.getText().trim());

			}
		});

		JLabel lblNewLabelSurfDetails = new JLabel("Surf Results");
		lblNewLabelSurfDetails.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabelSurfDetails.setBounds(241, 169, 109, 13);
		insertSession.add(lblNewLabelSurfDetails);

		JPanel footer = new JPanel();
		footer.setLayout(null);
		footer.setBounds(0, 399, 594, 43);
		insertSession.add(footer);

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
					Main.getDashboard().setEnabled(true);
					Main.getInsertSession().setVisible(false);
				}
			});
		btnNewButtonClose.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButtonClose.setBounds(76, 333, 109, 29);
		insertSession.add(btnNewButtonClose);

		JButton btnNewButtonSubmit = new JButton("SUBMIT");
		btnNewButtonSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SessionReadOnlyDTO sessionReadOnlyDTO;
				SessionInsertDTO insertDTO = doDataBinding();

				// Data binding

				// Validation
				Map<String , String> errors = SessionValidator.validate(insertDTO);

				if (!errors.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							String.join("\n", errors.values()),
							"Validation Errors",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				try {
					sessionReadOnlyDTO = sessionService.insertSession(insertDTO);

					Main.getInsertSuccess().setUuidText(sessionReadOnlyDTO.getUuid());
					Main.getInsertSession().setEnabled(false);
					Main.getInsertSuccess().setVisible(true);
					resetInputForm();

				} catch (SessionDaoException ex) {
					JOptionPane.showMessageDialog(null,
							"Database Error: " + ex.getMessage(),
							"Error",
							JOptionPane.ERROR_MESSAGE);
					;
//				} catch (SessionAlreadyExistsException ex) {
//					JOptionPane.showMessageDialog(null, "Session already exists", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (SessionNotFoundException ex) {
					JOptionPane.showMessageDialog(null, "Session was inserted but could not be mapped.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				}
		});

		btnNewButtonSubmit.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButtonSubmit.setBounds(394, 333, 109, 29);
		insertSession.add(btnNewButtonSubmit);
		
		surfspot_text = new JTextField();
		surfspot_text.setBounds(74, 112, 110, 29);
		insertSession.add(surfspot_text);
		surfspot_text.setColumns(10);

		surfspot_text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				surfspot_text.setText(surfspot_text.getText().trim());
			}
		});


		surfboard_text = new JTextField();
		surfboard_text.setColumns(10);
		surfboard_text.setBounds(240, 112, 110, 29);
		insertSession.add(surfboard_text);

		surfboard_text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				surfboard_text.setText(surfboard_text.getText().trim());
			}
		});
	}

	private SessionInsertDTO doDataBinding() {
		String surfspots = surfspot_text.getText().trim();
		String surfboards = surfboard_text.getText().trim();
		String conditions = conditionsText.getText().trim();
		String opinions = OpinionsText.getText().trim();

		return new SessionInsertDTO(surfspots, surfboards, conditions, opinions);
	}

	private void resetInputForm() {
		surfspot_text.setText("");
		surfboard_text.setText("");
		conditionsText.setText("");
		OpinionsText.setText("");
	}
}
