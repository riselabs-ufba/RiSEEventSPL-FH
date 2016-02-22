//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
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

import rise.splcc.data.Activity;
import rise.splcc.exception.ActivityAlreadyInsertedException;
import rise.splcc.exception.ActivityNotFoundException;
import rise.splcc.exception.RepositoryException;

public class ActivitySearchScreenP extends JInternalFrame {

	private static ActivitySearchScreenP instanceActivitySearchScreenP;
	private JTextField textFieldIdActivity;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	public static ActivitySearchScreenP getInstanceActivitySearchScreenP() {
		if (instanceActivitySearchScreenP == null) {
			ActivitySearchScreenP.instanceActivitySearchScreenP = new ActivitySearchScreenP();
		}
		return ActivitySearchScreenP.instanceActivitySearchScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActivitySearchScreenP frame = new ActivitySearchScreenP();
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
	public ActivitySearchScreenP() {
		setTitle("Search Activity");
		
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
		
		JLabel lblActivityId = new JLabel("Activity Id:");
		lblActivityId.setBounds(29, 34, 87, 16);
		getContentPane().add(lblActivityId);
		
		textFieldIdActivity = new JTextField();
		textFieldIdActivity.setBounds(110, 28, 134, 28);
		getContentPane().add(textFieldIdActivity);
		textFieldIdActivity.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(264, 57, 182, 211);
		getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(458, 29, 117, 29);
		getContentPane().add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(458, 70, 117, 29);
		getContentPane().add(btnBack);
		
		btnSearch.addActionListener(searchAction);
		btnBack.addActionListener(backAction);


	}

	
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {

			dispose();
			ActivitySearchScreenP.instanceActivitySearchScreenP = null;
		}
	}
	
	private class SearchButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Activity activity = new Activity();
			
			int idActivity =Integer.valueOf( textFieldIdActivity.getText());
			
			try {
				activity = RiSEEventMainScreenP.facade.searchActivity(idActivity);
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