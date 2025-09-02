package organizations;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Creating_organization_With_Help_of_Excel {

	public static void main(String[] args) throws InterruptedException, IOException {
		FileInputStream fis = new FileInputStream("F:\\java\\vtiger-crm-framework-A11\\src\\test\\resources\\commondata.properties");

		Properties pObj = new Properties();
		pObj.load(fis);

		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("un");
		String PASSWORD = pObj.getProperty("pwd");
		
        FileInputStream fis1 = new FileInputStream("F:\\java\\vtiger-crm-framework-A11\\src\\test\\resources\\testScriptData.xlsx");
		
		Workbook wb = WorkbookFactory.create(fis1);

		Sheet sh = wb.getSheet("org");
		
		String orgName = sh.getRow(8).getCell(0).getStringCellValue() + (int) (Math.random() * 9999);
		System.out.println(orgName);
		
        double phoneNo = sh.getRow(8).getCell(1).getNumericCellValue();
        String phoneNumber = String.valueOf((long)phoneNo);
		System.out.println(phoneNumber);
		
		double otherphoneNo = sh.getRow(8).getCell(2).getNumericCellValue();
        String otherphoneNumber = String.valueOf((long)otherphoneNo);
		System.out.println(otherphoneNumber);
		
		String Website = sh.getRow(8).getCell(3).getStringCellValue();
		System.out.println(Website);
		
		String industry = sh.getRow(8).getCell(4).getStringCellValue();
		System.out.println(industry);
		
		String rating = sh.getRow(8).getCell(5).getStringCellValue();
		System.out.println(rating);
				
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		Thread.sleep(3000);
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys(USERNAME);
		Thread.sleep(1000);
		WebElement password = driver.findElement(By.name("user_password"));
		password.sendKeys(PASSWORD);
		Thread.sleep(1000);
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		Thread.sleep(3000);
		
		WebElement orgField =driver.findElement(By.name("accountname"));
		orgField.sendKeys(orgName);
		
		
		WebElement phn = driver.findElement(By.id("phone"));
		phn.sendKeys(phoneNumber);
		
		WebElement otherphn = driver.findElement(By.id("otherphone"));
		otherphn.sendKeys(otherphoneNumber);
		
		WebElement website = driver.findElement(By.xpath("//input[@style='width:74%;']"));
		website.sendKeys(Website);

		WebElement industryID = driver.findElement(By.name("industry"));
		Select sel = new Select(industryID);
		sel.selectByVisibleText(industry);
		String indusValue = sel.getFirstSelectedOption().getText();
		Thread.sleep(2000);
		
		WebElement ratingRR =driver.findElement(By.name("rating"));
		Select selrating = new Select(ratingRR);
		selrating.selectByVisibleText(rating);
		String ratingValue = selrating.getFirstSelectedOption().getText();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		Thread.sleep(3000);

		String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if (actOrgName.equals(orgName)) {
			System.out.println("Organization created successfully!!!");
		}else {
			System.out.println("Organization could not be created successfully!!!");
		}
		
		String actPhone = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actPhone.equals(phoneNumber)) {
			System.out.println("Phone number verified successfully!");
		} else {
			System.out.println("Phone number mismatch!");
		}
		
		String actOtherPhone = driver.findElement(By.id("dtlview_Other Phone")).getText();
		if (actOtherPhone.equals(otherphoneNumber)) {
			System.out.println("Other phone number verified successfully!");
		} else {
			System.out.println("Other phone number mismatch!");
		}
		
		String actWebsite = driver.findElement(By.id("dtlview_Website")).getText();
		if (actWebsite.equals(Website)) {
			System.out.println("Website verified successfully!");
		} else {
			System.out.println("Website mismatch!");
		}
		
        String actIndustry=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustry.equals(indusValue)) {
		    System.out.println("Industry verified successfully!");
		}else {
		    System.out.println("Industry mismatch!");
		}
		
		String actRating = driver.findElement(By.id("dtlview_Rating")).getText();
		if(actRating.equals(ratingValue)) {
		    System.out.println("Rating verified successfully!");
		} else {
		    System.out.println("Rating mismatch!");
		}

		
		WebElement SignOut=driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(SignOut).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(3000);
		driver.quit();

	}

}
