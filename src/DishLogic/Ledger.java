package DishLogic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {


    public static void removeDish(int dishID, String file) {
        ArrayList<DishDescription> dishList = new ArrayList<>();
        try {
            File myFile = new File(file);
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                DishDescription dish = new DishDescription(line);
                dishList.add(dish);
            }
            for (DishDescription d : dishList) {
                if (d.getDishID() == dishID) {
                    System.out.println("ye");
                    int toRemove = dishList.indexOf(d);
                    dishList.remove(toRemove);
                    break;
                }
            }
            FileWriter writer = new FileWriter(myFile);
            for (int i = 0; i < dishList.size(); i++) {
                writer.write(dishList.get(i).addToFile() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("ERROR!!!");
            e.printStackTrace();
        }

    }

    public static void addDishToFile(DishDescription dish, String file) {
        System.out.println("Write to file");

        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(dish.addToFile() + "\n");
            writer.close();
            System.out.println("yay");
        } catch (IOException e) {
            System.out.println("ERROR!!!");
            e.printStackTrace();
        }

    }

    public static void getMenuFromFile(String file) {

        try {
            File myFile = new File(file);
            Scanner reader = new Scanner(myFile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                DishDescription dish = new DishDescription(data);
                Menu.addNewDish(dish);
            }
        } catch (Exception e) {
            System.out.println("ERROR!!!");
            e.printStackTrace();
        }
    }

    public static void createFile(String file) {
        File newFile = new File(file);
        try {
            if (newFile.createNewFile()) {
                System.out.println("File create" + newFile.getName());
            } else {
                System.out.println("File already exists");
            }

        } catch (IOException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
    }

    public static void sortMenu(ArrayList<DishDescription> dishes, String file) {
        try {
            FileWriter writer = new FileWriter(file);
            for (DishDescription d : dishes) {
                writer.write(d.addToFile());
            }
        } catch (IOException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
    }
}
