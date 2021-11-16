package SauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends  BasePage {
    private static final By TITLE = By.cssSelector(".title");
    private String productButtonLocator = By.xpath("[//*[text() = '%s']/ancestor::*[@class = 'inventory_item']//button");
    private static final By DROPDOWN = By.xpath("//select[@class='product_sort_container']");
    private static final String PRODUCT_TITLE = ".inventory_item_name";

    public ProductsPage(WebDriver driver) { //call constructor of parent class
        super(driver);
    }

    public String getHeader() {
        return driver.findElement(TITLE).getText();
    }

    public boolean isPageOpened() {
        isVisible(TITLE);
        return true;
    }

    public void addToCart(String productName) {
        driver.findElement(By.xpath(String.format(productButtonLocator, productName))).click();
    }

    public void removeFromCart(String productName) {
        driver.findElement(By.xpath(String.format(productButtonLocator, productName))).click();
    }

    public String getButtonText(String productName) {
        return driver.findElement(By.xpath(String.format(productButtonLocator, productName))).getText();
    }

    public void selectOption(String value) {
        WebElement selectElement = driver.findElement(DROPDOWN);
        Select option = new Select(selectElement);
        option.selectByVisibleText(value);
    }

    public List<String> getProductNames() {
        List<String> stringNames = new ArrayList<>();
        List<WebElement> names = driver.findElements(By.cssSelector(PRODUCT_TITLE));
        for (WebElement element : names) {
            stringNames.add(element.getText());
        }
        return stringNames;
    }

}
