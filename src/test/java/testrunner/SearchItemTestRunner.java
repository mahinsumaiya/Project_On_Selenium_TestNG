package testrunner;

import config.SetUp;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SearchItemPage;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;

public class SearchItemTestRunner extends SetUp {

    @Test(priority = 1,description = "Login with last registered user",groups = "smoke")
    public void doLogin() throws IOException, ParseException {
        LoginPage loginPage=new LoginPage(driver);

        JSONParser parser=new JSONParser();
        JSONArray jsonArray=(JSONArray) parser.parse(new FileReader("./src/test/resources/users.json"));
        JSONObject userObj=(JSONObject) jsonArray.get(jsonArray.size()-1);
        String email=(String) userObj.get("email");
        String password=(String)userObj.get("password");
        loginPage.doLogin(email,password);
    }
    @Test(priority = 2,description = "Search for an item by name from the list",groups = "smoke")
    public void searchItem(){
        SearchItemPage searchItem=new SearchItemPage(driver);
        searchItem.txtSearchItem.sendKeys("Juice");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(searchItem.juiceAmount));

        String expectedAmount=searchItem.juiceAmount.getText();
        System.out.println("Search item's Expected amount: "+expectedAmount);

        wait.until(ExpectedConditions.visibilityOf(searchItem.txtTotalCost));

        String totalCostText=searchItem.txtTotalCost.getText();
        System.out.println(totalCostText);
        String actualAmount=totalCostText.replaceAll("[^0-9]","");
        System.out.println("Search item's Actual amount: "+actualAmount);
        Assert.assertTrue(actualAmount.contains(expectedAmount),"Amount matched!");
    }
}
