package automation_test.php_travel;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGWebDriver {
    //Expected Title - Demo Script Test drive - PHPTRAVELS
    WebDriver driver;
    @BeforeMethod
    public void initiateBrowser(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.get("https://phptravels.com/demo");
        ActOn.browserActions(driver).openBrowser("https://phptravels.com/demo");

    }
    @Test
    public void verifyHomePageTitle(){

        String expectedTitle = "Demo Script Test drive - PHPTRAVELS";
        String actualTitle = ActOn.browserActions(driver).captureTitle();
        Assert.assertEquals(actualTitle,expectedTitle);

    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
        ActOn.browserActions(driver).close();

    }
}
