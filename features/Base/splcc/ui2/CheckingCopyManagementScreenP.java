//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package rise.splcc.ui2;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import rise.splcc.data.CheckingCopy;
import rise.splcc.data.CheckingCopy.TypeCheckingCopy;
import rise.splcc.data.Registration;
import rise.splcc.data.User;
import rise.splcc.exception.CheckingCopyAlreadyInsertedException;
import rise.splcc.exception.CheckingCopyNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.table.CheckingCopyTableModel;

public class CheckingCopyManagementScreenP extends JInternalFrame{

	private static CheckingCopyManagementScreenP instanceCheckingCopyManagementScreenP;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	
	JComboBox<String> typeComboBox;
	JComboBox<String> comboBoxRegistrationId;
	//Retirada de login
	JComboBox comboBoxUser;
	
	JButton btnInsert;
	JButton btnRemove;
	JButton btnUpdate;
	JButton btnSelect;
	JButton btnClean;
	JButton btnBack;
	
	JLabel lblLastCheckingCopyId;
	private JLabel lblUserId;
	private JLabel lblIdUserLogado;
	private JTextField textFieldDate;
	

	 public static CheckingCopyManagementScreenP getInstanceCheckingCopyManagementScreenP() {
		 if (instanceCheckingCopyManagementScreenP == null) {
			 CheckingCopyManagementScreenP.instanceCheckingCopyManagementScreenP = new CheckingCopyManagementScreenP();
		 }
		 return CheckingCopyManagementScreenP.instanceCheckingCopyManagementScreenP;
	 }
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckingCopyManagementScreenP frame = new CheckingCopyManagementScreenP();
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
	public CheckingCopyManagementScreenP() {
		
		InsertButtonAction insertAction = new InsertButtonAction(); 
		RemoveButtonAction removeAction = new RemoveButtonAction(); 
		UpdateButtonAction updateAction = new UpdateButtonAction();
		SelectButtonAction selectAction = new SelectButtonAction(); 
		CleanButtonAction cleanAction = new CleanButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		//Retirada de Login
		SelectComboUserAction selectUserAction = new SelectComboUserAction();
		
		setTitle("Checking Copy Management");
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
		panel_1.setBounds(6, 99, 829, 189);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
	
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(6, 327, 829, 158);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblCheckingCopyId = new JLabel("Checking Copy ID:");
		lblCheckingCopyId.setBounds(6, 20, 120, 16);
		panel_1.add(lblCheckingCopyId);
		
		lblLastCheckingCopyId = new JLabel("");
		lblLastCheckingCopyId.setBounds(128, 20, 61, 16);
		panel_1.add(lblLastCheckingCopyId);
		
		lblUserId = new JLabel("User Id:");
		lblUserId.setBounds(180, 20, 61, 16);
		panel_1.add(lblUserId);
		
		lblIdUserLogado = new JLabel("");
		lblIdUserLogado.setBounds(238, 20, 61, 16);
		panel_1.add(lblIdUserLogado);
		
		typeComboBox = new JComboBox<String>();
		typeComboBox.setBounds(248, 83, 134, 27);
		panel_1.add(typeComboBox);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(205, 87, 61, 16);
		panel_1.add(lblType);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(6, 87, 76, 16);
		panel_1.add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(50, 81, 134, 28);
		panel_1.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JLabel lblRegistration = new JLabel("Registration ID:");
		lblRegistration.setBounds(434, 24, 104, 16);
		panel_1.add(lblRegistration);
		
		comboBoxRegistrationId = new JComboBox<String>();
		comboBoxRegistrationId.setBounds(543, 20, 134, 27);
		panel_1.add(comboBoxRegistrationId);
		
		//REtirada Login
		comboBoxUser = new JComboBox();
		comboBoxUser.setBounds(262, 16, 139, 24);
		panel_1.add(comboBoxUser);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 817, 146);
		panel_2.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(6, 297, 117, 29);
		contentPane.add(btnInsert);
		
		btnRemove = new JButton("Remove");
		btnRemove.setBounds(146, 297, 117, 29);
		contentPane.add(btnRemove);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(285, 297, 117, 29);
		contentPane.add(btnUpdate);
		
		btnSelect = new JButton("Selection");
		btnSelect.setBounds(421, 297, 117, 29);
		contentPane.add(btnSelect);
		
		btnClean = new JButton("Clean");
		btnClean.setBounds(562, 297, 117, 29);
		contentPane.add(btnClean);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(694, 297, 117, 29);
		contentPane.add(btnBack);
		
		btnInsert.addActionListener(insertAction);
		btnRemove.addActionListener(removeAction);
		btnUpdate.addActionListener(updateAction);
		btnSelect.addActionListener(selectAction);
		btnClean.addActionListener(cleanAction);
		btnBack.addActionListener(backAction);
		// Retirada de Login
		comboBoxUser.addActionListener(selectUserAction);
		
		//Retirada do login
		//pegarUsuarioLogado();
		
		//Retirada Login
		carregarComboUser();
		
		loadLastIndex();
		
		carregarComboBoxTipo();
		carregarComboBoxIdRegistration();
		
		populateTable();
		
		
		
	}
	
	//Retirada Login
	private void carregarComboUser(){
		try {
			List<User> list;
			list = RiSEEventMainScreenP.facade.getUsers();
			Iterator<User> iterator = list.iterator();
			while(iterator.hasNext()){
				comboBoxUser.addItem(iterator.next().getNameUser());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
	}
	//Retirada de login
	private class SelectComboUserAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			try {
				String nameUser = comboBoxUser.getSelectedItem().toString();
				lblIdUserLogado.setText(String.valueOf(RiSEEventMainScreenP.facade.getUserIdByName(nameUser)));
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
		}
	}
	
	
	private void loadLastIndex(){
		try {
			lblLastCheckingCopyId.setText(String.valueOf(RiSEEventMainScreenP.facade.getCheckingCopyLastId()));
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	// retirada parte do login, adicionado um combo para a selecao do usuario.
//	private void pegarUsuarioLogado(){
//		lblIdUserLogado.setText(String.valueOf(RiSEEventLoginScreen.usuarioLogado.getIdUser()));
//	}
	
	private void carregarComboBoxTipo(){
		TypeCheckingCopy[] types = TypeCheckingCopy.values();
		List<String> typescheckingcopys = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			typescheckingcopys.add(i, types[i].name());
			typeComboBox.addItem(types[i].name());
		}
		
	}
	
	private void carregarComboBoxIdRegistration(){
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
			if(lblIdUserLogado.getText().equals(String.valueOf(registration.getIdUser()))){
				comboBoxRegistrationId.addItem(String.valueOf(registration.getIdRegistration()));
			}
			
		}
	}
	
	private void populateTable(){
		try {
			CheckingCopyTableModel model;
			model = new CheckingCopyTableModel(RiSEEventMainScreenP.facade.getCheckingCopys());
			table.setModel(model);
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void cleanFields() {
		textFieldDate.setText("");
		btnInsert.setEnabled(true);
	}
	
	//INSERINDO UM CHECKING COPY 
		private class InsertButtonAction  implements ActionListener{ 

			@Override
			public void actionPerformed(ActionEvent e) {

				
				CheckingCopy checkingCopy = null;
				
				Integer registrationId = Integer.parseInt(comboBoxRegistrationId.getSelectedItem().toString());
				TypeCheckingCopy type = TypeCheckingCopy.valueOf(typeComboBox.getSelectedItem().toString());
				// ISSO DEVERIA PEGAR O LABEL DE LBLUSERID OU LBLUSERLOGADO? EU ACHO Q O LOGADO
				//Integer userId = Integer.parseInt(lblUserId.getText());
				Integer userId = Integer.parseInt(lblIdUserLogado.getText());
				String date = textFieldDate.getText();
				
				//int resultado = 0;
				if (registrationId.equals("") || type.equals("") || userId.equals("")
						|| date.equals("") ) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Não pode haver campo vazio.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {					
					try {
						
						checkingCopy = new CheckingCopy();
						checkingCopy.setIdRegistration(registrationId);
						checkingCopy.setCheckingCopyType(type);
						checkingCopy.setIdUser(userId);
						checkingCopy.setDateOfIssue(date);
						
						//Atualizar JTable
						CheckingCopyTableModel model = new CheckingCopyTableModel(RiSEEventMainScreenP.facade.getCheckingCopys());
						
						RiSEEventMainScreenP.facade.insertCheckingCopy(checkingCopy); //isso obriga que o programa seja executado a partir da tela main screen, caso ele seja iniciado da tela de login ficaria RISEEVENTLOGINSCREEN.facade....
					
						model.addCheckingCopy(checkingCopy);
						table.setModel(model);

						// Limpar campos
						cleanFields();

					} catch (CheckingCopyAlreadyInsertedException e1) {
						JOptionPane
						.showMessageDialog(
								getContentPane(),
								"Já existe um comprovante cadastrada com esse Registro!",
								"Comprovante Existente",
								JOptionPane.ERROR_MESSAGE);
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
							"Selecione o Comprovante a ser removido!");
					return;
				}
				
				try {
					CheckingCopy checkingCopy = new CheckingCopyTableModel(RiSEEventMainScreenP.facade.getCheckingCopys()).get(rowIndex);
					RiSEEventMainScreenP.facade.removeCheckingCopy(checkingCopy.getIdCheckingCopy());
					CheckingCopyTableModel model = (CheckingCopyTableModel) table.getModel();
					model.removeCheckingCopy(rowIndex);
					table.setModel(model);
					
					cleanFields();

				} catch (CheckingCopyNotFoundException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (RepositoryException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (CheckingCopyAlreadyInsertedException e1) {
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
					
					Integer registrationId = Integer.parseInt(comboBoxRegistrationId.getSelectedItem().toString());
					TypeCheckingCopy type = TypeCheckingCopy.valueOf(typeComboBox.getSelectedItem().toString());
					// ISSO DEVERIA PEGAR O LABEL DE LBLUSERID OU LBLUSERLOGADO? EU ACHO Q O LOGADO
					//Integer userId = Integer.parseInt(lblUserId.getText());
					Integer userId = Integer.parseInt(lblIdUserLogado.getText());
					String date = textFieldDate.getText();
					
					if (registrationId.equals("") || type.equals("") || userId.equals("")
							|| date.equals("") ) {
						JOptionPane.showMessageDialog(getContentPane(),
								"Não pode haver campo vazio.", "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						
						CheckingCopy checkingCopyNew = new CheckingCopy();
						checkingCopyNew.setIdRegistration(registrationId);
						checkingCopyNew.setCheckingCopyType(type);
						checkingCopyNew.setIdUser(userId);
						checkingCopyNew.setDateOfIssue(date);
						
						try {
							RiSEEventMainScreenP.facade.updateCheckingCopy(checkingCopyNew);
							CheckingCopyTableModel model;
							model = new CheckingCopyTableModel(RiSEEventMainScreenP.facade.getCheckingCopys());
							table.setModel(model);
						} catch (CheckingCopyNotFoundException e1) {
							JOptionPane
							.showMessageDialog(
									getContentPane(),
									"Comprovante que está tentando alterar não existe!",
									"Comprovante Inexistente",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						} catch (CheckingCopyAlreadyInsertedException e1) {
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
			CheckingCopy checkingCopyOld = null;

			try {
				checkingCopyOld=  new CheckingCopyTableModel(RiSEEventMainScreenP.facade.getCheckingCopys()).get(rowIndex);
			
				lblLastCheckingCopyId.setText(String.valueOf(checkingCopyOld.getIdCheckingCopy()));
				// ISSO DEVERIA PEGAR O LABEL DE LBLUSERID OU LBLUSERLOGADO? EU ACHO Q O LOGADO
				//lblUserId.setText(String.valueOf(checkingCopyOld.getIdUser()));
				lblIdUserLogado.setText(String.valueOf(checkingCopyOld.getIdUser()));
				comboBoxRegistrationId.setSelectedItem(checkingCopyOld.getIdRegistration());
				typeComboBox.setSelectedItem(checkingCopyOld.getCheckingCopyType());
				textFieldDate.setText(checkingCopyOld.getDateOfIssue());
				
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
		loadLastIndex();
			
		}
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			dispose();
			CheckingCopyManagementScreenP.instanceCheckingCopyManagementScreenP = null;
		}
	}
}
//#endif