package com.moneycontrol.Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.moneycontrol.MPage.MasterPage;

public class LoginPage extends MasterPage {
	
	public LoginPage() throws IOException {
		super();
		}


	
	public void siteLogin()
	{
		List<WebElement> allFrames = driver.findElements(By.tagName(prop.getProperty("frame_tag")));
		System.out.println("The size of all frames is "+allFrames.size());
			driver.switchTo().frame("login_frame");
			
				
				click("txtbox_uname");
				enterTextValue("txtbox_uname","txtbox_email");
				click("txtbox_passwd");
				enterTextValue("txtbox_passwd","txtbox_password");
				click("btn_login");
				
			
			driver.switchTo().defaultContent();
			
			
		}
	

}
