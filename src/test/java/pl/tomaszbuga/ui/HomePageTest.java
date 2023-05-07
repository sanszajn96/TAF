package pl.tomaszbuga.ui;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.ui.framework.BaseTest;
import pl.tomaszbuga.ui.pom.HomePage;

import static pl.tomaszbuga.ui.TestGroups.REGRESSION;
import static pl.tomaszbuga.ui.TestGroups.SMOKE;

public class HomePageTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    private void setup() {
        homePage = new HomePage(getDriver());
        homePage.openHomePage();
    }

    @Test(groups = {SMOKE, REGRESSION})
    @Description("Verify that user can login with username and password")
    @TmsLink("QA-1")
    public void loginWithCredentialsTest() {
        homePage
                .clickLoginButton()
                .enterUsername()
                .clickContinueButton()
                .enterPassword()
                .clickLoginButton();
    }

    @Test(groups = {REGRESSION})
    @Description("Verify that user can login via Google")
    @TmsLink("QA-2")
    public void loginWithGoogleTest() {
        homePage
                .clickLoginButton()
                .clickLoginViaGoogleButton();
    }
}
