import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author div
 *
 */
public class Library_Project {

	public Library_Project() {
		/**
		 * setting panel and frame
		 *
		 */
		JPanel panel = new JPanel();// panel inside frame
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setBackground(Color.WHITE);
		JFrame frame = new JFrame("Library_Project");// frame
		frame.setSize(500, 500);// size of frame
		frame.setVisible(true);// visible true
		frame.setLayout(new BorderLayout());// layout of frame
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/**
		 * adding content to the panel
		 *
		 */
		JLabel welcome = new JLabel("Welcome to Library System");
		welcome.setFont(new Font("Arial", Font.BOLD, 24));
		JButton search = new JButton("Search/Find Book");
		JButton checkin = new JButton("Check In Book");
		JButton checkout = new JButton("Check Out Book");
		JButton borrower = new JButton("Create New Borrower");
		JButton fines = new JButton("Fines");
		panel.add(welcome);
		panel.add(search);
		panel.add(checkin);
		panel.add(checkout);
		panel.add(borrower);
		panel.add(fines);

		/**
		 * adding listeners to button
		 *
		 */
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Search();

			}
		});

		checkin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new checkin();

			}
		});

		checkout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new checkout();

			}
		});

		borrower.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new borrower();

			}
		});
		fines.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Fines();

			}
		});

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Library_Project();
			}
		});

		// DBConnector database = new DBConnector();
		// database.getData("Mark");

	}

}
