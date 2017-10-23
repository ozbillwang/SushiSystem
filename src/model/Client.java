package model;

/**
 * @author Yi Zheng
 * 
 * This class is the client socket and it does some message processing work.
 * Also it holds some local data (e.g. values in the tables) for the client. 
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import viewAndController.AlertPopup;
import viewAndController.ClientOrderData;
import viewAndController.ClientSushiDishesData;

public class Client {
	
	private boolean loggedIN; // boolean flag to mark the client has logged in or out. Some functions are only accessible when logged in.
	private final static int  PORT = 8189;	// Port number must be paired to which the server is using.
	private Socket client;
	private InputStream in;
	private OutputStream out;
	private Scanner sc;
	private PrintWriter pw;
	
	private ObservableList<ClientSushiDishesData> sushiData = FXCollections.observableArrayList();
	private ObservableList<ClientOrderData> orderData = FXCollections.observableArrayList();

	private String username; // to record who is using the client.
	
	/**
	 *  Constructor:
	 *  When the class is constructed, the socket communication starts.
	 */
	public Client() {
		try {
			client = new Socket("localhost",PORT);
			
			try {
				in = client.getInputStream();
				out = client.getOutputStream();
				
				sc = new Scanner(in);
				pw = new PrintWriter(out, true);
				
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
		} catch (IOException e) {
			// Show an error dialog window and then exit.
			
			AlertPopup alert = new AlertPopup(AlertType.ERROR, "Server Offline!", "Cannot connect to the server:",
					"Currently unable to connect to the server. \nPlease contact the Sushi restaurant.");
			e.printStackTrace();

			System.exit(0);
		}
		
		
	}

	
	/*
	 * 	 Useful methods.
	 */
	
	public void sendRequest(String request) {
		
		pw.println(request);
	}
	
	// Check messages from server periodically or check is there an acknowledgement from server.
	public void checkMessage() {
		boolean done = false;
		System.out.println("StartCheck");
		while(!done && sc.hasNextLine()) {
			String message = sc.nextLine();
			System.out.println(message);
			processMessage(message);
			if(message.equals("End")) {
				done = true;
			}
		}
	}
	
	public void processMessage(String message) {
		String[] content = message.split(":");
		switch(content[0]){
			case "rgExist":
						AlertPopup alert = new AlertPopup(AlertType.INFORMATION, "Account exists",
									null, "This user name has been registered, plesae use another one.");
						break;
			case "rgSuccess": 
						AlertPopup alert2 = new AlertPopup(AlertType.INFORMATION, "Successful", null, "You have been registered!!!");
						break;
			case "lgFailure":
						AlertPopup alert3 = new AlertPopup(AlertType.INFORMATION, "Login Failure", null, "Please check the username or password again.");
						break;
			case "lgSuccess":
						sushiData.add(new ClientSushiDishesData("I am a sushi sample!", "yummy,yummy", "Free", "Unlimited"));
						
						InitClientGUI i = new InitClientGUI(this);
						break;
			case "menu":
						sushiData.add(new ClientSushiDishesData(content[1],content[2],content[3],content[4]));
						break;
			case "order":
						orderData.add(new ClientOrderData(content[1],content[2],content[3]));
						break;
		}
	}
	
	public void exit() {
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 *  Getters and setters
	 */
	
	
	
	public ObservableList<ClientSushiDishesData> getSushiData() {
		return sushiData;
	}


	public void setSushiData(ObservableList<ClientSushiDishesData> sushiData) {
		this.sushiData = sushiData;
	}
	

	public ObservableList<ClientOrderData> getOrderData() {
		return orderData;
	}


	public void setOrderData(ObservableList<ClientOrderData> orderData) {
		this.orderData = orderData;
	}

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}

}
