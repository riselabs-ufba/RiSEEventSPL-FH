package rise.splcc.data;

public class Submission {

	public enum TypeSubmission{
		//#if ${SubmissionCompleta} == "T"
		Completa
		//#endif
	}

	private TypeSubmission type;
	
	public void setType(TypeSubmission type) {
		this.type = type;
	}
	
	
}
