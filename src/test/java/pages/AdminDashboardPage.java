package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class AdminDashboardPage {
    @FindBy(xpath = "//table//tbody//tr[1]//td")
    public List<WebElement> tableFirstRow;


    public AdminDashboardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);

    }

    public void matchingLastUserData(String firstname,String email,String phoneNumber){
        String FirstName=tableFirstRow.get(0).getText();
        String Email=tableFirstRow.get(2).getText();
        String PhoneNumber=tableFirstRow.get(3).getText();

        Assert.assertTrue(FirstName.contains(firstname));
        Assert.assertTrue(Email.contains(email));
        Assert.assertTrue(PhoneNumber.contains(phoneNumber));


    }


}


