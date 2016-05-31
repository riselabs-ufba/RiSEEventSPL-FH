//#if ${Organizer} == "T"
package rise.splcc.data;

public class Organizer extends User{

	private int idActivity;
	public enum TypeOrganizer{
		
		GeneralChair, 
		
		ProgramChair, 
		
		WorkshopChair, 
		
		TutorialChair,
		
		ProceedingsChair,
		
		PanelChair
		
	}
	private TypeOrganizer typeOrganizer;
	
	public TypeOrganizer getTypeOrganizer() {
		return typeOrganizer;
	}

	public void setTypeOrganizer(TypeOrganizer typeOrganizer) {
		this.typeOrganizer = typeOrganizer;
	}

	public int getIdActivity() {
		return idActivity;
	}

	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}

	public String toString(){
		//return "\nIdUser:" + getIdUser() + "\nName:" + getNameUser() + "\nEmail:" + getEmail() + "\nType:" + typeOrganizer.name() + "\nFiliation:" + getFiliation();
		return super.toString() + "\nType:" + typeOrganizer.name();
	}
	
	
}
//#endif