package model;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

import javafx.scene.control.Alert.AlertType;
import viewAndController.AlertPopup;

/**
 * A server allows multiple users to connect to.
 * It also holds all inforation about the Sushi business, including: all dishes, ingredients, staff, drones, customers, orders .....
 * 
 * @author Yi Zheng
 *
 */

public class Server implements Runnable{
	
	// Socket information
	static final int PORT = 8189; // Port number field.
	static int currentConnection = 0; // Record how many connections the server holds for clients.
	int connectionID = 1; // Give every connection a ID.
	
	// Business information
	StockManagement stockManage = new StockManagement();		// This includes all possible dishes to sell, ingredients to use, orders to deliver.
	ArrayList<Client> clientList = new ArrayList<>(); 
	ArrayList<Drone> droneList = new ArrayList<>();
	ArrayList<Supplier> suppList = new ArrayList<>();
	ArrayList<KitchenStaff> staffList = new ArrayList<>();
	HashMap<String,Customer> custList = new HashMap<>();
		
	@Override
	public void run() {
		startServer();
	}
	
	public void startServer(){
		
		// Set up the server, and get it ready to accept connection from clients.
		
		try {
			// 

			
			// Set up the Server socket
			ServerSocket s = new ServerSocket(PORT);
			
			// Keep waiting for new Client to connect
			while(true) {
				Socket incomingClient = s.accept();
				
				// for test only
				System.out.println("New client connected");
				
				currentConnection++;
				connectionID++;
				Runnable handler = new MultiClientConnectionHandler(incomingClient);
				Thread t = new Thread(handler);
				t.start();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		// Prepare all business information.
		
	}
	
	/*
	 *  Class to handle the client input for one server socket connect
	*/
	
	class MultiClientConnectionHandler implements Runnable{
		
		private Socket incomingClient;
		InputStream inStream;
		OutputStream outStream;
		Scanner in;
		PrintWriter out;
		
		/**
		 * Constructs a handler
		 * @param incomingClient: the socket that client uses. 
		 * @param count: the counter for the handlers.
		 */
		public MultiClientConnectionHandler(Socket incomingClient) {
			this.incomingClient = incomingClient;
		}
		
		@Override
		public void run() {
			try {
				try {
					inStream = incomingClient.getInputStream();
					outStream = incomingClient.getOutputStream();
					
					in = new Scanner(inStream);
					out = new PrintWriter(outStream, true);
					
					//Test only
					out.println("Test test, can you hear me?");
					
					boolean done = false;
					while(!done && in.hasNextLine()) {
						
						// Request from user.
						String line = in.nextLine();
						System.out.println(line);
						processRequest(line);
						if(line.trim().equals("Exit")) {
							done = true;
						}
					}
					out.println("Connection ends");
					
				}
				finally {
					currentConnection--;
					connectionID--;
					incomingClient.close();
					
				}
			}catch (Exception e) {
				
			}
		}
		
		public void processRequest(String request) {
			System.out.println(request);
			String[] command = request.split(":");
			
			//Example format: "lg:username:password"
			switch(command[0]) {
			
			
			
			case "lg": // checkLogin()
					
					String[] loginInfo = Arrays.copyOfRange(command, 1, command.length);
					checkLogin(loginInfo);
					
					break;
			case "rg": // addLogin()
				
					//Sample format of command: rg:name:username:email:phone:address:password
				
					// The information used to register. ( cut "rg" off the array to avoid confusion)
					
					// Sample format of info:
					// 0    1        2     3     4       5       (array index)
					// name:username:email:phone:address:password
					String[] info = Arrays.copyOfRange(command, 1, command.length);
					registerNewUser(info);
					break;
			case "showmeMenu": showMenu();
								break;
			case "showmeOrder": showOrder(command[1]);
								break;
			case "ord": 	createOrder(command[1],command[2],command[3]);
								break;

			}
		}
		
		
		/*
		 *  Methods are used in above functions.
		 */
		
		public void createOrder(String username,String id,String content) {
			Stack<Order> orderList = stockManage.getOrderList();
			ArrayList<SushiDish> sushiList = stockManage.getDishList();
			HashMap<SushiDish,Integer> contentlist = new HashMap<>();
			Order order = new Order(custList.get(username));
			order.setID(id);
			String[] items = content.split(" ");
			for(String s: items) {
				String[] itemAndAmount = s.split("-");
				for(SushiDish sd: sushiList) {
					if (sd.getName().equals(itemAndAmount[0])){
						order.addDish(sd, Integer.parseInt(itemAndAmount[1]));
					}
				}
			}
			orderList.push(order);
			out.println("End");
			
		}
		
		public void showMenu() {
			ArrayList<SushiDish> menu = stockManage.getDishList(); 
			System.out.println(menu.get(0));
			if(!menu.isEmpty()){
				for(SushiDish dish: menu) {
					out.println("menu:"+dish.getName()+":"+dish.getDescription()+":"+dish.getPrice()+":"+dish.getStock().getStockLevel());
				}
			}
			out.println("End");
		}
		
		public void showOrder(String username) {
			Stack<Order> order = stockManage.getOrderList();
			ArrayList<Order> orderFinished = stockManage.getFinishedOrder();
			for(Order o: order) {
				if(o.getCustomer().getLoginInfo().keySet().contains(username)) {
					out.println("order:"+o.getID()+":"+o.getStatus()+":"+o.getContent().toString());
				}
			}
			for(Order o: orderFinished) {
				if(o.getCustomer().getLoginInfo().keySet().contains(username)) {
					out.println("order:"+o.getID()+":"+o.getStatus()+":"+o.getContent().toString());
				}
			}
			out.println("End");
		}
		
		public void registerNewUser(String[] info) {
			
			boolean exist = false; // boolean flag: true if the username provided is already registered.
								   // username is the primary key of each customer. Means one email for only one account.
			for(String username: custList.keySet()) {
				if(username.equals(info[1])) {
					exist = true;
				}
			}
			
			if(!exist) {
				HashMap<String, String> loginInfo = new HashMap<>();
				loginInfo.put(info[1], info[5]);
				Customer newCust = new Customer(info[0], info[4], info[2], info[3], loginInfo);
				custList.put(info[1],newCust);
				out.println("rgSuccess");
				out.println("End");
			}
			else {
				out.println("rgExist");
				out.println("End");
			}

			
			
		}
		
		public void checkLogin(String[] loginInfo) {
			boolean exist = false;
			Customer c = null;
			
			for(String username: custList.keySet()) {
				if(username.equals(loginInfo[0])){
					c = custList.get(username);
					exist = true;
				}
			}
			
			if(exist) {
				if(c.getLoginInfo().get(loginInfo[0]).equals(loginInfo[1])) {
					out.println("lgSuccess");
					out.println("End");
				}
				else {
					out.println("lgFailure");
					out.println("End");
				}
			}
			else {
				out.println("lgFailure");
				out.println("End");
			}
		}
		
	}

	
	/*
	 * Setters and Getters
	 */
	
	public static int getCurrentConnection() {
		return currentConnection;
	}

	public static void setCurrentConnection(int currentConnection) {
		Server.currentConnection = currentConnection;
	}

	public int getConnectionID() {
		return connectionID;
	}

	public void setConnectionID(int connectionID) {
		this.connectionID = connectionID;
	}

	public StockManagement getStockManage() {
		return stockManage;
	}

	public void setStockManage(StockManagement stockManage) {
		this.stockManage = stockManage;
	}

	public ArrayList<Client> getClientList() {
		return clientList;
	}

	public void setClientList(ArrayList<Client> clientList) {
		this.clientList = clientList;
	}

	public ArrayList<Drone> getDroneList() {
		return droneList;
	}

	public void setDroneList(ArrayList<Drone> droneList) {
		this.droneList = droneList;
	}

	public ArrayList<Supplier> getSuppList() {
		return suppList;
	}

	public void setSuppList(ArrayList<Supplier> suppList) {
		this.suppList = suppList;
	}

	public ArrayList<KitchenStaff> getStaffList() {
		return staffList;
	}

	public void setStaffList(ArrayList<KitchenStaff> staffList) {
		this.staffList = staffList;
	}

	public HashMap<String, Customer> getCustList() {
		return custList;
	}

	public void setCustList(HashMap<String, Customer> custList) {
		this.custList = custList;
	}

	public static int getPort() {
		return PORT;
	}

}

