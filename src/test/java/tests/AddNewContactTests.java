package tests;

import model.Contact;
import model.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase {
    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("kate@gmail.com").withPassword("Kkate12345$"));
        }
    }
    @Test
    public void addNewContactSuccess(){
        Contact contact = Contact.builder()
                .name("Alina")
                .lastName("White")
                .phone("086534663")
                .email("alinaw2023@gmail.com")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();

    }

}
