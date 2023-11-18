package models.com;

import java.io.Serializable;
import java.util.Date;

public class Document implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String documentID;
    private String customerID; // or reference Customer directly
    private String employeeID; // or reference Employee directly
    private Date dateCreated;
    private double amount;
    // Other document attributes, getters, setters, and methods
    public Document(Document document) {
		super();
		this.documentID = document.documentID;
		this.customerID = document.customerID;
		this.employeeID = document.employeeID;
		this.dateCreated = document.dateCreated;
		this.amount = document.amount;
	}
    
	/**
	 * @param documentID
	 * @param customerID
	 * @param employeeID
	 * @param dateCreated
	 * @param amount
	 */
	public Document(String documentID, String customerID, String employeeID, Date dateCreated, double amount) {
		super();
		this.documentID = documentID;
		this.customerID = customerID;
		this.employeeID = employeeID;
		this.dateCreated = dateCreated;
		this.amount = amount;
	}
	
	public String getDocumentID() {
		return documentID;
	}

	public void setDocumentID(String documentID) {
		this.documentID = documentID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Document [documentID=" + documentID + ", customerID=" + customerID + ", employeeID=" + employeeID
				+ ", dateCreated=" + dateCreated + ", amount="+ "]";
	}
}
