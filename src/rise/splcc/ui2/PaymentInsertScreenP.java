//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rise.splcc.data.Payment;
import rise.splcc.data.Payment.StatusPayment;
import rise.splcc.data.Payment.TypePayment;
import rise.splcc.data.Registration;
import rise.splcc.exception.PaymentAlreadyInsertedException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.table.PaymentTableModel;

import com.lowagie.text.DocumentException;

public class PaymentInsertScreenP extends JInternalFrame{
	private JTextField textFieldStatus;
	private JTextField textFieldValue;
	private JTextField textFieldBarcode;
	private JTextField textFieldDate;
	private JComboBox comboBoxType;
	private JComboBox comboBoxRegistration;
	
	private static PaymentInsertScreenP instancePaymentInsertScreenP;
	private JTextField textFieldidPayment;
	public static PaymentInsertScreenP getInstancePaymentInsertScreenP() {
		if (instancePaymentInsertScreenP == null) {
			PaymentInsertScreenP.instancePaymentInsertScreenP = new PaymentInsertScreenP();
		}
		return PaymentInsertScreenP.instancePaymentInsertScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentInsertScreenP frame = new PaymentInsertScreenP();
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
	public PaymentInsertScreenP() {
setTitle("Insert Payment");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		InsertButtonAction updateAction = new InsertButtonAction();
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
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(157, 237, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(320, 237, 117, 29);
		getContentPane().add(btnBack);
		
		textFieldidPayment = new JTextField();
		textFieldidPayment.setBounds(85, 19, 86, 28);
		getContentPane().add(textFieldidPayment);
		textFieldidPayment.setColumns(10);
		
		btnInsert.addActionListener(updateAction);
		btnBack.addActionListener(backAction);
		
		
		
		TypePayment[] types = TypePayment.values();
		List<String> names = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			names.add(i, types[i].name());
			comboBoxType.addItem(types[i].name());
		}
		
		carregarRegistrationComboBox();

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			PaymentInsertScreenP.instancePaymentInsertScreenP = null;
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Payment payment = null;
			
			int idPayment = Integer.valueOf(textFieldidPayment.getText());
			int registrationId = Integer.valueOf(comboBoxRegistration.getSelectedItem().toString());
			String type = comboBoxType.getSelectedItem().toString();
			String status = textFieldStatus.getText();
			float value = Float.valueOf(textFieldValue.getText());
			String barcode = textFieldBarcode.getText();
			String date = textFieldDate.getText();
			
			//int resultado = 0;
			if ( registrationId == -1 || type.equals("") || status.equals("") || date.equals("")
					|| barcode.equals("") || value == -1) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {					
				try {
			
					payment = new Payment();
					
					payment.setIdRegistration(registrationId);
					payment.setPaymentType(TypePayment.valueOf(type));
					payment.setStatus(StatusPayment.valueOf(status));
					payment.setValue(value);
					payment.setBarcode(barcode);
					payment.setDate(date);
					
					RiSEEventMainScreenP.facade.insertPayment(payment); 
					PaymentTableModel model = new PaymentTableModel(RiSEEventMainScreenP.facade.getPayments());
					
					acaoType(payment);
				
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
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		}
	}
		
	public void acaoType(Payment paymentout) throws DocumentException, IOException{
		Payment payment = new Payment();
		RiSEEventMainScreenP.facade.typePayment(payment, paymentout);
		
	}	
		
	
	private void carregarRegistrationComboBox(){
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
	
}
//#endif