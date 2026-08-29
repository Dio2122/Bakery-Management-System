import java.util.*;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.*;

public class Orders extends MenuItem
{
	final int exit = -1;
	Scanner sc = new Scanner(System.in);
	Report report = new Report();
	String[] status = {"Pending", "In Progress", "Completed"};
	static int[] Id = new int[50];
	private ArrayList<Integer> orderedId = new ArrayList<Integer>();
	private ArrayList<String> orderedItem = new ArrayList<String>();
	private ArrayList<Integer> orderedQuantity = new ArrayList<Integer>();
	private ArrayList<Double> orderedPrice = new ArrayList<Double>();
	private ArrayList<String> orderedStatus = new ArrayList<String>();
    private int currentStatusIndex = 0; // Default to "Pending" (index 0)
    private String currentStatus;
	double total = 0.00;
	int itemId, quantity;
	char add;
	String fullOrderId;

	DecimalFormat dd = new DecimalFormat("#,##0.00");
	DecimalFormat nd = new DecimalFormat("000");
	LocalTime time = LocalTime.now();
	DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
	LocalDate today = LocalDate.now();
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyMMdd");

	public Orders(int runningOrderNum) {
		super();
		this.fullOrderId = today.format(dateFormat) + nd.format(runningOrderNum);
		this.currentStatus = status[0]; // Status Update
	}

    public String getOrderId() {
        return fullOrderId;
    }
    

	public void addItem(int itemId, String itemName, int qty, double price) {
		orderedId.add(itemId);
		orderedItem.add(itemName);
		orderedQuantity.add(qty);
		orderedPrice.add(price);
		total += price * qty;
	}

	public void placeOrder(Customer customer)
	{
		displayItems(); //displays menu to view before ordering
		do {
			add = 'y';
			System.out.print("\nEnter the Item ID (-1 to cancel): ");
			while (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please try again.\n");
				sc.next(); 
			}
			itemId = sc.nextInt();
			sc.nextLine();
			if(itemId == exit) {
				break;
			}

			do {
				System.out.print("Enter quantity: ");
				while (!sc.hasNextInt() || quantity < 0) {
					System.out.println("Invalid input. Please try again.\n");
					sc.next(); 
				}
				quantity = sc.nextInt();

				if (quantity > MenuItem.getItemQuantity(itemId - 1) || quantity < 0) {
					System.out.println("Invalid input, only " + MenuItem.getItemQuantity(itemId - 1) + " items available in stock.");
					System.out.print("Enter quantity: ");
				}
			} while (quantity > MenuItem.getItemQuantity(itemId - 1) || quantity < 0);

			int currentQuantity = MenuItem.getItemQuantity(itemId - 1);
            MenuItem.setItemQuantity(itemId - 1, currentQuantity - quantity); //updates stock
			this.addItem(MenuItem.getItemId(itemId - 1), MenuItem.getItemName(itemId - 1), quantity, MenuItem.getItemPrice(itemId - 1));
            MenuItem.setTotalSold(itemId - 1, MenuItem.getTotalSold(itemId - 1) + quantity);

            // Add the new sale value to totalSales
            double tSales=0.00;
            tSales += MenuItem.getItemPrice(itemId - 1) * quantity;
            setTotalSales(tSales);
			System.out.print("Would you like to add more items? (Y/N): ");
			while (!sc.hasNext("[yYnN]")) {
				System.out.println("Invalid input. Please try again.\n");
				sc.next(); 
			}
			add = sc.next().charAt(0);
		} while(add == 'y' || add == 'Y');
		//orderStatus;
		if(itemId != exit) {
			customer.addOrder(this);
			this.showOrderDetails(customer);
		}
	}

	public void showOrderDetails(Customer customer)
	{
		System.out.println("\t\t\t\t\tDoughminator");
		System.out.println("\t\t\t\t\tOrder #"+fullOrderId);
		System.out.println("Date: " +today + " " +time.format(timeFormat));
		System.out.println("Customer: " + customer.getMemberId() + " - " + customer.getMemberName());
		System.out.println("Order ID: "+fullOrderId);
		System.out.println("Status: "+currentStatus);
		System.out.println("\nItem ID\t\tItem Name\t\tPrice(RM)\tQuantity\t\tTotal(RM)");
		for (int i = 0; i < orderedItem.size(); i++) {
		    System.out.println(" "+nd.format(orderedId.get(i)) + "\t\t" + orderedItem.get(i) + "\t\t" + dd.format(orderedPrice.get(i)) + "\t\t" + orderedQuantity.get(i)+"\t\t\t"+dd.format(orderedPrice.get(i) * orderedQuantity.get(i)));
		}
		System.out.println("\n\t\t\t\t\t\t\t\tTotal Price: RM " + dd.format(total));
	}



    public void updateOrderStatus() {
        System.out.println("Current Status: " + status[currentStatusIndex]);
        System.out.println("Enter the new status for Order #" + fullOrderId);
        System.out.println("Available statuses:\n0 - Pending\n1 - In Progress\n2 - Completed");
    
        int newStatus = -1;
        while (newStatus < 0 || newStatus > 2) {
            try {
                System.out.print("Enter status number (0-2): ");
                newStatus = sc.nextInt();
                if (newStatus >= 0 && newStatus <= 2) {
                    currentStatus = status[newStatus];
                    System.out.println("Order #" + fullOrderId + " is now " + currentStatus);
                }
                else {
                    System.out.println("Invalid status choice.");                    }
                }
                catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); 
                    sc.nextLine(); 
                }
        }
        sc.nextLine();
        
        // Update the current status
        currentStatusIndex = newStatus;
    
        if (orderedStatus.isEmpty()) {
            orderedStatus.add(status[currentStatusIndex]);
        }
        else {
            orderedStatus.set(orderedStatus.size() - 1, status[currentStatusIndex]);
        }
    
        System.out.println("Order #" + fullOrderId + " status updated to: " + status[currentStatusIndex]);
    }

    //  getter for current status
    public String getCurrentStatus() {
        return status[currentStatusIndex];
    }
}

