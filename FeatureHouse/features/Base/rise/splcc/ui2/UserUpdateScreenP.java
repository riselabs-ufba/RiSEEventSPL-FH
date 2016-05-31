
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import rise.splcc.data.User;
import rise.splcc.data.User.TypeUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;

public class UserUpdateScreenP extends JInternalFrame {

	
	
	private static UserUpdateScreenP instanceUserUpdateScreenP;
	private JTextField textFieldIdUser;
	private JTextField textFieldName;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;
	private JTextField textFieldFiliation;
	
	private JComboBox comboBox;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	
	public static UserUpdateScreenP getInstanceUserUpdateScreenP() {
		if (instanceUserUpdateScreenP == null) {
			UserUpdateScreenP.instanceUserUpdateScreenP = new UserUpdateScreenP();
		}
		return UserUpdateScreenP.instanceUserUpdateScreenP;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserUpdateScreenP frame = new UserUpdateScreenP();
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
	public UserUpdateScreenP() {
		
		 
		
		setTitle("Update User");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		UpdateButtonAction updateAction = new UpdateButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		getContentPane().setLayout(null);
		
		JLabel lblUserId = new JLabel("User Id:");
		lblUserId.setBounds(20, 38, 61, 16);
		getContentPane().add(lblUserId);
		
		textFieldIdUser = new JTextField();
		textFieldIdUser.setBounds(72, 32, 150, 28);
		getContentPane().add(textFieldIdUser);
		textFieldIdUser.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(20, 77, 61, 16);
		getContentPane().add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(60, 71, 162, 28);
		getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(20, 119, 73, 16);
		getContentPane().add(lblPassword);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setBounds(88, 113, 144, 28);
		getContentPane().add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(20, 162, 61, 16);
		getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(60, 156, 162, 28);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblUserType = new JLabel("User Type:");
		lblUserType.setBounds(20, 203, 73, 16);
		getContentPane().add(lblUserType);
		
		comboBox = new JComboBox();
		comboBox.setBounds(88, 199, 134, 27);
		getContentPane().add(comboBox);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(475, 64, 117, 29);
		getContentPane().add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(475, 105, 117, 29);
		getContentPane().add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(274, 50, 175, 218);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblFiliation = new JLabel("Filiation:");
		lblFiliation.setBounds(20, 287, 61, 16);
		getContentPane().add(lblFiliation);
		
		textFieldFiliation = new JTextField();
		textFieldFiliation.setBounds(88, 278, 134, 28);
		getContentPane().add(textFieldFiliation);
		textFieldFiliation.setColumns(10);
		
		TypeUser[] types = TypeUser.values();
		List<String> names = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			names.add(i, types[i].name());
			comboBox.addItem(types[i].name());
		}
		
		btnUpdate.addActionListener(updateAction);
		btnBack.addActionListener(backAction);

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			esvaziarCampos();
			dispose();
			UserUpdateScreenP.instanceUserUpdateScreenP = null;
		}
	}
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			User user = new User();
			
			int idUser = Integer.valueOf(textFieldIdUser.getText());
			String password = textFieldPassword.getText();
			String nameUser = textFieldName.getText();
			String email = textFieldEmail.getText();
			String typeUser = comboBox.getSelectedItem().toString();
			String filiation = textFieldFiliation.getText();
			
			user.setIdUser(idUser);
			user.setNameUser(nameUser);
			user.setPassword(password);
			user.setEmail(email);
			user.setTypeUser(TypeUser.valueOf(typeUser));
			user.setFiliation(filiation);

			try {
				RiSEEventMainScreenP.getFacade().updateUser(user);
				//user = RiSEEventMainScreenP.facade.searchUser(user.getIdUser());
				textArea.setText("");
				textArea.append(user.toString());
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
				
			
		}
	}
	
	private JTextArea getTextArea() {
		if (this.textArea == null) {
			this.textArea = new JTextArea("Dados:\n\n");
			textArea.setBounds(62, 68, 273, 142);
			textArea.setEditable(false);
		}
		return this.textArea;
	}
	
	private JScrollPane getScrollPanel() {
		if (this.scrollPane == null) {
			this.scrollPane = new JScrollPane(
					getTextArea());
			this.scrollPane.setBounds(62, 68, 273, 142);
		}
		return this.scrollPane;
	}
	
	private void esvaziarCampos() {
		//nametextField.setText("");
		textFieldIdUser.setText("");
		textFieldEmail.setText("");
		textFieldName.setText("");
		textFieldPassword.setText("");
		textFieldFiliation.setText("");
		getTextArea().setText("Dados:\n\n");
	}
}
