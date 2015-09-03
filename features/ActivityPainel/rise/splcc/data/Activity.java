
//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package rise.splcc.data;

public class Activity {
	
	//solucao sera dividir os enums, contendo todas as possibilidades possiveis e colocando cc
	public enum TypeActivity{
		//#if ${ActivityPainel} == "T"
		Painel
		//#endif
	}
}
//#endif
