package model;
import java.util.Random;

/**
 *  kitchenStaff: runnable to check the stocking level of sushi. Prepare more sushi dishes, if there is not enough.
 *  @author Yi Zheng
 */

public class KitchenStaff implements Runnable{
	


	private boolean employed;	// it would be false, if he or she is fired.
	private int id;				// a unique id for the employee.
	private String name; 
	private String description;
	private String status;
	private StockManagement stock;
	
	/**
	 * Constructor for a new staff.
	 * @param id  The id of the new staff.
	 * @param name  The name of him or her.
	 * @param description Something description information, like what is he or she good at.
	 * @param stock The stockmanagement class, where all stock information is held.
	 */
	
	public KitchenStaff(int id, String name, String description,StockManagement stock) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.employed = true;
		this.status = "Free";
		this.stock = stock;
	}
	
	@Override
	public void run() {
		while(employed) {
			for(SushiDish dish: stock.getDishList()){

				prepareDish(dish);
				

			}

		}
	}
	
	
	// The method called by kitchenStaff
	// Each kitchenStaff can prepare only one sushi dish each time. Assume none of them can prepare two dishes at the same time.
	public void prepareDish(SushiDish dish) {
		
		if(dish.getStock().getStockLevel() < dish.getStock().getRestockLevel()) {
			boolean sufficientIngre = true;
			for(Ingredient i: dish.getRecipe().keySet()) {
				if(i.getStock().getStockLevel() < i.getStock().getRestockLevel()) {  // Check the amount of ingredients
					sufficientIngre = false;
					break;
				}
			}
			
			if(sufficientIngre) {
				
				status = "Preparing dishes";
				
				System.out.println(this.name +" "+this.status);
				
				for(Ingredient i: dish.getRecipe().keySet()) {
					i.getStock().decrementStock(dish.getRecipe().get(i));
					Random rand = new Random();
					try {
						
						Thread.sleep((rand.nextInt(40)+20)*10); // Simulates the time to prepare
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
				}
				dish.getStock().incrementStock(1);
				System.out.println(dish.getName()+"-"+dish.getStock().getStockLevel());
				status = "Free";
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * Setters and Getters
	 */
	
	public boolean isEmployed() {
		return employed;
	}

	public void setEmployed(boolean employed) {
		this.employed = employed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
