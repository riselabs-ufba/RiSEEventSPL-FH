//#if ${CheckingCopyCertificado} == "T" or ${CheckingCopyAtestado} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import rise.splcc.exception.ActivityAlreadyInsertedException;
import rise.splcc.exception.ActivityNotFoundException;
import rise.splcc.exception.RepositoryException;

public class CheckingCopyRemoveScreenP extends JInternalFrame {

	private static CheckingCopyRemoveScreenP instanceCheckingCopyRemoveScreenP;
	private JTextField textFieldActivityId;
	
	public static CheckingCopyRemoveScreenP getInstanceCheckingCopyRemoveScreenP() {
		if (instanceCheckingCopyRemoveScreenP == null) {
			CheckingCopyRemoveScreenP.instanceCheckingCopyRemoveScreenP = new CheckingCopyRemoveScreenP();
		}
		return CheckingCopyRemoveScreenP.instanceCheckingCopyRemoveScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckingCopyRemoveScreenP frame = new CheckingCopyRemoveScreenP();
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
	public CheckingCopyRemoveScreenP() {
		setTitle("Remove Activity");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		getContentPane().setLayout(null);
		
		RemoveButtonAction RemoveAction = new RemoveButtonAction();
		BackButtonAction backAction = new BackButtonAction();
		
		JLabel lblActivityid = new JLabel("ActivityId:");
		lblActivityid.setBounds(23, 38, 73, 16);
		getContentPane().add(lblActivityid);
		
		textFieldActivityId = new JTextField();
		textFieldActivityId.setBounds(109, 32, 134, 28);
		getContentPane().add(textFieldActivityId);
		textFieldActivityId.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(334, 32, 117, 29);
		getContentPane().add(btnRemove);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(334, 73, 117, 29);
		getContentPane().add(btnBack);
		
		btnRemove.addActionListener(RemoveAction);
		btnBack.addActionListener(backAction);
		
		
	}

	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			CheckingCopyRemoveScreenP.instanceCheckingCopyRemoveScreenP = null;
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int idActivity = Integer.valueOf(textFieldActivityId.getText());
			
			try {
				if (RiSEEventMainScreenP.facade.searchActivity(idActivity) == null){
					JOptionPane.showMessageDialog(getContentPane(),
							"Activity não existe.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					RiSEEventMainScreenP.facade.removeActivity(idActivity);
					JOptionPane.showMessageDialog(getContentPane(), "Remoção realizada com sucesso!!","Remoção",JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
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
	
}
//#endif