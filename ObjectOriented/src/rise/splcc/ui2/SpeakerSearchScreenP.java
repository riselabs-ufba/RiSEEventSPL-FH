//#if ${Speaker} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import rise.splcc.data.Speaker;
import rise.splcc.data.User;
import rise.splcc.exception.RepositoryException;
import rise.splcc.exception.SpeakerAlreadyInsertedException;
import rise.splcc.exception.SpeakerNotFoundException;
import rise.splcc.exception.UserAlreadyInsertedException;
import rise.splcc.exception.UserNotFoundException;

public class SpeakerSearchScreenP extends JInternalFrame {

	
	private static SpeakerSearchScreenP instanceSpeakerSearchScreenP;
	private JTextField textFieldSpeakerId;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	
	public static SpeakerSearchScreenP getInstanceSpeakerSearchScreenP() {
		if (instanceSpeakerSearchScreenP == null) {
			SpeakerSearchScreenP.instanceSpeakerSearchScreenP = new SpeakerSearchScreenP();
		}
		return SpeakerSearchScreenP.instanceSpeakerSearchScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SpeakerSearchScreenP frame = new SpeakerSearchScreenP();
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
	public SpeakerSearchScreenP() {
		setTitle("Search Speaker");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		
		SearchButtonAction searchAction = new SearchButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		getContentPane().setLayout(null);
		
		JLabel lblSpeakerId = new JLabel("User Id:");
		lblSpeakerId.setBounds(30, 40, 61, 16);
		getContentPane().add(lblSpeakerId);
		
		textFieldSpeakerId = new JTextField();
		textFieldSpeakerId.setBounds(91, 34, 134, 28);
		getContentPane().add(textFieldSpeakerId);
		textFieldSpeakerId.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(480, 54, 117, 29);
		getContentPane().add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(480, 95, 117, 29);
		getContentPane().add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(232, 69, 233, 196);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		

		
		btnSearch.addActionListener(searchAction);
		btnBack.addActionListener(backAction);

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			SpeakerSearchScreenP.instanceSpeakerSearchScreenP = null;
			
		}
	}
	
	private class SearchButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Speaker speaker = new Speaker();
			User user = new User();
			
			int idUser =Integer.valueOf(textFieldSpeakerId.getText());
			
			try {
				speaker = RiSEEventMainScreenP.facade.searchSpeaker(idUser);
				//user = RiSEEventMainScreenP.facade.searchUser(idUser);
				textArea.setText("");
				//textArea.append(user.toString() + speaker.toString());
				textArea.append(speaker.toString());
			} catch (SpeakerNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (SpeakerAlreadyInsertedException e1) {
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
			textArea.setBounds(232, 69, 233, 196);
			textArea.setEditable(false);
		}
		return this.textArea;
	}
	
	private JScrollPane getScrollPanel() {
		if (this.scrollPane == null) {
			this.scrollPane = new JScrollPane(
					getTextArea());
			this.scrollPane.setBounds(232, 69, 233, 196);
		}
		return this.scrollPane;
	}
		

}
//#endif