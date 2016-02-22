//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import rise.splcc.exception.RepositoryException;
import rise.splcc.table.ActivityTableModel;

import javax.swing.JButton;

public class ActivityListAllScreenP extends JInternalFrame {

	
	private static ActivityListAllScreenP instanceActivityListAllScreenP;
	
	private ActivityTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	
	JButton btnBack;
	
	public static ActivityListAllScreenP getInstanceActivityListAllScreenP() {
		if (instanceActivityListAllScreenP == null) {
			ActivityListAllScreenP.instanceActivityListAllScreenP = new ActivityListAllScreenP();
		}
		return ActivityListAllScreenP.instanceActivityListAllScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityListAllScreenP frame = new ActivityListAllScreenP();
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
	public ActivityListAllScreenP() {
		setTitle("List All Activity");
		
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
			model = new ActivityTableModel(RiSEEventMainScreenP.facade.getActivities());
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
		
		btnBack = new JButton("Back");
		btnBack.setBounds(280, 223, 117, 29);
		getContentPane().add(btnBack);
		
		btnBack.addActionListener(backAction);

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			ActivityListAllScreenP.instanceActivityListAllScreenP= null;
			
		}
	}
}
//#endif