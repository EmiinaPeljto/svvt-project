package technoshop.scenario13;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

public class FilterTest {
    private static WebDriver webDriver;
    private static String baseUrl;


    @BeforeAll
    public static void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\IBU\\Third year\\First semester\\Software Verification, Validation and Testing\\chromedriver-win64\\chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://technoshop.ba";

        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(5000);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement acceptAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[17]/div/div[2]/button")));
        acceptAllButton.click();
        Thread.sleep(5000);

        webDriver.navigate().to("https://technoshop.ba/proizvodi");
        Thread.sleep(2000);
    }

    @Test
    void testButtons() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        js.executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,-300)");
        Thread.sleep(1000);

        WebElement slider1 = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div[1]/div[2]/div[1]/label/span"));
        slider1.click();
        Thread.sleep(2000);

        WebElement checkbox1 = webDriver.findElement(By.id("category-1"));
        checkbox1.click();
        Thread.sleep(2000);
        WebElement checkbox2 = webDriver.findElement(By.id("category-3"));
        checkbox2.click();
        Thread.sleep(2000);

        WebElement showMore = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div[1]/div[4]/div[2]/div/span"));
        showMore.click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);

        WebElement manufacturer = webDriver.findElement(By.id("manufacturer-1"));
        manufacturer.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0, -300)");
        Thread.sleep(1000);

        WebElement sort = webDriver.findElement(By.xpath("//*[@id=\"requestFilterSort\"]"));
        sort.click();
        Thread.sleep(2000);

        WebElement lowest = webDriver.findElement(By.xpath("//*[@id=\"requestFilterSort\"]/option[5]"));
        lowest.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[2]/div[1]/div"))).click();
        Thread.sleep(4000);

        js.executeScript("window.scrollBy(0, 200)");
        Thread.sleep(2000);

        WebElement price = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[2]/div[2]/div[1]/div[4]/div[1]/div/div"));
        assertEquals("20", price.getText().substring(0,2));
    }

    @Test
    void testNotFound () throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        js.executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,-300)");
        Thread.sleep(1000);


        WebElement priceTo = webDriver.findElement(By.xpath("//*[@id=\"sidebar__price\"]/div/div[1]/div"));
        priceTo.click();
        Thread.sleep(2000);

        WebElement category = webDriver.findElement(By.id("category-7"));
        category.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,200)");
        Thread.sleep(1000);

        WebElement manufacturer = webDriver.findElement(By.id("manufacturer-2"));
        manufacturer.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,-200)");
        Thread.sleep(1000);

        String notFound = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[2]/div/div/h4")).getText();
        assertEquals("Nisu pronađeni rezultati koji odgovaraju Vašoj pretrazi.", notFound);
    }

    @AfterAll
    public static void tearDown() {
        if(webDriver != null) {
            webDriver.quit();
        }
    }
}
