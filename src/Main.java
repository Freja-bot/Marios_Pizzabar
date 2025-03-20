import DishLogic.DishDescription;
import DishLogic.Menu;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Menu.loadMenuFromFile("PizzaMenu.txt");
        /*DishDescription dd = new DishDescription(1, "Pizza", 599);
        DishDescription vin = new DishDescription(2, "Vin", 99);
        DishDescription de = new DishDescription(3,"vand",49.99);*/
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1 - show menu, 2 - remove dish, 3 add dish");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                Menu.showMenu();
            }
            if(choice==2){
                System.out.println("enter id to remove");
                int remove = sc.nextInt();
                sc.nextLine();
                Menu.removeDish(remove,"PizzaMenu.txt");
            }
            if(choice==3){
                System.out.println("enter id");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.println("enter name");
                String name = sc.nextLine();
                System.out.println("enter price");
                double price = sc.nextDouble();
                Menu.addNewDishToMenu(new DishDescription(id,name,price),"PizzaMenu.txt");
            }
        }
    }
}

