package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCostPage {

    @FindBy(className = "add-cost-button")
    public WebElement btnAddCost;
    @FindBy(id="itemName")
    WebElement txtItem;
    @FindBy(xpath = "//button[text()='+']")
    WebElement btnQuantityPlus;
    @FindBy(id="amount")
    WebElement txtAmount;
    @FindBy(id="purchaseDate")
    WebElement txtPurchaseDate;
    @FindBy(id="month")
    WebElement dropDownMonth;
    @FindBy(id="remarks")
    WebElement txtRemarks;
    @FindBy(className = "submit-button")
    WebElement btnInfoSubmit;
    @FindBy(xpath="//div[@class='summary']/span[contains(text(), 'Total Cost')]")
    public WebElement txtTotalCost;

    public AddCostPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void addCost(String item,int quantity,String amount,String date,String month,String remark){

        txtItem.sendKeys(item);

         for (int i=1;i<=quantity;i++){
             btnQuantityPlus.click();
         }

         txtAmount.sendKeys(amount);

         txtPurchaseDate.clear();
         txtPurchaseDate.sendKeys(date);

        Select select=new Select(dropDownMonth);
        select.selectByValue(month);

        txtRemarks.sendKeys(remark);

        btnInfoSubmit.click();
    }
}
