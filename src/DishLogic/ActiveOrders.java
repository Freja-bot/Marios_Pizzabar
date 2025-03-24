package DishLogic;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ActiveOrders {

    private static ArrayList<Order> orders = new ArrayList<>();

    public static void loadActiveOrders(String file) {
        Ledger.getActiveOrdersFromFile(file);
    }

    public static void removeOrder(int orderID, String file,String file2) {
        Order order = getOrderFromOrderID(orderID);
        Ledger.removeOrderFromFile(orderID, file);
        Ledger.saveForStatistics(order,file2);
    }

    public static void cancelOrder(int orderID,String file){
        for(Order o: orders){
            if(o.getOrderID()==orderID){
                Ledger.removeOrderFromFile(orderID,file);
                orders.remove(o);
            }
        }
    }

    public static void showOrders() {
        for (Order o : orders) {
            o.toString();
        }
    }

    public static void addNewOrder(Order o) {
        orders.add(o);
    }

    public static void addNewOrderToFile(Order o, String file) {
        addNewOrder(o);
        Ledger.addOrderToFile(o,file);
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

}
