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
public class checkout {
	public checkout() {
		DBConnector database = new DBConnector();// object of class running
													// queries
		/**
		 * adding panel and frame
		 *
		 */
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		JFrame frame = new JFrame("Check Out");
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.getContentPane().add(panel);
		/**
		 * adding content to panel
		 *
		 */

		JLabel ISBN = new JLabel("Enter ISBN");
		JTextField isbnvalue = new JTextField(13);
		panel.add(ISBN);
		panel.add(isbnvalue);

		JLabel Branch_id = new JLabel("Enter Branch ID");
		JTextField branchvalue = new JTextField(13);
		panel.add(Branch_id);
		panel.add(branchvalue);

		JLabel Card_no = new JLabel("Enter Card Number");
		JTextField cardvalue = new JTextField(13);
		panel.add(Card_no);
		panel.add(cardvalue);

		JButton submit = new JButton("Submit");
		panel.add(submit);

		JTextArea output = new JTextArea();// resulting query print
		panel.add(output);

		/**
		 * adding action listener to print result
		 *
		 */
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// variables
				String isbn, branchid, cardno = "";
				String result = "";
				// get text from text field
				isbn = isbnvalue.getText();
				branchid = branchvalue.getText();
				cardno = cardvalue.getText();
				// check if any text field is empty
				if (isbnvalue.getText().length() == 0 || branchvalue.getText().length() == 0
						|| cardvalue.getText().length() == 0) {
					output.setText("Pls enter all the values");
				} else {// connect with database
					result = database.doCheckout(isbn, branchid, cardno);
					output.setText(result);

				}

			}
		});

	}

}
