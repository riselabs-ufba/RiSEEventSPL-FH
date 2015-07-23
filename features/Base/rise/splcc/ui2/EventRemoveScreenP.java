package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;


public class EventRemoveScreenP extends JInternalFrame {

	
	
	private static EventRemoveScreenP instanceEventRemoveScreenP;
	private JTextField textFieldIdEvent;
	
	public static EventRemoveScreenP getInstanceEventRemoveScreenP() {
		if (instanceEventRemoveScreenP == null) {
			EventRemoveScreenP.instanceEventRemoveScreenP = new EventRemoveScreenP();
		}
		return EventRemoveScreenP.instanceEventRemoveScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventRemoveScreenP frame = new EventRemoveScreenP();
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
	public EventRemoveScreenP() {
		
		
		RemoveButtonAction removeAction = new RemoveButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		getContentPane().setLayout(null);
		
		JLabel lblEventId = new JLabel("Event Id:");
		lblEventId.setBounds(27, 45, 61, 16);
		getContentPane().add(lblEventId);
		
		textFieldIdEvent = new JTextField();
		textFieldIdEvent.setBounds(84, 39, 134, 28);
		getContentPane().add(textFieldIdEvent);
		textFieldIdEvent.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(263, 17, 117, 29);
		getContentPane().add(btnRemove);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(263, 58, 117, 29);
		getContentPane().add(btnBack);

		btnRemove.addActionListener(removeAction);
		btnBack.addActionListener(backAction);
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			EventRemoveScreenP.instanceEventRemoveScreenP = null;
			
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int idEvent = Integer.valueOf(textFieldIdEvent.getText());
			
			try {
				if (RiSEEventMainScreenP.facade.searchEvent(idEvent) == null){
					JOptionPane.showMessageDialog(getContentPane(),
							"Evento não existe.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					RiSEEventMainScreenP.facade.removeEvent(idEvent);
					JOptionPane.showMessageDialog(getContentPane(), "Remoção realizada com sucesso!!","Remoção",JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
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
}
