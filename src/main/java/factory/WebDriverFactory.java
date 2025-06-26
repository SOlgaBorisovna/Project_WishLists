package factory;

import dto.BrowserNameData;
import exceptions.BrowserNotSupportedException;
import factory.settings.ChromeSettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class WebDriverFactory {

    public static WebDriver create(BrowserNameData browserNameData) {
        AbstractDriverOptions options = null;
        switch (browserNameData) {
            case CHROME: {
                options = new ChromeOptions();
                break;
            }
            case FIREFOX: {
                options = new FirefoxOptions();
                break;
            }
            case EDGE: {
                options = new EdgeOptions();
                break;
            }

            default:
                throw new BrowserNotSupportedException(browserNameData.name());
        }

        return  WebDriverFactory.create(browserNameData, options);
    }


    public static WebDriver create(BrowserNameData browserNameData, AbstractDriverOptions options) {
        switch (browserNameData) {
            case CHROME: {
                return new ChromeDriver((ChromeOptions)options);
            }
            case FIREFOX: {
                return new FirefoxDriver((FirefoxOptions)options);
            }
            case EDGE: {
                return new EdgeDriver((EdgeOptions)options);
            }
        }
        throw new BrowserNotSupportedException(browserNameData.name());
    }
}