package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class FillOrderPage {
    private WebDriver driver;
    //локатор поля Имя
    private By nameFieldOrder =
            By.xpath(".//input[@placeholder='* Имя']");
    //локатор поля Фамилия
    private By lastnameFieldOrder =
            By.xpath(".//input[@placeholder='* Фамилия']");
    //локатор поля Адрес
    private By addressFieldOrder =
            By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор поля Станция метро
    private By statiunFieldOrder =
            By.className("select-search__input");
    //локатор для выбора станции метро
    private By selectStatiunFieldOrder =
            By.className("select-search__select");
    //локатор поля Телефон
    private By phoneFieldOrder =
            By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор кнопки Далее
    private By buttonSubmitOrder =
            By.xpath(".//button[(@class ='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее')]");

    public FillOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // ввод имени
    public void setNameField(String name) {
        driver.findElement(nameFieldOrder).sendKeys(name);
    }

    //ввод фамилии
    public void setLastNameField(String lastname) {
        driver.findElement(lastnameFieldOrder).sendKeys(lastname);
    }

    //ввод адреса
    public void setAddressField(String address) {
        driver.findElement(addressFieldOrder).sendKeys(address);
    }

    // тап по полю Станция метро и выбор станции
    public void submitSubwayStation(String station) {
        driver.findElement(statiunFieldOrder).sendKeys(station);
        driver.findElement(statiunFieldOrder).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    // ввод Телефона
    public void setPhoneField(String phone) {
        driver.findElement(phoneFieldOrder).sendKeys(phone);
    }

    // тап по кнопке Далее
    public void clickButtonSubmit() {
        driver.findElement(buttonSubmitOrder).click();
    }

    //ввод данных в форму и тап по Далее
    public FillOrderPage fillOrderForm(
            String name,
            String lastname,
            String address,
            String station,
            String phone) {
        setNameField(name);
        setLastNameField(lastname);
        setAddressField(address);
        submitSubwayStation(station);
        setPhoneField(phone);
        clickButtonSubmit();
        return this;
    }
}
