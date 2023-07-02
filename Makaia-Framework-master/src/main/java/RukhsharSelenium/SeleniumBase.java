package RukhsharSelenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SeleniumBase {
	
	   WebDriver driver ;
	
	    public void launchBrower(String browser,String url) throws InterruptedException {
	    	
	    if(browser.equalsIgnoreCase("chrome")) {
		
        System.setProperty("webdriver.chrome.driver",".\\src\\main\\java\\drivers\\chromedriver.exe");
		
	    driver = new ChromeDriver();
	    
	    }
	    
	    else if(browser.equalsIgnoreCase("firefox")) {
			
	        System.setProperty("webdriver.chrome.driver",".\\src\\main\\java\\drivers\\geckodriver.exe");
			
		    driver = new FirefoxDriver();
		    
		   
	    }
	    
	    else {
	    	
	    System.setProperty("webdriver.chrome.driver",".\\src\\main\\java\\drivers\\safaridriver.exe");
				
		driver = new SafariDriver();
	
			    
	    }
		
		driver.get(url);
		
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		Thread.sleep(5000);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		
	   }
	 
	    
	 public void enterTextByXpath(String enterXpath , String text) {
		 
			driver.findElement(By.xpath(enterXpath)).sendKeys(text);
			System.out.println("User has entered text : " +text);
			Reporter.log("User has entered text : " +text);
	 }
	 
	 public String getTextByXpath(String enterXpath) {
		 
	    return driver.findElement(By.xpath(enterXpath)).getText();		
	 }
	 
	 public void enterTextByID(String enterID , String text) {
		 
			driver.findElement(By.id(enterID)).sendKeys(text);
			System.out.println("User has entered text : " +text);
			Reporter.log("User has entered text : " +text);
	 }
	 
	 public void enterTextByName(String enterByName , String text) {
		 
			driver.findElement(By.name(enterByName)).sendKeys(text);
			System.out.println("User has entered text : " +text);
			Reporter.log("User has entered text : " +text);
			
	 }
	 
	 
     public void clickByXpath(String enterXpath ) {
    	 
    	 driver.findElement(By.xpath(enterXpath)).click();
    	 String text = driver.findElement(By.xpath(enterXpath)).getText();
    	 System.out.println("User has clicked on " +text + " button");
    	 Reporter.log("User has clicked on " +text + " button");
    	 
      }
     
    
     public void selectByVisisbleText(String enterXpath , String text) {
    	 
    	 Select dd = new Select(driver.findElement(By.xpath(enterXpath)));
 		 dd.selectByVisibleText(text); 
     }
     
     
        public void selectByVisisbleTextByID(String enterID , String text) {
    	 
    	 Select dd = new Select(driver.findElement(By.id(enterID)));
 		 dd.selectByVisibleText(text); 
 		 System.out.println("User has selected " +text+ " from the dropdown");
 		 Reporter.log("User has selected " +text+ " from the dropdown");
     }
        
        public void swichToWindow(int window) {
        	
        	Set<String> allhandles = driver.getWindowHandles();
    		
    		System.out.println(allhandles);
    		
    		List<String> list = new ArrayList<String>();
    		
    		list.addAll(allhandles);
    		
    		for(int i=0;i<list.size();i++) {
    			
    			//wait.until(driver.findElement(null).f)
    			driver.switchTo().window(list.get(window));
    		}
        	
        }
    
        
        
	 public void closeBrower() {
		 driver.close();
		 driver.quit();
	 }

}
