package rise.splcc.ui2;

public class CheckingCopyTypeScreenP  extends JInternalFrame {

	public void init(){
		original();
		
		//#if ${CheckingCopyCertificado} == "T"
		JButton btnCertificado = new JButton("Generate Certificado");
		btnCertificado.setBounds(481, 89, 183, 29);
		getContentPane().add(btnCertificado);
		//#endif
		
		//#if ${CheckingCopyCertificado} == "T"
		GenerateCertificadoButtonAction generateCertificadoAction = new GenerateCertificadoButtonAction();
		//#endif

		//#if ${CheckingCopyCertificado} == "T"
		btnCertificado.addActionListener(generateCertificadoAction);
		//#endif
	}
	
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
	
}
