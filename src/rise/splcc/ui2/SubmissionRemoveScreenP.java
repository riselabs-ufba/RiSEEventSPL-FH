//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
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


import rise.splcc.exception.SubmissionAlreadyInsertedException;
import rise.splcc.exception.SubmissionNotFoundException;

import rise.splcc.exception.RepositoryException;

public class SubmissionRemoveScreenP extends JInternalFrame {

	private static SubmissionRemoveScreenP instanceSubmissionRemoveScreenP;
	private JTextField textFieldSubmissionId;
	
	public static SubmissionRemoveScreenP getInstanceSubmissionRemoveScreenP() {
		if (instanceSubmissionRemoveScreenP == null) {
			SubmissionRemoveScreenP.instanceSubmissionRemoveScreenP = new SubmissionRemoveScreenP();
		}
		return SubmissionRemoveScreenP.instanceSubmissionRemoveScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubmissionRemoveScreenP frame = new SubmissionRemoveScreenP();
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
	public SubmissionRemoveScreenP() {
		setTitle("Remove Submission");
		
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
		
		JLabel lblSubmissionid = new JLabel("SubmissionId:");
		lblSubmissionid.setBounds(23, 38, 89, 16);
		getContentPane().add(lblSubmissionid);
		
		textFieldSubmissionId = new JTextField();
		textFieldSubmissionId.setBounds(130, 32, 134, 28);
		getContentPane().add(textFieldSubmissionId);
		textFieldSubmissionId.setColumns(10);
		
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
			SubmissionRemoveScreenP.instanceSubmissionRemoveScreenP = null;
		}
	}
	
	private class RemoveButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			int idSubmission = Integer.valueOf(textFieldSubmissionId.getText());
			
			try {
				if (RiSEEventMainScreenP.facade.searchSubmission(idSubmission) == null){
					JOptionPane.showMessageDialog(getContentPane(),
							"Submission não existe.", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}else{
					RiSEEventMainScreenP.facade.removeSubmission(idSubmission);
					JOptionPane.showMessageDialog(getContentPane(), "Remoção realizada com sucesso!!","Remoção",JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (HeadlessException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (SubmissionNotFoundException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (RepositoryException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			} catch (SubmissionAlreadyInsertedException e1) {
				JOptionPane.showMessageDialog(getContentPane(),
						e1.toString(), "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				e1.printStackTrace();
			}
				
		}

	}
	
}
//#endif