//#if ${Bugs} == "T"
package rise.splcc.ui2;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.apache.commons.mail.EmailException;

import rise.splcc.exception.RepositoryException;
import rise.splcc.util.Email;

public class BugtrackScreenP extends JInternalFrame {

	 private static BugtrackScreenP instanceBugtrackScreenP;
	 private JPanel contentPane;
	 
	 JButton btnClean;
	 JButton btnBack;
	 JButton btnEnviar;
	 JComboBox<String> comboBoxAssunto;
	 JComboBox<String> comboBoxEscolaridade;
	 
	 private JTextField textFieldNome;
	 private JTextField textFieldTitulo;
	 private JTextField textFieldEmail;
	 private JTextField textFieldArea;
	 private JTextPane textPaneObservacoes;
	 
	 public static BugtrackScreenP getInstanceBugtrackScreenP() {
		 if (instanceBugtrackScreenP == null) {
			 BugtrackScreenP.instanceBugtrackScreenP = new BugtrackScreenP();
		 }
		 return BugtrackScreenP.instanceBugtrackScreenP;
	 } 
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BugtrackScreenP frame = new BugtrackScreenP();
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
	public BugtrackScreenP() {
		
		setTitle("Bugtrack");
		
		int inset = 30;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, 736,
				480);
		
		setMaximizable(true);
		setClosable(true);

		CleanButtonAction cleanAction = new CleanButtonAction();
		BackButtonAction backAction = new BackButtonAction(); 
		EnviarButtonAction enviarAction = new EnviarButtonAction(); 

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 915, 553);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(220, 18, 392, 68);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblImage = new JLabel("");
		lblImage.setBounds(6, 6, 380, 56);
		ImageIcon image = new ImageIcon(getClass().getResource("/images/riseLabs.png"));
		//ImageIcon image = new ImageIcon("/Users/pamsn/Dropbox/00-Doutorado/WorkspaceProjetoSPL/SPLCC/src/images/riseLabs.png");
		Image imag = image.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
		lblImage.setIcon(new ImageIcon(imag));
		panel.add(lblImage);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 99, 829, 335);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(73, 16, 61, 16);
		panel_1.add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(73, 33, 542, 28);
		panel_1.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textPaneObservacoes = new JTextPane();
		textPaneObservacoes.setBounds(73, 208, 690, 103);
		panel_1.add(textPaneObservacoes);
		
		JLabel lblAssunto = new JLabel("Assunto:");
		lblAssunto.setBounds(73, 126, 61, 16);
		panel_1.add(lblAssunto);
		
		comboBoxAssunto = new JComboBox();
		comboBoxAssunto.setBounds(73, 145, 223, 27);
		panel_1.add(comboBoxAssunto);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(381, 143, 382, 28);
		panel_1.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(381, 126, 61, 16);
		panel_1.add(lblTitulo);
		
		JLabel lblObservacoes = new JLabel("Observacoes:");
		lblObservacoes.setBounds(73, 184, 102, 16);
		panel_1.add(lblObservacoes);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(73, 71, 61, 16);
		panel_1.add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(73, 85, 223, 28);
		panel_1.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblEscolaridade = new JLabel("Escolaridade:");
		lblEscolaridade.setBounds(381, 71, 90, 16);
		panel_1.add(lblEscolaridade);
		
		comboBoxEscolaridade = new JComboBox();
		comboBoxEscolaridade.setBounds(381, 87, 203, 27);
		panel_1.add(comboBoxEscolaridade);
		
		JLabel lblAreaDePesquisa = new JLabel("Area de Pesquisa:");
		lblAreaDePesquisa.setBounds(596, 69, 124, 16);
		panel_1.add(lblAreaDePesquisa);
		
		textFieldArea = new JTextField();
		textFieldArea.setBounds(596, 86, 167, 28);
		panel_1.add(textFieldArea);
		textFieldArea.setColumns(10);
		
		btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(443, 446, 117, 29);
		contentPane.add(btnEnviar);
		
		btnClean = new JButton("Clean");
		btnClean.setBounds(572, 446, 117, 29);
		contentPane.add(btnClean);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(704, 446, 117, 29);
		contentPane.add(btnBack);
		
		btnClean.addActionListener(cleanAction);
		btnBack.addActionListener(backAction);
		btnEnviar.addActionListener(enviarAction);
		
		carregarAllComboBox();
	}
	
	private class EnviarButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			Email email = new Email();
			String nome = textFieldNome.getText();
			String assunto = comboBoxAssunto.getSelectedItem().toString();
			String mensagem = textFieldTitulo.getText() + "\n\n" + textPaneObservacoes.getText() + "\n\n\n" + textFieldNome.getText() + "\n" + "Resacher in " + textFieldArea.getText() + "\n" + comboBoxEscolaridade.getSelectedItem().toString() + " - " + textFieldEmail.getText() ; 
			
			try {
				String msg = RiSEEventMainScreenP.facade.sendBug(nome, assunto, mensagem, email);
				JOptionPane.showMessageDialog(getContentPane(),
						msg.toString(), "SUCESSO",
						JOptionPane.INFORMATION_MESSAGE);
//				email.sendBugtrackEmail(nome, assunto, mensagem, email);
			} catch (EmailException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void carregarAllComboBox(){
	
		comboBoxAssunto.addItem("Erro");
		comboBoxAssunto.addItem("Duvida");
		comboBoxAssunto.addItem("Sugestao");
		
		comboBoxEscolaridade.addItem("Graduacao (Cursando)");
		comboBoxEscolaridade.addItem("Graduacao (Concluido)");
		comboBoxEscolaridade.addItem("Mestrado (Cursando)");
		comboBoxEscolaridade.addItem("Mestrado (Concluido)");
		comboBoxEscolaridade.addItem("Doutorado (Cursando)");
		comboBoxEscolaridade.addItem("Doutorado (Concluido)");
	}
	
	private class BackButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
			BugtrackScreenP.instanceBugtrackScreenP = null;
			
		}
	}
	
	private class CleanButtonAction  implements ActionListener{ 

		@Override
		public void actionPerformed(ActionEvent e) {
			
			cleanFields();

		}
	}
	
	private void cleanFields(){
		textFieldArea.setText("");
		textFieldEmail.setText("");
		textFieldNome.setText("");
		textFieldTitulo.setText("");
		textPaneObservacoes.setText("");
		comboBoxAssunto.setSelectedItem(0);
		comboBoxEscolaridade.setSelectedItem(0);
	}
}
//#endif