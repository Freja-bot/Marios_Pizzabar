package DishLogic;

//TODO
//addToFile
public class OrderLine {
    private DishDescription dish;
    private int quantity;

    public OrderLine(DishDescription dish, int quantity){
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

    public String addToFile(){
        return this.dish.getDishID() + "/" + this.quantity;
    }

}
