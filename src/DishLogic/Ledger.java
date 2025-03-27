package DishLogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger { //change name to LEDGER?

    //TODO
    //save to statistics

    //Adding a dish to a file
    public static void addDishToFile(DishDescription dish, String file) {
        writeLineToFile(dish.addToFile(), file);
    }

    //Returns a list of strings representing data from a file
    public static ArrayList<String> getFileAsArrayListOfStrings(String file) {
        ArrayList<String> strings = new ArrayList<>();
        try {
            //Instantiating a File object (NOT THE FILE ITSELF), that contains information about the file
            File myFile = new File(file);
            createFile(file, myFile);
            //Instantiating a scanner that scans a file
            Scanner reader = new Scanner(myFile);
            //Goes though file, and adds every line to array, until there are no lines in file
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                strings.add(data);
            }
        } catch (Exception e) {
            System.out.println("ERROR!!!");
            e.printStackTrace();
        }
        return strings;
    }

    //Creates a new file, unless one already exists
    public static void createFile(String fileName, File newFile) {
        try {
            if (newFile.createNewFile()) {
                System.out.println("Der er ikke nogen fil der hedder " + fileName + ", opretter en tom fil" + newFile.getName());
            }

        } catch (IOException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
    }

    //Goes through menu file and finds a specific dish, then removes it
    public static void removeDishFromMenu(ArrayList<DishDescription> dishes, String file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (DishDescription d : dishes) {
                writer.write(d.addToFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
    }

    //Goes through active orders file and finds a specific order, then removes it
    public static void removeOrderFromActiveOrders(ArrayList<Order> orders, String file){
        try {
            FileWriter writer = new FileWriter(file);
            for (Order o : orders) {
                writer.write(o.addToFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
    }

    //Adding an order to its file (methods could be combined into one, using interfaces)
    public static void addOrderToFile(Order order, String file) {
        writeLineToFile(order.addToFile(), file);
    }

    //Takes a sentence and adds it to a given file
    private static void writeLineToFile(String line, String file) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!!!");
            e.printStackTrace();
        }
    }

    //saves Order in statistics file
    public static void saveForStatistics(Order order, String file) {
        writeLineToFile(order.addToFile(), file);
    }

}
