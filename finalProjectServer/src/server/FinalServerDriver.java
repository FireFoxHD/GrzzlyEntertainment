package server;

import java.util.ArrayList;
import java.util.List;

import models.com.Event;



public class FinalServerDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    new AppServer();
	    
	    Event eventObj = new Event("12345","SocaMoca","12-12-2023");
		List<Event> eventList= new ArrayList<Event>();
		eventObj.addEventToFile();
		eventList= eventObj.readAll();
//		stu.delete();
		
		for(Event event :eventList) {
			System.out.println(event);
		}
        // Test functionalities by sending sample data
	}

}
