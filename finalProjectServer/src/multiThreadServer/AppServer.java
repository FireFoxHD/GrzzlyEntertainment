package multiThreadServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;



public class AppServer {

	private static Connection dbConn = null;
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	public static int count=0;


	public AppServer() {
		this.createConnection();
		this.waitForRequest();
	}

	private void createConnection() {
		try {
			serverSocket = new ServerSocket(8888);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static Connection getDatabaseConnection() {
		if (dbConn == null) {
			String url = "jdbc:mysql://localhost:3306/grizzlyequipment";
			try {
				dbConn = DriverManager.getConnection(url, "root", "");
				JOptionPane.showMessageDialog(null, "DB connected");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "DB not connected");
			}
		}
		return dbConn;

	}




	private void waitForRequest() {
	    
	    getDatabaseConnection();
	    try {
	        while (true) {
	        	connectionSocket = serverSocket.accept();
	        	ThreadedClass threadObj = new ThreadedClass(connectionSocket);
	        	Thread thread = new Thread(threadObj);
	        	thread.start();
	        	count = count+1;
	        	System.out.println("\nNumber of threads :"+count);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}


}
