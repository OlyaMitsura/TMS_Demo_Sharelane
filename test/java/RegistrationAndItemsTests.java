import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RegistrationAndItemsTests {
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        //Open browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void clearSession() {
        driver.manage().deleteAllCookies();
    }
    @Test
    public void successfulRegistration() {

        driver.get("https://www.sharelane.com/");
        WebElement enterButton = driver.findElement(By.cssSelector("a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement signUpLink = driver.findElement(By.cssSelector("a[href='./register.py']"));
        signUpLink.click();
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value = 'Continue']"));
        continueButton.click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value = 'Register']"));
        WebElement firstName = driver.findElement(By.name("first_name"));
        firstName.sendKeys("Regina");
        WebElement lastName = driver.findElement(By.name("last_name"));
        lastName.sendKeys("Kokorina");
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("reginatest@yandex.ru");
        WebElement password1 = driver.findElement(By.name("password1"));
        password1.sendKeys("reginakot18");
        WebElement password2 = driver.findElement(By.name("password2"));
        password2.sendKeys("reginakot18");
        WebElement registrationButton2 = driver.findElement(By.cssSelector("input[value='Register']"));
        registrationButton2.click();
        WebElement confirmationMessage = driver.findElement(By.className("confirmation_message"));
        Assert.assertEquals(confirmationMessage.getText(), "Account is created!");
    }

    @Test //should be failed
    public void differentPasswordAndConfirmationPassword() {

        driver.get("https://www.sharelane.com/");
        WebElement enterButton = driver.findElement(By.cssSelector("a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement signUpLink = driver.findElement(By.cssSelector("a[href='./register.py']"));
        signUpLink.click();
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value = 'Continue']"));
        continueButton.click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value = 'Register']"));
        WebElement firstName = driver.findElement(By.name("first_name"));
        firstName.sendKeys("Regina");
        WebElement lastName = driver.findElement(By.name("last_name"));
        lastName.sendKeys("Kokorina");
        WebElement email = driver.findElement(By.name("email"));
        email.sendKeys("reginatest@yandex.ru");
        WebElement password1 = driver.findElement(By.name("password1"));
        password1.sendKeys("reginakot18");
        WebElement password2 = driver.findElement(By.name("password2"));
        password2.sendKeys("Reginakotik1818");
        WebElement registrationButton2 = driver.findElement(By.cssSelector("input[value='Register']"));
        registrationButton2.click();
        WebElement errorMessage = driver.findElement(By.className("error_message"));
        Assert.assertEquals(errorMessage.getText(), "Oops! Something went wrong. Check your password again!");
    }

    @Test
    public void unregisteredShoppingCart(){
        driver.get("https://www.sharelane.com/");
        WebElement enterButton = driver.findElement(By.cssSelector("a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement shoppingCartLink = driver.findElement(By.cssSelector("a[href='./shopping_cart.py']"));
        shoppingCartLink.click();
        WebElement errorMessage = driver.findElement(By.className("error_message"));
        Assert.assertEquals(errorMessage.getText(), "Oops, error. You must log in");
    }

    @Test
    public  void loginAndAddBooksByTitle(){
        driver.get("https://www.sharelane.com/");
        WebElement enterButton = driver.findElement(By.cssSelector("a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement testPortalLink = driver.findElement(By.cssSelector("a[href='../test_portal.html']"));
        testPortalLink.click();
        WebElement accountCreator = driver.findElement(By.cssSelector("a[href='../cgi-bin/create_account.py']"));
        accountCreator.click();
        WebElement createUser = driver.findElement(By.cssSelector("input[value='Create new user account']"));
        createUser.click();
        WebElement autoLogin = driver.findElement(By.cssSelector("input[value='Auto Login']"));
        autoLogin.click();
        WebElement searchField = driver.findElement(By.cssSelector("input[type='text']"));
        searchField.click();
        searchField.sendKeys("The Adventures of Tom Sawyer");
        WebElement searchButton = driver.findElement(By.cssSelector("input[type='submit']"));
        searchButton.click();
        WebElement addToCartButton = driver.findElement(By.cssSelector("a[href='./add_to_cart.py?book_id=3']"));
        addToCartButton.click();
        WebElement shoppingCartLink = driver.findElement(By.cssSelector("a[href='./shopping_cart.py']"));
        shoppingCartLink.click();
        WebElement checkout = driver.findElement(By.cssSelector("input[value='Proceed to Checkout']"));
        Assert.assertTrue(checkout.isDisplayed());

    }

    @Test //should be failed
    public  void loginAndAddBooksByAuthor(){
        driver.get("https://www.sharelane.com/");
        WebElement enterButton = driver.findElement(By.cssSelector("a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement testPortalLink = driver.findElement(By.cssSelector("a[href='../test_portal.html']"));
        testPortalLink.click();
        WebElement accountCreator = driver.findElement(By.cssSelector("a[href='../cgi-bin/create_account.py']"));
        accountCreator.click();
        WebElement createUser = driver.findElement(By.cssSelector("input[value='Create new user account']"));
        createUser.click();
        WebElement autoLogin = driver.findElement(By.cssSelector("input[value='Auto Login']"));
        autoLogin.click();
        WebElement searchField = driver.findElement(By.cssSelector("input[type='text']"));
        searchField.click();
        searchField.sendKeys("Paulo Coelho");
        WebElement searchButton = driver.findElement(By.cssSelector("input[type='submit']"));
        searchButton.click();
        WebElement addToCartButton = driver.findElement(By.cssSelector("a[href='./add_to_cart.py?book_id=7']"));
        Assert.assertTrue(addToCartButton.isDisplayed());
    }
}
