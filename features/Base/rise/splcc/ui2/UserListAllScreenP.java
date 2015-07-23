
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import rise.splcc.exception.RepositoryException;
import rise.splcc.table.UserTableModel;

import javax.swing.JButton;

public class UserListAllScreenP extends JInternalFrame {

	private static UserListAllScreenP instanceUserListAllScreenP;
	private JTable table;
	private JScrollPane scrollPane;

	
	private JButton btnBack;
	
	public static UserListAllScreenP getInstanceUserListAllScreenP() {
		if (instanceUserListAllScreenP == null) {
			UserListAllScreenP.instanceUserListAllScreenP = new UserListAllScreenP();
		}
		return UserListAllScreenP.instanceUserListAllScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserListAllScreenP frame = new UserListAllScreenP();
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
	public UserListAllScreenP() {
		setTitle("List All User");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		getContentPane().setLayout(null);
		
		BackButtonAction backAction = new BackButtonAction();
		
		btnBack = new JButton("Back");
		btnBack.setBounds(339, 230, 117, 29);
		getContentPane().add(btnBack);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(65, 42, 608, 154);
		getContentPane().add(scrollPane);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);

		
	
		populateJTable();
		
	
		
		btnBack.addActionListener(backAction);
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			dispose();
			UserListAllScreenP.instanceUserListAllScreenP = null;
		}
	}
	

	
	private void populateJTable(){
		try {
			UserTableModel model;
			model = new UserTableModel(RiSEEventMainScreenP.getFacade().getUsers());
			table.setModel(model);

		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
}
