package models.com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventSchedule {
	private String eventScheduleID;
	private String eventID;
    private Employee employee;
    private Equipment equipment;
    private Date eventDate;
    
    
    
	public String getEventScheduleID() {
		return eventScheduleID;
	}
	public void setEventScheduleID(String eventScheduleID) {
		this.eventScheduleID = eventScheduleID;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Equipment getEquipment() {
		return equipment;
	}
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	        	this.eventDate = dateFormat.parse(eventDate);
	        } catch (ParseException e) {
	            e.printStackTrace();
	            // Handle parsing exception as needed
	        }
	}
	
	
}
