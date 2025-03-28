package services;

import models.Dish;
import models.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger { //change name to LEDGER?

    //TODO
    //save to statistics
    private Ledger(){


    //Adding a dish to a file

    }


    public static void addDishToFile(Dish dish, String fileName) {
        writeLineToFile(dish.addToFile(), fileName);
    }

    //Returns a list of strings representing data from a file
    public static ArrayList<String> getFileAsArrayListOfStrings(String fileName) {
        ArrayList<String> strings = new ArrayList<>();
        try {
            //Instantiating a File object (NOT THE FILE ITSELF), that contains information about the file
            File myFile = new File(fileName);
            createFile(fileName, myFile);
            //Instantiating a scanner that scans a file
            Scanner reader = new Scanner(myFile);
            //Goes though file, and adds every line to array, until there are no lines in file
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                strings.add(data);
            }
        } catch (IOException e) {
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


    //creates new menu file where a specific dish is removed
    public static void rewriteFileWithoutMissingID(ArrayList<Dish> dishes, String fileName) {

        try {
            FileWriter writer = new FileWriter(fileName);
            for (Dish d : dishes) {
                writer.write(d.addToFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
    }

    //Goes through active orders file and finds a specific order, then removes it
    public static void removeOrderFromActiveOrders(ArrayList<Order> orders, String fileName){
        try {
            FileWriter writer = new FileWriter(fileName);
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
    public static void addOrderToFile(Order order, String fileName) {
        writeLineToFile(order.addToFile(), fileName);
    }

    //Takes a sentence and adds it to a given file
    private static void writeLineToFile(String line, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!!!");
            e.printStackTrace();
        }
    }

    //saves Order in statistics file
    public static void saveForStatistics(Order order, String fileName) {
        writeLineToFile(order.addToStatistics(), fileName);
    }

}
