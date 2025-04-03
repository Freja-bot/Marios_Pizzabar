package services;

import models.Order;

import java.util.ArrayList;
import java.util.Collections;

public class ActiveOrders {

    private static ArrayList<Order> orders = new ArrayList<>();
    private static Order lastRemovedOrder;
    private final static String ACTIVE_ORDERS = "ActiveOrders.txt";
    private final static String STATISTICS = "statistics.txt";

    private ActiveOrders() {

    }

    //Loading saved active orders. Will be empty if all orders where complete
    public static void loadActiveOrders() {
        ArrayList<String> data = Ledger.getFileAsArrayListOfStrings(ACTIVE_ORDERS);
        for (String s : data) {
            orders.add(new Order(s));
        }
        sort();
    }

    //Checks if order exists and saves it for statistics
    public static void finishOrder(int orderID) {
        Order order = getOrderFromOrderID(orderID);
        if (order == null) {
            return;
        }
        cancelOrder(orderID);
        Ledger.writeLineToFile(order.addToStatistics(),STATISTICS);
    }

    //removes the order from the list of active orders and the file saving active orders,
    //and saving a one-time-only backup in lastRemovedOrder
    public static void cancelOrder(int orderID) {
        for (Order o : orders) {
            if (o.getOrderID() == orderID) {
                orders.remove(o);
                sort();
                Ledger.writeArrayToFile(getOrdersAsArrayOfStrings(),ACTIVE_ORDERS);
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
    public static void addNewOrderToFile(Order o) {
        if(o.getOrderLines().isEmpty()){
            return;
        }
        addNewOrder(o);
        Ledger.writeLineToFile(o.addToFile(),ACTIVE_ORDERS);
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

    //Restores latest removed order (NOT IMPLIMENTED)
    public static void restoreLatestRemovedOrder(){
        addNewOrderToFile(lastRemovedOrder);
    }

    //Sorts the list of active orders with the compareTo() methods in Order
    private static void sort() {
        Collections.sort(orders);
    }


    public static ArrayList<Order>getOrders(){
        return orders;
    }

    private static ArrayList<String> getOrdersAsArrayOfStrings(){
        ArrayList<String> lines = new ArrayList<>();
        for(Order o:orders){
            lines.add(o.addToFile());
        }
        return lines;
    }



}
