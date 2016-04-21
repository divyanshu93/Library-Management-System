import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * @author div
 *
 */
public class checkin {

	/**
	 * adding table contents
	 *
	 */
	DefaultTableModel dm;
	JTable table;

	private void createColumn() {// declare column fields

		dm = (DefaultTableModel) table.getModel();
		dm.addColumn("Loan ID");
		dm.addColumn("ISBN");
		dm.addColumn("Branch ID");
		dm.addColumn("Card No");
		dm.addColumn("Date Out");
		dm.addColumn("Due Date");
		dm.addColumn("Date in");
	}

	private void addData(String loanid, String isbn, String branchid, String cardno, String dateout, String duedate,
			String datein) {// add row to the table
		String[] rowData = { loanid, isbn, branchid, cardno, dateout, duedate, datein };
		dm.addRow(rowData);
	}

	public checkin() {
		DBConnector database = new DBConnector();// object of class running
													// queries

		/**
		 * adding panel and frame
		 *
		 */
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		JFrame frame = new JFrame("Check In");
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setSize(500, 500);
		frame.getContentPane().add(panel);

		/**
		 * adding contents to the panel
		 *
		 */
		JLabel bookid = new JLabel("Enter Book ID");
		JTextField idvalue = new JTextField(13);
		panel.add(bookid);
		panel.add(idvalue);

		JLabel cardno = new JLabel("Enter Card No");
		JTextField cardvalue = new JTextField(13);
		panel.add(cardno);
		panel.add(cardvalue);

		JLabel name = new JLabel("Enter Borrower Name");
		JTextField namevalue = new JTextField(13);
		panel.add(name);
		panel.add(namevalue);

		JButton submit = new JButton("Submit");
		panel.add(submit);

		JTextArea output = new JTextArea();// print if input not correct
		panel.add(output);

		JButton checkin = new JButton("Check In");
		panel.add(checkin);

		JTextArea printStatus = new JTextArea();
		panel.add(printStatus);

		table = new JTable(dm);
		createColumn();

		table.setEditingColumn(7);
		for (int i = 0; i < table.getColumnCount() - 1; i++) {
			Class<?> col_class = table.getColumnClass(i);
			table.setDefaultEditor(col_class, null);
		}
		JScrollPane scroll = new JScrollPane(table);
		table.setBackground(Color.WHITE);
		panel.add(scroll);

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// variables
				String bookid, cardno, name = "";
				ArrayList<String> result = new ArrayList<>();
				// get text from text field
				bookid = idvalue.getText();
				cardno = cardvalue.getText();
				name = namevalue.getText();
				// check if all text fields are empty
				if (idvalue.getText().length() == 0 && cardvalue.getText().length() == 0
						&& namevalue.getText().length() == 0) {
					output.setText("Pls enter at least one value");
				} else {// connect with database
					result = database.getCheckinResult(bookid, cardno, name);
					for (String s : result) {
						String[] out = s.split(" ");
						addData(out[0], out[1], out[2], out[3], out[4], out[5], out[6]);
					}
				}

			}
		});

		checkin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// variables
				String bookid, cardno, name = "";
				int rowCount = 0;
				String loanid = "";
				String output = "";
				// get text from text field
				bookid = idvalue.getText();
				cardno = cardvalue.getText();
				name = namevalue.getText();
				String[] out;
				rowCount = table.getSelectedRow();
				ArrayList<String> result = new ArrayList<>();
				result = database.getCheckinResult(bookid, cardno, name);
				for (int i = 0; i < result.size(); i++) {
					if (i == rowCount) {
						out = result.get(i).split(" ");
					}
				}
				try {
					output = database.docheckin(loanid);
					printStatus.setText(output);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});

	}

}
