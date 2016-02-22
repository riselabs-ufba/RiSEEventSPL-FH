//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package rise.splcc.ui2;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.commons.mail.EmailException;

import rise.splcc.data.Assignment;
//#if ${InsertAuthors} == "T"
import rise.splcc.data.Author;
//#endif
import rise.splcc.data.Review;
import rise.splcc.data.Review.StatusReview;
import rise.splcc.data.Reviewer;
import rise.splcc.data.Submission;
//#if ${InsertAuthors} == "T"
import rise.splcc.data.SubmissionAuthor;
//#endif
//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
import rise.splcc.data.SubmissionUser;
//#endif
import rise.splcc.data.User;
import rise.splcc.exception.AssignmentAlreadyInsertedException;
//#if ${InsertAuthors} == "T"
import rise.splcc.exception.AuthorAlreadyInsertedException;
import rise.splcc.exception.AuthorNotFoundException;
//#endif
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.ReviewAlreadyInsertedException;
import rise.splcc.exception.ReviewerAlreadyInsertedException;
import rise.splcc.exception.ReviewerNotFoundException;
import rise.splcc.exception.SubmissionAlreadyInsertedException;
import rise.splcc.exception.SubmissionNotFoundException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;
import rise.splcc.table.ReviewerTableModel;
//#if ${ConflictofinterestAutomatic} == "T"
import rise.splcc.util.Conflict;
//#endif
import rise.splcc.util.Email;

public class AssignmentInsertScreenP extends JInternalFrame{

	private static AssignmentInsertScreenP instanceAssignmentInsertScreenP;
	private JTextField textFieldDate;
	private JComboBox comboBoxSubmission;
	
	private JTable tableReviewer;
	private JTable tableSelectReviewer;
	
	//#if ${Assignmentautomatic} == "T"
	private JButton btnGenerate;
	//#endif
	
	private List<Reviewer> listaRevisoresSelecionados = new ArrayList<Reviewer>();
	
	private Submission submissionSelecionado = new Submission();
	
	public static AssignmentInsertScreenP getInstanceAssignmentInsertScreenP() {
		if (instanceAssignmentInsertScreenP == null) {
			AssignmentInsertScreenP.instanceAssignmentInsertScreenP = new AssignmentInsertScreenP();
		}
		return AssignmentInsertScreenP.instanceAssignmentInsertScreenP;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignmentInsertScreenP frame = new AssignmentInsertScreenP();
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
	public AssignmentInsertScreenP() {
setTitle("Insert Assignment");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		SelectComboSubmissionAction selectSubmissionAction = new SelectComboSubmissionAction();
		InsertButtonAction insertAction = new InsertButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		RightButtonAction buttonInsertRigthAction = new RightButtonAction();
		LeftButtonAction buttonInsertLeftAction = new LeftButtonAction();
		//#if ${Assignmentautomatic} == "T"
		GenerateButtonAction generateAction = new GenerateButtonAction();
		//#endif
		JLabel lblSubmission = new JLabel("Submission:");
		lblSubmission.setBounds(6, 71, 80, 16);
		getContentPane().add(lblSubmission);
		
		comboBoxSubmission = new JComboBox();
		comboBoxSubmission.setBounds(86, 67, 606, 27);
		getContentPane().add(comboBoxSubmission);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(514, 23, 61, 16);
		getContentPane().add(lblDate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 133, 202, 246);
		getContentPane().add(scrollPane);
		
		tableReviewer = new JTable();
		scrollPane.setViewportView(tableReviewer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(427, 133, 228, 246);
		getContentPane().add(scrollPane_1);
		
		tableSelectReviewer = new JTable();
		scrollPane_1.setViewportView(tableSelectReviewer);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(558, 17, 134, 28);
		getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JButton buttonInsert = new JButton("-->");
		buttonInsert.setBounds(280, 184, 117, 29);
		getContentPane().add(buttonInsert);
		
		JButton buttonRemove = new JButton("<--");
		buttonRemove.setBounds(280, 225, 117, 29);
		getContentPane().add(buttonRemove);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(194, 399, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(366, 399, 117, 29);
		getContentPane().add(btnBack);
		
		JList list = new JList();
		list.setBounds(335, 106, 1, 1);
		getContentPane().add(list);
		
		//#if ${Assignmentautomatic} == "T"
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(280, 358, 117, 29);
		getContentPane().add(btnGenerate);
		//#endif
		
		btnInsert.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		buttonInsert.addActionListener(buttonInsertRigthAction);
		buttonRemove.addActionListener(buttonInsertLeftAction);
		//#if ${Assignmentautomatic} == "T"
		btnGenerate.addActionListener(generateAction);
		//#endif
		
		populateTableReviewer();
		carregarComboSubmission();
		comboBoxSubmission.addActionListener(selectSubmissionAction);
		
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			AssignmentInsertScreenP.instanceAssignmentInsertScreenP = null;
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Assignment assignment1;
			Assignment assignment2;
			Assignment assignment3;
			Reviewer reviewer1;
			Reviewer reviewer2;
			Reviewer reviewer3;
			Review review1;
			Review review2;
			Review review3;
			
			
			String idsubmission = comboBoxSubmission.getSelectedItem().toString();
			String date = textFieldDate.getText();
			
			
			if (date.equals("") || idsubmission.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			try {
				
				assignment1 = new Assignment();
				assignment2 = new Assignment();
				assignment3 = new Assignment();
				reviewer1 = new Reviewer();
				reviewer2 = new Reviewer();
				reviewer3 = new Reviewer();
				review1 = new Review();
				review2 = new Review();
				review3 = new Review();
				assignment1.setDate(date);
				assignment2.setDate(date);
				assignment3.setDate(date);
				
				int idSubmission;
				
				idSubmission = RiSEEventMainScreenP.facade.getSubmissionIdByTitle(idsubmission);
				
				Submission submission = RiSEEventMainScreenP.facade.searchSubmission(idSubmission);
				assignment1.setIdReviewSubmission(idSubmission);
				assignment2.setIdReviewSubmission(idSubmission);
				assignment3.setIdReviewSubmission(idSubmission);
				
				review1.setIdSubmission(idSubmission);
				review2.setIdSubmission(idSubmission);
				review3.setIdSubmission(idSubmission);
				
				review1.setStatus(StatusReview.valueOf("Aguardando"));
				review2.setStatus(StatusReview.valueOf("Aguardando"));
				review3.setStatus(StatusReview.valueOf("Aguardando"));
				
				review1.setRound(0);
				review2.setRound(0);
				review3.setRound(0);
				
				reviewer1 = listaRevisoresSelecionados.get(0);
				reviewer2 = listaRevisoresSelecionados.get(1);
				reviewer3 = listaRevisoresSelecionados.get(2);
				
				
				RiSEEventMainScreenP.facade.insertReview(review1);
				int lastIdReview1 = RiSEEventMainScreenP.facade.getReviewLastId();
				assignment1.setIdReview(lastIdReview1 - 1);
				review1.setIdReview(lastIdReview1);
				
				RiSEEventMainScreenP.facade.insertReview(review2);
				int lastIdReview2 = RiSEEventMainScreenP.facade.getReviewLastId();
				assignment2.setIdReview(lastIdReview2 - 1);
				review2.setIdReview(lastIdReview2);
				
				RiSEEventMainScreenP.facade.insertReview(review3);
				int lastIdReview3 = RiSEEventMainScreenP.facade.getReviewLastId();
				assignment3.setIdReview(lastIdReview3 - 1);
				review3.setIdReview(lastIdReview3);
				
				assignment1.setIdReviwerUser(reviewer1.getIdUser());
				assignment2.setIdReviwerUser(reviewer2.getIdUser());
				assignment3.setIdReviwerUser(reviewer3.getIdUser());
				
				RiSEEventMainScreenP.facade.insertAssignment(assignment1);
				RiSEEventMainScreenP.facade.insertAssignment(assignment2);
				RiSEEventMainScreenP.facade.insertAssignment(assignment3);
				
				//#if ${InsertAuthors} == "T"
				Author author = new Author();
				List<SubmissionAuthor> submissionAuthor = new ArrayList<SubmissionAuthor>();
				submissionAuthor = RiSEEventMainScreenP.facade.getSubmissionAuthors();
							
				for(SubmissionAuthor sa : submissionAuthor){
					if(sa.getIdSubmission() == idSubmission){
						author = RiSEEventMainScreenP.facade.searchAuthor(sa.getIdAuthor());
					}
				}
				//#endif
				User user = new User();
				//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
				List<SubmissionUser> submissionUser = new ArrayList<SubmissionUser>();
				submissionUser = RiSEEventMainScreenP.facade.getSubmissionUsers();
							
				for(SubmissionUser su : submissionUser){
					if(su.getIdSubmission() == idSubmission){
						user = RiSEEventMainScreenP.facade.searchUser(su.getIdUser());
					}
				}
				//#endif
				
				//#if ${ConflictofinterestAutomatic} == "T"
				Conflict conflict = new Conflict();
				boolean resultAutomaticConflict1 = conflict.automaticInterestConflict(author, user, reviewer1);
				boolean resultAutomaticConflict2 = conflict.automaticInterestConflict(author, user, reviewer2);
				boolean resultAutomaticConflict3 = conflict.automaticInterestConflict(author, user, reviewer3);
				//#endif
				
				//#if ${ConflictofinterestAutomatic} == "T"
				if(resultAutomaticConflict1 == true){
				JOptionPane.showMessageDialog(getContentPane(),
						"Essa atribuicao nao pode ser feita por conflito de interesses", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				}else{
				//#endif	
					
				//#if ${NotificationsDeadline} == "T" or ${NotificationsPaperAssignemnt} == "T" or ${NotificationsAceptanceRejection} == "T"
				enviarEmails(reviewer1, submission, review1);
				//#endif
				
				//#if ${ConflictofinterestAutomatic} == "T"
				}
				if(resultAutomaticConflict2 == true){
				JOptionPane.showMessageDialog(getContentPane(),
						"Essa atribuicao nao pode ser feita por conflito de interesses", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				}else{
				//#endif
				
				//#if ${NotificationsDeadline} == "T" or ${NotificationsPaperAssignemnt} == "T" or ${NotificationsAceptanceRejection} == "T"	
				enviarEmails(reviewer2, submission, review2);
				//#endif
				
				//#if ${ConflictofinterestAutomatic} == "T"
				}
				if(resultAutomaticConflict3 == true){
				JOptionPane.showMessageDialog(getContentPane(),
						"Essa atribuicao nao pode ser feita por conflito de interesses", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				}else{
				//#endif
				
				//#if ${NotificationsDeadline} == "T" or ${NotificationsPaperAssignemnt} == "T" or ${NotificationsAceptanceRejection} == "T"	
				enviarEmails(reviewer3, submission, review3);
				//#endif
				
				//#if ${ConflictofinterestAutomatic} == "T"
				}
				//#endif
				
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
			} catch (ReviewAlreadyInsertedException e1) {
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
			//#if ${InsertAuthors} == "T"
			catch (AuthorNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AuthorAlreadyInsertedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//#endif
			catch (UserNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UserAlreadyInsertedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			}
		}
	
	private void carregarComboSubmission(){
		try {
			List<Submission> submissions = RiSEEventMainScreenP.facade.getSubmissions();
			Iterator<Submission> iterator = submissions.iterator();
			while(iterator.hasNext()){
				comboBoxSubmission.addItem(iterator.next().getTitle());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private class SelectComboSubmissionAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				int idSubmissionSelecionado;
				
				idSubmissionSelecionado = RiSEEventMainScreenP.facade.getSubmissionIdByTitle(comboBoxSubmission.getSelectedItem().toString());
				submissionSelecionado = RiSEEventMainScreenP.facade.searchSubmission(idSubmissionSelecionado);
				
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
	
	private List<Reviewer> retornarReviewersSubmission(){
		List<Reviewer> reviewers = new ArrayList<Reviewer>();
		List<String> keyWords;
		Submission submissionSelecionado = null;
		int idSubmission;
		try {
			reviewers = RiSEEventMainScreenP.facade.getReviewers();
			
			idSubmission = RiSEEventMainScreenP.facade.getSubmissionIdByTitle(comboBoxSubmission.getSelectedItem().toString());
			submissionSelecionado = RiSEEventMainScreenP.facade.searchSubmission(idSubmission);
			keyWords = quebrarKeywords(submissionSelecionado);
			
			Iterator<String> iteratorKeywords = keyWords.iterator();
			while(iteratorKeywords.hasNext()){
				reviewers.add(RiSEEventMainScreenP.facade.getReviewerByknowledgeArea(iteratorKeywords.next()));
			}
		} catch (RepositoryException e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		} catch (SubmissionNotFoundException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (SubmissionAlreadyInsertedException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (ReviewerNotFoundException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (ReviewerAlreadyInsertedException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		return reviewers;
	}
	

	
	private List<String> quebrarKeywords(Submission submission){
		List<String> palavrasDaKeyword = new ArrayList<String>();
		String [] array = submission.getKeywords().split("[,] *");
		
		for(int i=0; i< array.length ; i++){
			palavrasDaKeyword.add(array[i]);
		}
		return palavrasDaKeyword;
	}
	//#if ${NotificationsDeadline} == "T" or ${NotificationsPaperAssignemnt} == "T" or ${NotificationsAceptanceRejection} == "T"
	public void enviarEmails(Reviewer reviewer, Submission submission, Review review){
		Email email = new Email();
		User user = new User();
		try {
			user = RiSEEventMainScreenP.facade.searchUser(reviewer.getIdUser());
		} catch (UserNotFoundException e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		} catch (RepositoryException e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		} catch (UserAlreadyInsertedException e1) {
			JOptionPane.showMessageDialog(getContentPane(),
					e1.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e1.printStackTrace();
		}
		
		try {
			RiSEEventMainScreenP.facade.emailNotification(user, review, email);
		} catch (EmailException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	//#endif

	private class LeftButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = tableSelectReviewer.getSelectedRow();
			
			listaRevisoresSelecionados.remove(rowIndex); 
			ReviewerTableModel model;
			model = new ReviewerTableModel(listaRevisoresSelecionados);
			tableSelectReviewer.setModel(model);
		}
	}
	
	private class RightButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = tableReviewer.getSelectedRow();
			Reviewer reviewerRight = null;
			
	//		Reviewer reviewerLeft = null;
			
			try {
				
				reviewerRight =  new ReviewerTableModel(RiSEEventMainScreenP.facade.getReviewers()).get(rowIndex);
				if(listaRevisoresSelecionados.size() < 3){
					listaRevisoresSelecionados.add(reviewerRight);
					ReviewerTableModel model;
					model = new ReviewerTableModel(listaRevisoresSelecionados);
					tableSelectReviewer.setModel(model);
				}else{
					JOptionPane.showMessageDialog(getContentPane(),
							"Apenas 3 Revisores podem ser selecionados!", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} 
			
		}
	}
	
	private void populateTableReviewer(){
		try {
			ReviewerTableModel model;
			model = new ReviewerTableModel(RiSEEventMainScreenP.facade.getReviewers());

			tableReviewer.setModel(model);
			
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	
	//#if ${Assignmentautomatic} == "T"
	private class GenerateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			String submissao = comboBoxSubmission.getSelectedItem().toString();
			List<Reviewer> reviewerList = new ArrayList<Reviewer>();
			if(submissao.equals("")){
				JOptionPane.showMessageDialog(getContentPane(),
						"Selecione uma Submissão", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
			}else{
				try {
					int subId = RiSEEventMainScreenP.facade.getSubmissionIdByTitle(submissao);
					Submission sub = RiSEEventMainScreenP.facade.searchSubmission(subId);
					String keywords = sub.getKeywords();
					String keywordsSplit[] = keywords.split(Pattern.quote(","));
					reviewerList = RiSEEventMainScreenP.facade.getReviewers();
					boolean flag;

					
					for(Reviewer r : reviewerList){
						flag = false;
						ReviewerTableModel model;
						String knowledgeAreaSplit[] = r.getKnowledgeArea().split(Pattern.quote(","));						
						for(String know : knowledgeAreaSplit){
							flag = false;
							for(String key : keywordsSplit){
								if(know.equals(key)){
									listaRevisoresSelecionados.add(r);
									model = new ReviewerTableModel(listaRevisoresSelecionados);
									tableSelectReviewer.setModel(model);
									flag = true;
									break;
								}
							}
							if(flag == true){
								break;
							}
						}
						if(listaRevisoresSelecionados.size() == 3){
							break;
						}
					}
					
					if(listaRevisoresSelecionados.size() < 3){
					
						if(listaRevisoresSelecionados.isEmpty()){
							int i = 0;
							ReviewerTableModel model;
							while(i<3){
								listaRevisoresSelecionados.add(reviewerList.get(i));
								model = new ReviewerTableModel(listaRevisoresSelecionados);
								tableSelectReviewer.setModel(model);
								i++;
							}
							
						}else{
							int i = listaRevisoresSelecionados.size();
							ReviewerTableModel model;
							while(i<3){
								listaRevisoresSelecionados.add(reviewerList.get(i));
								model = new ReviewerTableModel(listaRevisoresSelecionados);
								tableSelectReviewer.setModel(model);
								i++;
							}
							
						}
						
					}
					
					
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
	}
	//#endif
	
}
//#endif