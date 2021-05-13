package Testcase1;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Login1 {
	
	ExtentReports report;
	 ExtentTest log;
	
	WebDriver dr;
	
	
	@BeforeTest
	@Parameters("browser")
	
	public void setup(String browser) throws Exception{
		
		
		report = new ExtentReports("./Reports/AutomationResult.html");
		log = report.startTest("Verify_Signin");

		
		//Check if parameter passed from TestNG is 'firefox'
		if(browser.equalsIgnoreCase("firefox")){
		//create firefox instance
			dr = new FirefoxDriver();
			log.log(LogStatus.INFO, "Launching Firefox browser");
		}

		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			log.log(LogStatus.INFO, "Launchin Chrome browser");
			//set path to chromedriver.exe
			//create chrome instance
			dr = new ChromeDriver();
			
		}
				
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@Test
	public void VerifySignin() throws InterruptedException{
		
		dr.get("https://statv2-dev.azurewebsites.net/login");
		log.log(LogStatus.INFO, "SignIn");
		
		dr.findElement(By.xpath("//body/app-root[1]/app-login[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[1]/input[1]")).sendKeys("Testuser1@gmail.com");
		dr.findElement(By.xpath("//body/app-root[1]/app-login[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/div[2]/input[1]")).sendKeys("Qwe4y@123");
		dr.findElement(By.xpath(" //body/app-root[1]/app-login[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/fieldset[1]/button[1]")).click();
		log.log(LogStatus.INFO, "launch_Homepage");
		Thread.sleep(3000);
WebDriverWait wait = new WebDriverWait(dr, 30);
		
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//app-header/nav[1]/div[1]/span[1]/span[2]/div[1]/i[1]")));
		dr.findElement(By.xpath("//app-header/nav[1]/div[1]/span[1]/span[2]/div[1]/i[1]")).click();
		
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//app-header/nav[1]/div[1]/span[1]/span[2]/div[2]/a[2]")));
		dr.findElement(By.xpath("//app-header/nav[1]/div[1]/span[1]/span[2]/div[2]/a[2]")).click();
		log.log(LogStatus.INFO, "Signout");
	}
	
	
@AfterMethod	
	
	public void takeSs(ITestResult results)
	{
		if(results.isSuccess())
		{
			log.log(LogStatus.PASS, "Test case got passed"+results.getName());
		}
		else {
			log.log(LogStatus.FAIL, "Test case got failed"+results.getName());
		}
			
		
}	
		
	
	

	@AfterMethod
	public void teardown() {
		
		dr.quit();
	
		report.endTest(log);
		report.flush();
	}
	
	
	
}

	


