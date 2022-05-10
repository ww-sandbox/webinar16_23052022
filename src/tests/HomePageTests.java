package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest{
//    private WebDriver driver;
//
//    @BeforeClass
//    public void setUp(){
//        driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        driver.get("http://sampleshop.inqa.pl/");
//    }
////    Metoda uruchamiana przed rozpoczęciem wykonywania testów w danej klasie. Zawiera wstępną konfigurację drivera
////    oraz otwieranie strony głównej
//
//    @AfterClass
//    public void tearDown(){
//        driver.quit();
//    }
////    Metoda zamykająca okno przeglądarki po wszystkich testach w klasie
    @BeforeClass
    public void openHomePage(){
        driver.get("http://sampleshop.inqa.pl/");
    }

    @Test
    public void checkContactText(){
//        System.setProperty("webdriver.gecko.driver", "C:\\sciezka\\do\\drivera\\geckodriver.exe");
//    Konfiguracja wskazująca miejsce przechowywania pliku uruchamialnego (executable) drivera dla naszej przeglądarki
//    Metoda ta jest o tyle uciążliwa, że każdy członek zespołu (użytkownik kodu) musi mieć drivera w tym samym miejscu
//    lub dostosować tę linijkę lokalnie. Alternatywą jest przechowywanie drivera w katalogu głównym projektu
//        WebDriver driver = new FirefoxDriver();
//    Tworzenie instancji WebDrivera - w tym wypadku korzystam z FireFoxDrivera
//        driver.manage().window().maximize();
//    Maksymalizacja okna przeglądarki
//        driver.get("http://sampleshop.inqa.pl/");
//    Otwieranie strony głównej
//    Pierwotna wersja kodu - inicjalizacja drivera w każdej metodzie osobno (powtarzalny kod)

        WebElement we = driver.findElement(By.id("contact-link"));
//      Wyszukiwanie elementu
        String linkText = we.getText();
//      Pobieranie tekstu dla danego elementu

        Assert.assertEquals(linkText, "Kontakt z nami");
//      Test powinien się kończyć sprawdzeniem jakiegoś warunku (asercją)
//        driver.quit();
//        Zamykanie okna przeglądarki - wyciągnięte to metody typu TearDown
    }

    @Test
    public void checkHomePageTitle(){
//        WebDriver driver = new FirefoxDriver();
//        driver.manage().window().maximize();
//        driver.get("http://sampleshop.inqa.pl/");
        String title = driver.getTitle();
//        driver.quit();

        Assert.assertEquals(title, "Automation Sample Shop");
    }

    @Test(priority = 1)
    public void chooseShirtSize(){
        driver.get("http://sampleshop.inqa.pl/men/1-1-hummingbird-printed-t-shirt.html#/1-rozmiar-s/8-kolor-bialy");

        WebElement selectElement = driver.findElement(By.id("group_1"));
//        Wyszukanie na stronie elementu reprezentującego listę rozwijaną
        Select selectSize = new Select(selectElement);
//        Zmiana WebElementu na dedykowany typ do obsługi list rozwijanych

//        selectElement.
//        Gdybyśmy nie posiadali obiektu typu select, nie moglibyśmy skorzystać z metody selectBy.... ponieważ
//        wyszukany wcześniej obiekt jest typu WebElement

        selectSize.selectByVisibleText("M");
//        Wybieranie danej opcji po tekście widocznym na stronie

        WebElement selectedValueElement = selectSize.getFirstSelectedOption();
//        Pobranie wybranej aktualnie opcji w celu sprawdzenia czy jest ona poprawna
        String selectedValue = selectedValueElement.getText();
//        Pobranie tekstu z wyszukanej wcześniej opcji

//        String selectedValue = selectSize.getFirstSelectedOption().getText();
//        Dwie powyższe linijki możemy zaprezentować w formie jednej skraca to zapis, jednak może wpływać na czytelność
//        Przy większej ilości operacji warto rozbijać na kilka linijek

        Assert.assertEquals(selectedValue, "M");
    }

    @Test
    public void openMensCategory() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id("category-3"))).pause(1000).moveToElement(driver.findElement(By.id("category-4")))
                .click().build().perform();
        WebElement we = driver.findElement(By.cssSelector(".h1"));

        Assert.assertEquals(we.getText(), "MEN");

    }

    @Test
    public void openWomenCategory(){
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.id("category-3"))).pause(1000).moveToElement(driver.findElement(By.id("category-5")))
                .click().build().perform();
        WebElement we = driver.findElement(By.cssSelector(".h1"));

        Assert.assertEquals(we.getText(), "WOMEN");
    }

}
