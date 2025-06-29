package main;

import dto.BrowserNameData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import pages.LoginPage;
import pages.RegistrationPage;
import pages.WishListPage;


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

    @ParameterizedTest
    @EnumSource(value = BrowserNameData.class, names = {"CHROME", "EDGE"})
    public void checkLoginBtn(BrowserNameData name) {
        logger.info("Run test checkLoginBtn");
        init(name);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open("/login");
        loginPage.fillValuesLoginPage();
        loginPage.checkSubmit();
        loginPage.checkExit();
        logger.info("Finish test checkLoginBtn");
    }

    @ParameterizedTest
    @EnumSource(value = BrowserNameData.class, names = {"CHROME", "EDGE"})
    public void checkWishList(BrowserNameData name) {
        logger.info("Run test checkWishList");
        init(name);
        WishListPage loginPage = new WishListPage(driver);
        loginPage.open("/login");
        loginPage.login();
        loginPage.addWishList(user);
        loginPage.viewWishList();
        loginPage.deleteWishList();
        logger.info("Finish test checkWishList");
    }
}

