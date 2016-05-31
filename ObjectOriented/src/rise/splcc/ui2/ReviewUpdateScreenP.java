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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rise.splcc.data.Review;
import rise.splcc.data.Review.StatusReview;
import rise.splcc.data.Submission;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.ReviewAlreadyInsertedException;
import rise.splcc.exception.ReviewNotFoundException;

public class ReviewUpdateScreenP extends JInternalFrame  {

	
	private static ReviewUpdateScreenP instanceReviewUpdateScreenP;
	
	private JTextField textFieldDate;
	private JTextField textFieldDescription;
	
	JComboBox<String> statusComboBox;
	JComboBox<String> submissionComboBox;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	private JLabel lblLastId;
	
	public static ReviewUpdateScreenP getInstanceReviewUpdateScreenP() {
		if (instanceReviewUpdateScreenP == null) {
			ReviewUpdateScreenP.instanceReviewUpdateScreenP = new ReviewUpdateScreenP();
		}
		return ReviewUpdateScreenP.instanceReviewUpdateScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewUpdateScreenP frame = new ReviewUpdateScreenP();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ReviewUpdateScreenP() {
		setTitle("Update Review");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		UpdateButtonAction insertAction = new UpdateButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		JLabel lblReviewId = new JLabel("Review Id:");
		lblReviewId.setBounds(17, 25, 76, 16);
		getContentPane().add(lblReviewId);
		
		lblLastId = new JLabel("");
		lblLastId.setBounds(90, 25, 61, 16);
		getContentPane().add(lblLastId);
		
		JLabel lblSubmission = new JLabel("Submission (Title):");
		lblSubmission.setBounds(120, 25, 125, 16);
		getContentPane().add(lblSubmission);
		
		submissionComboBox = new JComboBox<String>();
		submissionComboBox.setBounds(245, 21, 298, 27);
		getContentPane().add(submissionComboBox);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(53, 89, 134, 28);
		getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
		
		statusComboBox = new JComboBox<String>();
		statusComboBox.setBounds(348, 91, 195, 27);
		getContentPane().add(statusComboBox);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(19, 160, 104, 16);
		getContentPane().add(lblDescription);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(108, 154, 561, 28);
		getContentPane().add(textFieldDescription);
		textFieldDescription.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(157, 237, 117, 29);
		getContentPane().add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(320, 237, 117, 29);
		getContentPane().add(btnBack);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(17, 95, 61, 16);
		getContentPane().add(lblDate);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(292, 95, 61, 16);
		getContentPane().add(lblStatus);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(459, 228, 204, 164);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnUpdate.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		
		carregarComboBoxSubmission();
		carregarComboBoxStatus();
		
		try {
			lblLastId.setText(String.valueOf(RiSEEventMainScreenP.facade.getReviewLastId()));
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ReviewUpdateScreenP.instanceReviewUpdateScreenP = null;
		}
	}
	
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Review review = new Review();
			
			int idReview = Integer.valueOf(lblLastId.toString());
			String date = textFieldDate.getText();
			String description = textFieldDate.getText();
			String titleSubmission = submissionComboBox.getSelectedItem().toString();
			String status = statusComboBox.getSelectedItem().toString();
			
			try {
				review = RiSEEventMainScreenP.facade.searchReview(idReview);
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
			
			try {
				review.setDate(date);
				review.setDescription(description);
				review.setStatus(StatusReview.valueOf(status));
				review.setIdSubmission(RiSEEventMainScreenP.facade.getSubmissionIdByTitle(titleSubmission));
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
			try {
				RiSEEventMainScreenP.facade.updateReview(review);
				review = RiSEEventMainScreenP.facade.searchReview(review.getIdReview());
				textArea.setText("");
				textArea.append(review.toString());
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
	
	private void carregarComboBoxSubmission(){
		try {
			List<Submission> list = RiSEEventMainScreenP.facade.getSubmissions();
			Iterator<Submission> iterator = list.iterator();
			while(iterator.hasNext()){
				submissionComboBox.addItem(iterator.next().getTitle());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void carregarComboBoxStatus(){
		StatusReview[] status = StatusReview.values();
		List<String> statusreviews = new ArrayList<String>();
		for(int i=0; i<status.length; i++){
			statusreviews.add(i, status[i].name());
			statusComboBox.addItem(status[i].name());
		}
	}
}
//#endif