//#if ${Organizer} == "T"
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
import rise.splcc.table.OrganizerTableModel;

import javax.swing.JButton;

public class OrganizerListAllScreenP extends JInternalFrame {

	private static OrganizerListAllScreenP instanceOrganizerListAllScreenP;
	private JTable table;
	
	private OrganizerTableModel model;
	
	JButton btnBack;
	
	public static OrganizerListAllScreenP getInstanceOrganizerListAllScreenP() {
		if (instanceOrganizerListAllScreenP == null) {
			OrganizerListAllScreenP.instanceOrganizerListAllScreenP = new OrganizerListAllScreenP();
		}
		return OrganizerListAllScreenP.instanceOrganizerListAllScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrganizerListAllScreenP frame = new OrganizerListAllScreenP();
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
	public OrganizerListAllScreenP() {
		setTitle("List All Organizer");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		getContentPane().setLayout(null);
		
		
		BackButtonAction backAction = new BackButtonAction();
		
		
		try {
			model = new OrganizerTableModel(RiSEEventMainScreenP.facade.getOrganizers());
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(65, 42, 608, 154);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(279, 230, 117, 29);
		getContentPane().add(btnBack);
		
		btnBack.addActionListener(backAction);
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			OrganizerListAllScreenP.instanceOrganizerListAllScreenP= null;
			
		}
	}
}
//#endif