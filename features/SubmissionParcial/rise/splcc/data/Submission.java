package rise.splcc.data;

public class Submission {

	public enum TypeSubmission{
		original();
		
		//#if ${SubmissionParcial} == "T" 
		Parcial,
		//#endif
	}
	
}
