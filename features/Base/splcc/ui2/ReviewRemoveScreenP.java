//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
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

import rise.splcc.exception.ReviewAlreadyInsertedException;
import rise.splcc.exception.ReviewNotFoundException;
import rise.splcc.exception.RepositoryException;

public class ReviewRemoveScreenP extends JInternalFrame {

	private static ReviewRemoveScreenP instanceReviewRemoveScreenP;
	private JTextField textFieldReviewId;
	
	public static ReviewRemoveScreenP getInstanceReviewRemoveScreenP() {
		if (instanceReviewRemoveScreenP == null) {
			ReviewRemoveScreenP.instanceReviewRemoveScreenP = new ReviewRemoveScreenP();
		}
		return ReviewRemoveScreenP.instanceReviewRemoveScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewRemoveScreenP frame = new ReviewRemoveScreenP();
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
	public ReviewRemoveScreenP() {
		setTitle("Remove Review");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		RemoveButtonAction RemoveAction = new RemoveButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		JLabel lblReviewid = new JLabel("ReviewId:");
		lblReviewid.setBounds(23, 38, 73, 16);
		getContentPane().add(lblReviewid);
		
		textFieldReviewId = new JTextField();
		textFieldReviewId.setBounds(109, 32, 134, 28);
		getContentPane().add(textFieldReviewId);
		textFieldReviewId.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(334, 32, 117, 29);
		getContentPane().add(btnRemove);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(334, 73, 117, 29);
		getContentPane().add(btnBack);
		
		btnRemove.addActionListener(RemoveAction);
		btnBack.addActionListener(backAction);
		
		
	}

	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ReviewRemoveScreenP.instanceReviewRemoveScreenP = null;
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int idReview = Integer.valueOf(textFieldReviewId.getText());
			
			try {
				if (RiSEEventMainScreenP.facade.searchReview(idReview) == null){
					JOptionPane.showMessageDialog(getContentPane(),
							"Review não existe.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					RiSEEventMainScreenP.facade.removeReview(idReview);
					JOptionPane.showMessageDialog(getContentPane(), "Remoção realizada com sucesso!!","Remoção",JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ReviewNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ReviewAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
				
		}

	}
	
}
//#endif