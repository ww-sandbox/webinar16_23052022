package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
//    Metoda uruchamiana przed rozpoczęciem wykonywania testów w danej klasie. Zawiera wstępną konfigurację drivera
//    oraz otwieranie strony głównej

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
//    Metoda zamykająca okno przeglądarki po wszystkich testach w klasie
}
//  Tworzymy klasę bazową, która będzie posiadać metody wykorzystywane w większej ilości testów (metody wspólne) aby
//  zapobiec powielaniu kodu
