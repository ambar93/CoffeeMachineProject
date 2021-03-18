package coffeeMachine;

import coffeeMachine.exceptions.IngredientNotPresent;
import coffeeMachine.exceptions.InsufficientIngredient;

public class Processor implements Runnable {
    Beverage beverage;
    Processor(Beverage beverage)
    {
        this.beverage = beverage;
    }

    public void run()
    {
       // System.out.println(Thread.currentThread().getName()+ " " +"preparing " +beverage.getName());
        prepareBeverage();
    }
    public void prepareBeverage()
    {
        BeverageRecipe br = beverage.getRecipe();

       try
        {
            System.out.println(beverage.getName()+ " Beverage being prepared");
            InventoryManager.consumeIngredients(br.getIngredientQuantityMap());
            System.out.println(beverage.getName()+ " Beverage prepared");
        }
        catch (IngredientNotPresent e)
        {
            System.out.println("For Beverage " + beverage.getName() + " " + e.getMessage());
        }
        catch (InsufficientIngredient e)
        {
            System.out.println("For Beverage " + beverage.getName() + " " + e.getMessage());
        }

    }
}
