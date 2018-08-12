package com.moneycontrol.Pages;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.moneycontrol.MPage.MasterPage;

public class CheckWatchList {
	List<WebElement> StockName = MasterPage.driver.findElements(By.xpath(MasterPage.prop.getProperty("webtable_stockname")));
	List<WebElement> StockPrice = MasterPage.driver.findElements(By.xpath(MasterPage.prop.getProperty("webtable_stockprice")));
	String[] columns = {"StackName","StockPrice"};
	JavascriptExecutor js ;
	public void watchList()
	{
		
			MasterPage.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Actions act = new Actions(MasterPage.driver);
			
			WebElement Menu = MasterPage.driver.findElement(By.cssSelector(MasterPage.prop.getProperty("menu_menu")));
			WebElement WatchList = MasterPage.driver.findElement(By.xpath(MasterPage.prop.getProperty("menu_watchlist")));
			act.moveToElement(Menu).click().build().perform();
			System.out.println("Move To Element Step Performed");
			act.moveToElement(WatchList).click().build().perform();
						
		}
		


public void writeInToExcel() throws IOException
{
	
	File src = new File("C:\\Users\\JUNEJA\\TestData.xlsx");
	FileInputStream fis = new FileInputStream(src);
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	XSSFSheet sh=wb.createSheet();
	
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


public void eachStockdetails() throws InterruptedException, IOException
{
	js= (JavascriptExecutor) MasterPage.driver;
	System.out.println("the size of stock is "+ StockName.size());
	for (int a=0;a<StockName.size();a++)
	{
		System.out.println("Value of a is "+a);
		StockName = MasterPage.driver.findElements(By.xpath(MasterPage.prop.getProperty("webtable_stockname")));
		TakesScreenshot ts =(TakesScreenshot) MasterPage.driver;
		//boolean staleElement = true; 
	   // while(staleElement){
	      try{
	    	  	StockName.get(a).click();
	    	  	System.out.println(MasterPage.driver.getTitle());
				File source=ts.getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(source, new File("C:\\Users\\JUNEJA\\Screenshots\\"+a+".png"));
				 
				js.executeScript("window.history.go(-1)");
				Thread.sleep(3000);
	        // staleElement = false;

	      } catch(StaleElementReferenceException e){
	       // staleElement = true;
	      }
	   // }
		
		
	}
}




}