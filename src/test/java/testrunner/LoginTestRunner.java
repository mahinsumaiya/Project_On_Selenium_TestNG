package testrunner;
import config.SetUp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import java.io.FileReader;
import java.io.IOException;

public class LoginTestRunner extends SetUp {
    LoginPage loginPage;

    @Test(priority = 1,description = "Admin login with wrong creds")
    public void adminLoginWithWrongCreds(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("admin@test.com","wrongPassword");
        String errorMessageActual=driver.findElement(By.tagName("p")).getText();
        String errorMessageExpected="Invalid";
        Assert.assertTrue(errorMessageActual.contains(errorMessageExpected));

        clearCreds();
    }

    @Test(priority = 2,description = "Admin login with right creds")
    public void adminLogin() {
        loginPage = new LoginPage(driver);
        if(System.getProperty("username")!=null && System.getProperty("password")!=null){
            loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        }
        else {
            loginPage.doLogin("admin@test.com","admin123");
        }
        //Assertion
        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "Admin Dashboard";
        Assert.assertTrue(headerActual.contains(headerExpected));
        loginPage.doLogout();
    }

//    @Test(priority = 3,description ="LastUser can login with correct creds" )
//    public void userLogin() throws IOException, ParseException {
//        loginPage=new LoginPage(driver);
//
//        String email=(String) userObj.get("email");
//        String password=(String) userObj.get("password");
//
//        loginPage.doLogin(email,password);
//
//        loginPage.btnProfileIcon.isDisplayed();
//        Assert.assertTrue(loginPage.btnProfileIcon.isDisplayed());
//        loginPage.doLogout();
//
//    }
    public void clearCreds(){
        loginPage=new LoginPage(driver);
        loginPage.txtEmail.sendKeys(Keys.CONTROL,"a");
        loginPage.txtEmail.sendKeys(Keys.BACK_SPACE);

        loginPage.txtPassword.sendKeys(Keys.CONTROL,"a");
        loginPage.txtPassword.sendKeys(Keys.BACK_SPACE);

    }

}


