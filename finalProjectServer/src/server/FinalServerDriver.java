package server;

import java.util.ArrayList;
import java.util.List;

import models.com.Event;



public class FinalServerDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Good Connection");
		 try {
				Event eventObj = new Event("10002","VVS","2023-12-22");
				List<Event> eventList= new ArrayList<Event>();
				//eventObj.addEventToFile();
				eventList= eventObj.readAll();
//			stu.delete();
				
				for(Event event :eventList) {
					System.out.println(event);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	    new AppServer();
	    
	   
        // Test functionalities by sending sample data
	}

}
