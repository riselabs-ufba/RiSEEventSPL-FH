//#if ${CheckingCopyAtestado} == "T" or ${CheckingCopyCertificado} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.lowagie.text.DocumentException;

import rise.splcc.data.CheckingCopy;
import rise.splcc.data.Event;
import rise.splcc.data.User;
import rise.splcc.data.Activity.TypeActivity;
import rise.splcc.exception.EventAlreadyInsertedException;
import rise.splcc.exception.EventNotFoundException;
import rise.splcc.exception.RepositoryException;

public class CheckingCopyTypeScreenP  extends JInternalFrame {
	
	private JComboBox comboBoxEvent;
	private JComboBox comboBoxUser;
	private JComboBox comboBoxTypeActivity;
	private String periodo;
	
	private static CheckingCopyTypeScreenP instanceCheckingCopyTypeScreenP;
	
	public static CheckingCopyTypeScreenP getInstanceCheckingCopyTypeScreenP() {
		if (instanceCheckingCopyTypeScreenP == null) {
			CheckingCopyTypeScreenP.instanceCheckingCopyTypeScreenP = new CheckingCopyTypeScreenP();
		}
		return CheckingCopyTypeScreenP.instanceCheckingCopyTypeScreenP;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckingCopyTypeScreenP frame = new CheckingCopyTypeScreenP();
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
	public CheckingCopyTypeScreenP() {
		setTitle("Checking Copy Type");
		
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
		
		//#if ${CheckingCopyAtestado} == "T"
		JButton btnAtestado = new JButton("Generate Atestado");
		btnAtestado.setBounds(481, 37, 150, 29);
		getContentPane().add(btnAtestado);
		//#endif
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(481, 149, 117, 29);
		getContentPane().add(btnBack);
		
		//#if ${CheckingCopyCertificado} == "T"
		JButton btnCertificado = new JButton("Generate Certificado");
		btnCertificado.setBounds(481, 89, 183, 29);
		getContentPane().add(btnCertificado);
		//#endif
		
		comboBoxUser = new JComboBox();
		comboBoxUser.setBounds(72, 121, 360, 27);
		getContentPane().add(comboBoxUser);
		
		JLabel lblUser = new JLabel("User:");
		lblUser.setBounds(28, 125, 61, 16);
		getContentPane().add(lblUser);
		
		comboBoxTypeActivity = new JComboBox();
		comboBoxTypeActivity.setBounds(120, 182, 311, 27);
		getContentPane().add(comboBoxTypeActivity);
		
		JLabel lblTypeActivity = new JLabel("Type Activity:");
		lblTypeActivity.setBounds(6, 186, 102, 16);
		getContentPane().add(lblTypeActivity);
		
		//#if ${CheckingCopyAtestado} == "T"
		GenerateAtestadoButtonAction generateAtestadoAction = new GenerateAtestadoButtonAction();
		//#endif
		//#if ${CheckingCopyCertificado} == "T"
		GenerateCertificadoButtonAction generateCertificadoAction = new GenerateCertificadoButtonAction();
		//#endif
		BackButtonAction backAction = new BackButtonAction();
		
		//#if ${CheckingCopyAtestado} == "T"
		btnAtestado.addActionListener(generateAtestadoAction);
		//#endif
		//#if ${CheckingCopyCertificado} == "T"
		btnCertificado.addActionListener(generateCertificadoAction);
		//#endif
		btnBack.addActionListener(backAction);
		
		carregarEventComboBox();
		carregarUserComboBox();
		carregarTypeActivityComboBox();
		
		ItemListener listener = new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				pegarPeriodoEvent();
			}
			
		};
		comboBoxEvent.addItemListener(listener);

	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			CheckingCopyTypeScreenP.instanceCheckingCopyTypeScreenP = null;
		}
	}
	//#if ${CheckingCopyAtestado} == "T"
	private class GenerateAtestadoButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser local = new JFileChooser();  
			local.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
	        local.setDialogTitle("Escolha um local para salvar");  
	        
	        String nomeEvento = comboBoxEvent.getSelectedItem().toString();
	        String nomeUsuario = comboBoxUser.getSelectedItem().toString();
	        
	        if (nomeEvento.equals("") || nomeUsuario.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;

			} else {
	        
		        local.setFileHidingEnabled(false);
		        int res = local.showSaveDialog(null);  
		        
		        if (res == JFileChooser.APPROVE_OPTION) {
					String caminho = String.valueOf(local.getSelectedFile());
					CheckingCopy checkcopy = new CheckingCopy();
					try {
						RiSEEventMainScreenP.facade.emitirAtestado(nomeUsuario, nomeEvento, periodo, checkcopy);
					} catch (RepositoryException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}
		        }
			}
		}
	}
	//#endif
	
	//#if ${CheckingCopyCertificado} == "T"
	private class GenerateCertificadoButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser local = new JFileChooser();  
			local.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
	        local.setDialogTitle("Escolha um local para salvar");  
	        
	        String nomeEvento = comboBoxEvent.getSelectedItem().toString();
	        String nomeUsuario = comboBoxUser.getSelectedItem().toString();
	        String typeActivity = comboBoxTypeActivity.getSelectedItem().toString();
	        
	        if (nomeEvento.equals("") || nomeUsuario.equals("") || typeActivity.equals("")) {
				JOptionPane.showMessageDialog(getContentPane(),
						"Não pode haver campo vazio.", "Erro",
						JOptionPane.INFORMATION_MESSAGE);
				return;

			} else {
	        
		        local.setFileHidingEnabled(false);
		        int res = local.showSaveDialog(null);  
		        
		        if (res == JFileChooser.APPROVE_OPTION) {
					String caminho = String.valueOf(local.getSelectedFile());
					CheckingCopy checkcopy = new CheckingCopy();
					try {
						RiSEEventMainScreenP.facade.emitirCertificado(nomeUsuario, nomeEvento , periodo, typeActivity, checkcopy);
					} catch (RepositoryException e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								e.toString(), "Erro",
								JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					} catch (DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
			}
		}
	}
	//#endif
	
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
	
	private void carregarUserComboBox(){
		try {
			List<User> list = RiSEEventMainScreenP.facade.getUsers();
			Iterator<User> iterator = list.iterator();
			while(iterator.hasNext()){
				comboBoxUser.addItem(iterator.next().getNameUser());
			}
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
	}
	
	private void carregarTypeActivityComboBox(){
		TypeActivity[] types = TypeActivity.values();
		List<String> typesActivities = new ArrayList<String>();
		for(int i=0; i<types.length; i++){
			typesActivities.add(i, types[i].name());
			comboBoxTypeActivity.addItem(types[i].name());
		}
		
	}
	
	private void pegarPeriodoEvent(){
		int idEvent;
		Event event = new Event();
		
		try {
			idEvent = RiSEEventMainScreenP.facade.getEventIdByName(comboBoxEvent.getSelectedItem().toString());
			event = RiSEEventMainScreenP.facade.searchEvent(idEvent);
		} catch (RepositoryException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (EventNotFoundException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		} catch (EventAlreadyInsertedException e) {
			JOptionPane.showMessageDialog(getContentPane(),
					e.toString(), "Erro",
					JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
		periodo = event.getPeriod();
	}
}
//#endif