package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import rise.splcc.exception.RepositoryException;
import rise.splcc.table.EventTableModel;

public class EventListAllScreenP extends JInternalFrame {

	private static EventListAllScreenP instanceEventListAllScreenP;
	private JTable table;
	
	private EventTableModel model;
	
	JButton btnBack_1;
	
	public static EventListAllScreenP getInstanceEventListAllScreenP() {
		if (instanceEventListAllScreenP == null) {
			EventListAllScreenP.instanceEventListAllScreenP = new EventListAllScreenP();
		}
		return EventListAllScreenP.instanceEventListAllScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventListAllScreenP frame = new EventListAllScreenP();
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
	public EventListAllScreenP() {
		setTitle("List All Event");
		
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
			model = new EventTableModel(RiSEEventMainScreenP.facade.getEvents());
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
		
		btnBack_1 = new JButton("Back");
		btnBack_1.setBounds(281, 238, 117, 29);
		getContentPane().add(btnBack_1);
		
		btnBack_1.addActionListener(backAction);
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			EventListAllScreenP.instanceEventListAllScreenP= null;
			
		}
	}
}
