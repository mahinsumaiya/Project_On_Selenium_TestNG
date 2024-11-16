package testrunner;

import com.github.javafaker.Faker;
import config.SetUp;
import config.UserModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.Utils;

import java.io.IOException;


public class RegistrationTestRunner extends SetUp {

    @Test(priority = 1,description = "User can register by providing all info")
    public void userRegByAllFields() throws InterruptedException, IOException, ParseException {
        RegistrationPage userReg = new RegistrationPage(driver);
        Faker faker=new Faker();

        userReg.btnRegister.get(1).click();

        String firstname =faker.name().firstName();
        String lastname = faker.name().lastName();
        String email = "mahinsumaiya"+Utils.generateRandomNumber(10,99)+"@gmail.com";
        String password = "1234";
        String phonenumber = "01521"+ Utils.generateRandomNumber(100000,999999);
        String address = faker.address().fullAddress();

        UserModel userModel=new UserModel();
        userModel.setFirstname(firstname);
        userModel.setLastname(lastname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);
        userModel.setAddress(address);

        userReg.doRegistration(userModel);

        //Assertion
        doRegAssertion();

        //Storing info(i will use it in the end)
        JSONObject userObj=new JSONObject();
        userObj.put("firstName",firstname);
        userObj.put("lastName",lastname);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phonenumber);
        userObj.put("address",address);

        Utils.saveUserInfo("./src/test/resources/users.json",userObj);
        Thread.sleep(2000);
    }

    @Test(priority =2,description = "User can register by providing only mandatory info")
    public void userRegByMandatoryFields() throws IOException, ParseException, InterruptedException {
        RegistrationPage userReg=new RegistrationPage(driver);
        Faker faker=new Faker();
        userReg.btnRegister.get(1).click();

        String firstname =faker.name().firstName();
        String email = "mahinsumaiya"+Utils.generateRandomNumber(10,99)+"@gmail.com";
        String password = "1234";
        String phonenumber = "01521"+ Utils.generateRandomNumber(100000,999999);

        UserModel userModel=new UserModel();

        userModel.setFirstname(firstname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phonenumber);

        userReg.doRegistration(userModel);

        //Assertion
        doRegAssertion();

        //Storing info
        JSONObject userObj=new JSONObject();
        userObj.put("firstName",firstname);
        userObj.put("email",email);
        userObj.put("password",password);
        userObj.put("phoneNumber",phonenumber);
        Utils.saveUserInfo("./src/test/resources/users.json",userObj);
        Thread.sleep(5000);
    }

    @Test(priority =3,description = "User Registration with missing mandatory fields")
    public void userRegWithoutMandatoryField() throws InterruptedException {
        RegistrationPage userReg=new RegistrationPage(driver);
        Thread.sleep(2000);
        userReg.btnRegister.get(1).click();
        userReg.btnSubmitReg.click();

        String validationError=userReg.txtFirstName.getAttribute("validationMessage");
        Assert.assertTrue(validationError.contains("Please fill in this field"));

    }

    public void doRegAssertion() throws InterruptedException {
        Thread.sleep(4000);
//        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(50));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("Toastify__toast")));
        String successMessageActual=driver.findElement(By.className("Toastify__toast")).getText();
        System.out.println(successMessageActual);
        String successMessageExpected="successfully";
        Assert.assertTrue(successMessageActual.contains(successMessageExpected));
    }
}
