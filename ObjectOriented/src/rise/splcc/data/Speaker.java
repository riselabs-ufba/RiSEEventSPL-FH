//#if ${Speaker} == "T"
package rise.splcc.data;

public class Speaker extends User{
	private String biography;

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	@Override
	public boolean equals(Object obj) { 
		if (obj == this) { 
			return true; 
		} 
		if (obj == null || obj.getClass() != this.getClass()) { 
			return false; 
		} 
		
		Speaker speaker = (Speaker) obj; 
		
		return (this.getIdUser() == ((Speaker)obj).getIdUser() && 
				   this.getPassword().equals(((Speaker)obj).getPassword()) && 
				   this.getNameUser().equals(((Speaker)obj).getNameUser()) &&
				   this.getTypeUser() == ((Speaker)obj).getTypeUser() &&
				   this.getEmail().equals(((Speaker)obj).getEmail())&&
				   this.getBiography().equals(((Speaker)obj).getBiography()));
	}
	
	public String toString(){
		//return "User Id:"+ getIdUser() + "\nName:" + getNameUser() + "\nType:" + getTypeUser().toString() + "\nEmail:" + getEmail()  + "\nBiography:" + biography;
		//return "\nBiography:" + biography;
		return super.toString() + "\nBiography:" + biography;
	}
}
//#endif