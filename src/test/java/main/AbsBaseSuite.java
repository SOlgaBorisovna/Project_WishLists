package main;

import dto.BrowserNameData;
import dto.User;
import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;

public abstract class AbsBaseSuite {
    protected static final Logger logger = LogManager.getLogger("Suite Logger");
    protected WebDriver driver = null;
    protected User user = null;

    protected void init(BrowserNameData name){
        user = new User();
        driver = WebDriverFactory.create(name);
    }

    @AfterEach
    public void close() {
        if(driver != null)
            driver.quit();
    }
}