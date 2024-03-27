package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class OrderStatusTest {

    private final String buttonSelect;
    private final String name;
    private final String lastname;
    private final String address;
    private final String station;
    private final String phone;
    private final String dateOrder;
    private final String daysOrder;
    private final String color;
    private final String comment;

    @Rule
    public DriverRule driverRule = new DriverRule();

    //конструктор
    public OrderStatusTest(
            String buttonSelect,
            String name,
            String lastname,
            String address,
            String station,
            String phone,
            String dateOrder,
            String daysOrder,
            String color,
            String comment) {
        this.buttonSelect = buttonSelect;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.dateOrder = dateOrder;
        this.daysOrder = daysOrder;
        this.color = color;
        this.comment = comment;
    }

    // задание тестовых данных
    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {
                    "top",
                    "Лена",
                    "Иванова",
                    "проспект Заботы",
                    "Войковская",
                    "70022334455",
                    "10.07.2024",
                    "3",
                    "black",
                    "Подъём на 5 этаж"
                },
                {
                    "bottom",
                    "Света",
                    "Малькова",
                    "проспект Заботы",
                    "Динамо",
                    "80022335566",
                    "20.04.2024",
                    "1",
                    "grey",
                    "Из рук в руки"
                },
        };
    }

    @Test
    public void fullOrderStatusPageTest() {
        WebDriver driver = driverRule.getDriver();

        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        // тап на кнопку заказа в зависимости от тестовых данных
        objMainPage.clickButtonTopOrBottom(buttonSelect);

        //создание объекта страницы с данными заказчика
        new FillOrderPage(driver)
                //ввод данных заказчика в форму
                .fillOrderForm(name, lastname, address, station, phone);
        // создание объекта для страницы с дополнительными данными для заказа
        new CompleteFillOrderRage(driver)
                //ввод данных на странице про даты и сроки аренды
                .enterAllDataRentOrder(dateOrder, daysOrder, color, comment);
        //создние объекта попапа с подтверждением оформления заказа
        new SubmitOrderPopup(driver)
                //тап на Да для завершения заказа
                .clickOkButton();
        //объект страницы со статусом заказа
        OrderStatusPage objOrderIsProcessed = new OrderStatusPage(driver);
        //проверка статуса оформленного заказа
        assertTrue("Заказ не оформлен, не показали окно успешного заказа",
                objOrderIsProcessed.statusOrderOnPage());
    }
}
