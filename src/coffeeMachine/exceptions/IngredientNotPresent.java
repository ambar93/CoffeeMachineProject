package coffeeMachine.exceptions;

public class IngredientNotPresent extends Exception{
    public IngredientNotPresent(String errorMessage)
    {
        super(errorMessage);
    }
}
