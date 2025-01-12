package technoshop.scenario8;

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

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChangePasswordTest {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\IBU\\Third year\\First semester\\Software Verification, Validation and Testing\\chromedriver-win64\\chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://technoshop.ba/prijava";

        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement acceptAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[8]/div/div[2]/button")));
        acceptAllButton.click();
        Thread.sleep(5000);

        webDriver.findElement(By.id("email")).sendKeys("svvt.test@outlook.com");
        Thread.sleep(2000);
        webDriver.findElement(By.id("password")).sendKeys("svvtTEST1!!");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/button")).click();
        Thread.sleep(2000);

        //profile page
        webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[1]/div[2]/ul/li[4]/a")).click();
        Thread.sleep(2000);
    }

    @Test
    @Order(1)
    void testWrongOldPwd () throws InterruptedException {
        webDriver.findElement(By.xpath("//*[@id=\"customerProfileUpdatePassword\"]/div[1]/img[2]")).click();
        Thread.sleep(2000);

        webDriver.findElement(By.id("old_password_profile")).sendKeys("123456");
        Thread.sleep(2000);
        webDriver.findElement(By.id("new_password_profile")).sendKeys("svvtTEST11!!");
        Thread.sleep(2000);
        webDriver.findElement(By.id("new_password_repeat_profile")).sendKeys("svvtTEST11!!");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"customerProfileUpdatePassword\"]/div[2]/img[2]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"customerProfileUpdatePassword\"]/div[3]/img[2]")).click();
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,150)");
        Thread.sleep(1000);

        webDriver.findElement(By.xpath("//*[@id=\"customerProfileUpdatePassword\"]/div[4]/button")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Došlo je do greške. Stara lozinka nije ispravna.", error.getText());
        Thread.sleep(3000);
    }

    @Test
    @Order(2)
    void testPwdMissmatch() throws InterruptedException {

        webDriver.findElement(By.id("old_password_profile")).clear();
        webDriver.findElement(By.id("old_password_profile")).sendKeys("svvtTEST1!!");
        Thread.sleep(2000);
        webDriver.findElement(By.id("new_password_profile")).clear();
        webDriver.findElement(By.id("new_password_profile")).sendKeys("svvtTEST11!!");
        Thread.sleep(2000);
        webDriver.findElement(By.id("new_password_repeat_profile")).clear();
        webDriver.findElement(By.id("new_password_repeat_profile")).sendKeys("svvtTEST!");
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"customerProfileUpdatePassword\"]/div[4]/button")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Došlo je do greške. Nova lozinka i ponovljena nova lozinka se ne poklapaju.", error.getText());
        Thread.sleep(3000);
    }

    @Test
    @Order(3)
    void testPasswordChanged() throws InterruptedException {

        webDriver.findElement(By.id("old_password_profile")).clear();
        webDriver.findElement(By.id("old_password_profile")).sendKeys("svvtTEST1!!");
        Thread.sleep(2000);
        webDriver.findElement(By.id("new_password_profile")).clear();
        webDriver.findElement(By.id("new_password_profile")).sendKeys("svvtTEST11!!");
        Thread.sleep(2000);
        webDriver.findElement(By.id("new_password_repeat_profile")).clear();
        webDriver.findElement(By.id("new_password_repeat_profile")).sendKeys("svvtTEST11!!");
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"customerProfileUpdatePassword\"]/div[4]/button")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Uspješno ste napravili promjene na korisničkom računu.", success.getText());
        Thread.sleep(3000);

        //log in with new password
        webDriver.findElement(By.id("email")).sendKeys("svvt.test@outlook.com");
        Thread.sleep(2000);
        webDriver.findElement(By.id("password")).sendKeys("svvtTEST11!!");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/button")).click();
        Thread.sleep(2000);

        String currentUrl = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/korisnicki-racun", currentUrl);
        System.out.println("Current url: " + currentUrl + "\nPassword changed successfully!");
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
