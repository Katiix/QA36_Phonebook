package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
            logger.info("I need logout");
        }
    }
    @Test
    public void loginSuccess(){
        logger.info("Login with valid data: email - kate@gmail.com, password - Kkate12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("kate@gmail.com","Kkate12345$");
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Test passed with success");

    }

    public void loginSuccessModel(){
        User user = new User().withEmail("kate@gmail.com").withPassword("Kkate12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        logger.info("Test passed with success");

    }
    @Test
    public void loginWrongEmail(){
        User user = new User().withEmail("kateagmail.com").withPassword("Kkate12345$");
        logger.info("Test starts with email: " + user.getEmail());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Test passed with success");
    }
    @Test
    public void loginWrongPassword(){
        User user = new User().withEmail("kate@gmail.com").withPassword("ka123");
        logger.info("Test starts with password: " + user.getPassword());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("kate@gmail.com","ka123");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Test passed with success");
    }
    @Test
    public void loginUnregisteredUser(){
        User user = new User().withEmail("sfkdlfgdl@.com").withPassword("Kkate12345$");
        logger.info("Test starts with email: " + user.getEmail());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Test passed with success");
    }
}
