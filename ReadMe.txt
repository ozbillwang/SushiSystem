This is my personal project -- Sushi Restaurant System

1. A client-server model application that uses sockets to enable communications between a single server with multiple clients. 

2. Multithreading is used to allow multiple kitchen staff (preparing Sushi dishes) and drones(delivering order to customer or buying      
   ingredients from supplier) work concurrently. 

3. The GUI was built by JavaFX. 
    Specific operations might need to be taken to run the program 
    see:https://docs.oracle.com/javase/8/javafx/get-started-tutorial/jfx-overview.htm
    
4. For this version, it only allows one server to run. I will make a multi-server version in the future. 

5. To run the code, plesae compile InitLoginGUI.java and InitServerGUI.java
   Please run the server first and then clients, because clients need to connect to the server via socket.
   
6. There are still many System.out.println commands in the code. I decided not to remove them so that logs 
   of operations can be seen by the printings.

Introduction: 
This system is designed for a Sushi restaurant that has:
     - drones which delivers orders to customers and buys ingredients from suppliers.
     - kitchen staff who prepare Sushi dishes when the stock of certain Sushi dishes reached the restocking level.
     - Stockmanagement that deals with the storage of Sushi dishes and ingredients. 
     
