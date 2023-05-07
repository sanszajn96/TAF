package pl.tomaszbuga.ui.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Log4j2
public abstract class BaseTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setupWebDriver() {
        setupDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }

    private void setupDriver() {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();



        options.addArguments(

                "--remote-allow-origins=*"
        );

        driver = new ChromeDriver(options);

        driver
                .manage()
                .window()
                .maximize();
    }

    protected WebDriver getDriver() {
        if (driver != null)
            return driver;

        setupWebDriver();
        return getDriver();
    }
}
