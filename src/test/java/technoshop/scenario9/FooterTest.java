package technoshop.scenario9;

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
class FooterTest {
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
    }

    @Test
    @Order(1)
    void testWoringHours () throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(2000);

        String text = webDriver.findElement(By.xpath("/html/body/footer/div/div/div[1]/div[2]/div/span")).getText();
        assertEquals("od 08h do 16h", text.substring(16, 29));

        System.out.println(text.substring(16, 29));
    }

    @Test
    @Order(2)
    void testFooterLinks () throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        String[] conditions = {
                "https://technoshop.ba/onama/15/stanic-trade",
                "https://technoshop.ba/drustvo/2/nacin-placanja",
                "https://technoshop.ba/drustvo/20/povrat-proizvoda-novca-i-reklamacije",
                "https://technoshop.ba/drustvo/22/dostava-i-isporuka",
                "https://technoshop.ba/drustvo/23/izjava-o-privatnosti-i-registracija-iqos",
                "https://technoshop.ba/drustvo/25/zastita-privatnosti",
                "https://technoshop.ba/drustvo/28/garancije",
                "https://technoshop.ba/drustvo/28/garancije",
                "https://technoshop.ba/marketing/26/brand-kit-2024",
                "https://technoshop.ba/marketing/27/vodic-za-sigurnu-online-trgovinu",
                "https://technoshop.ba/marketing/30/korisni-linkovi",
                "https://technoshop.ba/galerija/41/techno-shop-alta",
                "https://technoshop.ba/galerija/39/techno-shop-bulevar",
                "https://technoshop.ba/galerija/42/techno-shop-bihac-2",
                "https://technoshop.ba/galerija/40/uvijek-klik-ispred-svih"

        };
        for (int i = 0; i < conditions.length; i++) {

            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
            Thread.sleep(2000);
            WebElement linkElement = webDriver.findElement(By.xpath("//a[@href='" + conditions[i] + "']"));
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", linkElement);
            Thread.sleep(2000);

            String currentUrl = webDriver.getCurrentUrl();
            assertEquals(conditions[i], currentUrl);

            webDriver.navigate().back();
            Thread.sleep(2000);

        }
    }

    @Test
    @Order(3)
    void testSafeECommerce () throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(2000);

        WebElement button = webDriver.findElement(By.xpath("/html/body/div[17]/div/div/div[2]/a[1]"));
        button.click();
        //Thread.sleep(2000);

        String originalWindow = webDriver.getWindowHandle();
        for (String windowHandle : webDriver.getWindowHandles()) {
            webDriver.switchTo().window(windowHandle);
            Thread.sleep(2000);
        }
        String sigurnaKupovina = webDriver.getCurrentUrl();
        assertEquals("https://sigurnakupovina.ba/", sigurnaKupovina);

        webDriver.findElement(By.xpath("//*[@id=\"menu-item-3981\"]/a")).click();
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(2000);

        WebElement text = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[3]/div/div[1]/div/div/div/div[2]/h3/span"));
        assertEquals("Legitimno i legalno poslovanje",text.getText() );

        webDriver.close();
        Thread.sleep(2000);
        webDriver.switchTo().window(originalWindow);
        Thread.sleep(2000);

    }

    @Test
    @Order(4)
    void testProduzenaGarancija () throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(2000);

        WebElement button = webDriver.findElement(By.xpath("/html/body/div[17]/div/div/div[2]/a[2]"));
        button.click();
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0, 300)");
        Thread.sleep(1000);
        js.executeScript("window.scrollBy(0, -300)");
        Thread.sleep(3000);


        String text = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText();
        assertEquals("PRODUŽENA GARANCIJA", text);
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}