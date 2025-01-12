package technoshop.scenario15;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SessionAndSecurityTest {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static WebDriverWait wait;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://technoshop.ba/prijava";

        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        js = (JavascriptExecutor) webDriver;

        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement acceptAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[8]/div/div[2]/button")));
        acceptAllButton.click();
        Thread.sleep(3000);

        WebElement emailField = webDriver.findElement(By.id("email"));
        emailField.sendKeys("svvttest@gmail.com");
        Thread.sleep(3000);

        WebElement passwordField = webDriver.findElement(By.id("password"));
        passwordField.sendKeys("StakleniSto1!");
        Thread.sleep(3000);

        WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/button"));
        loginButton.click();
        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(3000);
    }

    @Test
    @Order(1)
    void logOutSessionHandling() throws InterruptedException {
        WebElement logOutButton = webDriver.findElement(By.linkText("Odjavi se"));
        logOutButton.click();

        String token = (String) js.executeScript("return localStorage.getItem('authToken')");
        System.out.println(token);

        assertTrue(token == null || token.isEmpty(), "Token should be null after logout.");
        Thread.sleep(3000);
    }

    @Test
    @Order(2)
    void testHTTPS() throws InterruptedException {
        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("https"));
        Thread.sleep(3000);
    }
    @Test
    @Order(3)
    void testHTTPSv2() throws InterruptedException {
        String currentUrl = webDriver.getCurrentUrl();
        webDriver.get("http://technoshop.ba/");
        String http = webDriver.getCurrentUrl();
        assertEquals(currentUrl, http);
        Thread.sleep(3000);

    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
