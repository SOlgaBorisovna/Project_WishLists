package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegistrationPage extends AbsBasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void fillValues(User user)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement name = driver.findElement(By.cssSelector("input[type = text]"));
        name.sendKeys(user.getName() );
        WebElement email = driver.findElement(By.cssSelector("input[type = email]"));
        email.sendKeys(user.getEmail() );
        WebElement password = driver.findElement(By.cssSelector("input[type = password]"));
        password.sendKeys(user.getPassword() );
    }

    public void checkSubmit(User user) {
        WebElement registrationBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        registrationBtn.click();
        driver. manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        String result = "valid";
        try {
            WebElement text = driver.findElement(By.xpath("//h2[text()='Вход в систему']"));
        }
        catch (Exception e)
        {
            result = "error";
        }

        assertThat(result)
                .as("При успешной регистрации должны перейти на страницу входа в систему")
                .isEqualTo("valid");
    }
}