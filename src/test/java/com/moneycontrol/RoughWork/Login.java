package com.moneycontrol.RoughWork;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;


public class Login {

	WebDriverWait wait;
	WebDriver driver;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sh;
	List<WebElement> StockName;
	List<WebElement> StockPrice;
	String[] columns = {"StackName","StockPrice"};
	JavascriptExecutor js ;
	
	
	//@Test(priority=0)
	public void siteLogin()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\JUNEJA\\eclipse-workspace\\ZERODHA\\src\\test\\java\\com\\moneycontrol\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		driver.get("https://m.moneycontrol.com/login.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		List<WebElement> allFrames = driver.findElements(By.tagName("iframe"));
		System.out.println("The size of all frames is "+allFrames.size());
		for(int i=0;i<allFrames.size();i++)
		{
			driver.switchTo().frame(i);
			if (driver.findElements(By.xpath("//*[@id='email'][@class='textfield'][@type='text'][@form-name='register_form']")).size()==1)
			{
				System.out.println("FRAME FOUND IS"+ i);
				/*wait= new WebDriverWait(driver,5);
				wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//input[@id='email'][@form-name='login_form']"))));*/
				
				driver.findElement(By.xpath("//input[@id='email'][@form-name='login_form']")).click();
				driver.findElement(By.xpath("//input[@id='email'][@form-name='login_form']")).sendKeys("junejavit@gmail.com");
				driver.findElement(By.xpath("//input[@id='pwd'][@form-name='login_form']")).click();
				driver.findElement(By.xpath("//input[@id='pwd'][@form-name='login_form']")).sendKeys("Password_1234");
				driver.findElement(By.xpath("//button[text()='Login']")).click();
				break;
				}
			driver.switchTo().defaultContent();
			
			System.out.println("FRAME IS"+ i);
		}
		
}
	
	//@Test(priority=1)
	public void checkWatchlist()
	{
		
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Actions act = new Actions(driver);
		WebElement Menu = driver.findElement(By.cssSelector("#useric"));
		WebElement WatchList = driver.findElement(By.xpath("//*[@id='loggedinuser']/li[3]/a"));
		act.moveToElement(Menu).click().build().perform();
		System.out.println("Move To Element Step Performed");
		act.moveToElement(WatchList).click().build().perform();
					
	}
	
	/*@Test(priority=2)
	public void gotoWatchList()
	{
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		JavascriptExecutor jsc = (JavascriptExecutor) driver;
		jsc.executeScript("document.geElementById('useric').click();");
		jsc.executeScript("document.getElementById('loggedinuser').click();");
					
	}
	*/
	
	//@Test(priority=3)
	public void StockPrice()
	{
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		StockName =driver.findElements(By.xpath("//table[4]/tbody/tr[3]/td/table[1]/tbody/tr/td[1]"));
		StockPrice = driver.findElements(By.xpath("//table[4]/tbody/tr[3]/td/table[1]/tbody/tr/td[2]"));
		
		for(int i=0;i<StockName.size();i++)
		{
			System.out.println(StockName.get(i).getText() +"   "+StockPrice.get(i).getText());
				
		}
		
	}
	
	
	//https://www.callicoder.com/java-write-excel-file-apache-poi/
	//@Test(priority=4)
	public void writeExcel() throws IOException
	{
		StockName =driver.findElements(By.xpath("//table[4]/tbody/tr[3]/td/table[1]/tbody/tr/td[1]"));
		StockPrice = driver.findElements(By.xpath("//table[4]/tbody/tr[3]/td/table[1]/tbody/tr/td[2]"));
		File src = new File("C:\\Users\\JUNEJA\\TestData.xlsx");
		FileInputStream fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		sh=wb.createSheet();
		
		Row row = sh.createRow(0);
		for(int x=0;x<StockName.size();x++)
		{
			if(x==0)
			{
				for(int y=0;y<columns.length;y++)
				{
				Cell cell = row.createCell(y);
				cell.setCellValue(columns[y]);
				}
			}
			else
			{
			Row row1 = sh.createRow(x);
			XSSFCellStyle cs = wb.createCellStyle();
			cs.setDataFormat(wb.createDataFormat().getFormat("#.##"));
			row1.createCell(0).setCellValue(StockName.get(x).getText());
			row1.createCell(1).setCellValue(StockPrice.get(x).getText());
		 	System.out.println("YOYO " + x);
		 	}
			
		}
		sh.autoSizeColumn(0);
		sh.autoSizeColumn(1);
		FileOutputStream fout=new FileOutputStream(src);
		System.out.println("Written into TestData.xlsx");
		wb.write(fout);
		wb.close();
	}
	
	//@Test(priority=5)
	public void eachStockdetails() throws InterruptedException, IOException
	{
		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
		js= (JavascriptExecutor) driver;
		StockName =driver.findElements(By.xpath("//table[4]/tbody/tr[3]/td/table[1]/tbody/tr/td[1]/div/a"));
		StockPrice = driver.findElements(By.xpath("//table[4]/tbody/tr[3]/td/table[1]/tbody/tr/td[2]"));
		for (int a=0;a<StockName.size();a++)
		{
			System.out.println("Value of a is "+a);
			TakesScreenshot ts =(TakesScreenshot) driver;
			StockName =driver.findElements(By.xpath("//table[4]/tbody/tr[3]/td/table[1]/tbody/tr/td[1]/div/a"));
			//boolean staleElement = true; 
		   // while(staleElement){
		      try{
		    	  StockName.get(a).click();
		        	System.out.println(driver.getTitle());
					File source=ts.getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(source, new File("C:\\Users\\JUNEJA\\Screenshots\\"+a+".png"));
					 
					js.executeScript("window.history.go(-1)");
					Thread.sleep(3000);
		        

		      } catch(StaleElementReferenceException e){
		       // 
		      }
		   // }
			
			
		}
	}
	
	//@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		if(ITestResult.SUCCESS==result.getStatus())//1: Pass & 2:fail 
		{//call screenshot capture utlitlity
		captureScreenShot(driver, result.getName());
		}
	}
	
	
	public static void captureScreenShot(WebDriver driver, String TcName) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(source, new File("C:\\Users\\JUNEJA\\Screenshots\\"+TcName+".png"));
		
	}
	
	
	
	}

