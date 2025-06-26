package pages;

import org.openqa.selenium.WebDriver;

public abstract class AbsBasePage {

    protected WebDriver driver = null;
    private String baseUrl = "https://wishlist.otus.kartushin.su";//System.getProperty("base.url");

    public AbsBasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open(String path) {
        driver.get(baseUrl + path);
    }
}