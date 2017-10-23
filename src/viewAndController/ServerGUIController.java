package viewAndController;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;

import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Drone;
import model.Ingredient;
import model.KitchenStaff;
import model.Order;
import model.Server;
import model.Stock;
import model.Supplier;
import model.SushiDish;
import javafx.scene.control.TextArea;

import javafx.scene.control.Tab;

import javafx.scene.control.CheckBox;

import javafx.scene.control.Spinner;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

/**
 * 
 * @author Yi Zheng
 *
 * A Controller for server application.
 */

public class ServerGUIController {
	
	//FXML fields
	@FXML
	private Tab dishTab;
	@FXML
	private TableView<ServerSushiData> dish;
	@FXML
	private TableColumn<ServerSushiData,String> dishName;
	@FXML
	private TableColumn<ServerSushiData,String> dishDescr;
	@FXML
	private TableColumn<ServerSushiData,Integer> dishPrice;
	@FXML
	private TableColumn<ServerSushiData,String> dishRecipe;
	@FXML
	private TableColumn<ServerSushiData,Integer> dishStock;
	@FXML
	private TableColumn<ServerSushiData,Integer> dishRestock;
	@FXML
	private TableView<ServerRecipeData> recipe;
	@FXML
	private TableColumn<ServerRecipeData,String> recipeIngre;
	@FXML
	private TableColumn<ServerRecipeData,String> recipeUnit;
	@FXML
	private Button addIngreRecipe;
	@FXML
	private Button addDish;
	@FXML
	private Button removeIngreRecipe;
	@FXML
	private Spinner recipeNumber;
	@FXML
	private TextArea dishDescrField;
	@FXML
	private Spinner dishPriceSpinner;
	@FXML
	private TextField dishNameField;
	@FXML
	private TableView<ServerRecipeData> recipeFinal;
	@FXML
	private TableColumn<ServerRecipeData,String> recipeIngreFInal;
	@FXML
	private TableColumn<ServerRecipeData,Integer> recipeIngreUsed;
	@FXML
	private Tab supplierTab;
	@FXML
	private TableView<ServerSupplierData> supplier;
	@FXML
	private TableColumn<ServerSupplierData,String> supplierName;
	@FXML
	private TableColumn<ServerSupplierData,String> supplierDistance;
	@FXML
	private TextField supplierNameField;
	@FXML
	private Spinner supplierDistanceSpinner;
	@FXML
	private Button addSupplier;
	@FXML
	private Tab ingredientTab;
	@FXML
	private TableView<ServerIngreData> ingredient;
	@FXML
	private TableColumn<ServerIngreData,String> ingredientName;
	@FXML
	private TableColumn<ServerIngreData,String> ingredientSupplier;
	@FXML
	private TableColumn<ServerIngreData,String> ingredientUnit;
	@FXML
	private TableColumn<ServerIngreData,String> ingredientStock;
	@FXML
	private TableColumn<ServerIngreData,String> ingredientRestock;
	@FXML
	private TextField ingredientNameField;
	@FXML
	private TextField ingredientUnitField;
	@FXML
	private Spinner ingredientStockSpinner;
	@FXML
	private Spinner ingrendientRestockSpinner;
	@FXML
	private Button addIngredient;
	@FXML
	private TableView<ServerSupplierData> ingredientSupplierTable;
	@FXML
	private TableColumn<ServerSupplierData,String> ingreSupplierColumn;
	@FXML
	private Tab staffTab;
	@FXML
	private TableView<ServerStaffData> staff;
	@FXML
	private TableColumn<ServerStaffData, String> staffID;
	@FXML
	private TableColumn<ServerStaffData, String> staffName;
	@FXML
	private TableColumn<ServerStaffData, String> staffStatus;
	@FXML
	private TableColumn<ServerStaffData, String> staffDescr;
	@FXML
	private TextArea staffDescrField;
	@FXML
	private TextField staffNameField;
	@FXML
	private Button addStaff;
	@FXML
	private Tab droneTab;
	@FXML
	private TableView<ServerDroneData> drone;
	@FXML
	private TableColumn<ServerDroneData, String> droneID;
	@FXML
	private TableColumn<ServerDroneData, String> droneStatus;
	@FXML
	private TableColumn<ServerDroneData, String> droneDescr;
	@FXML
	private TableColumn<ServerDroneData, String> droneDeliver;
	@FXML
	private TextArea droneDescrField;
	@FXML
	private Button addDrone;
	@FXML
	private CheckBox droneDeliverTick;
	@FXML
	private CheckBox droneBuyTick;
	@FXML
	private Tab orderTab;
	@FXML
	private TableView<ServerOrderData> order;
	@FXML
	private TableColumn<ServerOrderData,String> orderID;
	@FXML
	private TableColumn<ServerOrderData,String> orderCustomer;
	@FXML
	private TableColumn<ServerOrderData,String> orderContent;
	@FXML
	private TableColumn<ServerOrderData,String> orderStatus;
	@FXML
	private Tab customerTab;
	@FXML
	private TableView<ServerCustomerData> customer;
	@FXML
	private TableColumn<ServerCustomerData,String> customerID;
	@FXML
	private TableColumn<ServerCustomerData,String> customerName;
	@FXML
	private TableColumn<ServerCustomerData,String> customerEmail;
	@FXML
	private TableColumn<ServerCustomerData,String> customerPhone;
	@FXML
	private TableColumn<ServerCustomerData,String> customerAddress;
	@FXML
	private TableColumn<ServerCustomerData,String> customerLogin;

	
	//Data fields
	private Server server;
	private ObservableList<ServerOrderData> orders = FXCollections.observableArrayList();
	private ObservableList<ServerCustomerData> customers = FXCollections.observableArrayList();
	private ObservableList<ServerStaffData> staffList = FXCollections.observableArrayList();
	private ObservableList<ServerDroneData> drones = FXCollections.observableArrayList();
	private ObservableList<ServerSupplierData> suppliers = FXCollections.observableArrayList();
	private ObservableList<ServerSupplierData> ingreSuppliers = FXCollections.observableArrayList();
	private ObservableList<ServerIngreData> ingredients = FXCollections.observableArrayList();
	private ObservableList<ServerRecipeData> recipes = FXCollections.observableArrayList();
	private ObservableList<ServerRecipeData> recipesfinal = FXCollections.observableArrayList();
	private ObservableList<ServerSushiData> sushis = FXCollections.observableArrayList();
	
	private boolean deliverOrBuy = true; 		// true for delivering orders, and false for buying ingredients.

	
	
	public void setUp() {
		
		dishName.setCellValueFactory(new PropertyValueFactory<ServerSushiData,String>("name"));
		dishDescr.setCellValueFactory(new PropertyValueFactory<ServerSushiData,String>("description"));
		dishPrice.setCellValueFactory(new PropertyValueFactory<ServerSushiData,Integer>("price"));
		dishRecipe.setCellValueFactory(new PropertyValueFactory<ServerSushiData,String>("recipe"));
		dishStock.setCellValueFactory(new PropertyValueFactory<ServerSushiData,Integer>("stock"));
		dishRestock.setCellValueFactory(new PropertyValueFactory<ServerSushiData,Integer>("restock"));

		recipeIngre.setCellValueFactory(new PropertyValueFactory<ServerRecipeData,String>("ingreName"));
		recipeIngreFInal.setCellValueFactory(new PropertyValueFactory<ServerRecipeData,String>("ingreName"));
		recipeIngreUsed.setCellValueFactory(new PropertyValueFactory<ServerRecipeData,Integer>("amount"));
		recipeUnit.setCellValueFactory(new PropertyValueFactory<ServerRecipeData,String>("ingreUnit"));

		ingredientName.setCellValueFactory(new PropertyValueFactory<ServerIngreData,String>("name"));
		ingredientRestock.setCellValueFactory(new PropertyValueFactory<ServerIngreData,String>("restockLevel"));
		ingredientStock.setCellValueFactory(new PropertyValueFactory<ServerIngreData,String>("stockLevel"));
		ingredientSupplier.setCellValueFactory(new PropertyValueFactory<ServerIngreData,String>("supplier"));
		ingredientUnit.setCellValueFactory(new PropertyValueFactory<ServerIngreData,String>("unit"));
		
		updateIngredient();
		
		ingreSupplierColumn.setCellValueFactory(new PropertyValueFactory<ServerSupplierData,String>("name"));
		
		supplierName.setCellValueFactory(new PropertyValueFactory<ServerSupplierData,String>("name"));
		supplierDistance.setCellValueFactory(new PropertyValueFactory<ServerSupplierData,String>("distance"));

		updateSupplier();
		
		droneID.setCellValueFactory(new PropertyValueFactory<ServerDroneData,String>("ID"));
		droneStatus.setCellValueFactory(new PropertyValueFactory<ServerDroneData,String>("status"));
		droneDescr.setCellValueFactory(new PropertyValueFactory<ServerDroneData,String>("description"));
		droneDeliver.setCellValueFactory(new PropertyValueFactory<ServerDroneData,String>("deliverOrBuy"));

		updateDrone();
		
		staffID.setCellValueFactory(new PropertyValueFactory<ServerStaffData, String>("ID"));
		staffName.setCellValueFactory(new PropertyValueFactory<ServerStaffData, String>("name"));
		staffStatus.setCellValueFactory(new PropertyValueFactory<ServerStaffData, String>("status"));
		staffDescr.setCellValueFactory(new PropertyValueFactory<ServerStaffData, String>("description"));
		
		updateStaff();
		
		orders.add(new ServerOrderData("1", "2", "3", "4"));
		orderID.setCellValueFactory(new PropertyValueFactory<ServerOrderData,String>("ID"));
		orderCustomer.setCellValueFactory(new PropertyValueFactory<ServerOrderData, String>("username"));
		orderContent.setCellValueFactory(new PropertyValueFactory<ServerOrderData, String>("content"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<ServerOrderData, String>("status"));

		updateOrderPage();
		
		customerID.setCellValueFactory(new PropertyValueFactory<ServerCustomerData,String>("ID"));
		customerName.setCellValueFactory(new PropertyValueFactory<ServerCustomerData,String>("name"));
		customerEmail.setCellValueFactory(new PropertyValueFactory<ServerCustomerData,String>("email"));
		customerPhone.setCellValueFactory(new PropertyValueFactory<ServerCustomerData,String>("phone"));
		customerAddress.setCellValueFactory(new PropertyValueFactory<ServerCustomerData,String>("address"));
		customerLogin.setCellValueFactory(new PropertyValueFactory<ServerCustomerData,String>("loginInfo"));

		updateCustomers();
		
		droneDeliverTick.setSelected(true);
	}
	
	public void addSushi() {
		if(recipesfinal.size()==0) {
			AlertPopup alert = new AlertPopup(AlertType.INFORMATION, "No recipe", null, "You cannot make sushi using air. Add something to the recipe!");

		}
		else {
			Hashtable<Ingredient, Integer> recipeForNew = new Hashtable<>();
			
			for(Ingredient i: server.getStockManage().getIngreList()) {
				for(ServerRecipeData sid: recipesfinal) {
					if(sid.getIngreName().equals(i.getName())) {
						recipeForNew.put(i, sid.getAmount());
					}
				}
			}
			
			// Sample stock, stock level default to 0, restock level default to 100.
			Stock stockForNew = new Stock(0, 100);
			
			SushiDish newDish = new SushiDish(dishNameField.getText(), dishDescrField.getText(), (Integer)dishPriceSpinner.getValue(), recipeForNew, stockForNew);
			server.getStockManage().getDishList().add(newDish);
			
			updateSushi();
		}
		
	}

	public void updateSushi() {
		
		sushis.clear();
		if(server != null) {
			ArrayList<SushiDish> sushiList = server.getStockManage().getDishList();
			
			if(sushiList.size()!=0) {
				for(SushiDish sd: sushiList) {
					sushis.add(new ServerSushiData(sd.getName(), sd.getDescription(), sd.getPrice(), sd.getRecipe().toString(),
												sd.getStock().getStockLevel(), sd.getStock().getRestockLevel()));
				}
				
				dish.setItems(sushis);
			}
		}
		
	}
	
	public void addToRecipe() {
		if(recipe.getSelectionModel().getSelectedItem()==null) {
			AlertPopup alert = new AlertPopup(AlertType.INFORMATION, "No Ingredient picked", null, "Please pick an ingredient in the table.");

		}
		else {
			ServerRecipeData selected = recipe.getSelectionModel().getSelectedItem();
			ServerRecipeData existItem = null;
			boolean exist = false;
			for(ServerRecipeData srd: recipesfinal) {
				if(selected.getIngreName().equals(srd.getIngreName())) {
					exist = true;
					existItem = srd;
				}
			}
			
			Integer amount = (Integer) recipeNumber.getValue();
			
			if(exist) {
				int oldAmount = existItem.getAmount();
				int indexOfExist = recipesfinal.indexOf(existItem);
				recipesfinal.remove(indexOfExist);
				recipesfinal.add(new ServerRecipeData(selected.getIngreName(), selected.getIngreUnit(), amount+oldAmount));

			}
			else {
				recipesfinal.add(new ServerRecipeData(selected.getIngreName(), selected.getIngreUnit(), amount));
			}
			updateRecipeFinal();
		}
	}
	
	

	public void removeRecipeItem() {
		if(recipeFinal.getSelectionModel().getSelectedItem()!=null) {
			int index = recipeFinal.getSelectionModel().getSelectedIndex();
			recipesfinal.remove(index);
			updateRecipeFinal();
		}
	}
	
	public void updateRecipeFinal() {
		recipeFinal.setItems(recipesfinal);
	}
	
	public void addIngredient() {

		if(ingredientSupplierTable.getSelectionModel().getSelectedItem()==null) {
			AlertPopup alert = new AlertPopup(AlertType.INFORMATION, "No Supplier picked", null, "Please pick a supplier in the table.");
		}
		else {
			String supplierName = ingredientSupplierTable.getSelectionModel().getSelectedItem().getName();
			Supplier supplier = null;
			for(Supplier s: server.getSuppList()) {
				if(s.getName().equals(supplierName)) {
					supplier = s;
				}
			}

			Stock stock = new Stock((int)ingredientStockSpinner.getValue(), (int)ingrendientRestockSpinner.getValue());
			
			Ingredient newIngre = new Ingredient(ingredientNameField.getText(), ingredientUnitField.getText(),
													supplier, stock);
			server.getStockManage().getIngreList().add(newIngre);
			
			updateIngredient();
		}

	}
	
	public void updateIngredient() {
		ingredients.clear();
		recipes.clear();
		ArrayList<Ingredient> ingreList = server.getStockManage().getIngreList();
		for(Ingredient ingre: ingreList) {
			ingredients.add(new ServerIngreData(ingre.getName(), ingre.getSupplier().getName(), 
								ingre.getUnit(), Integer.toString(ingre.getStock().getStockLevel()), 
									Integer.toString(ingre.getStock().getRestockLevel())));
			recipes.add(new ServerRecipeData(ingre.getName(), ingre.getUnit(), 0));
		}
		ingredient.setItems(ingredients);
		recipe.setItems(recipes);
	}
	
	public void addSupplier() {
		
		String name = supplierNameField.getText();
		boolean exist = false;
		for(Supplier sp:server.getSuppList()) {
			if(sp.getName().equals(name)) {
				exist = true;
			}
		}
		if(!exist) {
			Supplier newSupplier = new Supplier(supplierNameField.getText(), (Integer)supplierDistanceSpinner.getValue());
			server.getSuppList().add(newSupplier);
			updateSupplier();
		}
		else {
			new AlertPopup(AlertType.INFORMATION, "The Supplier already exists", null, "Please pick another name");
			
		}
	}
	
	public void updateSupplier() {
		suppliers.clear();
		ingreSuppliers.clear();
		ArrayList<Supplier> supplierList = server.getSuppList();
		for(Supplier s: supplierList) {
			suppliers.add(new ServerSupplierData(s.getName(),Integer.toString(s.getDistance())));
			ingreSuppliers.add(new ServerSupplierData(s.getName(),Integer.toString(s.getDistance())));
		}
		supplier.setItems(suppliers);
		ingredientSupplierTable.setItems(ingreSuppliers);
		
	}
	
	public void droneTickDeliver() {
		
		// unselect the check box of "buy ingredients" 
		if(droneBuyTick.isSelected()) {
			droneBuyTick.setSelected(false);
		}
		
		deliverOrBuy = true;
		
		
	}
	
	public void droneTickBuy() {
		
		// unselect the check box of "deliver orders"
		if(droneDeliverTick.isSelected()) {
			droneDeliverTick.setSelected(false);
		}
		
		deliverOrBuy = false;
		
	}
	
	public void addDrone() {
		
		Drone newDrone = new Drone(server.getDroneList().size(), droneDescrField.getText(), server.getStockManage(), 10, deliverOrBuy);
		server.getDroneList().add(newDrone);
		Thread t = new Thread(newDrone);
		t.start();
		updateDrone();
	}
	
	
	public void updateDrone() {
		drones.clear();
		ArrayList<Drone> droneList = server.getDroneList();
		for(Drone d: droneList) {
			String useFor;
			if(d.isUseForDeliveringDishes()) {
				useFor = "Deliver Orders";
			}
			else {
				useFor = "Buy ingredients";
			}
			drones.add(new ServerDroneData(Integer.toString(d.getId()),d.getStatus(),d.getDescription(), useFor));
		}
		drone.setItems(drones);
	}
	
	public void addNewStaff() {
		KitchenStaff newStaff = new KitchenStaff(staffList.size(), staffNameField.getText(), staffDescrField.getText(), server.getStockManage());
		server.getStaffList().add(newStaff);
		Thread t = new Thread(newStaff);
		t.start();
		System.out.println(newStaff.getId());
		updateStaff();
	}
	
	public void updateStaff() {
		staffList.clear();
		ArrayList<KitchenStaff> staffArray = server.getStaffList();
		for(KitchenStaff ks: staffArray) {
			staffList.add(new ServerStaffData(Integer.toString(ks.getId()),ks.getName(),ks.getStatus(),ks.getDescription()));
			
		}
		staff.setItems(staffList);
	}
	
	public void updateCustomers() {
		customers.clear();
		List<Customer> customerList = new ArrayList<Customer>(server.getCustList().values());
		for(Customer c: customerList) {
			customers.add(new ServerCustomerData(Integer.toString(customerList.indexOf(c)), c.getName(), c.getEmail(), c.getPhoneNum(), c.getAddress(), c.getLoginInfo().toString()));
			
		}
		customer.setItems(customers);
	}

	public void updateOrderPage() {
		orders.clear();
		Stack<Order> orderList = server.getStockManage().getOrderList();
		ArrayList<Order> orderFinished = server.getStockManage().getFinishedOrder();
		for(Order o: orderList) {
			orders.add(new ServerOrderData(o.getID(), o.getStatus(), o.getContent().toString(), o.getCustomer().getLoginInfo().keySet().toString()));
		}
		for(Order o: orderFinished) {
			orders.add(new ServerOrderData(o.getID(), o.getStatus(), o.getContent().toString(), o.getCustomer().getLoginInfo().keySet().toString()));
		}
		order.setItems(orders);
	}
	
	/*
	 * Setters and getters.
	 */
	
	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
}
