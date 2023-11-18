package multiThreadServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JOptionPane;

import models.com.Customer;
import models.com.Employee;
import models.com.Equipment;
import models.com.Message;
import models.com.RentalRequest;
import models.com.Transaction;

public class ThreadedClass implements Runnable {
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private Statement stmt;
	private ResultSet result = null;
	private Socket connectionSocket;
	private String action;
	Customer custObj;
	Employee empObj;
	Equipment equObj;
	Message messObj;
	Transaction tranObj;
	RentalRequest rentalReqObj;

	
	public ThreadedClass(Socket connectionSocket) {
		this.connectionSocket = connectionSocket;
		this.configureStreams();
	}
	
	private void configureStreams() {
		try {
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			configureStreams();
			// Process the request or handle incoming data here
			// Example: Determine request type and call appropriate methods
			// based on the received request.
			action = (String) objIs.readObject();
			if (action.equalsIgnoreCase("Add Employee")) {
				empObj = (Employee) objIs.readObject();
				this.addEmployeeToFile(empObj);
				objOs.writeObject(true);
			} else if (action.equalsIgnoreCase("Add Customer")) {
				custObj = (Customer) objIs.readObject();
				this.addCustomerToFile(custObj);
				objOs.writeObject(true);
			} else if (action.equalsIgnoreCase("Add Equipment")) {
				equObj = (Equipment) objIs.readObject();
				this.addEquipmentToFile(equObj);
				objOs.writeObject(true);
			} else if (action.equalsIgnoreCase("Add Message")) {
				messObj = (Message) objIs.readObject();
				this.addMessage(messObj);
				objOs.writeObject(true);
			} else if (action.equalsIgnoreCase("Add Transaction")) {
				tranObj = (Transaction) objIs.readObject();
				this.addTransactionToFile(tranObj);
				objOs.writeObject(true);
			} else if (action.equalsIgnoreCase("Add Rental Request")) {
				rentalReqObj = (RentalRequest) objIs.readObject();
				this.addRentalRequestToFile(rentalReqObj);
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
			} else if (action.equalsIgnoreCase("Find Equipment")) {
				String id = (String) objIs.readObject();
				equObj = findEquipmentById(id);
				objOs.writeObject(equObj);
			} else if (action.equalsIgnoreCase("Find Message")) {
				String id = (String) objIs.readObject();
				messObj = findMessageById(id);
				objOs.writeObject(messObj);
			} else if (action.equalsIgnoreCase("Find Transaction")) {
				String id = (String) objIs.readObject();
				tranObj = findTransactionById(id);
				objOs.writeObject(tranObj);
			} else if (action.equalsIgnoreCase("Find Rental Request")) {
				String id = (String) objIs.readObject();
				rentalReqObj = findRentalReqById(id);
				objOs.writeObject(rentalReqObj);
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

	closeConnection();

	}

	public Queue<Equipment> viewEquipmentByCategory(String category) {
		Queue<Equipment> listEquipment = new LinkedList<>();

		String query = "SELECT * FROM equipment WHERE equipmentCategory =" + category;

		try {
			stmt = AppServer.getDatabaseConnection().createStatement();
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

	void addCustomerToFile(Customer customer) {
		String sql = "INSERT INTO customer(customerID,username,password,accountBalance)" + " VALUES('"
				+ customer.getCustomerID() + "','" + customer.getUsername() + "','" + customer.getPassword() + "','"
				+ customer.getAccountBalance() + "');";
		try {
			stmt = AppServer.getDatabaseConnection().createStatement();

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

	private Customer findCustomerById(String id) {
		Customer custObj = new Customer();

		String query = "SELECT * FROM grizzlyequipment.customer WHERE customerID =" + id;
		try {
			stmt = AppServer.getDatabaseConnection().createStatement();
			result = stmt.executeQuery(query);
			if (result.next()) {
				custObj.setCustomerID(result.getString(1));
				custObj.setUsername(result.getString(2));
				;
				custObj.setPassword(result.getString(3));

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
			stmt = AppServer.getDatabaseConnection().createStatement();

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
			stmt = AppServer.getDatabaseConnection().createStatement();
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

	// add cost per day
	void addEquipmentToFile(Equipment equipment) {
		String sql = "INSERT INTO grizzlyequipment.equipment(equipmentID,equipmentName,equipmentCategory,isAvailable)"
				+ " VALUES('" + equipment.getEquipmentID() + "','" + equipment.getEquipmentName() + "','"
				+ equipment.getEquipmentCategory() + "','" + equipment.isAvailabilityStatus() + "');";
		try {
			stmt = AppServer.getDatabaseConnection().createStatement();
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

	private Equipment findEquipmentById(String id) {
		Equipment equObj = new Equipment();
		String query = "SELECT * FROM grizzlyequipment.equipment WHERE equipmentID =" + id;
		try {
			stmt = AppServer.getDatabaseConnection().createStatement();
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
			stmt = AppServer.getDatabaseConnection().createStatement();

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
			stmt = AppServer.getDatabaseConnection().createStatement();
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

	public void addTransactionToFile(Transaction transaction) {
		String sql = "INSERT INTO grizzlyequipment.transaction(transactionId,customerID,requestID,transactionDate,amountPaid)"
				+ " VALUES('" + transaction.getTransactionID() + "','" + transaction.getCustomerID() + "','"
				+ transaction.getRequestID() + "','" + transaction.getTransactionDate() + "','"
				+ transaction.getAmountPaid() + "');";
		try {
			stmt = AppServer.getDatabaseConnection().createStatement();

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

	private Transaction findTransactionById(String id) {
		Transaction tranObj = new Transaction();
		String query = "SELECT * FROM grizzlyequipment.transaction WHERE transactionID =" + id;
		try {
			stmt = AppServer.getDatabaseConnection().createStatement();
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
			stmt = AppServer.getDatabaseConnection().createStatement();

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
			stmt = AppServer.getDatabaseConnection().createStatement();
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

}
