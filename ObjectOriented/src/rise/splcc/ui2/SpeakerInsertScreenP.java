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
import javax.swing.JTextField;

import rise.splcc.data.Speaker;
import rise.splcc.data.User;
import rise.splcc.data.User.TypeUser;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.SpeakerAlreadyInsertedException;
import rise.splcc.exception.UserAlreadyInsertedException;

public class SpeakerInsertScreenP extends JInternalFrame {

	
	private static SpeakerInsertScreenP instanceSpeakerInsertScreenP;
	private JTextField textFieldIdSpeaker;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;
	private JTextField textFieldName;
	private JComboBox comboBox;
	private JTextField textFieldBiography;
	
	public static SpeakerInsertScreenP getInstanceSpeakerInsertScreenP() {
		if (instanceSpeakerInsertScreenP == null) {
			SpeakerInsertScreenP.instanceSpeakerInsertScreenP = new SpeakerInsertScreenP();
		}
		return SpeakerInsertScreenP.instanceSpeakerInsertScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpeakerInsertScreenP frame = new SpeakerInsertScreenP();
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
	public SpeakerInsertScreenP() {
		setTitle("Insert Speaker");
		
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
		
		JLabel lblSpeakerId = new JLabel(" User Id:");
		lblSpeakerId.setBounds(6, 36, 61, 16);
		getContentPane().add(lblSpeakerId);
		
		textFieldIdSpeaker = new JTextField();
		textFieldIdSpeaker.setBounds(58, 30, 134, 28);
		getContentPane().add(textFieldIdSpeaker);
		textFieldIdSpeaker.setColumns(10);
		
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
		
		JLabel lblSpeakerType = new JLabel("User Type:");
		lblSpeakerType.setBounds(409, 94, 76, 16);
		getContentPane().add(lblSpeakerType);
		
		comboBox = new JComboBox();
		comboBox.setBounds(479, 90, 134, 27);
		getContentPane().add(comboBox);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(345, 149, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(496, 149, 117, 29);
		getContentPane().add(btnBack);
		
		JLabel lblBiography = new JLabel("Biography:");
		lblBiography.setBounds(6, 197, 76, 16);
		getContentPane().add(lblBiography);
		
		textFieldBiography = new JTextField();
		textFieldBiography.setBounds(87, 191, 223, 28);
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
			SpeakerInsertScreenP.instanceSpeakerInsertScreenP = null;
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Speaker speaker = new Speaker();
			User user = new User();
			
			int idUser =Integer.valueOf(textFieldIdSpeaker.getText());
			String password = textFieldPassword.getText();
			String nameSpeaker = textFieldName.getText();
			String email = textFieldEmail.getText();
			String typeUser = comboBox.getSelectedItem().toString();
			String biography = textFieldBiography.getText();
			
			
			if (idUser == 0 || password.equals("") || nameSpeaker.equals("")
					|| email.equals("") || biography.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{				
					speaker.setIdUser(idUser);
					speaker.setPassword(password);
					speaker.setTypeUser(TypeUser.valueOf(typeUser));
					speaker.setNameUser(nameSpeaker);
					speaker.setEmail(email);
					speaker.setBiography(biography);
					
//					user.setIdUser(idUser);
//					user.setPassword(password);
//					user.setTypeUser(TypeUser.valueOf(typeUser));
//					user.setNameUser(nameSpeaker);
//					user.setEmail(email);
					
					try {
						//RiSEEventMainScreenP.facade.insertUser(user);
						RiSEEventMainScreenP.facade.insertSpeaker(speaker);
						JOptionPane.showMessageDialog(getContentPane(),
								"Speaker cadastrado com Sucesso!!.", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (SpeakerAlreadyInsertedException e1) {
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