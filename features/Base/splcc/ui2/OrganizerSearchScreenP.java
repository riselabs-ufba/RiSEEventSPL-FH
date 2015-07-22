//#if ${Organizer} == "T"
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

import rise.splcc.data.Organizer;
import rise.splcc.data.User;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.OrganizerAlreadyInsertedException;
import rise.splcc.exception.OrganizerNotFoundException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;

public class OrganizerSearchScreenP extends JInternalFrame {

	
	private static OrganizerSearchScreenP instanceOrganizerSearchScreenP;
	private JTextField textFieldOrganizerId;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	public static OrganizerSearchScreenP getInstanceOrganizerSearchScreenP() {
		if (instanceOrganizerSearchScreenP == null) {
			OrganizerSearchScreenP.instanceOrganizerSearchScreenP = new OrganizerSearchScreenP();
		}
		return OrganizerSearchScreenP.instanceOrganizerSearchScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrganizerSearchScreenP frame = new OrganizerSearchScreenP();
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
	public OrganizerSearchScreenP() {
		setTitle("Search Organizer");
		
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
		
		JLabel lblOrganizerId = new JLabel("User Id:");
		lblOrganizerId.setBounds(30, 40, 61, 16);
		getContentPane().add(lblOrganizerId);
		
		textFieldOrganizerId = new JTextField();
		textFieldOrganizerId.setBounds(91, 34, 134, 28);
		getContentPane().add(textFieldOrganizerId);
		textFieldOrganizerId.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(480, 54, 117, 29);
		getContentPane().add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(480, 95, 117, 29);
		getContentPane().add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(232, 69, 233, 196);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		

		
		btnSearch.addActionListener(searchAction);
		btnBack.addActionListener(backAction);

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			OrganizerSearchScreenP.instanceOrganizerSearchScreenP = null;
			
		}
	}
	
	private class SearchButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Organizer organizer = new Organizer();
			User user = new User();
			
			int idUser =Integer.valueOf(textFieldOrganizerId.getText());
			
			try {
				organizer = RiSEEventMainScreenP.facade.searchOrganizer(idUser);
				//user = RiSEEventMainScreenP.facade.searchUser(idUser);
				textArea.setText("");
				//textArea.append(user.toString() + organizer.toString());
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
//#endif