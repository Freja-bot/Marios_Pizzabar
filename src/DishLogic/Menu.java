package DishLogic;

import java.util.ArrayList;
import java.util.Collections;

public class Menu {
    private static ArrayList<DishDescription> menu = new ArrayList<>();


    public static void showMenu() {
        for (DishDescription d : menu) {
            System.out.println(d.toString());
        }
    }

    public static void loadMenuFromFile(String file) {
        Ledger.getMenuFromFile(file);
    }

    public static void addNewDish(DishDescription dish) {
        menu.add(dish);
    }

    public static void addNewDishToMenu(DishDescription dish, String fileName) {
        for (DishDescription d : menu) {
            if (d.getDishID() == dish.getDishID()) {
                System.out.println("Id already exists");
                return;
            }
        }
        addNewDish(dish);
        Ledger.addDishToFile(dish, fileName);
    }

    public static void removeDish(int dishID, String fileName) {
        for (DishDescription d : menu) {
            if (d.getDishID() == dishID) {
                Ledger.removeDish(dishID, fileName);
                menu.remove(d);
                return;
            }
        }
        System.out.println("ID findes ikke");
    }

    public static DishDescription getDishFromID(int dishID){
        for (DishDescription d : menu) {
            if (d.getDishID() == dishID){
                return d;
            }
        }
        System.out.println("ID findes ikke");
        return null;
    }

    public static ArrayList<DishDescription> getMenu() {
        return menu;
    }

    public static void sort(String file){
        Collections.sort(menu);
        Ledger.sortMenu(menu,file);
    }

}
