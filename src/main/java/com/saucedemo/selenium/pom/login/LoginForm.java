package com.saucedemo.selenium.pom.login;

import com.saucedemo.selenium.pom.SauceDemoPom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.saucedemo.util.Globals.SAUCE_DEMO_URL;

public class LoginForm extends SauceDemoPom {
    @FindBy(id = "user-name")
    private WebElement userNameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(className = "btn_action")
    private WebElement loginButton;

    public void open() {
        driver.get(SAUCE_DEMO_URL);
    }

    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    public void fillUpUserName(String name) {
        userNameField.clear();
        userNameField.sendKeys(name);
    }

    public void fillUpPassword(String pass) {
        passwordField.clear();
        passwordField.sendKeys(pass);
    }

    public void fillUp(LoginDto loginDto) {
        fillUpUserName(loginDto.getUserName());
        fillUpPassword(loginDto.getPassword());
    }

    public void login() {
        loginButton.click();
    }

    public void login(String name, String pass) {
        fillUpUserName(name);
        fillUpPassword(pass);
        login();
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
