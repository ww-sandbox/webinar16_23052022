package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TheInternetTests extends BaseTest{


    @Test(priority = 0, enabled = false)
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

    @Test(priority = 1)
    public void iframeTest(){
        driver.get("http://the-internet.herokuapp.com/iframe");
        WebElement frameElement = driver.findElement(By.id("mce_0_ifr"));
//        Wyszukiwanie elementu zawierającego ramkę
        driver.switchTo().frame("mce_0_ifr");
//        Przełączanie do ramki na podstawie id
//        driver.switchTo().frame(frameElement);
//        Przełączanie do ramki na podstawie WebElementu
//        Bez przełączenie nie będziemy w stanie wyszukiwać elementów wewnątrz ramki
        WebElement editorText = driver.findElement(By.xpath("//p[text()=\"Your content goes here.\"]"));
//        Wyszukiwanie elementu znajdującego się już wewnątrz ramki

        Assert.assertTrue(editorText.isDisplayed());
    }

    @Test(priority = 2)
    public void checkEditorMenu(){
//        driver.get("http://the-internet.herokuapp.com/iframe");
        driver.switchTo().parentFrame();
//        Powrót do ramki głównej
        WebElement menuElement = driver.findElement(By.cssSelector(".tox-menubar"));
//        wyszukiwanie w ramach strony 'bazowej'

        Assert.assertTrue(menuElement.isDisplayed());
    }

    @Test(priority = 3)
    public void newTabTest(){
        driver.get("http://the-internet.herokuapp.com/windows");
        Set<String> wh = driver.getWindowHandles();
//        Podczas webinaru zapomniałem zadeklarować typu danych w secie, dlatego pojawiały mi się problemy z typem
//        danych Object
        String curWindow = driver.getWindowHandle();

        System.out.println(wh);
        Assert.assertEquals(wh.size(), 1);
        driver.findElement(By.cssSelector(".example a")).click();
//        wh = driver.getWindowHandles();

        System.out.println(wh);
        Assert.assertEquals(wh.size(), 2);

//        Ponieważ Set nie daje nam 100% gwarancji posortowanych stron, musimy to obsłużyć
//        Poniższe rozwiązanie jest trochę rozbudowane, ale pozwala nam działać przy większej ilości stron
//        String[] whArray = wh.toArray();
//        System.out.println(whArray);
//        for(int i=0; i<whArray.length; i++){
//            if(whArray[i] != curWindow){
//                driver.switchTo().window((String)whArray[i]);
//            }
//        }
//        Lub mając już ten brakujący <String> przy deklaracji Set'a skorzystać z foreach
//        for (String whadnle: wh
//             ) {
//            if(whadnle != curWindow){
//                driver.switchTo().window(whadnle);
//            }
//        }
//        W naszym wypadku, przy 2 stronach, możemy uprościć zapis do poniższego - usuwamy aktualnie otwartą stronę
//        i przełączamy się na pozostałą w Secie
        wh.remove(curWindow);
        driver.switchTo().window(wh.iterator().next());

        WebElement header = driver.findElement(By.tagName("h3"));

        Assert.assertEquals(header.getText(), "New Window");
        driver.close();
        driver.switchTo().window(curWindow);
//        wh = driver.getWindowHandles();
//        System.out.println(wh);
//        Assert.assertEquals(wh.size(), 1);
    }

    @Test(priority = 4)
    public void mainWindowTest(){
        String header = driver.findElement(By.tagName("h3")).getText();

        Assert.assertEquals(header, "Opening a new window");
    }
}
