

package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rise.splcc.data.Activity;
import rise.splcc.data.Event;
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.RepositoryException;

public class EventInsertScreenP extends JInternalFrame {

	
	
	private JTextField textFieldName;
	private JTextField textFieldPeriod;
	private JTextField textFieldInstitution;
	private JTextField textFieldSponsors;
	private JTextField textFieldPlace;
	boolean flag = false;
	
	private static EventInsertScreenP instanceEventInsertScreenP;
	public static EventInsertScreenP getInstanceEventInsertScreenP() {
		if (instanceEventInsertScreenP == null) {
			EventInsertScreenP.instanceEventInsertScreenP = new EventInsertScreenP();
		}
		return EventInsertScreenP.instanceEventInsertScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventInsertScreenP frame = new EventInsertScreenP();
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
	public EventInsertScreenP() {
		setTitle("Insert Event");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		InsertButtonAction insertAction = new InsertButtonAction();
		InsertActivityButtonAction insertActivityAction = new InsertActivityButtonAction();
		BackButtonAction backAction = new BackButtonAction(); 
		
		getContentPane().setLayout(null);
		
		JLabel lblEventId = new JLabel("Event Id:");
		lblEventId.setBounds(6, 36, 61, 16);
		getContentPane().add(lblEventId);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.setBounds(345, 149, 117, 29);
		getContentPane().add(btnInsert);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(496, 149, 117, 29);
		getContentPane().add(btnBack);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(65, 36, 61, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblEventName = new JLabel("Event Name:");
		lblEventName.setBounds(131, 36, 78, 16);
		getContentPane().add(lblEventName);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(216, 30, 414, 28);
		getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblPeriod = new JLabel("Period:");
		lblPeriod.setBounds(6, 75, 61, 16);
		getContentPane().add(lblPeriod);
		
		textFieldPeriod = new JTextField();
		textFieldPeriod.setBounds(57, 69, 134, 28);
		getContentPane().add(textFieldPeriod);
		textFieldPeriod.setColumns(10);
		
		JLabel lblInstitution = new JLabel("Institution:");
		lblInstitution.setBounds(216, 75, 86, 16);
		getContentPane().add(lblInstitution);
		
		textFieldInstitution = new JTextField();
		textFieldInstitution.setBounds(297, 69, 333, 28);
		getContentPane().add(textFieldInstitution);
		textFieldInstitution.setColumns(10);
		
		JLabel lblSponsors = new JLabel("Sponsors:");
		lblSponsors.setBounds(6, 119, 78, 16);
		getContentPane().add(lblSponsors);
		
		textFieldSponsors = new JTextField();
		textFieldSponsors.setBounds(78, 109, 158, 28);
		getContentPane().add(textFieldSponsors);
		textFieldSponsors.setColumns(10);
		
		JButton btnInsertEventMain = new JButton("Insert Event Main Track");
		btnInsertEventMain.setBounds(65, 149, 171, 29);
		getContentPane().add(btnInsertEventMain);
		
		JLabel lblPlace = new JLabel("Place:");
		lblPlace.setBounds(248, 119, 61, 16);
		getContentPane().add(lblPlace);
		
		textFieldPlace = new JTextField();
		textFieldPlace.setBounds(297, 109, 134, 28);
		getContentPane().add(textFieldPlace);
		textFieldPlace.setColumns(10);
		
		btnInsert.addActionListener(insertAction);
		btnBack.addActionListener(backAction);
		btnInsertEventMain.addActionListener(insertActivityAction);
		
		
		try {
			lblNewLabel.setText(String.valueOf(RiSEEventMainScreenP.facade.getEventLastId()));
			
			
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			EventInsertScreenP.instanceEventInsertScreenP = null;
		}
	}
	
	private class InsertButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			Event event = null;
			
			String eventName = textFieldName.getText();
			String period = textFieldPeriod.getText();
			String place  = textFieldPlace.getText();
			String institution = textFieldInstitution.getText();
			String sponsors = textFieldSponsors.getText();
			
			if (eventName.equals("") || period.equals("") || place.equals("")
					|| institution.equals("") || sponsors.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else {
				event = new Event();
				event.setEventName(eventName);
				event.setPeriod(period);
				event.setPlace(place);
				event.setInstitution(institution);
				event.setSponsors(sponsors);
				
				
				try {
					RiSEEventMainScreenP.facade.insertEvent(event);
					JOptionPane.showMessageDialog(getContentPane(),
							"Evento inserido com Sucesso.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					flag = true;
					
				} catch (EventAlreadyInsertedException e1) {
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
	
	private class InsertActivityButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(flag==false){
				JOptionPane.showMessageDialog(getContentPane(),
						"Firstly, you should create an event.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			ActivityInsertScreenP activityScreen = new ActivityInsertScreenP();
			JDesktopPane desktopPane = getDesktopPane();
			desktopPane.add(activityScreen);//add f1 to desktop pane
			activityScreen.setVisible(true);// set the f1 frame visible
		}
	}
}
