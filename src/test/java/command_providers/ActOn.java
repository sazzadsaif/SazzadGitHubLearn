package command_providers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ActOn {
    public static BrowserActions browserActions(WebDriver driver){
        return new BrowserActions(driver);
    }
    public static ElementActions elementActions(WebDriver driver, By locator){
        return new ElementActions(driver,locator);
    }
    public static WaitFor waitFor(WebDriver driver, By locator){
        return new WaitFor(driver,locator);
    }
}
