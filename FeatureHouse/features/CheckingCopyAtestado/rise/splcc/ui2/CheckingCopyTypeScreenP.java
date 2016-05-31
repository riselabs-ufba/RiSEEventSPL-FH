package rise.splcc.ui2;

public class CheckingCopyTypeScreenP  extends JInternalFrame {
	
	public void init(){
		original();
		
		//#if ${CheckingCopyAtestado} == "T"
		JButton btnAtestado = new JButton("Generate Atestado");
		btnAtestado.setBounds(481, 37, 150, 29);
		getContentPane().add(btnAtestado);
		//#endif
		
		//#if ${CheckingCopyAtestado} == "T"
		GenerateAtestadoButtonAction generateAtestadoAction = new GenerateAtestadoButtonAction();
		//#endif

		//#if ${CheckingCopyAtestado} == "T"
		btnAtestado.addActionListener(generateAtestadoAction);
		//#endif

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
	
}
