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
                    int toRemove = dishList.indexOf(d);
                    dishList.remove(toRemove);
                    break;
                }
            }

            for (int i = 0; i < dishList.size(); i++) {
                writeLineToFile(dishList.get(i).addToFile(),file);
            }
        } catch (Exception e) {
            System.out.println("ERROR!!!");
            e.printStackTrace();
        }

    }

    public static void addDishToFile(DishDescription dish, String file) {
        writeLineToFile(dish.addToFile(), file);
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
                writer.write(d.addToFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("ERROR!!");
            e.printStackTrace();
        }
    }

    public static void addOrderToFile(Order order, String file) {
        writeLineToFile(order.toString(), file);
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

    private static void removeLineFromFile(ArrayList<String> lines,String file){

    }

    public static void showActiveOrders(String file) {

    }

    public static void removeOrderFromFile(String file) {

    }
}
