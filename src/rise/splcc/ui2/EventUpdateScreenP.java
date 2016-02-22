package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import rise.splcc.data.Event;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;

public class EventUpdateScreenP extends JInternalFrame {

	
	
	private static EventUpdateScreenP instanceEventUpdateScreenP;
	private JTextField textFieldIdEvent;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextField textFieldNameEvent;
	private JTextField textFieldPeriod;
	private JTextField textFieldInstitution;
	private JTextField textFieldSponsors;
	private JTextField textFieldPlace;
	
	public static EventUpdateScreenP getInstanceEventUpdateScreenP() {
		if (instanceEventUpdateScreenP == null) {
			EventUpdateScreenP.instanceEventUpdateScreenP = new EventUpdateScreenP();
		}
		return EventUpdateScreenP.instanceEventUpdateScreenP;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventUpdateScreenP frame = new EventUpdateScreenP();
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
	public EventUpdateScreenP() {
		
		 
		
		setTitle("Update Event");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		UpdateButtonAction updateAction = new UpdateButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		getContentPane().setLayout(null);
		
		JLabel lblEventId = new JLabel("Event Id:");
		lblEventId.setBounds(20, 38, 61, 16);
		getContentPane().add(lblEventId);
		
		textFieldIdEvent = new JTextField();
		textFieldIdEvent.setBounds(72, 32, 150, 28);
		getContentPane().add(textFieldIdEvent);
		textFieldIdEvent.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(508, 112, 117, 29);
		getContentPane().add(btnUpdate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(508, 153, 117, 29);
		getContentPane().add(btnBack);
		
		textFieldNameEvent = new JTextField();
		textFieldNameEvent.setColumns(10);
		textFieldNameEvent.setBounds(314, 32, 311, 28);
		getContentPane().add(textFieldNameEvent);
		
		JLabel label = new JLabel("Event Name:");
		label.setBounds(229, 38, 396, 16);
		getContentPane().add(label);
		
		textFieldPeriod = new JTextField();
		textFieldPeriod.setColumns(10);
		textFieldPeriod.setBounds(81, 72, 134, 28);
		getContentPane().add(textFieldPeriod);
		
		JLabel label_1 = new JLabel("Period:");
		label_1.setBounds(30, 78, 61, 16);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Institution:");
		label_2.setBounds(240, 78, 86, 16);
		getContentPane().add(label_2);
		
		textFieldInstitution = new JTextField();
		textFieldInstitution.setColumns(10);
		textFieldInstitution.setBounds(321, 72, 333, 28);
		getContentPane().add(textFieldInstitution);
		
		textFieldSponsors = new JTextField();
		textFieldSponsors.setColumns(10);
		textFieldSponsors.setBounds(102, 112, 158, 28);
		getContentPane().add(textFieldSponsors);
		
		JLabel label_3 = new JLabel("Sponsors:");
		label_3.setBounds(30, 122, 78, 16);
		getContentPane().add(label_3);
		
		textFieldPlace = new JTextField();
		textFieldPlace.setColumns(10);
		textFieldPlace.setBounds(321, 112, 134, 28);
		getContentPane().add(textFieldPlace);
		
		JLabel label_4 = new JLabel("Place:");
		label_4.setBounds(272, 122, 61, 16);
		getContentPane().add(label_4);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(229, 184, 196, 191);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		btnUpdate.addActionListener(updateAction);
		btnBack.addActionListener(backAction);

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			esvaziarCampos();
			dispose();
			EventUpdateScreenP.instanceEventUpdateScreenP = null;
			
		}
	}
	
	private class UpdateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Event event = new Event();
			
			int idEvent = Integer.valueOf(textFieldIdEvent.getText());
			String nameEvent = textFieldNameEvent.getText();
			String period = textFieldPeriod.getText();
			String place = textFieldPlace.getText();
			String institution = textFieldInstitution.getText();
			String sponsors = textFieldSponsors.getText();
			
			try {
				event = RiSEEventMainScreenP.facade.searchEvent(idEvent);
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
			event.setEventName(nameEvent);
			event.setPeriod(period);
			event.setPlace(place);
			event.setInstitution(institution);
			event.setSponsors(sponsors);

			try {
				RiSEEventMainScreenP.facade.updateEvent(event);
				event = RiSEEventMainScreenP.facade.searchEvent(event.getIdEvent());
				textArea.setText("");
				textArea.append(event.toString());
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
	
	private void esvaziarCampos() {
		//nametextField.setText("");
		textFieldIdEvent.setText("");
		textFieldInstitution.setText("");
		textFieldPeriod.setText("");
		textFieldPlace.setText("");
		textFieldSponsors.setText("");
		getTextArea().setText("Dados:\n\n");
	}
}
