package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rise.splcc.data.Registration;
import rise.splcc.exception.RegistrationAlreadyInsertedException;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;

public class RegistrationSearchScreenP extends JInternalFrame {

	private static RegistrationSearchScreenP instanceRegistrationSearchScreenP;
	private JTextField textFieldIdRegistration;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	public static RegistrationSearchScreenP getInstanceRegistrationSearchScreenP() {
		if (instanceRegistrationSearchScreenP == null) {
			RegistrationSearchScreenP.instanceRegistrationSearchScreenP = new RegistrationSearchScreenP();
		}
		return RegistrationSearchScreenP.instanceRegistrationSearchScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationSearchScreenP frame = new RegistrationSearchScreenP();
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
	public RegistrationSearchScreenP() {
		setTitle("Search Registration");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		SearchButtonAction searchAction = new SearchButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		getContentPane().setLayout(null);
		
		JLabel lblRegistrationId = new JLabel("Registration Id:");
		lblRegistrationId.setBounds(29, 34, 87, 16);
		getContentPane().add(lblRegistrationId);
		
		textFieldIdRegistration = new JTextField();
		textFieldIdRegistration.setBounds(110, 28, 134, 28);
		getContentPane().add(textFieldIdRegistration);
		textFieldIdRegistration.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 57, 182, 211);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(458, 29, 117, 29);
		getContentPane().add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(458, 70, 117, 29);
		getContentPane().add(btnBack);
		
		btnSearch.addActionListener(searchAction);
		btnBack.addActionListener(backAction);


	}

	
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			RegistrationSearchScreenP.instanceRegistrationSearchScreenP = null;
			
		}
	}
	
	private class SearchButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Registration activity = new Registration();
			
			int idRegistration =Integer.valueOf( textFieldIdRegistration.getText());
			
			try {
				activity = RiSEEventMainScreenP.facade.searchRegistration(idRegistration);
				textArea.setText("");
				textArea.append(activity.toString());
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
	
	private JTextArea getTextArea() {
		if (this.textArea == null) {
			this.textArea = new JTextArea("Dados:\n\n");
			textArea.setBounds(232, 69, 233, 196);
			textArea.setEditable(false);
		}
		return this.textArea;
	}
	
	private JScrollPane getScrollPanel() {
		if (this.scrollPane == null) {
			this.scrollPane = new JScrollPane(
					getTextArea());
			this.scrollPane.setBounds(232, 69, 233, 196);
		}
		return this.scrollPane;
	}
}
