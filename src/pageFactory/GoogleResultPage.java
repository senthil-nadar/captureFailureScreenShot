package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class GoogleResultPage {
	
	WebDriver driver;
	
	@FindBy( css = "#rso h3 > a")
	WebElement firstSearch;

	public GoogleResultPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	
	public boolean isFirstSearchWiki(){
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(this.firstSearch));
		String url = this.firstSearch.getAttribute("href");	
		System.out.println(url + ": " + url.contains("wikipedia"));
		return (url.contains("wikipedia"));
	}

}
