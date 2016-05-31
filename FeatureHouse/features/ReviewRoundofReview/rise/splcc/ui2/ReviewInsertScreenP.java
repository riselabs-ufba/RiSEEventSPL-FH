package rise.splcc.ui2;

public class ReviewInsertScreenP extends JInternalFrame  {

	private void carregarComboBoxStatus(){
		original();
		
		//#if ${ReviewRoundofReview} == "T"
		statusComboBox.addItem("Em Analise");
		//#endif
	}
	
}
