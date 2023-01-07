package tests;

import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @Test
    public void loginSuccess(){
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("kate@gmail.com","Kkate12345$");
        app.getHelperUser().submitLogin();
    }
    @Test
    public void loginWrongEmail(){}
    @Test
    public void loginWrongPassword(){}
    @Test
    public void loginUnregisteredUser(){}
}
