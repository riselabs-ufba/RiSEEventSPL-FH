package rise.splcc.derivation;
public class Feature {
	
	
private String name;
private String status;


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

public String toString(){
	return "Name: " + name + "\nStatus: " + status;
}

}
