//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
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
import rise.splcc.table.CheckingCopyTableModel;

import javax.swing.JButton;

public class CheckingCopyListAllScreenP extends JInternalFrame {

	
	private static CheckingCopyListAllScreenP instanceCheckingCopyListAllScreenP;
	
	private CheckingCopyTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	
	JButton btnBack ;
	
	public static CheckingCopyListAllScreenP getInstanceCheckingCopyListAllScreenP() {
		if (instanceCheckingCopyListAllScreenP == null) {
			CheckingCopyListAllScreenP.instanceCheckingCopyListAllScreenP = new CheckingCopyListAllScreenP();
		}
		return CheckingCopyListAllScreenP.instanceCheckingCopyListAllScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckingCopyListAllScreenP frame = new CheckingCopyListAllScreenP();
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
	public CheckingCopyListAllScreenP() {
		setTitle("List All CheckingCopy");
		
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
			model = new CheckingCopyTableModel(RiSEEventMainScreenP.facade.getCheckingCopys());
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
		
		table = new JTable(model);
	    scrollPane = new JScrollPane(table);
		scrollPane.setBounds(32, 46, 643, 134);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(273, 214, 117, 29);
		getContentPane().add(btnBack);
		
		btnBack.addActionListener(backAction);

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			CheckingCopyListAllScreenP.instanceCheckingCopyListAllScreenP = null;
		}
	}
}
//#endif