import java.util.*;
import java.time.*;
import java.time.format.*;

public class BakeryMain {


///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static void initializeDefaultMenuItems(MenuItem menu) {
        System.out.println("Initializing default menu items...");
        
        // Set up default bakery items (ID, Name, Price, Quantity)
        MenuItem.setItemId(0, 1);
        MenuItem.setItemName(0, "Sourdough Bread");
        MenuItem.setItemPrice(0, 8.50);
        MenuItem.setItemQuantity(0, 20);
        
        MenuItem.setItemId(1, 2);
        MenuItem.setItemName(1, "Chocolate Cake");
        MenuItem.setItemPrice(1, 35.00);
        MenuItem.setItemQuantity(1, 10);
        
        MenuItem.setItemId(2, 3);
        MenuItem.setItemName(2, "Chocolate Chip");
        MenuItem.setItemPrice(2, 2.50);
        MenuItem.setItemQuantity(2, 50);
        
        MenuItem.setItemId(3, 4);
        MenuItem.setItemName(3, "Croissant");
        MenuItem.setItemPrice(3, 4.00);
        MenuItem.setItemQuantity(3, 25);
        
        MenuItem.setItemId(4, 5);
        MenuItem.setItemName(4, "Cinnamon Roll");
        MenuItem.setItemPrice(4, 5.50);
        MenuItem.setItemQuantity(4, 15);
        
        MenuItem.setItemId(5, 6);
        MenuItem.setItemName(5, "Blueberry");
        MenuItem.setItemPrice(5, 3.50);
        MenuItem.setItemQuantity(5, 20);
        
        MenuItem.setItemId(6, 7);
        MenuItem.setItemName(6, "Apple Pie");
        MenuItem.setItemPrice(6, 28.00);
        MenuItem.setItemQuantity(6, 8);
        
        MenuItem.setItemId(7, 8);
        MenuItem.setItemName(7, "Baguette");
        MenuItem.setItemPrice(7, 6.00);
        MenuItem.setItemQuantity(7, 15);
        
        MenuItem.setItemId(8, 9);
        MenuItem.setItemName(8, "Cheese Danish");
        MenuItem.setItemPrice(8, 4.50);
        MenuItem.setItemQuantity(8, 12);
        
        MenuItem.setItemId(9, 10);
        MenuItem.setItemName(9, "Red Velvet");
        MenuItem.setItemPrice(9, 3.75);
        MenuItem.setItemQuantity(9, 24);
        
        System.out.println("Default menu items initialized successfully!");
    }
    
    // Method to initialize default customers
    private static void initializeDefaultCustomers(HashMap<String, Customer> customerMap) {
        System.out.println("Initializing default customers...");
        
        // Register some default customers
        Customer customer1 = Customer.registerNewCustomer("John Smith", "0123456789");
        customerMap.put(customer1.getMemberId(), customer1);
        
        Customer customer2 = Customer.registerNewCustomer("Jane Doe", "0198765432");
        customerMap.put(customer2.getMemberId(), customer2);
        
        Customer customer3 = Customer.registerNewCustomer("Mike Johnson", "0111223344");
        customerMap.put(customer3.getMemberId(), customer3);
        
        System.out.println("Default customers initialized successfully!");
        System.out.println("Available Member IDs: " + customer1.getMemberId() + ", " + 
                          customer2.getMemberId() + ", " + customer3.getMemberId());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) {
	    HashMap<String, Customer> customerMap = new HashMap<>();
		final int exit = -1; //sentinel value to exit the program
		int orderCounter = 1;
		int select1, select2; //used for customer selection
		String searchId;
		LocalTime time = LocalTime.now();
		DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDate today = LocalDate.now();
		Scanner sc = new Scanner(System.in);
		MenuItem menu = new MenuItem();
		Orders order = new Orders(orderCounter);
		Customer customer = new Customer();
		Report report = new Report();
		
		
		
        // Initialize default menu items
		initializeDefaultMenuItems(menu);
		
		// Initialize default customers
		initializeDefaultCustomers(customerMap);
		
		
		do {
			select1 = 0;
			select2 = 0;
			System.out.print("\nDoughminator\n");
			System.out.println(today +" "+time.format(timeFormat));
			System.out.println("\nEnter a code:\n 1 - Menu\n 2 - Order\n 3 - Customer\n 4 - Report\n-1 - Exit The System");
			while (!sc.hasNextInt()) { //Ensures the input value is the correct data type
				System.out.println("Invalid input. Please try again.\n");
				sc.next(); // discard invalid input
			}
			select1 = sc.nextInt(); //User inputs the function they want to select
			switch(select1) {
			    case 1: { //displays Menu related functions
				    System.out.println("\nMenu:\n1 - View Menu\n2 - Add Item\n3 - Update Item\n4 - Remove Item\n5 - Return");
				    while (!sc.hasNextInt()) {
				    	System.out.println("Invalid input. Please try again.\n");
			    		sc.next(); 
			    	}
			    	select2 = sc.nextInt(); //User inputs the function they want to select
			    	switch(select2) {
			           	case 1: { //Displays Menu
			        		menu.displayItems();
			        		break;
			        	}
			        	case 2: { //Allows for adding new items to menu
			        		menu.addItem();
			        		break;
			        	}
			        	case 3: { //Allows user to update existing items on the menu
			        		menu.updateItem();
				        	break;
				        }
				        case 4: { //Allows user to remove an item from the menu
			        		menu.removeItem();
			        		break;
				        }
				        case 5:{ //Returns to the main menu
				            break;
				        }
				        default: {
				        	System.out.println("Invalid input.");
				        }
			        }
                    break;
			    }
		    	case 2: { //Displays Order related functions
		    		System.out.println("\nOrder:\n1 - Place Order\n2 - Show Order Details\n3 - Update Order Status\n4 - Return");
			    	while (!sc.hasNextInt()) {
				    	System.out.println("Invalid input. Please try again.\n");
				    	sc.next(); 
				    }
				    select2 = sc.nextInt();
				    switch(select2) {
				        case 1: { //allows user to place order
				            System.out.print("Enter your Member ID: ");
                            String custId = sc.next().toUpperCase(); //toUpperCase() converts value of string to uppercase
                            sc.nextLine(); 

                            Customer foundCustomer = Customer.findCustomerById(custId);
                            if (foundCustomer != null) { //places order if customer is found
                                Orders newOrder = new Orders(orderCounter);
                                newOrder.placeOrder(foundCustomer);
                                orderCounter++;
                            }
                            else { //prompts to register customer if there is no existing member ID in the system
                                System.out.println("Invalid Member ID.");
                                System.out.print("Would you like to register as a new customer? (Y/N): ");
                                String choice = sc.nextLine();
                                if (choice.equalsIgnoreCase("Y")) {
                                    System.out.print("Enter your name: ");
                                    String name = sc.nextLine();
                                    System.out.print("Enter your contact number: ");
                                    String phone = sc.nextLine();

                                    Customer newCustomer = Customer.registerNewCustomer(name, phone);
                                    customerMap.put(newCustomer.getMemberId(), newCustomer);

                                    Orders newOrder = new Orders(orderCounter);
                                    newOrder.placeOrder(newCustomer);
                                    orderCounter++;
                                 }
                                else {
                                    System.out.println("Order cancelled.");
                                }
                            }
                            break;
				        }
				        case 2: { //View orders
				          System.out.print("Enter Member ID to search: "); //Search for orders based on member ID
			    	        searchId = sc.next().toUpperCase();
			    	        sc.nextLine();
			    	        Customer.findCustomerById(searchId);
                            break;

				        }
				        case 3: { //Updates order status 
				            System.out.print("Enter your Member ID: "); 
                            String custId = sc.next().toUpperCase();
                            sc.nextLine();

                            Customer foundCustomer = Customer.findCustomerById(custId);
                            if (foundCustomer != null) {
                                System.out.print("Enter Order ID to update status: ");
                                String orderId = sc.next();
                                sc.nextLine();
                                
                                foundCustomer.updateOrderStatus(orderId);
                            } else {
                             System.out.println("Customer not found.");
                            }
				        	break;
				        }
				        case 4:{ //Returns to the main menu
				            break;
				        }
				        default: {
				        	System.out.println("Invalid input.");
				        }
				    }
				    break;
			    }
			    case 3: { //Displays Customer related functions
			    	System.out.println("\nCustomer:\n1 - View All Customers\n2 - Search Customer\n3 - Register Customer\n4 - Return");
			    	while (!sc.hasNextInt()) {
			    		System.out.println("Invalid input. Please try again.\n");
			    		sc.next(); 
			    	}
			    	select2 = sc.nextInt();
				    switch(select2){
			    	    case 1:{ //displays all customer records
			    	        Customer.viewAllCustomers();
			    	        break;
			    	    }
			    	    case 2:{ //search for a single customer
			    	        System.out.print("Enter Member ID to search: ");
			    	        searchId = sc.next().toUpperCase();
			    	        sc.nextLine();
			    	        Customer.findCustomerById(searchId);
                            break;
			    	    }
			    	    case 3:{ //registers new customers
			    	        sc.nextLine();
			    	        System.out.print("Enter new customer name: ");
			    	        String newCustomer = sc.nextLine();
			    	        System.out.print("Enter new customer contact number: ");
			    	        String newNumber = sc.next();
			    	        sc.nextLine();
			    	        Customer newCust = Customer.registerNewCustomer(newCustomer, newNumber);
                            customerMap.put(newCust.getMemberId(), newCust);
			    	        break;
			    	    }
			    	    case 4:{ //Returns to the main menu
				            break;
				        }
			    	    default:{
			    	        System.out.println("Invalid input.");
			    	    }
				    }
			    	 break;
			    }
			    case 4: { //Displays Report related functions
				    System.out.println("\nReport:\n1 - View Items Sold\n2 - View Sales\n3 - View Inventory\n4 - Return");
				    while (!sc.hasNextInt()) {
					    System.out.println("Invalid input. Please try again.\n");
					    sc.next(); 
				    }
				    select2 = sc.nextInt();
				    switch(select2){
				        case 1:{ //Shows all items sold
                            report.itemsSold();
				            break;
			    	    }
				        case 2:{ //Shows total sales
                          report.viewTotalSales();
			    	        break;
			    	    }
			            case 3:{ //Shows items and its inventory
                            report.viewInventory();
			    	        break;
			    	    }
			    	    case 4:{ //Returns to the main menu
				            break;
				        }
			    	    default:{
			    	        System.out.println("Invalid input.");
			    	    }
			        }
			        break;
			    }
			    case -1:{ //Terminates the program
			        System.out.println("\n\nProgram Terminated.");
			        break;
			    }
				default: {
			        System.out.println("Invalid input.");
			    }
			    
            } 
	    } while(select1 != exit); //program runs as long as select1 does not equal the sentinel value
    }
}

/*
References
Title: How can I clear the Scanner buffer in Java?
Author: -
Date: -
Code Version: -
Availability: https://stackoverflow.com/questions/10604125/how-can-i-clear-the-scanner-buffer-in-java

Title: Class Scanner
Author: -
Date: -
Code Version: -
Availability: https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html

Title: Class TreeMap<K,V>
Author: -
Date: -
Code Version: -
Availability: https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html

Title: Java String to Uppercase
Author: Pankaj
Date: August 4, 2022
Code Version: -
Availability:https://www.digitalocean.com/community/tutorials/java-string-to-uppercase

Title: Java Date and Time
Author: w3schools
Date: -
Code Version: -
Availability: https://www.w3schools.com/java/java_date.asp
*/