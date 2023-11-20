package multiThreadServer;

import java.io.*;
import java.net.*;
import java.sql.*;

import models.com.Customer;
import models.com.Employee;

public class AppServer {
	private ServerSocket serverSocket;
	private Connection dbConn = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new AppServer();
	}

	public AppServer() {
		this.createConnection();
		this.waitForRequest();
	}

	private void createConnection() {
		try {
			serverSocket = new ServerSocket(8888);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getDatabaseConnection();
	}

	private void waitForRequest() {
		try {
			while (true) {
				Socket connectionSocket = serverSocket.accept();
				ClientHandler clientHandler = new ClientHandler(connectionSocket, this);
				Thread clientThread = new Thread(clientHandler);
				clientThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Connection getDatabaseConnection() {
		// Database connection logic
		return dbConn;
	}

	public void addCustomerToFile(Customer cust) {
		// TODO Auto-generated method stub

	}

	public void addEmployeeToFile(Employee emp) {
		// TODO Auto-generated method stub

	}

}

//	private void waitForRequest() {
//	    
//	    getDatabaseConnection();
//	    try {
//	        while (true) {
//	        	Socket connectionSocket = serverSocket.accept();
//	        	ThreadedClass threadObj = new ThreadedClass(connectionSocket);
//	        	Thread thread = new Thread(threadObj);
//	        	thread.start();
//	        	count = count+1;
//	        	System.out.println("\nNumber of threads :"+count);
//	        }
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}

//
//
//}
