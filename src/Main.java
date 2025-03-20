import DishLogic.DishDescription;
import DishLogic.Ledger;
import DishLogic.Menu;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1 - new file, 2 - existing file");
        int fileChoice = sc.nextInt();
        sc.nextLine();
        System.out.println("enter filename");
        String fileName = sc.nextLine();
        if (fileChoice == 1) {
            Ledger.createFile(fileName);
        }
        if (fileChoice == 2) {
            Menu.loadMenuFromFile(fileName);
        }
        /*DishDescription dd = new DishDescription(1, "Pizza", 599);
        DishDescription vin = new DishDescription(2, "Vin", 99);
        DishDescription de = new DishDescription(3,"vand",49.99);*/

        while (true) {
            System.out.println("1 - show menu, 2 - remove dish, 3 add dish, 4 sort menu");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                Menu.showMenu();
            }
            if (choice == 2) {
                System.out.println("enter id to remove");
                int remove = sc.nextInt();
                sc.nextLine();
                Menu.removeDish(remove, fileName);
            }
            if (choice == 3) {
                System.out.println("enter id");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.println("enter name");
                String name = sc.nextLine();
                System.out.println("enter price");
                double price = sc.nextDouble();
                sc.nextLine();
                Menu.addNewDishToMenu(new DishDescription(id, name, price), fileName);
            }
            if(choice==4){
                Menu.sort(fileName);
            }
        }
    }

    public static void startProgram(Scanner scanner){
        final int choiceBoundary = 2;
        int userChoice;
        boolean isrunning = true;
        Controller controller = new Controller(scanner, choiceBoundary);

        while (isrunning) {

            userChoice = controller.getUserInput(scanner);
            System.out.println(userChoice);

            switch (userChoice) {
                case 1: {
                    System.out.println("Tilføjer ordre");
                    break;
                }
                case 2: {
                    System.out.println("Lukker program");
                    isrunning = false;
                    break;
                }
                default: {
                    System.out.println("Error");
                    break;
                }
            }
        }

        scanner.close();
    }

}
