import DishLogic.DishDescription;
import DishLogic.Ledger;
import DishLogic.Menu;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        startProgram(sc);
    }


    public static void startProgram(Scanner scanner) {
        int choiceAmount = 2;
        int userChoice;
        boolean isrunning = true;
        Controller controller = new Controller(scanner);

        while (isrunning) {
            System.out.println("print activeOrders next to menu\n1. Tilføj odre\n2. Luk program\nVælg en mulighed ve at skrive et tal");
            userChoice = controller.getUserInput(choiceAmount);
            System.out.println(userChoice);

            switch (userChoice) {
                case 1: {
                    System.out.println("Indtast afhentingstidspunktet\nTime:");
                    int hour = controller.getUserInput(23, 0);
                    System.out.println(hour);
                    System.out.println("Minut:");
                    int minute = controller.getUserInput(59);
                    //controller.newOrder(hour, minute);
                    System.out.println(hour);
                    System.out.println(minute);
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

