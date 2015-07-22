//#if ${PaymentAvista} == "T" or ${PaymentDeposito} == "T" or ${PaymentCartao} == "T"
package rise.splcc.ui2;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rise.splcc.data.Payment;
import rise.splcc.data.Payment.StatusPayment;
import rise.splcc.data.Payment.TypePayment;
import rise.splcc.data.Registration;
import rise.splcc.exception.PaymentAlreadyInsertedException;
import rise.splcc.exception.PaymentNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.table.PaymentTableModel;

import com.itextpdf.text.pdf.BarcodeEAN;
import com.lowagie.text.DocumentException;

public class PaymentManagementScreenP extends JInternalFrame{

	private static PaymentManagementScreenP instancePaymentManagementScreenP;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	
	private JTextField textFieldValuePayment;
	private JTextField textFieldBarCode;
	private JLabel lblData;
	
	JComboBox<String> comboBoxTypePayment;
	JComboBox<String> comboBoxStatusPayment;
	JComboBox<Integer> comboBoxIdRegistration;
	
	JButton btnInsert;
	JButton btnRemove;
	JButton btnUpdate;
	JButton btnSelect;
	JButton btnClean;
	

	 public static PaymentManagementScreenP getInstancePaymentManagementScreenP() {
		 if (instancePaymentManagementScreenP == null) {
			 PaymentManagementScreenP.instancePaymentManagementScreenP = new PaymentManagementScreenP();
		 }
		 return PaymentManagementScreenP.instancePaymentManagementScreenP;
	 }
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentManagementScreenP frame = new PaymentManagementScreenP();
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
	public PaymentManagementScreenP() {
		
		InsertButtonAction insertAction = new InsertButtonAction(); 
		RemoveButtonAction removeAction = new RemoveButtonAction(); 
		UpdateButtonAction updateAction = new UpdateButtonAction();
		SelectButtonAction selectAction = new SelectButtonAction(); 
		CleanButtonAction cleanAction = new CleanButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		setTitle("Payment Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(220, 18, 392, 68);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblImage = new JLabel("");
		lblImage.setBounds(6, 6, 380, 56);
		ImageIcon image = new ImageIcon(getClass().getResource("/images/riseLabs.png"));
		Image imag = image.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(imag));
		panel.add(lblImage);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 99, 793, 189);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		comboBoxTypePayment = new JComboBox<String>();
		comboBoxTypePayment.setBounds(139, 61, 128, 27);
		panel_1.add(comboBoxTypePayment);
		
		JLabel lblTipoDePagamento = new JLabel("Tipo de Pagamento:");
		lblTipoDePagamento.setBounds(6, 65, 128, 16);
		panel_1.add(lblTipoDePagamento);
		
		JLabel lblStatusDoPagamento = new JLabel("Status do Pagamento:");
		lblStatusDoPagamento.setBounds(279, 65, 147, 16);
		panel_1.add(lblStatusDoPagamento);
		
		comboBoxStatusPayment = new JComboBox<String>();
		comboBoxStatusPayment.setBounds(417, 61, 128, 27);
		panel_1.add(comboBoxStatusPayment);
		
		JLabel lblValor = new JLabel("Valor:");
		lblValor.setBounds(356, 121, 43, 16);
		panel_1.add(lblValor);
		
		textFieldValuePayment = new JTextField();
		textFieldValuePayment.setBounds(399, 115, 134, 28);
		panel_1.add(textFieldValuePayment);
		textFieldValuePayment.setColumns(10);
		
		JLabel lblCodigoDeBarras = new JLabel("Codigo de Barras:");
		lblCodigoDeBarras.setBounds(6, 121, 147, 16);
		panel_1.add(lblCodigoDeBarras);
		
		textFieldBarCode = new JTextField();
		textFieldBarCode.setEditable(false);
		textFieldBarCode.setBounds(118, 115, 206, 28);
		panel_1.add(textFieldBarCode);
		textFieldBarCode.setColumns(10);
		
		JLabel lblNumeroDeInscrio = new JLabel("Numero de Inscrição:");
		lblNumeroDeInscrio.setBounds(6, 17, 147, 16);
		panel_1.add(lblNumeroDeInscrio);
		
		comboBoxIdRegistration = new JComboBox<Integer>();
		comboBoxIdRegistration.setBounds(139, 13, 113, 27);
		panel_1.add(comboBoxIdRegistration);
		
		lblData = new JLabel("");
		lblData.setBounds(453, 17, 80, 16);
		panel_1.add(lblData);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 327, 829, 158);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 817, 146);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(74, 296, 117, 29);
		contentPane.add(btnInsert);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(214, 296, 117, 29);
		contentPane.add(btnRemove);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(353, 296, 117, 29);
		contentPane.add(btnUpdate);
		
		btnSelect = new JButton("Selection");
		btnSelect.setBounds(489, 296, 117, 29);
		contentPane.add(btnSelect);
		
		btnClean = new JButton("Clean");
		btnClean.setBounds(630, 296, 117, 29);
		contentPane.add(btnClean);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(320, 237, 117, 29);
		getContentPane().add(btnBack);
		
		btnInsert.addActionListener(insertAction);
		btnRemove.addActionListener(removeAction);
		btnUpdate.addActionListener(updateAction);
		btnSelect.addActionListener(selectAction);
		btnClean.addActionListener(cleanAction);
		btnBack.addActionListener(backAction);
		
		populateTable();
		
		String data = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
		lblData.setText(data);
		
		ItemListener listener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				carregarValorPorIdRegistro();  
				gerarCodigoBarra();
			}
		};
		comboBoxIdRegistration.addItemListener(listener);
		
		
	}
	
	private void carregarValorPorIdRegistro(){
		Integer selecteditem = Integer.parseInt(comboBoxIdRegistration.getSelectedItem().toString());
		
		List<Registration> registrations = new ArrayList<Registration>();
		try {
			registrations = RiSEEventMainScreenP.facade.getRegistrations();
		} catch (RepositoryException ex) {
			JOptionPane.showMessageDialog(getContentPane(),
					ex.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			ex.printStackTrace();
		}
		for(Registration registration : registrations){
			if(registration.getIdRegistration() == selecteditem){
				textFieldValuePayment.setText(Float.toString(registration.getTotalValue()));
			}
		}
	}
	
	
	private void populateTable(){
		try {
			PaymentTableModel model;
			model = new PaymentTableModel(RiSEEventMainScreenP.facade.getPayments());
			table.setModel(model);
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	//ESSA FUNCAO VAI USAR COMO BASE O IDREGISTRO E O VALOR PAGO PARA FAZER UMA SEQUENCIA DE BARCODE
		private void gerarCodigoBarra(){
			
			BarcodeEAN codeEAN = new BarcodeEAN();

	         //O iText suporta os principais tipos de código de barra, como Barcode39,
	         //  Barcode128 (128, 128_UCC, 128_RAW),  BarcodeEAN (EAN13, EAN8, UPCA, UPCE), EANSUP, etc
	         codeEAN.setCodeType(codeEAN.EAN13);
	         codeEAN.setCode(geradorBarcodeNumeral());
	         textFieldBarCode.setText(codeEAN.getCode());
		}
		
		private String geradorBarcodeNumeral(){
			String numeroCodigoBarra = "";
			String numRegistro = comboBoxIdRegistration.getSelectedItem().toString();
			String numValor = textFieldValuePayment.getText();
			numeroCodigoBarra = numerosAleatorios(numRegistro) + numerosAleatorios(numValor);
			return numeroCodigoBarra;
			
			}
			

		private String numerosAleatorios(String s){
				String barcode = "";
				for(int i =0; i<s.length(); i++){
					char c = s.charAt(i);
					if(c == '0'){
						barcode = barcode + "00110";
					}
					if(c == '1'){
						barcode = barcode +"10001";
					}
					if(c == '2'){
						barcode = barcode + "01001";
					}
					if(c == '3'){
						barcode = barcode + "11000";
					}
					if(c == '4'){
						barcode = barcode + "00101";
					}
					if(c == '5'){
						barcode = barcode + "10100";
					}
					if(c == '6'){
						barcode = barcode + "01100";
					}
					if(c == '7'){
						barcode = barcode + "00011";
					}
					if(c == '8'){
						barcode = barcode + "10010";
					}
					if(c == '9'){
						barcode = barcode + "01010";
				}
				
			}
		return barcode;
		}
		
		public void acaoType(Payment paymentout) throws DocumentException, IOException{
			Payment payment = new Payment();
			RiSEEventMainScreenP.facade.typePayment(payment, paymentout);
			
		}		
		
		
		
		//INSERT
		private class InsertButtonAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Payment payment = null;
				
				
				
				Integer registrationId = Integer.parseInt(comboBoxIdRegistration.getSelectedItem().toString());
				String type = comboBoxTypePayment.getSelectedItem().toString();
				String status = comboBoxStatusPayment.getSelectedItem().toString();
				String date = lblData.getText();
				String barcode = textFieldBarCode.getText();
				float value = Float.valueOf(textFieldValuePayment.getText());
				
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
						
						payment.setBarcode(barcode);
						payment.setDate(date);
						payment.setIdRegistration(registrationId);
						payment.setPaymentType(TypePayment.valueOf(type));
						payment.setStatus(StatusPayment.valueOf(status));
						payment.setValue(value);
						
						RiSEEventMainScreenP.facade.insertPayment(payment); 
						PaymentTableModel model = new PaymentTableModel(RiSEEventMainScreenP.facade.getPayments());
							
						acaoType(payment);
						
						model.addPayment(payment);
						table.setModel(model);

						// Limpar campos
						cleanFields();

					} catch (PaymentAlreadyInsertedException e1) {
						JOptionPane
						.showMessageDialog(
								getContentPane(),
								"Já existe um pagamento cadastrado com esse Registro!",
								"Pagamento Existente",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (RepositoryException e1) {
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
		
		private class UpdateButtonAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int rowIndex = table.getSelectedRow();
				try {

					
					Payment payment = null;
					
					Integer registrationId = Integer.parseInt(comboBoxIdRegistration.getSelectedItem().toString());
					String type = comboBoxTypePayment.getSelectedItem().toString();
					String status = comboBoxStatusPayment.getSelectedItem().toString();
					String date = lblData.getText();
					String barcode = textFieldBarCode.getText();
					float value = Float.valueOf(textFieldValuePayment.getText());
					
					//int resultado = 0;
					if ( registrationId == -1 || type.equals("") || status.equals("") || date.equals("")
							|| barcode.equals("") || value == -1) {
						JOptionPane.showMessageDialog(getContentPane(),
								"Não pode haver campo vazio.", "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						return;

					} else {
							
						payment = new Payment();
						
						payment.setBarcode(barcode);
						payment.setDate(date);
						payment.setIdRegistration(registrationId);
						payment.setPaymentType(TypePayment.valueOf(type));
						payment.setStatus(StatusPayment.valueOf(status));
						payment.setValue(value);
						
						try {
							
							RiSEEventMainScreenP.facade.updatePayment(payment);
							PaymentTableModel model;
							model = new PaymentTableModel(RiSEEventMainScreenP.facade.getPayments());
							table.setModel(model);
						} catch (PaymentNotFoundException e1) {
							JOptionPane
							.showMessageDialog(
									getContentPane(),
									"Payment que está tentando alterar não existe!",
									"Payment Inexistente",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						} catch (PaymentAlreadyInsertedException e1) {
							JOptionPane.showMessageDialog(getContentPane(),
									e1.toString(), "Erro",
									JOptionPane.INFORMATION_MESSAGE);
							e1.printStackTrace();
						}
						
					}
					
				} catch (RepositoryException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
		
		private class SelectButtonAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				Payment payment = null;

				try {
					payment=  new PaymentTableModel(RiSEEventMainScreenP.facade.getPayments()).get(rowIndex);
				
					
					comboBoxIdRegistration.setSelectedItem(String.valueOf(payment.getIdRegistration()));
					comboBoxStatusPayment.setSelectedItem(String.valueOf(payment.getStatus()));
					comboBoxTypePayment.setSelectedItem(String.valueOf(payment.getPaymentType()));
					lblData.setText(payment.getDate());
					textFieldBarCode.setText(payment.getBarcode());
					textFieldValuePayment.setText(String.valueOf(payment.getValue()));
				
				} catch (RepositoryException ex) {
					JOptionPane.showMessageDialog(getContentPane(),
							ex.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					ex.printStackTrace();
				}
			}
		}
		
		private class RemoveButtonAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				
				if(rowIndex == -1){
					JOptionPane.showMessageDialog(getContentPane(),
							"Selecione o Payment a ser removido!");
					return;
				}
				
				try {
					Payment payment = new PaymentTableModel(RiSEEventMainScreenP.facade.getPayments()).get(rowIndex);
					RiSEEventMainScreenP.facade.removePayment(payment.getIdPayment());
					PaymentTableModel model = (PaymentTableModel) table.getModel();
					model.removePayment(rowIndex);
					table.setModel(model);
					
					//cleanFields();

				
				} catch (RepositoryException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (PaymentNotFoundException e1) {
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
		
		
		private class CleanButtonAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				
				cleanFields();
			}
		}
		
		private void cleanFields(){
			
			
			comboBoxIdRegistration.setSelectedItem("");
			comboBoxTypePayment.setSelectedItem("");
			comboBoxStatusPayment.setSelectedItem("");
			lblData.setText("");
			textFieldBarCode.setText("");
			textFieldValuePayment.setText("");
			
		}
		
		private class BackButtonAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				PaymentManagementScreenP.instancePaymentManagementScreenP = null;
			}
		}
}
//#endif