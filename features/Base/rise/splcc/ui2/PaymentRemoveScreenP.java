//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rise.splcc.exception.PaymentAlreadyInsertedException;
import rise.splcc.exception.PaymentNotFoundException;
import rise.splcc.exception.RepositoryException;

public class PaymentRemoveScreenP extends JInternalFrame {

	private static PaymentRemoveScreenP instancePaymentRemoveScreenP;
	private JTextField textFieldPaymentId;
	
	public static PaymentRemoveScreenP getInstancePaymentRemoveScreenP() {
		if (instancePaymentRemoveScreenP == null) {
			PaymentRemoveScreenP.instancePaymentRemoveScreenP = new PaymentRemoveScreenP();
		}
		return PaymentRemoveScreenP.instancePaymentRemoveScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentRemoveScreenP frame = new PaymentRemoveScreenP();
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
	public PaymentRemoveScreenP() {
		setTitle("Remove Payment");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		RemoveButtonAction RemoveAction = new RemoveButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		JLabel lblPaymentid = new JLabel("PaymentId:");
		lblPaymentid.setBounds(23, 38, 73, 16);
		getContentPane().add(lblPaymentid);
		
		textFieldPaymentId = new JTextField();
		textFieldPaymentId.setBounds(109, 32, 134, 28);
		getContentPane().add(textFieldPaymentId);
		textFieldPaymentId.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(334, 32, 117, 29);
		getContentPane().add(btnRemove);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(334, 73, 117, 29);
		getContentPane().add(btnBack);
		
		btnRemove.addActionListener(RemoveAction);
		btnBack.addActionListener(backAction);
		
		
	}

	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			PaymentRemoveScreenP.instancePaymentRemoveScreenP = null;
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int idPayment = Integer.valueOf(textFieldPaymentId.getText());
			
			try {
				if (RiSEEventMainScreenP.facade.searchPayment(idPayment) == null){
					JOptionPane.showMessageDialog(getContentPane(),
							"Payment não existe.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					RiSEEventMainScreenP.facade.removePayment(idPayment);
					JOptionPane.showMessageDialog(getContentPane(), "Remoção realizada com sucesso!!","Remoção",JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (PaymentNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (PaymentAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
				
		}

	}
	
}
//#endif
