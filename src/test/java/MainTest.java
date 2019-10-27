package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class MainTest {
    static WebDriver driver; //объявили драйвер, что б не прописывать в каждом методе

    @BeforeMethod
    public static void setUp() {
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mak\\IdeaProjects\\selenium");
        driver.manage().window().maximize();//развернет окно браузера на максимус
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS); //изменили неявное ожидание, которое изначально 10 сек, на 100милисек
    }


    @Test
    public static void mainATest() throws InterruptedException {

        driver.get("http://iteaua-develop.demo.gns-it.com/");
        WebDriverWait wait = new WebDriverWait(driver, 10); //изменяем явное ожидание (делаем когда кликабельные или другие действенные ожидания, моменты)
        WebElement preloader = driver.findElement(By.id("preload-it"));
        wait.until(ExpectedConditions.visibilityOf(preloader)); //стабильность тестов
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        WebElement el = driver.findElement(By.xpath("(//a[@hreflang='en-GB'])[1]"));

        WebElement javaCourses = driver.findElement(By.xpath("//h3[contains(text(), 'Java')]/../img"));
        //WebElement callBackBtn = driver.findElement(By.xpath("//img[@alt='ITEA']")); // .stavim potomu 4to element clasa 4erez css selector (поменяли на xpath) webelement = element, xpath=locator by
        wait.until(ExpectedConditions.elementToBeClickable(javaCourses));
        javaCourses.click();

        WebElement payBtn = driver.findElement(By.xpath("//button[@name='roadFull_payOnce']"));
        wait.until(ExpectedConditions.elementToBeClickable(payBtn));
        payBtn.click();
        wait.until(ExpectedConditions.visibilityOf(preloader)); //стабильность тестов
        wait.until(ExpectedConditions.invisibilityOf(preloader));
        //assertEquals (1, 1);


    }

    @AfterMethod
    public static void tearDown() {
        driver.quit();
    }
}
