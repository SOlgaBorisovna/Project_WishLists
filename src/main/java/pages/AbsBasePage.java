package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class AbsBasePage {

    protected WebDriver driver = null;
    protected WebDriverWait wait = null;
    protected String baseUrl = System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(String path) {
        driver.get(baseUrl + path);
    }
}