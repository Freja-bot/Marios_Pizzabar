package DishLogic;

public class UniqueID {
    private static int orderID;
    private static int dishID;

    public static int getOrderID(){
        for (Order o : ActiveOrders.getOrders()) {
            int idCheck = o.getOrderID();
            if (idCheck > orderID) {
                orderID = idCheck;
            }
        }
        orderID++;
        return orderID;
    }

    public static int getDishID(){
        for (DishDescription d : Menu.getMenu()) {
            int idCheck = d.getDishID();
            if (idCheck > dishID) {
                dishID = idCheck;
            }
        }
        dishID++;
        return dishID;
    }

    public static void fixMenuNumbers(String file){
        int id = 1;
        for(DishDescription d:Menu.getMenu()){
            d.setDishID(id);
            id++;
        }
        Ledger.rewriteFileWithoutMissingID(Menu.getMenu(),file);
    }
}
