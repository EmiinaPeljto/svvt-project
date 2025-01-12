package technoshop.scenario7;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ProfileBarTest {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static WebDriverWait wait;

    @BeforeEach
    public void setUp() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://technoshop.ba/prijava";

        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement acceptAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[8]/div/div[2]/button")));
        acceptAllButton.click();
        Thread.sleep(3000);
    }

    @BeforeEach
    void logInEach() throws InterruptedException {


        WebElement emailField = webDriver.findElement(By.id("email"));
        emailField.sendKeys("svvttest@gmail.com");
        Thread.sleep(3000);

        WebElement passwordField = webDriver.findElement(By.id("password"));
        passwordField.sendKeys("StakleniSto1!");
        Thread.sleep(3000);

        WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"loginForm\"]/div[3]/button"));
        loginButton.click();
        Thread.sleep(3000);
    }

    @Test
    void testProfileBar() throws InterruptedException {
        String[] links = {
                "https://technoshop.ba/korisnicki-racun#at-1",
                "https://technoshop.ba/korisnicki-racun#at-2",
                "https://technoshop.ba/korisnicki-racun#at-3",
                "https://technoshop.ba/korisnicki-racun#at-4",
                "https://technoshop.ba/korisnicki-racun#at-5",
                "https://technoshop.ba/korisnicki-racun#at-6",
                "https://technoshop.ba/korisnicki-racun#at-8",
                "https://technoshop.ba/odjava"
        };

        for (int i = 0; i < links.length; i++) {

            if (links[i].equals("https://technoshop.ba/korisnicki-racun#at-1")) {
                webDriver.navigate().to(links[i]);
                WebElement myAccount = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[2]/div[1]/div[1]/h4"));
                assertEquals("MOJ RAČUN", myAccount.getText());
            } else if (links[i].equals("https://technoshop.ba/korisnicki-racun#at-2")) {
                WebElement orderButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[1]/div[2]/ul/li[2]"));
                orderButton.click();
                WebElement myOrders = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[2]/div[2]/div[1]/h4"));
                assertEquals("AKTIVNE NARUDŽBE", myOrders.getText());
                Thread.sleep(3000);
            } else if (links[i].equals("https://technoshop.ba/korisnicki-racun#at-3")) {
                WebElement detailsButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[1]/div[2]/ul/li[3]"));
                detailsButton.click();
                WebElement myOrders = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[2]/div[3]/div[1]/h4"));
                assertEquals("MOJI DETALJI", myOrders.getText());
                Thread.sleep(3000);
            } else if (links[i].equals("https://technoshop.ba/korisnicki-racun#at-4")) {
                WebElement changePasswordButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[1]/div[2]/ul/li[4]"));
                changePasswordButton.click();
                WebElement myOrders = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[2]/div[4]/div[1]/h4"));
                assertEquals("PROMJENA LOZINKE", myOrders.getText());
                Thread.sleep(3000);
            } else if (links[i].equals("https://technoshop.ba/korisnicki-racun#at-5")) {
                WebElement myAddressesButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[1]/div[2]/ul/li[5]"));
                myAddressesButton.click();
                WebElement myOrders = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[2]/div[5]/div[1]/h4"));
                assertEquals("MOJE ADRESE", myOrders.getText());
                Thread.sleep(3000);
            } else if (links[i].equals("https://technoshop.ba/korisnicki-racun#at-6")) {
                WebElement contactInfoButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[1]/div[2]/ul/li[6]"));
                contactInfoButton.click();
                WebElement myOrders = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[2]/div[6]/div[1]/h4"));
                assertEquals("PODACI ZA KONTAKTIRANJE", myOrders.getText());
                Thread.sleep(3000);
            } else if (links[i].equals("https://technoshop.ba/korisnicki-racun#at-8")) {
                WebElement invoicesButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[1]/div[2]/ul/li[8]"));
                invoicesButton.click();
                WebElement myOrders = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[2]/div[8]/div[1]/h4"));
                assertEquals("RAČUNI I GARANCIJE", myOrders.getText());
                Thread.sleep(3000);
            } else if (links[i].equals("https://technoshop.ba/odjava")) {
                WebElement logOutButton = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[1]/div[2]/ul/li[9]"));
                logOutButton.click();
                String currentUrl = webDriver.getCurrentUrl();
                assertTrue(true, currentUrl);
                Thread.sleep(3000);
            }
        }

    }

    @Test
    void testPresentButtonInProfileBar() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,500)", "");
        Thread.sleep(3000);

        WebElement presentButtun = webDriver.findElement(By.xpath("/html/body/div[10]/div/div[2]/div[1]/div[2]/ul/li[7]"));
        Actions actions1 = new Actions(webDriver);
        actions1.moveToElement(presentButtun).perform();
        Thread.sleep(3000);

        WebElement tooltip1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='tooltip' and @data-tooltip='Uskoro' and @data-tab='.at-7']")));
        String tooltipText1 = tooltip1.getAttribute("data-tooltip");
        assertEquals("Uskoro", tooltipText1);
        Thread.sleep(3000);

        presentButtun.click();
        String currentUrl = webDriver.getCurrentUrl();
        assertNotEquals("https://technoshop.ba/korisnicki-racun#at-7", currentUrl);
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
