package document;

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

public class Creating_Document_With_DownloadType {
	public static void main(String[] args) throws InterruptedException, IOException {
		FileInputStream fis = new FileInputStream("F:\\java\\vtiger-crm-framework-A11\\src\\test\\resources\\commondata.properties");

		Properties pObj = new Properties();
		pObj.load(fis);

		String URL = pObj.getProperty("url");
		String USERNAME = pObj.getProperty("un");
		String PASSWORD = pObj.getProperty("pwd");
		
		FileInputStream fis1 = new FileInputStream("F:\\java\\vtiger-crm-framework-A11\\src\\test\\resources\\testScriptData.xlsx");
        Workbook wb = WorkbookFactory.create(fis1);
        Sheet sh = wb.getSheet("doc"); 

        String titleName = sh.getRow(1).getCell(0).getStringCellValue() + (int)(Math.random()*999);
        System.out.println(titleName);

        String groupId = sh.getRow(1).getCell(1).getStringCellValue(); 
        System.out.println(groupId);
        
        String downloadType = sh.getRow(1).getCell(2).getStringCellValue();
        System.out.println(downloadType);

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

        driver.findElement(By.xpath("//a[text()='Documents']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
        Thread.sleep(3000);

        
        WebElement titleField = driver.findElement(By.name("notes_title"));
        titleField.sendKeys(titleName);

       
        driver.findElement(By.xpath("//input[@value='T']")).click();
        WebElement assignAT = driver.findElement(By.name("assigned_group_id"));
        Select selassign = new Select(assignAT);
        selassign.selectByVisibleText(groupId);
			
        WebElement downloadDT = driver.findElement(By.name("filelocationtype"));
        Select seldownload = new Select(downloadDT);
        seldownload.selectByVisibleText(downloadType);
			
			driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
			Thread.sleep(3000);
			
			 String actTitle = driver.findElement(By.id("dtlview_Title")).getText();
		        if (actTitle.equals(titleName)) {
		            System.out.println("Document created successfully with Title!");
		        } else {
		            System.out.println("Document Title mismatch!");
		        }
		        
		        String actDownloadType = driver.findElement(By.id("dtlview_File Location Type")).getText();
		        if (actDownloadType.equalsIgnoreCase(downloadType)) {
		            System.out.println("Download Type selected correctly!");
		        } else {
		            System.out.println("Download Type mismatch!");
		        }

			WebElement SignOut=driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
			Actions action=new Actions(driver);
			action.moveToElement(SignOut).perform();
			
			driver.findElement(By.linkText("Sign Out")).click();
			Thread.sleep(3000);
		
	      driver.quit();	
	}

}
