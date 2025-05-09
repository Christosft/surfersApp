package gr.surfapp.cf.view_controller;

import gr.surfapp.cf.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * {@code UpdateSessionSuccessPage} is a confirmation window that appears when a session update
 * is successfully completed. It displays a message confirming the success along with the session's unique code.
 * <p>
 * The window provides a close button that hides the success window and re-enables the previous update and view windows.
 * </p>
 *
 *  @author Christos Ftoulis
 */

public class UpdateSessionSuccessPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel uuidText;


    public UpdateSessionSuccessPage() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 564, 362);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnClose = new JButton("Κλείσιμο");
        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Main.getUpdateSession().setEnabled(true);
                Main.getUpdateSuccess().setVisible(false);
                Main.getViewSessionsPage().setEnabled(true);
                Main.getUpdateSession().setVisible(false);
            }
        });
        btnClose.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnClose.setBounds(345, 257, 184, 45);
        contentPane.add(btnClose);

        JLabel lblInsert = new JLabel("Session is Updated");
        lblInsert.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblInsert.setHorizontalAlignment(SwingConstants.CENTER);
        lblInsert.setBounds(27, 22, 504, 35);
        contentPane.add(lblInsert);

        JPanel successPanel = new JPanel();
        successPanel.setBackground(new Color(0, 98, 49));
        successPanel.setBounds(68, 80, 425, 139);
        contentPane.add(successPanel);
        successPanel.setLayout(null);

        JLabel successText = new JLabel("Session updated successfully");
        successText.setFont(new Font("Tahoma", Font.BOLD, 16));
        successText.setForeground(new Color(255, 255, 255));
        successText.setBounds(40, 24, 354, 36);
        successPanel.add(successText);

        JLabel lblNewLabel = new JLabel("Session code");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

        lblNewLabel.setBounds(81, 71, 273, 24);
        successPanel.add(lblNewLabel);

        uuidText = new JLabel("");
        uuidText.setFont(new Font("Tahoma", Font.PLAIN, 12));
        uuidText.setForeground(new Color(255, 255, 255));
        uuidText.setBounds(46, 104, 342, 24);
        successPanel.add(uuidText);
        uuidText.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setUuidText(String uuid) {
        uuidText.setText(uuid);
    }
}