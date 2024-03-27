package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class StatusPage {
    private final WebDriver driver;
    // локатор ошибки при неверном вводе заказа
    protected final By notFoundImage =
            By.cssSelector("[alt='Not found']");

    public StatusPage(WebDriver driver) {
        this.driver = driver;
    }

    // проверка показа ошибки при вводе неверного номера заказа
    public StatusPage checkNotFound() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(notFoundImage));
        assert driver.findElement(notFoundImage).isDisplayed();
        return this;
    }
}
