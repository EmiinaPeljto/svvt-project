package technoshop.scenario14;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortTest {

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
    }

    @Test
    void testLowestPrice () throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement allProducts = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[3]"));
        allProducts.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,-300)");
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

        String lowestPriceString = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[2]/div[2]/div[1]/div[4]/div[1]/div/div[1]")).getText();
        System.out.println(lowestPriceString);

        String secondLowestPriceString = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[2]/div[2]/div[2]/div[4]/div[1]/div/div[1]")).getText();
        System.out.println(secondLowestPriceString);

        double lowestPrice = Double.parseDouble(lowestPriceString.substring(0, lowestPriceString.length() - 3).replace(",", "."));
        double secondLowestPrice = Double.parseDouble(secondLowestPriceString.substring(0, secondLowestPriceString.length() - 3).replace(",", "."));

        assertTrue(lowestPrice <= secondLowestPrice);
    }

    @Test
    void testHighestPrice() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement allProducts = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[3]"));
        allProducts.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,-300)");
        Thread.sleep(1000);

        WebElement sort = webDriver.findElement(By.xpath("//*[@id=\"requestFilterSort\"]"));
        sort.click();
        Thread.sleep(2000);

        WebElement highest = webDriver.findElement(By.xpath("//*[@id=\"requestFilterSort\"]/option[6]"));
        highest.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[2]/div[1]/div"))).click();
        Thread.sleep(4000);

        js.executeScript("window.scrollBy(0, 200)");
        Thread.sleep(2000);

        String highestPriceString = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[2]/div[2]/div[1]/div[4]/div[1]/div/div")).getText();
        System.out.println(highestPriceString);

        String secondHighestPriceString = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[2]/div[2]/div[3]/div[4]/div[1]/div/div")).getText();
        System.out.println(secondHighestPriceString);

        double highestPrice = Double.parseDouble(highestPriceString.replace(" KM", "").replace(".", "").replace("-", "").replace(",", "."));
        double secondHighestPrice = Double.parseDouble(secondHighestPriceString.replace(" KM", "").replace(".", "").replace("-", "").replace(",", "."));

        assertTrue(highestPrice >= secondHighestPrice);
    }

    @Test
    void testNumOfProducts () throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement allProducts = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[3]"));
        allProducts.click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,300)");
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0,-300)");
        Thread.sleep(1000);

        WebElement sort = webDriver.findElement(By.id("requestFilterLimit"));
        sort.click();
        Thread.sleep(2000);

        WebElement twentyFour = webDriver.findElement(By.xpath("//*[@id=\"requestFilterLimit\"]/option[2]"));
        twentyFour.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[2]/div[1]/div"))).click();
        Thread.sleep(4000);

        WebElement text = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[3]/div[2]/div[2]/div[1]/div/h4"));
        System.out.println(text.getText().substring(10, 12));
        assertEquals("24", text.getText().substring(10, 12));

    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
