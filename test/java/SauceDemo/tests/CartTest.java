package SauceDemo.tests;

import SauceDemo.pages.BasePage;
import SauceDemo.pages.CartPage;
import SauceDemo.pages.ProductsPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class CartTest extends BaseTest {
    @Test(description = "Check removing product from the cart in the Your cart page when it has been added")
    public void removeProductFromCart() {
        ProductsPage loginPage;
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        ProductsPage productsPage;
        assertTrue(productsPage.isPageOpened());
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        CartPage cartPage;
        cartPage.open();
        cartPage.removeItemFromCart("Sauce Labs Fleece Jacket");
        assertEquals(cartPage.getAmountOfProducts(), 0, "Amount of products is wrong");
    }

    @Test(description = "Check that product is still in the cart when user continues shopping")
    public void continueShopping() {
        ProductsPage loginPage;
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        ProductsPage productsPage;
        assertTrue(productsPage.isPageOpened());
        productsPage.addToCart("Sauce Labs Fleece Jacket");
        CartPage cartPage;
        cartPage.openByClickingOnCartButton();
        cartPage.continueShopping();
        assertEquals(productsPage.getButtonText("Sauce Labs Fleece Jacket"), "REMOVE",
                "Product isn't added to the cart when user continues shopping");
    }
}
