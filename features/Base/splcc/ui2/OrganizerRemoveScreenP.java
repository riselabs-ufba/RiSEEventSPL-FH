//#if ${Organizer} == "T"
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
import rise.splcc.exception.OrganizerAlreadyInsertedException;
import rise.splcc.exception.OrganizerNotFoundException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;


public class OrganizerRemoveScreenP extends JInternalFrame {

	
	
	private static OrganizerRemoveScreenP instanceOrganizerRemoveScreenP;
	private JTextField textFieldIdUser;
	
	public static OrganizerRemoveScreenP getInstanceOrganizerRemoveScreenP() {
		if (instanceOrganizerRemoveScreenP == null) {
			OrganizerRemoveScreenP.instanceOrganizerRemoveScreenP = new OrganizerRemoveScreenP();
		}
		return OrganizerRemoveScreenP.instanceOrganizerRemoveScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrganizerRemoveScreenP frame = new OrganizerRemoveScreenP();
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
	public OrganizerRemoveScreenP() {
		
		
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
		
		JLabel lblOrganizerId = new JLabel("User Id:");
		lblOrganizerId.setBounds(27, 45, 61, 16);
		getContentPane().add(lblOrganizerId);
		
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
			OrganizerRemoveScreenP.instanceOrganizerRemoveScreenP = null;
			
			
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int idUser = Integer.valueOf(textFieldIdUser.getText());
			
			try {
					RiSEEventMainScreenP.facade.removeOrganizer(idUser);
					JOptionPane.showMessageDialog(getContentPane(), "Remoção realizada com sucesso!!","Remoção",JOptionPane.INFORMATION_MESSAGE);
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
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
}
//#endif