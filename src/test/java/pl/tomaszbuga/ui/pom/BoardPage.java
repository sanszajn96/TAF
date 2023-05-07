package pl.tomaszbuga.ui.pom;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import pl.tomaszbuga.ui.framework.PageObject;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class BoardPage extends PageObject {
    @FindBy(tagName = "h1")
    private WebElement boardTitle;

    @FindBy(css = ".list-card-details")
    private List<WebElement> cardDetails;

    @FindBy(css = ".list-card-title")
    private List<WebElement> cardTitle;

    @FindBy(tagName = "data-color")
    private List<WebElement> labelColor;

    @FindBy(css = ".icon-description")
    private List<WebElement> descriptionIcon;

    @FindBy( css = ".icon-attachment")
    private List<WebElement> attachmentIcon;

    @FindBy(css = ".list-card")
    private WebElement cardPage;

    @FindBy(css = "[aria-label='Pokaż menu']")
    private WebElement ellipsisButton;

    @FindBy(css = ".js-open-more")
    private WebElement showMoreActionsButton;

    @FindBy(css = ".js-close-board")
    private WebElement closeBoardButton;

    @FindBy(css = ".js-confirm")
    private WebElement confirmCloseButton;

    @FindBy(css = "[data-testid='close-board-delete-board-button']")
    private WebElement permanentlyRemoveBoardButton;

    @FindBy(css = "[data-testid='close-board-delete-board-confirm-button']")
    private WebElement confirmPermanentlyRemoveButton;




    public BoardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Get board title")
    public String getBoardTitle() {
        waitUntilElementIsVisible(boardTitle);
        String boardTitleText = getText(boardTitle);
        log.info("Get board title: " + boardTitleText);
        return boardTitleText;
    }

    @Step("Verify number of lists")
    public BoardPage getNumberOfLists() {
        List<WebElement> lists = driver.findElements(By.cssSelector(".list"));
        log.info("The number of lists is " + lists.size());
        return this;
    }

    @Step("Verify titles of lists")
    public BoardPage getTitlesOfLists() {
        List<String> listTitle = new ArrayList<>();
        List<WebElement> allListTitles = driver.findElements(By.cssSelector(".list-header-name"));
        for (WebElement l : allListTitles) {
            listTitle.add(l.getText());
        }
        log.info("List titles: " + listTitle);
        return this;

    }

    //kolor oznacznika - brak w pierwszej kolumnie - w 5 pozostałych istnieje "aria-label" "data-color"
    //udowodnić, że w pierwszej kolumnie brak tagu data-color - none
    //

    @Step("Check 'how to use' list data")
    public BoardPage checkHowToUseData() {
        log.info("Title: " + cardTitle.get(0).getText());
        log.info("Label color: " + checkColorLabel());
        log.info("Description icon: " + descriptionIcon.get(0).isDisplayed());
        log.info("Attachment icon: " + attachmentIcon.get(0).isDisplayed());
        return this;
    }

    @Step("Check 'Blocker' card data")
    public BoardPage checkBlockerData() {
        log.info("Title: " + cardTitle.get(1).getText());
        log.info("Label color: " + checkColorLabel());
        log.info("Description icon: " + descriptionIcon.get(1).isDisplayed());
        log.info("Attachment icon: " + attachmentIcon.get(1).isDisplayed());
        return this;
    }

    @Step("Check 'Discuss' card data")
    public BoardPage checkDiscussData() {
        log.info("Title: " + cardTitle.get(2).getText());
        log.info("Label color: " + checkColorLabel());
        log.info("Description icon: " + descriptionIcon.get(2).isDisplayed());
        log.info("Attachment icon: " + attachmentIcon.get(2).isDisplayed());
        return this;
    }

    @Step("Check 'FYI' card data")
    public BoardPage checkFyiData() {
        log.info("Title: " + cardTitle.get(3).getText());
        log.info("Label color: " + checkColorLabel());
       /* log.info("Description icon: " + descriptionIcon.get(3).isDisplayed());
        log.info("Attachment icon: " + attachmentIcon.get(3).isDisplayed());

        */
        return this;
    }

    @Step("Check 'Paused' card data")
    public BoardPage checkPausedData() {
        log.info("Title: " + cardTitle.get(4).getText());
        log.info("Label color: " + checkColorLabel());
     /*   log.info("Description icon: " + descriptionIcon.get(4).isDisplayed());
        log.info("Attachment icon: " + attachmentIcon.get(4).isDisplayed());

      */
        return this;
    }

    @Step("Check 'Goal' card data")
    public BoardPage checkGoalData() {
        log.info("Title: " + cardTitle.get(5).getText());
        log.info("Label color: " + checkColorLabel());
       /* log.info("Description icon: " + descriptionIcon.get(5).isDisplayed());
        log.info("Attachment icon: " + attachmentIcon.get(5).isDisplayed());

        */
        return this;
    }

   /* @Step("Open 'how to use this board' card page")
    public CardPage openCardPage() {
        log.info("Click on 'how to use this board' card page");
        clickOnWebElement(cardPage);
        return new CardPage(driver);
    }

    */


    @Step("Open board menu")
    public BoardPage openBoardMenu() {
        log.info("Open board menu");
        clickOnWebElement(ellipsisButton);
        return this;
    }

    @Step("Click show more actions button")
    public BoardPage clickShowMoreActionsButton() {
        log.info("Click show more actions button");
        clickOnWebElement(showMoreActionsButton);
        return this;
    }

    @Step("Click close board button")
    public BoardPage clickCloseBoardButton() {
        log.info("Click close board button");
        clickOnWebElement(closeBoardButton);
        return this;
    }

    @Step("Click confirm close button")
    public BoardPage clickConfirmCloseButton() {
        log.info("Click confirm close button");
        clickOnWebElement(confirmCloseButton);
        return this;
    }

    @Step("Click permanently remove board button")
    public BoardPage clickPermanentlyRemoveBoardButton() {
        log.info("Click permanently remove board button");
        clickOnWebElement(permanentlyRemoveBoardButton);
        return this;
    }

    @Step("Click confirm permanently remove button")
    public DashboardPage clickConfirmPermanentlyRemoveButton() {
        log.info("Click confirm permanently remove button");
        clickOnWebElement(confirmPermanentlyRemoveButton);
        return new DashboardPage(driver);
    }

    @Step("Remove board")
    public DashboardPage removeBoard() {
        return openBoardMenu()
                .clickShowMoreActionsButton()
                .clickCloseBoardButton()
                .clickConfirmCloseButton()
                .clickPermanentlyRemoveBoardButton()
                .clickConfirmPermanentlyRemoveButton();
    }
}
