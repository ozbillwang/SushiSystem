package model;
import java.util.HashMap;

/**
 * @author Yi Zheng
 * 
 *  Class to model an order from customer. 
 */
public class Order {
	
	private String ID;
	private Customer customer;
	private HashMap<SushiDish, Integer> content; // content of the order
	private String status="Pending";


	/**
	 *  A constructor for orders of Sushi dishes
	 *  @param customer Customer: which customer does this order belong to.
	 *  @param content HashMap<SushiDish, Integer>: the content of the order. 
	 *  
	 */
	
	public Order(Customer customer) {
		this.customer = customer;
		this.content = new HashMap<SushiDish, Integer>();
	}
	
	public void addDish(SushiDish dish, Integer amount) {
		if(content.containsKey(dish)) {			// there is already such a dish in the order.
			content.put(dish, content.get(dish) + amount);
		}
		else {
			content.put(dish, amount);
		}
	}
	
	public void removeDish(SushiDish dish, Integer amount) {
		if(content.containsKey(dish)) {
			if(content.get(dish) <= amount) {
				content.remove(dish);
			}
			else {
				content.put(dish, content.get(dish) - amount);
			}
		}
	}
	
	/*
	 * Setters and Getters
	 */
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public HashMap<SushiDish, Integer> getContent() {
		return content;
	}

	public void setContent(HashMap<SushiDish, Integer> content) {
		this.content = content;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
