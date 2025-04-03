package services;

import models.Dish;

import java.util.ArrayList;
import java.util.Collections;

public class Menu {

    //TODO change variable names

    //variables:
    private static ArrayList<Dish> menu = new ArrayList<>();
    private static int dishID;
    private final static String MENU_FILE = "PizzaMenu.txt";

    /*Private constructor means this class can only be instantiated as an object from within this class.
    This class is a utility class*/
    private Menu() {
    }


    //Iterating through the ArrayList menu and printing all dishes
    public static void showMenu() {
        for (Dish d : menu) {
            System.out.println(d);
        }
    }

    /*Given a file path use services.Ledger to get an Arraylist of strings representing dishes. Then instantiating new dishes
    and adding them to the menu Arraylist*/
    public static void loadMenuFromFile() {
        ArrayList<String> data = Ledger.getFileAsArrayListOfStrings(MENU_FILE);
        for (String s : data) {
            addNewDish(new Dish(s));
        }
        sort();
    }

    //Adding a given dish to menu
    private static void addNewDish(Dish dish) {
        menu.add(dish);
    }


    public static void addNewDishWithCustomID(Dish dish) {
        menu.add(dish.getDishID() - 1, dish);
        fixMenuNumbers();
        Ledger.writeArrayToFile(getMenuAsArrayOfStrings(), MENU_FILE);
    }

    //Adding new dish if it does not already exist, then adding it to menu and to services.Ledger
    public static void addNewDishToMenu(Dish dish) {
        dish.setDishID(getDishID());
        for (Dish d : menu) {
            if (d.getDishID() == dish.getDishID()) {
                System.out.println("Id already exists");
                return;
            }
            if (d.getName().equalsIgnoreCase(dish.getName().toLowerCase())) {
                System.out.println("Name already exists");
                return;
            }
        }
        addNewDish(dish);
        Ledger.writeLineToFile(dish.addToFile(), MENU_FILE);
    }

    //Removing dish with given dishID in menu and services.Ledger
    public static void removeDish(int dishID) {
        for (Dish d : menu) {
            if (d.getDishID() == dishID) {
                menu.remove(d);
                fixMenuNumbers();
                Ledger.writeArrayToFile(getMenuAsArrayOfStrings(), MENU_FILE);
                return;
            }
        }
        System.out.println("ID findes ikke");
    }

    //Returning a dish given an ID
    public static Dish getDishFromID(int dishID) {
        for (Dish d : menu) {
            if (d.getDishID() == dishID) {
                return d;
            }
        }
        System.out.println("ID findes ikke");
        return null;
    }

    //getter
    public static ArrayList<Dish> getMenu() {
        return menu;
    }

    //Sorting menu, using the compareTo() methods in Dish
    public static void sort() {
        Collections.sort(menu);
    }

    private static ArrayList<String> getMenuAsArrayOfStrings() {
        ArrayList<String> lines = new ArrayList<>();
        for (Dish d : menu) {
            lines.add(d.addToFile());
        }
        return lines;
    }

    private static void fixMenuNumbers() {
        int id = 1;
        for (Dish d : Menu.getMenu()) {
            d.setDishID(id);
            id++;
        }

    }

    private static int getDishID() {
        for (Dish d : menu) {
            int idCheck = d.getDishID();
            if (idCheck > dishID) {
                dishID = idCheck;
            }
        }
        dishID++;
        return dishID;
    }

}
