package DishLogic;

import java.util.ArrayList;
import java.util.Collections;

public class Menu {
    private static ArrayList<DishDescription> menu = new ArrayList<>();

    private Menu(){

    }

    public static void showMenu() {
        for (DishDescription d : menu) {
            System.out.println(d.toString());
        }
    }

    public static void loadMenuFromFile(String file) {
        ArrayList<String> data = Ledger.getFileAsArrayListOfStrings(file);
        for (String s : data) {
            menu.add(new DishDescription(s));
        }
        sort();
    }

    public static void addNewDish(DishDescription dish) {
        menu.add(dish);
    }

    public static void addNewDishWithCustomID(DishDescription dish,String fileName){
        menu.add(dish.getDishID()-1,dish);
        UniqueID.fixMenuNumbers(fileName);
    }

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

    public static void removeDish(int dishID, String fileName) {
        for (DishDescription d : menu) {
            if (d.getDishID() == dishID) {
                menu.remove(d);
                UniqueID.fixMenuNumbers(fileName);
                return;
            }
        }
        System.out.println("ID findes ikke");
    }

    public static DishDescription getDishFromID(int dishID) {
        for (DishDescription d : menu) {
            if (d.getDishID() == dishID) {
                return d;
            }
        }
        System.out.println("ID findes ikke");
        return null;
    }

    public static ArrayList<DishDescription> getMenu() {
        return menu;
    }

    public static void sort() {
        Collections.sort(menu);
    }

}
