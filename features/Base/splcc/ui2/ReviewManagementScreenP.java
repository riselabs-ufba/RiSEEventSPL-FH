//#if ${ReviewRoundofReview} == "T" or ${ReviewSimpleReview} == "T"
package rise.splcc.ui2;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rise.splcc.data.Review;
import rise.splcc.data.Review.StatusReview;
import rise.splcc.data.Submission;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.ReviewAlreadyInsertedException;
import rise.splcc.exception.ReviewNotFoundException;
import rise.splcc.table.ReviewTableModel;

public class ReviewManagementScreenP extends JInternalFrame{

	private static ReviewManagementScreenP instanceReviewManagementScreenP;
	private JTextField textFieldDate;
	private JTextField textFieldDescription;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	
	private JButton btnBack;
	
	JComboBox<String> submissionIdcomboBox;
	JComboBox<String> statusComboBox;
	
	JButton btnInsert;
	JButton btnRemove;
	JButton btnUpdate;
	JButton btnSelect;
	JButton btnClean;
	
	JLabel lblLastReviewId;
	private JTextField textFieldReviewId;
	

	 public static ReviewManagementScreenP getInstanceReviewManagementScreenP() {
		 if (instanceReviewManagementScreenP == null) {
			 ReviewManagementScreenP.instanceReviewManagementScreenP = new ReviewManagementScreenP();
		 }
		 return ReviewManagementScreenP.instanceReviewManagementScreenP;
	 }
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewManagementScreenP frame = new ReviewManagementScreenP();
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
	public ReviewManagementScreenP() {
		
		InsertButtonAction insertAction = new InsertButtonAction(); 
		RemoveButtonAction removeAction = new RemoveButtonAction(); 
		UpdateButtonAction updateAction = new UpdateButtonAction();
		SelectButtonAction selectAction = new SelectButtonAction(); 
		CleanButtonAction cleanAction = new CleanButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		setTitle("Review Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(220, 18, 392, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblImage = new JLabel("");
		lblImage.setBounds(6, 6, 380, 56);
		ImageIcon image = new ImageIcon(getClass().getResource("/images/riseLabs.png"));
		Image imag = image.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(imag));
		panel.add(lblImage);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 99, 789, 189);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblReviewId = new JLabel("Review Id:");
		lblReviewId.setBounds(6, 6, 69, 16);
		panel_1.add(lblReviewId);
		
		lblLastReviewId = new JLabel("");
		lblLastReviewId.setBounds(79, 6, 61, 16);
		panel_1.add(lblLastReviewId);
		
		JLabel lblSubmissionId = new JLabel("Submission Id:");
		lblSubmissionId.setBounds(6, 49, 104, 16);
		panel_1.add(lblSubmissionId);
		
		submissionIdcomboBox = new JComboBox<String>();
		submissionIdcomboBox.setBounds(102, 45, 84, 27);
		panel_1.add(submissionIdcomboBox);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(205, 49, 61, 16);
		panel_1.add(lblStatus);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(420, 49, 61, 16);
		panel_1.add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(465, 43, 134, 28);
		panel_1.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		statusComboBox = new JComboBox<String>();
		statusComboBox.setBounds(262, 45, 134, 27);
		panel_1.add(statusComboBox);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(6, 126, 104, 16);
		panel_1.add(lblDescription);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(95, 120, 504, 28);
		panel_1.add(textFieldDescription);
		textFieldDescription.setColumns(10);
		
		textFieldReviewId = new JTextField();
		textFieldReviewId.setBounds(74, 0, 134, 28);
		panel_1.add(textFieldReviewId);
		textFieldReviewId.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 327, 829, 158);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 800, 121);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(26, 296, 117, 29);
		contentPane.add(btnInsert);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(166, 296, 117, 29);
		contentPane.add(btnRemove);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(305, 296, 117, 29);
		contentPane.add(btnUpdate);
		
		btnSelect = new JButton("Selection");
		btnSelect.setBounds(441, 296, 117, 29);
		contentPane.add(btnSelect);
		
		btnClean = new JButton("Clean");
		btnClean.setBounds(570, 296, 117, 29);
		contentPane.add(btnClean);
		
		btnInsert.addActionListener(insertAction);
		btnRemove.addActionListener(removeAction);
		btnUpdate.addActionListener(updateAction);
		btnSelect.addActionListener(selectAction);
		btnClean.addActionListener(cleanAction);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(694, 296, 117, 29);
		getContentPane().add(btnBack);
		
		btnBack.addActionListener(backAction);

		
		carregarComboBoxStatus();
		carregarComboBoxIdSubmission();
		
		populateTable();
		
		
	}
	
	private void carregarComboBoxStatus(){
		StatusReview[] status = StatusReview.values();
		List<String> statusreviews = new ArrayList<String>();
		for(int i=0; i<status.length; i++){
			statusreviews.add(i, status[i].name());
			statusComboBox.addItem(status[i].name());
		}
	}
	
	private void carregarComboBoxIdSubmission(){
		List<Submission> submissions = new ArrayList<Submission>();
		try {
			submissions = RiSEEventMainScreenP.facade.getSubmissions();
		} catch (RepositoryException ex) {
			JOptionPane.showMessageDialog(getContentPane(),
					ex.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			ex.printStackTrace();
		}
		
		for(Submission submission : submissions){
			submissionIdcomboBox.addItem(String.valueOf(submission.getIdSubmission()));
		}
		
	}
	
	
	private void populateTable(){
		try {
			ReviewTableModel model;
			model = new ReviewTableModel(RiSEEventMainScreenP.facade.getReviews());
			table.setModel(model);
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void cleanFields() {
		textFieldDate.setText("");
		textFieldDescription.setText("");
		btnInsert.setEnabled(true);
	}
	
	
	//INSERINDO UMA REVIEW
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			
			Review review = null;
			
			Integer submissionId = Integer.parseInt(submissionIdcomboBox.getSelectedItem().toString());
			StatusReview status = StatusReview.valueOf(statusComboBox.getSelectedItem().toString());
			String date = textFieldDate.getText();
			String description  = textFieldDescription.getText();
			
			//int resultado = 0;
			if (submissionId.equals("") || status.equals("") || date.equals("")
					|| description.equals("") ) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {					
				try {
					
					review = new Review();
					review.setIdSubmission(submissionId);
					review.setDate(date);
					review.setDescription(description);
					review.setStatus(status);
					
					//Atualizar JTable
					ReviewTableModel model = new ReviewTableModel(RiSEEventMainScreenP.facade.getReviews());
					
					RiSEEventMainScreenP.facade.insertReview(review); //isso obriga que o programa seja executado a partir da tela main screen, caso ele seja iniciado da tela de login ficaria RISEEVENTLOGINSCREEN.facade....
				

							//(ReviewTableModel) table.getModel();
					model.addReview(review);
					table.setModel(model);

					// Limpar campos
					cleanFields();

				} catch (ReviewAlreadyInsertedException e1) {
					JOptionPane
					.showMessageDialog(
							getContentPane(),
							"Já existe uma revisao cadastrada com esse Registro!",
							"Revisao Existente",
							JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				} catch (RepositoryException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
				
				
			}

			
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = table.getSelectedRow();
			
			if(rowIndex == -1){
				JOptionPane.showMessageDialog(getContentPane(),
						"Selecione a Revisao a ser removida!");
				return;
			}
			
			try {
				Review review = new ReviewTableModel(RiSEEventMainScreenP.facade.getReviews()).get(rowIndex);
				RiSEEventMainScreenP.facade.removeReview(review.getIdReview());
				ReviewTableModel model = (ReviewTableModel) table.getModel();
				model.removeReview(rowIndex);
				table.setModel(model);
				
				cleanFields();

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
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
		int rowIndex = table.getSelectedRow();
			
			
			try {

				
				Integer submissionId = Integer.parseInt(submissionIdcomboBox.getSelectedItem().toString());
				StatusReview status = StatusReview.valueOf(statusComboBox.getSelectedItem().toString());
				String date = textFieldDate.getText();
				String description  = textFieldDescription.getText();
				
				if (submissionId.equals("") || status.equals("") || date.equals("")
						|| description.equals("") ) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Não pode haver campo vazio.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;

				} else {
					
					Review reviewNew = new Review();
					reviewNew.setIdSubmission(submissionId);
					reviewNew.setDate(date);
					reviewNew.setDescription(description);
					reviewNew.setStatus(status);
					
					try {
						RiSEEventMainScreenP.facade.updateReview(reviewNew);
						ReviewTableModel model;
						model = new ReviewTableModel(RiSEEventMainScreenP.facade.getReviews());
						table.setModel(model);
					} catch (ReviewNotFoundException e1) {
						JOptionPane
						.showMessageDialog(
								getContentPane(),
								"Revisao que está tentando alterar não existe!",
								"Revisao Inexistente",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (ReviewAlreadyInsertedException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e1.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}
					
				}
				
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
			
		}
	}
	
	private class SelectButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			int rowIndex = table.getSelectedRow();
			Review reviewOld = null;

			try {
				reviewOld=  new ReviewTableModel(RiSEEventMainScreenP.facade.getReviews()).get(rowIndex);
			
				lblLastReviewId.setText(String.valueOf(reviewOld.getIdReview()));
				submissionIdcomboBox.setSelectedItem(reviewOld.getIdSubmission());
				statusComboBox.setSelectedItem(reviewOld.getStatus());
				textFieldDate.setText(reviewOld.getDate());
				textFieldDescription.setText(reviewOld.getDescription());
				
			} catch (RepositoryException ex) {
				JOptionPane.showMessageDialog(getContentPane(),
						ex.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				ex.printStackTrace();
			}
			
		}
	}
	
	private class CleanButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
		cleanFields();
			
		}
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ReviewManagementScreenP.instanceReviewManagementScreenP = null;
		}
	}
	
}
//#endif