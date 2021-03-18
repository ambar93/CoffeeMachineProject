package coffeeMachine;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Driver {
    public static void main(String args[])
    {
        JSONParser jsonParser = new JSONParser();
        try
        {
            JSONObject obj = (JSONObject) jsonParser.parse(new FileReader("resources/input.json"));
            JSONObject machineObj = (JSONObject) obj.get("machine");
            JSONObject outletsObj = (JSONObject) machineObj.get("outlets");
            Long outlets = (Long) outletsObj.get("count_n");
            CoffeeMachine coffeeMachine = new CoffeeMachine(outlets.intValue());
            InventoryManager inventoryManager = new InventoryManager();
            Map<String, Long> totalItemsQuantityMap = (Map<String, Long> )machineObj.get("total_items_quantity");
            Iterator<Map.Entry<String, Long>> itr1 = totalItemsQuantityMap.entrySet().iterator();
            while(itr1.hasNext() == true)
            {
                Map.Entry<String,Long> pair = itr1.next();
                String IngredientName = pair.getKey();
                Integer quantity =  pair.getValue().intValue();
                inventoryManager.addIngredients(IngredientName, quantity);
            }
            JSONObject beveragesObj = (JSONObject) machineObj.get("beverages");
            Iterator iterator = beveragesObj.keySet().iterator();

            while (iterator.hasNext() == true)
            {
                String beverageName = (String) iterator.next();
                Map<String, Long> beverageRecipeMap  = (Map<String, Long>)beveragesObj.get(beverageName);
                Iterator<Map.Entry<String, Long>> beverageItr = beverageRecipeMap.entrySet().iterator();
                Map<Ingredient, Integer> ingredientMap = new HashMap<>();
                while(beverageItr.hasNext() == true)
                {
                    Map.Entry<String,Long> pair = beverageItr.next();
                    String ingredientName = pair.getKey();
                    Integer ingredientQuantity =  pair.getValue().intValue();
                    Ingredient ingredient = new Ingredient(ingredientName);
                    ingredientMap.put(ingredient, ingredientQuantity);
                }
                BeverageRecipe br = new BeverageRecipe(ingredientMap);

                Beverage b = new Beverage(beverageName, br);
                coffeeMachine.addBeverage(b);

            }
            coffeeMachine.prepareBeverage();
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getStackTrace());
        }
    }
}
