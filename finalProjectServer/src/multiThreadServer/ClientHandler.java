package multiThreadServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import models.com.Customer;
import models.com.Employee;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private ObjectOutputStream objOs;
    private ObjectInputStream objIs;
    private AppServer appServer;

    public ClientHandler(Socket clientSocket, AppServer appServer) {
        this.clientSocket = clientSocket;
        this.appServer = appServer;

        try {
            objOs = new ObjectOutputStream(clientSocket.getOutputStream());
            objIs = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String action = (String) objIs.readObject();
            
            // Handle actions based on received requests
            switch (action.toLowerCase()) {
                case "add employee":
                    // Handle adding an employee
                    Employee emp = (Employee) objIs.readObject();
                    appServer.addEmployeeToFile(emp);
                    objOs.writeObject(true); // Send confirmation
                    break;
                case "add customer":
                    // Handle adding a customer
                    Customer cust = (Customer) objIs.readObject();
                    appServer.addCustomerToFile(cust);
                    objOs.writeObject(true); // Send confirmation
                    break;
                // Add cases for other actions...
                default:
                    // Handle unrecognized action
                    objOs.writeObject(false); // Send failure confirmation
                    break;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                objOs.close();
                objIs.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}