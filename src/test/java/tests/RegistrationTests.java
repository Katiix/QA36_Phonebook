package tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTests extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getHelperUser().isLogged()){
            app.getHelperUser().logout();
        }
    }
    @Test(groups = {"smoke", "task"})
    public void registrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(100);
        String email = "cat" + i + "@gmail.com";
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm(email,"Kkate12345$");
        app.getHelperUser().submitRegistration ();
        Assert.assertTrue(app.getHelperUser().isLogged());
        //logout

    }

    @Test
    public void registrationWrongEmail(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillRegistrationForm(new User().withName("Kate").withLastName("Black").withEmail("catgmail.com").withPassword("Kkate12345$"));

        app.getHelperUser().submitRegistration ();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password format"));
    }
    @Test
    public void registrationWrongPassword(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("cat@gmail.com","Kk5$");
        app.getHelperUser().submitRegistration ();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("Wrong email or password format"));
    }
    @Test
    public void registrationUserAlreadyExists(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("kate@gmail.com","Kkate12345$");
        app.getHelperUser().submitRegistration ();
        Assert.assertFalse(app.getHelperUser().isLogged());
        Assert.assertTrue(app.getHelperUser().isErrorMessageDisplayed("User already exist"));
    }
}
