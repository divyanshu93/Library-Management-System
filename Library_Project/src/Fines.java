import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author div
 *
 */
public class Fines {

	public Fines() {
		DBConnector database = new DBConnector();// object of class running
		// queries
		/**
		 * adding panel and frame
		 *
		 */
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		JFrame frame = new JFrame("Fines");
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.getContentPane().add(panel);
		/**
		 * adding content to panel
		 *
		 */
		JButton update = new JButton("Update Fines");
		panel.add(update);

		JLabel output = new JLabel();
		panel.add(output);
		/**
		 * adding action listener to run update on fines database
		 *
		 */
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (database.updateFines()) {
						output.setText("Update Successfull");
					} else {
						output.setText("Update Failed");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

		JLabel displayFine = new JLabel("Display Fines");
		panel.add(displayFine);

		JCheckBox paidfine = new JCheckBox("Paid Fine");
		paidfine.setSelected(false);
		panel.add(paidfine);

		JCheckBox finedue = new JCheckBox("Fines Due");
		finedue.setSelected(false);
		panel.add(finedue);

		JLabel cardno = new JLabel("Enter Borrower Card No");
		panel.add(cardno);

		JTextField cardvalue = new JTextField();
		panel.add(cardvalue);

		JButton submit = new JButton("Submit");
		panel.add(submit);

		JTextArea result = new JTextArea();// resulting query print
		panel.add(result);
		/**
		 * adding action listener to print result
		 *
		 */
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int check = 0;
				Double output = 0.0;
				if (paidfine.isSelected() && finedue.isSelected()) {
					check = 1;
					String cardno = "";
					cardno = cardvalue.getText();
					if (cardno.length() == 0) {
						result.setText("Pls enter the Card Number");
					} else {
						output = database.DisplayFines(cardno, check);
						result.setText(String.valueOf(output));
					}
				} else if (paidfine.isSelected()) {
					check = 2;
					String cardno = "";
					cardno = cardvalue.getText();
					if (cardno.length() == 0) {
						result.setText("Pls enter the Card Number");
					} else {
						output = database.DisplayFines(cardno, check);
						result.setText(String.valueOf(output));
					}
				} else if (finedue.isSelected()) {
					check = 3;
					String cardno = "";
					cardno = cardvalue.getText();
					if (cardno.length() == 0) {
						result.setText("Pls enter the Card Number");
					} else {
						output = database.DisplayFines(cardno, check);
						result.setText(String.valueOf(output));
					}
				} else {
					result.setText("Pls Check at-least one check box");
				}

			}
		});

	}

}
