package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//public class LoginPage extends AbsBasePage {
//
//    public LoginPage(WebDriver driver) {
//        super(driver);
//    }
//
//    public void fillValuesLogin(User user)
//    {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//
//        String name = user.getName();
//        js.executeScript("document.getElementById('username').value = '" + user.getName() + "';");
//        js.executeScript("document.getElementById('email').value = '" + user.getEmail() + "';");
//        js.executeScript("document.getElementById('password').value = '" + user.getPassword() + "';");
//    }
//
//    public void checkSubmit(User user) {
//
//        WebElement registrationBtn = driver.findElement(By.cssSelector("button[type=submit]"));
//        registrationBtn.click();
//        WebElement registrationOutput = driver.findElement(By.id("output"));
//
//    }
//}