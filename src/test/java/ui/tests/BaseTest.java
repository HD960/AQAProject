package ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import utils.ConfigLoader;

public abstract class BaseTest {

    @BeforeAll
    public static void initAll(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browser = ConfigLoader.get().getUi().getConfiguration().getBrowser();
        Configuration.browserSize = ConfigLoader.get().getUi().getConfiguration().getBrowserSize();
        Configuration.headless = ConfigLoader.get().getUi().getConfiguration().isHeadless();
        Configuration.baseUrl = ConfigLoader.get().getUi().getConfiguration().getBaseUrl();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

}
