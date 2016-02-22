//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rise.splcc.data.CheckingCopy;
import rise.splcc.data.CheckingCopy.TypeCheckingCopy;
import rise.splcc.data.Registration;
import rise.splcc.data.User;
import rise.splcc.exception.CheckingCopyAlreadyInsertedException;
import rise.splcc.exception.RegistrationAlreadyInsertedException;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;

public class CheckingCopyInsertScreenP extends JInternalFrame {

	private static CheckingCopyInsertScreenP instanceCheckingCopyInsertScreenP;
	private JTextField textFieldDate;
	private JComboBox comboBoxCheckingCopyType;
	private JComboBox comboBoxUserId;
	private JComboBox comboBoxRegistrationId;
	
	
	
	
	public static CheckingCopyInsertScreenP getInstanceCheckingCopyInsertScreenP() {
		if (instanceCheckingCopyInsertScreenP == null) {
			CheckingCopyInsertScreenP.instanceCheckingCopyInsertScreenP = new CheckingCopyInsertScreenP();
		}
		return CheckingCopyInsertScreenP.instanceCheckingCopyInsertScreenP;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckingCopyInsertScreenP frame = new CheckingCopyInsertScreenP();
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
	public CheckingCopyInsertScreenP() {
		setTitle("Insert CheckingCopy");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		InsertButtonAction insertAction = new InsertButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		JLabel lblCheckingCopyId = new JLabel("CheckingCopy Id:");
		lblCheckingCopyId.setBounds(17, 25, 126, 16);
		getContentPane().add(lblCheckingCopyId);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(137, 25, 61, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblRegistration = new JLabel("Registration:");
		lblRegistration.setBounds(155, 25, 87, 16);
		getContentPane().add(lblRegistration);
		
		JLabel lblCheckingCopyType = new JLabel("CheckingCopy Type:");
		lblCheckingCopyType.setBounds(17, 113, 153, 16);
		getContentPane().add(lblCheckingCopyType);
		
		comboBoxCheckingCopyType = new JComboBox();
		comboBoxCheckingCopyType.setBounds(146, 109, 159, 27);
		getContentPane().add(comboBoxCheckingCopyType);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(376, 113, 61, 16);
		getContentPane().add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(417, 101, 134, 28);
		getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(157, 237, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(320, 237, 117, 29);
		getContentPane().add(btnBack);
		
		
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(376, 25, 61, 16);
		getContentPane().add(lblUser);
		
		comboBoxUserId = new JComboBox();
		comboBoxUserId.setBounds(417, 21, 278, 27);
		getContentPane().add(comboBoxUserId);
		
		comboBoxRegistrationId = new JComboBox();
		comboBoxRegistrationId.setBounds(242, 21, 117, 27);
		getContentPane().add(comboBoxRegistrationId);
		
		
		try {
			lblNewLabel.setText(String.valueOf(RiSEEventMainScreenP.facade.getCheckingCopyLastId()));
			
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
		TypeCheckingCopy[] types = TypeCheckingCopy.values();
		List<String> names = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			names.add(i, types[i].name());
			comboBoxCheckingCopyType.addItem(types[i].name());
		}
		
		carregarRegistrationComboBox();
		carregarUserComboBox();
		
		ItemListener listener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				setarNomeUsuarioByRegistration();
			}
			
		};
		comboBoxRegistrationId.addItemListener(listener);

		
		btnInsert.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			CheckingCopyInsertScreenP.instanceCheckingCopyInsertScreenP = null;
		}
	}
	
	private void setarNomeUsuarioByRegistration(){
		try {
			Registration registration = RiSEEventMainScreenP.facade.searchRegistration(Integer.parseInt(comboBoxRegistrationId.getSelectedItem().toString()));
			User user = RiSEEventMainScreenP.facade.searchUser(registration.getIdUser());
			comboBoxUserId.setSelectedItem(user.getNameUser());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (RegistrationNotFoundException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (RegistrationAlreadyInsertedException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (UserAlreadyInsertedException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			CheckingCopy checkingCopy = null;
			Registration registration = null;
			User user = null;
			try {
				registration = RiSEEventMainScreenP.facade.searchRegistration(Integer.parseInt(comboBoxRegistrationId.getSelectedItem().toString()));
				user = RiSEEventMainScreenP.facade.searchUser(registration.getIdUser());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RegistrationNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RegistrationAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (UserNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (UserAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
			
			int registrationId = registration.getIdRegistration();
			int userId = user.getIdUser();
			String date = textFieldDate.getText();
			String type = comboBoxCheckingCopyType.getSelectedItem().toString();
			
			if (userId == -1 || registrationId == -1 || date.equals("") || type.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
					try {
						checkingCopy = new CheckingCopy();
						checkingCopy.setIdUser(userId);
						checkingCopy.setIdRegistration(registrationId);
						checkingCopy.setDateOfIssue(date);
						checkingCopy.setCheckingCopyType(TypeCheckingCopy.valueOf(type));
				
						RiSEEventMainScreenP.facade.insertCheckingCopy(checkingCopy);


					} catch (CheckingCopyAlreadyInsertedException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					} catch (RepositoryException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}
	
			}	
		}
		
	}
		
	
	private void carregarRegistrationComboBox(){
		try {
			List<Registration> list = RiSEEventMainScreenP.facade.getRegistrations();
			Iterator<Registration> iterator = list.iterator();
			while(iterator.hasNext()){
				comboBoxRegistrationId.addItem(iterator.next().getIdRegistration());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void carregarUserComboBox(){
		try {
			List<User> list = RiSEEventMainScreenP.facade.getUsers();
			Iterator<User> iterator = list.iterator();
			while(iterator.hasNext()){
				comboBoxUserId.addItem(iterator.next().getNameUser());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
}
//#endif