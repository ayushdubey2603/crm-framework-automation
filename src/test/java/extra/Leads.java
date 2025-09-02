package extra;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Leads {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://localhost:8888/");
		Thread.sleep(3000);
		WebElement username = driver.findElement(By.name("user_name"));
		username.sendKeys("admin");
		Thread.sleep(1000);
		WebElement password = driver.findElement(By.name("user_password"));
		password.sendKeys("manager");
		Thread.sleep(1000);
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Leads']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		Thread.sleep(3000);
		
		WebElement salutationST =driver.findElement(By.name("salutationtype"));
		Select selsalutation = new Select(salutationST);
		selsalutation.selectByValue("Mr.");
		
		String firstName = "Ab";
		WebElement firstField =driver.findElement(By.name("firstname"));
		firstField.sendKeys(firstName);
		
		String lastName = "cd"+(int)(Math.random()*99);
		WebElement lastField =driver.findElement(By.name("lastname"));
		lastField.sendKeys(lastName);
		
		WebElement company = driver.findElement(By.name("company"));
		company.sendKeys("xyz"+(int)(Math.random()*999));
		
		//WebElement phn = driver.findElement(By.id("phone"));
		//phn.sendKeys("912456"+(int)(Math.random()*9999));
		
		WebElement leadsourceLS =driver.findElement(By.name("leadsource"));
		Select leadsource = new Select(leadsourceLS);
		leadsource.selectByValue("Self Generated");
		
		WebElement industryID =driver.findElement(By.name("industry"));
		Select selindustry = new Select(industryID);
		selindustry.selectByValue("Banking");
		
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		Thread.sleep(3000);
		
		WebElement SignOut=driver.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		Actions action=new Actions(driver);
		action.moveToElement(SignOut).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(3000);
		
		driver.quit();
	}

}
