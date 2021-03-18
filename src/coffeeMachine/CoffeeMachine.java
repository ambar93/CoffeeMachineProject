package coffeeMachine;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoffeeMachine {
    Integer outlets;
    InventoryManager inventoryManager;
    Processor processor;
    Menu menu;

   public CoffeeMachine(Integer outlets)
    {
        this.outlets = outlets;
        this.inventoryManager = new InventoryManager();
        this.menu = new Menu();
        this.processor = null;
    }

    public void addBeverage(Beverage b)
    {
        menu.addNewBeverage(b);
    }

    public Menu getMenu()
    {
        return this.menu;
    }

    public void prepareBeverage()
    {
        ExecutorService executor = Executors.newFixedThreadPool(outlets);
        Iterator<Beverage> itr = menu.getBeverageList().iterator();
        while (itr.hasNext())
        {
            Beverage b = itr.next();
            Runnable processor = new Processor(b);
            executor.execute(processor);
        }

        executor.shutdown();
    }
}
