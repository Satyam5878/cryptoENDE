package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import javafx.beans.property.StringProperty;

public class ENDEEngine {
	
	private static String DEFAULT_PATH = "F:\\Projects\\Eclipse\\TestFile\\cryptoENDE";
	private static String ENCRYPTION = "Encrypted Files";
	private static String DECRYPTION = "Decrypted Files";
	private static String PASSWORD = "Password Files";
	private static String FILE_RECORD = "Record Files";
	
	public static void encrypt(String eFileAddrTxtString,
							   String eDestinationAddrTxtString,
							   String eNameAddrTxtString,
							   String ePasswordAddrTxtString,
							   String algoUsed,
							   StringProperty logTxtAreaString ,
							   StringProperty leftLblString,
							   StringProperty middleLblString,
							   StringProperty rightLblString
							   ){
		leftLblString.set("Encrypting...");
		Runnable r = new Runnable(){
			public void run() {
				try {	try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
						byte[] tmpPassword = ePasswordAddrTxtString.getBytes();
						if(ePasswordAddrTxtString.isEmpty()){
							SecureRandom sr = new SecureRandom();
							sr.nextBytes(tmpPassword);
						}
						MessageDigest md = MessageDigest.getInstance("SHA-1");
						tmpPassword = md.digest(tmpPassword);
						tmpPassword = Arrays.copyOf(tmpPassword,16);
					
						SecretKey Key = new SecretKeySpec(tmpPassword,"AES");
						
						Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
						cipher.init(Cipher.ENCRYPT_MODE,Key);
						
						
						FileInputStream fin = new FileInputStream(eFileAddrTxtString);
						byte[] plainFile = new byte[fin.available()];
							   fin.read(plainFile, 0, fin.available());
							   
					
						//Encrypting File
						byte[] cipherFile = cipher.doFinal(plainFile);
						
						//Saving Encrypted File.
						FileOutputStream fout = new FileOutputStream(eDestinationAddrTxtString+"\\"
																		+getFileNameWithoutExtension(eNameAddrTxtString)+".ENDE");
						fout.write(cipherFile);
					
					
					//Saving Encrypted Key.
					Files.createDirectories(Paths.get(DEFAULT_PATH+"\\"+PASSWORD +"\\"+eNameAddrTxtString));
					FileOutputStream fout1 = new FileOutputStream(DEFAULT_PATH+"\\"+PASSWORD +"\\"+eNameAddrTxtString+"\\"+"Key.VIRUS");
					fout1.write(tmpPassword);
		
					System.out.println("Done");
					
					fin.close();
					fout.close();
					fout1.close();
					System.out.println("Generating Record");
					
					generateRecord(eFileAddrTxtString,
							 	   eDestinationAddrTxtString,
							 	   eNameAddrTxtString,
							 	   ePasswordAddrTxtString,
							 	   algoUsed,
							 	   "Encrypted"
							 	   );
					System.out.println("Generated Record");
					System.out.println("Done Encryption.");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			
			}
		};
		leftLblString.set("");
		Thread eThread = new Thread(r);
		long l = System.currentTimeMillis();
		System.out.println(l);
		eThread.start();
		System.out.println(l-System.currentTimeMillis());	
	}

		public static void decrypt( String dFileAddrTxtString,
									String dDestinationAddrTxtString,
									String dNameAddrTxtString , 
									String dPasswordAddrTxtString){
		//test();
			
		Runnable r = new Runnable(){
			public void run() {
				try {	
					
					if(!Files.exists(Paths.get(dPasswordAddrTxtString+"\\Key.VIRUS"))){
						//TODO Some Action for handling it
						System.out.println("Key is Courrupted");
						return;
					}
					FileInputStream fin = new FileInputStream(dPasswordAddrTxtString+"\\Key.VIRUS");
					byte[] tmpPassword = new byte[fin.available()];
					fin.read(tmpPassword, 0, fin.available());
					
							/*if(ePasswordAddrTxtString.isEmpty()){
								SecureRandom sr = new SecureRandom();
								sr.nextBytes(tmpPassword);
							}*/
							MessageDigest md = MessageDigest.getInstance("SHA-1");
							tmpPassword = md.digest(tmpPassword);
							tmpPassword = Arrays.copyOf(tmpPassword,16);
						
							SecretKey Key = new SecretKeySpec(tmpPassword,"AES");
							
							Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
							cipher.init(Cipher.DECRYPT_MODE,Key);
							
							
							FileInputStream fin2 = new FileInputStream(dFileAddrTxtString);
							byte[] cipherFile = new byte[fin.available()];
								   fin2.read(cipherFile, 0, fin.available());
							byte[] plainFile = cipher.doFinal(cipherFile);
						
							//Saving Encrypted File.
							FileOutputStream fout = new FileOutputStream(dDestinationAddrTxtString+"\\"
																			+getFileNameWithoutExtension(dNameAddrTxtString)+".DECRYPED");
							fout.write(plainFile);
						
						
						//Saving Encrypted Key.
					
			
						System.out.println("Done");
						
						fin.close();
						fout.close();
						fin2.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidKeyException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalBlockSizeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (BadPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}			
				}
			};
			
			Thread eThread = new Thread(r);
			long l = System.currentTimeMillis();
			System.out.println(l);
			eThread.start();
			System.out.println(l-System.currentTimeMillis());
			
			
			
			

			
		generateRecord(dFileAddrTxtString,
				 	   dDestinationAddrTxtString,
				 	   dNameAddrTxtString,
				 	   dPasswordAddrTxtString,
				 	   "",
				 	   "Decrypted");
		System.out.println("Done Decryption.");
	}
	private static void generateRecord(String FileAddrTxtString,
									   String DestinationAddrTxtString,
									   String NameAddrTxtString, 
									   String PasswordAddrTxtString,
									   String algoUsed,
									   String EncryptedOrDecrypted) {
		DateFormat dateFormat = DateFormat.getDateInstance(); 
		if(algoUsed.isEmpty()){
			System.out.println(PasswordAddrTxtString);
			if(Files.exists(Paths.get(PasswordAddrTxtString+"\\Key.VIRUS"))){
				try {
					FileInputStream fin = new FileInputStream(PasswordAddrTxtString+"\\Key.VIRUS");
					
					byte algoUsedByte[] =  new byte[fin.available()];
					fin.read(algoUsedByte, 0, fin.available());
					algoUsed = new String(algoUsedByte);
					/*
					ObjectInputStream oin = new ObjectInputStream(fin);
					
			//TamJham for getting AlgoUsed For Decryption		
					algoUsed  = (String)oin.readObject().toString();
					oin.close();*/
					fin.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Todo handling of key Not found
				System.out.println("Exist");
			}
			else{
				System.out.println("Not Exist");
			}
		}
		else {
			System.out.println("Generating Record for File info");
			FileInfo fileInfo = new FileInfo(ENDEEngine.getNameOfFile(FileAddrTxtString),
									NameAddrTxtString,
									dateFormat.format(new Date()),
		 /*file Format to inserted*/EncryptedOrDecrypted.equals("Decrypted")?"decrypt":getFileFormat(FileAddrTxtString),
									DestinationAddrTxtString, 
									EncryptedOrDecrypted, 
									algoUsed);
			System.out.println("Generated Record for File info");
			System.out.println("1 "+NameAddrTxtString);
			System.out.println("Serializing Record for File info");
			try {
				FileOutputStream fout = new FileOutputStream(
						DEFAULT_PATH + "\\" + FILE_RECORD + "\\" + getFileNameWithoutExtension(NameAddrTxtString)+".rcFiles"/*NameAddrTxtString*/);
				ObjectOutputStream oOut = new ObjectOutputStream(fout);
				oOut.writeObject(fileInfo);
				oOut.flush();
				oOut.close();
				fout.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			System.out.println("Serialized Record for File info");
		}
	}
	
	public static String getNameOfFile(String fileAddr){
		String fileNameArray[] = fileAddr.split("\\\\");
		String fileName = fileNameArray[fileNameArray.length-1];
		return fileName;
		
	}
	public static String getFileFormat(String originalName){
		String fileNameArray[] = originalName.split("\\\\");
		String fileName = fileNameArray[fileNameArray.length-1];
		System.out.println(fileName);
		String format[] = fileName.split("\\"
				+ ".");
		System.out.println(format.length);
		System.out.println(format[format.length-1]);
		return format[format.length-1];
	}
	public static String getFileNameWithoutExtension(String name){
		String fileNameArray[] = name.split("\\\\");
		String fileName = fileNameArray[fileNameArray.length-1];
		String format[] = fileName.split("\\.");
		return format[0];
	}

}
