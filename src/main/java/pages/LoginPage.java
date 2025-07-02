package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Мои списки желаний']")));

        assertThat(element)
                .as("При успешной авторизации должны перейти на страницу 'Мои списки желаний'")
                .isNotEqualTo(null);
    }

    public void checkExit() {
        WebElement exitBtn = driver.findElement(By.cssSelector("a[href=\"#\"]"));
        exitBtn.click();
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Вход в систему']")));

        assertThat(element)
                .as("При успешной выходе должны перейти на страницу 'Вход в систему'")
                .isNotEqualTo("null");
    }
}