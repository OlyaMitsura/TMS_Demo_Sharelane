package SauceDemo.tests;

import SauceDemo.pages.ProductsPage;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class ProductTest extends  BaseTest{
    @Test(description = "Check adding one product to the cart")
    public void addOneItem() {
        ProductsPage loginPage;
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        ProductsPage productsPage;
        assertTrue(productsPage.isPageOpened());
        productsPage.addToCart("Sauce Labs Onesie");
        assertEquals(productsPage.getButtonText("Sauce Labs Onesie"), "REMOVE", "Text of button is wrong");
    }

    @Test(description = "Check removing one product from the cart when it has been added to the cart")
    public void removeOneItem() {
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isPageOpened());
        productsPage.addToCart("Sauce Labs Backpack");
        productsPage.removeFromCart("Sauce Labs Backpack");
        assertEquals(productsPage.getButtonText("Sauce Labs Backpack"), "ADD TO CART", "Text of button is wrong");
    }

    @Test(description = "Check sorting option: Name (Z to A)")
    public void sortDescOrderName() {
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isPageOpened());
        productsPage.selectOption("Name (Z to A)");
        List<String> names = productsPage.getProductNames();
        assertEquals(names.get(0), "Test.allTheThings() T-Shirt (Red)", "Sorting is not correctly performed");
        assertEquals(names.get(5), "Sauce Labs Backpack", "Sorting is not correctly performed");
    }

    @Test(description = "Check sorting option: Name (A to Z)")
    public void sortAscOrderName() {
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isPageOpened());
        productsPage.selectOption("Name (A to Z)");
        List<String> names = productsPage.getProductNames();
        assertEquals(names.get(0), "Sauce Labs Backpack");
        assertEquals(names.get(5), "Test.allTheThings() T-Shirt (Red)");
    }

    @Test(description = "Check sorting option: Price (low to high)")
    public void sortAscOrderPrice() {
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isPageOpened());
        productsPage.selectOption("Price (low to high)");
        List<String> names = productsPage.getProductNames();
        assertEquals(names.get(0), "Sauce Labs Onesie");
        assertEquals(names.get(5), "Sauce Labs Fleece Jacket");
    }

    @Test(description = "Check sorting option: Price (high to low)")
    public void sortDescOrderPrice() {
        loginPage.isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(productsPage.isPageOpened());
        productsPage.selectOption("Price (high to low)");
        List<String> names = productsPage.getProductNames();
        assertEquals(names.get(0), "Sauce Labs Fleece Jacket");
        assertEquals(names.get(5), "Sauce Labs Onesie");
    }
}
