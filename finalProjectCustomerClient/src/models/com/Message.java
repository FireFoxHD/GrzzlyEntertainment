package models.com;

import java.io.Serializable;

public class Message implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messageID;
    private String customerID; // or reference Customer or Employee directly
    private String employeeID; // or reference Customer or Employee directly
    private String messageContent;
    
    public Message(Message message) {
		super();
		this.messageID = message.messageID;
		this.customerID = message.customerID;
		this.employeeID =message.employeeID;
		this.messageContent = message.messageContent;
	}
	/**
	 * @param messageID
	 * @param customerID
	 * @param employeeID
	 * @param messageContent
	 */
	public Message(String messageID, String customerID, String employeeID, String messageContent) {
		super();
		this.messageID = messageID;
		this.customerID = customerID;
		this.employeeID = employeeID;
		this.messageContent = messageContent;
	}
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public String getMessageID() {
		return messageID;
	}
	public void setMessageID(String messageID) {
		this.messageID = messageID;
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
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	@Override
	public String toString() {
		return "Message [messageID=" + messageID + ", customerID=" + customerID + ", employeeID=" + employeeID
				+ ", messageContent=" + messageContent + ", getMessageID()=" + getMessageID() + ", getCustomerID()="
				+ getCustomerID() + ", getEmployeeID()=" + getEmployeeID() + ", getMessageContent()="
				+ getMessageContent() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

    // Other message attributes, getters, setters, and methods
}


