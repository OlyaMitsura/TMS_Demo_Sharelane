package SauceDemo.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class CheckoutPageTest extends  BaseTest {

    public CheckoutPageTest(ChromeDriver driver) {

    }

    public Object[][] dataForCheckoutForm() {
        return new Object[][]{
                {"", "", "", "Error: First Name is required"},
                {"", "Doe", "222424", "Error: First Name is required"},
                {"John", "", "222424", "Error: Last Name is required"},
                {"John", "Doe", "", "Error: Postal Code is required"},
        };
    }

    @Test(description = "Check submitting the checkout form with valid values")
    public void validSubmitOfCheckoutForm() {
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isPageOpened());
        productsPage.addToCart("Sauce Labs Onesie");
        cartPage.openByClickingOnCartButton();
        cartPage.checkout();
        assertTrue(checkoutPage.isPageOpened());
        checkoutPage.submit("John", "Doe", "222424");
        assertTrue(checkoutOverviewPage.isPageOpened());
    }

    @Test(description = "Check submitting the checkout form with invalid values")
    public void invalidSubmitOfCheckoutForm(String firstName, String lastName, String zipCode, String errorText) {
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isPageOpened());
        productsPage.addToCart("Sauce Labs Onesie");
        cartPage.openByClickingOnCartButton();
        cartPage.checkout();
        assertTrue(checkoutPage.isPageOpened());
        checkoutPage.submit(firstName, lastName, zipCode);
        assertEquals(checkoutPage.getError(), errorText);
    }
}
