import java.util.Scanner;
public class Controller {
    private Scanner scanner;
    private final int choiceBoundary;

    public Controller(Scanner scanner, int choiceBoundary){
        this.scanner = scanner;
        this.choiceBoundary = choiceBoundary;
    }

    public int getUserInput(Scanner scanner){
        int userInput;
        System.out.println("print activeOrders next to menu\n1. Tilføj odre\n2. ting\nVælg en mulighed ve at skrive et tal");

        do{
            userInput = scanner.nextInt();
            scanner.nextLine();
        }while (userInput > choiceBoundary || userInput < 1);

        return userInput;
    }

}
