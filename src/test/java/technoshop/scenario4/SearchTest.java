package technoshop.scenario4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

class SearchTest {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeEach
    public void setUp() throws InterruptedException {
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
    }
    @Test
    void testExistingProduct () throws InterruptedException {
        WebElement searchBar = webDriver.findElement(By.id("search"));
        searchBar.click();
        Thread.sleep(2000);

        searchBar.sendKeys("MacBook Pro 16-inch Apple M4");
        Thread.sleep(2000);

        WebElement searchIcon = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[1]/div/div/div/div/a"));
        searchIcon.click();
        Thread.sleep(2000);

        String macUrl = webDriver.getCurrentUrl();

        assertEquals("https://technoshop.ba/proizvodi/it-oprema/laptopi-i-racunari/laptopi/macbook-pro-16-inch-apple-m4-41463", macUrl);
    }

    @Test
    void testNonExistingProduct () throws InterruptedException {
        WebElement searchBar = webDriver.findElement(By.id("search"));
        searchBar.click();
        Thread.sleep(2000);

        searchBar.sendKeys("hgdhss");
        Thread.sleep(2000);

        WebElement searchButton = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[1]/div/div/img"));
        searchButton.click();
        Thread.sleep(2000);

        ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0, 300)");
        Thread.sleep(1000);
        ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0, -300)");
        Thread.sleep(1000);

        WebElement noResults = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[2]/div/div/h4"));

        assertEquals("Nisu pronađeni rezultati koji odgovaraju Vašoj pretrazi.", noResults.getText());
    }
    @AfterEach
    public void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}