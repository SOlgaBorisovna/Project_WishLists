package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WishListPage extends AbsBasePage{
    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public void login()
    {
        String loginStr = System.getProperty("user");
        WebElement name = driver.findElement(By.cssSelector("input[type = text]"));
        name.sendKeys(loginStr);
        WebElement password = driver.findElement(By.cssSelector("input[type = password]"));
        String passwordStr = System.getProperty("password");
        password.sendKeys(passwordStr);
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        loginBtn.click();
    }

    public void addWishList(User user)
    {
        WebElement newListBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[text()='Создать новый список']")));
        newListBtn.click();
        WebElement name = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[class=form-control]")));
        name.sendKeys(user.getWish());
        WebElement text = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("textarea[class=form-control]")));
        text.sendKeys(user.getText());
        WebElement createBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[class='btn btn-primary'][type=submit]")));
        createBtn.click();

        WebElement findName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()='" + user.getWish() + "']")));
        WebElement findText = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text()='" + user.getText() + "']")));

        assertThat(findName)
                .as("При добавлении списка желаний он должен отобразиться в виде карточки")
                .isNotEqualTo(null);

        assertThat(findText)
                .as("При добавлении списка желаний он должен отобразиться в виде карточки")
                .isNotEqualTo(null);

    }

    public void viewWishList()
    {
        List<WebElement> elements = driver.findElements(By.cssSelector("div[class='card-body']"));
        assertThat(elements.size())
                .as("Должны быть карточки желаний т.к. мы добавили одну")
                .isGreaterThan(0);

        WebElement el = elements.get(0);
        WebElement viewBtn = el.findElement(By.cssSelector("button[class='btn btn-primary']"));
        viewBtn.click();

        WebElement giftBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[class='mb-4 btn btn-primary']")));

        assertThat(giftBtn)
                .as("При просмотре карточки желаний должна быть кнопка добавления подарка")
                .isNotEqualTo(null);
    }

    public void deleteWishList()
    {
        WebElement listBtn = driver.findElement(By.cssSelector("a[href='/wishlists']"));
        listBtn.click();

        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='card-body']")));
        for(WebElement el : elements) {
            WebElement deleteBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[class='btn btn-danger']")));
            deleteBtn.click();
            driver.navigate().refresh();
        }

        elements = driver.findElements(By.cssSelector("div[class='card-body']"));

        assertThat(elements.size())
                  .as("После удаления всех карточек желаний список должен быть пустой")
                  .isEqualTo(0);
    }
}
