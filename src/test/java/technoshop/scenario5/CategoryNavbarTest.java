package technoshop.scenario5;

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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CategoryNavbarTest {
    private static WebDriver webDriver;
    private static String baseUrl;
    public static WebDriverWait wait;

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

        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement acceptAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[17]/div/div[2]/button")));
        acceptAllButton.click();
        Thread.sleep(5000);
    }

    @Test
    @Order(1)
    void testTvAudio() throws InterruptedException {
        String [] tvAudio = {
                "https://technoshop.ba/proizvodi/tv-i-audio/tv",
                "https://technoshop.ba/proizvodi/tv-i-audio/audio",
                "https://technoshop.ba/proizvodi/tv-i-audio/video"
        };
        WebElement tvAudioDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div")));
        tvAudioDropdown.click();
        Thread.sleep(2000);


        List<WebElement> tvAudioOptionTv = webDriver.findElements(By.xpath("//h4[contains(.,'TV I AUDIO')]"));

        for (int i = 0; i < tvAudio.length; i++) {
            //String originalWindow = webDriver.getWindowHandle();
            if (tvAudio[i].equals("https://technoshop.ba/proizvodi/tv-i-audio/tv")) {
                webDriver.navigate().to(tvAudio[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("TV")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("TV", data.getText());
                }
                String[] tvLinks = {
                        "https://technoshop.ba/proizvodi/tv-i-audio/tv/led",
                        "https://technoshop.ba/proizvodi/tv-i-audio/tv/mini-led",
                        "https://technoshop.ba/proizvodi/tv-i-audio/tv/oled",
                        "https://technoshop.ba/proizvodi/tv-i-audio/tv/nanocell",
                        "https://technoshop.ba/proizvodi/tv-i-audio/tv/qled",
                        "https://technoshop.ba/proizvodi/tv-i-audio/tv/qned",
                        "https://technoshop.ba/proizvodi/tv-i-audio/tv/uled",
                        "https://technoshop.ba/proizvodi/tv-i-audio/tv/nosaci-za-tv",
                        "https://technoshop.ba/proizvodi/tv-i-audio/tv/produzena-garancija",
                };
                for (int j = 0; j < tvLinks.length; j++) {
                    webDriver.navigate().to(tvLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (tvAudio[i].equals("https://technoshop.ba/proizvodi/tv-i-audio/audio")) {
                webDriver.navigate().to(tvAudio[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("AUDIO")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("AUDIO", data.getText());
                }
                String[] audioLinks = {
                        "https://technoshop.ba/proizvodi/tv-i-audio/audio/soundbar",
                        "https://technoshop.ba/proizvodi/tv-i-audio/audio/slusalice",
                        "https://technoshop.ba/proizvodi/tv-i-audio/audio/hi-fi"
                };
                for (int j = 0; j < audioLinks.length; j++) {
                    webDriver.navigate().to(audioLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else {
                webDriver.navigate().to(tvAudio[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("VIDEO")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("VIDEO", data.getText());
                }
                String [] videoLinks = {
                        "https://technoshop.ba/proizvodi/tv-i-audio/video/projektori",
                        "https://technoshop.ba/proizvodi/tv-i-audio/video/kamere",
                        "https://technoshop.ba/proizvodi/tv-i-audio/video/dronovi",
                        "https://technoshop.ba/proizvodi/tv-i-audio/video/fotoaparati"
                };
                for (int j = 0; j < videoLinks.length; j++) {
                    webDriver.navigate().to(videoLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
        }

    }

    @Test
    @Order(2)
    void testBijelaTehinka() throws InterruptedException {
        String [] bijelaTehnika = {
                "https://technoshop.ba/proizvodi/bijela-tehnika/kuhanje-i-pecenje",
                "https://technoshop.ba/proizvodi/bijela-tehnika/ves-masine-i-susilice",
                "https://technoshop.ba/proizvodi/bijela-tehnika/frizideri-i-zamrzivaci",
                "https://technoshop.ba/proizvodi/bijela-tehnika/pranje-posuda"
        };
        WebElement bijelaTehnikaDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div")));
        bijelaTehnikaDropdown.click();
        Thread.sleep(2000);

        for (int i = 0; i < bijelaTehnika.length; i++) {
            //String originalWindow = webDriver.getWindowHandle();
            if (bijelaTehnika[i].equals("https://technoshop.ba/proizvodi/bijela-tehnika/kuhanje-i-pecenje")) {
                webDriver.navigate().to(bijelaTehnika[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("KUHANJE I PEČENJE")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("KUHANJE I PEČENJE", data.getText());
                }
                String[] kuhanjePecenjeLinks = {
                        "https://technoshop.ba/proizvodi/bijela-tehnika/kuhanje-i-pecenje/ugradbene-ploce",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/kuhanje-i-pecenje/ugradbene-pecnice",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/kuhanje-i-pecenje/ugradbene-mikrovalne",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/kuhanje-i-pecenje/samostojece-mikrovalne",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/kuhanje-i-pecenje/stednjaci",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/kuhanje-i-pecenje/kuhinjske-nape"
                };
                for (int j = 0; j < kuhanjePecenjeLinks.length; j++) {
                    webDriver.navigate().to(kuhanjePecenjeLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (bijelaTehnika[i].equals("https://technoshop.ba/proizvodi/bijela-tehnika/ves-masine-i-susilice")) {
                webDriver.navigate().to(bijelaTehnika[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("VEŠ MAŠINE I SUŠILICE")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("VEŠ MAŠINE I SUŠILICE", data.getText());
                }
                String[] vesMasineSusiliceLinks = {
                        "https://technoshop.ba/proizvodi/bijela-tehnika/ves-masine-i-susilice/susilice",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/ves-masine-i-susilice/ves-masine",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/ves-masine-i-susilice/masine-za-pranje-i-susenje",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/ves-masine-i-susilice/profesional"
                };
                for (int j = 0; j < vesMasineSusiliceLinks.length; j++) {
                    webDriver.navigate().to(vesMasineSusiliceLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (bijelaTehnika[i].equals("https://technoshop.ba/proizvodi/bijela-tehnika/frizideri-i-zamrzivaci")) {
                webDriver.navigate().to(bijelaTehnika[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("FRIŽIDERI I ZAMRZIVAČI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("FRIŽIDERI I ZAMRZIVAČI", data.getText());
                }
                String[] frizideriZamrzivaciLinks = {
                        "https://technoshop.ba/proizvodi/bijela-tehnika/frizideri-i-zamrzivaci/frizideri",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/frizideri-i-zamrzivaci/kombinovani-frizideri",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/frizideri-i-zamrzivaci/side-by-side",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/frizideri-i-zamrzivaci/ugradbeni-frizideri",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/frizideri-i-zamrzivaci/zamrzivaci",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/frizideri-i-zamrzivaci/rashladne-vitrine",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/frizideri-i-zamrzivaci/ugradbeni-zamrzivaci"
                };
                for (int j = 0; j < frizideriZamrzivaciLinks.length; j++) {
                    webDriver.navigate().to(frizideriZamrzivaciLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else {
                webDriver.navigate().to(bijelaTehnika[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("PRANJE POSUĐA")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("PRANJE POSUĐA", data.getText());
                }
                String [] pranjePosudjaLinks = {
                        "https://technoshop.ba/proizvodi/bijela-tehnika/pranje-posuda/ugradbene-masine-za-sude",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/pranje-posuda/samostojece-masine-za-sude",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/pranje-posuda/sudoperi",
                        "https://technoshop.ba/proizvodi/bijela-tehnika/pranje-posuda/cesme"
                };
                for (int j = 0; j < pranjePosudjaLinks.length; j++) {
                    webDriver.navigate().to(pranjePosudjaLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
        }

    }

    @Test
    @Order(3)
    void testMaliKucanskiAparati() throws InterruptedException {
        String [] maliKucanskiAparati = {
                "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati",
                "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje",
                "https://technoshop.ba/proizvodi/mali-kucanski-aparati/pegle",
                "https://technoshop.ba/proizvodi/mali-kucanski-aparati/usisivaci"
        };
        WebElement bijelaTehnikaDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div")));
        bijelaTehnikaDropdown.click();
        Thread.sleep(2000);

        for (int i = 0; i < maliKucanskiAparati.length; i++) {
            //String originalWindow = webDriver.getWindowHandle();
            if (maliKucanskiAparati[i].equals("https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati")) {
                webDriver.navigate().to(maliKucanskiAparati[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("KUHINJSKI APARATI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("KUHINJSKI APARATI", data.getText());
                }
                String[] kuhinjskiAparatiLinks = {
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/rostilji",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/pekaci-hljeba",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/friteze",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/blenderi-i-sjeckalice",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/sokovnici-i-citrusete",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/kuhala-za-vodu",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/kafe-aparati",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/mikseri",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/multipraktici",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/rezalice",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/tosteri",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/kuhinjske-vage",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/kuhinjski-aparati/aparati-za-tjesteninu"
                };
                for (int j = 0; j < kuhinjskiAparatiLinks.length; j++) {
                    webDriver.navigate().to(kuhinjskiAparatiLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (maliKucanskiAparati[i].equals("https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje")) {
                webDriver.navigate().to(maliKucanskiAparati[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("LJEPOTA I ZDRAVLJE")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("LJEPOTA I ZDRAVLJE", data.getText());
                }
                String[] ljepotaZdravljeLinks = {
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/fenovi",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/pegle-i-uvijaci-za-kosu",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/epilatori",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/aparati-za-brijanje",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/aparati-za-sisanje",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/trimeri",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/masazeri",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/tjelesne-vage",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/elektricne-cetke-za-zube",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/prociscivaci-zraka",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/ovlazivaci-zraka",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/odvlazivaci-zraka",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/ljepota-i-zdravlje/baby"
                };
                for (int j = 0; j < ljepotaZdravljeLinks.length; j++) {
                    webDriver.navigate().to(ljepotaZdravljeLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (maliKucanskiAparati[i].equals("https://technoshop.ba/proizvodi/mali-kucanski-aparati/pegle")) {
                webDriver.navigate().to(maliKucanskiAparati[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("PEGLE")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("PEGLE", data.getText());
                }
                String[] pegleLinks = {
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/pegle/pegle-na-paru",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/pegle/parne-postaje",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/pegle/vertikalno-peglanje",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/pegle/daske-za-peglanje"
                };
                for (int j = 0; j < pegleLinks.length; j++) {
                    webDriver.navigate().to(pegleLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else {
                webDriver.navigate().to(maliKucanskiAparati[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("USISIVAČI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("USISIVAČI", data.getText());
                }
                String[] usisivaciLinks = {
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/usisivaci/usisivaci-sa-kesom",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/usisivaci/usisivaci-sa-posudom",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/usisivaci/stapni-usisivaci",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/usisivaci/rucni-usisivaci",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/usisivaci/robot-usisivaci",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/usisivaci/dodatna-oprema",
                        "https://technoshop.ba/proizvodi/mali-kucanski-aparati/usisivaci/visokotlacni-cistaci"
                };
                for (int j = 0; j < usisivaciLinks.length; j++) {
                    webDriver.navigate().to(usisivaciLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
        }

    }

    @Test
    @Order(4)
    void testHladjenjeGrijanje() throws InterruptedException {
        String[] hladnjenjeGrijanjeLinks = {
                "https://technoshop.ba/proizvodi/hladenje-i-grijanje/klime-inverter",
                "https://technoshop.ba/proizvodi/hladenje-i-grijanje/klime-on-off",
                "https://technoshop.ba/proizvodi/hladenje-i-grijanje/klime-mobilne",
                "https://technoshop.ba/proizvodi/hladenje-i-grijanje/ventilatori",
                "https://technoshop.ba/proizvodi/hladenje-i-grijanje/konvektori",
                "https://technoshop.ba/proizvodi/hladenje-i-grijanje/grijalice",
                "https://technoshop.ba/proizvodi/hladenje-i-grijanje/bojleri",
                "https://technoshop.ba/uploads/files/ovlasteni_montazeri.xlsx.pdf?t=1"
        };
        WebElement hladjenjeGrijanjeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div/ul/li[4]")));
        hladjenjeGrijanjeDropdown.click();
        Thread.sleep(2000);

        for (int i = 0; i < hladnjenjeGrijanjeLinks.length; i++) {
            webDriver.navigate().to(hladnjenjeGrijanjeLinks[i]);
            Thread.sleep(2000);

            if (hladnjenjeGrijanjeLinks[i].equals("https://technoshop.ba/proizvodi/hladenje-i-grijanje/klime-inverter")) {
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("KLIME INVERTER")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("KLIME INVERTER", data.getText());

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (hladnjenjeGrijanjeLinks[i].equals("https://technoshop.ba/proizvodi/hladenje-i-grijanje/klime-on-off")) {
                WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
                if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"))).getText().equals("KLIME ON/OFF")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("KLIME ON/OFF", data.getText());

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (hladnjenjeGrijanjeLinks[i].equals("https://technoshop.ba/proizvodi/hladenje-i-grijanje/klime-mobilne")) {
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("KLIME MOBILNE")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("KLIME MOBILNE", data.getText());

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (hladnjenjeGrijanjeLinks[i].equals("https://technoshop.ba/proizvodi/hladenje-i-grijanje/ventilatori")) {
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("VENTILATORI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("VENTILATORI", data.getText());

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (hladnjenjeGrijanjeLinks[i].equals("https://technoshop.ba/proizvodi/hladenje-i-grijanje/konvektori")) {
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("KONVEKTORI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("KONVEKTORI", data.getText());

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (hladnjenjeGrijanjeLinks[i].equals("https://technoshop.ba/proizvodi/hladenje-i-grijanje/grijalice")) {
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("GRIJALICE")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("GRIJALICE", data.getText());

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (hladnjenjeGrijanjeLinks[i].equals("https://technoshop.ba/proizvodi/hladenje-i-grijanje/bojleri")) {
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("BOJLERI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("BOJLERI", data.getText());

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else {

                String currentWindow = webDriver.getCurrentUrl();
                assertEquals("https://technoshop.ba/uploads/files/ovlasteni_montazeri.xlsx.pdf?t=1", currentWindow);
                webDriver.navigate().to(baseUrl);
                Thread.sleep(2000);
            }
        }

    }

    @Test
    @Order(5)
    void testItOprema() throws InterruptedException {
        String [] itOprema = {
                "https://technoshop.ba/proizvodi/it-oprema/laptopi-i-racunari",
                "https://technoshop.ba/proizvodi/it-oprema/tableti",
                "https://technoshop.ba/proizvodi/it-oprema/monitori",
                "https://technoshop.ba/proizvodi/it-oprema/periferija-i-dodaci"
        };
        WebElement itOpremaDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div/ul/li[5]")));
        itOpremaDropdown.click();
        Thread.sleep(2000);

        for (int i = 0; i < itOprema.length; i++) {
            //String originalWindow = webDriver.getWindowHandle();
            if (itOprema[i].equals("https://technoshop.ba/proizvodi/it-oprema/laptopi-i-racunari")) {
                webDriver.navigate().to(itOprema[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("LAPTOPI I RAČUNARI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("LAPTOPI I RAČUNARI", data.getText());
                }
                String[] racunariLinks = {
                        "https://technoshop.ba/proizvodi/it-oprema/laptopi-i-racunari/laptopi",
                        "https://technoshop.ba/proizvodi/it-oprema/laptopi-i-racunari/gaming-laptopi",
                        "https://technoshop.ba/proizvodi/it-oprema/laptopi-i-racunari/aio"
                };
                for (int j = 0; j < racunariLinks.length; j++) {
                    webDriver.navigate().to(racunariLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (itOprema[i].equals("https://technoshop.ba/proizvodi/it-oprema/tableti")) {
                webDriver.navigate().to(itOprema[i]);

                ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                Thread.sleep(1000);

                ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                Thread.sleep(1000);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("TABLETI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("TABLETI", data.getText());
                }
            }
            else if (itOprema[i].equals("https://technoshop.ba/proizvodi/it-oprema/monitori")) {
                webDriver.navigate().to(itOprema[i]);

                ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                Thread.sleep(1000);

                ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                Thread.sleep(1000);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("MONITORI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("MONITORI", data.getText());
                }
            }
            else {
                webDriver.navigate().to(itOprema[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("PERIFERIJA I DODACI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("PERIFERIJA I DODACI", data.getText());
                }
                String[] periferijaDodaciLinks = {
                        "https://technoshop.ba/proizvodi/it-oprema/periferija-i-dodaci/misevi",
                        "https://technoshop.ba/proizvodi/it-oprema/periferija-i-dodaci/tastature",
                        "https://technoshop.ba/proizvodi/it-oprema/periferija-i-dodaci/memorije",
                        "https://technoshop.ba/proizvodi/it-oprema/periferija-i-dodaci/mikrofoni",
                        "https://technoshop.ba/proizvodi/it-oprema/periferija-i-dodaci/stolice",
                        "https://technoshop.ba/proizvodi/it-oprema/periferija-i-dodaci/stolovi"
                };
                for (int j = 0; j < periferijaDodaciLinks.length; j++) {
                    webDriver.navigate().to(periferijaDodaciLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
        }

    }

    @Test
    @Order(6)
    void testMobilniNavigacije() throws InterruptedException {
        String[] mobilniNavigacijeLinks = {
                "https://technoshop.ba/proizvodi/mobilni-i-navigacije/pametni-telefoni",
                "https://technoshop.ba/proizvodi/mobilni-i-navigacije/pametni-satovi",
                "https://technoshop.ba/proizvodi/mobilni-i-navigacije/navigacije",
                "https://technoshop.ba/proizvodi/mobilni-i-navigacije/dodaci"
        };
        WebElement mobilniNavigacijeDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div/ul/li[6]")));
        mobilniNavigacijeDropdown.click();
        Thread.sleep(2000);

        for (int i = 0; i < mobilniNavigacijeLinks.length; i++) {
            webDriver.navigate().to(mobilniNavigacijeLinks[i]);
            Thread.sleep(2000);

            if (mobilniNavigacijeLinks[i].equals("https://technoshop.ba/proizvodi/mobilni-i-navigacije/pametni-telefoni")) {
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("PAMETNI TELEFONI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("PAMETNI TELEFONI", data.getText());

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (mobilniNavigacijeLinks[i].equals("https://technoshop.ba/proizvodi/mobilni-i-navigacije/pametni-satovi")) {
                WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
                if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"))).getText().equals("PAMETNI SATOVI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("PAMETNI SATOVI", data.getText());

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (mobilniNavigacijeLinks[i].equals("https://technoshop.ba/proizvodi/mobilni-i-navigacije/navigacije")) {
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("NAVIGACIJE")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("NAVIGACIJE", data.getText());

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else  {
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("DODACI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("DODACI", data.getText());

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    ((JavascriptExecutor) webDriver).executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
        }

    }

    @Test
    @Order(7)
    void testGamingFun() throws InterruptedException {
        String [] gamingFun = {
                "https://technoshop.ba/proizvodi/gaming-and-fun/konzole",
                "https://technoshop.ba/proizvodi/gaming-and-fun/igre-i-dodaci",
                "https://technoshop.ba/proizvodi/gaming-and-fun/romobili-i-bicikla"
        };
        WebElement gamingFunDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div/ul/li[7]")));
        gamingFunDropdown.click();
        Thread.sleep(2000);

        for (int i = 0; i < gamingFun.length; i++) {
            //String originalWindow = webDriver.getWindowHandle();
            if (gamingFun[i].equals("https://technoshop.ba/proizvodi/gaming-and-fun/konzole")) {
                webDriver.navigate().to(gamingFun[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1")).getText().equals("KONZOLE")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[8]/div/div[1]/div[2]/h1"));
                    assertEquals("KONZOLE", data.getText());
                }
                String[] kozoleLinks = {
                        "https://technoshop.ba/proizvodi/gaming-and-fun/konzole/playstation",
                        "https://technoshop.ba/proizvodi/gaming-and-fun/konzole/nintendo"
                };
                for (int j = 0; j < kozoleLinks.length; j++) {
                    webDriver.navigate().to(kozoleLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
            else if (gamingFun[i].equals("https://technoshop.ba/proizvodi/gaming-and-fun/igre-i-dodaci")) {
                webDriver.navigate().to(gamingFun[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("IGRE I DODACI")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("IGRE I DODACI", data.getText());
                }
                String igre = "https://technoshop.ba/proizvodi/gaming-and-fun/igre-i-dodaci/igre";
                webDriver.navigate().to(igre);
                Thread.sleep(3000);
                JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                scrolDown.executeScript("window.scrollBy(0,300)", "");
                Thread.sleep(1000);

                JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                scrolUp.executeScript("window.scrollBy(0,-300)", "");
                Thread.sleep(1000);

            }
            else {
                webDriver.navigate().to(gamingFun[i]);

                //assertion if
                if (webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1")).getText().equals("ROMOBILI I BICIKLA")) {
                    WebElement data = webDriver.findElement(By.xpath("/html/body/div[7]/div/div[1]/div[2]/h1"));
                    assertEquals("ROMOBILI I BICIKLA", data.getText());
                }
                String [] romobiliBiciklaLinks = {
                        "https://technoshop.ba/proizvodi/gaming-and-fun/romobili-i-bicikla/elektricni-romobili",
                        "https://technoshop.ba/proizvodi/gaming-and-fun/romobili-i-bicikla/elektricni-bicikli"
                };
                for (int j = 0; j < romobiliBiciklaLinks.length; j++) {
                    webDriver.navigate().to(romobiliBiciklaLinks[j]);
                    Thread.sleep(3000);
                    JavascriptExecutor scrolDown = (JavascriptExecutor) webDriver;
                    scrolDown.executeScript("window.scrollBy(0,300)", "");
                    Thread.sleep(1000);

                    JavascriptExecutor scrolUp = (JavascriptExecutor) webDriver;
                    scrolUp.executeScript("window.scrollBy(0,-300)", "");
                    Thread.sleep(1000);
                }
            }
        }

    }

    @Test
    @Order(8)
    void testBrandZone() throws InterruptedException {
        String originalWindow = webDriver.getWindowHandle();
        String[] newCatsLinks = {
                "https://technoshop.ba/philips-landing.php",
                "https://technoshop.ba/demo/dyson/",
                "https://technoshop.ba/whirlpool-landing.php",
                "https://technoshop.ba/philips-series.php",
                "https://technoshop.ba/philips-care.php",
                "https://technoshop.ba/philips-micro.php"
        };

        int i = 0;
        while (i < newCatsLinks.length) {
            WebElement brandZone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[2]/div/ul/li[8]")));
            brandZone.click();
            Thread.sleep(2000);

            if (!(newCatsLinks[i].equals("https://technoshop.ba/demo/dyson/"))) {
                WebElement linkElement = webDriver.findElement(By.xpath("//a[@href='" + newCatsLinks[i] + "']"));
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", linkElement);
                Thread.sleep(2000);

                wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
                wait.until(ExpectedConditions.numberOfWindowsToBe(2));

                for (String windowHandle : webDriver.getWindowHandles()) {
                    if (!originalWindow.contentEquals(windowHandle)) {
                        webDriver.switchTo().window(windowHandle);
                        Thread.sleep(2000);
                        break;
                    }
                }

                String currentUrl = webDriver.getCurrentUrl();
                assertEquals(newCatsLinks[i], currentUrl);

                webDriver.close();
                Thread.sleep(2000);
                webDriver.switchTo().window(originalWindow);
                Thread.sleep(2000);
            } else {
                WebElement linkElement = webDriver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[9]/div/a[2]"));
                ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", linkElement);
                Thread.sleep(2000);
                wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
                wait.until(ExpectedConditions.numberOfWindowsToBe(2));

                for (String windowHandle : webDriver.getWindowHandles()) {
                    if (!originalWindow.contentEquals(windowHandle)) {
                        webDriver.switchTo().window(windowHandle);
                        Thread.sleep(2000);
                        break;
                    }
                }

                webDriver.close();
                Thread.sleep(2000);
                webDriver.switchTo().window(originalWindow);
                Thread.sleep(2000);
                webDriver.navigate().to(baseUrl);
            }

            i++;
        }
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }

}