package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserProfilePage {
    @FindBy(xpath = "//button[@type='button' and text()='Edit']")
    public WebElement btnEdit;
    @FindBy(className = "upload-input")
    public WebElement btnFileChoose;
    @FindBy(xpath="//button[@type='button' and text()='Upload Image']")
    public WebElement btnUploadImage;
    @FindBy(xpath ="//button[@type='button' and text()='Update']" )
    public WebElement btnUpdate;

    public UserProfilePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

}
