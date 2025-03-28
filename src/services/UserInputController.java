package services;

import models.Dish;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInputController {

    //variables
    private static UserInputController userInputController = null;
    private Scanner scanner;
    private ArrayList<Dish> menu;


    //private constructor is used to control how many exists in this program
    private UserInputController() {
        this.scanner = new Scanner(System.in);

    }

    //getInstance gives and if needed creates an instance of userInputController, this is to ensure there is only one
    public static UserInputController getInstance() {
        if (userInputController == null) {
            userInputController = new UserInputController();
        }
        return userInputController;
    }

    //
    public Scanner getScanner() {
        return scanner;
    }

    //Looks in console for a valid int
    public int getUserInput(int choiceBoundary) {

        return getUserInput(choiceBoundary, 1);
    }

    //Overloading to allow for more choice in boundaries
    public int getUserInput(int choiceUpperBoundary, int choiceLowerBoundary) {
        int userInput;

        do {
            //While loop skips every token (non-number input) until there is a number,
            //then the loop ends and that number is saved in userInput
            while (!this.scanner.hasNextInt()) {

                this.scanner.next();

            }
            userInput = this.scanner.nextInt();
            this.scanner.nextLine();
        } while (userInput > choiceUpperBoundary || userInput < choiceLowerBoundary);

        return userInput;
    }


    public double getUserInputAsDouble() {
        double userInput;

        do {
            //While loop skips every token (non-number input) until there is a number,
            //then the loop ends and that number is saved in userInput
            while (!this.scanner.hasNextDouble()) {

                this.scanner.next();

            }
            userInput = this.scanner.nextDouble();
            this.scanner.nextLine();
        } while (userInput < 0);

        return userInput;

    }
        public String getNonEmptyString() {
            String input;
            while (true) {
                input = scanner.nextLine();
                if (input.isEmpty()||input.contains(":")||input.contains("/")) {
                    continue;
                }
                break;
            }
            return input;

        }

    }

