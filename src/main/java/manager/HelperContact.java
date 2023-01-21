package manager;

import model.Contact;
import org.openqa.selenium.*;

import java.util.List;


public class HelperContact extends HelperBase{
    public HelperContact(WebDriver wd){
        super(wd);
    }

    public void openContactForm(){
        click(By.xpath("//a[@href='/add']"));

    }
    public void fillContactForm(Contact contact){
        type(By.cssSelector("input[placeholder='Name']"),contact.getName());
        type(By.cssSelector("input[placeholder='Last Name']"), contact.getLastName());
        type(By.cssSelector("input[placeholder='Phone']"),contact.getPhone());
        type(By.cssSelector("input[placeholder='email']"), contact.getEmail());
        type(By.cssSelector("input[placeholder='Address']"),contact.getAddress());
        type(By.cssSelector("input[placeholder='description']"),contact.getDescription());
    }

    public void submit(){
        click(By.xpath("//b[text()='Save']"));
    }

    public String getText() {
        WebElement text = wd.findElement(By.cssSelector("div [class^='contact-item_card']:first-child"));
        return text.getText();
    }

    public boolean isContactAddedByName(String name) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for(WebElement el: list){
            if(el.getText().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByPhone(String phone) {
        List<WebElement> list = wd.findElements(By.cssSelector("h3"));
        for(WebElement el: list){
            if(el.getText().equals(phone)){
                return true;
            }
        }
        return false;
    }

    public boolean isContactAddedByEmail(Contact contact) {
        List<WebElement> list = wd.findElements(By.cssSelector("h2"));
        for(WebElement el: list){
            if(el.getText().equals(contact.getName())){
                el.click();
                String text = wd.findElement(By.xpath("//div[starts-with(@class,'contact-item-detailed_card')]")).getText();
                if(text.contains(contact.getEmail())){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isErrorMessageDisplayed(String message) {
        Alert alert = wd.switchTo().alert();
        String text = alert.getText();
        System.out.println(text);
        alert.accept();
        return text.contains(message);
    }

    public boolean isAddTabActive() {
        return wd.findElements(By.cssSelector("a[href='/add'][class='active']")).size()>0;
    }

    public boolean isAlertPresent() {

        boolean presentFlag = false;

        try {

            // Check the presence of alert
            Alert alert = wd.switchTo().alert();
            // Alert present; set the flag
            presentFlag = true;
            // if present consume the alert


        } catch (NoAlertPresentException ex) {
            // Alert not present
            ex.printStackTrace();
        }

        return presentFlag;

    }
}
