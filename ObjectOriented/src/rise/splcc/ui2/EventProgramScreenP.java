//#if ${EventProgram} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.lowagie.text.DocumentException;

import rise.splcc.data.Activity;
import rise.splcc.data.Event;
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;
import rise.splcc.exception.RepositoryException;

public class EventProgramScreenP extends JInternalFrame {

	
	JComboBox comboBoxEvent;
	private static EventProgramScreenP instanceEventProgramScreenP;
	
	public static EventProgramScreenP getInstanceEventProgramScreenP() {
		if (instanceEventProgramScreenP == null) {
			EventProgramScreenP.instanceEventProgramScreenP = new EventProgramScreenP();
		}
		return EventProgramScreenP.instanceEventProgramScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventProgramScreenP frame = new EventProgramScreenP();
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
	public EventProgramScreenP() {
		setTitle("Event Program");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		JLabel lblEvent = new JLabel("Event:");
		lblEvent.setBounds(28, 70, 61, 16);
		getContentPane().add(lblEvent);
		
		comboBoxEvent = new JComboBox();
		comboBoxEvent.setBounds(72, 66, 360, 27);
		getContentPane().add(comboBoxEvent);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setBounds(481, 37, 117, 29);
		getContentPane().add(btnGenerate);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(481, 78, 117, 29);
		getContentPane().add(btnBack);
		
		GenerateButtonAction generateAction = new GenerateButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		btnGenerate.addActionListener(generateAction);
		btnBack.addActionListener(backAction);
		
		carregarEventComboBox();

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			EventProgramScreenP.instanceEventProgramScreenP = null;
		}
	}
	
	private class GenerateButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int idEvent = RiSEEventMainScreenP.facade.getEventIdByName(comboBoxEvent.getSelectedItem().toString());
				Event event = RiSEEventMainScreenP.facade.searchEvent(idEvent);
				List<Activity> activities = RiSEEventMainScreenP.facade.getActivitiesByEvent(idEvent);
				RiSEEventMainScreenP.facade.generateProgram(activities,event );
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