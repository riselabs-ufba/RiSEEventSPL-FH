//#if ${RegistrationUserActivity} == "T"
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
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import rise.splcc.data.Activity;
import rise.splcc.data.ActivityUser;
import rise.splcc.data.Event;
import rise.splcc.data.Registration;
import rise.splcc.data.User;
import rise.splcc.exception.ActivityAlreadyInsertedException;
import rise.splcc.exception.ActivityNotFoundException;
import rise.splcc.exception.ActivityUserAlreadyInsertedException;
import rise.splcc.exception.ActivityUserNotFoundException;
import rise.splcc.exception.RegistrationNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.table.ActivityUserTableModel;
import rise.splcc.table.UserTableModel;

public class ActivityUserManagementScreenP extends JInternalFrame {

	private JPanel contentPane;
	private JTable tableUsers;
	private JTable tableUserActivity;
	
	private JComboBox comboBoxEvent ;
	private JComboBox comboBoxActivity;
	
	private JLabel lblLimit;
	private JLabel lblNOfParticipants; 
	
	private JButton btnBack;
	
	
	private static ActivityUserManagementScreenP instanceActivityUserManagementScreenP;
	public static ActivityUserManagementScreenP getInstanceActivityUserManagementScreenP() {
		if (instanceActivityUserManagementScreenP == null) {
			ActivityUserManagementScreenP.instanceActivityUserManagementScreenP = new ActivityUserManagementScreenP();
		}
		return ActivityUserManagementScreenP.instanceActivityUserManagementScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityUserManagementScreenP frame = new ActivityUserManagementScreenP();
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
	public ActivityUserManagementScreenP() {
		setTitle("Registraton User on Activity");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);	
		
		InsertButtonAction insertAction = new InsertButtonAction();
		RemoveButtonAction removeAction = new RemoveButtonAction();
		SelectComboActivityAction selectActivityAction = new SelectComboActivityAction();
		SelectComboEventAction selectEventAction = new SelectComboEventAction();
		BackButtonAction backAction = new BackButtonAction();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 762, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEvent = new JLabel("Event:");
		lblEvent.setBounds(189, 28, 61, 16);
		contentPane.add(lblEvent);
		
		comboBoxEvent = new JComboBox();
		comboBoxEvent.setBounds(230, 24, 228, 27);
		contentPane.add(comboBoxEvent);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(122, 112, 61, 16);
		contentPane.add(lblUser);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 133, 202, 246);
		contentPane.add(scrollPane);
		
		tableUsers = new JTable();
		scrollPane.setViewportView(tableUsers);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(427, 133, 228, 246);
		contentPane.add(scrollPane_1);
		
		tableUserActivity = new JTable();
		scrollPane_1.setViewportView(tableUserActivity);
		
		JLabel lblActivity = new JLabel("Activity:");
		lblActivity.setBounds(381, 105, 61, 16);
		contentPane.add(lblActivity);
		
		comboBoxActivity = new JComboBox();
		comboBoxActivity.setBounds(438, 101, 217, 27);
		contentPane.add(comboBoxActivity);
		
		JButton buttonInsert = new JButton("-->");
		buttonInsert.setBounds(280, 184, 117, 29);
		contentPane.add(buttonInsert);
		
		JButton buttonRemove = new JButton("<--");
		buttonRemove.setBounds(280, 225, 117, 29);
		contentPane.add(buttonRemove);
		
		JLabel label = new JLabel("Reg. Limit:");
		label.setBounds(269, 143, 76, 16);
		contentPane.add(label);
		
		lblLimit = new JLabel("");
		lblLimit.setBounds(338, 143, 61, 16);
		contentPane.add(lblLimit);
		
		JLabel label_2 = new JLabel("N# of participants:");
		label_2.setBounds(262, 319, 124, 16);
		contentPane.add(label_2);
		
		lblNOfParticipants = new JLabel("");
		lblNOfParticipants.setBounds(284, 341, 61, 16);
		contentPane.add(lblNOfParticipants);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(280, 386, 117, 29);
		contentPane.add(btnBack);
		
		buttonInsert.addActionListener(insertAction);
		buttonRemove.addActionListener(removeAction);
		comboBoxActivity.addActionListener(selectActivityAction);
		comboBoxEvent.addActionListener(selectEventAction);
		btnBack.addActionListener(backAction);
		
		populateTableUsers();
		carregarEventComboBox();
		atualizarNumeroParticipantes();

	}

	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ActivityUserManagementScreenP.instanceActivityUserManagementScreenP = null;
			
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = tableUsers.getSelectedRow();
			User user = null;
			Activity activity = null;
			
			try {
				
				user =  new UserTableModel(RiSEEventMainScreenP.facade.getUsers()).get(rowIndex);
				int idActivity = RiSEEventMainScreenP.facade.getActivityIdByName(comboBoxActivity.getSelectedItem().toString());
				//Verificando se o numero de registration é maior que o numero de participantes atual
				activity = RiSEEventMainScreenP.facade.searchActivity(idActivity);
				if(activity.getNumberOfParticipants() < activity.getRegistrationLimit()){
					//Criando ActivityUser
					ActivityUser activityUser = new ActivityUser();
					activityUser.setIdActivity(idActivity);
					activityUser.setIdUser(user.getIdUser());
					//Inserindo na tabela
					RiSEEventMainScreenP.facade.insertActivityUser(activityUser);		
					//Atualizando a tabela
					ActivityUserTableModel model;
					model = new ActivityUserTableModel(RiSEEventMainScreenP.facade.getActivitiesUsersById(idActivity));
					tableUserActivity.setModel(model);  		
					// Incrementando numero de participantes
					activity.setNumberOfParticipants(activity.getNumberOfParticipants() + 1);
					RiSEEventMainScreenP.facade.updateActivity(activity);
					atualizarNumeroParticipantes();
					// Atualizando valor no registration quando uma atividade é adicionada
					int idEvent = RiSEEventMainScreenP.getFacade().getEventbyActivity(idActivity);
					int idUser = user.getIdUser();
					int idRegistration = RiSEEventMainScreenP.getFacade().searchRegistration(idUser, idEvent);
					
					RiSEEventMainScreenP.getFacade().addValue(activity.getValue(),idRegistration);
				}else{
					JOptionPane.showMessageDialog(getContentPane(),
							"Activity has no vacancy .", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ActivityUserAlreadyInsertedException e1) {
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
			} catch (RegistrationNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} 
			
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = tableUserActivity.getSelectedRow();
			ActivityUser activityUser = null;
			Activity activity = null;
			try {
				activityUser = new ActivityUserTableModel(RiSEEventMainScreenP.facade.getActivitiesUsers()).get(rowIndex);
				RiSEEventMainScreenP.facade.removeActivityUser(activityUser);
				//Atualizando a tabela
				int idActivity;
				idActivity = RiSEEventMainScreenP.facade.getActivityIdByName(comboBoxActivity.getSelectedItem().toString());
				ActivityUserTableModel model;
				model = new ActivityUserTableModel(RiSEEventMainScreenP.facade.getActivitiesUsersById(idActivity));
				tableUserActivity.setModel(model);	
				// Decrementando numero de participantes
				activity = RiSEEventMainScreenP.facade.searchActivity(idActivity);
				activity.setNumberOfParticipants(activity.getNumberOfParticipants() -1);
				RiSEEventMainScreenP.facade.updateActivity(activity);
				atualizarNumeroParticipantes();
				// Atualizando valor no registration quando uma atividade é removida
				int idEvent = RiSEEventMainScreenP.getFacade().getEventbyActivity(idActivity);
				int idUser = activityUser.getIdUser();
				int idRegistration = RiSEEventMainScreenP.getFacade().searchRegistration(idUser, idEvent);
				
				RiSEEventMainScreenP.getFacade().removeValue(activity.getValue(),idRegistration);
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ActivityUserNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ActivityUserAlreadyInsertedException e1) {
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
			} catch (RegistrationNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
		}
	}
	
	private class SelectComboActivityAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				
				if(comboBoxActivity.getItemCount() ==0)
					return;
				// buscando atividade com base no nome
				int i;
				i = RiSEEventMainScreenP.facade.getActivityIdByName(comboBoxActivity.getSelectedItem().toString());
				//Atualizando a tabela
				ActivityUserTableModel model;
				model = new ActivityUserTableModel(RiSEEventMainScreenP.facade.getActivitiesUsersById(i));
				tableUserActivity.setModel(model);
				
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
		}
	}
	
	
	private class SelectComboEventAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				// buscando atividade com base no nome
				List<Activity> activities;
				List<String> nameActivities = new ArrayList<String>();
				activities = RiSEEventMainScreenP.facade.getActivitiesByEvent(RiSEEventMainScreenP.facade.getEventIdByName(comboBoxEvent.getSelectedItem().toString()));
				comboBoxActivity.removeAllItems();
				//Passando de lista de atividades para lista de nome de atividades
				Iterator<Activity> iteratorActivity = activities.iterator();
				while(iteratorActivity.hasNext()){
					nameActivities.add(iteratorActivity.next().getNameActivity());
				}
				//CarregarCombobox activities	
				Iterator<String> iterator = nameActivities.iterator();
				while(iterator.hasNext()){
					comboBoxActivity.addItem(iterator.next());
				}
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
		}
	}
	
	private void populateTableUsers(){
		try {
			UserTableModel model;
			model = new UserTableModel(RiSEEventMainScreenP.facade.getUsers());

			tableUsers.setModel(model);
			
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
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
	
	private void atualizarNumeroParticipantes(){
		int IdActivity;
		try {
			
			IdActivity = RiSEEventMainScreenP.facade.getActivityIdByName(comboBoxActivity.getSelectedItem().toString());
			Activity activity = RiSEEventMainScreenP.facade.searchActivity(IdActivity);
			lblNOfParticipants.setText(String.valueOf(activity.getNumberOfParticipants()));
			lblLimit.setText(String.valueOf(activity.getRegistrationLimit()));
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (ActivityNotFoundException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (ActivityAlreadyInsertedException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);  
			e.printStackTrace();
		}
		
	}
}
//#endif