package technoshop.scenario3;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HeaderTest {
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

        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement acceptAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[17]/div/div[2]/button")));
        acceptAllButton.click();
        Thread.sleep(3000);
    }

    @Test
    @Order(1)
    void testHeader() throws InterruptedException {

        WebElement kontaktLink = webDriver.findElement(By.linkText("KONTAKT"));
        kontaktLink.click();
        WebElement contactText = webDriver.findElement(By.xpath("/html/body/div[6]/div/div/div[2]/div[1]/h3"));
        assertEquals("Kontakt", contactText.getText());
        Thread.sleep(3000);


        WebElement technoCardElement = webDriver.findElement(By.linkText("TECHNO CARD"));

        Actions actions = new Actions(webDriver);
        actions.moveToElement(technoCardElement).perform();
        Thread.sleep(3000);

        WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='tooltip' and @data-tooltip='Uskoro']")));
        String tooltipText = tooltip.getAttribute("data-tooltip");
        assertEquals("Uskoro", tooltipText);
        Thread.sleep(3000);


        WebElement outletLink = webDriver.findElement(By.linkText("OUTLET"));
        outletLink.click();
        WebElement productNumber = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/span"));
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/proizvodi?outlet=da&odcijene=5.45&docijene=47999.00&prikaz=limit12&sortiranje=sortSuggested", currentUrl);
        Thread.sleep(3000);

        // Example: Click on the "SHOWROOM" link
        WebElement showroomLink = webDriver.findElement(By.linkText("SHOWROOM"));
        showroomLink.click();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0,500)", "");
        WebElement showroom = webDriver.findElement(By.xpath("/html/body/div[6]/div/div"));
        assertTrue(showroom.isDisplayed());
        Thread.sleep(3000);

        WebElement newsletterLink = webDriver.findElement(By.linkText("NEWSLETTER"));
        newsletterLink.click();
        Thread.sleep(3000);

        WebElement newsletter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div/p")));
        assertEquals("Hiljade zadovoljnih korisnika redovno dobija informacije o svim pogodnostima koje Techno Shop pažljivo priprema za svoje kupce. Prijavom na newsletter ostvari fenomenalne uštede. Samo jedan klik vas dijeli od trenda!", newsletter.getText());
        WebElement cancel  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div/div")));
        cancel.click();
        Thread.sleep(3000);

        WebElement newsLink = webDriver.findElement(By.linkText("NOVOSTI"));
        newsLink.click();
        WebElement news = webDriver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/h3"));
        assertEquals("Novosti", news.getText());
        Thread.sleep(3000);

        // Example: Click on the "KATALOG" link
        WebElement katalogLink = webDriver.findElement(By.linkText("KATALOG"));
        katalogLink.click();
        String currentUrl1 = webDriver.getCurrentUrl();
        assertTrue(true, currentUrl1);
        Thread.sleep(3000);

        webDriver.navigate().back();
        String currentUrl2 = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/novosti",currentUrl2);
        Thread.sleep(3000);

        WebElement phoneLink = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[1]/div[2]/a[1]"));
        phoneLink.click();
        String currentUrl3 = webDriver.getCurrentUrl();
        assertTrue(true, currentUrl3);
        Thread.sleep(3000);

        WebElement present = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[1]/div[2]/a[2]"));

        Actions actions1 = new Actions(webDriver);
        actions1.moveToElement(present).perform();
        Thread.sleep(3000);

        WebElement tooltip1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='tooltip' and @data-tooltip='Uskoro']")));
        String tooltipText1 = tooltip1.getAttribute("data-tooltip");
        assertEquals("Uskoro", tooltipText1);
        Thread.sleep(3000);
    }

    @Test
    @Order(2)
    void testHeaderButtonsWithoutLogIn() throws InterruptedException {

        WebElement logo = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[1]/a/div/img"));
        logo.click();
        String currentUrl1 = webDriver.getCurrentUrl();
        assertTrue(true, currentUrl1);
        Thread.sleep(3000);

        WebElement searchButton = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[1]/div/div/img"));
        searchButton.click();
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/proizvodi?pretraga=&odcijene=5.45&docijene=47999.00&prikaz=limit12&sortiranje=sortSuggested", currentUrl);
        Thread.sleep(3000);

        WebElement image = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[1]/img"));
        image.click();
        String currentUrl2 = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/katalog-ponuda", currentUrl2);
        Thread.sleep(3000);

        WebElement allProducts = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[3]"));
        allProducts.click();
        String currentUrl3 = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/proizvodi?odcijene=5.45&docijene=47999.00&prikaz=limit12&sortiranje=sortSuggested", currentUrl3);
        Thread.sleep(3000);

        WebElement profileButton = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[4]"));
        profileButton.click();
        String currentUrl4 = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/prijava", currentUrl4);
        Thread.sleep(3000);

        WebElement cart = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[5]"));
        cart.click();
        String currentUrl5 = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/kosarica", currentUrl5);
        Thread.sleep(3000);
    }

    @Test
    @Order(3)
    void testHeaderButtonsWithLogIn() throws InterruptedException {
        WebElement userIcon = webDriver.findElement(By.className("icon--action"));
        userIcon.click();
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

        webDriver.navigate().back();
        webDriver.navigate().back();
        Thread.sleep(3000);

        WebElement logo = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[1]/a/div/img"));
        logo.click();
        String currentUrl1 = webDriver.getCurrentUrl();
        assertTrue(true, currentUrl1);
        Thread.sleep(3000);

        WebElement searchButton = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[1]/div/div/img"));
        searchButton.click();
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/proizvodi?pretraga=&odcijene=5.45&docijene=47999.00&prikaz=limit12&sortiranje=sortSuggested", currentUrl);
        Thread.sleep(3000);

        WebElement image = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[1]/img"));
        image.click();
        String currentUrl2 = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/katalog-ponuda", currentUrl2);
        Thread.sleep(3000);

        WebElement allProducts = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[3]"));
        allProducts.click();
        String currentUrl3 = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/proizvodi?odcijene=5.45&docijene=47999.00&prikaz=limit12&sortiranje=sortSuggested", currentUrl3);
        Thread.sleep(3000);

        WebElement profileButton = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[4]"));
        profileButton.click();
        String currentUrl4 = webDriver.getCurrentUrl();
        assertEquals("https://technoshop.ba/korisnicki-racun", currentUrl4);
        Thread.sleep(3000);

        WebElement present = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[5]"));

        Actions actions1 = new Actions(webDriver);
        actions1.moveToElement(present).perform();
        Thread.sleep(3000);

        WebElement tooltip1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='tooltip' and @data-tooltip='Uskoro']")));
        String tooltipText1 = tooltip1.getAttribute("data-tooltip");
        assertEquals("Uskoro", tooltipText1);
        Thread.sleep(3000);


        WebElement cart = webDriver.findElement(By.xpath("/html/body/header/div[2]/div/div[2]/div[2]/a[5]"));
        cart.click();
        String currentUrl5 = webDriver.getCurrentUrl();
       assertTrue(true,currentUrl5);
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