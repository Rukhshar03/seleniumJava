package RukhsharSelenium;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FacebookLogin_TC_02 extends SeleniumBase{
	
	SeleniumBase sb = new SeleniumBase();
	GmailLocator gl = new GmailLocator();
	
	@BeforeTest
	public void launchBrowser() throws InterruptedException {
		
		String url = "https://www.facebook.com/";
		sb.launchBrower("chrome",url);	
	}
	
	@Test()
	public void facebookAccountCreation() throws InterruptedException {
		
		sb.clickByXpath(gl.allowCookiesBtn);
		sb.clickByXpath(gl.createAccountBtn);
		Thread.sleep(2000);
		
		sb.enterTextByName(gl.firstName,"Sania");
		sb.enterTextByName(gl.lastName,"Mirza");
		sb.enterTextByName(gl.email,"rukhsharfatema123@gmail.com");
		sb.enterTextByName(gl.reEnterEmail,"rukhsharfatema123@gmail.com");
		sb.enterTextByName(gl.newPasswordtextBox,"Lovely.10");
		
		sb.selectByVisisbleText(gl.fbMonth,"Jul");
		sb.selectByVisisbleText(gl.fbDay,"7");
		sb.selectByVisisbleText(gl.fbYear,"2000");
		
		sb.clickByXpath(gl.selectGenderF);
		sb.clickByXpath(gl.singUpBtn);
		
		Thread.sleep(10000);
		
		
	}
       @AfterTest
       public void closeBrowser(){
    	   sb.closeBrower();	
	  }

}
