package herokuapp;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

    public class DropdownTest {
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/dropdown");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

        //Check getting all elements from dropdown
        @Test
        public void availableElements() {
            Select dropdown = new Select(driver.findElement(By.id("dropdown")));
            List<WebElement> numOfElements = dropdown.getOptions();
            Assert.assertEquals(numOfElements.size(), 3, "Amount of options is incorrect");
        }

        //Check selection the first element
        @Test
        public void selectFirstOption() {
            Select dropdown = new Select(driver.findElement(By.id("dropdown")));
            dropdown.selectByVisibleText("Option 1");
            String text = dropdown.getFirstSelectedOption().getText();
            Assert.assertEquals(text, "Option 1", "Chosen option is incorrect");
        }

        //Check selection the second element
        @Test
        public void selectSecondOption() {
            Select dropdown = new Select(driver.findElement(By.id("dropdown")));
            dropdown.selectByVisibleText("Option 2");
            String text = dropdown.getFirstSelectedOption().getText();
            Assert.assertEquals(text, "Option 2", "Chosen option is incorrect");
        }

}
