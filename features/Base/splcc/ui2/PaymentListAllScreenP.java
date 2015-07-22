//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
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
import rise.splcc.table.PaymentTableModel;

import javax.swing.JButton;

public class PaymentListAllScreenP extends JInternalFrame {

	
	private static PaymentListAllScreenP instancePaymentListAllScreenP;
	
	private PaymentTableModel model;
	private JTable table;
	private JScrollPane scrollPane;
	
	JButton btnBack;
	
	public static PaymentListAllScreenP getInstancePaymentListAllScreenP() {
		if (instancePaymentListAllScreenP == null) {
			PaymentListAllScreenP.instancePaymentListAllScreenP = new PaymentListAllScreenP();
		}
		return PaymentListAllScreenP.instancePaymentListAllScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentListAllScreenP frame = new PaymentListAllScreenP();
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
	public PaymentListAllScreenP() {
		setTitle("List All Payment");
		
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
			model = new PaymentTableModel(RiSEEventMainScreenP.facade.getPayments());
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
		btnBack.setBounds(271, 217, 117, 29);
		getContentPane().add(btnBack);
		
		
		btnBack.addActionListener(backAction);
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			PaymentListAllScreenP.instancePaymentListAllScreenP = null;
		}
	}
}
//#endif