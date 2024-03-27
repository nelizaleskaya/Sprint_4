package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubmitOrderPopup {
    private WebDriver driver;
    //локатор кнопка Да в попапе
    private By okButton =
            By.xpath(".//div[contains(@class,'Order_Modal_')]/div[contains(@class,'Order_Buttons_')]/button[2]");

    public SubmitOrderPopup(WebDriver driver) {
        this.driver = driver;
    }

    //метод нажатия на кнопку Да
    public SubmitOrderPopup clickOkButton() {
        driver.findElement(okButton).click();
        return this;
    }
}
