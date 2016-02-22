//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rise.splcc.data.Payment;
import rise.splcc.data.Payment.StatusPayment;
import rise.splcc.data.Payment.TypePayment;
import rise.splcc.data.Registration;
import rise.splcc.exception.PaymentAlreadyInsertedException;
import rise.splcc.exception.PaymentNotFoundException;
import rise.splcc.exception.RepositoryException;

public class PaymentUpdateScreenP extends JInternalFrame{
	private JTextField textFieldStatus;
	private JTextField textFieldValue;
	private JTextField textFieldBarcode;
	private JTextField textFieldDate;
	private JComboBox comboBoxType;
	private JComboBox comboBoxRegistration;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	private static PaymentUpdateScreenP instancePaymentUpdateScreenP;
	private JTextField textFieldidPayment;
	public static PaymentUpdateScreenP getInstancePaymentUpdateScreenP() {
		if (instancePaymentUpdateScreenP == null) {
			PaymentUpdateScreenP.instancePaymentUpdateScreenP = new PaymentUpdateScreenP();
		}
		return PaymentUpdateScreenP.instancePaymentUpdateScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentUpdateScreenP frame = new PaymentUpdateScreenP();
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
	public PaymentUpdateScreenP() {
setTitle("Insert Payment");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		UpdateButtonAction updateAction = new UpdateButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		JLabel lblPaymentId = new JLabel("Payment Id:");
		lblPaymentId.setBounds(17, 25, 76, 16);
		getContentPane().add(lblPaymentId);
		
		JLabel lblRegistration = new JLabel("Registration:");
		lblRegistration.setBounds(187, 25, 86, 16);
		getContentPane().add(lblRegistration);
		
		comboBoxRegistration = new JComboBox();
		comboBoxRegistration.setBounds(279, 21, 363, 27);
		getContentPane().add(comboBoxRegistration);
		
		JLabel lblPaymentType = new JLabel("Payment Type:");
		lblPaymentType.setBounds(17, 113, 86, 16);
		getContentPane().add(lblPaymentType);
		
		comboBoxType = new JComboBox();
		comboBoxType.setBounds(115, 109, 159, 27);
		getContentPane().add(comboBoxType);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setBounds(304, 113, 61, 16);
		getContentPane().add(lblStatus);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setBounds(351, 107, 134, 28);
		getContentPane().add(textFieldStatus);
		textFieldStatus.setColumns(10);
		
		JLabel lblValue = new JLabel("Value:");
		lblValue.setBounds(17, 153, 61, 16);
		getContentPane().add(lblValue);
		
		textFieldValue = new JTextField();
		textFieldValue.setBounds(64, 147, 134, 28);
		getContentPane().add(textFieldValue);
		textFieldValue.setColumns(10);
		
		JLabel lblBarcode = new JLabel("Barcode:");
		lblBarcode.setBounds(231, 153, 86, 16);
		getContentPane().add(lblBarcode);
		
		textFieldBarcode = new JTextField();
		textFieldBarcode.setBounds(329, 147, 134, 28);
		getContentPane().add(textFieldBarcode);
		textFieldBarcode.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(488, 153, 61, 16);
		getContentPane().add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(529, 147, 134, 28);
		getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(459, 228, 204, 164);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(157, 237, 117, 29);
		getContentPane().add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(320, 237, 117, 29);
		getContentPane().add(btnBack);
		
		textFieldidPayment = new JTextField();
		textFieldidPayment.setBounds(85, 19, 86, 28);
		getContentPane().add(textFieldidPayment);
		textFieldidPayment.setColumns(10);
		
		btnUpdate.addActionListener(updateAction);
		btnBack.addActionListener(backAction);
		
		
		
		TypePayment[] types = TypePayment.values();
		List<String> names = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			names.add(i, types[i].name());
			comboBoxType.addItem(types[i].name());
		}
		
		carregarEventComboBox();

	}
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			PaymentUpdateScreenP.instancePaymentUpdateScreenP = null;
		}
	}
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Payment payment = new Payment();
			
			int idPayment = Integer.valueOf(textFieldidPayment.getText());
			int registration = Integer.valueOf(comboBoxRegistration.getSelectedItem().toString());
			String paymentType = comboBoxType.getSelectedItem().toString();
			String status = textFieldStatus.getText();
			float value = Float.valueOf(textFieldValue.getText());
			String barcode = textFieldBarcode.getText();
			String date = textFieldDate.getText();
			
			try {
				payment = RiSEEventMainScreenP.facade.searchPayment(idPayment);
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
			
			payment.setIdRegistration(registration);
			payment.setPaymentType(TypePayment.valueOf(paymentType));
			payment.setStatus(StatusPayment.valueOf(status));
			payment.setValue(value);
			payment.setBarcode(barcode);
			payment.setDate(date);
			
			try {
				RiSEEventMainScreenP.facade.updatePayment(payment);
				payment = RiSEEventMainScreenP.facade.searchPayment(payment.getIdPayment());
				textArea.setText("");
				textArea.append(payment.toString());
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
	
	private void carregarEventComboBox(){
		try {
			List<Registration> list = RiSEEventMainScreenP.facade.getRegistrations();
			Iterator<Registration> iterator = list.iterator();
			while(iterator.hasNext()){
				comboBoxRegistration.addItem(iterator.next().getIdRegistration());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private JTextArea getTextArea() {
		if (this.textArea == null) {
			this.textArea = new JTextArea("Dados:\n\n");
			textArea.setBounds(62, 68, 273, 142);
			textArea.setEditable(false);
		}
		return this.textArea;
	}
	
	private JScrollPane getScrollPanel() {
		if (this.scrollPane == null) {
			this.scrollPane = new JScrollPane(
					getTextArea());
			this.scrollPane.setBounds(62, 68, 273, 142);
		}
		return this.scrollPane;
	}
	
}
//#endif