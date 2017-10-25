This is my personal project -- Sushi Restaurant System

1. Introduction: 

This project is a client-server model based project. It is complete, but I am still wokring on some 
improvements(list in the bottom).
There are some System.out.println commands in the code. I leave them there as a kind of log, so that
what happens in the server/client can be seen.

1.1 The goal of this project:

      - Implement an inventory and delivery management system for the sushi business. The system should 
        keep track of the stocks of sushi dishes and ingredients that are used to prepare sushi dishes. 
        Also the system should manages kitchen staff and drones 
        which prepare sushi dishes and deliver orders/buy ingredients respectively for the business. 
        
      - Also a client application that customers can use to view the range of sushi on offer, to place 
        orders and to view the progress of their order.
        
1.2 There are two applications implemented: one for client and the other for server. 
    
       Both of them are modelled using MVC model.
       There are some ClientxxxxData.java and ServerxxxxData.java files, I decide to include them in the
       model, because they are only used for the tableviews in the GUI. 
   
1.3  Some features: 
        - A client-server model application that uses sockets to enable communications between a single 
          server with multiple clients. 

        - Multithreading is used to allow multiple kitchen staff (preparing Sushi dishes) and drones
          (delivering order to customer or buying ingredients from supplier) work concurrently. 
       
       - The GUI was built by JavaFX. 
          Specific operations might need to be taken to run the program 
          see:https://docs.oracle.com/javase/8/javafx/get-started-tutorial/jfx-overview.htm

1.4  More details will be introduced below.
    ( It would be much eaiser, if you run the two applications to see how it works)
     
        To run the applications, please compile InitLoginGUI.java and InitServerGUI.java. Then run 
        InitServerGUI first, because the client application need to establish a connection to the 
        server first. 

2. The client application

      2.1 Java files includes: 
        
       
         - InitLoginGUI.java
         
               The class which contains a main methods to invoke the display of Login GUI. 
               A client must be logged in to gain access to the Client application (GUI).
               
         - InitClientGUI.java
         
               Once the login information is confirmed by the server, an instance of this class will
               be created to display the client application to the user.
               
         Model: -------------------------------------------------------------------------------------------------------------
         - Client.java
         
               This is where the client socket lives. It communicates to the server. 
               It also includes methods to send messages to the server and process messages from the 
               server. 
               
               It obtains information of the Order and SushiDish objects which lives in the server via 
               Socket. And the information is uesd to construct corresponding models for the tableviews
               in the GUI.
               
               It also sends messages to the server. For example, a message contains the order 
               information will be sent to the server, once the user creates one. 
               
         View: ------------------------------------------------------------------------------------------------------------------
         - AlertPopup.java
         
               This class is created to reduce the redundancy of code.
               I use it to give the user prompt of errros and information in several classes. 
               
         - ClientCartData.java
         - ClientOrderData.java
         - ClientSushiDishesData.java
         
               Three simple classes to represent data models for JavaFX tableviews. 
               There are only setters and getters in the class. Nothing really interesting. 
                        
         These are fxml files for the GUI: 
         - ClentGUI.fxml
         - LoginGUI.fxml
         - RegisterGUI.fxml
         
         Controller:  -----------------------------------------------------------------------------------------------------------   
         - ClientGUIController.java
         
               It is the controller of the Client GUI. 
               It has a reference of the client object ( client.java), so that the user can 
               communication with the server via the GUI.
               
         - LoginGUIController.java
         
               It controls the Login GUI. 
               
               Once a user tries to log in, a message is sent to the server to ask for an
               acknowledgement.
               If the login inforamtion is correct, the Client GUI will be displayed, 
               otherwise a alert window will pop out.
        
               It includes the register window codes that allows the user to register a new
               account ( messages will be sent to the server by client socket)
               
         - RegisterGUIController.java
         
               Controller of the Register GUI. 
               
               New account information will be sent to the server via client socket, once the 
               register button is clicked.


3. The server application: 
      3.1 files includes: 
      
         - InitServerGUI.java
               
               It initializes the server and the server GUI.
         
         Model: -------------------------------------------------------------------------------------------------------------
         - Server.java
         
               It is the server socket. And it includes methods to send messages to the client(s)
               and process the requests from clients.It also holds lists of clients, customers,
               drones, suppliers and kitchen staff. Also there is a stock managemetn object 
               in the class. It works like a database, I will implement a database via 
               JDBC in the future. 
               
        - StockManagement.java
         
               It manages lists of ingredients and sushi dishes that the restaurant has, 
               also the orders and finished orders.

               Order list is implemented by a stack. Each time, drone picks an order to deliver, 
               the order will be poped out from the stack. By doing this, a concurrent problem,
               which is two drones (in different threads) pick the same order to deliver,
               can be avoided.

               After the order has been delivered, it will be added to the finished order 
               ArrayList for the record. 
               
         - Customer.java
         
               Holds basic information about the customer, 
               e.g. name, phone number, login information
               
         - Ingredient.java
            
               holds inforamtion about the ingredient, 
               e.g. name, stock, supplier
         
         - KitchenStaff.java
               
               holds information about the staff, 
               including an ID, description of him or her, status ( Free or preparing dishes) 
               
               It implements Runnable, so that it can run in different threads to work concurrently. 
               Once an instance of this class is created and run in a thread, it starts to check 
               the stock level of each type of sushi dish. If the stock level fails below the 
               restocking level, it prepares more dishes and updates the stock level. 
               The time of preparing can be customized. For now the value is set to be small, 
               so that changes of stock level can be spotted easily.
               
               
         - Drone.java
         
               Similar to the KichenStaff.java. 
               Drones have two functions: deliver orders to customers or buying ingredients 
               from the supplier. 
               
               For now, how many ingredients it can hold and how long the buying takes are 
               set to specific values. Both can be customized according to the real suitaion.
               
         - Order.java
         
               including information like the customer who placed it, the content of the order , etc.
               
         - Stock.java
         
               It holds the stock level and the restocking level of a sushi dish type or 
               an ingredient type.
               It works like a pair (A,B) structure. 
               Looking into the java file can help to understand.
               
         - Supplier.java
         
               It has information about the supplier. E.g. how far is the supplier from 
               the business, the name of supplier
               
         - SushiDish.java
               
               Contains information including name, description, stock, recipe 
               (a hashtable of ingredients and amount) etc.

         
         View: -------------------------------------------------------------------------------------------------------------
         - AlertPopup.java
         
               As I described above.
               
         - ServerCustomerData.java
         - ServerDroneData.java
         - ServerIngreData.java
         - ServerOrderData.java
         - ServerRecipeData.java
         - ServerStaffData.java
         - ServerSupplierData.java
         - ServerSushiData.java
               
               They are data models for Server GUI tableviews. 
         
         - ServerGUI.fxml
         
               The GUI file.
               
         Controller: --------------------------------------------------------------------------------------------------------
         
         - ServerGUIController.java
         
               Holds a reference to the server socket. Controls all components of the GUI.
               It can invoke the server instance (server.java) to send messages to client applications.
         
3. Todo List in the future

   3.1 Make a new version of communication: 
         Using Java RMI and Serialization to implement the communication between clients and the server. 
         Allow objects in the server to be remotely invoked by the clients, so that
         clients don't have to send String messages, which contains order information, to the 
         server (this could be unsafe and lead to information leakage).
         
   3.2 Change the single server model to a multi-server model:
         Let multiple servers talk to the same database. Considering the sushi business might be 
         expanded to different cities, multiple server applications , which running at the same 
         time and access to the same database, might be necessary. 
         
   



