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
        logger.info("Test started with data: " + contact.toString());
        app.getHelperContact().openContactForm();
        pause(2000);
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact));

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
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact));
    }
    @Test
    public void addNewContactWrongEmail(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email("alinaw"+i+"gmail.com")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        pause(2000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
    }
    @Test
    public void addNewContactNullEmail(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email("")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        pause(2000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
    }
    @Test
    public void addNewContactBlankEmail(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email(" ")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        pause(2000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
    }
    @Test
    public void addNewContactTwoAtEmail(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email("alinaw"+i+"@@gmail.com")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        pause(2000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
    }
    @Test
    public void addNewContactMinimumCharBeforeAtEmail(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email("@gmail.com")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        pause(2000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
    }
    @Test
    public void addNewContactMinimumCharAfterAtEmail(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email("alinaw"+i+"@")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        pause(2000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
    }
    @Test
    public void addNewContactNonEnglishEmail(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email("алина"+i+"@мэйл.ком")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        pause(2000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
    }
    @Test
    public void addNewContactExistingEmail(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email("alinaw2023@gmail.com")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        pause(2000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
    }
    @Test
    public void addNewContactNullName(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("")
                .lastName("White")
                .phone("086534"+i)
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }
    @Test
    public void addNewContactBlankName(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name(" ")
                .lastName("White")
                .phone("086534"+i)
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }
    @Test
    public void addNewContactNullLastName(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("")
                .phone("086534"+i)
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }
    @Test
    public void addNewContactBlankLastName(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName(" ")
                .phone("086534"+i)
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv, Ukraine")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }

    @Test
    public void addNewContactAddressNull(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email("alinaw"+i+"@gmail.com")
                .address("")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(2000);
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());

    }
    @Test
    public void addNewContactAddressBlank(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email("alinaw"+i+"@gmail.com")
                .address(" ")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(2000);
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());

    }
    @Test
    public void addNewContactPhoneNull(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("")
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(2000);
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        pause(5000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());


    }
    @Test
    public void addNewContactPhoneBlank(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone(" ")
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(2000);
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        pause(5000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }
    @Test
    public void addNewContactPhoneNotNumbers(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086oooooooooo")
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(2000);
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        pause(5000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }
    @Test
    public void addNewContactPhoneMinimumNumbers(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086555")
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(2000);
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        pause(5000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }
    @Test
    public void addNewContactPhoneMaximumNumbers(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("08655555555555555555555")
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(2000);
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        pause(5000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }
    @Test
    public void addNewContactPhoneSpecialChars(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086$4667876")
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(2000);
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        pause(5000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }
    @Test
    public void addNewContactPhoneAlreadyExisting(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("08653466365")
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv")
                .description("Childhood friend")
                .build();
        app.getHelperContact().openContactForm();
        pause(2000);
        app.getHelperContact().fillContactForm(contact);
        pause(2000);
        app.getHelperContact().submit();
        pause(5000);
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
    }
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
