package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class CompleteFillOrderRage {
    private WebDriver driver;
    //локатор поля Когда привезти самокат
    private By setDataDelivery =
            By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //локатор поля Срок аренды
    private By fieldRentalPeriod =
            By.className("Dropdown-placeholder");
    //локатор цвета самоката - Чёрный жемчуг
    private By selectBlackColor =
            By.xpath(".//input[@id='black']");
    //локатор цвета самоката - Серая безысходность
    private By selectGreyColor =
            By.xpath(".//input[@id='grey']");
    // локатор поля Комментарий для курьера
    private By commentOrder =
            By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //локатор кнопки "Заказать"
    private By buttonOrderComplete =
            By.xpath(".//div[contains(@class,'Order_Buttons_')]/button[2]");

    public CompleteFillOrderRage(WebDriver driver) {
        this.driver = driver;
    }

    //выбор даты в Когда привезти самокат
    public void setDataOrder(String dateOrder) {
        driver.findElement(setDataDelivery).sendKeys(dateOrder);
        driver.findElement(setDataDelivery).sendKeys(Keys.ENTER);
    }

    //выбор количества дней аренды
    public void chooseDayPeriod(String daysOrder) {
        driver.findElement(fieldRentalPeriod).click();
        setElements(daysOrder);
    }
    public void setElements(String daysOrder) {
        driver.findElement(By.xpath(".//*[@class='Dropdown-option'][" + daysOrder + "]"))
                .click();
    }

    //клик по чек-боксу цвета - Чёрный жемчуг
    public void clickBlackColor() {
        driver.findElement(selectBlackColor).click();
    }

    //клик по чек-боксу цвета - Серая безысходность
    public void clickGreyColor() {
        driver.findElement(selectGreyColor).click();
    }

    //выбора цвета по параметру
    public void chooseColor(String color) {
        if (color.equals("black")) {
            clickBlackColor();
        } else if (color.equals("grey")) {
            clickGreyColor();
        }
    }

    //ввод комментария для курьера
    public CompleteFillOrderRage enterComment(String comment) {
        driver.findElement(commentOrder).sendKeys(comment);
        return this;
    }

    //тап по кнопке Заказать
    public CompleteFillOrderRage clickOrderButton() {
        driver.findElement(buttonOrderComplete).click();
        return this;
    }

    //общий метод для ввода данных для формы и тап по кнопке Заказать
    public CompleteFillOrderRage enterAllDataRentOrder(
            String dateOrder,
            String daysOrder,
            String color,
            String comment) {
        setDataOrder(dateOrder);
        chooseDayPeriod(daysOrder);
        chooseColor(color);
        enterComment(comment);
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT));
        clickOrderButton();
        return this;
    }


}
