package praktikum;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.MainPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ImpotantQuestionTest {
    // счетчик для хождения по строкам вопросов
    private final int listIndex;

    @Rule
    public DriverRule driverRule = new DriverRule();

    public ImpotantQuestionTest(int listIndex) {
        this.listIndex = listIndex;
    }

    // задаём индекс для массивов вопросов
    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
        };
    }

    @Test
    public void IsVisibleTextWhenClickedButton() {
        WebDriver driver = driverRule.getDriver();

        MainPage objMainPage = new MainPage(driver);
        objMainPage.open();
        objMainPage.clickButtonsImpotantQuestions(listIndex);

        assertTrue("Нет соответствия текста в пункте \"Вопросы о важном\"", objMainPage.contentIsDisplayed(listIndex));

    }
}
