package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rise.splcc.exception.RegistrationAlreadyInsertedException;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;

public class RegistrationRemoveScreenP extends JInternalFrame {

	private static RegistrationRemoveScreenP instanceRegistrationRemoveScreenP;
	private JTextField textFieldRegistrationId;
	
	public static RegistrationRemoveScreenP getInstanceRegistrationRemoveScreenP() {
		if (instanceRegistrationRemoveScreenP == null) {
			RegistrationRemoveScreenP.instanceRegistrationRemoveScreenP = new RegistrationRemoveScreenP();
		}
		return RegistrationRemoveScreenP.instanceRegistrationRemoveScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationRemoveScreenP frame = new RegistrationRemoveScreenP();
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
	public RegistrationRemoveScreenP() {
		setTitle("Remove Registration");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		RemoveButtonAction RemoveAction = new RemoveButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		JLabel lblRegistrationid = new JLabel("RegistrationId:");
		lblRegistrationid.setBounds(23, 38, 73, 16);
		getContentPane().add(lblRegistrationid);
		
		textFieldRegistrationId = new JTextField();
		textFieldRegistrationId.setBounds(109, 32, 134, 28);
		getContentPane().add(textFieldRegistrationId);
		textFieldRegistrationId.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(334, 32, 117, 29);
		getContentPane().add(btnRemove);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(334, 73, 117, 29);
		getContentPane().add(btnBack);
		
		btnRemove.addActionListener(RemoveAction);
		btnBack.addActionListener(backAction);
		
		
	}

	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			RegistrationRemoveScreenP.instanceRegistrationRemoveScreenP = null;
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int idRegistration = Integer.valueOf(textFieldRegistrationId.getText());
			
			try {
				if (RiSEEventMainScreenP.facade.searchRegistration(idRegistration) == null){
					JOptionPane.showMessageDialog(getContentPane(),
							"Registration não existe.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					RiSEEventMainScreenP.facade.removeRegistration(idRegistration);
					JOptionPane.showMessageDialog(getContentPane(), "Remoção realizada com sucesso!!","Remoção",JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
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
	
}
