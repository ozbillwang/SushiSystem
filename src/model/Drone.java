package model;

import viewAndController.ServerGUIController;

/**
 * @author Yi Zheng
 *  Class to represent a drone which delivers dishes to customers or delivers ingredients from our supplier.
 */

public class Drone implements Runnable{
	
	private int id;
	private String description;  // Information like: Type, flight speed, etc.
	private StockManagement stock;
	private String status;
	private double flyingSpeed;
	private boolean useForDeliveringDishes;     // false: it is used to buy ingredients.
	private static final int CAPACITY = 20; 	// how many ingredients this drone can deliver each time.
												// assume each drone is capable of delivering all dishes in an order.
	
	
	public Drone(int id, String description, StockManagement stock, double flyingSpeed, boolean useForDeliveringDishes) {
		this.id = id;
		this.description = description;
		this.stock = stock;
		this.status = "Free";
		this.flyingSpeed = flyingSpeed;
		this.useForDeliveringDishes = useForDeliveringDishes;
	}
	
	@Override
	public void run() {
		while(useForDeliveringDishes){
			
			if(!stock.getOrderList().isEmpty()) {
				System.out.println("Start to Deliver--------------");
				
				Order orderToDeliver = stock.getOrderList().pop();
				boolean sufficientDish = false;
				
				// If there is not enough dishes to deliver, drone keeps checking the stock, until it find enough dishes.
				while(!sufficientDish){
					sufficientDish = true;
					for(SushiDish dish: orderToDeliver.getContent().keySet()) {
						if(orderToDeliver.getContent().get(dish) > dish.getStock().getStockLevel()) {
							sufficientDish = false;
						}
						try {
							 // Drone sleeps for a while to wait for more dishes being prepared.
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				
				// Drone finds enough dishes for the order, so it starts to deliver.
				for(SushiDish dish: orderToDeliver.getContent().keySet()) {
					deliverDishes(dish,orderToDeliver.getContent().get(dish));
				}
				orderToDeliver.setStatus("Delivered");
				stock.getFinishedOrder().add(orderToDeliver);
				System.out.println("Order Delivered!");  // test information.
				this.setStatus("Free");
			}

		}
		while(!useForDeliveringDishes) {
			for(Ingredient ingre: stock.getIngreList()) {
				this.setStatus("Buying Ingredients");
				buyIngredient(ingre, CAPACITY);
				this.setStatus("Free");
			}
		}
	}
	
	// Method called by drones that deliver dishes to our customers.
	public void deliverDishes(SushiDish dish, Integer amount) {
		if(amount <= dish.getStock().getStockLevel()) {
			status = "Delivering dishes";
			dish.getStock().decrementStock(amount);
			
		}
	}
	

	// Method called by drones that deliver ingredients from suppliers.
	public void buyIngredient(Ingredient ingre, Integer amount) {
		if(ingre.getStock().getStockLevel() < ingre.getStock().getRestockLevel()) {
			try {
				status = "Delivering ingredients";
				System.out.println(this.getId() + " " + this.getStatus());
				// Simulates the time for flying 
				Integer time = (int)((ingre.getSupplier().getDistance())*2/flyingSpeed)*1000;
				Thread.sleep(time);
				ingre.getStock().incrementStock(amount);;
				System.out.println(ingre.getName() + "-" + ingre.getStock().getStockLevel());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

	/*
	 * Setters and getters
	 * 
	 */
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StockManagement getStock() {
		return stock;
	}

	public void setStock(StockManagement stock) {
		this.stock = stock;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getFlyingSpeed() {
		return flyingSpeed;
	}

	public void setFlyingSpeed(double flyingSpeed) {
		this.flyingSpeed = flyingSpeed;
	}

	public boolean isUseForDeliveringDishes() {
		return useForDeliveringDishes;
	}

	public void setUseForDeliveringDishes(boolean useForDeliveringDishes) {
		this.useForDeliveringDishes = useForDeliveringDishes;
	} 
}
