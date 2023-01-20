package tests;

import model.Contact;
import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {
    @BeforeMethod
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("kate@gmail.com").withPassword("Kkate12345$"));
        }
    }
    @Test
    public void addNewContactSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        System.out.println(contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        // Assert.assertEquals(app.getHelperContact().getText(),contact.getName());
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        //HomeWork: negative scenarios for contact fields
        //if name or surname is empty save doesn't work - check that you stay on "add" tab
        Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact.getEmail()));//check

    }

    @Test
    public void addNewContactSuccessRequiredFields(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Gomer"+i)
                .lastName("Simpson")
                .phone("086534"+i)
                .email("alinaw"+i+"@gmail.com")
                .address("NY")

                .build();
        System.out.println(contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        // Assert.assertEquals(app.getHelperContact().getText(),contact.getName());
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));

    }
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
