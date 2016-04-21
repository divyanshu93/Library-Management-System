import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
public class Search {

	public Search() {
		DBConnector database = new DBConnector();// object of class running
													// queries
		/**
		 * adding panel and frame
		 *
		 */
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		JFrame frame = new JFrame("Search");
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.getContentPane().add(panel);
		/**
		 * adding contents to the page
		 *
		 */
		JLabel ISBN = new JLabel("Enter ISBN");
		JTextField isbnvalue = new JTextField(13);
		panel.add(ISBN);
		panel.add(isbnvalue);

		JLabel title = new JLabel("Enter Book Title");
		JTextField titlevalue = new JTextField(25);
		panel.add(title);
		panel.add(titlevalue);

		JLabel author = new JLabel("Enter Author name");
		JTextField authorvalue = new JTextField(20);
		panel.add(author);
		panel.add(authorvalue);

		JButton submit = new JButton("Submit");
		panel.add(submit);

		JTextArea output = new JTextArea();
		panel.add(output);
		submit.addActionListener(new ActionListener() {// run query and print
														// result on clicking
														// submit

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> result = new ArrayList<>();// resulting set
				String isbn, title, author = null;
				isbn = isbnvalue.getText();
				title = titlevalue.getText();
				author = authorvalue.getText();
				// check if all text fields are empty
				if (isbnvalue.getText().length() == 0 && titlevalue.getText().length() == 0
						&& authorvalue.getText().length() == 0) {
					output.setText("Pls enter at least one value");
				} else {
					result = database.getResult(isbn, title, author);
					String out = "";
					for (String s : result) {
						out += s;
						out += "\n";
					}
					output.setText(out);

				}
				isbnvalue.setText("");
				titlevalue.setText("");
				authorvalue.setText("");

			}
		});

	}
	// run query and retrieve result

}
