package manager;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



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

    }
}
