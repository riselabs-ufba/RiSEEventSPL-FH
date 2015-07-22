//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rise.splcc.data.Submission;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.SubmissionAlreadyInsertedException;
import rise.splcc.exception.SubmissionNotFoundException;

public class ReviewResultsScreenP extends JInternalFrame {
	
	
	private static ReviewResultsScreenP instanceReviewResultsScreenP;
	private JTextField textFieldIdUser;
	
	JComboBox comboBoxSubmission;
	JLabel lblReview1; 
	JLabel lblReview2 ;
	JLabel lblReview3;
	
	JLabel lblAbs;
	
	public static ReviewResultsScreenP getInstanceReviewResultsScreenP() {
		if (instanceReviewResultsScreenP == null) {
			ReviewResultsScreenP.instanceReviewResultsScreenP = new ReviewResultsScreenP();
		}
		return ReviewResultsScreenP.instanceReviewResultsScreenP;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewResultsScreenP frame = new ReviewResultsScreenP();
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
	public ReviewResultsScreenP() {
		setTitle("Review Results");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		JLabel lblIdUser = new JLabel("Id User:");
		lblIdUser.setBounds(21, 21, 61, 16);
		getContentPane().add(lblIdUser);
		
		textFieldIdUser = new JTextField();
		textFieldIdUser.setBounds(76, 15, 134, 28);
		getContentPane().add(textFieldIdUser);
		textFieldIdUser.setColumns(10);
		
		JLabel lblSubmissionTitle = new JLabel("Submission Title:");
		lblSubmissionTitle.setBounds(21, 70, 114, 16);
		getContentPane().add(lblSubmissionTitle);
		
		comboBoxSubmission = new JComboBox();
		comboBoxSubmission.setBounds(147, 66, 473, 28);
		getContentPane().add(comboBoxSubmission);
		
		JLabel lblAbstract = new JLabel("Abstract:");
		lblAbstract.setBounds(21, 121, 61, 16);
		getContentPane().add(lblAbstract);
		
		JLabel lblReview = new JLabel("Review 1:");
		lblReview.setBounds(21, 242, 61, 16);
		getContentPane().add(lblReview);
		
		JLabel lblReview_1 = new JLabel("Review 2:");
		lblReview_1.setBounds(21, 315, 61, 16);
		getContentPane().add(lblReview_1);
		
		JLabel lblReview_2 = new JLabel("Review 3:");
		lblReview_2.setBounds(21, 372, 61, 16);
		getContentPane().add(lblReview_2);
		
		lblReview1 = new JLabel("");
		lblReview1.setBounds(90, 242, 530, 68);
		getContentPane().add(lblReview1);
		
		lblReview2 = new JLabel("");
		lblReview2.setBounds(88, 315, 532, 56);
		getContentPane().add(lblReview2);
		
		lblReview3 = new JLabel("");
		lblReview3.setBounds(86, 372, 534, 56);
		getContentPane().add(lblReview3);
		
		lblAbs = new JLabel("");
		lblAbs.setBounds(88, 121, 532, 105);
		getContentPane().add(lblAbs);
		
		JButton btnGetSubmissions = new JButton("Get Submissions");
		btnGetSubmissions.setBounds(224, 16, 134, 29);
		getContentPane().add(btnGetSubmissions);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(370, 16, 117, 29);
		getContentPane().add(btnBack);
		
		SelectComboSubmissionAction selectSubmissionAction = new SelectComboSubmissionAction();
		GetSubmissionsButtonAction generateAction = new GetSubmissionsButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		btnGetSubmissions.addActionListener(generateAction);
		btnBack.addActionListener(backAction);
		comboBoxSubmission.addActionListener(selectSubmissionAction);

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ReviewResultsScreenP.instanceReviewResultsScreenP = null;
		}
	}
	
	private class SelectComboSubmissionAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Submission submission = null;
			int idSubmission;
			try {
				idSubmission = RiSEEventMainScreenP.getFacade().getSubmissionIdByTitle(comboBoxSubmission.getSelectedItem().toString());
				submission = RiSEEventMainScreenP.getFacade().searchSubmission(idSubmission);
				lblAbs.setText(submission.getAbstractPaper());
				
				List<String> listOfReviews = new ArrayList<String>();
				listOfReviews = RiSEEventMainScreenP.getFacade().getReviewsBySubmission(idSubmission);
				
				if(listOfReviews.size() ==1)
				lblReview1.setText(listOfReviews.get(0));
				if(listOfReviews.size() ==2)
				lblReview2.setText(listOfReviews.get(1));
				if(listOfReviews.size() ==3)
				lblReview3.setText(listOfReviews.get(2));
				
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (SubmissionNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (SubmissionAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
			
		}
	}
	
	private class GetSubmissionsButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			int idUser = Integer.valueOf(textFieldIdUser.getText());
			
			
			List<Submission> list;
			try {
				list = RiSEEventMainScreenP.getFacade().getSubmissionsByUser(idUser);
				Iterator<Submission> iterator = list.iterator();
				while(iterator.hasNext()){
					comboBoxSubmission.addItem(iterator.next().getTitle().toString());
				}
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
			
		}
	}
	
}
//#endif