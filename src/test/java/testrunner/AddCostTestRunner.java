package testrunner;

import config.CostDataSet;
import config.SetUp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AddCostPage;
import pages.LoginPage;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class AddCostTestRunner extends SetUp {
    AddCostPage addCostPage;
    int totalSum=0;

    @BeforeTest
    public void doLogin() throws IOException, ParseException {
        LoginPage loginPage=new LoginPage(driver);

        JSONParser parser=new JSONParser();
        JSONArray jsonArray=(JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj=(JSONObject) jsonArray.get(jsonArray.size()-1);
        String email=(String) userObj.get("email");
        String password=(String)userObj.get("password");
        loginPage.doLogin(email,password);
    }

    @Test(priority = 1,dataProvider = "AddCostCSVData",dataProviderClass = CostDataSet.class,description = "Adding Data from CSV File",groups = "smoke")

    public void addCost(String item,int quantity,String amount,String date,String month,String remark){

        addCostPage=new AddCostPage(driver);
        addCostPage.btnAddCost.click();

        addCostPage.addCost(item,quantity,amount,date, month, remark);
        //alert handling
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
        
        //totalSum+=Integer.parseInt(amount);
        totalSum += Integer.parseInt(amount.trim().replaceAll("[^0-9]", ""));
        System.out.println("Cost added successfully for item: " + item);


    }

    @Test(priority = 2,description = "Total cost print and asserting it against expected total sum of the amounts",groups = "smoke")
    public void totalCost(){
        addCostPage=new AddCostPage(driver);

        String expectedTotalCost=String.valueOf(totalSum);
        System.out.println("Expected Total Cost="+expectedTotalCost);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(addCostPage.txtTotalCost, expectedTotalCost));

        String totalCostCal=addCostPage.txtTotalCost.getText();
        String actualTotalCost=totalCostCal.replaceAll("[^0-9]", "");
//        String actualTotalCost = totalCostCal.replaceAll("[^0-9]", "").trim();

        System.out.println("Actual Total Cost="+actualTotalCost);

        Assert.assertTrue(actualTotalCost.contains(expectedTotalCost),"Expected and actual matched.");

    }


}
