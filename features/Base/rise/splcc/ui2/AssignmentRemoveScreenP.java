//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rise.splcc.data.Assignment;
import rise.splcc.exception.AssignmentAlreadyInsertedException;
import rise.splcc.exception.AssignmentNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;

public class AssignmentRemoveScreenP extends JInternalFrame {

	
	
	private static AssignmentRemoveScreenP instanceAssignmentRemoveScreenP;
	private JTextField textFieldReviewerId;
	private JTextField textFieldReviewId;
	private JTextField textFieldSubmissionId;
	
	public static AssignmentRemoveScreenP getInstanceAssignmentRemoveScreenP() {
		if (instanceAssignmentRemoveScreenP == null) {
			AssignmentRemoveScreenP.instanceAssignmentRemoveScreenP = new AssignmentRemoveScreenP();
		}
		return AssignmentRemoveScreenP.instanceAssignmentRemoveScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignmentRemoveScreenP frame = new AssignmentRemoveScreenP();
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
	public AssignmentRemoveScreenP() {
		
		
		RemoveButtonAction removeAction = new RemoveButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		getContentPane().setLayout(null);
		
		JLabel lblReviewerId = new JLabel("Reviewer Id:");
		lblReviewerId.setBounds(27, 45, 78, 16);
		getContentPane().add(lblReviewerId);
		
		textFieldReviewerId = new JTextField();
		textFieldReviewerId.setBounds(117, 39, 134, 28);
		getContentPane().add(textFieldReviewerId);
		textFieldReviewerId.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(263, 17, 117, 29);
		getContentPane().add(btnRemove);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(263, 58, 117, 29);
		getContentPane().add(btnBack);
		
		JLabel lblReviewId = new JLabel("Review Id:");
		lblReviewId.setBounds(27, 89, 78, 16);
		getContentPane().add(lblReviewId);
		
		textFieldReviewId = new JTextField();
		textFieldReviewId.setBounds(117, 83, 134, 28);
		getContentPane().add(textFieldReviewId);
		textFieldReviewId.setColumns(10);
		
		JLabel lblSubmissionId = new JLabel("Submission Id:");
		lblSubmissionId.setBounds(27, 135, 93, 16);
		getContentPane().add(lblSubmissionId);
		
		textFieldSubmissionId = new JTextField();
		textFieldSubmissionId.setBounds(117, 129, 134, 28);
		getContentPane().add(textFieldSubmissionId);
		textFieldSubmissionId.setColumns(10);

		btnRemove.addActionListener(removeAction);
		btnBack.addActionListener(backAction);
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			AssignmentRemoveScreenP.instanceAssignmentRemoveScreenP = null;
			
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Assignment assignment = new Assignment();
			assignment.setIdReviwerUser(Integer.valueOf(textFieldReviewerId.getText()));
			assignment.setIdReview(Integer.valueOf(textFieldReviewId.getText()));
			assignment.setIdReviewSubmission(Integer.valueOf(textFieldSubmissionId.getText()));
			
			try {
				if (RiSEEventMainScreenP.facade.searchAssignment(assignment) == null){
					JOptionPane.showMessageDialog(getContentPane(),
							"Assignment não existe.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					RiSEEventMainScreenP.facade.removeAssignment(assignment);
					JOptionPane.showMessageDialog(getContentPane(), "Remoção realizada com sucesso!!","Remoção",JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
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
}
//#endif
