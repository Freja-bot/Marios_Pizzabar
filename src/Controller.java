import DishLogic.DishDescription;
import DishLogic.Menu;
import DishLogic.Order;

import java.util.ArrayList;
import java.util.Scanner;
public class Controller {

    /*TODO:
    - getUniqueID();
    */
    private static Controller controller = null;
    private Scanner scanner;
    private ArrayList<DishDescription> menu;

    private Controller(){
        this.scanner = new Scanner(System.in);

    }

    public static Controller getInstance(){
        if(controller == null){
            controller = new Controller();
        }
        return controller;
    }

    public Scanner getScanner(){
        return scanner;
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

}
