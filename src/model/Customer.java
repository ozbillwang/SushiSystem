package model;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *  @author Yi Zheng
 *   
 *  Class stores basic information about the customers of the system.
 *  
 *  - Distance between the customer's address and the Sushi business address hasn't be modelled.
 *    A random time which stands for time of delivering is assigned to drones. 
 */

public class Customer {
	private String name;
	private String address;
	private String email;
	private String phoneNum;




	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	private ArrayList<Order> orderList;
	private HashMap<String,String> loginInfo;
	
	/**
	 *  A constructor for creating new customer.
	 *  @param name String:the name of customer
	 *  @param address String: where to deliver his or her orders
	 *  @param loginInfo HashMap<String, String>: consists of a username and password.
	 */
	public Customer(String name, String address, String email, String phoneNum,HashMap<String, String> loginInfo) {
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNum = phoneNum;
		this.orderList = new ArrayList<Order> ();
		this.loginInfo = loginInfo;
	}

	
	
	/*
	 *  Setters and Getters 
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<Order> orderList) {
		this.orderList = orderList;
	}

	public HashMap<String, String> getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(HashMap<String, String> loginInfo) {
		this.loginInfo = loginInfo;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	
	
	
}
