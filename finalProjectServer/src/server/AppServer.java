package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import models.com.Customer;
import models.com.Employee;
import models.com.Equipment;
import models.com.EquipmentStock;
import models.com.Event;
import models.com.EventSchedule;
import models.com.Message;
import models.com.RentalRequest;
import models.com.TransactionClass;

public class AppServer {

	private static final Logger serverLogger = LogManager.getLogger(AppServer.class.getName());
	private static Connection dbConn = null;
	private ServerSocket serverSocket;
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private Statement stmt;
	private ResultSet result = null;

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
			serverLogger.error(e);
		}
	}

	private void configureStreams() {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			serverLogger.error(e);
		}
	}

	public static Connection getDatabaseConnection() {
		if (dbConn == null) {
			String url = "jdbc:mysql://localhost:3306/grizzlyequipment";
			try {
				dbConn = DriverManager.getConnection(url, "root", "");
				JOptionPane.showMessageDialog(null, "DB connected");
				serverLogger.info("No errors");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "DB not connected");
				serverLogger.error(e);
			}
		}
		return dbConn;

	}

	private void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			serverLogger.error(e);
		}

	}

	void addCustomerToFile(Customer customer) {
		String sql = "INSERT INTO customer(customerID,username,password,accountBalance)" + " VALUES('"
				+ customer.getCustomerID() + "','" + customer.getUsername() + "','" + customer.getPassword() + "','"
				+ customer.getAccountBalance() + "');";
		try {
			stmt = dbConn.createStatement();

			if ((stmt.executeUpdate(sql) == 1)) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException ioe) {
			// ideally you want to save errors to a log file
			serverLogger.error(ioe);
			ioe.printStackTrace();
		} catch (SQLException e) {
			serverLogger.error(e);
			e.printStackTrace();
		}
	}

	private Customer findCustomerById(String id) {
		Customer custObj = new Customer();

		String query = "SELECT * FROM grizzlyequipment.customer WHERE customerID =" + id;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				custObj.setCustomerID(result.getString(1));
				custObj.setUsername(result.getString(2));
				custObj.setPassword(result.getString(3));
				custObj.setAccountBalance(result.getDouble(4));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return custObj;
	}

	public void addEmployeeToFile(Employee employee) {
		String sql = "INSERT INTO grizzlyequipment.employee(employeeID,username,password)" + " VALUES('"
				+ employee.getEmployeeID() + "','" + employee.getUsername() + "','" + employee.getPassword() + "');";
		try {
			stmt = dbConn.createStatement();

			if ((stmt.executeUpdate(sql) == 1)) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException ioe) {
			// ideally you want to save errors to a log file
			ioe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Employee findEmployeeById(String id) {
		Employee empObj = new Employee();
		String query = "SELECT * FROM grizzlyequipment.employee WHERE employeeID =" + id;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				empObj.setEmployeeID(result.getString(1));
				empObj.setUsername(result.getString(2));
				empObj.setPassword(result.getString(3));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empObj;
	}

	void addEquipmentToFile(Equipment equipment) {
		String sql = "INSERT INTO grizzlyequipment.equipment(equipmentID,equipmentName,equipmentCategory,isAvailable)"
				+ " VALUES('" + equipment.getEquipmentID() + "','" + equipment.getEquipmentName() + "','"
				+ equipment.getEquipmentCategory() + "','" + equipment.isAvailabilityStatus() + "','"
				+ equipment.getPrice() + "');";
		try {
			stmt = dbConn.createStatement();

			if ((stmt.executeUpdate(sql) == 1)) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException ioe) {
			// ideally you want to save errors to a log file
			ioe.printStackTrace();
		} catch (SQLException e) {
			serverLogger.info("Issue with SQL");
			e.printStackTrace();
		}
	}

	private Equipment findEquipmentById(String id) {
		Equipment equObj = new Equipment();
		String query = "SELECT * FROM grizzlyequipment.equipment WHERE equipmentID =" + id;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				equObj.setEquipmentID(result.getString(1));
				equObj.setEquipmentName(result.getString(2));
				;
				equObj.setEquipmentCategory(result.getString(3));
				equObj.setAvailabilityStatus(result.getBoolean(4));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return equObj;
	}

	public void addMessage(Message message) {
		String sql = "INSERT INTO grizzlyequipment.message(messageID,customerID,employeeID,messageContent)"
				+ " VALUES('" + message.getMessageID() + "','" + message.getCustomerID() + "','"
				+ message.getEmployeeID() + "','" + message.getMessageContent() + "');";
		try {
			stmt = dbConn.createStatement();

			if ((stmt.executeUpdate(sql) == 1)) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException ioe) {
			// ideally you want to save errors to a log file
			ioe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Message findMessageById(String id) {
		Message messObj = new Message();
		String query = "SELECT * FROM grizzlyequipment.message WHERE messageID =" + id;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				messObj.setMessageID(result.getString(1));
				messObj.setCustomerID(result.getString(2));
				;
				messObj.setEmployeeID(result.getString(3));
				messObj.setMessageContent(result.getString(4));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messObj;
	}

	public void addTransactionToFile(TransactionClass transaction) {
		String sql = "INSERT INTO grizzlyequipment.transaction(transactionId,customerID,requestID,transactionDate,amountPaid)"
				+ " VALUES('" + transaction.getTransactionID() + "','" + transaction.getCustomerID() + "','"
				+ transaction.getRequestID() + "','" + transaction.getTransactionDate() + "','"
				+ transaction.getAmountPaid() + "');";
		try {
			stmt = dbConn.createStatement();

			if ((stmt.executeUpdate(sql) == 1)) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException ioe) {
			// ideally you want to save errors to a log file
			ioe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private TransactionClass findTransactionById(String id) {
		TransactionClass tranObj = new TransactionClass();
		String query = "SELECT * FROM grizzlyequipment.transaction WHERE transactionID =" + id;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				tranObj.setTransactionID(result.getString(1));
				tranObj.setCustomerID(result.getString(2));
				tranObj.setRequestID(result.getString(3));
				tranObj.setTransactionDate(result.getString("transactionDate"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tranObj;
	}

	void addRentalRequestToFile(RentalRequest rentalRequest) {
		String sql = "INSERT INTO grizzlyequipment.rentalrequest(transactionId,customerID,requestID,transactionDate,amountPaid)"
				+ " VALUES('" + rentalRequest.getRequestID() + "','" + rentalRequest.getCustomerID() + "','"
				+ rentalRequest.getEquipmentID() + "','" + rentalRequest.getRentalDate() + "','"
				+ rentalRequest.getQuotationCost() + "','" + rentalRequest.getQuotationCost() + "');";
		try {
			stmt = dbConn.createStatement();

			if ((stmt.executeUpdate(sql) == 1)) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException ioe) {
			// ideally you want to save errors to a log file
			ioe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private RentalRequest findRentalReqById(String id) {
		RentalRequest tranObj = new RentalRequest();
		String query = "SELECT * FROM grizzlyequipment.transaction WHERE transactionID =" + id;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				tranObj.setRequestID(result.getString(1));
				tranObj.setCustomerID(result.getString(2));
				tranObj.setEquipmentID(result.getString(3));
				tranObj.setRentalDate(result.getString("rentalDate"));
				tranObj.setQuotationCost(result.getDouble(5));
				tranObj.setRentalStatus(result.getBoolean(6));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tranObj;
	}

	private Event findEventById(String id) {
		// TODO Auto-generated method stub
		Event eventObj = new Event();
		String query = "SELECT * FROM grizzlyequipment.event WHERE eventID =" + id;
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				eventObj.setEventID(result.getString(1));
				eventObj.setEventName(result.getString(2));
				eventObj.setEventDate(result.getString("rentalDate"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return eventObj;
	}


	private void waitForRequest() {
		String action = "";
		getDatabaseConnection();
		Employee empObj = null;
		Customer custObj = null;
		Equipment equObj = null;
		List<EquipmentStock> equipmentStockObj;
		Message messObj = null;
		TransactionClass tranObj = null;
		EventSchedule eventScheduleObj=null;
		
		RentalRequest rentalReqObj = null;
		Event eventObj;

		try {

			while (true) {
				try {
					connectionSocket = serverSocket.accept();
					this.configureStreams();
					// Process the request or handle incoming data here
					// Example: Determine request type and call appropriate methods
					// based on the received request.
					action = (String) objIs.readObject();
					if (action.equalsIgnoreCase("Add Employee")) {
						empObj = (Employee) objIs.readObject();
						this.addEmployeeToFile(empObj);
						objOs.writeObject(true);
					}if (action.equalsIgnoreCase("Add Customer")) {
						custObj = (Customer) objIs.readObject();
						this.addCustomerToFile(custObj);
						objOs.writeObject(true);
					}if (action.equalsIgnoreCase("Add Equipment")) {
						equObj = (Equipment) objIs.readObject();
						this.addEquipmentToFile(equObj);
						objOs.writeObject(true);
					}if (action.equalsIgnoreCase("Add Message")) {
						messObj = (Message) objIs.readObject();
						this.addMessage(messObj);
						objOs.writeObject(true);
					}if (action.equalsIgnoreCase("Add Transaction")) {
						tranObj = (TransactionClass) objIs.readObject();
						this.addTransactionToFile(tranObj);
						objOs.writeObject(true);
					}if (action.equalsIgnoreCase("Add Rental Request")) {
						rentalReqObj = (RentalRequest) objIs.readObject();
						this.addRentalRequestToFile(rentalReqObj);
						objOs.writeObject(true);
					}if (action.equalsIgnoreCase("Add Event")) {
						eventObj = new Event();
						eventObj.addEventToFile();
						objOs.writeObject(true);
					}if (action.equalsIgnoreCase("Update Event")) {
						eventObj = new Event();
						eventObj.update();
						objOs.writeObject(true);
					}
					if (action.equalsIgnoreCase("Delete Event")) {
						eventObj = new Event();
						eventObj.deleteEvent();
						objOs.writeObject(true);
					}
					if(action.equalsIgnoreCase("Add Equipment to Event")) {
						eventObj = (Event) objIs.readObject();
						this.addScheduleEquipmentsForEvent(eventScheduleObj);
						objOs.writeObject(true);
					}
					if (action.equalsIgnoreCase("Find Employee")) {
						String id = (String) objIs.readObject();
						empObj = findEmployeeById(id);
						objOs.writeObject(empObj);
					}
					if (action.equalsIgnoreCase("Find Customer")) {
						String id = (String) objIs.readObject();
						custObj = findCustomerById(id);
						objOs.writeObject(custObj);
					}if (action.equalsIgnoreCase("Find Equipment")) {
						String id = (String) objIs.readObject();
						equObj = findEquipmentById(id);
						objOs.writeObject(equObj);
					}if (action.equalsIgnoreCase("Find Message")) {
						String id = (String) objIs.readObject();
						messObj = findMessageById(id);
						objOs.writeObject(messObj);
					}
					if (action.equalsIgnoreCase("Find Transaction")) {
						String id = (String) objIs.readObject();
						tranObj = findTransactionById(id);
						objOs.writeObject(tranObj);
					}if (action.equalsIgnoreCase("Find Rental Request")) {
						String id = (String) objIs.readObject();
						rentalReqObj = findRentalReqById(id);
					}if (action.equals("Find Event")) {
						String id = (String) objIs.readObject();
						eventObj = findEventById(id);
						objOs.writeObject(eventObj);
					}if(action.equalsIgnoreCase("View Equipment Stock")) {
						equipmentStockObj = (List<EquipmentStock>) viewEquipmnentStock();
						objOs.writeObject(equipmentStockObj);						
					}if(action.equalsIgnoreCase("View Equipment By Category")) {
						equipmentStockObj = (List<EquipmentStock>) viewEquipmnentStock();
						objOs.writeObject(equipmentStockObj);
						
					}if(action.equalsIgnoreCase("create Receipt")) {
						
					}if(action.equalsIgnoreCase("View Equipment Stock")) {
						
					}if(action.equalsIgnoreCase("View Equipment Stock")) {
						
					}if(action.equalsIgnoreCase("Create Quotation")) {
						
					}if(action.equalsIgnoreCase("Create Invoice")) {
						
					}if(action.equalsIgnoreCase("View All Message")) {
						
					}if(action.equalsIgnoreCase("View List Of RentalRequest")) {
						
					}if(action.equalsIgnoreCase("Customer SignIn")) {
						
					}
					
				} catch (ClassCastException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				closeConnection();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public Queue<Equipment> viewEquipmentByCategory(String category) {
		Queue<Equipment> listEquipment = new LinkedList<>();

		String query = "SELECT * FROM equipment WHERE equipmentCategory =" + category;

		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {
				Equipment equipment = new Equipment();
				equipment.setEquipmentID(result.getString(1));
				equipment.setEquipmentName(result.getString(2));
				equipment.setEquipmentCategory(result.getString(3));
				equipment.setAvailabilityStatus(result.getBoolean(4));

				if (equipment.getEquipmentCategory().equals(category)) {
					listEquipment.add(equipment);
				}
			}

			if (!listEquipment.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Found equipment", "Equipment Status",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Not Found!", "Status", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// Handle SQLException appropriately
			e.printStackTrace();
		}

		return listEquipment;
	}

	// Employee methods
	public void createQuotation() {

	}

	public void creatReceipt() {

	}

	public void createInvoice() {

	}

	public void customerSignIn() {

	}

	public void respondToMessages() {

	}

	public void addScheduleEquipmentsForEvent(EventSchedule eventScheduleObj) {
		String sql = "INSERT INTO grizzlyequipment.eventschedule(eventScheduleID"+
				"eventID,employeeID,EquipmentID,eventDate)" 
				+ " VALUES('"
				+eventScheduleObj.getEventScheduleID() + "','" + eventScheduleObj.getEventID() + 
				"','" + eventScheduleObj.getEmployeeID() +"','"+eventScheduleObj.getEquipment() +
				"','"+eventScheduleObj.getEventDate() +"');";
		try {
			stmt = dbConn.createStatement();

			if ((stmt.executeUpdate(sql) == 1)) {
				objOs.writeObject(true);
			} else {
				objOs.writeObject(false);
			}
		} catch (IOException ioe) {
			// ideally you want to save errors to a log file
			ioe.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Message> viewAllMessages() {
		List<Message> listMessage = new ArrayList<>();
		String query = "SELECT * FROM message ";
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {
				Message messageObj = new Message();
				messageObj.setMessageID(result.getString(1));
				messageObj.setCustomerID(result.getString(2));
				messageObj.setEmployeeID(result.getString(3));
				messageObj.setMessageContent(result.getString(4));


				listMessage.add(messageObj);

			}

			if (!listMessage.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Found Rental Request Found", "Rental Request Status",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Not Found!", "Status", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// Handle SQLException appropriately
			e.printStackTrace();
		}
		return listMessage;

	}

	public List<EquipmentStock> viewEquipmnentStock() {
		List<EquipmentStock> listEquipmentStock = new ArrayList<>();
		String query = "SELECT * FROM equipmentstock ";
		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {
				EquipmentStock equipmentStock = new EquipmentStock();
				equipmentStock.setEquipmentStockID(result.getString(1));
				equipmentStock.setEquipmentID(result.getString(2));
				equipmentStock.setQuantityAvailable(result.getInt(3));
				equipmentStock.setEquipmentCategory(result.getString(4));


				listEquipmentStock.add(equipmentStock);

			}

			if (!listEquipmentStock.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Found Rental Request Found", "Rental Request Status",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Not Found!", "Status", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// Handle SQLException appropriately
			e.printStackTrace();
		}
		return listEquipmentStock;
	}

	public List<RentalRequest> viewListOfRentalRequest() {
		List<RentalRequest> listRentalRequest = new ArrayList<>();

		String query = "SELECT * FROM rentalrequest ";

		try {
			stmt = dbConn.createStatement();
			result = stmt.executeQuery(query);

			while (result.next()) {
				RentalRequest rentalRequest = new RentalRequest();
				rentalRequest.setRequestID(result.getString(1));
				rentalRequest.setCustomerID(result.getString(2));
				rentalRequest.setEquipmentID(result.getString(3));
				rentalRequest.setRentalDate(result.getString(4));
				rentalRequest.setQuotationCost(result.getDouble(5));
				rentalRequest.setRentalStatus(result.getBoolean(5));

				listRentalRequest.add(rentalRequest);

			}

			if (!listRentalRequest.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Found Rental Request Found", "Rental Request Status",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Not Found!", "Status", JOptionPane.ERROR_MESSAGE);
			}
		} catch (SQLException e) {
			// Handle SQLException appropriately
			e.printStackTrace();
		}

		return listRentalRequest;

	}
	// Employee methods
}
