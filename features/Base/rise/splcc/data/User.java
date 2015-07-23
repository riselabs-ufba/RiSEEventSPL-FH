
package rise.splcc.data;

public class User {
	
	public enum TypeUser{
		Associado, Profissional, Estudante
	}
	
	private int idUser;
	private String password;
	private String nameUser;
	private TypeUser typeUser;
	private String email;
	private String filiation;
	
	
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNameUser() {
		return nameUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public TypeUser getTypeUser() {
		return typeUser;
	}
	public void setTypeUser(TypeUser typeUser) {
		this.typeUser = typeUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFiliation() {
		return filiation;
	}
	public void setFiliation(String filiation) {
		this.filiation = filiation;
	}

	
	public String toString(){
		return "User Id:"+ idUser + "\nName:" + nameUser + "\nFiliation:" + filiation + "\nType:" + typeUser.toString() + "\nEmail:" + email;
	}
	
}

