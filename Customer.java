import java.util.*;

public class Customer {
    private static int nextId = 1;
    private String name;
    private String phone;
    protected String memberId;
    private HashMap<String, Orders> orders = new HashMap<>();
    private static Map<String, Customer> allCustomers = new TreeMap<>(); //TreeMap so that it sorts the members in ascending

    public Customer() {
        name = "";
        phone = "";
    }

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.memberId = String.format("M%03d", nextId++);
    }
    
    public String getMemberId() {
        return memberId;
    }
    
    public String getMemberName(){
        return name;
    }

    public void addOrder(Orders order) {
        orders.put(order.getOrderId(), order);
    }

    public static Customer registerNewCustomer(String name, String phone) { //Register new customers
        Customer newCust = new Customer(name, phone);
        allCustomers.put(newCust.memberId, newCust);
        return newCust;
    }

    public static void viewAllCustomers() { //view all existing customers
        System.out.println("Registered Customers");
        System.out.println("ID\tName\t\t\t\tContact");
        for (Customer cust : allCustomers.values()) {
            System.out.println(cust.memberId + "\t" + cust.name + "\t\t\t" + cust.phone);
        }
    }

    public static Customer findCustomerById(String memberId) { //retrieves customer information based on ID
        Customer found = allCustomers.get(memberId);
        if (found != null) {
            System.out.println("Customer: " + found.name + "\nContact Number: " + found.phone);
            if (found.orders.isEmpty()) { //if customer has no previous orders
                System.out.println("No orders found for this customer.");
            }
            else {
                System.out.println("Order History:");
                for (Map.Entry<String, Orders> entry : found.orders.entrySet()) {
                    String orderId = entry.getKey();
                    Orders order = entry.getValue();
                    System.out.println("Order ID: " + orderId);
                    order.showOrderDetails(found);
                }
            }
        }
        else {
            System.out.println("Customer not found.");
        }
        return found;
    }


    public void updateOrderStatus(String orderId) { //Update order status
        Orders order = orders.get(orderId);
        if (order != null) {
            order.updateOrderStatus();
        }
        else {
            System.out.println("Order not found.");
        }
    }
}