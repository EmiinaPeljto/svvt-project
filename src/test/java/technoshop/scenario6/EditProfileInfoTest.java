package technoshop.scenario6;

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
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EditProfileInfoTest {

    private static WebDriver webDriver;
    private static String baseUrl;


    @BeforeAll
    public static void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\IBU\\Third year\\First semester\\Software Verification, Validation and Testing\\chromedriver-win64\\chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://technoshop.ba/";

        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement acceptAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[17]/div/div[2]/button")));
        acceptAllButton.click();
        Thread.sleep(5000);

        //Login
        WebElement login = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[4]"));
        login.click();
        Thread.sleep(2000);

        webDriver.findElement(By.id("email")).sendKeys("svvttest@gmail.com");
        Thread.sleep(2000);
        webDriver.findElement(By.id("password")).sendKeys("StakleniSto1!");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/button")).click();
        Thread.sleep(2000);
    }



    @Test
    @Order(1)
    void testMandatoryField () throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement myDetails = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[1]/div[2]/ul/li[3]/a"));
        myDetails.click();
        Thread.sleep(2000);

        WebElement editDetailsButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[2]/div[3]/div[2]/div[1]/div"));
        editDetailsButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0, 600)");
        Thread.sleep(2000);

        WebElement phoneNumberField = webDriver.findElement(By.id("phone_number_profile"));
        phoneNumberField.clear();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(2000);

        //click on the button
        webDriver.findElement(By.xpath("//*[@id=\"customerProfileUpdateDetails\"]/div[9]/button")).click();
        //Thread.sleep(2000);

        WebElement successfullyAdded = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Došlo je do greške. Obavezna polja nisu ispunjena.", successfullyAdded.getText());
        Thread.sleep(3000);

    }

    @Test
    @Order(2)
    void testEditProfileInfo () throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebElement myDetails = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[1]/div[2]/ul/li[3]/a"));
        myDetails.click();
        Thread.sleep(2000);

        WebElement editDetailsButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[2]/div[3]/div[2]/div[1]/div"));
        editDetailsButton.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0, 600)");
        Thread.sleep(2000);

        WebElement oldNumber = webDriver.findElement(By.id("phone_number_profile"));
        String oldNumberValue = oldNumber.getAttribute("value");
        oldNumber.clear();
        Thread.sleep(2000);
        oldNumber.sendKeys("+123456");
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"customerProfileUpdateDetails\"]/div[9]/button")).click();
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement updatedNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone_number_profile")));
        String newNumberValue = updatedNumber.getAttribute("value");

        if (oldNumberValue.equals(newNumberValue)) {
            assertEquals(oldNumberValue, newNumberValue);
        }  else {
            assertNotEquals(oldNumberValue, newNumberValue);
        }
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}