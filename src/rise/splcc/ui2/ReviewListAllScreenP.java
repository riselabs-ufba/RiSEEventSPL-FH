//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import rise.splcc.exception.RepositoryException;
import rise.splcc.table.ReviewTableModel;

public class ReviewListAllScreenP extends JInternalFrame {

	
	private static ReviewListAllScreenP instanceReviewListAllScreenP;
	
	private ReviewTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	
	public static ReviewListAllScreenP getInstanceReviewListAllScreenP() {
		if (instanceReviewListAllScreenP == null) {
			ReviewListAllScreenP.instanceReviewListAllScreenP = new ReviewListAllScreenP();
		}
		return ReviewListAllScreenP.instanceReviewListAllScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewListAllScreenP frame = new ReviewListAllScreenP();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReviewListAllScreenP() {
		setTitle("List All Review");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		try {
			model = new ReviewTableModel(RiSEEventMainScreenP.facade.getReviews());
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
		
		table = new JTable(model);
	    scrollPane = new JScrollPane(table);
		scrollPane.setBounds(32, 46, 643, 134);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		

	}
}
//#endif