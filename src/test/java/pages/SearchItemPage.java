package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchItemPage {
    @FindBy(xpath = "//input[@class='search-input']")
    public WebElement txtSearchItem;
    @FindBy(xpath="//tr[td[normalize-space()='Juice']]/td[3]")
    public WebElement juiceAmount;
    @FindBy(xpath="//div[@class='summary']/span[contains(text(), 'Total Cost')]")
    public WebElement txtTotalCost;


    //AddCostPage theke Add cost button, total cost likhata ei duita nibo.

    public SearchItemPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }



}
