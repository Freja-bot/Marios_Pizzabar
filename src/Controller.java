import DishLogic.DishDescription;
import DishLogic.Menu;
import DishLogic.Order;

import java.util.ArrayList;
import java.util.Scanner;
public class Controller {

    /*TODO:
    - getUniqueID();
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

}
