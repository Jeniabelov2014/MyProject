package main.java.PO;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class  CoursePage extends BasePage {

    public Logger logger = LogManager.getLogger(this.getClass());


    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public CoursePage clicPay() {
        WebElement payBtn = driver.findElement(By.xpath("//button[@name='roadFull_payOnce']"));
        wait.until(ExpectedConditions.elementToBeClickable(payBtn));
        payBtn.click();
        this.waitSpinner();
        return this;
    }

    public CoursePage putInfo(){
        WebElement nameField = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        nameField.sendKeys("test");
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        emailField.sendKeys("jeniabelov2014@gmail.com");
        WebElement phoneField = driver.findElement(By.xpath("//*[@id=\"phone\"]"));
        phoneField.sendKeys("0638141284");
        WebElement checkbox = driver.findElement(By.xpath("//div[@class='privacy-policy__wrapper']//span"));
        wait.until(ExpectedConditions.elementToBeClickable(checkbox));
        checkbox.click();
        WebElement submitBtn = driver.findElement(By.xpath("//input[@class='submit ']"));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn));
        submitBtn.click();
        waitSpinner();
        WebElement thanksBlock = driver.findElement(By.xpath("//div[@class='thanks-block']"));
        wait.until(ExpectedConditions.visibilityOf(thanksBlock));
        Assert.assertTrue(thanksBlock.isDisplayed());
        return this;
    }

    public boolean checkIfLocationIsSelected(String location) {

        By element = By.xpath("//input[@id=//div[contains(text(), '" + location + "')]/../@for]");
        WebElement webLocation = driver.findElement(element);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        logger.info("Checked location '"+ location +"' ");
        return webLocation.isSelected();
    }
    public boolean checkIfPolitAgreemSelected() {
        By element1 = By.id("input-privacy-policy");
        WebElement agreemCheckBox = driver.findElement(element1);
        wait.until(ExpectedConditions.elementToBeClickable(agreemCheckBox));
        return agreemCheckBox.isSelected();
    }
}
