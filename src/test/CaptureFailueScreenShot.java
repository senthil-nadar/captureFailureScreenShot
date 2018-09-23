package test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageFactory.GoogleResultPage;
import pageFactory.GoogleSearch;

public class CaptureFailueScreenShot {
	
	WebDriver driver;
	
	@BeforeSuite
	@Parameters({"browserName"})
	public void browserSetUp(String browserName){
		if(browserName.equalsIgnoreCase("internetexplorer")){
		System.setProperty("webdriver.ie.driver","C:\\seleniumDrivers\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")){
		System.setProperty("webdriver.gecko.driver","C:\\seleniumDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		}
		else{
		System.setProperty("webdriver.chrome.driver","C:\\seleniumDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
	}
	
	@Test (dataProvider = "searchTerms")
	public void googleSearchForText(String firstTerm, String secondTerm){
		driver.get("https://www.google.com");
		GoogleSearch googlePage=new GoogleSearch(driver);
		googlePage.enterSearchText(firstTerm+" "+secondTerm);
		googlePage.searchForText();
		
		GoogleResultPage resultPage=new GoogleResultPage(driver);
		boolean isWikiSearch = resultPage.isFirstSearchWiki();
		if(!(isWikiSearch)){
			try {
				SoftAssert softAssert=new SoftAssert();
				softAssert.assertEquals(true, isWikiSearch);
				File screenShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(screenShotFile, new File("C:\\Seleinum\\Eclipse Workspace\\CaptureFailureScreenShot\\failureScreenShots\\"+System.currentTimeMillis()+".png"));
				softAssert.assertAll();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	@DataProvider(name = "searchTerms")
	public String[][] getSearchTerms(){
		String[][] input = {{"Gaming","Console"},{"Abdul","Kalam"},{"Finance","Minister"}};
		return input;
	}
	
	@AfterSuite
	public void tearDownSetUp(){
		driver.quit();
	}

}
