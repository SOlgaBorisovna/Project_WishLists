package main;

import dto.BrowserNameData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pages.RegistrationPage;


public class TestSuite extends AbsBaseSuite{

    @ParameterizedTest
    @EnumSource(value = BrowserNameData.class, names = {"CHROME", "EDGE"})
    public void checkRegistrationBtn(BrowserNameData name) {
        logger.info("Run test checkRegistrationBtn");
        init(name);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.open("/register");
        registrationPage.fillValues(user);
        registrationPage.checkSubmit(user);
        logger.info("Finish test checkRegistrationBtn");
    }
}

