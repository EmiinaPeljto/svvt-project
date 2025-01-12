package technoshop.scenario10;

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

public class AddToCartTest {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static WebDriverWait wait;

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

        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement acceptAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[17]/div/div[2]/button")));
        acceptAllButton.click();
        Thread.sleep(3000);
    }

    @Test
    void testAddToCartWithErrorMessage() throws InterruptedException {
        WebElement searchBar = webDriver.findElement(By.id("search"));
        searchBar.click();
        Thread.sleep(2000);

        searchBar.sendKeys("MacBook Pro 16-inch Apple M4");
        Thread.sleep(2000);

        WebElement searchIcon = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[1]/div/div/div/div/a"));
        searchIcon.click();
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(3000);

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#korpa-41463']")));
        addToCartButton.click();
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Uspješno ste dodali proizvod MacBook Pro 16-inch Apple M4 u košaricu.", message.getText());
        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0, -1000)", "");
        Thread.sleep(5000);

        WebElement cart = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[5]"));
        cart.click();
        Thread.sleep(3000);
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(3000);

        WebElement total = webDriver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[2]/ul/li[3]"));
        assertEquals("Ukupna cijena: 10.732,50 KM", total.getText().replace("\n"," "));
        Thread.sleep(3000);

        WebElement productInCart = webDriver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[1]/div[1]/div"));
        WebElement addOneMore = productInCart.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[3]"));
        addOneMore.click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Došlo je do greške. Odabrana količina je veća od dostupne.", error.getText());
        Thread.sleep(3000);

        //check if the number of products is still 1
        //number in cart cannot be negative
        WebElement minusOne = productInCart.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[1]/div[1]/div/div[2]/div[1]/div[1]"));
        minusOne.click();
        Thread.sleep(3000);
        WebElement currentNumberOfProdact = webDriver.findElement(By.xpath("//div[@class='qu__value']/input[@type='text' and @value='1' and @readonly]"));
        assertEquals("1", currentNumberOfProdact.getAttribute("value"));
        Thread.sleep(3000);


    }

    @Test
    void emptyCart() throws InterruptedException {
        WebElement cart = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[5]"));
        cart.click();
        Thread.sleep(3000);

        WebElement emptyCart = webDriver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div/h4"));
        assertEquals("Trenutno nemate proizvoda u košarici.", emptyCart.getText());
        Thread.sleep(3000);
    }

    @Test
    void testAddToCart() throws InterruptedException {
        WebElement searchBar = webDriver.findElement(By.id("search"));
        searchBar.click();
        Thread.sleep(2000);

        searchBar.sendKeys("hp mouse");
        Thread.sleep(2000);

        WebElement searchIcon = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[1]/div/div/img"));
        searchIcon.click();
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,500)", "");

        WebElement addToCartButton1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#korpa-29193']")));
        addToCartButton1.click();
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Uspješno ste dodali proizvod HP Z3700 Black Wireless Mouse u košaricu.", message.getText());
        Thread.sleep(3000);

        WebElement addToCartButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#korpa-31882']")));
        addToCartButton2.click();
        WebElement message1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Uspješno ste dodali proizvod HP 150 Wired Mouse mis u košaricu.", message1.getText());
        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0, -1000)", "");
        Thread.sleep(5000);

        WebElement cart = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[5]"));
        cart.click();
        Thread.sleep(3000);
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(3000);

        WebElement total = webDriver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[2]/ul/li[3]"));
        assertEquals("Ukupna cijena: 49,60 KM", total.getText().replace("\n"," "));
        Thread.sleep(3000);

        WebElement product1InCart = webDriver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[1]/div[1]/div[1]"));
        WebElement addOneMore = product1InCart.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[3]"));
        addOneMore.click();
        WebElement successfullyAdded = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Uspješno ste ažurirali proizvod HP Z3700 Black Wireless Mouse u košarici.", successfullyAdded.getText());
        Thread.sleep(3000);

        WebElement product2InCart = webDriver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[1]/div[1]/div[2]"));
        WebElement minusOne = product2InCart.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]"));
        minusOne.click();
        WebElement successfullyRemoved = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Uspješno ste ažurirali proizvod HP Z3700 Black Wireless Mouse u košarici.", successfullyRemoved.getText());
        Thread.sleep(3000);

        product2InCart = webDriver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[1]/div[1]/div[2]"));
        WebElement removeProduct = product2InCart.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[1]/div[1]/div[2]/div[2]/a/div/img"));
        removeProduct.click();
        WebElement successfullyRemovedProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Uspješno ste uklonili proizvod HP Z3700 Black Wireless Mouse iz košarice.", successfullyRemovedProduct.getText());
        Thread.sleep(3000);

        WebElement newTotal = webDriver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[2]/ul/li[3]"));
        assertEquals("Ukupna cijena: 14,85 KM", newTotal.getText().replace("\n"," "));
        Thread.sleep(3000);

        //Invalid coupon code
        WebElement coupon = webDriver.findElement(By.xpath("//*[@id=\"coupon\"]"));
        coupon.sendKeys("123");
        WebElement applyCoupon = webDriver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[1]/div[2]/button"));
        applyCoupon.click();
        WebElement invalidCoupon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Uneseni kupon kod nije postojeći.", invalidCoupon.getText());
        Thread.sleep(3000);
    }

    @AfterEach
    public void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
