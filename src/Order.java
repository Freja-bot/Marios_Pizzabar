import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Order {
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

    public void addDish(int dishID){
        OrderLine oderLine = new OrderLine();
    }

}
