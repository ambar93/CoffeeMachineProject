package coffeeMachine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeverageRecipe {
    Map<Ingredient, Integer> ingredientQuantityMap;

    public BeverageRecipe(Map<Ingredient, Integer> ingredientQuantityMap)
    {
        this.ingredientQuantityMap = ingredientQuantityMap;
    }

    public Map<Ingredient, Integer> getIngredientQuantityMap()
    {
        return this.ingredientQuantityMap;
    }

}
