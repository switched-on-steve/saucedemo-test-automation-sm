package com.saucedemo.selenium.pom.checkout;

import com.saucedemo.selenium.pom.SauceDemoPom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.saucedemo.util.Globals.SAUCE_DEMO_CHECKOUT_STEP_ONE_PAGE_URL;

public class CheckoutStepOnePage extends SauceDemoPom {
    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement zipCodeField;

    @FindBy(className = "cart_button")
    private WebElement continueButton;

    public void open() {
        driver.get(SAUCE_DEMO_CHECKOUT_STEP_ONE_PAGE_URL);
    }

    public void fillUp(CheckoutStepOneDto checkoutStepOneDto) {
        firstNameField.clear();
        firstNameField.sendKeys(checkoutStepOneDto.getFirstName());
        lastNameField.clear();
        lastNameField.sendKeys(checkoutStepOneDto.getLastName());
        zipCodeField.clear();
        zipCodeField.sendKeys(checkoutStepOneDto.getZipCode());
    }

    public void goNext() {
        continueButton.click();
    }

    public boolean isErrorMsgExist(String errorMessage) {
        List<WebElement> elements = driver.findElements(By.cssSelector("h3[data-test='error']"));

        for (WebElement elem : elements) {
            if (elem.getText().contains(errorMessage)) {
                return true;
            }
        }

        return false;
    }
}
