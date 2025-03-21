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
            System.out.println("1 - show menu, 2 - remove dish, 3 add dish, 4 sort menu, 5 start program");
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
            if(choice == 5){
                startProgram(sc);
            }

        }
    }

    public static void startProgram(Scanner scanner){
        int choiceAmount = 2;
        int userChoice;
        boolean isrunning = true;
        Controller controller = new Controller(scanner);

        while (isrunning) {

            System.out.println("print activeOrders next to menu\n1. Tilføj odre\n2. Luk program\nVælg en mulighed ved at skrive et tal");
            userChoice = controller.getUserInput(choiceAmount);
            System.out.println(userChoice);

            switch (userChoice) {
                case 1: {
                    System.out.println("Indtast afhentingstidspunktet\nTime:");
                    int hour = controller.getUserInput(23,0);
                    System.out.println("Minut:");
                    int minute = controller.getUserInput(59,0);
                    controller.newOrder(hour, minute);

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

    }

}
