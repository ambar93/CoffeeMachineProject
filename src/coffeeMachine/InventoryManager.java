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
        // multiple threads will update this map
        inventorMap = Collections.synchronizedMap(new HashMap<>());
    }

    public void addIngredients(String ingredientName, Integer quantity)
    {
        inventorMap.put(ingredientName, quantity);
    }
}
