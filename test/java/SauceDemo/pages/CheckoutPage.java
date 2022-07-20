package SauceDemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {
    private static final By CHECKOUT_OVERVIEW_TITLE = By.cssSelector(".title");
    private static final By FINISH_BUTTON = By.cssSelector(".cart_button");
    private static final By CANCEL_BUTTON = By.cssSelector(".cart_cancel_link");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageOpened() {
        isVisible(CHECKOUT_OVERVIEW_TITLE);
        return true;
    }

    public void finishOrder() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public void cancelOrder() {
        driver.findElement(CANCEL_BUTTON).click();
    }
}
