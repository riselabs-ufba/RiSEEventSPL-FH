//#if ${SubmissionParcial} == "T" or ${SubmissionCompleta} == "T"
package rise.splcc.data;

import java.sql.Blob;

public class Submission {

	public enum TypeSubmission{
		//#if ${SubmissionCompleta} == "T"
		Completa,
		//#endif
		
		//#if ${SubmissionParcial} == "T"
		Parcial
		//#endif
	}
	
	
	private int idSubmission;
	private int idActivity;
	private TypeSubmission type;
	private String abstractPaper;
	private Blob attachment;
	private String keywords;
	private String title;
	
	
	public int getIdSubmission() {
		return idSubmission;
	}
	public void setIdSubmission(int idSubmission) {
		this.idSubmission = idSubmission;
	}
	public int getIdActivity() {
		return idActivity;
	}
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}
	public String getAbstractPaper() {
		return abstractPaper;
	}
	public void setAbstractPaper(String abstractPaper) {
		this.abstractPaper = abstractPaper;
	}
	public Blob getAttachment() {
		return attachment;
	}
	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public TypeSubmission getType() {
		return type;
	}
	public void setType(TypeSubmission type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String toString(){
		return "IdSubmission:"+ idSubmission + "\nIdActivity:" + idActivity  + "\nTitle:" + title + "\nAbstract:" + abstractPaper+ "\nKeyWords:" + keywords + "\nType:" + type.toString();
	}
	
}
//#endif
