package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }
    public void type(By locator, String text){
        if(text!=null){
            WebElement element = new WebDriverWait(wd, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }
    public void click(By locator){
        WebElement element = new WebDriverWait(wd, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
        element.click();

    }


    public void test(String data){ //"30"
        String locator = "//div[text()='"+data+"']";
        click(By.xpath(locator));

    }
}
