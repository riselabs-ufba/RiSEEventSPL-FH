package rise.splcc.ui2;


public class AssignmentInsertScreenP extends JInternalFrame{

	private void notifyConflictOfInterest(){
		original();
		
		JOptionPane.showMessageDialog(getContentPane(),
				"Essa atribuicao nao pode ser feita por conflito de interesses", "Erro",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
