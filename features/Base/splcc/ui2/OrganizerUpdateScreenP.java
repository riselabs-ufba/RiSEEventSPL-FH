//#if ${Organizer} == "T"

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

import rise.splcc.data.Activity;
import rise.splcc.data.Event;
import rise.splcc.data.Organizer;
import rise.splcc.data.User;
import rise.splcc.data.Organizer.TypeOrganizer;
import rise.splcc.data.User.TypeUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.OrganizerAlreadyInsertedException;
import rise.splcc.exception.OrganizerNotFoundException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;


public class OrganizerUpdateScreenP extends JInternalFrame {

	
	
	private static OrganizerUpdateScreenP instanceOrganizerUpdateScreenP;
	private JTextField textFieldIdOrganizer;
	private JTextField textFieldName;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;
	
	private JComboBox comboBox;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel lblFiliation;
	private JComboBox comboBox_1;
	private JLabel lblFiliation_1;
	private JTextField textFieldFiliation;
	
	public static OrganizerUpdateScreenP getInstanceOrganizerUpdateScreenP() {
		if (instanceOrganizerUpdateScreenP == null) {
			OrganizerUpdateScreenP.instanceOrganizerUpdateScreenP = new OrganizerUpdateScreenP();
		}
		return OrganizerUpdateScreenP.instanceOrganizerUpdateScreenP;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrganizerUpdateScreenP frame = new OrganizerUpdateScreenP();
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
	public OrganizerUpdateScreenP() {
		
		 
		
		setTitle("Update Organizer");
		
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
		
		JLabel lblOrganizerId = new JLabel("User Id:");
		lblOrganizerId.setBounds(20, 38, 61, 16);
		getContentPane().add(lblOrganizerId);
		
		textFieldIdOrganizer = new JTextField();
		textFieldIdOrganizer.setBounds(72, 32, 150, 28);
		getContentPane().add(textFieldIdOrganizer);
		textFieldIdOrganizer.setColumns(10);
		
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
		
		JLabel lblOrganizerType = new JLabel("User Type:");
		lblOrganizerType.setBounds(20, 203, 73, 16);
		getContentPane().add(lblOrganizerType);
		
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
		scrollPane.setBounds(271, 38, 192, 152);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		lblFiliation = new JLabel("Type Organizer:");
		lblFiliation.setBounds(20, 231, 108, 16);
		getContentPane().add(lblFiliation);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(163, 226, 134, 28);
		getContentPane().add(comboBox_1);
		
		lblFiliation_1 = new JLabel("Filiation:");
		lblFiliation_1.setBounds(20, 259, 61, 16);
		getContentPane().add(lblFiliation_1);
		
		textFieldFiliation = new JTextField();
		textFieldFiliation.setBounds(88, 253, 134, 28);
		getContentPane().add(textFieldFiliation);
		textFieldFiliation.setColumns(10);
		
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
			comboBox_1.addItem(types2[i].name());
		}
		
		btnUpdate.addActionListener(updateAction);
		btnBack.addActionListener(backAction);

		
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			esvaziarCampos();
			dispose();
			OrganizerUpdateScreenP.instanceOrganizerUpdateScreenP= null;
			
		}
	}
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Organizer organizer = new Organizer();
			
			int idUser= Integer.valueOf(textFieldIdOrganizer.getText());
			String password = textFieldPassword.getText();
			String nameOrganizer = textFieldName.getText();
			String email = textFieldEmail.getText();
			String typeUser = comboBox.getSelectedItem().toString();
			String typeOrganizer = comboBox_1.getSelectedItem().toString();
			String filiation = textFieldFiliation.getText();
			
			organizer.setIdUser(idUser);
			organizer.setNameUser(nameOrganizer);
			organizer.setPassword(password);
			organizer.setEmail(email);
			organizer.setTypeUser(TypeUser.valueOf(typeUser));
			organizer.setTypeOrganizer(TypeOrganizer.valueOf(typeOrganizer));
			organizer.setFiliation(filiation);
			

			try {
				//RiSEEventMainScreenP.facade.updateUser(organizer);
				RiSEEventMainScreenP.facade.updateOrganizer(organizer);
				//organizer = RiSEEventMainScreenP.facade.searchOrganizer(organizer.getIdUser());
				textArea.setText("");
				textArea.append(organizer.toString());
			} catch (OrganizerNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (OrganizerAlreadyInsertedException e1) {
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
		textFieldIdOrganizer.setText("");
		textFieldEmail.setText("");
		textFieldName.setText("");
		textFieldPassword.setText("");
		getTextArea().setText("Dados:\n\n");
	}
	
	
	
}
//#endif