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
                System.out.println("ingredient name "+ ingredient.getName()+" max "+ maxQuantity);
                System.out.println("ingredient name "+ ingredient.getName()+" qunatity "+ quantity);

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
}
