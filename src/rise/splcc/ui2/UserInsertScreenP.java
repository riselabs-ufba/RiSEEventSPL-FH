

package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;

import rise.splcc.data.User;
import rise.splcc.data.User.TypeUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.UserAlreadyInsertedException;

public class UserInsertScreenP extends JInternalFrame {

	
	private static UserInsertScreenP instanceUserInsertScreenP;
	private JTextField textFieldIdUser;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;
	private JTextField textFieldName;
	private JTextField textFieldFiliation;
	private JComboBox comboBox;
	
	public static UserInsertScreenP getInstanceUserInsertScreenP() {
		if (instanceUserInsertScreenP == null) {
			UserInsertScreenP.instanceUserInsertScreenP = new UserInsertScreenP();
		}
		return UserInsertScreenP.instanceUserInsertScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInsertScreenP frame = new UserInsertScreenP();
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
	public UserInsertScreenP() {
		setTitle("Insert User");
		
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
		
		JLabel lblUserId = new JLabel("User Id:");
		lblUserId.setBounds(6, 36, 61, 16);
		getContentPane().add(lblUserId);
		
		textFieldIdUser = new JTextField();
		textFieldIdUser.setBounds(58, 30, 134, 28);
		getContentPane().add(textFieldIdUser);
		textFieldIdUser.setColumns(10);
		
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
		
		JLabel lblUserType = new JLabel("User Type:");
		lblUserType.setBounds(409, 94, 76, 16);
		getContentPane().add(lblUserType);
		
		comboBox = new JComboBox();
		comboBox.setBounds(477, 90, 134, 27);
		getContentPane().add(comboBox);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(344, 200, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(494, 200, 117, 29);
		getContentPane().add(btnBack);
		
		JLabel lblFiliation = new JLabel("Filiation:");
		lblFiliation.setBounds(336, 154, 61, 16);
		getContentPane().add(lblFiliation);
		
		textFieldFiliation = new JTextField();
		textFieldFiliation.setBounds(409, 148, 202, 28);
		getContentPane().add(textFieldFiliation);
		textFieldFiliation.setColumns(10);
		
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
			UserInsertScreenP.instanceUserInsertScreenP = null;
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			User user = new User();
			
			int idUser =Integer.valueOf(textFieldIdUser.getText());
			String password = textFieldPassword.getText();
			String nameUser = textFieldName.getText();
			String email = textFieldEmail.getText();
			String typeUser = comboBox.getSelectedItem().toString();
			String filiation = textFieldFiliation.getText();
			
			
			if (idUser == 0 || password.equals("") || nameUser.equals("")
					|| email.equals("") || filiation.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{				
					user.setIdUser(idUser);
					user.setPassword(password);
					user.setTypeUser(TypeUser.valueOf(typeUser));
					user.setNameUser(nameUser);
					user.setEmail(email);
					user.setFiliation(filiation);
					try {
						RiSEEventMainScreenP.facade.insertUser(user);
						JOptionPane.showMessageDialog(getContentPane(),
								"Usuario cadastrado com Sucesso!!.", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (UserAlreadyInsertedException e1) {
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
