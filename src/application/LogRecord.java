package application;

import java.io.Serializable;

public class LogRecord implements Serializable{
	private String date;
	private String action;
	private String status;
	private String fileName;
	private String destination;
	private String newName;
	
	public LogRecord(String date,
					 String action,
	                 String status,
	                 String fileName,
	                 String destination,
	                 String newName){
		
		this.date = date;
		this.action = action;
		this.status = status;
		this.fileName = fileName;
		this.destination = destination;
		this.newName = newName;
		
	}
	public void setDate(String date) {
		this.date = date;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("Date : "+this.date
				 +"\nAction : "+this.action+" "+this.status
				 +"\nFile Name : "+this.fileName +" "+(this.action.equals("Encryption")?"Encrypted":"Decrypted")
				 +"\nDestination : "+this.destination
				 +"\nNew Name : "+this.newName
				 +"------------------------");
		return sb.toString();
		
	}
}
