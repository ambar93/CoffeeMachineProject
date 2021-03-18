package coffeeMachine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoffeeMachine {
    Integer outlets;
    InventoryManager inventoryManager;
    Menu menu;

   public CoffeeMachine(Integer outlets)
    {
        this.outlets = outlets;
        this.inventoryManager = new InventoryManager();
        this.menu = new Menu();
    }

    public void addBeverage(Beverage b)
    {
        menu.addNewBeverage(b);
    }

    public Menu getMenu()
    {
        return this.menu;
    }
}
