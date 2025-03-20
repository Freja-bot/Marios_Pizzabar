import java.util.Scanner;
public class Controller {

    /*TODO:
    - showActiveOrders()
    - newOrder()
    - deleteOrder()
    - finishOrder()
    */

    private Scanner scanner;


    public Controller(Scanner scanner){
        this.scanner = scanner;

    }

    //Looks in console for aa valid int
    public int getUserInput(int choiceBoundary){
        int userInput;
        System.out.println("print activeOrders next to menu\n1. Tilføj odre\n2. Luk program\nVælg en mulighed ve at skrive et tal");

        do {
            userInput = this.scanner.nextInt();
            this.scanner.nextLine();

            /*in the while condition the first condition ensures an int was captured.
            Because of the logic operator or, the other condition are only checked
            if an int was captured ensuring no exceptions are trown*/
        } while (!this.scanner.hasNextInt() || userInput > choiceBoundary || userInput < 1);


        return userInput;
    }

    //Overloading to allow for more choice in boundaries
    public int getUserInput(int choiceUpperBoundary, int choiceLowerBoundary){
        int userInput;
        System.out.println("print activeOrders next to menu\n1. Tilføj odre\n2. Luk program\nVælg en mulighed ve at skrive et tal");
        do {
            do {
                userInput = this.scanner.nextInt();
                this.scanner.nextLine();
            } while (!this.scanner.hasNextInt());
        }while (userInput > choiceUpperBoundary || userInput < choiceLowerBoundary);

        return userInput;
    }


    public void newOrder(int hour,int minute){
        Order oder = new Order(hour, minute);
        int userChoice;
        do{
            oder.addDish(1, 2);
            userChoice = getUserInput(2);
        }while (userChoice == 2);
    }

}
