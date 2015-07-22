//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
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

import rise.splcc.data.Activity;
import rise.splcc.data.Activity.TypeActivity;
import rise.splcc.data.Event;
import rise.splcc.exception.ActivityAlreadyInsertedException;
import rise.splcc.exception.ActivityNotFoundException;
import rise.splcc.exception.RepositoryException;

public class ActivityUpdateScreenP extends JInternalFrame {

	private JTextField textFieldNameActivity;
	private JTextField textFieldDescription;
	private JTextField textFieldStatus;
	private JTextField textFieldValue;
	private JTextField textFieldHourlyLoad;
	private JTextField textFieldDate;
	private JTextField textFieldHour;
	private JTextField textFieldNOParticipants;
	private JTextField textFieldRegLimit;
	private JComboBox comboBoxActivity;
	private JComboBox comboBoxEvent;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	private static ActivityUpdateScreenP instanceActivityUpdateScreenP;
	private JTextField textFieldidActivity;
	public static ActivityUpdateScreenP getInstanceActivityUpdateScreenP() {
		if (instanceActivityUpdateScreenP == null) {
			ActivityUpdateScreenP.instanceActivityUpdateScreenP = new ActivityUpdateScreenP();
		}
		return ActivityUpdateScreenP.instanceActivityUpdateScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivityUpdateScreenP frame = new ActivityUpdateScreenP();
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
	public ActivityUpdateScreenP() {
setTitle("Insert Activity");
		
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
		
		JLabel lblActivityId = new JLabel("Activity Id:");
		lblActivityId.setBounds(17, 25, 76, 16);
		getContentPane().add(lblActivityId);
		
		JLabel lblEvent = new JLabel("Event:");
		lblEvent.setBounds(187, 25, 61, 16);
		getContentPane().add(lblEvent);
		
		comboBoxEvent = new JComboBox();
		comboBoxEvent.setBounds(231, 21, 363, 27);
		getContentPane().add(comboBoxEvent);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(17, 69, 61, 16);
		getContentPane().add(lblName);
		
		textFieldNameActivity = new JTextField();
		textFieldNameActivity.setBounds(64, 63, 134, 28);
		getContentPane().add(textFieldNameActivity);
		textFieldNameActivity.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(231, 69, 77, 16);
		getContentPane().add(lblDescription);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(320, 63, 274, 28);
		getContentPane().add(textFieldDescription);
		textFieldDescription.setColumns(10);
		
		JLabel lblActivityType = new JLabel("Activity Type:");
		lblActivityType.setBounds(17, 113, 86, 16);
		getContentPane().add(lblActivityType);
		
		comboBoxActivity = new JComboBox();
		comboBoxActivity.setBounds(115, 109, 159, 27);
		getContentPane().add(comboBoxActivity);
		
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
		
		JLabel lblHourlyLoad = new JLabel("Hourly Load:");
		lblHourlyLoad.setBounds(231, 153, 86, 16);
		getContentPane().add(lblHourlyLoad);
		
		textFieldHourlyLoad = new JTextField();
		textFieldHourlyLoad.setBounds(329, 147, 134, 28);
		getContentPane().add(textFieldHourlyLoad);
		textFieldHourlyLoad.setColumns(10);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(488, 153, 61, 16);
		getContentPane().add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(529, 147, 134, 28);
		getContentPane().add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JLabel lblHour = new JLabel("Hour:");
		lblHour.setBounds(17, 187, 61, 16);
		getContentPane().add(lblHour);
		
		textFieldHour = new JTextField();
		textFieldHour.setBounds(64, 181, 134, 28);
		getContentPane().add(textFieldHour);
		textFieldHour.setColumns(10);
		
		JLabel lblNOfParticipants = new JLabel("N Of Participants:");
		lblNOfParticipants.setBounds(210, 187, 120, 16);
		getContentPane().add(lblNOfParticipants);
		
		textFieldNOParticipants = new JTextField();
		textFieldNOParticipants.setBounds(330, 181, 134, 28);
		getContentPane().add(textFieldNOParticipants);
		textFieldNOParticipants.setColumns(10);
		
		JLabel lblRegLimit = new JLabel("Reg. Limit:");
		lblRegLimit.setBounds(476, 187, 73, 16);
		getContentPane().add(lblRegLimit);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(459, 228, 204, 164);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		textFieldRegLimit = new JTextField();
		textFieldRegLimit.setBounds(561, 181, 134, 28);
		getContentPane().add(textFieldRegLimit);
		textFieldRegLimit.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(157, 237, 117, 29);
		getContentPane().add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(320, 237, 117, 29);
		getContentPane().add(btnBack);
		
		textFieldidActivity = new JTextField();
		textFieldidActivity.setBounds(85, 19, 86, 28);
		getContentPane().add(textFieldidActivity);
		textFieldidActivity.setColumns(10);
		
		btnUpdate.addActionListener(updateAction);
		btnBack.addActionListener(backAction);
		
		
		
		TypeActivity[] types = TypeActivity.values();
		List<String> names = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			names.add(i, types[i].name());
			comboBoxActivity.addItem(types[i].name());
		}
		
		carregarEventComboBox();

	}
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			ActivityUpdateScreenP.instanceActivityUpdateScreenP = null;
		}
	}
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Activity activity = new Activity();
			
			int idActivity = Integer.valueOf(textFieldidActivity.getText());
			String nameEvent = comboBoxEvent.getSelectedItem().toString();
			String nameActivity = textFieldNameActivity.getText();
			String descriptionActivity = textFieldDescription.getText();
			String activityType = comboBoxActivity.getSelectedItem().toString();
			String status = textFieldStatus.getText();
			float value = Float.valueOf(textFieldValue.getText());
			float hourlyLoad = Float.valueOf(textFieldHourlyLoad.getText());
			String date = textFieldDate.getText();
			String hour = textFieldHour.getText();
			int numberOfParticipants = Integer.valueOf(textFieldNOParticipants.getText());
			int registrationLimit = Integer.valueOf(textFieldRegLimit.getText());
			
			try {
				activity = RiSEEventMainScreenP.facade.searchActivity(idActivity);
			} catch (ActivityNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
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
			
			try {
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
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
			
			try {
				RiSEEventMainScreenP.facade.updateActivity(activity);
				activity = RiSEEventMainScreenP.facade.searchActivity(activity.getIdActivity());
				textArea.setText("");
				textArea.append(activity.toString());
			} catch (ActivityNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
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