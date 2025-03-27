import DishLogic.*;

import java.util.Scanner;

public class UserInterface {

    //variables
    private static Controller controller = Controller.getInstance();
    private static Scanner scanner = controller.getScanner();
    private final static String MENU_FILE = "PizzaMenu.txt";
    private final static String ACTIVE_ORDERS = "ActiveOrders.txt";

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
            System.out.println("MENU-KORT");
            Menu.showMenu();
            System.out.println("\nAKTIVE BESTILLINGER");
            ActiveOrders.showOrders();
            System.out.println("0 - Exit, 1 - Tilføj bestilling, 2 - Fjern en færdiggjort bestilling, 3 - Fortryd en bestilling, 4 - Menu-indstillinger");
            int userChoice = controller.getUserInput(4, 0);
            System.out.println("Programmet modtog: " + userChoice);

            //a switch is like many if statements asking,
            //if userChoice (a number) is equal to number besides case ... :{
            switch (userChoice) {

                case 1: {
                    createNewOrder();
                    break;
                }
                case 2: {
                    System.out.println("Indtast OrderID");
                    int orderID = controller.getUserInput(50000, 0);
                    ActiveOrders.finishOrder(orderID, ACTIVE_ORDERS, "Staticstics.txt");
                    break;
                }
                case 3: {
                    System.out.println("Indtast OrderID");
                    int orderID = controller.getUserInput(50000, 0);
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
    public static void createNewOrder() {
        System.out.println("Type -1 to cancel - Indtast afhentingstidspunktet\nTime:");
        int hour = controller.getUserInput(23, -1);
        if(hour==-1){
            return;
        }
        System.out.println("Minut:");
        int minute = controller.getUserInput(59, 0);
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
                    System.out.println("Skriv rettens nummer");
                    int dishID = controller.getUserInput(Menu.getMenu().size());
                    System.out.println("Antal?");
                    int quantity = controller.getUserInput(20);
                    order.addDish(Menu.getDishFromID(dishID), quantity);
                    break;
                }
                default: {
                    System.out.println("ERROR");
                    break;
                }
            }
            if (isRunning) {
                System.out.println("1 - Afslut\n2 - Tilføj en ret mere");
                userChoice = controller.getUserInput(2);
            }
        }
    }

    //uses same trick to display a menu over pizzaMenu options
    public static void menuSettings() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("0 - for at gå tilbage, 1 - for at tilføje en ny ret til menuen, 2 - for at fjerne en ret fra menuen");
            int userChoice = controller.getUserInput(2, 0);
            switch (userChoice) {
                case 1: {
                    System.out.println("enter id , type 0 to cancel");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if (id == 0) {
                        break;
                    }
                    System.out.println("enter name");
                    String name = scanner.nextLine();
                    System.out.println("enter description");
                    String description = scanner.nextLine();
                    System.out.println("enter price");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    Menu.addNewDishToMenu(new DishDescription(id, name,description, price), MENU_FILE);
                    break;
                }
                case 2: {
                    System.out.println("Enter ID to remove - type 0 to cancel");
                    int remove = scanner.nextInt();
                    scanner.nextLine();
                    if (remove == 0) {
                        break;
                    }
                    Menu.removeDish(remove, MENU_FILE);
                    break;
                }
                default: {
                    isRunning=false;
                    break;
                }
            }
        }
    }

}

