package automation_test.mortgage_calculator;

import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CalculateRealAprRate {
    private final By RateLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");
    private final By CalculatorTab = By.xpath("//label[text()='Calculator']");
    private final By HomePriceInputField = By.name("HomeValue");
    private final By DownPaymentInDollar = By.id("DownPaymentSel0");
    private final By DownPaymentInputField = By.name("DownPayment");
    private final By InterestRateInputField = By.name("Interest");
    private final By YearDuration = By.name("Length");
    private final By CalculateRateButton = By.name("calculate");
    private final By ActualAprRate = By.xpath("//*[@id='analysisDiv']/table/tbody/tr/td/strong[text()='Actual APR:']/../../td[2]/strong");

    WebDriver driver;
    Select select;

    @BeforeMethod
    public void browserInitialization(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver.get("https://www.mortgagecalculator.org/");
        //driver.manage().window().maximize();
        ActOn.browserActions(driver).openBrowser("https://www.mortgagecalculator.org/");
    }
    public void navigateToRealAprPage(){
        //Mouse Hover to Rates link
        //Actions actions = new Actions(driver);
        //actions.moveToElement(driver.findElement(RateLink)).perform();
        ActOn.elementActions(driver,RateLink).mouseHover();
        //Click on Real Apr Link
        driver.findElement(RealAprLink).click();
        ActOn.elementActions(driver,RealAprLink).click();
        // Wait for the Page to Load
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(CalculatorTab));
        ActOn.waitFor(driver,CalculatorTab).waitForElementToBeVisible();
    }
    public void enterData(){

        //driver.findElement(HomePriceInputField).clear();
        //driver.findElement(HomePriceInputField).sendKeys("200000");
        ActOn.elementActions(driver,HomePriceInputField).setValue("200000");

        //driver.findElement(DownPaymentInDollar).click();
        ActOn.elementActions(driver,DownPaymentInDollar).click();

        //driver.findElement(DownPaymentInputField).clear();
        //driver.findElement(DownPaymentInputField).sendKeys("15000");
        ActOn.elementActions(driver,DownPaymentInputField).setValue("15000");

        //driver.findElement(InterestRateInputField).clear();
        //driver.findElement(InterestRateInputField).sendKeys("3");
        ActOn.elementActions(driver,InterestRateInputField).setValue("3");

        //driver.findElement(YearDuration).clear();
        //driver.findElement(YearDuration).sendKeys("30");
        ActOn.elementActions(driver,YearDuration).setValue("30");

    }

    @Test
    public void calculateRealApr(){
        navigateToRealAprPage();
        enterData();

        //driver.findElement(CalculateRateButton).click();
        ActOn.elementActions(driver,CalculateRateButton).click();

        //Validate the Real APR rate is "3.130"
        //String actualAprRate = driver.findElement(ActualAprRate).getText();
        String actualRealAprRate = ActOn.elementActions(driver,ActualAprRate).getTextValue();
        Assert.assertEquals(actualRealAprRate,"3.130%");


    }
    @AfterMethod
    public void closeBrowser(){

        driver.quit();
        ActOn.browserActions(driver).close();
    }


}
