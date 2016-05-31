//#if ${Reviewer} == "T"
package rise.splcc.data;

public class Reviewer extends User{

	private String knowledgeArea;

	public String getKnowledgeArea() {
		return knowledgeArea;
	}

	public void setKnowledgeArea(String knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}

	@Override
	public boolean equals(Object obj) { 
		if (obj == this) { 
			return true; 
		} 
		if (obj == null || obj.getClass() != this.getClass()) { 
			return false; 
		} 
		
		Reviewer reviewer = (Reviewer) obj; 
		
		return (this.getIdUser() == ((Reviewer)obj).getIdUser() && 
				   this.getPassword().equals(((Reviewer)obj).getPassword()) && 
				   this.getNameUser().equals(((Reviewer)obj).getNameUser()) &&
				   this.getTypeUser() == ((Reviewer)obj).getTypeUser() &&
				   this.getEmail().equals(((Reviewer)obj).getEmail())&&
				   this.getKnowledgeArea().equals(((Reviewer)obj).getKnowledgeArea()));
	}
	
	public String toString(){
		//return "User Id:"+ getIdUser() + "\nName:" + getNameUser() + "\nType:" + getTypeUser().toString() + "\nEmail:" + getEmail()   + "\nKnowledgeArea:" + knowledgeArea + "\nFiliation:" + getFiliation() ;
		//return "\nKnowledge Area:" + knowledgeArea;
		return super.toString()  + "\nKnowledgeArea:" + knowledgeArea;
	}

}
//#endif