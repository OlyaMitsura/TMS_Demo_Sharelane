package herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class NotificationMessageTest {
    private static WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/notification_message_rendered");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
    @Test
    public void checkNotification() {
        driver.findElement(By.xpath("//*[@id='content']//a")).click();
        String notificationText = driver.findElement(By.xpath("//*[@id='flash']")).getText();
        Assert.assertEquals(notificationText, "Action unsuccessful, please try again\n", "Text is wrong");
    }

}
