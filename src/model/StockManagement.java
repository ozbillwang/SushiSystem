package model;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * @author Yi Zheng
 *
 * The class stores information about the storage of dishes and ingredients. 
 * It also has methods to operate on the stock/restocking level of dishes and ingredients.
 */

public class StockManagement {
	private ArrayList<Ingredient> ingreList = new ArrayList<Ingredient>();
	private ArrayList<SushiDish> dishList = new ArrayList<SushiDish>();
	
	// Stack implementation.
	// When a drone pick up an order to deliver, the order is poped out from the stack.
	private Stack<Order> orderList = new Stack<Order>();
	private ArrayList<Order> finishedOrder = new ArrayList<>();



	public void newOrder(Order order) {
		orderList.push(order);
	}
	
	public boolean newIngre(Ingredient ingre) {
		if(!ingreList.contains(ingre)) {
			ingreList.add(ingre);
			return true;
		}
		return false;
	}
	
	public boolean newDish(SushiDish dish) {
		if(!dishList.contains(dish)) {
			dishList.add(dish);
			return true;	
		}
		return false;
	}
	
	
	/*
	 * Setters and Getters.
	 */
	
	public ArrayList<Ingredient> getIngreList() {
		return ingreList;
	}

	public void setIngreList(ArrayList<Ingredient> ingreList) {
		this.ingreList = ingreList;
	}

	public ArrayList<SushiDish> getDishList() {
		return dishList;
	}

	public void setDishList(ArrayList<SushiDish> dishList) {
		this.dishList = dishList;
	}


	public Stack<Order> getOrderList() {
		return orderList;
	}



	public void setOrderList(Stack<Order> orders) {
		this.orderList = orders;
	}
	
	public ArrayList<Order> getFinishedOrder() {
		return finishedOrder;
	}

	public void setFinishedOrder(ArrayList<Order> finishedOrder) {
		this.finishedOrder = finishedOrder;
	}
}
