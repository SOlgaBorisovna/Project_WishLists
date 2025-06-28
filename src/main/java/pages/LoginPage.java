package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import properties.FilePropertyReader;

import java.util.Map;

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
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        loginBtn.click();

        String result = "error";
        try {
            WebElement allert = driver.findElement(By.cssSelector("div[class='fade alert alert-danger show']"));
        }
        catch (Exception e) {
            result = "valid";
        }

        assertThat(result)
                .as("При успешной авторизации не должно быть сообщения об ошибке")
                .isEqualTo("valid");
    }
}