package models;

//TODO
//addToFile
public class OrderLine {
    private Dish dish;
    private int quantity;

    public OrderLine(Dish dish, int quantity){
        this.dish = dish;
        this.quantity = quantity;
    }

    public double getSubTotal(){
        return this.dish.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return this.dish.getDishID() + ". " + this.dish.getName() + "  Antal. " + this.quantity + '\n';
    }

    //A different form of toString, that takes this orderline's values and returns a string containing them
    public String addToFile(){
        return this.dish.getDishID() + "/" + this.quantity;
    }

    public String addToStatistics(){
        return this.dish.getName() + "/" + this.quantity;
    }

}
