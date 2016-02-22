//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
import rise.splcc.data.SubmissionUser;
import rise.splcc.data.User;
import rise.splcc.exception.AssignmentAlreadyInsertedException;
import rise.splcc.exception.AssignmentNotFoundException;
//#if ${InsertAuthors} == "T"
import rise.splcc.exception.AuthorAlreadyInsertedException;
import rise.splcc.exception.AuthorNotFoundException;
//#endif
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.ReviewAlreadyInsertedException;
import rise.splcc.exception.SubmissionAlreadyInsertedException;
import rise.splcc.exception.SubmissionNotFoundException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;
import rise.splcc.table.AssignmentTableModel;
import rise.splcc.table.ReviewerTableModel;
//#if ${ConflictofinterestAutomatic} == "T"
import rise.splcc.util.Conflict;
//#endif
import rise.splcc.util.Email;

public class AssignmentManagementScreenP extends JInternalFrame {

	
	private static AssignmentManagementScreenP instanceAssignmentManagementScreenP;
	private JPanel contentPane;
	private JTable table;
	
	private JTable tableReviewer;
	private JTable tableSelectReviewer;
	
	private JButton btnBack;
	//#if ${Assignmentautomatic} == "T"
	private JButton btnGenerate;
	//#endif
	private JTextField textFieldDate;
	private JTable table_1;
	
	private JComboBox comboBoxSubmission;
	
	private List<Reviewer> listaRevisoresSelecionados = new ArrayList<Reviewer>();
	
	private Submission submissionSelecionado = new Submission();
	
	private Assignment assignmentSelecionado = new Assignment();
	
	public static AssignmentManagementScreenP getInstanceAssignmentManagementScreenP() {
		if (instanceAssignmentManagementScreenP == null) {
			AssignmentManagementScreenP.instanceAssignmentManagementScreenP = new AssignmentManagementScreenP();
		}
		return AssignmentManagementScreenP.instanceAssignmentManagementScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignmentManagementScreenP frame = new AssignmentManagementScreenP();
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
	public AssignmentManagementScreenP() {
		setTitle("Assignment Management");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		SelectComboSubmissionAction selectSubmissionAction = new SelectComboSubmissionAction();
		RightButtonAction buttonInsertRigthAction = new RightButtonAction();
		LeftButtonAction buttonInsertLeftAction = new LeftButtonAction();
		InsertButtonAction insertAction = new InsertButtonAction(); 
		RemoveButtonAction removeAction = new RemoveButtonAction(); 
		SelectButtonAction selectAction = new SelectButtonAction(); 
		CleanButtonAction cleanAction = new CleanButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		//#if ${Assignmentautomatic} == "T"
		GenerateButtonAction generateAction = new GenerateButtonAction();
		//#endif
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 728, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(147, 17, 413, 72);
		contentPane.add(panel);
		
		JLabel label = new JLabel("");
		label.setBounds(6, 6, 399, 60);
		ImageIcon image = new ImageIcon(getClass().getResource("/images/riseLabs.png"));
		Image imag = image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(imag));
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 97, 680, 164);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(499, 12, 61, 16);
		panel_1.add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(540, 6, 134, 28);
		panel_1.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JLabel label_1 = new JLabel("Submission:");
		label_1.setBounds(6, 12, 88, 16);
		panel_1.add(label_1);
		
		comboBoxSubmission = new JComboBox();
		comboBoxSubmission.setBounds(84, 8, 403, 27);
		panel_1.add(comboBoxSubmission);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 47, 202, 111);
		panel_1.add(scrollPane_1);
		
		tableReviewer = new JTable();
		scrollPane_1.setViewportView(tableReviewer);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(424, 40, 228, 118);
		panel_1.add(scrollPane_2);
		
		JButton buttonInsert = new JButton("-->");
		buttonInsert.setBounds(263, 61, 117, 29);
		panel_1.add(buttonInsert);
		
		JButton buttonRemove = new JButton("<--");
		buttonRemove.setBounds(263, 102, 117, 29);
		panel_1.add(buttonRemove);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 309, 716, 143);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		tableSelectReviewer = new JTable();
		scrollPane_2.setViewportView(tableSelectReviewer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 689, 118);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(6, 273, 117, 29);
		contentPane.add(btnInsert);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(127, 273, 117, 29);
		contentPane.add(btnRemove);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(377, 273, 117, 29);
		contentPane.add(btnSelect);
		
		JButton btnClean = new JButton("Clean");
		btnClean.setBounds(494, 273, 117, 29);
		contentPane.add(btnClean);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(605, 273, 117, 29);
		contentPane.add(btnBack);
		
		JList list = new JList();
		list.setBounds(335, 106, 1, 1);
		getContentPane().add(list);
		
		//#if ${Assignmentautomatic} == "T"
		btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(248, 273, 117, 29);
		contentPane.add(btnGenerate);
		//#endif
		
		//PASSO 2
		btnInsert.addActionListener(insertAction);
		btnRemove.addActionListener(removeAction);
		btnSelect.addActionListener(selectAction);
		btnClean.addActionListener(cleanAction);
		btnBack.addActionListener(backAction);
		buttonInsert.addActionListener(buttonInsertRigthAction);
		buttonRemove.addActionListener(buttonInsertLeftAction);
		//#if ${Assignmentautomatic} == "T"
		btnGenerate.addActionListener(generateAction);
		//#endif
		
		populateTable();
		populateTableReviewer();
		carregarComboSubmission();
		comboBoxSubmission.addActionListener(selectSubmissionAction);
		
	}
	
	private void populateTable(){
		
		try {
			AssignmentTableModel model;
			model = new AssignmentTableModel(RiSEEventMainScreenP.facade.getAssignments());	
			table.setModel(model);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			AssignmentManagementScreenP.instanceAssignmentManagementScreenP = null;
		}
	}
	
	private class CleanButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			cleanFields();
		}
	}
	
	private void cleanFields(){
		comboBoxSubmission.setSelectedIndex(0);
		textFieldDate.setText("");
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
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				RiSEEventMainScreenP.facade.removeAssignment(assignmentSelecionado);
				JOptionPane.showMessageDialog(getContentPane(), "Remoção realizada com sucesso!!","Remoção",JOptionPane.INFORMATION_MESSAGE);
			} catch (AssignmentNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (AssignmentAlreadyInsertedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
		}
	}
	
	private class SelectButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = table.getSelectedRow();

			try {
				assignmentSelecionado =  new AssignmentTableModel(RiSEEventMainScreenP.facade.getAssignments()).get(rowIndex);
			} catch (RepositoryException e1) {
				// TODO Auto-generated catch block
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