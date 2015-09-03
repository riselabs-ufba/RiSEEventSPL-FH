
//#if ${ActivityMinicurso} == "T" or ${ActivityTutorial} == "T" or ${ActivityPainel} == "T" or ${ActivityWorkshop} == "T" or ${ActivityMainTrack} == "T"
package rise.splcc.data;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.itextpdf.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class Activity {
	private int idActivity;
	private int idEvent;
	private int idRegistration;
	private String nameActivity;
	private String descriptionActivity;

	//solucao sera dividir os enums, contendo todas as possibilidades possiveis e colocando cc
	public enum TypeActivity{
		//#if ${ActivityMainTrack} == "T"
		MainTrack
		//#endif
	}
	
	private float value;
	private float hourlyLoad;
	private String date;
	private String hour;
	private int numberOfParticipants;
	private int registrationLimit;
	private TypeActivity typeActivity;
	
	public TypeActivity getTypeActivity() {
		return typeActivity;
	}
	public void setTypeActivity(TypeActivity typeActivity) {
		this.typeActivity = typeActivity;
	}
	
	
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public String getNameActivity() {
		return nameActivity;
	}
	public void setNameActivity(String nameActivity) {
		this.nameActivity = nameActivity;
	}
	public String getDescriptionActivity() {
		return descriptionActivity;
	}
	public void setDescriptionActivity(String descriptionActivity) {
		this.descriptionActivity = descriptionActivity;
	}
	public int getNumberOfParticipants() {
		return numberOfParticipants;
	}
	public void setNumberOfParticipants(int numberOfParticipants) {
		this.numberOfParticipants = numberOfParticipants;
	}
	public int getRegistrationLimit() {
		return registrationLimit;
	}
	public void setRegistrationLimit(int registrationLimit) {
		this.registrationLimit = registrationLimit;
	}
	public int getIdActivity() {
		return idActivity;
	}
	public void setIdActivity(int idActivity) {
		this.idActivity = idActivity;
	}
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	public int getIdRegistration() {
		return idRegistration;
	}
	public void setIdRegistration(int idRegistration) {
		this.idRegistration = idRegistration;
	}

	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public float getHourlyLoad() {
		return hourlyLoad;
	}
	public void setHourlyLoad(float hourlyLoad) {
		this.hourlyLoad = hourlyLoad;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString(){
		return "Activity Id:"+ idActivity + "\nName:" + nameActivity + "\nDescription:" + descriptionActivity + "\nType:" + typeActivity.toString() + "\nValeu:" + value + "\nHourly Load:" + hourlyLoad + "\nDate:" + date + "\nHour:" + hour+ "\nN Of Part.::" + numberOfParticipants+ "\nReg. Limit:" + registrationLimit;
	}

}
//#endif
