package coffeeMachine;

import coffeeMachine.exceptions.IngredientNotPresent;
import coffeeMachine.exceptions.InsufficientIngredient;

import java.util.*;

/** It takes care of showing the current inventory
 * and also provides method to add more inventory
 */
public class InventoryManager {
    private static Map<String, Integer> inventorMap = null;

    InventoryManager()
    {
        inventorMap = Collections.synchronizedMap(new HashMap<>());
    }

    public void addIngredients(String ingredientName, Integer quantity)
    {
        inventorMap.put(ingredientName, quantity);
    }

    public static void consumeIngredients(Map<Ingredient, Integer> ingredientMap) throws IngredientNotPresent, InsufficientIngredient {
        synchronized (inventorMap)
        {
            Iterator<Map.Entry<Ingredient, Integer>> itr1 = ingredientMap.entrySet().iterator();
            while (itr1.hasNext() == true)
            {
                Map.Entry<Ingredient, Integer> pair = itr1.next();
                Ingredient ingredient = pair.getKey();
                Integer quantity = pair.getValue();
                if(inventorMap.containsKey(ingredient.getName()) == false)
                {
                    throw new IngredientNotPresent("Ingredient not present " + ingredient.getName());
                }
                Integer maxQuantity = inventorMap.get(ingredient.getName());
                if (maxQuantity < quantity)
                {
                    throw new InsufficientIngredient("Insufficient ingredient "+ ingredient.getName()+ " for making beverage ");
                }
            }
            Iterator<Map.Entry<Ingredient, Integer>> itr2 = ingredientMap.entrySet().iterator();

            while (itr2.hasNext() == true)
            {
                Map.Entry<Ingredient, Integer> pair = itr2.next();
                Ingredient ingredient = pair.getKey();
                Integer quantity = pair.getValue();
                String ingredientName = ingredient.getName();
                Integer maxQuantity = inventorMap.get(ingredientName);
                maxQuantity -=quantity;
                inventorMap.put(ingredientName, maxQuantity);
            }
        }
    }

    public void showInventory()
    {
        Iterator<Map.Entry<String, Integer>> itr1 = inventorMap.entrySet().iterator();
        while (itr1.hasNext() == true)
        {
            Map.Entry<String, Integer> pair = itr1.next();
            String ingredient = pair.getKey();
            Integer quantity = pair.getValue();

            System.out.println(ingredient+ "  " + quantity);
        }
    }

    public Map<String, Integer> getInventory()
    {
        return inventorMap;

    }

    public void refillInventory(HashMap<String, Integer> refillMap)
    {
        Iterator<Map.Entry<String, Integer>> itr1 = refillMap.entrySet().iterator();

        while (itr1.hasNext() == true)
        {
            Map.Entry<String, Integer> pair = itr1.next();
            String ingredient = pair.getKey();
            Integer quantity = pair.getValue();
            if (inventorMap.containsKey(ingredient) == true)
            {
                Integer initialQuantity = inventorMap.get(ingredient);
                inventorMap.put(ingredient, initialQuantity + quantity);
            }
            else
            {
                inventorMap.put(ingredient, quantity);
            }
        }
    }
}
