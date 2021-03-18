package coffeeMachine;

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

        System.out.println(beverage.getName()+ " Beverage being prepared");
        InventoryManager.consumeIngredients(br.getIngredientQuantityMap());
        System.out.println(beverage.getName()+ " Beverage prepared");

    }
}
