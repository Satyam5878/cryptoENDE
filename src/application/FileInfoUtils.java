package application;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class FileInfoUtils {

	private static  String DEFAULT_PATH = "F:\\Projects\\Eclipse\\TestFile\\cryptoENDE";
	/*private static String ENCRYPTION = "Encrypted Files";
	private static String DECRYPTION = "Decrypted Files";
	private static String PASSWORD = "Password Files";*/
	private static String FILE_RECORD = "Record Files";
	
	public static ObservableList<FileInfo> getRecordFiles(){
		
		File recordDir = new File(DEFAULT_PATH+"\\"+FILE_RECORD);
		FileFilter fileFilter = new FileFilter(){
			@Override
			public boolean accept(File file) {
				System.out.println(checkExtension(file.toPath().toString()));
				return  checkExtension(file.toPath().toString()) ;
			}
		};
		File[] recordFileList = recordDir.listFiles(fileFilter);
		ArrayList<FileInfo> recordFileInfoList = new ArrayList<FileInfo>();
		for(File file : recordFileList){
			try {
				
				FileInputStream fileInput = new FileInputStream(file);
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);
				
				recordFileInfoList.add((FileInfo)objectInput.readObject());
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//Some code Goes Here 
				// Message Dialog
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return FXCollections.<FileInfo>observableArrayList(recordFileInfoList);
	}
	
	public static TableColumn<FileInfo ,String> getOriginalNameCol(){
		TableColumn<FileInfo,String> originalNameCol = new TableColumn<FileInfo, String>("Original\nName");
		originalNameCol.setMinWidth(50);
		originalNameCol.setCellValueFactory(new PropertyValueFactory<>("originalName"));
		return originalNameCol;
	}
	public static TableColumn<FileInfo,String> getNewName(){
		TableColumn<FileInfo,String> newNameCol = new TableColumn<FileInfo,String>("New\nName");
		newNameCol.setMinWidth(50);
		newNameCol.setCellValueFactory(new PropertyValueFactory<>("newName"));
		return newNameCol;
	}
	public static TableColumn<FileInfo,String> getLastModifiedDate(){
		TableColumn<FileInfo,String> lastModifiedDateCol = new TableColumn<FileInfo,String>("Modified\nOn");
		lastModifiedDateCol.setMinWidth(50);
		lastModifiedDateCol.setCellValueFactory(new PropertyValueFactory<>("lastModifiedDate"));
		return lastModifiedDateCol;
		
	}
	public static TableColumn<FileInfo,String> getFileFormat(){
		TableColumn<FileInfo,String> fileFormatCol = new TableColumn<FileInfo,String>("File\nFormat");
		fileFormatCol.setMinWidth(50);
		fileFormatCol.setCellValueFactory(new PropertyValueFactory<>("fileFormat"));
		return fileFormatCol;
	}
	public static TableColumn<FileInfo,String> getDestinationLocCol(){
		TableColumn<FileInfo,String> destinationLocCol = new TableColumn<FileInfo,String>("Destination\nLocation");
		destinationLocCol.setMinWidth(50);
		destinationLocCol.setCellValueFactory(new PropertyValueFactory<>("DestinationLoc"));
		return destinationLocCol;
	}
	public static TableColumn<FileInfo,String> getEncryptedOrDecrypted(){
		TableColumn<FileInfo,String> EncryptedOrDecryptedCol = new TableColumn<FileInfo,String>("Encrypted Or\nDecrypted");
		EncryptedOrDecryptedCol.setMinWidth(50);
		EncryptedOrDecryptedCol.setCellValueFactory(new PropertyValueFactory<>("EncryptedOrDecrypted"));
		return EncryptedOrDecryptedCol;
	}
	public static TableColumn<FileInfo,String> getAlgoUsed(){
		TableColumn<FileInfo,String> algoUsedCol = new TableColumn<FileInfo,String>("Algorithm\nUsed");
		algoUsedCol.setMinWidth(50);
		algoUsedCol.setCellValueFactory(new PropertyValueFactory<>("algoUsed"));
		return algoUsedCol;
	}
	public static boolean checkExtension(String fileName){
		String fileNameArray[] = fileName.split("\\\\");
		String fileNameWithExt = fileNameArray[fileNameArray.length-1];
		String format[] = fileNameWithExt.split("\\.");
		System.out.println(format.length);
		if(format.length<=1){
			return false;
		}
		return format[1].equalsIgnoreCase("rcFiles")?true:false;
	}
}
