package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    // кнопка Заказать в шапке старницы
    private final By orderButtonHeader =
            By.xpath(".//div[contains(@class,'Header_Nav_')]/button[contains(@class,'Button_Button_')]");
    // локатор для "Заказать" снизу
    private final By orderButtonBottom =
            By.xpath(".//div[contains(@class,'Home_FinishButton')]/button[contains(@class,'Button_Button_')]");
    // кнопка "Статус заказа"
    private final By orderStatusField =
            By.xpath(".//div[contains(@class,'Header_Nav')]/button[contains(@class,'Header_Link_')]");
    // поле для ввода номер заказа
    private final By orderField =
            By.cssSelector(".Input_Input__1iN_Z");
    // локатор для кнопки "GO" для поиска заказа
    public final By buttonGo =
            By.cssSelector("[class*=Header_Button_]");
    //локатор кнопки для согласия по кукам
    private final By cookiesYesButton = By.id("rcc-confirm-button");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage open() {
        driver.get(EnvConfig.BASE_URL);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(cookiesYesButton));
        driver.findElement(cookiesYesButton).click();
        return this;
    }

    //клик по кнопке Заказать в шапке на главной
    public void higherOrderButtonClick() {
        driver.findElement(orderButtonHeader).click();
    }
    //кликнуть по кнопке Заказать снизу c подскроллом
    public MainPage lowerOrderButtonClick() {
        WebElement element = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(orderButtonBottom).click();
        return this;
    }
    public MainPage clickButtonTopOrBottom (String buttonSelect) {
        if (buttonSelect.equals("top")) {
            higherOrderButtonClick();
            return this;
        } else if (buttonSelect.equals("bottom")){
            lowerOrderButtonClick();
            return this;
        }
        return this;
    }

    // тап на Статус заказа
    public MainPage clickOnOrderStatus() {
        driver.findElement(orderStatusField).click();
        return this;
    }

    // отправка номера заказа в поле статус заказа
    public MainPage enterOrderNumber(String orderNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderField));
        driver.findElement(orderField).sendKeys(orderNumber);
        return this;
    }

    // клик по кнопке Go
    public StatusPage clickOnGo() {
        driver.findElement(buttonGo).click();
        return new StatusPage(driver);
    }

    // тапнуть по стрелке в строке вопросов о важном с ожиданием загрузки и подскроллом
    public void clickButtonsImpotantQuestions(int listIndex) {
        By locator = By.id("accordion__heading-" + listIndex);
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    // нахождение локатора пункта Вопросы о важном. В зависимости от передаваемого индекса
    public By locator(int listIndex) {
        return By.xpath(".//div[@id='accordion__panel-"+listIndex+"']/p");
    }
    // получение текста из поля ответа в раздела "Вопрсоы о важном"
    public String getAnswersImpotantQuestions(int listIndex) {
        return driver.findElement(locator(listIndex)).getText();
    }

    public boolean contentIsDisplayed(int listIndex) {
        String expectedAnswersText = (EnvConfig.expectedResult(listIndex));
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator(listIndex)));
        return driver.findElement(locator(listIndex)).isDisplayed()
                && getAnswersImpotantQuestions(listIndex).equals(expectedAnswersText);
    }
}
