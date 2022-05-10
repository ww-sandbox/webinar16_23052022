package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TheInternetTests extends BaseTest{


    @Test
    public void loadingTest(){
        driver.get("http://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.cssSelector("div#start button")).click();
//        W tym przypadku nie tworzymy instancji obiektu WebElement. Zamiast tego działamy na obiektach
//        zwracanych z kolejnych metod.

        String loadingText = driver.findElement(By.cssSelector("#loading")).getText();
        System.out.println(loadingText);

        Assert.assertEquals(loadingText, "Loading...");
//        Najpierw wyszukujemy element, a następnie sprawdzamy czy ma poprawny tekst. Można to zastąpić xptahem
//        //div[@id='loading' and contains(., 'Loading')]
//        W tym wypadku za jednym zamachem sprawdzamy czy istnieje element o odpowiednim id ze wskazanym tekstem

        WebDriverWait wait = new WebDriverWait(driver, 10);
//        Tworzymy instancję obiektu wait (oczekiwanie na zdarzenie), do konstruktora przekazujemy instancję drivera
//        oraz informację o tym ile maksymalnie driver powinien oczekiwać na dane zdarzenie
        WebElement finishElement = driver.findElement(By.cssSelector("#finish"));
//        Deklarujemy obiekt, na którego pojawienie się (stanie się widocznym, ponieważ element jest w źródle strony
//        od początku, a jedynie jest niewidoczny) będziemy oczekiwać
        wait.until(ExpectedConditions.visibilityOf(finishElement));
//        informujemy obiekt typu WebDriverWait na jakie wydarzenie ma czekać. Korzystamy z klasy ExpectedConditions

        String finishText = finishElement.getText();
//        Pobieramy tekst wskazanego elementu

        Assert.assertEquals(finishText, "Hello World!");
//        upewniamy się, że tekst jest poprawny. Podobnie jak wyżej można to skrócić do xpatha. Minusem xpatha jest
//        to, że nie będziemy wiedzieli czy dany obiekt nie został zlokalizowany na stronie czy ma inny tekst
    }
}
