package com.saucedemo.automation.product;

import com.saucedemo.automation.SauceDemoTest;
import com.saucedemo.selenium.pom.product.ProductDetailForm;
import com.saucedemo.selenium.pom.product.ProductDto;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.saucedemo.automation.DataProviderUtils.getDataObjectArray;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductDetailFormTest extends SauceDemoTest {
    private static String FILE_NAME = "product/productDetail.csv";
    private ProductDetailForm productDetailForm;

    @Override
    public void init() {
        productDetailForm = createPomInstance(ProductDetailForm.class);
    }

    @AfterClass
    public final void afterMethod() {
        productDetailForm.logout();
    }

    @DataProvider(name = "getProductDetail")
    public static Object[][] getProductDetail(Method testMethod) {
        String testCaseCode = testMethod.getAnnotation(Test.class).testName();

        return getDataObjectArray(ProductDto.class, FILE_NAME, testCaseCode);
    }

    @Test(testName = "TC-1", description = "Product Name in both Product page and Product Detail page should be same", dataProvider = "getProductDetail")
    public void testProductNameShouldBeSame(ProductDto productDto) {
        productDetailForm.goToProductForm(productDto.getUserName(), productDto.getPassword());
        int noOfProducts = productDetailForm.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            String productName = productDetailForm.getProductNameAtIndexOf(i);
            productDetailForm.goToProductDetailAtIndexOf(i);
            assertThat(productName.equals(productDetailForm.getProductName())).isTrue();
            productDetailForm.goBack();
        }
    }

    @Test(testName = "TC-2", description = "Product Description in both Product page and Product Detail page should be same", dataProvider = "getProductDetail")
    public void testProductDescriptionShouldBeSame(ProductDto productDto) {
        productDetailForm.goToProductForm(productDto.getUserName(), productDto.getPassword());
        int noOfProducts = productDetailForm.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            String productDescription = productDetailForm.getProductDescriptionAtIndexOf(i);
            productDetailForm.goToProductDetailAtIndexOf(i);
            assertThat(productDescription.equals(productDetailForm.getProductDescription())).isTrue();
            productDetailForm.goBack();
        }
    }

    @Test(testName = "TC-3", description = "Product Price in both Product page and Product Detail page should be same", dataProvider = "getProductDetail")
    public void testProductPriceShouldBeSame(ProductDto productDto) {
        productDetailForm.goToProductForm(productDto.getUserName(), productDto.getPassword());
        int noOfProducts = productDetailForm.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            String productPrice = productDetailForm.getProductPriceAtIndexOf(i);
            productDetailForm.goToProductDetailAtIndexOf(i);
            assertThat(productPrice.equals(productDetailForm.getProductPrice())).isTrue();
            productDetailForm.goBack();
        }
    }

    @Test(testName = "TC-4", description = "Product is correctly removed from Product Detail page", dataProvider = "getProductDetail")
    public void testProductRemovedCorrectly(ProductDto productDto) {
        productDetailForm.goToProductForm(productDto.getUserName(), productDto.getPassword());
        productDetailForm.addAllProducts(productDto.getLabelForAdd());
        int noOfProducts = productDetailForm.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            productDetailForm.goToProductDetailAtIndexOf(i);
            productDetailForm.removeProduct(productDto.getLabelForRemove());
            assertThat(productDetailForm.countOfProductsInShoppingCart() == (noOfProducts - i - 1)).isTrue();
            productDetailForm.goBack();
        }
    }

    @Test(testName = "TC-5", description = "Product is correctly added from Product Detail page", dataProvider = "getProductDetail")
    public void testProductAddedCorrectly(ProductDto productDto) {
        productDetailForm.goToProductForm(productDto.getUserName(), productDto.getPassword());
        productDetailForm.removeAllProducts(productDto.getLabelForRemove());
        int noOfProducts = productDetailForm.countOfProducts();

        for(int i = 0; i < noOfProducts; i++) {
            productDetailForm.goToProductDetailAtIndexOf(i);
            productDetailForm.addProduct(productDto.getLabelForAdd());
            assertThat(productDetailForm.countOfProductsInShoppingCart() == (i + 1)).isTrue();
            productDetailForm.goBack();
        }
    }
}
