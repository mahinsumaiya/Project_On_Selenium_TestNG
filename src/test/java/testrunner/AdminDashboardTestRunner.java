package testrunner;

import config.SetUp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AdminDashboardPage;
import pages.LoginPage;

import java.io.FileReader;
import java.io.IOException;

public class AdminDashboardTestRunner extends SetUp {
    LoginPage loginPage;
    @BeforeTest
    public void doLogin(){
        loginPage=new LoginPage(driver);
        if(System.getProperty("username")!=null && System.getProperty("password")!=null){
            loginPage.doLogin(System.getProperty("username"),System.getProperty("password"));
        }
        else {
            loginPage.doLogin("admin@test.com","admin123");
        }
    }
@Test(priority = 1,description = "checking if the last registered user is displayed on the admin dashboard")
    public void userValueChecking() throws IOException, ParseException {
        JSONParser parser=new JSONParser();
        JSONArray jsonArray=(JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj= (JSONObject) jsonArray.get(jsonArray.size()-1); //Getting Last user for login

        String firstName=(String) userObj.get("firstName");
        String email=(String) userObj.get("email");
        String phoneNumber=(String) userObj.get("phoneNumber");

        AdminDashboardPage lastUserObj=new AdminDashboardPage(driver);
        lastUserObj.matchingLastUserData(firstName,email,phoneNumber);
//    WebElement firstRow=lastUserObj.getLastRow();

    }

}
