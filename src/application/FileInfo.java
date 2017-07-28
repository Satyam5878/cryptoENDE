package application;

import java.io.Serializable;

public class FileInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String originalName;
	private String newName;
	private String lastModifiedDate;
	private String fileFormat;
	private String DestinationLoc;
	private String EncryptedOrDecrypted;
	private String algoUsed;

	public FileInfo(String originalName,
					String newName,
					String lastModifiedDate,
					String fileFormat,
					String DestinationLoc,
					String EncryptedOrDecrypted,
					String algoUsed){
		
		this.originalName = originalName;
		this.newName = newName;
		this.lastModifiedDate = lastModifiedDate;
		this.fileFormat = fileFormat;
		this.DestinationLoc = DestinationLoc;
		this.EncryptedOrDecrypted = EncryptedOrDecrypted;
		this.algoUsed = algoUsed;
		
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginName(String originalName) {
		this.originalName = originalName;
	}
	public String getNewName() {
		return newName;
	}
	public void setNewName(String newName) {
		this.newName = newName;
	}
	public String getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(String lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getFileFormat() {
		return fileFormat;
	}
	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}
	public String getDestinationLoc() {
		return DestinationLoc;
	}
	public void setDestinationLoc(String destinationLoc) {
		DestinationLoc = destinationLoc;
	}
	public String getEncryptedOrDecrypted() {
		return EncryptedOrDecrypted;
	}
	public void setEncryptedOrDecrypted(String encryptedOrDecrypted) {
		EncryptedOrDecrypted = encryptedOrDecrypted;
	}
	public String getAlgoUsed() {
		return algoUsed;
	}
	public void setAlgoUsed(String algoUsed) {
		this.algoUsed = algoUsed;
	}
}
