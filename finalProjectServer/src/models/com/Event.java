package models.com;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "events")
public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "eventID")
	private String eventID;

	@Column(name = "eventName")
	private String eventName;

	@Temporal(TemporalType.DATE)
	@Column(name = "eventDate")
	private Date eventDate;

	public Event() {

	}

	/**
	 * @param eventID
	 * @param eventName
	 * @param date
	 */
	public Event(String eventID, String eventName, String eventDate) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.eventDate = dateFormat.parse(eventDate);
		} catch (ParseException e) {
			e.printStackTrace();
			// Handle parsing exception as needed
		}

	}

	public String getEventID() {
		return eventID;
	}

	public void setEventID(String eventID) {
		this.eventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(String date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.eventDate = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			// Handle parsing exception as needed
		}
	}

	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", eventName=" + eventName + ", date=" + eventDate + "]";
	}
}
