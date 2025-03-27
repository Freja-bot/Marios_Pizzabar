package DishLogic;

public class DishDescription implements Comparable<DishDescription> {

    private int dishID;
    private String name;
    private double price;
    private String description;

    //a constructor creating a new dish
    public DishDescription(int dishID, String name, String description, double price) {

        this.dishID = dishID;
        this.name = name;
        this.description = description;
        this.price = price;


    }

    //constructor loading an old dish
    public DishDescription(String lineFromFile) {
        String[] createDish = lineFromFile.split(":");
        if (createDish.length == 4) {
            this.dishID = Integer.parseInt(createDish[0]);
            this.name = createDish[1];
            this.description = createDish[2];
            this.price = Double.parseDouble(createDish[3]);
        }
    }

    public String toString() {
        return dishID + ". " + name + ": " + description + " " + String.format("%.2f", price) + ",-";
    }

    //getters
    public int getDishID() {
        return dishID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    //A different form of toString, that takes this dish's values and returns a string containing them
    public String addToFile() {
        return dishID + ":" + name + ":" + description + ":" + price;
    }

    @Override
    public int compareTo(DishDescription o) {
        return dishID - o.getDishID();
    }
}