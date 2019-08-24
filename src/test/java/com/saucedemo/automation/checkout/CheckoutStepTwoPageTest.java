package com.saucedemo.automation.checkout;

import com.saucedemo.automation.SauceDemoTest;
import com.saucedemo.selenium.pom.checkout.CheckoutStepTwoPage;
import com.saucedemo.selenium.pom.login.LoginDto;
import com.saucedemo.selenium.pom.login.LoginPage;
import com.saucedemo.selenium.pom.product.ProductPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.saucedemo.automation.DataProviderUtils.getDataObjectArray;

public class CheckoutStepTwoPageTest extends SauceDemoTest {
    private static String FILE_NAME = "checkout/checkoutStepTwo.csv";
    private LoginPage loginPage;
    private ProductPage productPage;
    private CheckoutStepTwoPage checkoutStepTwoPage;

    @Override
    public void init() {
        loginPage = createPomInstance(LoginPage.class);
        productPage = createPomInstance(ProductPage.class);
        checkoutStepTwoPage = createPomInstance(CheckoutStepTwoPage.class);
    }

    @DataProvider(name = "getCheckoutStepTwo")
    public static Object[][] getCheckoutStepTwo(Method testMethod) {
        String testCaseCode = testMethod.getAnnotation(Test.class).testName();

        return getDataObjectArray(LoginDto.class, FILE_NAME, testCaseCode);
    }
}
