//#if ${Speaker} == "T"
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rise.splcc.data.Speaker;
import rise.splcc.data.User;
import rise.splcc.data.User.TypeUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.SpeakerAlreadyInsertedException;
import rise.splcc.exception.SpeakerNotFoundException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;

public class SpeakerUpdateScreenP extends JInternalFrame {

	
	
	private static SpeakerUpdateScreenP instanceSpeakerUpdateScreenP;
	private JTextField textFieldIdSpeaker;
	private JTextField textFieldName;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;
	
	private JComboBox comboBox;
	private JLabel lblBiography;
	private JTextField textFieldBiography;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	public static SpeakerUpdateScreenP getInstanceSpeakerUpdateScreenP() {
		if (instanceSpeakerUpdateScreenP == null) {
			SpeakerUpdateScreenP.instanceSpeakerUpdateScreenP = new SpeakerUpdateScreenP();
		}
		return SpeakerUpdateScreenP.instanceSpeakerUpdateScreenP;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpeakerUpdateScreenP frame = new SpeakerUpdateScreenP();
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
	public SpeakerUpdateScreenP() {
		
		 
		
		setTitle("Update Speaker");
		
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
		
		JLabel lblSpeakerId = new JLabel("User Id:");
		lblSpeakerId.setBounds(20, 38, 61, 16);
		getContentPane().add(lblSpeakerId);
		
		textFieldIdSpeaker = new JTextField();
		textFieldIdSpeaker.setBounds(72, 32, 150, 28);
		getContentPane().add(textFieldIdSpeaker);
		textFieldIdSpeaker.setColumns(10);
		
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
		
		JLabel lblSpeakerType = new JLabel("User Type:");
		lblSpeakerType.setBounds(20, 203, 73, 16);
		getContentPane().add(lblSpeakerType);
		
		comboBox = new JComboBox();
		comboBox.setBounds(88, 199, 134, 27);
		getContentPane().add(comboBox);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(475, 64, 117, 29);
		getContentPane().add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(475, 105, 117, 29);
		getContentPane().add(btnBack);
		
		lblBiography = new JLabel("Biography:");
		lblBiography.setBounds(20, 282, 73, 16);
		getContentPane().add(lblBiography);
		
		textFieldBiography = new JTextField();
		textFieldBiography.setBounds(98, 278, 134, 28);
		getContentPane().add(textFieldBiography);
		textFieldBiography.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(267, 71, 185, 224);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
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
			SpeakerUpdateScreenP.instanceSpeakerUpdateScreenP = null;
			
		}
	}
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Speaker speaker = new Speaker();
			
			int idUser= Integer.valueOf(textFieldIdSpeaker.getText());
			String password = textFieldPassword.getText();
			String nameSpeaker = textFieldName.getText();
			String email = textFieldEmail.getText();
			String typeUser = comboBox.getSelectedItem().toString();
			String biography = textFieldBiography.getText();
			
			speaker.setIdUser(idUser);
			speaker.setNameUser(nameSpeaker);
			speaker.setPassword(password);
			speaker.setEmail(email);
			speaker.setTypeUser(TypeUser.valueOf(typeUser));
			speaker.setBiography(biography);

			try {
				//RiSEEventMainScreenP.facade.updateUser(speaker);
				RiSEEventMainScreenP.facade.updateSpeaker(speaker);
				textArea.setText("");
				//textArea.append(RiSEEventMainScreenP.facade.searchUser(idUser).toString() +   speaker.toString());
				textArea.append(speaker.toString());
			} catch (SpeakerNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (SpeakerAlreadyInsertedException e1) {
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
		textFieldIdSpeaker.setText("");
		textFieldEmail.setText("");
		textFieldName.setText("");
		textFieldPassword.setText("");
		getTextArea().setText("Dados:\n\n");
	}
	
}
//#endif