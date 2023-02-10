package tests;

import model.Contact;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class AddNewContactTests extends TestBase {
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(!app.getHelperUser().isLogged()){
            app.getHelperUser().login(new User().withEmail("kate@gmail.com").withPassword("Kkate12345$"));
        }
    }
    @Test(groups={"smoke"})
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
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact));
        logger.info("Test passed with success");

    }

    @Test
    public void addNewContactSuccessRequiredFields(){
        Random random = new Random();
        int i = random.nextInt(1000)+1000;
        Contact contact = Contact.builder()
                .name("Alina"+i)
                .lastName("White")
                .phone("086534"+i)
                .email("alinaw"+i+"@gmail.com")
                .address("Kyiv, Ukraine")
                .build();
        logger.info("Test started with data: " + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByEmail(contact));
        logger.info("Test passed with success");
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
        logger.info("Test started with email: " + contact.getEmail());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
        logger.info("Test passed with success");
    }
    @Test(description = "bug",enabled = false)
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
        logger.info("Test started with email: " + contact.getEmail());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
        logger.info("Test passed with success");
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
        logger.info("Test started with email: " + contact.getEmail());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
        logger.info("Test passed with success");

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
        logger.info("Test started with email: " + contact.getEmail());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
        logger.info("Test passed with success");

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
        logger.info("Test started with email: " + contact.getEmail());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
        logger.info("Test passed with success");

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
        logger.info("Test started with email: " + contact.getEmail());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
        logger.info("Test passed with success");

    }
    @Test(description = "bug",enabled = false)
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
        logger.info("Test started with email: " + contact.getEmail());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
        logger.info("Test passed with success");

    }
    @Test(description = "bug",enabled = false)
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
        logger.info("Test started with email: " + contact.getEmail());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Email not valid:"));
        logger.info("Test passed with success");

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
        logger.info("Test started with name: " + contact.getName());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");

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
        logger.info("Test started with name: " + contact.getName());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");

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
        logger.info("Test started with last name: " + contact.getLastName());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");

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
        logger.info("Test started with last name: " + contact.getLastName());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");

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
        logger.info("Test started with address: " + contact.getAddress());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");


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
        logger.info("Test started with address: " + contact.getAddress());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");


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
        logger.info("Test started with phone: " + contact.getPhone());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");


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
        logger.info("Test started with phone: " + contact.getPhone());
        app.getHelperContact().openContactForm();

        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");

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
        logger.info("Test started with phone: " + contact.getPhone());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");

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
        logger.info("Test started with phone: " + contact.getPhone());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");
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
        logger.info("Test started with phone: " + contact.getPhone());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");
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
        logger.info("Test started with phone: " + contact.getPhone());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");
    }
    @Test(description = "bug",enabled = false)
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
        logger.info("Test started with phone: " + contact.getPhone());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().submit();
        Assert.assertTrue(app.getHelperContact().isErrorMessageDisplayed("Phone not valid"));
        Assert.assertTrue(app.getHelperContact().isAddTabActive());
        logger.info("Test passed with success");

    }
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
