//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
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

import rise.splcc.data.Activity;
import rise.splcc.data.Event;
import rise.splcc.data.Activity.TypeActivity;
import rise.splcc.exception.ActivityAlreadyInsertedException;
import rise.splcc.exception.ActivityNotFoundException;
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.table.ActivityTableModel;

public class ActivityManagementScreenP extends JInternalFrame {

	
	private static ActivityManagementScreenP instanceActivityManagementScreenP;
	private JPanel contentPane;
	private JTextField textFieldActivityName;
	private JTextField textFieldDescription;
	private JTextField textFieldStatus;
	private JTextField textFieldValue;
	private JTextField textFieldHourlyLoad;
	private JTextField textFieldDate;
	private JTextField textFieldHour;
	private JTextField textFieldNofParticipants;
	private JTextField textFieldRegistrationLimit;
	private JTable table;
	
	private JLabel lblLastIdActivity;
	
	private JComboBox comboBoxActivityType;
	private JComboBox comboBoxEvent;
	
	private JButton btnBack;
	public static ActivityManagementScreenP getInstanceActivityManagementScreenP() {
		if (instanceActivityManagementScreenP == null) {
			ActivityManagementScreenP.instanceActivityManagementScreenP = new ActivityManagementScreenP();
		}
		return ActivityManagementScreenP.instanceActivityManagementScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityManagementScreenP frame = new ActivityManagementScreenP();
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
	public ActivityManagementScreenP() {
		setTitle("Activity Management");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 1003,800);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		//PASSO 1
				InsertButtonAction insertAction = new InsertButtonAction(); 
				RemoveButtonAction removeAction = new RemoveButtonAction(); 
				UpdateButtonAction updateAction = new UpdateButtonAction();
				SelectButtonAction selectAction = new SelectButtonAction(); 
				CleanButtonAction cleanAction = new CleanButtonAction();
				BackButtonAction backAction = new BackButtonAction();
						
				
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(100, 100, 728, 480);
				contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				setContentPane(contentPane);
				contentPane.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setLayout(null);
				panel.setBounds(147, 17, 413, 72);
				contentPane.add(panel);
				
				JLabel label = new JLabel("");
				label.setBounds(6, 6, 399, 60);
				ImageIcon image = new ImageIcon(getClass().getResource("/images/riseLabs.png"));
				Image imag = image.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
				label.setIcon(new ImageIcon(imag));
				panel.add(label);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBounds(6, 97, 716, 164);
				contentPane.add(panel_1);
				panel_1.setLayout(null);
				
				JLabel lblActivityId = new JLabel("Activity Id:");
				lblActivityId.setBounds(6, 6, 68, 16);
				panel_1.add(lblActivityId);
				
				lblLastIdActivity = new JLabel("");
				lblLastIdActivity.setBounds(87, 6, 61, 16);
				panel_1.add(lblLastIdActivity);
				
				comboBoxEvent = new JComboBox();
				comboBoxEvent.setBounds(259, 2, 451, 27);
				panel_1.add(comboBoxEvent);
				
				JLabel lblEvent = new JLabel("Event:");
				lblEvent.setBounds(216, 6, 61, 16);
				panel_1.add(lblEvent);
				
				JLabel lblName = new JLabel("Name:");
				lblName.setBounds(6, 37, 61, 16);
				panel_1.add(lblName);
				
				textFieldActivityName = new JTextField();
				textFieldActivityName.setBounds(50, 34, 209, 27);
				panel_1.add(textFieldActivityName);
				textFieldActivityName.setColumns(10);
				
				JLabel lblDescription = new JLabel("Description:");
				lblDescription.setBounds(269, 37, 83, 16);
				panel_1.add(lblDescription);
				
				textFieldDescription = new JTextField();
				textFieldDescription.setBounds(364, 31, 346, 27);
				panel_1.add(textFieldDescription);
				textFieldDescription.setColumns(10);
				
				JLabel lblActivityType = new JLabel("Activity Type:");
				lblActivityType.setBounds(6, 65, 92, 16);
				panel_1.add(lblActivityType);
				
				comboBoxActivityType = new JComboBox<String>();
				comboBoxActivityType.setBounds(99, 61, 156, 27);
				panel_1.add(comboBoxActivityType);
				
				JLabel lblStatus = new JLabel("Status:");
				lblStatus.setBounds(279, 65, 61, 16);
				panel_1.add(lblStatus);
				
				textFieldStatus = new JTextField();
				textFieldStatus.setBounds(335, 59, 134, 28);
				panel_1.add(textFieldStatus);
				textFieldStatus.setColumns(10);
				
				JLabel lblValue = new JLabel("Value:");
				lblValue.setBounds(6, 99, 61, 16);
				panel_1.add(lblValue);
				
				textFieldValue = new JTextField();
				textFieldValue.setBounds(55, 93, 134, 28);
				panel_1.add(textFieldValue);
				textFieldValue.setColumns(10);
				
				JLabel lblHourlyLoad = new JLabel("Hourly Load:");
				lblHourlyLoad.setBounds(201, 100, 83, 16);
				panel_1.add(lblHourlyLoad);
				
				textFieldHourlyLoad = new JTextField();
				textFieldHourlyLoad.setBounds(289, 93, 134, 28);
				panel_1.add(textFieldHourlyLoad);
				textFieldHourlyLoad.setColumns(10);
				
				JLabel lblDate = new JLabel("Date:");
				lblDate.setBounds(433, 99, 61, 16);
				panel_1.add(lblDate);
				
				textFieldDate = new JTextField();
				textFieldDate.setBounds(469, 93, 134, 28);
				panel_1.add(textFieldDate);
				textFieldDate.setColumns(10);
				
				JLabel lblHour = new JLabel("Hour:");
				lblHour.setBounds(6, 127, 61, 16);
				panel_1.add(lblHour);
				
				textFieldHour = new JTextField();
				textFieldHour.setBounds(55, 121, 134, 28);
				panel_1.add(textFieldHour);
				textFieldHour.setColumns(10);
				
				JLabel lblNewLabel = new JLabel("N of Participants:");
				lblNewLabel.setBounds(201, 127, 109, 16);
				panel_1.add(lblNewLabel);
				
				textFieldNofParticipants = new JTextField();
				textFieldNofParticipants.setBounds(322, 121, 134, 28);
				panel_1.add(textFieldNofParticipants);
				textFieldNofParticipants.setColumns(10);
				
				JLabel lblRegistrationLimit = new JLabel("Registration Limit:");
				lblRegistrationLimit.setBounds(469, 127, 116, 16);
				panel_1.add(lblRegistrationLimit);
				
				textFieldRegistrationLimit = new JTextField();
				textFieldRegistrationLimit.setBounds(600, 121, 116, 28);
				panel_1.add(textFieldRegistrationLimit);
				textFieldRegistrationLimit.setColumns(10);
				
				JPanel panel_2 = new JPanel();
				panel_2.setBounds(6, 309, 716, 143);
				contentPane.add(panel_2);
				panel_2.setLayout(null);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(6, 6, 704, 131);
				panel_2.add(scrollPane);
				
				table = new JTable();
				scrollPane.setViewportView(table);
				
				JButton btnInsert = new JButton("Insert");
				btnInsert.setBounds(6, 273, 117, 29);
				contentPane.add(btnInsert);
				
				JButton btnRemove = new JButton("Remove");
				btnRemove.setBounds(127, 273, 117, 29);
				contentPane.add(btnRemove);
				
				JButton btnUpdate = new JButton("Update");
				btnUpdate.setBounds(248, 273, 117, 29);
				contentPane.add(btnUpdate);
				
				JButton btnSelect = new JButton("Select");
				btnSelect.setBounds(377, 273, 117, 29);
				contentPane.add(btnSelect);
				
				JButton btnClean = new JButton("Clean");
				btnClean.setBounds(503, 273, 117, 29);
				contentPane.add(btnClean);
				
				btnBack = new JButton("Back");
				btnBack.setBounds(611, 273, 117, 29);
				contentPane.add(btnBack);
				
				//PASSO 2
				btnInsert.addActionListener(insertAction);
				btnRemove.addActionListener(removeAction);
				btnUpdate.addActionListener(updateAction);
				btnSelect.addActionListener(selectAction);
				btnClean.addActionListener(cleanAction);
				btnBack.addActionListener(backAction);
				
				carregarActivityTypeComboBox();
				carregarEventComboBox();
				carregarLastId();
				populateTable();

	}
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ActivityManagementScreenP.instanceActivityManagementScreenP = null;
		}
	}
	
	//PASSO 3

	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Activity activity = null;
			
			String nameEvent = comboBoxEvent.getSelectedItem().toString();
			String nameActivity = textFieldActivityName.getText();
			String descriptionActivity = textFieldDescription.getText();
			String activityType = comboBoxActivityType.getSelectedItem().toString();
			String status = textFieldStatus.getText();
			float value = Float.valueOf(textFieldValue.getText());
			float hourlyLoad = Float.valueOf(textFieldHourlyLoad.getText());
			String date = textFieldDate.getText();
			String hour = textFieldHour.getText();
			int numberOfParticipants = Integer.valueOf(textFieldNofParticipants.getText());
			int registrationLimit = Integer.valueOf(textFieldRegistrationLimit.getText());
			
			if (nameEvent.equals("")|| nameActivity.equals("") || descriptionActivity.equals("")
					|| activityType.equals("") || status.equals("") 
					|| value == -1 || hourlyLoad == -1 || date.equals("") || hour.equals("")
					|| numberOfParticipants == -1 || registrationLimit == -1) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}else{
				if (numberOfParticipants > registrationLimit){
					JOptionPane.showMessageDialog(getContentPane(),
							"Numero Limite de Indcrições na atividade deve ser maior que o numero atual de participantes.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					try {
						activity = new Activity();
						activity.setIdEvent(RiSEEventMainScreenP.facade.getEventIdByName(nameEvent));
						activity.setNameActivity(nameActivity);
						activity.setDescriptionActivity(descriptionActivity);
						activity.setTypeActivity(TypeActivity.valueOf(activityType));
						activity.setValue(value);
						activity.setHourlyLoad(hourlyLoad);
						activity.setDate(date);
						activity.setHour(hour);
						activity.setNumberOfParticipants(numberOfParticipants);
						activity.setRegistrationLimit(registrationLimit);

						ActivityTableModel model = new ActivityTableModel(RiSEEventMainScreenP.facade.getActivities());	
						model.addActivity(activity);
						table.setModel(model);

						RiSEEventMainScreenP.facade.insertActivity(activity);


					} catch (ActivityAlreadyInsertedException e1) {
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
	}

	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = table.getSelectedRow();
			
			if(rowIndex == -1){
				JOptionPane.showMessageDialog(getContentPane(),
						"Selecione o Activity a ser removido!");
				return;
			}
			
			try {
				Activity activity = new ActivityTableModel(RiSEEventMainScreenP.facade.getActivities()).get(rowIndex);
				RiSEEventMainScreenP.facade.removeActivity(activity.getIdActivity());
				ActivityTableModel model = (ActivityTableModel) table.getModel();
				model.removeActivity(rowIndex);
				table.setModel(model);
				
				//cleanFields();

			
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ActivityNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ActivityAlreadyInsertedException e1) {
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

				
				Activity activity = null;
				
				String nameEvent = comboBoxEvent.getSelectedItem().toString();
				String nameActivity = textFieldActivityName.getText();
				String descriptionActivity = textFieldDescription.getText();
				String activityType = comboBoxActivityType.getSelectedItem().toString();
				String status = textFieldStatus.getText();
				float value = Float.valueOf(textFieldValue.getText());
				float hourlyLoad = Float.valueOf(textFieldHourlyLoad.getText());
				String date = textFieldDate.getText();
				String hour = textFieldHour.getText();
				int numberOfParticipants = Integer.valueOf(textFieldNofParticipants.getText());
				int registrationLimit = Integer.valueOf(textFieldRegistrationLimit.getText());
				
				if (nameEvent.equals("")|| nameActivity.equals("") || descriptionActivity.equals("")
						|| activityType.equals("") || status.equals("") 
						|| value == -1 || hourlyLoad == -1 || date.equals("") || hour.equals("")
						|| numberOfParticipants == -1 || registrationLimit == -1) {
					JOptionPane.showMessageDialog(getContentPane(),
							"Não pode haver campo vazio.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;

				} else {
					activity = new Activity();
					activity.setIdActivity(Integer.parseInt(lblLastIdActivity.getText()));
					activity.setIdEvent(RiSEEventMainScreenP.facade.getEventIdByName(nameEvent));
					activity.setNameActivity(nameActivity);
					activity.setDescriptionActivity(descriptionActivity);
					activity.setTypeActivity(TypeActivity.valueOf(activityType));

					activity.setValue(value);
					activity.setHourlyLoad(hourlyLoad);
					activity.setDate(date);
					activity.setHour(hour);
					activity.setNumberOfParticipants(numberOfParticipants);
					activity.setRegistrationLimit(registrationLimit);
					
					try {
						
						RiSEEventMainScreenP.facade.updateActivity(activity);
						ActivityTableModel model;
						model = new ActivityTableModel(RiSEEventMainScreenP.facade.getActivities());
						table.setModel(model);
					} catch (ActivityNotFoundException e1) {
						JOptionPane
						.showMessageDialog(
								getContentPane(),
								"Activity que está tentando alterar não existe!",
								"Activity Inexistente",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (ActivityAlreadyInsertedException e1) {
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
			Activity activity = null;

			try {
				activity=  new ActivityTableModel(RiSEEventMainScreenP.facade.getActivities()).get(rowIndex);
			
				lblLastIdActivity.setText(String.valueOf(activity.getIdActivity()));
				comboBoxEvent.setSelectedItem(RiSEEventMainScreenP.facade.searchEvent(activity.getIdEvent()).getEventName());
				textFieldActivityName.setText(activity.getNameActivity());
				textFieldDescription.setText(activity.getDescriptionActivity());
				comboBoxActivityType.setSelectedItem(activity.getTypeActivity().toString());
				textFieldValue.setText(String.valueOf(activity.getValue()));
				textFieldHourlyLoad.setText(String.valueOf(activity.getHourlyLoad()));
				textFieldDate.setText(activity.getDate());
				textFieldHour.setText(activity.getHour());
				textFieldNofParticipants.setText(String.valueOf(activity.getNumberOfParticipants()));
				textFieldRegistrationLimit.setText(String.valueOf(activity.getRegistrationLimit()));
			
			} catch (RepositoryException ex) {
				JOptionPane.showMessageDialog(getContentPane(),
						ex.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				ex.printStackTrace();
			} catch (EventNotFoundException e1) {
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
	
	private class CleanButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			cleanFields();
		}
	}
	
	private void carregarActivityTypeComboBox(){
		TypeActivity[] types = TypeActivity.values();
		List<String> names = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			names.add(i, types[i].name());
			comboBoxActivityType.addItem(types[i].name());
		}
	}
	
	private void carregarEventComboBox(){
		try {
			List<Event> list = RiSEEventMainScreenP.facade.getEvents();
			Iterator<Event> iterator = list.iterator();
			while(iterator.hasNext()){
				comboBoxEvent.addItem(iterator.next().getEventName());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void carregarLastId(){
		try {
			lblLastIdActivity.setText(String.valueOf(RiSEEventMainScreenP.facade.getActivityLastId()));
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void populateTable(){
		
		try {
			ActivityTableModel model;
			model = new ActivityTableModel(RiSEEventMainScreenP.facade.getActivities());	
			table.setModel(model);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
	}
	
	private void cleanFields(){
		comboBoxEvent.setSelectedItem("");
		textFieldActivityName.setText("");
		textFieldDescription.setText("");
		comboBoxActivityType.setSelectedItem("");
		textFieldStatus.setText("");
		textFieldValue.setText("");
		textFieldHourlyLoad.setText("");
		textFieldDate.setText("");
		textFieldHour.setText("");
		textFieldNofParticipants.setText("");
		textFieldRegistrationLimit.setText("");
	}
	
}
//#endif