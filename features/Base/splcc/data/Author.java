//#if ${InsertAuthors} == "T"
package rise.splcc.data;

public class Author {

	private int idAuthor;
	private String name;
	private String filiation;
	private String email;
	
	
	public int getIdAuthor() {
		return idAuthor;
	}
	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFiliation() {
		return filiation;
	}
	public void setFiliation(String filiation) {
		this.filiation = filiation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(){
		return "Author Id:"+ idAuthor + "\nName:" + name + "\nFiliation:" + filiation + "\nEmail:" + email;
	}
	
}
//#endif