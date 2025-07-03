package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegistrationPage extends AbsBasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void fillValues(User user)
    {
        WebElement name = driver.findElement(By.cssSelector("input[type = text]"));
        name.sendKeys(user.getName() );
        WebElement email = driver.findElement(By.cssSelector("input[type = email]"));
        email.sendKeys(user.getEmail() );
        WebElement password = driver.findElement(By.cssSelector("input[type = password]"));
        password.sendKeys(user.getPassword() );
    }

    public void checkSubmit() {
        WebElement registrationBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        registrationBtn.click();

        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[text()='Вход в систему']")));

        assertThat(element)
                .as("При успешной регистрации должны перейти на страницу 'Вход в систему'")
                .isNotEqualTo("null");
    }
}