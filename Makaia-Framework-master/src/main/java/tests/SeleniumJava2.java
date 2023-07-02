package tests;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumJava2 extends SeleniumBase{
	
	SeleniumBase sb = new SeleniumBase();
	GmailLocator gl = new GmailLocator();
	
	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		
		String url = "https://support.google.com/accounts/answer/27441?hl=en#";
		sb.launchBrower("chrome",url);	
	}
	
	@Test
	public void gmailAccountCreation() throws InterruptedException {
		
		sb.clickByXpath(gl.enterButtonMyself);
		
		sb.swichToWindow(1);
		
		String actualNameText = sb.getTextByXpath(gl.enterName);
		String expetedNameText="Enter your name";
		Assert.assertEquals(actualNameText,expetedNameText);
		System.out.println("User is at Enter your name page");
		
		sb.enterTextByXpath(gl.enterFirstName, "Rukhshar");
		sb.enterTextByXpath(gl.enterLastName, "Fatema");
		
		sb.clickByXpath(gl.nextButton);
		
		Thread.sleep(2000);
		
        String actualBasicInfo = sb.getTextByXpath(gl.textBasicInformation);
		String expetedBasicInfo="Basic information";
		Assert.assertEquals(actualBasicInfo,expetedBasicInfo);
		System.out.println("User is at Basic information page");
		
		sb.selectByVisisbleText(gl.monthJanuary, "January");
		
        sb.enterTextByID(gl.day,"04");	
        sb.enterTextByID(gl.year,"1995");
		
        sb.selectByVisisbleTextByID(gl.gender,"Female");
		
		sb.clickByXpath(gl.nextButton);
		
		Thread.sleep(2000);
		
		String actualGmailAddress = sb.getTextByXpath(gl.textChooseEmailAdd);	
	    String expetedGmailAddress="Choose your Gmail address";	
		Assert.assertEquals(actualGmailAddress,expetedGmailAddress);
		System.out.println("User is at Choose your Gmail address page");
		
		sb.clickByXpath(gl.radioButton);	
		sb.clickByXpath(gl.nextButton);
		
	}
       @AfterTest
       public void closeBrowser(){
    	   sb.closeBrower();	
	  }

}
