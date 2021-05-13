package Testcase1;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class Login {
	
	 ExtentReports report;
	 ExtentTest log;
	 
	
	WebDriver dr;

	@BeforeTest

	public void setup() {
		report = new ExtentReports("./Reports/AutomationResult.html");
		log = report.startTest("OneSource automation result");

		// dr = new SafariDriver();

		// WebDriver dr = new SafariDriver();

		dr = new ChromeDriver();

		dr.manage().window();

		dr.get("https://osqbo.com/");

		//dr.findElement(By.xpath("//button [@type='button']")).click();

	}

	@Test
	public void signin() throws InterruptedException {
		dr.findElement(By.id("txtUserId")).sendKeys("muralikannanest1@gmail.com");
		dr.findElement(By.id("txtPassword")).sendKeys("Qwerty@123");	
		dr.findElement(By.xpath("//button [@type='submit']")).click();
		dr.findElement(By.xpath("//a [@class = 'linkcolor']")).click();
//dashboard 
		dr.findElement(By.xpath("//label[contains(text(),'Customers')]")).click();

		String parent = dr.getWindowHandle();

		Set<String> s = dr.getWindowHandles();

		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			if (!parent.equals(child_window)) {
				dr.switchTo().window(child_window);
				
// dashboard customer list
				WebDriverWait wait = new WebDriverWait(dr, 30);

				wait.until(
						ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(),'Customers')]")));
				dr.findElement(By.xpath("//label[contains(text(),'Customers')]")).click();

// customer add new
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='btnnewcustomer']")));
				dr.findElement(By.xpath("//button[@id='btnnewcustomer']")).click();

// Company name

				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='txtCompanyName']")));
				dr.findElement(By.xpath("//input[@id='txtCompanyName']")).sendKeys("sam billing ");

// Address type dropdwon
				

				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ddlAddressType_text")));
				dr.findElement(By.id("ddlAddressType_text")).clear();

				//dr.findElement(By.id("ddlAddressType_text")).clear();
				
				Thread.sleep(5000);

	/*		Actions action = new Actions(dr);
				WebElement optionsList = dr.findElement(By.id("ddlAddressType_text"));
				action.moveToElement(optionsList);

				List<WebElement> options = dr.findElements(By.id("ddlAddressType_text"));
				for (WebElement option : options) {
					if (option.getText().equals("Both (Bill To & Ship To)")) {
						option.click();
						
						Thread.sleep(5000);*/
//First name						
					
						wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@id='txtFirstName']")));
						dr.findElement(By.xpath("//input[@id='txtFirstName']")).sendKeys("Sam");
						
//Last name
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='txtLastName']")));
						dr.findElement(By.xpath("//input[@id='txtLastName']")).sendKeys("billing ");
						
			
						
						
						System.out.println("----------------");

						// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='txtCompanyName']")));
						// dr.findElement(By.xpath("//input[@id='txtCompanyName']")).sendKeys("sam
						// billing ");

						System.out.println(dr.switchTo().window(child_window).getTitle());

							
					}

				}
				// switch to the parent window
				dr.switchTo().window(parent);

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
		
	
	

	@AfterTest
	public void teardown() {
		
		dr.quit();
		report.endTest(log);
		report.flush();
	}
	}

	
