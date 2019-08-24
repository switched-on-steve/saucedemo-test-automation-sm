package com.saucedemo.selenium.pom.product;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailForm extends ProductForm {
    @FindBy(className = "inventory_details_name")
    private WebElement name;

    @FindBy(className = "inventory_details_desc")
    private WebElement description;

    @FindBy(className = "inventory_details_price")
    private WebElement price;

    @FindBy(className = "btn_inventory")
    private WebElement addRomoveButton;

    @FindBy(className = "inventory_details_back_button")
    private WebElement backButton;

    public String getProductName() {
        return name.getText();
    }

    public String getProductDescription() {
        return description.getText();
    }

    public String getProductPrice() {
        return price.getText();
    }

    public void removeProduct(String removeText) {
        if (addRomoveButton.getText().toLowerCase().equals(removeText.toLowerCase()))
            addRomoveButton.click();
    }

    public void addProduct(String addText) {
        if (addRomoveButton.getText().toLowerCase().equals(addText.toLowerCase()))
            addRomoveButton.click();
    }

    public void goBack() {
        backButton.click();
    }
}
