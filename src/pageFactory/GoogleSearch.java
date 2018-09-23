package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearch {
	
	WebDriver driver;
	
	@FindBy(id="lst-ib")
	WebElement searchBox;
	
	@FindBy(name="btnK")
	WebElement submitButton;

	public GoogleSearch(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterSearchText(String text){
		this.searchBox.sendKeys(text);
	}
	
	public void searchForText(){
		this.submitButton.click();
	}

}
