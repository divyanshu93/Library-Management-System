import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Demo {
	
	public Demo(){
		JLabel l1 = new JLabel("this is card1");
		JLabel l2 = new JLabel("this is card2");
		JPanel card1 = new JPanel();
		card1.add(l1);
		JPanel card2 = new JPanel();
		card2.add(l2);
		JPanel panel = new JPanel();
		panel.setLayout(new CardLayout());
		panel.add("card1", card1);
		panel.add("card2", card2);
		panel.setVisible(false);
		JPanel panel2 = new JPanel();
		JButton b1 = new JButton("card1");
		b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panel.getLayout());
				cl.show(panel, e.getActionCommand());
				panel.setVisible(true);
				
				
			}
		});
		JButton b2 = new JButton("card2");
		b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(panel.getLayout());
				cl.show(panel, e.getActionCommand());
				panel.setVisible(true);
				
			}
		});
		panel2.add(b1);
		panel2.add(b2);
		JFrame frame = new JFrame("Demo");
		frame.setVisible(true);
		frame.setSize(400, 200);
		frame.setLayout(new BorderLayout());
		frame.getContentPane().add(panel2, BorderLayout.PAGE_START);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		
	}

	public static void main(String[] args) {
SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Demo();
			}
		});

	}

}
