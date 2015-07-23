
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;


public class UserRemoveScreenP extends JInternalFrame {

	
	
	private static UserRemoveScreenP instanceUserRemoveScreenP;
	private JTextField textFieldIdUser;
	
	public static UserRemoveScreenP getInstanceUserRemoveScreenP() {
		if (instanceUserRemoveScreenP == null) {
			UserRemoveScreenP.instanceUserRemoveScreenP = new UserRemoveScreenP();
		}
		return UserRemoveScreenP.instanceUserRemoveScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRemoveScreenP frame = new UserRemoveScreenP();
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
	public UserRemoveScreenP() {
		
		
		RemoveButtonAction removeAction = new RemoveButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		getContentPane().setLayout(null);
		
		JLabel lblUserId = new JLabel("User Id:");
		lblUserId.setBounds(27, 45, 61, 16);
		getContentPane().add(lblUserId);
		
		textFieldIdUser = new JTextField();
		textFieldIdUser.setBounds(84, 39, 134, 28);
		getContentPane().add(textFieldIdUser);
		textFieldIdUser.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(263, 17, 117, 29);
		getContentPane().add(btnRemove);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(263, 58, 117, 29);
		getContentPane().add(btnBack);

		btnRemove.addActionListener(removeAction);
		btnBack.addActionListener(backAction);
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			UserRemoveScreenP.instanceUserRemoveScreenP = null;
			
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int idUser = Integer.valueOf(textFieldIdUser.getText());
			
			try {
				
					RiSEEventMainScreenP.facade.removeUser(idUser);
					JOptionPane.showMessageDialog(getContentPane(), "Remoção realizada com sucesso!!","Sucesso",JOptionPane.INFORMATION_MESSAGE);
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
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
}

