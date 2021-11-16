package SauceDemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTest extends  BaseTest {

    public Object[][] dataForLogin() {
        return new Object[][]{
                {"", "", "Epic sadface: Username is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"45", "45", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }
    @Test(description = "Check successful authorization with valid credentials")
    public void validLogin() {
        Assert.assertTrue(loginPage.isPageOpened());
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getHeader(), "PRODUCTS", "Wrong page is opened");
    }

    @Test(description = "Check not successful authorization with invalid credentials")
    public void invalidLogin(String username, String password, String errorText) {
        loginPage.isPageOpened();
        loginPage.login(username, password);
        assertEquals(loginPage.getError(), errorText);

    }

}
