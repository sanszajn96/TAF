package pl.tomaszbuga.ui;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.tomaszbuga.ui.framework.BaseTest;
import pl.tomaszbuga.ui.pom.BoardPage;
import pl.tomaszbuga.ui.pom.HomePage;

import static org.testng.Assert.assertEquals;
import static pl.tomaszbuga.ui.TestGroups.REGRESSION;
import static pl.tomaszbuga.ui.TestGroups.SMOKE;

public class DashboardPageTest extends BaseTest {
    private HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    private void setup() {
        homePage = new HomePage(getDriver());
        homePage.openHomePage();
    }

    @Test(groups = {SMOKE, REGRESSION})
    public void addNewBoardTest() {
        String expectedTitle = "1-on-1";

        String boardTitleFromPage = homePage
                .clickLoginButton()
                .enterUsername()
                .clickContinueButton()
                .enterPassword()
                .clickLoginButton()
                .clickOnCreateNewBoardButton()
                .clickStartWithATemplateButton()
                .selectTemplateByIndex(0)
                .enterRequiredData()
                .getBoardTitle();

        assertEquals(boardTitleFromPage, expectedTitle);

        BoardPage boardPage = new BoardPage(getDriver());

        boardPage
                .getNumberOfLists()
                .getTitlesOfLists()
                .checkHowToUseData()
                .checkBlockerData()
                .checkDiscussData()
                .checkFyiData()
                .checkPausedData()
                .checkGoalData()
                /*.openCardPage()*/
                .removeBoard();

    }
}
