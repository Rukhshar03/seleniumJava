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
import org.testng.annotations.Test;

public class SeleniumJava {
	
	@Test
	public void gmailAccountCreation() throws InterruptedException {
		
		System.out.println();
		
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\tariq\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		System.setProperty("webdriver.chrome.driver",".\\src\\main\\java\\drivers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://support.google.com/accounts/answer/27441?hl=en#");
		
		driver.manage().window().maximize();
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		Thread.sleep(5000);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
		
		driver.findElement(By.xpath("//a[text()='For myself']")).click();
		
		Set<String> allhandles = driver.getWindowHandles();
		
		System.out.println(allhandles);
		
		List<String> list = new ArrayList<String>();
		
		list.addAll(allhandles);
		
		for(int i=0;i<list.size();i++) {
			
			//wait.until(driver.findElement(null).f)
			driver.switchTo().window(list.get(1));
		}
		
		String actualNameText = driver.findElement(By.xpath("//span[text()='Enter your name']")).getText();
		
		String expetedNameText="Enter your name";
		
		Assert.assertEquals(actualNameText,expetedNameText);
		
		System.out.println("User is at Enter your name page");
		
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys("Rukhshar");
		
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys("Fatema");
		
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		Thread.sleep(5000);
		
        String actualBasicInfo = driver.findElement(By.xpath("//span[text()='Basic information']")).getText();
		
		String expetedBasicInfo="Basic information";
		
		Assert.assertEquals(actualBasicInfo,expetedBasicInfo);
		
		System.out.println("User is at Basic information page");
		
		Select dd = new Select(driver.findElement(By.xpath("//select[@id='month']")));
		
		dd.selectByVisibleText("January");
		
		driver.findElement(By.id("day")).sendKeys("04");
		
		driver.findElement(By.id("year")).sendKeys("1995");
		
		Select dd1 = new Select(driver.findElement(By.id("gender")));
		
		dd1.selectByVisibleText("Female");
		
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		Thread.sleep(2000);
		
		String actualGmailAddress = driver.findElement(By.xpath("//span[text()='Choose your Gmail address']")).getText();
			
	    String expetedGmailAddress="Choose your Gmail address";
			
		Assert.assertEquals(actualGmailAddress,expetedGmailAddress);
		
		System.out.println("User is at Choose your Gmail address page");
		
		driver.findElement(By.xpath("(//div[@class='t5nRo Id5V1'])[1]")).click();
		
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		
		driver.quit();
		
		 	
	}
	

		
		
		
		
		
	

}
