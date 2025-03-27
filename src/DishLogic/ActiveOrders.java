package DishLogic;

import java.util.ArrayList;
import java.util.Collections;

public class ActiveOrders {

    private static ArrayList<Order> orders = new ArrayList<>();
    private static Order lastRemovedOrder;

    //Loading saved active orders. Will be empty if all orders where complete
    public static void loadActiveOrders(String file) {
        ArrayList<String> data = Ledger.getFileAsArrayListOfStrings(file);
        for (String s : data) {
            orders.add(new Order(s));
        }
        sort();
    }

    //Checks if order exists and saves it for statistics
    public static void finishOrder(int orderID, String file, String file2) {
        Order order = getOrderFromOrderID(orderID);
        if (order == null) {
            return;
        }
        cancelOrder(orderID, file);
        Ledger.saveForStatistics(order, file2);
    }

    //removes the order from the list of active orders and the file saving active orders,
    //and saving a one-time-only backup in lastRemovedOrder
    public static void cancelOrder(int orderID, String file) {
        for (Order o : orders) {
            if (o.getOrderID() == orderID) {
                orders.remove(o);
                Ledger.removeOrderFromActiveOrders(orders, file);
                sort();
                lastRemovedOrder = o;
                return;
            }
        }
        System.out.println("ID findes ikke");
    }

    //display all active orders with a for each loop
    public static void showOrders() {
        for (Order o : orders) {
                System.out.println(o);
        }
    }

    //adds a new order and sorts all active orders
    private static void addNewOrder(Order o) {
        orders.add(o);
        sort();
    }

    //Saves the new order and adds it through addNewOrder() method
    public static void addNewOrderToFile(Order o, String file) {
        addNewOrder(o);
        Ledger.addOrderToFile(o, file);
    }

    //Returns an order, given an orderID
    public static Order getOrderFromOrderID(int orderID) {
        for (Order o : orders) {
            if (o.getOrderID() == orderID) {
                return o;
            }
        }
        System.out.println("Bestilling findes ikke");
        return null;
    }

    //Restores latest removed order
    public static void restoreLatestRemovedOrder(String file){
        addNewOrderToFile(lastRemovedOrder,file);
    }

    //Sorts the list of active orders with the compareTo() methods in Order
    private static void sort() {
        Collections.sort(orders);
    }

}
