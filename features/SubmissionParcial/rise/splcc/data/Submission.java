package rise.splcc.data;

public class Submission {

	public enum TypeSubmission{
		//#if ${SubmissionParcial} == "T" 
		Parcial
		//#endif
	}
	
	private TypeSubmission type;
	
	public void setType(TypeSubmission type) {
		this.type = type;
	}
	
}
