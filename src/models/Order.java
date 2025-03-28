package models;
import services.UniqueID;
import services.Menu;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Order implements Comparable<Order> {

    //Variables
    private int orderID;
    private LocalDate date;
    private LocalTime collectionTime;
    private ArrayList<OrderLine> orderLines;


    //Constuctor for creating new order
    public Order(int hour, int minute) {

        this.orderID = UniqueID.getOrderID();
        this.date = LocalDate.now();
        collectionTime = LocalTime.of(hour, minute);
        //If collection time is set to earlier than today, ths program assumes that it
        // should be made tomorrow instead, to account for late hours
        if(LocalTime.now().isAfter(collectionTime)){
            this.date = this.date.plusDays(1);
        }
        orderLines = new ArrayList<>();
    }


    //An overloading constructor for loading old order
    public Order(String data) {
        //split function splits our data string into multiple strings, so we can access them individually

        String[] splitData = data.split(":");
        this.orderID = Integer.parseInt(splitData[0]);
        this.date = LocalDate.parse(splitData[1]);
        collectionTime = LocalTime.of(Integer.parseInt(splitData[2]), Integer.parseInt(splitData[3]));
        orderLines = new ArrayList<>();


        for (int i = 4; i < splitData.length; i++) {

            String[] doubleSpiltData = splitData[i].split("/");
            addDish(Menu.getDishFromID(Integer.parseInt(doubleSpiltData[0])), Integer.parseInt(doubleSpiltData[1]));
        }

    }

    //getters
    public LocalTime getCollectionTime() {

        return this.collectionTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getOrderID(){
        return this.orderID;
    }


    public void addDish(Dish dish, int quantity) {
        if(quantity<=0){
            return;
        }

        orderLines.add(new OrderLine(dish, quantity));
    }

    public double getTotalPrice() {
        double price = 0;
        for (OrderLine ol : orderLines) {
            price += ol.getSubTotal();
        }
        return price;
    }


    public ArrayList<OrderLine> getOrderLines() {
        return orderLines;
    }


    //CompareTo() takes an Order in its parameter and compares itself to its collection time
    //Then it returns 1 0 or -1 to indecate if it should be moved left or right in an array
    //This method is used to sort an array of orders
    public int compareTo(Order otherOrder) {
        if (this.date.compareTo(otherOrder.getDate()) == 0) {

            return this.collectionTime.compareTo(otherOrder.getCollectionTime());
        }else{
            return this.date.compareTo(otherOrder.getDate());
        }
     }

    public String toString(){
        String orderText = "_Tidspunkt: " + collectionTime + "   OrderID: " + this.orderID + "_\n";
        for(int i = 0; i < orderLines.size(); i++){
            orderText = orderText + orderLines.get(i);
        }
        return orderText.toString();
    }

    //A different form of toString, that takes this order's values and returns a string containing them
    public String addToFile() {

        String temp = this.orderID + ":" + this.date + ":" + this.collectionTime.toString();
        for(OrderLine o : orderLines){
            temp = temp + ":" + o.addToFile();
        }
        return temp;
    }

    public String addToStatistics() {
        String temp = this.date + ":" + this.collectionTime.toString();
        for (OrderLine o : orderLines) {
            temp = temp + ":" + o.addToStatistics();
        }
        return temp + ":" + getTotalPrice();
    }

}

