import DishLogic.DishDescription;
import DishLogic.Menu;
import DishLogic.Order;

import java.util.ArrayList;
import java.util.Scanner;
public class Controller {

    /*TODO:
    - showActiveOrders()
    - newOrder()
    - deleteOrder()
    - finishOrder()
    */

    private Scanner scanner;
    private ArrayList<DishDescription> menu;

    public Controller(Scanner scanner){
        this.scanner = scanner;

    }

    //Looks in console for aa valid int
    public int getUserInput(int choiceBoundary){
        return getUserInput(choiceBoundary, 1);
    }

    //Overloading to allow for more choice in boundaries
    public int getUserInput(int choiceUpperBoundary, int choiceLowerBoundary){
        int userInput;

        do {
            while (!this.scanner.hasNextInt()) {

                this.scanner.next();

            }
            userInput = this.scanner.nextInt();
            this.scanner.nextLine();
        }while (userInput > choiceUpperBoundary || userInput < choiceLowerBoundary);

        return userInput;
    }


    public void newOrder(int hour,int minute){
        menu = Menu.getMenu();
        Order order = new Order(hour, minute);
        int userChoice;
        do{
            System.out.println("Skriv rettens nummer");
            int dishID = getUserInput(menu.size());
            order.addDish(menu.get(dishID-1), 2);
            System.out.println("Odre:\n" + order);
            System.out.println("1. Afslut\n2. Tilføj en ret mere");
            userChoice = getUserInput(2);
        }while (userChoice == 2);
    }

}
