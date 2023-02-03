package manager;

import model.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.time.Duration;
public class HelperUser extends HelperBase {
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginRegistrationForm() {
        WebElement el = new WebDriverWait(wd, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='/login']")));
        el.click();
    }

    public void fillLoginRegistrationForm(String email, String password) {
        type(By.cssSelector("input[name='email']"), email);
        type(By.cssSelector("input[name='password']"), password);
    }

    public void fillLoginRegistrationForm(User user) {
        type(By.cssSelector("input[name='email']"), user.getEmail());
        type(By.cssSelector("input[name='password']"), user.getPassword());
    }
    public void submitLogin() {
        WebElement el = new WebDriverWait(wd, Duration.ofSeconds(9))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name='login']")));
        el.click();
    }


    public boolean isLogged() {
        //return wd.findElement(By.xpath("//button[text()='Sign Out']")).isDisplayed();
        List<WebElement> list = wd.findElements(By.xpath("//button[text()='Sign Out']"));
        return list.size() > 0;
    }

    public void logout() {
        WebElement el = new WebDriverWait(wd, Duration.ofSeconds(9))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Sign Out']")));
        el.click();
    }


    public boolean isErrorMessageDisplayed(String message) {
        Alert alert = new WebDriverWait(wd, Duration.ofSeconds(9))
                .until(ExpectedConditions.alertIsPresent());
        String text = alert.getText();
        System.out.println(text);
        alert.accept();
        return text.contains(message);
    }


    public void submitRegistration() {
        WebElement el = new WebDriverWait(wd, Duration.ofSeconds(9))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("[name='registration']")));
       el.click();
    }

    public void login(User user) {
        openLoginRegistrationForm();
        fillLoginRegistrationForm(user);
        submitLogin();

    }
}
