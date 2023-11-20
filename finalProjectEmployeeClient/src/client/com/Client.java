package client.com;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import models.com.EquipmentStock;

import models.com.*;

public class Client {
	private static final Logger serverLogger = LogManager.getLogger(Client.class.getName());
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	private String action = "";
	private boolean isLogin;
	
	//need to log client side the minute you see this
	
	
	public boolean isLoginStatus() {
		return isLogin;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.isLogin = loginStatus;
	}
	public Client() {
		this.createConnection();
		this.configureStreams();
	}
	private void createConnection() {
		try {
			serverLogger.info("No errors");
			connectionSocket = new Socket("127.0.0.1", 8888);
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	private void configureStreams() {
		try {
			objIs = new ObjectInputStream(connectionSocket.getInputStream());
			objOs = new ObjectOutputStream(connectionSocket.getOutputStream());
		}catch(IOException ex) {
			ex.printStackTrace();
			serverLogger.error(ex);
		}
	}
	
	public void receiveResponse() {
		try {
			serverLogger.info("No errors");
			if(action.equalsIgnoreCase("Add Customer")) {
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true) {
					JOptionPane.showMessageDialog(null, "Account Created Successfully", 
							"Customer Account Creation Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Find Customer")) {
				Customer customer = new Customer();
				customer=null;
				customer =(Customer)objIs.readObject();
				if(customer==null) {
					JOptionPane.showMessageDialog(null, "No record found","status",JOptionPane.ERROR_MESSAGE);
				}
				System.out.println(customer);
			}
			
			if(action.equalsIgnoreCase("Add Equipment")) {
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true) {
					JOptionPane.showMessageDialog(null, "Account Created Successfully", 
							"Customer Account Creation Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Find Equipment")) {
				Equipment equipment = new Equipment();
				equipment =(Equipment)objIs.readObject();
				if(equipment==null) {
					JOptionPane.showMessageDialog(null, "No record found","status",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Add Message")) {
				Boolean flag = (Boolean) objIs.readObject();
				if(flag == true) {
					JOptionPane.showMessageDialog(null, "Message Sent Successfully", 
							"Message Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Find Message")) {
				Message message = new Message();
				message =(Message)objIs.readObject();
				if(message==null) {
					JOptionPane.showMessageDialog(null, "No record found","status",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Add Transaction")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag) {
					JOptionPane.showMessageDialog(null, "Message Sent Successfully", 
							"Message Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Find Transaction")) {
				Transaction transaction = new Transaction();
				transaction =(Transaction)objIs.readObject();
				if(transaction==null) {
					JOptionPane.showMessageDialog(null, "No record found","status",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Add Rental Request")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag) {
					JOptionPane.showMessageDialog(null, "Message Sent Successfully", 
							"Message Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Find Rental Request")) {
				RentalRequest rentalReq = new RentalRequest();
				rentalReq =(RentalRequest)objIs.readObject();
				if(rentalReq==null) {
					JOptionPane.showMessageDialog(null, "No record found","status",JOptionPane.ERROR_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Add Employee")) {
				Boolean flag = (Boolean) objIs.readObject();
				if (flag) {
					JOptionPane.showMessageDialog(null, "Message Sent Successfully", 
							"Message Status", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			if(action.equalsIgnoreCase("Find Employee")) {
				Employee employee = new Employee();
				employee = (Employee) objIs.readObject();
				if(employee==null) {
					JOptionPane.showMessageDialog(null, "Record could not be found", "Find Record Status", JOptionPane.ERROR_MESSAGE);
					return;
				}
				System.out.println(employee);
			}
			if(action.equalsIgnoreCase("Add Event")) {
				Boolean flag = (Boolean) objIs.readObject();
				isLogin = flag;
			}
			if(action.equalsIgnoreCase("Find Event")) {
				Boolean flag = (Boolean) objIs.readObject();
				isLogin = flag;
			}
			if(action.equalsIgnoreCase("Customer Login")) {
				Boolean flag = (Boolean) objIs.readObject();
				isLogin = flag;
			}
			if(action.equalsIgnoreCase("Employee Login")) {
				Boolean flag = (Boolean) objIs.readObject();
				isLogin = flag;
			}
		}catch(ClassCastException ex) {
			ex.printStackTrace();
			serverLogger.error(ex);
		}catch(ClassNotFoundException ex) {
			serverLogger.error(ex);
			ex.printStackTrace();
		}catch(IOException ex) {
			serverLogger.error(ex);
			ex.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			objOs.close();
			objIs.close();
			connectionSocket.close();
		}catch(IOException e) {
			e.printStackTrace();
			serverLogger.error(e);
		}
	}
	
	public void sendCustomer(Customer customerObj) {
		try {
			objOs.writeObject(customerObj);
		}catch(IOException e) {
			e.printStackTrace();
			serverLogger.error(e);
		}
	}
	
	public void sendEmployee(Equipment employeeObj) {
		try {
			objOs.writeObject(employeeObj);
		}catch(IOException e) {
			e.printStackTrace();
			serverLogger.error(e);
		}
	}
	
	public void sendEquipment(Equipment equipmentObj) {
		try {
			objOs.writeObject(equipmentObj);
		}catch(IOException e) {
			e.printStackTrace();
			serverLogger.error(e);
		}
	}
	
	public void sendMessage(Message message) {
		try {
			objOs.writeObject(message);
		}catch(IOException e) {
			e.printStackTrace();
			serverLogger.error(e);
		}
	}
	
	public void sendTransaction(Transaction transactionObj) {
		try {
			objOs.writeObject(transactionObj);
		}catch(IOException e) {
			e.printStackTrace();
			serverLogger.error(e);
		}
	}
	
	public void sendRentalRequest(RentalRequest rentalRequestObj) {
		try {
			objOs.writeObject(rentalRequestObj);
		}catch(IOException e) {
			e.printStackTrace();
			serverLogger.error(e);
		}
	}
	
	public void sendAction(String action) {
		this.action = action;
		try {
			objOs.writeObject(action);
		}catch(IOException e) {
			e.printStackTrace();
			serverLogger.error(e);
		}
	}
	
	public void sendCustomerId(String customerId) {
		try {
			objOs.writeObject(customerId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmployeeId(String employeeId) {
		try {
			objOs.writeObject(employeeId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEquipmentId(String equipmentId) {
		try {
			objOs.writeObject(equipmentId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessageId(int messageId) {
		try {
			objOs.writeObject(messageId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendTransactionId(int transactionId) {
		try {
			objOs.writeObject(transactionId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendRentalRequestId(int rentalRequestId) {
		try {
			objOs.writeObject(rentalRequestId);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEquipmentCategory(String equipmentCategory) {
		try {
			objOs.writeObject(equipmentCategory);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void sendLoginDetails(int id, String username, String password) {
		try {
            objOs.writeObject(id);
            objOs.writeObject(username);
            objOs.writeObject(password);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	// Winroy Jennings
	// For View Available Equipment
	public void viewAllAvailableEquipmentsResponse(String category) {
		try {
			objOs.writeObject(category);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Equipment> retrieveAllAvailableEquipmentsResponse() {
		List<Equipment> result = null;

		try {
			result = (List<Equipment>) objIs.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	// For Rent Available Equipment

	public boolean receiveEquipmentValidation() {
		boolean result = false;

		try {
			result = (Boolean) objIs.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	// Feedback - Change Equipment from available to rented status
	public boolean receiveEquipmentUpdate() {
		boolean result = false;

		try {
			result = (Boolean) objIs.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public double receiveEquipmentCost() {
		double result = 0.0;

		try {
			result = (double) objIs.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	// Transaction Section
	public boolean receiveTransactionStatus() {
		boolean result = false;

		try {
			result = (Boolean) objIs.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	@SuppressWarnings("unchecked")
	public List<EquipmentStock> viewEquipmentInventoryResponse() {
		List<EquipmentStock> result = null;

		try {
			result = (List<EquipmentStock>) objIs.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		new Client();
	}
}