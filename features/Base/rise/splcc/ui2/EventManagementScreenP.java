
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import rise.splcc.data.Event;
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.table.EventTableModel;

public class EventManagementScreenP extends JInternalFrame {

	private JPanel contentPane;
	private JTextField eventNametextField;
	private JTextField periodtextField;
	private JTextField placetextField;
	private JTextField institutiontextField;
	private JTextField sponsorstextField;
	private JTable table;
	JButton btnInsert;
	JButton btnUpdate;
	JButton btnRemove;
	JButton btnSelect;
	
	JLabel lblLastEventId;
	
	List<Event> eventList;
	
	 private static EventManagementScreenP instanceEventManagementScreenP;
	 private JButton btnBack;
		
	 public static EventManagementScreenP getInstanceEventManagementScreenP() {
		 if (instanceEventManagementScreenP == null) {
			 EventManagementScreenP.instanceEventManagementScreenP = new EventManagementScreenP();
		 }
		 return EventManagementScreenP.instanceEventManagementScreenP;
	 }
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventManagementScreenP frame = new EventManagementScreenP();
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
	public EventManagementScreenP() {
		
		setTitle("Event Management");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 943,
				526);
		
		setMaximizable(true);
		setClosable(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setIconifiable(true);
		
		//PASSO 1
		InsertButtonAction insertAction = new InsertButtonAction(); 
		RemoveButtonAction removeAction = new RemoveButtonAction(); 
		UpdateButtonAction updateAction = new UpdateButtonAction();
		SelectButtonAction selectAction = new SelectButtonAction(); 
		CleanButtonAction cleanAction = new CleanButtonAction();
		BackButtonAction backAction = new BackButtonAction();

		setTitle("Event Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		JPanel panel = new JPanel();
		panel.setBounds(251, 6, 413, 72);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblImage = new JLabel("");
		lblImage.setBounds(6, 6, 399, 60);
		ImageIcon image = new ImageIcon(getClass().getResource("/images/riseLabs.png"));
		//ImageIcon image = new ImageIcon("/Users/pamsn/Dropbox/00-Doutorado/WorkspaceProjetoSPL/SPLCC/src/images/riseLabs.png");
		Image imag = image.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(imag));
		panel.add(lblImage);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(18, 82, 841, 159);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblEventId = new JLabel("Event Id:");
		lblEventId.setBounds(6, 6, 61, 16);
		panel_1.add(lblEventId);

		lblLastEventId = new JLabel("");
		lblLastEventId.setBounds(79, 6, 61, 16);
		panel_1.add(lblLastEventId);

		JLabel lblEventName = new JLabel("Event Name:");
		lblEventName.setBounds(6, 49, 90, 16);
		panel_1.add(lblEventName);

		eventNametextField = new JTextField();
		eventNametextField.setBounds(89, 43, 385, 28);
		panel_1.add(eventNametextField);
		eventNametextField.setColumns(10);

		JLabel lblPeriod = new JLabel("Period:");
		lblPeriod.setBounds(500, 49, 61, 16);
		panel_1.add(lblPeriod);

		periodtextField = new JTextField();
		periodtextField.setBounds(560, 43, 261, 28);
		panel_1.add(periodtextField);
		periodtextField.setColumns(10);

		JLabel lblPlace = new JLabel("Place:");
		lblPlace.setBounds(6, 89, 61, 16);
		panel_1.add(lblPlace);

		placetextField = new JTextField();
		placetextField.setBounds(50, 83, 261, 28);
		panel_1.add(placetextField);
		placetextField.setColumns(10);

		JLabel lblInstitution = new JLabel("Institution:");
		lblInstitution.setBounds(337, 89, 90, 16);
		panel_1.add(lblInstitution);

		institutiontextField = new JTextField();
		institutiontextField.setBounds(423, 83, 398, 28);
		panel_1.add(institutiontextField);
		institutiontextField.setColumns(10);

		JLabel lblSponsors = new JLabel("Sponsors:");
		lblSponsors.setBounds(6, 131, 74, 16);
		panel_1.add(lblSponsors);

		sponsorstextField = new JTextField();
		sponsorstextField.setBounds(89, 125, 338, 28);
		panel_1.add(sponsorstextField);
		sponsorstextField.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(18, 279, 841, 159);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 829, 147);
		panel_2.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		btnInsert = new JButton("Insert");
		btnInsert.setBounds(46, 248, 117, 29);
		contentPane.add(btnInsert);

		btnRemove = new JButton("Remove");
		btnRemove.setBounds(179, 248, 117, 29);
		contentPane.add(btnRemove);

		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(314, 248, 117, 29);
		contentPane.add(btnUpdate);

		JButton btnClean = new JButton("Clean");
		btnClean.setBounds(572, 248, 117, 29);
		contentPane.add(btnClean);

		btnSelect = new JButton("Select");
		btnSelect.setBounds(443, 248, 117, 29);
		contentPane.add(btnSelect);

		btnBack = new JButton("Back");
		btnBack.setBounds(702, 248, 117, 29);
		contentPane.add(btnBack);

		//PASSO 2
		btnInsert.addActionListener(insertAction);
		btnRemove.addActionListener(removeAction);
		btnUpdate.addActionListener(updateAction);
		btnSelect.addActionListener(selectAction);
		btnClean.addActionListener(cleanAction);
		btnBack.addActionListener(backAction);

		loadLastIndex();


		populateTable();

	}

	//PASSO 3

	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			EventManagementScreenP.instanceEventManagementScreenP= null;
		}

	}


	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			//						int confirm = JOptionPane.showConfirmDialog(getContentPane(),
			//								"Deseja cadastrar um novo Evento?",
			//								"Cadastrar Evento", JOptionPane.YES_NO_OPTION);
			//						if (confirm != 0) {
			//							return;
			//						}

			Event event = null;

			String eventName = eventNametextField.getText();
			String period = periodtextField.getText();
			String place  = placetextField.getText();
			String institution = institutiontextField.getText();
			String sponsors = sponsorstextField.getText();

			//int resultado = 0;
			if (eventName.equals("") || period.equals("") || place.equals("")
					|| institution.equals("") || sponsors.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {					
				try {

					event = new Event();
					event.setEventName(eventName);
					event.setPeriod(period);
					event.setPlace(place);
					event.setInstitution(institution);
					event.setSponsors(sponsors);

					//Atualizar JTable
					EventTableModel model = new EventTableModel(RiSEEventMainScreenP.facade.getEvents());

					RiSEEventMainScreenP.facade.insertEvent(event); //isso obriga que o programa seja executado a partir da tela main screen, caso ele seja iniciado da tela de login ficaria RISEEVENTLOGINSCREEN.facade....


					//(EventTableModel) table.getModel();
					model.addEvent(event);
					table.setModel(model);

					// Limpar campos
					cleanFields();

				} catch (EventAlreadyInsertedException e1) {
					JOptionPane
					.showMessageDialog(
							getContentPane(),
							"Já existe um evento cadastrado com esse Registro!",
							"Evento Existente",
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
						"Selecione o Evento a ser removido!");
				return;
			}

			try {
				Event event = new EventTableModel(RiSEEventMainScreenP.facade.getEvents()).get(rowIndex);
				RiSEEventMainScreenP.facade.removeEvent(event.getIdEvent());
				EventTableModel model = (EventTableModel) table.getModel();
				model.removeEvent(rowIndex);
				table.setModel(model);

				cleanFields();

			} catch (EventNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (EventAlreadyInsertedException e1) {
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


				String eventName = eventNametextField.getText();
				String period = periodtextField.getText();
				String place = placetextField.getText();
				String institution = institutiontextField.getText();
				String sponsors = sponsorstextField.getText();

				if (eventName.equals("") || period.equals("") || place.equals("")
						|| institution.equals("") || sponsors.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Não pode haver campo vazio.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;

				} else {
					Event eventNew = new Event();
					eventNew.setIdEvent(Integer.parseInt(lblLastEventId.getText()));
					eventNew.setEventName(eventName);
					eventNew.setPeriod(period);
					eventNew.setPlace(place);
					eventNew.setInstitution(institution);
					eventNew.setSponsors(sponsors);

					try {
						RiSEEventMainScreenP.facade.updateEvent(eventNew);
						EventTableModel model;
						model = new EventTableModel(RiSEEventMainScreenP.facade.getEvents());
						table.setModel(model);
					} catch (EventNotFoundException e1) {
						JOptionPane
						.showMessageDialog(
								getContentPane(),
								"Evento que está tentando alterar não existe!",
								"Evento Inexistente",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (EventAlreadyInsertedException e1) {
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
			Event eventOld = null;

			try {
				eventOld=  new EventTableModel(RiSEEventMainScreenP.facade.getEvents()).get(rowIndex);

				lblLastEventId.setText(String.valueOf(eventOld.getIdEvent()));
				eventNametextField.setText(eventOld.getEventName());
				periodtextField.setText(eventOld.getPeriod());
				placetextField.setText(eventOld.getPlace());
				institutiontextField.setText(eventOld.getInstitution());
				sponsorstextField.setText(eventOld.getSponsors());
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

	private void cleanFields() {
		eventNametextField.setText("");
		periodtextField.setText("");
		placetextField.setText("");
		institutiontextField.setText("");
		sponsorstextField.setText("");
		btnInsert.setEnabled(true);
	}


	private void loadLastIndex(){
		try {
			lblLastEventId.setText(String.valueOf(RiSEEventMainScreenP.facade.getEventLastId()));
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}

	private void populateTable(){
		try {
			EventTableModel model;
			model = new EventTableModel(RiSEEventMainScreenP.facade.getEvents());
			table.setModel(model);
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}

}
