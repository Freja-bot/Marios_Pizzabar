package DishLogic;

import java.util.ArrayList;
import java.util.Collections;

public class Menu {

    //TODO change variable names

    //variables:
    private static ArrayList<DishDescription> menu = new ArrayList<>();

    /*Private constructor means this class can only be instantiated as an object from within this class.
    This class is a utility class*/
    private Menu(){}

    //Iterating through the ArrayList menu and printing all dishes
    public static void showMenu() {
        for (DishDescription d : menu) {
            System.out.println(d);
        }
    }

    /*Given a file path use Ledger to get an Arraylist of strings representing dishes. Then instantiating new dishes
    and adding them to the menu Arraylist*/
    public static void loadMenuFromFile(String file) {
        ArrayList<String> data = Ledger.getFileAsArrayListOfStrings(file);
        for (String s : data) {
            addNewDish(new DishDescription(s));
        }
        sort();
    }

    //Adding a given dish to menu
    private static void addNewDish(DishDescription dish) {
        menu.add(dish);
    }

    //Adding new dish if it does not already exist, then adding it to menu and to Ledger
    public static void addNewDishToMenu(DishDescription dish, String fileName) {
        for (DishDescription d : menu) {
            if (d.getDishID() == dish.getDishID()) {
                System.out.println("Id already exists");
                return;
            }
            if (d.getName().toLowerCase().equals(dish.getName().toLowerCase())) {
                System.out.println("Name already exists");
                return;
            }
        }
        addNewDish(dish);
        Ledger.addDishToFile(dish, fileName);
        sort();
    }

    //Removing dish with given dishID in menu and Ledger
    public static void removeDish(int dishID, String fileName) {
        for (DishDescription d : menu) {
            if (d.getDishID() == dishID) {
                menu.remove(d);
                Ledger.removeDishFromMenu(menu, fileName);
                return;
            }
        }
        System.out.println("ID findes ikke");
    }

    //Returning a dish given an ID
    public static DishDescription getDishFromID(int dishID) {
        for (DishDescription d : menu) {
            if (d.getDishID() == dishID) {
                return d;
            }
        }
        System.out.println("ID findes ikke");
        return null;
    }

    //getter
    public static ArrayList<DishDescription> getMenu() {
        return menu;
    }

    //Sorting menu, using the compareTo() methods in DishDescription
    public static void sort() {
        Collections.sort(menu);
    }

}
