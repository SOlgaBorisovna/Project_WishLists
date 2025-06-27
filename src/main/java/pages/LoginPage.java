package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import properties.FilePropertyReader;

import java.sql.DriverManager;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LoginPage extends AbsBasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillValuesLoginPage()
    {
        Map<String, String> settings = new FilePropertyReader().getSettings();
        String loginStr = settings.get("login");
        WebElement name = driver.findElement(By.cssSelector("input[type = text]"));
        name.sendKeys(loginStr);
        WebElement password = driver.findElement(By.cssSelector("input[type = password]"));
        String passwordStr = settings.get("password");
        password.sendKeys(passwordStr);
    }

    public void checkSubmit() {
        String oldUrl = driver.getCurrentUrl();
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        loginBtn.click();
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String newUrl = driver.getCurrentUrl();

        assertThat(newUrl)
                .as("При успешной авторизации должен осуществиться переход на другую страницу")
                .isNotEqualTo(oldUrl);
    }
}