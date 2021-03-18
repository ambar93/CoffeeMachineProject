package coffeeMachine;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    List<Beverage> beverageList;

    Menu()
    {
        beverageList = new ArrayList<>();
    }
    // can have additional features like cost of each beverage.


    public void addNewBeverage(Beverage b)
    {
        if (beverageList.contains(b) == false)
        {
            beverageList.add(b);
        }
    }

    public List<Beverage> getBeverageList()
    {
        return this.beverageList;
    }
}
