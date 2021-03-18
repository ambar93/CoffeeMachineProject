package test;


import coffeeMachine.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestCoffeeMachine extends TestCase {

    protected  CoffeeMachine coffeeMachine;
    @Before
    public void setUp()
    {
        coffeeMachine = new CoffeeMachine(4);
        InventoryManager inventoryManager = coffeeMachine.getInventoryManager();
        HashMap<String, Integer> inventoryMap = new HashMap<>();

        inventoryManager.addIngredients("hot_water", 5000);
        inventoryManager.addIngredients("ginger_syrup", 5000);
        inventoryManager.addIngredients("sugar_syrup", 5000);
        inventoryManager.addIngredients("tea_leaves_syrup", 5000);
        inventoryManager.addIngredients("hot_milk", 5000);

        Ingredient ingredient1 = new Ingredient("hot_water");
        Ingredient ingredient2 = new Ingredient("ginger_syrup");
        Ingredient ingredient3 = new Ingredient("sugar_syrup");
        Ingredient ingredient4 = new Ingredient("tea_leaves_syrup");
        Ingredient ingredient5 = new Ingredient("hot_milk");


        HashMap<Ingredient, Integer> beverageRecipeOne = new HashMap<>();
        beverageRecipeOne.put(ingredient1, 1000);
        beverageRecipeOne.put(ingredient3, 1000);
        BeverageRecipe br1 = new BeverageRecipe(beverageRecipeOne);
        Beverage b1 = new Beverage("green_tea", br1);

        HashMap<Ingredient, Integer> beverageRecipeTwo = new HashMap<>();
        beverageRecipeTwo.put(ingredient1, 2000);
        beverageRecipeTwo.put(ingredient2, 1000);
        beverageRecipeTwo.put(ingredient5, 1000);
        BeverageRecipe br2 = new BeverageRecipe(beverageRecipeTwo);
        Beverage b2 = new Beverage("hot_tea", br2);

        HashMap<Ingredient, Integer> beverageRecipeThree = new HashMap<>();
        beverageRecipeThree.put(ingredient1, 1000);
        beverageRecipeThree.put(ingredient4, 1000);
        BeverageRecipe br3 = new BeverageRecipe(beverageRecipeThree);
        Beverage b3 = new Beverage("black_coffee", br3);

        HashMap<Ingredient, Integer> beverageRecipeFour = new HashMap<>();
        beverageRecipeFour.put(ingredient4, 1000);
        BeverageRecipe br4 = new BeverageRecipe(beverageRecipeFour);
        Beverage b4 = new Beverage("black_tea", br4);

        Menu menu = coffeeMachine.getMenu();
        coffeeMachine.addBeverage(b1);
        coffeeMachine.addBeverage(b2);
        coffeeMachine.addBeverage(b3);
        coffeeMachine.addBeverage(b4);
    }

    @Test
    public void testCoffeeMachineSuccessForAllBeverages() throws InterruptedException {
        Map<String, Integer>  initialInventory = coffeeMachine.getInventoryManager().getInventory();
        assertEquals((Object) initialInventory.get("hot_water"), 5000);
        assertEquals((Object) initialInventory.get("ginger_syrup"), 5000);
        assertEquals((Object) initialInventory.get("sugar_syrup"), 5000);
        assertEquals((Object) initialInventory.get("tea_leaves_syrup"), 5000);
        assertEquals((Object) initialInventory.get("hot_milk"), 5000);

        coffeeMachine.prepareBeverage();

        Thread.sleep(5000);

        Map<String, Integer>  finalInventory = coffeeMachine.getInventoryManager().getInventory();

        assertEquals((Object) finalInventory.get("hot_water"), 1000);
        assertEquals((Object) finalInventory.get("ginger_syrup"), 4000);
        assertEquals((Object) finalInventory.get("sugar_syrup"), 4000);
        assertEquals((Object) finalInventory.get("tea_leaves_syrup"), 3000);
        assertEquals((Object) finalInventory.get("hot_milk"), 4000);

    }

    @Test
    public void testCoffeeMachineFailureDueToInsufficientIngredients() throws InterruptedException {
        Map<String, Integer>  initialInventory = coffeeMachine.getInventoryManager().getInventory();
        assertEquals((Object) initialInventory.get("hot_water"), 5000);
        assertEquals((Object) initialInventory.get("ginger_syrup"), 5000);
        assertEquals((Object) initialInventory.get("sugar_syrup"), 5000);
        assertEquals((Object) initialInventory.get("tea_leaves_syrup"), 5000);
        assertEquals((Object) initialInventory.get("hot_milk"), 5000);

        // This beverage will not be prepared due to insufficient ingredient
        Ingredient ingredient1 = new Ingredient("hot_water");
        Ingredient ingredient2 = new Ingredient("ginger_syrup");
        Ingredient ingredient3 = new Ingredient("sugar_syrup");
        HashMap<Ingredient, Integer> beverageRecipeFive = new HashMap<>();
        beverageRecipeFive.put(ingredient1, 400000);
        beverageRecipeFive.put(ingredient2, 400000);
        beverageRecipeFive.put(ingredient3, 400000);

        BeverageRecipe br5 = new BeverageRecipe(beverageRecipeFive);
        Beverage b5 = new Beverage("hot_ginger_tea", br5);

        coffeeMachine.addBeverage(b5);
        coffeeMachine.prepareBeverage();

        Thread.sleep(3000);

        Map<String, Integer>  finalInventory = coffeeMachine.getInventoryManager().getInventory();

        assertEquals((Object) finalInventory.get("hot_water"), 1000);
        assertEquals((Object) finalInventory.get("ginger_syrup"), 4000);
        assertEquals((Object) finalInventory.get("sugar_syrup"), 4000);
        assertEquals((Object) finalInventory.get("tea_leaves_syrup"), 3000);
        assertEquals((Object) finalInventory.get("hot_milk"), 4000);
    }

    public void tearDown() {
    }

}
