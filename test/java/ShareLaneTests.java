import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class ShareLaneTests {
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        //Open browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test
    void zipCodePositiveTest() {

        //Navigate to ShareLane (https://www.sharelane.com/)
        driver.get("https://www.sharelane.com/");
        WebElement enterButton = driver.findElement(By.cssSelector("a[href='../cgi-bin/main.py']"));
        enterButton.click();
        // Click "Sign Up" link
        WebElement signUpLink = driver.findElement(By.cssSelector("a[href='./register.py']"));
        signUpLink.click();
        // Enter 5 digits to Zip code field
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        // Click "Continue"
        WebElement continueButton = driver.findElement(By.cssSelector("input[value = 'Continue']"));
        continueButton.click();
        // Verify that user is redirected to "Sign Up" page
        WebElement registerButton = driver.findElement(By.cssSelector("input[value = 'Register']"));
        //Asserts
        zipCodeInput = driver.findElement(By.name("zip_code"));
        Assert.assertFalse(zipCodeInput.isDisplayed(), "Zip code input should not be present");
        Assert.assertTrue(registerButton.isDisplayed(), "Register button should be displayed");
    }

    @Test
    public void maxValueZipCode() {
        driver.get("https://www.sharelane.com/");
        WebElement enterButton = driver.findElement(By.cssSelector("a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement signUpLink = driver.findElement(By.cssSelector("a[href='./register.py']"));
        signUpLink.click();
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        WebElement maxValueZipCode = driver.findElement(By.name("zip_code"));
        maxValueZipCode.sendKeys("123456789012345678901234567890");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value = 'Continue']"));
        continueButton.click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value = 'Register']"));
        Assert.assertTrue(registerButton.isDisplayed(), "Register button should be displayed");
    }

    public void emptyZipCodeTest() {
        driver.get("https://www.sharelane.com/");
        WebElement enterButton = driver.findElement(By.cssSelector("a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement signUpLink = driver.findElement(By.cssSelector("a[href='./register.py']"));
        signUpLink.click();
        WebElement continueButton = driver.findElement(By.cssSelector("input[value = 'Continue']"));
        continueButton.click();
        WebElement errorMessage = driver.findElement(By.cssSelector("error_message"));
        Assert.assertTrue(errorMessage.isDisplayed(), "Oops, error on page. ZIP code should have 5 digits");
    }

    public void zipCode3DigitsTest() {
        driver.get("https://www.sharelane.com/");
        WebElement enterButton = driver.findElement(By.cssSelector("a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement signUpLink = driver.findElement(By.cssSelector("a[href='./register.py']"));
        signUpLink.click();
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        WebElement zipCode3DigitsTest = driver.findElement(By.name("zip_code"));
        zipCode3DigitsTest.sendKeys("123");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value = 'Continue']"));
        continueButton.click();
        WebElement errorMessage = driver.findElement(By.cssSelector("error_message"));
        Assert.assertEquals(errorMessage.getText(), "Oops, error on page. ZIP code should have 5 digits");
    }

    public void zipCode6DigitsTest() {
        //Open browser
        //Navigate to ShareLane (https://www.sharelane.com/)
        // Click "Sign Up" link
        // Enter 6 digits to Zip code field
        // Click "Continue"
        // Verify that user stays on the same page

    }

}
