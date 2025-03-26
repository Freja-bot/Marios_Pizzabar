package DishLogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {

    //TODO
    //save to statistics


    public static void addDishToFile(DishDescription dish, String file) {
        writeLineToFile(dish.addToFile(), file);
    }

    public static ArrayList<String> getFileAsArrayListOfStrings(String file) {
        ArrayList<String> strings = new ArrayList<>();
        try {
            File myFile = new File(file);
            createFile(file, myFile);
            Scanner reader = new Scanner(myFile);
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

    public static void createFile(String fileName, File newFile) {
        try {
            if (newFile.createNewFile()) {
                System.out.println("Der er ikke nogen fil der hedder " + fileName + ", opretter en tom fil" + newFile.getName());
            } else {
                System.out.println("File already exists");
            }

        } catch (IOException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
    }

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

    public static void addOrderToFile(Order order, String file) {
        writeLineToFile(order.addToFile(), file);
    }

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

    public static void saveForStatistics(Order order, String file) {
        writeLineToFile(order.addToFile(), file);
    }

}
