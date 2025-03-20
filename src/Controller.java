import java.util.Scanner;

public class Controller {

    /*TODO:
    - showActiveOrders()
    - newOrder()
    - deleteOrder()
    - finishOrder()
    */

    private Scanner scanner;


    public Controller(Scanner scanner) {
        this.scanner = scanner;

    }

    //Looks in console for aa valid int
    public int getUserInput(int choiceBoundary) {
        return getUserInput(choiceBoundary, 1);
    }

    //Overloading to allow for more choice in boundaries
    public int getUserInput(int choiceUpperBoundary, int choiceLowerBoundary) {
        int userInput = choiceUpperBoundary + 1;

        do {
            if (!(this.scanner.hasNextInt())) {
                this.scanner.nextLine();
                continue;
            }
            userInput = this.scanner.nextInt();
            this.scanner.nextLine();

        } while (userInput > choiceUpperBoundary || userInput < choiceLowerBoundary);

        return userInput;
    }


    public void newOrder(int hour, int minute) {
        Order oder = new Order(hour, minute);
        int userChoice;
        do {
            oder.addDish(1, 2);
            userChoice = getUserInput(2);
        } while (userChoice == 2);
    }

}
