//#if ${Receipt} == "T" 
package rise.splcc.ui2;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
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

import com.lowagie.text.DocumentException;

import rise.splcc.data.Payment;
import rise.splcc.data.Receipt;
import rise.splcc.exception.PaymentAlreadyInsertedException;
import rise.splcc.exception.PaymentNotFoundException;
import rise.splcc.exception.ReceiptAlreadyInsertedException;
import rise.splcc.exception.ReceiptNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.table.ReceiptTableModel;

public class ReceiptScreenP extends JInternalFrame{

	private static ReceiptScreenP instanceReceiptScreenP;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JComboBox comboBoxIdPayment;
	
	private JButton btnInsert;
	private JButton btnRemove;
	private JButton btnSelect;
	private JButton btnClean;
	private JButton btnBack;
	private JTextField textFieldTotalValue;
	private JTextField textFieldDateOfIssue;
	
	private JLabel lblLastReceiptId;
	

	 public static ReceiptScreenP getInstanceReceiptScreenP() {
		 if (instanceReceiptScreenP == null) {
			 ReceiptScreenP.instanceReceiptScreenP = new ReceiptScreenP();
		 }
		 return ReceiptScreenP.instanceReceiptScreenP;
	 }
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceiptScreenP frame = new ReceiptScreenP();
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
	public ReceiptScreenP() {
		
		InsertButtonAction insertAction = new InsertButtonAction(); 
		RemoveButtonAction removeAction = new RemoveButtonAction(); 
		SelectButtonAction selectAction = new SelectButtonAction(); 
		CleanButtonAction cleanAction = new CleanButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		setTitle("Receipt Management");
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
		
		JLabel lblPaymentId = new JLabel("Payment Id:");
		lblPaymentId.setBounds(6, 56, 103, 16);
		panel_1.add(lblPaymentId);
		
		comboBoxIdPayment = new JComboBox();
		comboBoxIdPayment.setEnabled(false);
		comboBoxIdPayment.setBounds(87, 52, 181, 27);
		panel_1.add(comboBoxIdPayment);
		
		JLabel lblTotalValue = new JLabel("Total Value:");
		lblTotalValue.setBounds(280, 57, 92, 16);
		panel_1.add(lblTotalValue);
		
		JLabel lblReceiptId = new JLabel("Receipt Id:");
		lblReceiptId.setBounds(6, 16, 75, 16);
		panel_1.add(lblReceiptId);
		
		lblLastReceiptId = new JLabel("");
		lblLastReceiptId.setBounds(74, 16, 61, 16);
		panel_1.add(lblLastReceiptId);
		
		textFieldTotalValue = new JTextField();
		textFieldTotalValue.setEnabled(false);
		textFieldTotalValue.setBounds(360, 51, 134, 28);
		panel_1.add(textFieldTotalValue);
		textFieldTotalValue.setColumns(10);
		
		JLabel lblDateOfIssue = new JLabel("Date Of Issue:");
		lblDateOfIssue.setBounds(6, 115, 91, 16);
		panel_1.add(lblDateOfIssue);
		
		textFieldDateOfIssue = new JTextField();
		textFieldDateOfIssue.setEnabled(false);
		textFieldDateOfIssue.setBounds(109, 109, 134, 28);
		panel_1.add(textFieldDateOfIssue);
		textFieldDateOfIssue.setColumns(10);
		
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
		
		btnSelect = new JButton("Selection");
		btnSelect.setBounds(343, 296, 117, 29);
		contentPane.add(btnSelect);
		
		btnClean = new JButton("Clean");
		btnClean.setBounds(495, 296, 117, 29);
		contentPane.add(btnClean);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(624, 296, 117, 29);
		contentPane.add(btnBack);
		
		btnInsert.addActionListener(insertAction);
		btnRemove.addActionListener(removeAction);
		btnSelect.addActionListener(selectAction);
		btnClean.addActionListener(cleanAction);
		btnBack.addActionListener(backAction);
		
		
		loadLastIndex();
		populateTable();
		carregarComboBoxIdPayment();
	
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Receipt receipt = new Receipt();
			Receipt receipt2 = new Receipt();
			Payment payment = new Payment();
			
			String idPayment = comboBoxIdPayment.getSelectedItem().toString();
			String totalValue = textFieldTotalValue.getText();
			String date = textFieldDateOfIssue.getText();
			receipt2.setDateOfIssue(date);
			receipt2.setIdPayment(Integer.valueOf(idPayment));
			receipt2.setTotalValue(Float.valueOf(totalValue));
			
			try {
				
				payment = RiSEEventMainScreenP.facade.searchPayment(receipt2.getIdPayment());
				receipt.gerarRecibo(payment);
			} catch (PaymentNotFoundException | RepositoryException
					| PaymentAlreadyInsertedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private class SelectButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = table.getSelectedRow();
			Receipt receipt = null;

			try {
				receipt=  new ReceiptTableModel(RiSEEventMainScreenP.facade.getReceipts()).get(rowIndex);
			
				lblLastReceiptId.setText(String.valueOf(receipt.getIdReceipt()));
				comboBoxIdPayment.setSelectedItem(String.valueOf(receipt.getIdPayment()));
				textFieldDateOfIssue.setText(receipt.getDateOfIssue());
				textFieldTotalValue.setText(String.valueOf(receipt.getTotalValue()));
			
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
						"Selecione o Receipt a ser removido!");
				return;
			}
			
			try {
				Receipt payment = new ReceiptTableModel(RiSEEventMainScreenP.facade.getReceipts()).get(rowIndex);
				RiSEEventMainScreenP.facade.removeReceipt(payment.getIdReceipt());
				ReceiptTableModel model = (ReceiptTableModel) table.getModel();
				model.removeReceipt(rowIndex);
				table.setModel(model);
				
				cleanFields();

			
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ReceiptNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ReceiptAlreadyInsertedException e1) {
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
		
		
		comboBoxIdPayment.setSelectedItem("");
		textFieldDateOfIssue.setText("");
		textFieldTotalValue.setText("");
		lblLastReceiptId.setText("");
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ReceiptScreenP.instanceReceiptScreenP = null;
		}
	}
	
	private void populateTable(){
		try {
			ReceiptTableModel model;
			model = new ReceiptTableModel(RiSEEventMainScreenP.facade.getReceipts());
			table.setModel(model);
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void carregarComboBoxIdPayment(){
			try {
				List<Receipt> list = RiSEEventMainScreenP.facade.getReceipts();
				Iterator<Receipt> iterator = list.iterator();
				while(iterator.hasNext()){
					comboBoxIdPayment.addItem(iterator.next().getIdReceipt());
				}
			} catch (RepositoryException e) {
				JOptionPane.showMessageDialog(getContentPane(),
						e.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
			}	
	}
	
	private void loadLastIndex(){
		try {
			lblLastReceiptId.setText(String.valueOf(RiSEEventMainScreenP.facade.getReceiptLastId()));
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
}
//#endif