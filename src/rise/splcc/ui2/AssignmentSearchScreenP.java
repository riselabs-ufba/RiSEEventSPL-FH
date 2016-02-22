//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rise.splcc.data.Assignment;
import rise.splcc.exception.AssignmentAlreadyInsertedException;
import rise.splcc.exception.AssignmentNotFoundException;
import rise.splcc.exception.RepositoryException;

public class AssignmentSearchScreenP extends JInternalFrame {

	private static AssignmentSearchScreenP instanceAssignmentSearchScreenP;
	private JTextField textFieldIdUser;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextField textFieldIdReview;
	private JTextField textFieldIdSubmission;
	public static AssignmentSearchScreenP getInstanceAssignmentSearchScreenP() {
		if (instanceAssignmentSearchScreenP == null) {
			AssignmentSearchScreenP.instanceAssignmentSearchScreenP = new AssignmentSearchScreenP();
		}
		return AssignmentSearchScreenP.instanceAssignmentSearchScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignmentSearchScreenP frame = new AssignmentSearchScreenP();
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
	public AssignmentSearchScreenP() {
		setTitle("Search Assignment");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		SearchButtonAction searchAction = new SearchButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		getContentPane().setLayout(null);
		
		JLabel lblUserId = new JLabel("User Id:");
		lblUserId.setBounds(48, 34, 101, 16);
		getContentPane().add(lblUserId);
		
		textFieldIdUser = new JTextField();
		textFieldIdUser.setBounds(101, 28, 134, 28);
		getContentPane().add(textFieldIdUser);
		textFieldIdUser.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 57, 182, 211);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(458, 29, 117, 29);
		getContentPane().add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(458, 70, 117, 29);
		getContentPane().add(btnBack);
		
		JLabel lblReviewId = new JLabel("Review Id:");
		lblReviewId.setBounds(29, 86, 75, 16);
		getContentPane().add(lblReviewId);
		
		JLabel lblSubmissionId = new JLabel("Submission Id:");
		lblSubmissionId.setBounds(6, 137, 101, 16);
		getContentPane().add(lblSubmissionId);
		
		textFieldIdReview = new JTextField();
		textFieldIdReview.setBounds(101, 80, 134, 28);
		getContentPane().add(textFieldIdReview);
		textFieldIdReview.setColumns(10);
		
		textFieldIdSubmission = new JTextField();
		textFieldIdSubmission.setBounds(101, 131, 134, 28);
		getContentPane().add(textFieldIdSubmission);
		textFieldIdSubmission.setColumns(10);
		
		btnSearch.addActionListener(searchAction);
		btnBack.addActionListener(backAction);


	}

	
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			AssignmentSearchScreenP.instanceAssignmentSearchScreenP = null;
			
		}
	}
	
	private class SearchButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Assignment assignment = new Assignment();
			
			assignment.setIdReviwerUser(Integer.valueOf( textFieldIdUser.getText()));
			assignment.setIdReview(Integer.valueOf( textFieldIdReview.getText()));
			assignment.setIdReviewSubmission(Integer.valueOf( textFieldIdSubmission.getText()));
			
			try {
				assignment = RiSEEventMainScreenP.facade.searchAssignment(assignment);
				textArea.setText("");
				textArea.append(assignment.toString());
			} catch (AssignmentNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (AssignmentAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
			
		}
		
	}
	
	private JTextArea getTextArea() {
		if (this.textArea == null) {
			this.textArea = new JTextArea("Dados:\n\n");
			textArea.setBounds(232, 69, 233, 196);
			textArea.setEditable(false);
		}
		return this.textArea;
	}
	
	private JScrollPane getScrollPanel() {
		if (this.scrollPane == null) {
			this.scrollPane = new JScrollPane(
					getTextArea());
			this.scrollPane.setBounds(232, 69, 233, 196);
		}
		return this.scrollPane;
	}
}
//#endif