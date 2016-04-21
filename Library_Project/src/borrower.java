import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author div
 *
 */
public class borrower {
	public borrower() {
		DBConnector database = new DBConnector();// object of class running
													// queries
		/**
		 * adding panel and frame
		 *
		 */
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		JFrame frame = new JFrame("Borrower");
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.getContentPane().add(panel);
		/**
		 * adding contents to the panel
		 *
		 */
		JLabel ssn = new JLabel("Enter SSN");
		JTextField ssnvalue = new JTextField(13);
		panel.add(ssn);
		panel.add(ssnvalue);

		JLabel fname = new JLabel("Enter First Name");
		JTextField fvalue = new JTextField(13);
		panel.add(fname);
		panel.add(fvalue);

		JLabel lname = new JLabel("Enter Last Name");
		JTextField lvalue = new JTextField(13);
		panel.add(lname);
		panel.add(lvalue);

		JLabel phone = new JLabel("Enter Phone Number");
		JTextField phonevalue = new JTextField(13);
		panel.add(phone);
		panel.add(phonevalue);

		JLabel address = new JLabel("Enter Address");
		JTextField addvalue = new JTextField(13);
		panel.add(address);
		panel.add(addvalue);

		JButton submit = new JButton("Create new Record");
		panel.add(submit);

		JTextArea output = new JTextArea();// print if input not correct
		panel.add(output);

		/**
		 * adding action listener to print result
		 *
		 */
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// variables
				String ssn, fname, lname, phone, address;
				String result = "";
				// get text from text field
				ssn = ssnvalue.getText();
				fname = fvalue.getText();
				lname = lvalue.getText();
				phone = phonevalue.getText();
				address = addvalue.getText();
				// check if any text field is empty
				if (ssn.length() == 0 || fname.length() == 0 || lname.length() == 0 || address.length() == 0) {
					output.setText("Pls enter all the values");
				} else {
					result = database.createRecord(ssn, fname, lname, phone, address);
					output.setText(result);
				}

			}
		});

	}

}
