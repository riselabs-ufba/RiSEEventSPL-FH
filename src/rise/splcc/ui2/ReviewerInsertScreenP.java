//#if ${Reviewer} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rise.splcc.data.Reviewer;
import rise.splcc.data.User;
import rise.splcc.data.User.TypeUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.ReviewerAlreadyInsertedException;
import rise.splcc.exception.UserAlreadyInsertedException;

public class ReviewerInsertScreenP extends JInternalFrame {

	
	private static ReviewerInsertScreenP instanceReviewerInsertScreenP;
	private JTextField textFieldIdReviewer;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;
	private JTextField textFieldName;
	private JComboBox comboBox;
	private JTextField textFieldBiography;
	
	public static ReviewerInsertScreenP getInstanceReviewerInsertScreenP() {
		if (instanceReviewerInsertScreenP == null) {
			ReviewerInsertScreenP.instanceReviewerInsertScreenP = new ReviewerInsertScreenP();
		}
		return ReviewerInsertScreenP.instanceReviewerInsertScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReviewerInsertScreenP frame = new ReviewerInsertScreenP();
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
	public ReviewerInsertScreenP() {
		setTitle("Insert Reviewer");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		InsertButtonAction insertAction = new InsertButtonAction();
		BackButtonAction backAction = new BackButtonAction(); 
		
		getContentPane().setLayout(null);
		
		JLabel lblReviewerId = new JLabel(" User Id:");
		lblReviewerId.setBounds(6, 36, 61, 16);
		getContentPane().add(lblReviewerId);
		
		textFieldIdReviewer = new JTextField();
		textFieldIdReviewer.setBounds(58, 30, 134, 28);
		getContentPane().add(textFieldIdReviewer);
		textFieldIdReviewer.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(194, 36, 76, 16);
		getContentPane().add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(263, 30, 134, 28);
		getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(409, 36, 61, 16);
		getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(454, 30, 252, 28);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(6, 94, 61, 16);
		getContentPane().add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(58, 88, 339, 28);
		getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblReviewerType = new JLabel("User Type:");
		lblReviewerType.setBounds(409, 94, 76, 16);
		getContentPane().add(lblReviewerType);
		
		comboBox = new JComboBox();
		comboBox.setBounds(479, 90, 134, 27);
		getContentPane().add(comboBox);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(345, 149, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(496, 149, 117, 29);
		getContentPane().add(btnBack);
		
		JLabel lblKnowledgeArea = new JLabel("Knowledge Area:");
		lblKnowledgeArea.setBounds(6, 194, 104, 16);
		getContentPane().add(lblKnowledgeArea);
		
		textFieldBiography = new JTextField();
		textFieldBiography.setBounds(122, 188, 223, 28);
		getContentPane().add(textFieldBiography);
		textFieldBiography.setColumns(10);
		
		btnInsert.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		
		TypeUser[] types = TypeUser.values();
		List<String> names = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			names.add(i, types[i].name());
			comboBox.addItem(types[i].name());
		}
		

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ReviewerInsertScreenP.instanceReviewerInsertScreenP = null;
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Reviewer reviewer = new Reviewer();
			User user = new User();
			
			int idUser =Integer.valueOf(textFieldIdReviewer.getText());
			String password = textFieldPassword.getText();
			String nameReviewer = textFieldName.getText();
			String email = textFieldEmail.getText();
			String typeUser = comboBox.getSelectedItem().toString();
			String knowledgeArea = textFieldBiography.getText();
			
			
			if (idUser == 0 || password.equals("") || nameReviewer.equals("")
					|| email.equals("") || knowledgeArea.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{				
					reviewer.setIdUser(idUser);
					reviewer.setPassword(password);
					reviewer.setTypeUser(TypeUser.valueOf(typeUser));
					reviewer.setNameUser(nameReviewer);
					reviewer.setEmail(email);
					reviewer.setKnowledgeArea(knowledgeArea);
					
//					user.setIdUser(idUser);
//					user.setPassword(password);
//					user.setTypeUser(TypeUser.valueOf(typeUser));
//					user.setNameUser(nameReviewer);
//					user.setEmail(email);
					
					try {
					//	RiSEEventMainScreenP.facade.insertUser(user);
						RiSEEventMainScreenP.facade.insertReviewer(reviewer);
					} catch (ReviewerAlreadyInsertedException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e1.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
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
	
}
//#endif