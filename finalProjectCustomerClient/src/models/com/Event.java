package models.com;

import java.util.Date;

public class Event {

	private String eventID ;
	private String eventName;
	private Date date;
	
	public Event() {
		
	}
	/**
	 * @param eventID
	 * @param eventName
	 * @param date
	 */
	public Event(String eventID, String eventName, Date date) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.date = date;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Event [eventID=" + eventID + ", eventName=" + eventName + ", date=" + date + "]";
	}
	
	
}
