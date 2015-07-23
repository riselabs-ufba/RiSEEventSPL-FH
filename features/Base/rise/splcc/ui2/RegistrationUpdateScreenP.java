package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import rise.splcc.data.Registration;
import rise.splcc.data.User;
import rise.splcc.exception.RegistrationAlreadyInsertedException;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;

public class RegistrationUpdateScreenP extends JInternalFrame {

	private JTextField textFieldTotalValue;
	private JComboBox comboBoxUser;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	private static RegistrationUpdateScreenP instanceRegistrationUpdateScreenP;
	private JTextField textFieldidRegistration;
	public static RegistrationUpdateScreenP getInstanceRegistrationUpdateScreenP() {
		if (instanceRegistrationUpdateScreenP == null) {
			RegistrationUpdateScreenP.instanceRegistrationUpdateScreenP = new RegistrationUpdateScreenP();
		}
		return RegistrationUpdateScreenP.instanceRegistrationUpdateScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationUpdateScreenP frame = new RegistrationUpdateScreenP();
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
	public RegistrationUpdateScreenP() {
setTitle("Update Registration");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		UpdateButtonAction updateAction = new UpdateButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		JLabel lblRegistrationId = new JLabel("Registration Id:");
		lblRegistrationId.setBounds(17, 25, 76, 16);
		getContentPane().add(lblRegistrationId);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(187, 25, 61, 16);
		getContentPane().add(lblUser);
		
		comboBoxUser = new JComboBox();
		comboBoxUser.setBounds(231, 21, 363, 27);
		getContentPane().add(comboBoxUser);
		
		JLabel lblTotalValue = new JLabel("Total Value:");
		lblTotalValue.setBounds(17, 69, 101, 16);
		getContentPane().add(lblTotalValue);
		
		textFieldTotalValue = new JTextField();
		textFieldTotalValue.setBounds(115, 63, 134, 28);
		getContentPane().add(textFieldTotalValue);
		textFieldTotalValue.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(459, 228, 204, 164);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(157, 237, 117, 29);
		getContentPane().add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(320, 237, 117, 29);
		getContentPane().add(btnBack);
		
		textFieldidRegistration = new JTextField();
		textFieldidRegistration.setBounds(85, 19, 86, 28);
		getContentPane().add(textFieldidRegistration);
		textFieldidRegistration.setColumns(10);
		
		btnUpdate.addActionListener(updateAction);
		btnBack.addActionListener(backAction);

		carregarUserComboBox();

	}
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			RegistrationUpdateScreenP.instanceRegistrationUpdateScreenP = null;
		}
	}
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Registration registration = new Registration();
			
			int idRegistration = Integer.valueOf(textFieldidRegistration.getText());
			String nameUser = comboBoxUser.getSelectedItem().toString();
			String totalValue = textFieldTotalValue.getText();
			
			try {
				registration = RiSEEventMainScreenP.facade.searchRegistration(idRegistration);
			} catch (RegistrationNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RegistrationAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
			try {
				registration.setIdUser(RiSEEventMainScreenP.facade.getUserIdByName(nameUser));
				registration.setTotalValue(Float.parseFloat(totalValue));
				
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
			try {
				RiSEEventMainScreenP.facade.updateRegistration(registration);
				registration = RiSEEventMainScreenP.facade.searchRegistration(registration.getIdRegistration());
				textArea.setText("");
				textArea.append(registration.toString());
			} catch (RegistrationNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RegistrationAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
		}
	}
	
	private void carregarUserComboBox(){
		try {
			List<User> list = RiSEEventMainScreenP.facade.getUsers();
			Iterator<User> iterator = list.iterator();
			while(iterator.hasNext()){
				comboBoxUser.addItem(iterator.next().getNameUser());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
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
}
