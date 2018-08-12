package com.moneycontrol.MPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MasterPage {
	public static WebDriver driver;
	public static Properties prop; 
	public MasterPage() throws IOException
	{
		File src = new File("C:\\Users\\JUNEJA\\eclipse-workspace\\ZERODHA\\src\\test\\java\\com\\moneycontrol\\Utilities\\OR.properties");
		FileInputStream fis = new FileInputStream(src);
		prop= new Properties();
		prop.load(fis);
		String browser = prop.getProperty("browser");
		if (browser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Users\\JUNEJA\\eclipse-workspace\\ZERODHA\\src\\test\\java\\com\\moneycontrol\\Driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://m.moneycontrol.com/login.php");
		}
	}
	
	public void click(String linkToClick)
	{
		driver.findElement(By.xpath(prop.getProperty(linkToClick))).click();
	}
	
	public void enterTextValue(String pathToFill, String valueToFill)
	{
		driver.findElement(By.xpath(prop.getProperty(pathToFill))).sendKeys(prop.getProperty(valueToFill));
	}
	
}
