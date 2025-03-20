import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        startProgram(scanner);

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
