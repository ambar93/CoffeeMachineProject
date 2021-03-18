package coffeeMachine;

public class Beverage {
    String name;
    BeverageRecipe recipe;
    public Beverage(String name, BeverageRecipe recipe)
    {
        this.name = name;
        this.recipe = recipe;
    }

    public BeverageRecipe getRecipe()
    {
        return this.recipe;
    }

    public String getName()
    {
        return this.name;
    }
}
