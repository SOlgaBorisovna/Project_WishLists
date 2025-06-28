package pages;

import dto.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import properties.FilePropertyReader;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WishListPage extends AbsBasePage{
    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public void login()
    {
        Map<String, String> settings = new FilePropertyReader().getSettings();
        String loginStr = settings.get("login");
        WebElement name = driver.findElement(By.cssSelector("input[type = text]"));
        name.sendKeys(loginStr);
        WebElement password = driver.findElement(By.cssSelector("input[type = password]"));
        String passwordStr = settings.get("password");
        password.sendKeys(passwordStr);
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type=submit]"));
        loginBtn.click();
    }

    public void addWishList(User user)
    {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement newListBtn = driver.findElement(By.xpath("//button[text()='Создать новый список']"));
        newListBtn.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebElement name = driver.findElement(By.cssSelector("input[class=form-control]"));
        name.sendKeys(user.getWish());
        WebElement text = driver.findElement(By.cssSelector("textarea[class=form-control]"));
        text.sendKeys(user.getText());
        WebElement createBtn = driver.findElement(By.cssSelector("button[class='btn btn-primary'][type=submit]"));
        createBtn.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        String result = "valid";
        try {
            WebElement findName = driver.findElement(By.xpath("//div[text()='" + user.getWish() + "']"));
            WebElement findText = driver.findElement(By.xpath("//p[text()='" + user.getText() + "']"));
        }
        catch (Exception e)
        {
            result = "error";
        }

        assertThat(result)
                .as("При добавлении списка желаний он должен отобразиться в виде карточки")
                .isEqualTo("valid");
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
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        String result = "valid";
        try {
            WebElement giftBtn = driver.findElement(By.cssSelector("button[class='mb-4 btn btn-primary']"));
        }
        catch (Exception e)
        {
            result = "error";
        }
        assertThat(result)
                .as("При просмотре карточки желаний должна быть кнопка добавления подарка")
                .isEqualTo("valid");
    }

    public void deleteWishList()
    {
        WebElement listBtn = driver.findElement(By.cssSelector("a[href='/wishlists']"));
        listBtn.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        List<WebElement> elements = driver.findElements(By.cssSelector("div[class='card-body']"));
        for(WebElement el : elements) {
            WebElement viewBtn = el.findElement(By.cssSelector("button[class='btn btn-danger']"));
            viewBtn.click();
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        }

        listBtn = driver.findElement(By.cssSelector("a[href='/wishlists']"));
        listBtn.click();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        elements = driver.findElements(By.cssSelector("div[class='card-body']"));

        assertThat(elements.size())
                  .as("После удаления всех карточек желаний список должен быть пустой")
                  .isEqualTo(0);
    }
}
