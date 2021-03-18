package test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestCoffeeMachineRunner {
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(TestCoffeeMachine.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

    }

}
