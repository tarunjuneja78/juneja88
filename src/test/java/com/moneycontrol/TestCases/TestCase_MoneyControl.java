package com.moneycontrol.TestCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.moneycontrol.Pages.CheckWatchList;
import com.moneycontrol.Pages.LoginPage;

public class TestCase_MoneyControl {
	@Test(priority=1)	
	public void websiteLogin() throws IOException
	{
	LoginPage lp = new LoginPage();
	lp.siteLogin();
	}
	@Test(priority=2)
	public void getStockMyWatchList()
	{
		CheckWatchList cwl = new CheckWatchList();
		cwl.watchList();
	}
	
	@Test(priority=3)
	public void writingInToExcel() throws IOException 
	
	{
		CheckWatchList cwl = new CheckWatchList();
	    cwl.writeInToExcel();	
	}
	
	//@Test(priority=4)
	public void stocksScreenshots() throws IOException, InterruptedException 
	
	{
		CheckWatchList cwl = new CheckWatchList();
	    cwl.eachStockdetails();	
	}

}
