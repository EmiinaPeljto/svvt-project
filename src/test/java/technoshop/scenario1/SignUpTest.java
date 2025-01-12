package technoshop.scenario1;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class SignUpTest {

    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeEach
    public void setUp() throws InterruptedException {
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
    }

    @Test
    public void testExistingOfAccount() throws InterruptedException {

        WebElement userIcon = webDriver.findElement(By.className("icon--action"));
        userIcon.click();
        Thread.sleep(3000);

        WebElement first_name = webDriver.findElement(By.name("first_name"));
        first_name.sendKeys("svvt");
        Thread.sleep(3000);

        WebElement last_name = webDriver.findElement(By.name("last_name"));
        last_name.sendKeys("test");
        Thread.sleep(3000);

        WebElement email = webDriver.findElement(By.id("email_register"));
        email.sendKeys("svvttest@gmail.com");
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(2000);

        WebElement password = webDriver.findElement(By.id("password_register"));
        password.sendKeys("StakleniSto1!");

        WebElement phone = webDriver.findElement(By.name("phone_number"));
        phone.sendKeys("+911234567");
        Thread.sleep(3000);

        WebElement dateOfBirth = webDriver.findElement(By.name("date_of_birth"));
        dateOfBirth.sendKeys("01/11/2002");
        Thread.sleep(3000);

        Select gender = new Select(webDriver.findElement(By.name("gender_id")));
        gender.selectByValue("2");
        Thread.sleep(3000);

        WebElement registration = webDriver.findElement(By.xpath("//*[@id=\"registerForm\"]/div[9]/button"));
        registration.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));

        System.out.println(element.getText());

        assertEquals("Došlo je do greške. E-mail adresa (svvttest@gmail.com) je već u upotrebi.", element.getText());
        webDriver.get(baseUrl);

    }

    @Test
    public void testNewAccount() throws InterruptedException {
        WebElement userIcon = webDriver.findElement(By.className("icon--action"));
        userIcon.click();
        Thread.sleep(3000);

        WebElement first_name = webDriver.findElement(By.name("first_name"));
        first_name.sendKeys("svvt");
        Thread.sleep(3000);

        WebElement last_name = webDriver.findElement(By.name("last_name"));
        last_name.sendKeys("test");
        Thread.sleep(3000);

        WebElement email = webDriver.findElement(By.id("email_register"));
        email.sendKeys("svvtutesssrt@gmail.com");
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(2000);

        WebElement password = webDriver.findElement(By.id("password_register"));
        password.sendKeys("StakleniSto1!");

        WebElement phone = webDriver.findElement(By.name("phone_number"));
        phone.sendKeys("+911234567");
        Thread.sleep(3000);

        WebElement dateOfBirth = webDriver.findElement(By.name("date_of_birth"));
        dateOfBirth.sendKeys("01/11/2002");
        Thread.sleep(3000);

        Select gender = new Select(webDriver.findElement(By.name("gender_id")));
        gender.selectByValue("2");
        Thread.sleep(3000);

        WebElement registration = webDriver.findElement(By.xpath("//*[@id=\"registerForm\"]/div[9]/button"));
        registration.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));

        System.out.println(element.getText());

        assertEquals("Uspješno ste registrovali račun.", element.getText());
        webDriver.get(baseUrl);

    }



    @AfterEach
    public void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }



}