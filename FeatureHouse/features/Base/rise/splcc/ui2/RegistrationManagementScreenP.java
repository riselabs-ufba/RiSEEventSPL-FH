package rise.splcc.ui2;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import rise.splcc.data.Registration;
import rise.splcc.exception.RegistrationAlreadyInsertedException;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.table.RegistrationTableModel;

public class RegistrationManagementScreenP extends JInternalFrame{

	private static RegistrationManagementScreenP instanceRegistrationManagementScreenP;
	private JTextField textFieldTotalValue;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	
	private JButton btnBack;
	
	JButton btnInsert;
	JButton btnRemove;
	JButton btnUpdate;
	JButton btnSelect;
	JButton btnClean;
	
	JLabel lblLastRegistrationId;
	private JTextField textFieldUserId;
	

	 public static RegistrationManagementScreenP getInstanceRegistrationManagementScreenP() {
		 if (instanceRegistrationManagementScreenP == null) {
			 RegistrationManagementScreenP.instanceRegistrationManagementScreenP = new RegistrationManagementScreenP();
		 }
		 return RegistrationManagementScreenP.instanceRegistrationManagementScreenP;
	 }
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrationManagementScreenP frame = new RegistrationManagementScreenP();
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
	public RegistrationManagementScreenP() {
		
		InsertButtonAction insertAction = new InsertButtonAction(); 
		RemoveButtonAction removeAction = new RemoveButtonAction(); 
		UpdateButtonAction updateAction = new UpdateButtonAction();
		SelectButtonAction selectAction = new SelectButtonAction(); 
		CleanButtonAction cleanAction = new CleanButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		setTitle("Registration Management");
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
		panel_1.setBounds(6, 99, 789, 189);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblRegistrationId = new JLabel("Registration Id:");
		lblRegistrationId.setBounds(6, 6, 111, 16);
		panel_1.add(lblRegistrationId);
		
		lblLastRegistrationId = new JLabel("");
		lblLastRegistrationId.setBounds(79, 6, 61, 16);
		panel_1.add(lblLastRegistrationId);
		
		JLabel lblTotalValue = new JLabel("Total Value:");
		lblTotalValue.setBounds(367, 49, 86, 16);
		panel_1.add(lblTotalValue);
		
		textFieldTotalValue = new JTextField();
		textFieldTotalValue.setBounds(465, 43, 134, 28);
		panel_1.add(textFieldTotalValue);
		textFieldTotalValue.setColumns(10);
		
		textFieldUserId = new JTextField();
		textFieldUserId.setBounds(89, 43, 134, 28);
		panel_1.add(textFieldUserId);
		textFieldUserId.setColumns(10);
		
		JLabel labelLastRegistrationId = new JLabel("");
		labelLastRegistrationId.setBounds(145, 6, 61, 16);
		panel_1.add(labelLastRegistrationId);
		
		JLabel lblUserId = new JLabel("User Id");
		lblUserId.setBounds(6, 49, 61, 16);
		panel_1.add(lblUserId);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 327, 829, 158);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 800, 121);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(26, 296, 117, 29);
		contentPane.add(btnInsert);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(166, 296, 117, 29);
		contentPane.add(btnRemove);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(305, 296, 117, 29);
		contentPane.add(btnUpdate);
		
		btnSelect = new JButton("Selection");
		btnSelect.setBounds(441, 296, 117, 29);
		contentPane.add(btnSelect);
		
		btnClean = new JButton("Clean");
		btnClean.setBounds(570, 296, 117, 29);
		contentPane.add(btnClean);
		
		btnInsert.addActionListener(insertAction);
		btnRemove.addActionListener(removeAction);
		btnUpdate.addActionListener(updateAction);
		btnSelect.addActionListener(selectAction);
		btnClean.addActionListener(cleanAction);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(694, 296, 117, 29);
		getContentPane().add(btnBack);
		
		btnBack.addActionListener(backAction);

		
		loadLastIndex();
		
		populateTable();
		
		
	}
	
	private void loadLastIndex(){
		try {
			lblLastRegistrationId.setText(String.valueOf(RiSEEventMainScreenP.facade.getRegistrationLastId()));
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void populateTable(){
		try {
			RegistrationTableModel model;
			model = new RegistrationTableModel(RiSEEventMainScreenP.facade.getRegistrations());
			table.setModel(model);
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void cleanFields() {
		textFieldTotalValue.setText("");
		textFieldUserId.setText("");
		loadLastIndex();
		btnInsert.setEnabled(true);
	}
	
	
	//INSERINDO UMA REVIEW
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			
			Registration registration = null;
			
			Integer userId = Integer.valueOf(textFieldUserId.getText());
			String value = textFieldTotalValue.getText();
			
			//int resultado = 0;
			if (userId == 0 ||  value.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {					
				try {
					
					registration = new Registration();
					registration.setIdUser(userId);
					registration.setTotalValue(Float.valueOf(value));
					
					//Atualizar JTable
					RegistrationTableModel model = new RegistrationTableModel(RiSEEventMainScreenP.facade.getRegistrations());
					
					RiSEEventMainScreenP.facade.insertRegistration(registration); //isso obriga que o programa seja executado a partir da tela main screen, caso ele seja iniciado da tela de login ficaria RISEEVENTLOGINSCREEN.facade....
				

							//(RegistrationTableModel) table.getModel();
					model.addRegistration(registration);
					table.setModel(model);

					// Limpar campos
					cleanFields();

				} catch (RegistrationAlreadyInsertedException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (RepositoryException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
				
				
			}

			
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = table.getSelectedRow();
			
			if(rowIndex == -1){
				JOptionPane.showMessageDialog(getContentPane(),
						"Selecione o Registro a ser removido!");
				return;
			}
			
			try {
				Registration registration = new RegistrationTableModel(RiSEEventMainScreenP.facade.getRegistrations()).get(rowIndex);
				RiSEEventMainScreenP.facade.removeRegistration(registration.getIdRegistration());
				RegistrationTableModel model = (RegistrationTableModel) table.getModel();
				model.removeRegistration(rowIndex);
				table.setModel(model);
				
				cleanFields();

			} catch (RegistrationNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RegistrationAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
		}
	}
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
		int rowIndex = table.getSelectedRow();
			
			
			try {

				
				Integer userId = Integer.valueOf(textFieldUserId.getText());
				String value = textFieldTotalValue.getText();
				
				if (userId == 0 ||  value.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Não pode haver campo vazio.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {					
					
					Registration registrationNew = new Registration();
					registrationNew = new Registration();
					registrationNew.setIdUser(userId);
					registrationNew.setTotalValue(Float.valueOf(value)); {
					
					
					
					try {
						RiSEEventMainScreenP.facade.updateRegistration(registrationNew);
						RegistrationTableModel model;
						model = new RegistrationTableModel(RiSEEventMainScreenP.facade.getRegistrations());
						table.setModel(model);
					} catch (RegistrationNotFoundException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e1.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					} catch (RegistrationAlreadyInsertedException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e1.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}
					
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
			Registration registrationOld = null;

			try {
				registrationOld=  new RegistrationTableModel(RiSEEventMainScreenP.facade.getRegistrations()).get(rowIndex);
			
				lblLastRegistrationId.setText(String.valueOf(registrationOld.getIdRegistration()));
				textFieldUserId.setText(String.valueOf(registrationOld.getIdUser()));
				textFieldTotalValue.setText(String.valueOf(registrationOld.getTotalValue()));
				
			} catch (RepositoryException ex) {
				JOptionPane.showMessageDialog(getContentPane(),
						ex.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				ex.printStackTrace();
			}
			
		}
	}
	
	private class CleanButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
		cleanFields();
			
		}
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			RegistrationManagementScreenP.instanceRegistrationManagementScreenP = null;
		}
	}
}
