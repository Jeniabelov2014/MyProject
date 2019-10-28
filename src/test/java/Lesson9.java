package test.java;

import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import main.java.PO.CoursePage;
import main.java.PO.HomePage;
import main.java.Utils.RetryAnalyzer;
import main.java.Utils.Screenshot;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

@Epic("Cart menu")
@Feature("Add product")
public class Lesson9 {
    static WebDriver driver;
    static WebDriverWait wait;
    static WebElement preloader;
    static HomePage homePage;
    static CoursePage coursePage;

    @BeforeMethod
    public static void setUp(ITestContext context) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        context.setAttribute("webdriver", driver);
        homePage = new HomePage(driver);
        coursePage = new CoursePage(driver);
    }
    @Story("positive story")
    @Test(dataProvider = "providerEveningCourses", retryAnalyzer = RetryAnalyzer.class)
    public static void mainTest(String courseName) throws InterruptedException {
        /*homePage.isShown()
                .selectLanguage(courseName);
        assertTrue(false);*/
        homePage.isShown()
                .openEveningCourses().openCourses(courseName).isPresent();
        coursePage.clicPay().checkIfLocationIsSelected("Берестейская");
        assertFalse(coursePage.checkIfLocationIsSelected("Позняки"));
        assertFalse(coursePage.checkIfLocationIsSelected("ВДНХ"));
                /*.selectLanguage(lang);
                .clicLogo()
                .openCourses("C++");*//*
        coursePage.clicPay();

        .checkIfPolitAgreemSelected("");


        assertTrue(coursePage.checkIfLocationIsSelected("Берестейская"));
        assertFalse(coursePage.checkIfLocationIsSelected("Позняки"));
        assertFalse(coursePage.checkIfLocationIsSelected("ВДНХ"));*/
    }

    @Test()
    public static void checkEveningCourses() {
        assertTrue(homePage.isShown().openEveningCourses().isPresent());
    }

    @Test
    public static void randomCourse() {
        String[] str = {"Тестирование",
                "Frontend development",
                "JS development",
                "Веб-дизайн",
                "PHP",
                "Программирование под IOS",
                "Программирование под Android",
                "Java programming",
                "Python",
                "Data Science/Machine Learning",
                "C# /.NET development",
                "C++",
                "Game Development",
                "DEVOPS",
                "Digital Marketing",
                "Управление персоналом",
                "Управление проектами",
                "Менеджмент",
                "Кибербезопасность",
                "Mobile development",
                "Видеомонтаж",
                "Cisco",
                "Go development"};
        int rand = (int) (Math.random() * (str.length + 1) );
        homePage.isShown()
                .openEveningCourses().openCourses(str[rand]);
        coursePage.clicPay().putInfo();
    }

    @Test
    public static void test() {
        homePage.isShown();
        List<String> expected = new ArrayList<String>();
        expected.add("RU");
        expected.add("UA");
        expected.add("EN");
        //for(String el: expected){
        //System.out.println(el);
        //}
        //for(int i = 0; i<expected.size(); i++){
        //System.out.println(expected.get(i));
        //}
        //List<Integer> list = new ArrayList<Integer>(); - обвертка для интеджеров(примитивов)
        //String expected[] = {"RU","UA","EN"};
        boolean isPresent = true;
        List<WebElement> elements = driver.findElements(By.xpath("(//ul[@class='lang'])[1]/li/a"));
        for (WebElement el : elements) {
            String text = el.getText();
            if (!expected.contains(text)) {
                isPresent = false;
            }
        }
        assertTrue(isPresent);
    }

    @AfterMethod
    public static void tearDown(ITestResult result) {
        saveScreenshot();
        driver.quit();
    }

    @DataProvider
    public Object[][] providerEveningCourses() {
        return new Object[][]{
                {"hестирование"},
                {"Frontend development"},
                {"JS development"},
                {"Веб-дизайн"},
                {"PHP"},
                {"Программирование под IOS"},
                {"Программирование под Android"},
                {"Java programming"},
                {"Python"},
                {"Data Science/Machine Learning"},
                {"C# /.NET development"},
                {"C++"},
                {"Game Development"},
                {"DEVOPS"},
                {"Digital Marketing"},
                {"Управление персоналом"},
                {"Управление проектами"},
                {"Менеджмент"},
                {"Кибербезопасность"},
                {"Mobile development"},
                {"Видеомонтаж"},
                {"Cisco"},
                {"Go development"}

        };
    }

    @DataProvider
    public Object[][] provider() {
        return new Object[][]{
                {"ru-RU"}/*,
                {"en-GB"},
                {"uk"}*/
        };
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}