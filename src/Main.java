import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        startProgram(scanner);

    }

    public static void startProgram(Scanner scanner){
        int choiceBoundary = 2;
        int userChoice;
        boolean isrunning = true;
        Controller controller = new Controller(scanner, choiceBoundary);

        while (isrunning) {

            userChoice = controller.getUserInput(choiceBoundary);
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

        scanner.close();
    }

}
