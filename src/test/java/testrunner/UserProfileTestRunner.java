package testrunner;

import config.SetUp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserProfilePage;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;


public class UserProfileTestRunner extends SetUp {
    LoginPage loginPage;
    UserProfilePage userProfile;

    @Test(priority = 1,description = "Login with last registered user")
    public void userLogin() throws IOException, ParseException {
        loginPage=new LoginPage(driver);
        JSONParser parser=new JSONParser();
        JSONArray jsonArray=(JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));

        JSONObject userObj=(JSONObject) jsonArray.get(jsonArray.size()-1);
        String email=(String) userObj.get("email");
        String password=(String)userObj.get("password");
        loginPage.doLogin(email,password);

        String headerActual = driver.findElement(By.tagName("h2")).getText();
        String headerExpected = "User Daily Costs";
        Assert.assertTrue(headerActual.contains(headerExpected));

    }

    @Test(priority = 2,description="Upload Image")
    public void profileImageUp() throws InterruptedException{
        loginPage.btnProfileIcon.click();
        loginPage.btnProfileMenuItems.get(0).click();
        userProfile=new UserProfilePage(driver);
        userProfile.btnEdit.click();
        String relativePath="\\src\\test\\resources\\image.png";
        String absolutePath=System.getProperty("user.dir")+relativePath;
        userProfile.btnFileChoose.sendKeys(absolutePath);
        userProfile.btnUploadImage.click();

//        Thread.sleep(5000);
        //Image Upload alert
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert=driver.switchTo().alert();
        String uploadImageExpected="Image uploaded successfully!";
        String uploadImageActual=alert.getText();
        Assert.assertTrue(uploadImageActual.contains(uploadImageExpected));
        alert.accept();

        //Profile update alert
        Utils.scroll(driver,500);
        userProfile.btnUpdate.click();
//        Thread.sleep(5000);

        WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(60));
        wait.until(ExpectedConditions.alertIsPresent());
        alert=driver.switchTo().alert();
        String expectedUpdatedMsg="User updated successfully!";
        String actualUpdatedMasg=alert.getText();
        Assert.assertTrue(actualUpdatedMasg.contains(expectedUpdatedMsg));
        alert.accept();

    }

}
