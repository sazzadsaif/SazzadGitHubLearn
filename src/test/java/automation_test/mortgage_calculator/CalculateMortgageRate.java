package automation_test.mortgage_calculator;

import command_providers.ActOn;
import command_providers.AssertThat;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.DateUtils;

public class CalculateMortgageRate {

    private final By HomeValueInputField = By.id("homeval");
    private final By DownPaymentInputField = By.id("downpayment");
    private final By SelectDownPaymentInDollar = By.name("param[downpayment_type]");
    private final By LoanAmountInputField = By.id("loanamt");
    private final By InterestRateInputField = By.id("intrstsrate");
    private final By LoanTermInputField = By.id("loanterm");
    private final By StartMonthDropDown = By.name("param[start_month]");
    private final By StartYearInputField = By.id("start_year");
    private final By PropertyTaxInputField = By.id("pptytax");
    private final By PMIInputField = By.id("pmi");
    private final By HomeInsInputField = By.id("hoi");
    private final By HoaInputField = By.id("hoa");
    private final By LoanTypeDropDown = By.name("param[milserve]");
    private final By RefiOrBuyDropDown = By.name("param[refiorbuy]");
    private final By CalculateButton = By.name("cal");

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
    private void enterData(){
        // Enter Home Value "300000"
        //driver.findElement(HomeValueInputField).clear();
        //driver.findElement(HomeValueInputField).sendKeys("300000");
        ActOn.elementActions(driver,HomeValueInputField).setValue("300000");

        // Down Payment is 60000
        //driver.findElement(DownPaymentInputField).clear();
        //driver.findElement(DownPaymentInputField).sendKeys("60000");
        ActOn.elementActions(driver,DownPaymentInputField).setValue("60000");

        // Click on the Radio button $
        //driver.findElement(SelectDownPaymentInDollar).click();
        ActOn.elementActions(driver,SelectDownPaymentInDollar).click();

        //Enter Loan Amount "240000"
        //driver.findElement(LoanAmountInputField).clear();
        //driver.findElement(LoanAmountInputField).sendKeys("240000");
        ActOn.elementActions(driver,LoanAmountInputField).setValue("240000");

        //Enter Interest Rate "3%"
        //driver.findElement(InterestRateInputField).clear();
        //driver.findElement(InterestRateInputField).sendKeys("3");
        ActOn.elementActions(driver,InterestRateInputField).setValue("3");

        // Enter Loan term 30 years
        //driver.findElement(LoanTermInputField).clear();
        //driver.findElement(LoanTermInputField).sendKeys("30");
        ActOn.elementActions(driver,LoanTermInputField).setValue("30");

        // Month and Year

        String date = DateUtils.returnNextMonth();
        String[] dates = date.split("-");
        String month = dates[0];
        String year = dates[1];

        //Enter Start  month "Dec" drop down
        //select = new Select(driver.findElement(StartMonthDropDown));
        //select.selectByVisibleText("Dec");
        ActOn.elementActions(driver,StartMonthDropDown).selectValue(month);

        // Enter the year "2021"
        //driver.findElement(StartYearInputField).clear();
        //driver.findElement(StartYearInputField).sendKeys("2021");
        ActOn.elementActions(driver,StartYearInputField).setValue(year);

        // Enter Property Tax "5000"
        //driver.findElement(PropertyTaxInputField).clear();
        //driver.findElement(PropertyTaxInputField).sendKeys("5000");
        ActOn.elementActions(driver,PropertyTaxInputField).setValue("5000");

        //Enter PMI "0.5"
        //driver.findElement(PMIInputField).clear();
        //driver.findElement(PMIInputField).sendKeys("0.5");
        ActOn.elementActions(driver,PMIInputField).setValue("0.5");

        // Enter Home Ins "1000"
        //driver.findElement(HomeInsInputField).clear();
        //driver.findElement(HomeInsInputField).sendKeys("1000");
        ActOn.elementActions(driver,HomeInsInputField).setValue("1000");

        // Enter Monthly HOA "100"
        //driver.findElement(HoaInputField).clear();
        //driver.findElement(HoaInputField).sendKeys("100");
        ActOn.elementActions(driver,HoaInputField).setValue("100");

        // Select Loan type "FHA"
        //select = new Select(driver.findElement(LoanTypeDropDown));
        //select.selectByVisibleText("FHA");
        ActOn.elementActions(driver,LoanTypeDropDown).selectValue("FHA");

        // SELECT "BUY" OPTION
        //select = new Select(driver.findElement(RefiOrBuyDropDown));
        //select.selectByVisibleText("Buy");
        ActOn.elementActions(driver,RefiOrBuyDropDown).selectValue("Buy");

    }
    @Test
    public void calculateMonthlyPayment(){

        enterData();
        // Click on the Calculate Button

        //driver.findElement(CalculateButton).click();
        ActOn.elementActions(driver,CalculateButton).click();

        String expectedTotalMonthlyPayment = "1,611.85";
        String formattedXpath = String.format("//h3[text()='$%s']",expectedTotalMonthlyPayment);
        By monthlyPayment = By.xpath(formattedXpath);

        // Another way
        // String formattedXpath = String.format("//h3[text()='$' + expectedTotalMonthlyPayment]");


        //boolean present = driver.findElement(By.xpath(formattedXpath)).isDisplayed();

        // Validate that the total monthly payment is "$1,611.85"

        //Assert.assertTrue(present,"Total monthly payment is not presented");

        AssertThat.elementAssertions(driver,monthlyPayment).elementIsDisplayed();

    }
    @AfterMethod
    public void closeBrowser(){
        //driver.quit();
        ActOn.browserActions(driver).close();
    }
}
