package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllertsTests extends BaseTest{
    @Test
    public void jsAlertTest(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        //button[contains(.,'JS Alert')]
        driver.findElement(By.xpath("//button[@onClick='jsAlert()']")).click();

        Alert alert = driver.switchTo().alert();
//        Stworzenie obiektu typu Alert do obsługi alertów - posiada odpowiednie metody
        String alertText = alert.getText();
//        Pobranie tekstu do weryfikacji
        alert.accept();
//        Zaakceptowanie - w praktycie kliknięcie w przycis OK
        WebElement resultText = driver.findElement(By.id("result"));

        Assert.assertEquals(alertText, "I am a JS Alert");
        Assert.assertEquals(resultText.getText(), "You successfully clicked an alert" );
    }

    @Test
    public void jsConfirmOkTest(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        //button[contains(.,'JS Confirm')]
        driver.findElement(By.xpath("//button[@onClick='jsConfirm()']")).click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        WebElement resultText = driver.findElement(By.id("result"));

        Assert.assertEquals(alertText, "I am a JS Confirm");
        Assert.assertEquals(resultText.getText(), "You clicked: Ok" );
    }

    @Test
    public void jsConfirmCancelTest(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        //button[contains(.,'JS Confirm')]
        driver.findElement(By.xpath("//button[@onClick='jsConfirm()']")).click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.dismiss();
//        Kliknięcie w przycisk Cancel
        WebElement resultText = driver.findElement(By.id("result"));

        Assert.assertEquals(alertText, "I am a JS Confirm");
        Assert.assertEquals(resultText.getText(), "You clicked: Cancel" );
    }

    @Test
    public void jsConfirmPromptTest(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        //button[contains(.,'JS Confirm')]
        driver.findElement(By.xpath("//button[@onClick='jsPrompt()']")).click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.sendKeys("hello");
//        Wysłanie tekstu do pola tekstowego w alercie
        alert.accept();
        WebElement resultText = driver.findElement(By.id("result"));

        Assert.assertEquals(alertText, "I am a JS prompt");
        Assert.assertEquals(resultText.getText(), "You entered: hello" );
    }
}
