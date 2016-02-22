//#if ${EventImportantDates} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import rise.splcc.data.Activity;
import rise.splcc.data.Event;
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;
import rise.splcc.exception.RepositoryException;

import javax.swing.JComboBox;

import com.lowagie.text.DocumentException;

public class EventImportantDatesScreenP extends JInternalFrame {
	
	JComboBox comboBoxEvent;
	Event event;
	
	private static EventImportantDatesScreenP instanceEventImportantDatesScreenP;
	private JTextField textFieldAbstractDate;
	private JTextField textFieldFullPaperDate;
	private JTextField textFieldNotificationsDAte;
	public static EventImportantDatesScreenP getInstanceEventImportantDatesScreenP() {
		if (instanceEventImportantDatesScreenP == null) {
			EventImportantDatesScreenP.instanceEventImportantDatesScreenP = new EventImportantDatesScreenP();
		}
		return EventImportantDatesScreenP.instanceEventImportantDatesScreenP;
	}
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventImportantDatesScreenP frame = new EventImportantDatesScreenP();
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
	public EventImportantDatesScreenP() {
		setTitle("Event Important Dates");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		JLabel lblAbstract = new JLabel("Abstracts:");
		lblAbstract.setBounds(31, 55, 64, 16);
		getContentPane().add(lblAbstract);
		
		JLabel lblFullPapers = new JLabel("Full Papers:");
		lblFullPapers.setBounds(31, 83, 72, 16);
		getContentPane().add(lblFullPapers);
		
		JLabel lblNotifications = new JLabel("Notifications:");
		lblNotifications.setBounds(34, 111, 86, 16);
		getContentPane().add(lblNotifications);
		
		textFieldAbstractDate = new JTextField();
		textFieldAbstractDate.setBounds(106, 49, 134, 28);
		getContentPane().add(textFieldAbstractDate);
		textFieldAbstractDate.setColumns(10);
		
		textFieldFullPaperDate = new JTextField();
		textFieldFullPaperDate.setBounds(106, 77, 134, 28);
		getContentPane().add(textFieldFullPaperDate);
		textFieldFullPaperDate.setColumns(10);
		
		textFieldNotificationsDAte = new JTextField();
		textFieldNotificationsDAte.setBounds(127, 105, 134, 28);
		getContentPane().add(textFieldNotificationsDAte);
		textFieldNotificationsDAte.setColumns(10);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(305, 55, 117, 29);
		getContentPane().add(btnPrint);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(305, 106, 117, 29);
		getContentPane().add(btnBack);
		
		JLabel lblEvent = new JLabel("Event:");
		lblEvent.setBounds(31, 19, 61, 16);
		getContentPane().add(lblEvent);
		
		comboBoxEvent = new JComboBox();
		comboBoxEvent.setBounds(78, 15, 239, 28);
		getContentPane().add(comboBoxEvent);
		
		PrintButtonAction generateAction = new PrintButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		SelectEventButtonAction selectAction = new SelectEventButtonAction();
		
		btnPrint.addActionListener(generateAction);
		btnBack.addActionListener(backAction);
		comboBoxEvent.addActionListener(selectAction);
		
		carregarEventComboBox();

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			EventImportantDatesScreenP.instanceEventImportantDatesScreenP = null;
		}
	}
	
	private class PrintButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

				String abstractPaper = textFieldAbstractDate.getText();
				String fullPaper = textFieldFullPaperDate.getText();
				String notification = textFieldNotificationsDAte.getText();
				try {
					RiSEEventMainScreenP.facade.generateImportantDates(abstractPaper, fullPaper, notification,event);
				} catch (DocumentException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(getContentPane(),
							e1.toString(), "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					e1.printStackTrace();
				}
				
			
		}
	}
	
	private class SelectEventButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int idEvent = RiSEEventMainScreenP.facade.getEventIdByName(comboBoxEvent.getSelectedItem().toString());
				event = RiSEEventMainScreenP.facade.searchEvent(idEvent);
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
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