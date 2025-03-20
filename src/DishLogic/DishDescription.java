package DishLogic;

import java.util.Collections;
import java.util.Comparator;

public class DishDescription implements Comparable<DishDescription>{

    private int dishID;
    private String name;
    private double price;

    public DishDescription(int dishID, String name, double price) {

        this.dishID = dishID;
        this.name = name;
        this.price = price;

    }

    public DishDescription(String lineFromFile) {
        String[] createDish = lineFromFile.split(":");
        if (createDish.length == 3) {
            this.dishID = Integer.parseInt(createDish[0]);
            this.name = createDish[1];
            this.price = Double.parseDouble(createDish[2]);
        }
    }

    public String toString() {
        return dishID + ". " + name + ": " + price + ",-";
    }

    public int getDishID() {
        return dishID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String addToFile() {
        return dishID + ":" + name + ":" + price;
    }

    @Override
    public int compareTo(DishDescription o) {
        return dishID-o.getDishID();
    }
}