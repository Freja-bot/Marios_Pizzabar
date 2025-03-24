package DishLogic;

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

    public void addDish(DishDescription dish, int quantity){
        orderLines.add(new OrderLine(dish, quantity));
    }


     public int compareTo(Order otherOrder){
        return this.collectionTime.compareTo(otherOrder.getCollectionTime());
    }

    public String toString(){
        StringBuilder orderText = new StringBuilder();
        for(int i = 0; i < orderLines.size(); i++){
            orderText.append(orderLines.get(i));
        }
        return orderText.toString();
    }

    public String addToFile(){
        return "";
    }

}

