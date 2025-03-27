package DishLogic;

import java.util.ArrayList;
import java.util.Collections;

public class ActiveOrders {

    private static ArrayList<Order> orders = new ArrayList<>();

    //TODO
    //statistics stuff

    private ActiveOrders() {

    }

    public static void loadActiveOrders(String file) {
        ArrayList<String> data = Ledger.getFileAsArrayListOfStrings(file);
        for (String s : data) {
            orders.add(new Order(s));
        }
        sort();
    }

    public static void finishOrder(int orderID, String file, String file2) {
        Order order = getOrderFromOrderID(orderID);
        if (order == null) {
            return;
        }
        cancelOrder(orderID, file);
        Ledger.saveForStatistics(order, file2);
    }

    public static void cancelOrder(int orderID, String file) {
        for (Order o : orders) {
            if (o.getOrderID() == orderID) {
                orders.remove(o);
                Ledger.removeOrderFromActiveOrders(orders, file);
                sort();
                return;
            }
        }
        System.out.println("ID findes ikke");
    }

    public static void showOrders() {
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    public static void addNewOrder(Order o) {
        orders.add(o);
        sort();
    }

    public static void addNewOrderToFile(Order o, String file) {
        addNewOrder(o);
        Ledger.addOrderToFile(o, file);
    }

    public static Order getOrderFromOrderID(int orderID) {
        for (Order o : orders) {
            if (o.getOrderID() == orderID) {
                return o;
            }
        }
        System.out.println("Bestilling findes ikke");
        return null;
    }

    private static void sort() {
        Collections.sort(orders);
    }

    public static ArrayList<Order> getOrders() {
        return orders;
    }
}
