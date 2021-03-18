package coffeeMachine.exceptions;

public class InsufficientIngredient extends Exception {
    public InsufficientIngredient(String errorMessage)
    {
        super(errorMessage);
    }
}
