package rise.splcc.data;

public class Submission {

	public enum TypeSubmission{
		original();
		
		//#if ${SubmissionCompleta} == "T"
		Completa,
		//#endif
	}
	
	
}
