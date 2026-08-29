import java.util.*;
import java.text.DecimalFormat;

public class MenuItem {

    int temp, index, choice;
    String temp2;
    double temp3;
    char confirm;
    private static int[] iId = new int[50];
    private static String[] iName= new String[50];
    private static double[] iPrice = new double[50];
    private static int[] iQuantity = new int[50];
    private static int[] totalSold = new int[50];
    private static double totalSales = 0.0;
    static int itemCount = 10;
    Scanner sc = new Scanner(System.in);
    DecimalFormat dd = new DecimalFormat("#,##0.00"); //for prices
    DecimalFormat nd = new DecimalFormat("000"); //for item ID's (001, 002, ...)

    
    public MenuItem()
    {}
    
    public static void setItemId(int index, int id) {
        iId[index] = id;
    }

    public static void setItemName(int index, String name) {
        iName[index] = name;
    }

    public static void setItemPrice(int index, double price) {
        iPrice[index] = price;
    }

    public static void setItemQuantity(int index, int quantity) {
        iQuantity[index] = quantity;
    }
    
    public static void setTotalSold(int index, int quantity) {
        totalSold[index] = quantity;
    }
    
    public static void setTotalSales(double totalSales) {
    MenuItem.totalSales = totalSales;
}
    
    public static int getItemId(int index) {
        return iId[index];
    }

    public static String getItemName(int index) {
        return iName[index];
    }

    public static double getItemPrice(int index) {
        return iPrice[index];
    }

    public static int getItemQuantity(int index) {
        return iQuantity[index];
    }

    public static int getTotalSold(int index) {
        return totalSold[index];
    }

    public static double getTotalSales() {
        return totalSales;
    }

    //Add items to the menu
    public void addItem()
    {
        boolean duplicate;
        //Add Item ID
        do{ //ensures no duplicate values
            duplicate = false;
            System.out.print("Enter the Item ID: ");
      
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please try again.\n");
                sc.next(); // discard invalid input
            }
            temp = sc.nextInt();
            sc.nextLine();
            for(int i = 0; i < iId.length; i++){
                if(iId[i] == temp){
                    System.out.println("Item ID already exists.");
                    duplicate =true;
                }
            }
        }while(duplicate);
        index = temp - 1;
        setItemId(index, temp);

        //Add Item Name
        do{
            duplicate = false;
            System.out.print("Enter the Item Name: ");

            while (!sc.hasNext()) {
                System.out.println("Invalid input. Please try again.\n");
                sc.next(); // discard invalid input
            }
            temp2 = sc.nextLine();
            for(int i = 0; i < iName.length; i++){
                if(temp2.equals(iName[i])){
                    System.out.println("Item name already exists.");
                    duplicate =true;
                }
            }
        }while(duplicate);
        iName[index] = temp2;

        //Add Item Price
        System.out.print("Enter the Item Price: RM");

        while (!sc.hasNextDouble()) {
            System.out.println("Invalid input. Please try again.\n");
            sc.next(); // discard invalid input
        }
        iPrice[index] = sc.nextDouble();
        while(iPrice[index] < 0.00){
            System.out.println("Invalid input, please enter a positive value.");
            System.out.print("Enter the Item Price: RM");
            iPrice[index] = sc.nextDouble();
        }

        //Add Item Quantity
        System.out.print("Enter the Item Quantity: ");

        while (!sc.hasNextInt()) {
            System.out.println("Invalid input. Please try again.\n");
            sc.next(); // discard invalid input
        }
        iQuantity[index] = sc.nextInt();
        while(iQuantity[index] < 0){
            System.out.println("Invalid input, please enter a positive value.");
            System.out.print("Enter the Item Quantity: ");
            iQuantity[index] = sc.nextInt();
        }

        itemCount++;
        System.out.println("Item added successfully!");
    }

  
    //display all items
    public void displayItems() {
        if (itemCount == 0) { //displays error message if no items found
            System.out.println("No items to display.");
            return;
        }

        System.out.println("\nMenu Items:");
        System.out.println("Item ID\t\tItem Name\t\tPrice(RM)\tQuantity");
        for (int i = 0; i < itemCount; i++) {
            System.out.println("  "+nd.format(iId[i]) + "\t\t" + iName[i] + "\t\t" + dd.format(iPrice[i]) + "\t\t" + iQuantity[i]);
        }
    }

  
    //update item
    public void updateItem(){
        displayItems();
        System.out.print("Enter the Item ID to update: ");
        while (true) {
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid ID.");
                sc.next(); // discard invalid input
            }
        
            temp = sc.nextInt() - 1;
            sc.nextLine(); // clear buffer

            // Check if it's in a valid range
            if (temp >= 0 && temp < itemCount) {
                break; // exit loop if valid
            }   

            System.out.print("Invalid Item ID. Please try again: ");
        }
        index = temp;
        System.out.println("Item ID\t\t\tItem Name\t\tPrice(RM)\tQuantity");
        System.out.println("\n "+nd.format(iId[index]) + "\t" + iName[index] + "\t\t\t\t\t" + dd.format(iPrice[index]) + "\t\t" + iQuantity[index]);
        choice = 0;
        while(choice != -1){
            System.out.println("What would you like to update?\n1. Item Name\n2. Item Price\n3. Item Quantity\n-1. Exit");
            choice = sc.nextInt();
            sc.nextLine();
            if(choice == 1){ //update item name
                System.out.print("Enter the new Item Name: ");
                while (!sc.hasNext()) {
                    System.out.println("Invalid input. Please try again.\n");
                    sc.next(); 
                }
                iName[index] = sc.nextLine();
                System.out.println("Item Name updated successfully.");
                choice = 0;
            }
            else if(choice == 2){ //update item price
                System.out.print("Enter the new Item Price: ");
                while (!sc.hasNextDouble()) {
                    System.out.println("Invalid input. Please try again.\n");
                    sc.next(); 
                }
                iPrice[index] = sc.nextDouble();
                System.out.println("Item Price updated successfully.");
                choice = 0;
            }
            else if(choice == 3){ //update item quantity
                System.out.print("Enter the new Item Quantity: ");
                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input. Please try again.\n");
                    sc.next(); 
                }
                iQuantity[index] = sc.nextInt();
                System.out.println("Item Quantity updated successfully.");
                choice = 0;
            }
            else if(choice == -1){ //exit update
                break;
            }
            else{
                System.out.println("Invalid choice. Please try again.");
                choice = 0;
            }
            System.out.println("\n"+iId[index] + "\t\t\t" + iName[index] + "\t\t\t\t" + iPrice[index] + "\t\t\t" +iQuantity[index]);
        }
    }


    //remove item
    public void removeItem(){
        displayItems();
        System.out.print("Enter the Item ID to remove: ");
        index = sc.nextInt() - 1;
        System.out.println("Delete item: " + iName[index]); //ensures user really wants to remove the item
        System.out.print("Confirm? (Y/N): ");
        while (!sc.hasNext("[yYnN]")) {
            System.out.println("Invalid input. Please try again.\n");
            sc.next(); // discard invalid input
        }
        confirm = sc.next().charAt(0);
        if(confirm == 'Y' || confirm == 'y'){
            for (int i = index; i < itemCount - 1; i++) {
                iName[i] = iName[i + 1];
                iPrice[i] = iPrice[i + 1];
                iQuantity[i] = iQuantity[i + 1];
            }
            // Clear the last item
            iName[itemCount - 1] = null;
            iPrice[itemCount - 1] = 0.0;
            iQuantity[itemCount - 1] = 0;
    
            // Reduce the item count
            itemCount--;
        }
        else{
            System.out.println("Item removal cancelled.");
        }
    
    }
}

