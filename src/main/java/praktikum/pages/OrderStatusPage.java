package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderStatusPage {
    private WebDriver driver;
    // локатор для Заказ оформлен
    private By  popupOnSuccessOrder =
            By.xpath(".//div[contains(@class,'Order_NextButton_')]/button[contains(@class,'Button_Middle_')]");

    public OrderStatusPage (WebDriver driver) {
        this.driver = driver;
    }

    // если есть поле Заказ оформлен, то возвращаем тру
    public boolean statusOrderOnPage() {
        return !driver.findElements(popupOnSuccessOrder).isEmpty();

    }
}
