package DishLogic;

import java.awt.*;
import java.util.ArrayList;

public class UniqueID {
    public static int orderID = 0;

    private UniqueID(){

    }

    public static int getOrderID() {
        //Vi skal lave et loop som gå igennem alle bestillinger og finder det højeste nummer og lægger 1 til.
        //Derpå får man et unikt ID. For/ Each loop.
        ArrayList<Order> Orders = ActiveOrders.getOrders();

        if(Orders.isEmpty()){
            orderID=1;
        } else {

        Order highestOrder = Orders.get(0);
        for(Order activeOrder: Orders)

        {
            if(highestOrder.getOrderID()<activeOrder.getOrderID()){
            highestOrder = activeOrder;}

        }

        orderID = highestOrder.getOrderID()+1;}

        return orderID;
    }



}

