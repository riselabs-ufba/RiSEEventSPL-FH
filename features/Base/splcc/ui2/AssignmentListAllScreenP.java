//#if ${AssignmentChairindication} == "T" or ${Assignmentautomatic} == "T"
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
import rise.splcc.table.AssignmentTableModel;

import javax.swing.JButton;

public class AssignmentListAllScreenP extends JInternalFrame {

	private static AssignmentListAllScreenP instanceAssignmentListAllScreenP;
	private JTable table;
	
	private AssignmentTableModel model;
	
	private JButton btnBack;
	
	public static AssignmentListAllScreenP getInstanceAssignmentListAllScreenP() {
		if (instanceAssignmentListAllScreenP == null) {
			AssignmentListAllScreenP.instanceAssignmentListAllScreenP = new AssignmentListAllScreenP();
		}
		return AssignmentListAllScreenP.instanceAssignmentListAllScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignmentListAllScreenP frame = new AssignmentListAllScreenP();
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
	public AssignmentListAllScreenP() {
		setTitle("List All Assignment");
		
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
			model = new AssignmentTableModel(RiSEEventMainScreenP.facade.getAssignments());
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
		btnBack.setBounds(274, 229, 117, 29);
		getContentPane().add(btnBack);
		
		btnBack.addActionListener(backAction);
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			AssignmentListAllScreenP.instanceAssignmentListAllScreenP = null;
		}
	}
}
//#endif