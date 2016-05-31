package rise.splcc.data;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.Element;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class Event {
	private int idEvent;
	private String eventName;
	private String period;
	private String place;
	private String institution;
	private String sponsors;
	
	
	public int getIdEvent() {
		return idEvent;
	}
	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getSponsors() {
		return sponsors;
	}
	public void setSponsors(String sponsors) {
		this.sponsors = sponsors;
	}
	
//	public String getEventNameAndId(){
//		return this.idEvent +" - "+ this.eventName;
//	}
	
	public String toString(){
		return "Event Name:"+ eventName + "\nPeriod:" + period + "\nPlace:" + place+ "\nInstitution:" + institution + "\nSponsors:" + sponsors;
	}
	
}

