import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Order implements Comparable<Order> {
    private int orderID;
    private LocalDate date;
    private LocalTime collectionTime;
    private ArrayList<OrderLine> orderLines;

    public Order(int hour, int minute){
        this.orderID = 1;
        this.date = LocalDate.now();
        collectionTime = LocalTime.of(hour, minute);
        orderLines = new ArrayList<>();
    }

    public LocalTime getCollectionTime(){
        return this.collectionTime;
    }

    public int getOrderID(){
        return this.orderID;
    }

    public void addDish(int dishID, int quantity){
        OrderLine oderLine = new OrderLine(dishID, quantity);

    }


     public int compareTo(Order otherOrder){
        return this.collectionTime.compareTo(otherOrder.getCollectionTime());
    }

}
