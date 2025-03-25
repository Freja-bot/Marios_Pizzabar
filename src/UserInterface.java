import DishLogic.*;

import java.util.Scanner;

public class UserInterface {

    private static Scanner scanner = new Scanner(System.in);
    private static Controller controller = new Controller(scanner);
    private final static String MENU_FILE = "PizzaMenu.txt";
    private final static String ACTIVE_ORDERS = "ActiveOrders.txt";

    public static void startProgram() {
        Menu.loadMenuFromFile(MENU_FILE);
        ActiveOrders.loadActiveOrders(ACTIVE_ORDERS);
        menuInterface();
    }

    public static void menuInterface() {

        Controller controller = new Controller(scanner);
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("0 - Exit, 1 - Se menuen, 2 - Fjern en ret, 3 - Tilføj en ret, 4 - Tilføj bestilling, 5 - Fjern en færdiggjort bestilling, 6 - Fortryd en bestilling, 7 - Se bestillinger");
            int userChoice = controller.getUserInput(5, 0);
            System.out.println(userChoice);

            switch (userChoice) {
                case 1: {
                    Menu.showMenu();
                    break;
                }
                case 2: {
                    System.out.println("Enter ID to remove");
                    int remove = scanner.nextInt();
                    scanner.nextLine();
                    Menu.removeDish(remove, MENU_FILE);
                    Menu.sort(MENU_FILE);
                    break;
                }
                case 3: {
                    System.out.println("enter id");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("enter name");
                    String name = scanner.nextLine();
                    System.out.println("enter price");
                    double price = scanner.nextDouble();
                    scanner.nextLine();
                    Menu.addNewDishToMenu(new DishDescription(id, name, price), MENU_FILE);
                    Menu.sort(MENU_FILE);
                    break;
                }
                case 4: {
                    createNewOrder();
                    break;
                }
                case 5: {
                    System.out.println("Indtast OrderID");
                    int orderID = controller.getUserInput(50000, 0);
                    ActiveOrders.finishOrder(orderID,ACTIVE_ORDERS,"Staticstics.txt");
                    break;
                }
                case 6: {

                    break;
                }
                case 7:{
                    ActiveOrders.showOrders();
                    break;
                }
                default: {
                    isRunning = false;
                    break;
                }
            }
        }
    }

    public static void createNewOrder() {
        System.out.println("Indtast afhentingstidspunktet\nTime:");
        int hour = controller.getUserInput(23, 0);
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
                    order.addDish(Menu.getMenu().get(dishID - 1), quantity);
                    System.out.println("1 - Afslut\n2 - Tilføj en ret mere");
                    break;
                }
                default: {
                    System.out.println("ERROR");
                    break;
                }
            }
            if (isRunning) {
                userChoice = controller.getUserInput(2);
            }
        }
    }
}

