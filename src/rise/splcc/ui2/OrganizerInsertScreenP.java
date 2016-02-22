//#if ${Organizer} == "T"
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

import rise.splcc.data.Organizer;
import rise.splcc.data.Organizer.TypeOrganizer;
import rise.splcc.data.User;
import rise.splcc.data.User.TypeUser;
import rise.splcc.exception.OrganizerAlreadyInsertedException;
import rise.splcc.exception.RepositoryException;

public class OrganizerInsertScreenP extends JInternalFrame {

	
	private static OrganizerInsertScreenP instanceOrganizerInsertScreenP;
	private JTextField textFieldIdOrganizer;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;
	private JTextField textFieldName;
	private JComboBox comboBox;
	private JComboBox comboBoxOrganizer;
	
	public static OrganizerInsertScreenP getInstanceOrganizerInsertScreenP() {
		if (instanceOrganizerInsertScreenP == null) {
			OrganizerInsertScreenP.instanceOrganizerInsertScreenP = new OrganizerInsertScreenP();
		}
		return OrganizerInsertScreenP.instanceOrganizerInsertScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrganizerInsertScreenP frame = new OrganizerInsertScreenP();
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
	public OrganizerInsertScreenP() {
		setTitle("Insert Organizer");
		
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
		
		JLabel lblOrganizerId = new JLabel(" User Id:");
		lblOrganizerId.setBounds(6, 36, 61, 16);
		getContentPane().add(lblOrganizerId);
		
		textFieldIdOrganizer = new JTextField();
		textFieldIdOrganizer.setBounds(58, 30, 134, 28);
		getContentPane().add(textFieldIdOrganizer);
		textFieldIdOrganizer.setColumns(10);
		
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
		
		JLabel lblOrganizerType = new JLabel("User Type:");
		lblOrganizerType.setBounds(409, 94, 76, 16);
		getContentPane().add(lblOrganizerType);
		
		comboBox = new JComboBox();
		comboBox.setBounds(479, 90, 134, 27);
		getContentPane().add(comboBox);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(345, 136, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(496, 136, 117, 29);
		getContentPane().add(btnBack);
		
		JLabel lblOrganizerType_1 = new JLabel("Organizer Type:");
		lblOrganizerType_1.setBounds(6, 128, 107, 16);
		getContentPane().add(lblOrganizerType_1);
		
		comboBoxOrganizer = new JComboBox();
		comboBoxOrganizer.setBounds(125, 124, 178, 28);
		getContentPane().add(comboBoxOrganizer);
		
		btnInsert.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		
		TypeUser[] types = TypeUser.values();
		List<String> names = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			names.add(i, types[i].name());
			comboBox.addItem(types[i].name());
		}
		
		TypeOrganizer[] types2 = TypeOrganizer.values();
		List<String> names2 = new ArrayList<String>();
		for(int i=0; i<types2.length; i++){
			names2.add(i, types2[i].name());
			comboBoxOrganizer.addItem(types2[i].name());
		}
		

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			OrganizerInsertScreenP.instanceOrganizerInsertScreenP = null;
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Organizer organizer = new Organizer();
			User user = new User();
			
			int idUser =Integer.valueOf(textFieldIdOrganizer.getText());
			String password = textFieldPassword.getText();
			String nameOrganizer = textFieldName.getText();
			String email = textFieldEmail.getText();
			String typeUser = comboBox.getSelectedItem().toString();
			String typeOrganizer = comboBoxOrganizer.getSelectedItem().toString();
			
			
			if (idUser == 0 || password.equals("") || nameOrganizer.equals("")
					|| email.equals("") ) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{				
					organizer.setIdUser(idUser);
					organizer.setPassword(password);
					organizer.setTypeUser(TypeUser.valueOf(typeUser));
					organizer.setNameUser(nameOrganizer);
					organizer.setEmail(email);
					organizer.setTypeOrganizer(TypeOrganizer.valueOf(typeOrganizer));
					
//					user.setIdUser(idUser);
//					user.setPassword(password);
//					user.setTypeUser(TypeUser.valueOf(typeUser));
//					user.setNameUser(nameOrganizer);
//					user.setEmail(email);
					
					try {
				//		RiSEEventMainScreenP.facade.insertUser(user);
						RiSEEventMainScreenP.getFacade().insertOrganizer(organizer);
					} catch (OrganizerAlreadyInsertedException e1) {
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
