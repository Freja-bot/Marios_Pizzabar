import models.*;
import services.ActiveOrders;
import services.Menu;
import services.UniqueID;
import services.UserInputController;

import java.util.Scanner;

public class UserInterface {

    //variables
    private static UserInputController userInputController = UserInputController.getInstance();
    private static Scanner scanner = userInputController.getScanner();
    private final static String MENU_FILE = "PizzaMenu.txt";
    private final static String ACTIVE_ORDERS = "ActiveOrders.txt";
    private final static String STATISTICS = "statistics.txt";

    //Pre-program tasks are completed
    public static void startProgram() {
        Menu.loadMenuFromFile(MENU_FILE);
        ActiveOrders.loadActiveOrders(ACTIVE_ORDERS);
        menuInterface();
    }

    //This method controls the menu in the console
    public static void menuInterface() {


        //a while loop runs the entire program logic, until the user chooses exit,
        //where after the program exits the loop and ends
        boolean isRunning = true;
        while (isRunning) {
            int dishAmount = Menu.getMenu().size();
            System.out.println("MENU-KORT");
            Menu.showMenu();
            System.out.println("\nAKTIVE BESTILLINGER");
            ActiveOrders.showOrders();
            System.out.println("0 - Exit, 1 - Tilføj bestilling, 2 - Fjern en færdiggjort bestilling, 3 - Fortryd en bestilling, 4 - services.Menu-indstillinger");
            int userChoice = userInputController.getUserInput(4, 0);

            //a switch is like many if statements asking,
            //if userChoice (a number) is equal to number besides case ... :{
            switch (userChoice) {

                case 1: {
                    if (dishAmount == 0) {
                        System.out.println("MENUKORTET ER TOMT !\n");
                        break;
                    }
                    createNewOrder(dishAmount);
                    break;
                }


                case 2: {
                    System.out.println("Indtast OrderID");
                    int orderID = userInputController.getUserInput(50000, 0);
                    ActiveOrders.finishOrder(orderID, ACTIVE_ORDERS, STATISTICS);
                    break;
                }
                case 3: {
                    System.out.println("Tast 0 for at fortyde - Indtast OrderID");
                    int orderID = userInputController.getUserInput(50000, 0);
                    ActiveOrders.cancelOrder(orderID, ACTIVE_ORDERS);
                    break;
                }
                case 4: {
                    menuSettings();
                    break;
                }
                default: {
                    isRunning = false;
                    break;
                }
            }
        }
    }


    //has its own while and switch to determine if the user wishes one or multiple dishes.

    public static void createNewOrder(int dishAmount) {
        System.out.println("Tast -1 for at fortyde - Indtast afhentingstidspunktet\nIndtast Timetal");
        int hour = userInputController.getUserInput(23, -1);
        if (hour == -1) {
            return;
        }
        System.out.println("Indtast minuttal");
        int minute = userInputController.getUserInput(59, 0);
        if(minute==-1){
            return;
        }
        Order order = new Order(hour, minute);
        int userChoice = 2;
        boolean isRunning = true;
        while (isRunning) {

            switch (userChoice) {
                case 1: {
                    ActiveOrders.addNewOrderToFile(order, ACTIVE_ORDERS);
                    isRunning = false;
                    break;
                }
                case 2: {
                    System.out.println("Indtast rettens nummer");
                    int dishID = userInputController.getUserInput(dishAmount);
                    System.out.println("Indtast antal af denne ret");
                    int quantity = userInputController.getUserInput(20, 0);
                    order.addDish(Menu.getDishFromID(dishID), quantity);
                    break;
                }
                default: {
                    System.out.println("ERROR");
                    break;
                }
            }
            if (isRunning) {
                System.out.println("1 - Afslut bestilling\n2 - Tilføj en ret mere");
                userChoice = userInputController.getUserInput(2);
            }
        }
    }

    //uses same trick to display a menu over pizzaMenu options
    public static void menuSettings() {
        if (!ActiveOrders.getOrders().isEmpty()) {
            System.out.println("Fjern alle aktive ordre før du ændre i menuen");
            return;
        }
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("0 - for at gå tilbage, 1 - for at indsætte en ny ret på en anden rets plads, 2 - for at tilføje en ny ret til menuen, 3 - for at fjerne en ret fra menuen");
            int userChoice = userInputController.getUserInput(3, 0);
            switch (userChoice) {
                case 1: {
                    System.out.println("Tast 0 for at fortyde - Indtast id");
                    int id = userInputController.getUserInput(500, 0);
                    if (id == 0 || id > Menu.getMenu().size()) {
                        break;
                    }
                    System.out.println("Indtast navn");
                    String name = userInputController.getNonEmptyString();
                    System.out.println("Indtast beskrivelse");
                    String description = userInputController.getNonEmptyString();
                    System.out.println("Indtast pris");
                    double price = userInputController.getUserInputAsDouble();
                    Menu.addNewDishWithCustomID(new Dish(id, name, description, price), MENU_FILE);
                    break;
                }
                case 2: {
                    System.out.println("Indtast navn");
                    String name = userInputController.getNonEmptyString();
                    System.out.println("Indtast beskrivelse");
                    String description = userInputController.getNonEmptyString();
                    System.out.println("Indtast pris");
                    double price = userInputController.getUserInputAsDouble();
                    Menu.addNewDishToMenu(new Dish(UniqueID.getDishID(), name, description, price), MENU_FILE);
                    break;
                }
                case 3: {
                    System.out.println("Tast 0 for at fortyde - Indtast ID for at fjerne ret fra menuen");
                    int remove = userInputController.getUserInput(500);
                    if (remove == 0) {
                        break;
                    }
                    Menu.removeDish(remove, MENU_FILE);
                    break;
                }
                default: {
                    isRunning = false;
                    break;
                }
            }
        }
    }

}

