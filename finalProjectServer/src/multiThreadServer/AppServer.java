package multiThreadServer;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JOptionPane;

public class AppServer {
    private static final int PORT = 8888;
    private static ExecutorService pool = Executors.newFixedThreadPool(10);
    private ServerSocket serverSocket;
    private static Connection dbConn;

    public AppServer() {
        createConnection();
    }

    private void createConnection() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server started...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getDatabaseConnection() {
        if (getDbConn() == null) {
            String url = "jdbc:mysql://localhost:3306/dblab";
            try {
                setDbConn(DriverManager.getConnection(url, "root", ""));
                JOptionPane.showMessageDialog(null, "DB connected");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "DB not connected");
            }
        }
        return getDbConn();
    }
    private void waitForRequest() {
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Create a new thread for each client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                pool.execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getDbConn() {
		return dbConn;
	}

	public static void setDbConn(Connection dbConn) {
		AppServer.dbConn = dbConn;
	}

	public static void main(String[] args) {
        AppServer server = new AppServer();
        server.waitForRequest();
    }
}