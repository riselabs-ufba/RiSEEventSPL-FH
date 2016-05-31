//#if ${RegistrationOrganizerActivity} == "T"
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
import rise.splcc.data.ActivityOrganizer;
import rise.splcc.data.Event;

import rise.splcc.data.Organizer;

import rise.splcc.exception.ActivityOrganizerAlreadyInsertedException;
import rise.splcc.exception.ActivityOrganizerNotFoundException;
import rise.splcc.exception.RepositoryException;
import rise.splcc.table.ActivityOrganizerTableModel;

import rise.splcc.table.OrganizerTableModel;



public class ActivityOrganizerManagementScreenP extends JInternalFrame {
	private JPanel contentPane;
	private JTable tableOrganizers;
	private JTable tableActivities;
	
	private JComboBox comboBox_Activities;
	private JComboBox comboBoxEvent;

	JButton btnInsert;
	JButton btnRemove;
	
	 private JButton btnBack;

	
	private static ActivityOrganizerManagementScreenP instanceActivityOrganizerManagementScreenP;
	public static ActivityOrganizerManagementScreenP getInstanceActivityOrganizerManagementScreenP() {
		if (instanceActivityOrganizerManagementScreenP == null) {
			ActivityOrganizerManagementScreenP.instanceActivityOrganizerManagementScreenP = new ActivityOrganizerManagementScreenP();
		}
		return ActivityOrganizerManagementScreenP.instanceActivityOrganizerManagementScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityOrganizerManagementScreenP frame = new ActivityOrganizerManagementScreenP();
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
	public ActivityOrganizerManagementScreenP() {
		setTitle("Registraton Organizer on Activity");
		
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
		setBounds(100, 100, 783, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPaneUsers = new JScrollPane();
		scrollPaneUsers.setBounds(85, 114, 227, 321);
		contentPane.add(scrollPaneUsers);
		
		tableOrganizers = new JTable();
		scrollPaneUsers.setViewportView(tableOrganizers);
		
		
		JScrollPane scrollPaneActivities = new JScrollPane();
		scrollPaneActivities.setBounds(473, 114, 227, 321);
		contentPane.add(scrollPaneActivities);
		
		tableActivities = new JTable();
		scrollPaneActivities.setViewportView(tableActivities);
		
		comboBox_Activities = new JComboBox();
		comboBox_Activities.setBounds(459, 75, 262, 27);
		contentPane.add(comboBox_Activities);
		
		JLabel lblUsers = new JLabel("Organizers:");
		lblUsers.setBounds(170, 86, 61, 16);
		contentPane.add(lblUsers);
		
		JLabel lblActivities = new JLabel("Activities:");
		lblActivities.setBounds(542, 47, 86, 16);
		contentPane.add(lblActivities);
		
		btnInsert = new JButton("-->");
		btnInsert.setBounds(331, 211, 117, 29);
		contentPane.add(btnInsert);
		
		btnRemove = new JButton("<--");
		btnRemove.setBounds(331, 252, 117, 29);
		contentPane.add(btnRemove);
		
		JLabel lblEvent = new JLabel("Event:");
		lblEvent.setBounds(208, 18, 61, 16);
		contentPane.add(lblEvent);
		
		comboBoxEvent = new JComboBox();
		comboBoxEvent.setBounds(259, 14, 286, 21);
		contentPane.add(comboBoxEvent);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(331, 408, 117, 29);
		contentPane.add(btnBack);
		
		btnInsert.addActionListener(insertAction);
		btnRemove.addActionListener(removeAction);
		comboBox_Activities.addActionListener(selectActivityAction);
		comboBoxEvent.addActionListener(selectEventAction);
		btnBack.addActionListener(backAction);
		
		populateTableOrganizers();
		
		//carregarActivityComboBox();
		carregarEventComboBox();

	}


	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
		     dispose();
		     ActivityOrganizerManagementScreenP.instanceActivityOrganizerManagementScreenP = null;
			
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int rowIndex = tableOrganizers.getSelectedRow();
			Organizer organizer = null;

			try {
				organizer =  new OrganizerTableModel(RiSEEventMainScreenP.facade.getOrganizers()).get(rowIndex);
				int idActivity = RiSEEventMainScreenP.facade.getActivityIdByName(comboBox_Activities.getSelectedItem().toString());
				//Criando ActivityOrganizer
				ActivityOrganizer activityOrganizer = new ActivityOrganizer();
				activityOrganizer.setIdActivity(idActivity);
				activityOrganizer.setIdOrganizer(organizer.getIdUser());
				//Inserindo na tabela
				RiSEEventMainScreenP.facade.insertActivityOrganizer(activityOrganizer);
				// buscando atividade com base no nome
				int i;
				i = RiSEEventMainScreenP.facade.getActivityIdByName(comboBox_Activities.getSelectedItem().toString());
				//Atualizando a tabela
				ActivityOrganizerTableModel model;
				model = new ActivityOrganizerTableModel(RiSEEventMainScreenP.facade.getActivitiesOrganizersById(i));
				tableActivities.setModel(model);      
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ActivityOrganizerAlreadyInsertedException e1) {
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
			int rowIndex = tableActivities.getSelectedRow();
			ActivityOrganizer activityOrganizer = null;
			
			try {
				activityOrganizer = new ActivityOrganizerTableModel(RiSEEventMainScreenP.facade.getActivitiesOrganizers()).get(rowIndex);
				RiSEEventMainScreenP.facade.removeActivityOrganizer(activityOrganizer);
				//Atualizando a tabela
				int i;
				i = RiSEEventMainScreenP.facade.getActivityIdByName(comboBox_Activities.getSelectedItem().toString());
				ActivityOrganizerTableModel model;
				model = new ActivityOrganizerTableModel(RiSEEventMainScreenP.facade.getActivitiesOrganizersById(i));
				tableActivities.setModel(model);
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ActivityOrganizerNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (ActivityOrganizerAlreadyInsertedException e1) {
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
				
				if(comboBox_Activities.getItemCount() ==0)
					return;
				// buscando atividade com base no nome
				int i;
				i = RiSEEventMainScreenP.facade.getActivityIdByName(comboBox_Activities.getSelectedItem().toString());
				//Atualizando a tabela
				ActivityOrganizerTableModel model;
				model = new ActivityOrganizerTableModel(RiSEEventMainScreenP.facade.getActivitiesOrganizersById(i));
				tableActivities.setModel(model);
				
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
				comboBox_Activities.removeAllItems();
				//Passando de lista de atividades para lista de nome de atividades
				Iterator<Activity> iteratorActivity = activities.iterator();
				while(iteratorActivity.hasNext()){
					nameActivities.add(iteratorActivity.next().getNameActivity());
				}
				//CarregarCombobox activities	
				Iterator<String> iterator = nameActivities.iterator();
				while(iterator.hasNext()){
					comboBox_Activities.addItem(iterator.next());
				}
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
		}
	}
	
	
	private void populateTableOrganizers(){
		try {
			OrganizerTableModel model;
			model = new OrganizerTableModel(RiSEEventMainScreenP.facade.getOrganizers());

			tableOrganizers.setModel(model);
			
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
}
//#endif