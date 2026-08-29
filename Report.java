import java.util.*;
import java.text.DecimalFormat;

public class Report
  {
    MenuItem menu = new MenuItem();
    DecimalFormat nd = new DecimalFormat("000");
    DecimalFormat dd = new DecimalFormat("#,##0.00");
    
    public void itemsSold() { //shows all items that have been sold
        System.out.println("\nItems Sold");
        System.out.println("Item ID\t\tItem Name\t\t\tQuantity");

        for (int i = 0; i < menu.itemCount; i++) {
            int soldQuantity = menu.getTotalSold(i); 
            if (soldQuantity > 0) {
                System.out.println(nd.format(menu.getItemId(i)) + "\t\t"+menu.getItemName(i) + "\t\t\t" + soldQuantity);
            }
        }
    }

    public void viewTotalSales() { //shows total value of all sales
        System.out.println("Total Sales: RM" + dd.format(menu.getTotalSales()) + "\n\n");
    }

    public void viewInventory() { //views the inventory for all menu items
        System.out.println("\nCurrent Inventory");
        System.out.println("Item ID\t\tItem Name\t\t\tQuantity");
        for (int i = 0; i < menu.itemCount; i++) {
            System.out.println(nd.format(menu.getItemId(i)) + "\t\t"+ menu.getItemName(i) +"\t\t\t"+ menu.getItemQuantity(i));
        }
    }
  }
