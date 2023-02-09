package tests;

import manager.DataProviderUser;
import manager.ListenerTNG;
import manager.ListenerWD;
import model.User;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Listeners(ListenerTNG.class)
public class LoginTests extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (app.getHelperUser().isLogged()) {
            app.getHelperUser().logout();
            logger.info("Need logout");

        }
    }

    @DataProvider
    public Iterator<Object[]> loginData() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"kate@gmail.com", "Kkate12345$"});
        list.add(new Object[]{"kate@gmail.com", "Kkate12345$"});
        list.add(new Object[]{"kate@gmail.com", "Kkate12345$"});
        return list.iterator();
    }

    @Test(dataProvider = "loginData")
    public void loginSuccess(String email, String password) {
        logger.info("Login with valid data: email - " + email + ", password - " + password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Test passed with success");

    }

    @Test(dataProvider = "loginDataCls", dataProviderClass = DataProviderUser.class)
    public void loginSuccess2(String email, String password) {
        logger.info("Login with valid data: email - " + email + ", password - " + password);
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email, password);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Test passed with success");

    }

    @Test(dataProvider = "loginDataUser", dataProviderClass = DataProviderUser.class)
    public void loginSuccess2(User user) {
        logger.info("Login with valid data: " + user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Test passed with success");

    }

    @Test(dataProvider = "loginDataUserFromFile", dataProviderClass = DataProviderUser.class, groups = "smoke")
    public void loginSuccessModelFormFile(User user) {
        logger.info("Login with valid data: " + user.toString());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Test passed with success");

    }

    public void loginSuccessModel() {
        User user =  new User().withEmail("kate@gmail.com").withPassword("Kkate12345$");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        logger.info("Test passed with success");

    }

    @Test(groups = {"smoke"})
    public void loginWrongEmail() {
        User user =  new User().withEmail("kategmail.com").withPassword("Kkate12345$");
        logger.info("Test starts with email: " + user.getEmail());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Test passed with success");
    }

    @Test
    public void loginWrongPassword() {
        User user =  new User().withEmail("kate@gmail.com").withPassword("kat123");

        logger.info("Test starts with password: " + user.getPassword());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("kate@gmail.com", "ka123");
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Test passed with success");
    }

    @Test
    public void loginUnregisteredUser() {
        User user =  new User().withEmail("sddgffgh@gmail.com").withPassword("Kkate12345$");

        logger.info("Test starts with email: " + user.getEmail());
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(user);
        app.getHelperUser().submitLogin();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password"));
        logger.info("Test passed with success");
    }

}
