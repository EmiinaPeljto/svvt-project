package technoshop.scenario11;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderLoggedInTest {
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

        webDriver.get("https://technoshop.ba/proizvodi/it-oprema/periferija-i-dodaci/misevi/hp-z3700-black-wireless-mouse-29193#korpa-29193");
        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(3000);

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#korpa-29193']")));
        addToCartButton.click();
        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0,-10000)", "");
        Thread.sleep(3000);

        WebElement cart = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[6]"));
        cart.click();
        Thread.sleep(3000);

    }

    @Test
    @Order(1)
    void emptyInputBox() throws InterruptedException {
        WebElement cart = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[6]"));
        cart.click();
        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(3000);

        WebElement continueButton = webDriver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div[2]/a/button"));
        continueButton.click();
        Thread.sleep(3000);

        WebElement payment_address = webDriver.findElement(By.id("payment_address"));
        payment_address.click();

        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"payment_address\"]/option[2]")));
        option.click();
        assertEquals("kuca", option.getText());
        Thread.sleep(3000);

        WebElement edditAddress = webDriver.findElement(By.xpath("//*[@id=\"checkout\"]/div/div[1]/div[1]/div[3]/div"));
        edditAddress.click();
        Thread.sleep(3000);
        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(3000);

        WebElement name = webDriver.findElement(By.id("payment_name"));
        name.clear();

        js.executeScript("window.scrollBy(0,100)", "");

        WebElement continueButton2 = webDriver.findElement(By.xpath("//*[@id=\"checkout\"]/div/div[2]/button"));
        js.executeScript("arguments[0].click();", continueButton2); // Use JavaScript to click the button
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Došlo je do greške. Obavezna polja nisu ispunjena.", errorMessage.getText());
        Thread.sleep(5000);
    }

    @Test
    @Order(2)
    void userInformation() throws InterruptedException {

        WebElement name = webDriver.findElement(By.id("payment_name"));
        name.sendKeys("Svvt");
        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0,500)", "");

        WebElement continueButton2 = webDriver.findElement(By.xpath("//*[@id=\"checkout\"]/div/div[2]/button"));
        continueButton2.click();
        WebElement successfully = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("swal2-html-container")));
        assertEquals("Preusmjeravanje na stranicu za plaćanje. Molimo pričekajte.", successfully.getText());
        Thread.sleep(3000);

        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(3000);

    }

    @Test
    @Order(3)
    void orderInformationTest() throws InterruptedException {
        WebElement shipment = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"payment\"]/div/div/div[1]/div[1]/div[2]/ul/li[1]")));
        assertEquals("Dostava: 9,00 KM", shipment.getText().replace("\n"," "));
        Thread.sleep(3000);

        WebElement checkBox1 = webDriver.findElement(By.xpath("//*[@id=\"payment\"]/div/div/div[2]/div[1]/div[2]/div[1]/div"));
        checkBox1.click();
        WebElement text1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"payment\"]/div/div/div[2]/div[1]/div[2]/div[2]/p")));
        assertTrue(text1.isDisplayed());
        Thread.sleep(3000);

        WebElement checkBox2 = webDriver.findElement(By.xpath("//*[@id=\"payment\"]/div/div/div[2]/div[1]/div[3]/div[1]/div"));
        checkBox2.click();
        Thread.sleep(3000);
        Select rate = new Select(webDriver.findElement(By.id("installments")));
        rate.selectByValue("2");
        assertEquals("2 rate", rate.getFirstSelectedOption().getText());
        Thread.sleep(3000);

        WebElement checkBox3 = webDriver.findElement(By.xpath("//*[@id=\"payment\"]/div/div/div[2]/div[1]/div[1]/div[1]/div"));
        checkBox3.click();
        WebElement text2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"payment\"]/div/div/div[2]/div[1]/div[1]/div[2]/p")));
        assertEquals("Pošiljku plaćate dostavljaču prilikom preuzimanja.", text2.getText());
        Thread.sleep(3000);

        WebElement messageBox = webDriver.findElement(By.id("note"));
        messageBox.sendKeys("Ova narudzma je napravljena u svrku testiranja web stranice za SVVT projekat");
        Thread.sleep(3000);

    }

    @Test
    @Order(4)
    void invalidGiftCardCode () throws InterruptedException {

        js.executeScript("window.scrollBy(0,500)", "");

        WebElement giftCard = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"giftcard\"]")));
        giftCard.sendKeys("123456789");
        Thread.sleep(3000);

        WebElement applyButton = webDriver.findElement(By.xpath("//*[@id=\"payment\"]/div/div/div[2]/div[3]/div/button"));
        applyButton.click();
        System.out.println("Ovaj test ne može proći jer ne postoji gift card sa ovim kodom");
        Thread.sleep(3000);

        //dalje testiranje ne možemo nastaviti jer se narudžba finalizira
    }

    @Test
    @Order(5)
    void checkCartAfterOrder() throws InterruptedException {
        js.executeScript("window.scrollBy(0,-1000)", "");
        WebElement cart = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[6]"));
        cart.click();
        Thread.sleep(5000);
        WebElement emptyCart = webDriver.findElement(By.xpath("/html/body/div[7]/div/div/div[2]/div/h4"));
        assertEquals("Trenutno nemate proizvoda u košarici.", emptyCart.getText());
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