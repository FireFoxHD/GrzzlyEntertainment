package multiThreadServer;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.com.Customer;
import models.com.Employee;

public class ClientHandler implements Runnable {
	private Socket clientSocket;
	private ObjectInputStream objIs;
	private ObjectOutputStream objOs;
	private Connection dbConnection;

	public ClientHandler(Socket socket) {
		this.clientSocket = socket;
		this.dbConnection = AppServer.getDatabaseConnection();
		try {
			objIs = new ObjectInputStream(clientSocket.getInputStream());
			objOs = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			String action;
			while (true) {
				try {
					if (clientSocket != null) {
						action = (String) objIs.readObject();

						// Handle requests based on the action received from the client
						switch (action) {
						case "AddCustomer":
							Customer customer = (Customer) objIs.readObject();
							addCustomerToDatabase(customer);
							objOs.writeObject(true);
							break;
						case "FindCustomer":
							String customerId = (String) objIs.readObject();
							Customer foundCustomer = findCustomerById(customerId);
							objOs.writeObject(foundCustomer);
							break;
						case "AddDocument":
							// Handling code for adding a document to the database
							break;
						case "FindDocument":
							// Handling code for finding a document in the database
							break;
						case "AddEmployee":
							// Handling code for adding an employee to the database
							break;
						case "FindEmployee":
							// Handling code for finding an employee in the database
							break;
						case "AddEquipment":
					        // Handling code for adding equipment to the database
					        break;
					    case "FindEquipment":
					        // Handling code for finding equipment in the database
					        break;
					    case "AddEquipmentStock":
					        // Handling code for adding equipment stock to the database
					        break;
					    case "FindEquipmentStock":
					        // Handling code for finding equipment stock in the database
					        break;
					    case "AddEvent":
					        // Handling code for adding an event to the database
					        break;
					    case "FindEvent":
					        // Handling code for finding an event in the database
					        break;
					    case "AddEventSchedule":
					        // Handling code for adding an event schedule to the database
					        break;
					    case "FindEventSchedule":
					        // Handling code for finding an event schedule in the database
					        break;
					    case "AddMessage":
					        // Handling code for adding a message to the database
					        break;
					    case "FindMessage":
					        // Handling code for finding a message in the database
					        break;
					    case "AddRentalRequest":
					        // Handling code for adding a rental request to the database
					        break;
					    case "FindRentalRequest":
					        // Handling code for finding a rental request in the database
					        break;
					    case "AddTransaction":
					        // Handling code for adding a transaction to the database
					        break;
					    case "FindTransaction":
					        // Handling code for finding a transaction in the database
					        break;
					    case "DeleteCustomer":
					        // Handling code for deleting a customer from the database
					        break;
					    case "DeleteDocument":
					        // Handling code for deleting a document from the database
					        break;
					    case "DeleteEmployee":
					        // Handling code for deleting an employee from the database
					        break;
					    // Add cases for other tables
					    case "DeleteEquipment":
					        // Handling code for deleting equipment from the database
					        break;
					    case "DeleteEquipmentStock":
					        // Handling code for deleting equipment stock from the database
					        break;
					    case "DeleteEvent":
					        // Handling code for deleting an event from the database
					        break;
					    case "DeleteEventSchedule":
					        // Handling code for deleting an event schedule from the database
					        break;
					    case "DeleteMessage":
					        // Handling code for deleting a message from the database
					        break;
					    case "DeleteRentalRequest":
					        // Handling code for deleting a rental request from the database
					        break;
					    case "DeleteTransaction":
					        // Handling code for deleting a transaction from the database
					        break;
						default:
							break;
							
						}
					}
				} catch (ClassCastException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				objOs.close();
				objIs.close();
				clientSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

	// Example for adding a Customer record
	private void addCustomerToDatabase(Customer customer) {
	    String sql = "INSERT INTO dblab.customers(customerID, username,password)" 
	            + " VALUES('" + customer.getCustomerID() + "','"
	            + customer.getUsername() + "','" + customer.getPassword() + "');";
	    try {
	        Statement stmt = dbConnection.createStatement();

	        if ((stmt.executeUpdate(sql) == 1)) {
	            objOs.writeObject(true);
	        } else {
	            objOs.writeObject(false);
	        }
	    } catch (IOException | SQLException e) {
	        e.printStackTrace();
	    }
	}

	// Example for adding an Employee record
	// Example for finding a Customer record by ID
	private Customer findCustomerById(String customerId) {
	    Customer customer = null;
	    String sql = "SELECT * FROM dblab.customers WHERE customerID='" + customerId + "';";
	    try {
	    	Statement stmt = dbConnection.createStatement();
	        ResultSet rs = stmt.executeQuery(sql);

	        if (rs.next()) {
	            // Retrieve customer data and create a Customer object
	       
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return customer;
	}

	// Example for deleting a Customer record by ID
	private void deleteCustomerById(String customerId) {
	    String sql = "DELETE FROM dblab.customers WHERE customerID='" + customerId + "';";
	    try {
	        Statement stmt = dbConnection.createStatement();

	        if ((stmt.executeUpdate(sql) == 1)) {
	            objOs.writeObject(true);
	        } else {
	            objOs.writeObject(false);
	        }
	    } catch (IOException | SQLException e) {
	        e.printStackTrace();
	    }
	}

	// Example for deleting an Employee record by ID


	// Implement similar methods for other tables...
// TEMPLATE
//	private void addEntityToDatabase(EntityType entity) {
//	    String sql = ""; // Your SQL INSERT statement here based on the entity fields
//	    try {
//	        Statement stmt = dbConnection.createStatement();
//
//	        int rowsAffected = stmt.executeUpdate(sql);
//	        if (rowsAffected == 1) {
//	            objOs.writeObject(true);
//	        } else {
//	            objOs.writeObject(false);
//	        }
//	    } catch (IOException | SQLException e) {
//	        e.printStackTrace();
//	    }
//	}
//	private void findEntityFromDatabase(String entityId) {
//	    String sql = ""; // Your SQL SELECT statement here based on the entityId
//	    try {
//	        Statement stmt = dbConnection.createStatement();
//
//	        // Execute the SQL query and handle the results appropriately
//	        ResultSet resultSet = stmt.executeQuery(sql);
//
//	        // Process the resultSet and send the entity object back
//	        // objOs.writeObject(entityObject); // Send the entity object
//	    } catch (IOException | SQLException e) {
//	        e.printStackTrace();
//	    }
//	}
//	private void deleteEntityFromDatabase(String entityId) {
//	    String sql = ""; // Your SQL DELETE statement here based on the entityId
//	    try {
//	        Statement stmt = dbConnection.createStatement();
//
//	        int rowsAffected = stmt.executeUpdate(sql);
//	        if (rowsAffected == 1) {
//	            objOs.writeObject(true);
//	        } else {
//	            objOs.writeObject(false);
//	        }
//	    } catch (IOException | SQLException e) {
//	        e.printStackTrace();
//	    }
//	}




	// Implement similar methods (add, find, delete.) for other tables
	// For Employee, Equipment, EquipmentStock, Event, EventSchedule, Message,
	// RentalRequest, Transaction
	// For each table, perform respective database operations
}
