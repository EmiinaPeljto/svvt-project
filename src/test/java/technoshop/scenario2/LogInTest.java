package technoshop.scenario2;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LogInTest {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://technoshop.ba/";

        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement acceptAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[17]/div/div[2]/button")));
        acceptAllButton.click();
        Thread.sleep(3000);

        WebElement userIcon = webDriver.findElement(By.className("icon--action"));
        userIcon.click();
        Thread.sleep(3000);
    }

    @Test
    @Order(1)
    void emptyForm() throws InterruptedException {

        WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/button"));
        loginButton.click();
        Thread.sleep(2000);

        WebElement emailInput = webDriver.findElement(By.id("email"));
        String validationMessage = emailInput.getAttribute("validationMessage");
        assertEquals("Please fill out this field.", validationMessage);
        Thread.sleep(2000);
    }
    @Test
    @Order(2)
    public void invalidInputlogInTest() throws InterruptedException {

        WebElement emailField = webDriver.findElement(By.id("email"));
        emailField.sendKeys("svvttest@gmail.com");
        Thread.sleep(3000);

        WebElement passwordField = webDriver.findElement(By.id("password"));
        passwordField.sendKeys("StakleniSto1");
        Thread.sleep(3000);

        WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/button"));
        loginButton.click();
        Thread.sleep(1000);

        WebElement invalidInput = webDriver.findElement(By.id("swal2-html-container"));

        assertEquals("Došlo je do greške. Korisnički račun nije pronađen.", invalidInput.getText());

        emailField.clear();
        passwordField.clear();
        Thread.sleep(3000);
    }

    @Test
    @Order(3)
    public void testValidLogIn() throws InterruptedException {

        WebElement emailField = webDriver.findElement(By.id("email"));
        emailField.sendKeys("svvttest@gmail.com");
        Thread.sleep(3000);

        WebElement passwordField = webDriver.findElement(By.id("password"));
        passwordField.sendKeys("StakleniSto1!");
        Thread.sleep(3000);

        WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/button"));
        loginButton.click();
        Thread.sleep(3000);

        WebElement profile = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[2]/div[1]/div[1]/h4"));
        assertEquals("MOJ RAČUN", profile.getText());
    }



    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}