package com.stgshansen.skiutah.challenge;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Challenge2 {


	public static void main(String[] args) {

		 System.setProperty("webdriver.gecko.driver", "C:/Dev/Tools/geckodriver.exe");
	        FirefoxDriver driver = new FirefoxDriver();
	        driver.navigate().to("https:\\www.skiutah.com");
	        driver.findElement(By.linkText("Deals")).click();
	        driver.getCurrentUrl().contains("deals");
//	        assert.containsString("Utah Ski Trip Deals") != null//("Title will have Utah Ski Trip Deals in it", (driver.getTitle().contains("Utah Ski Trip Deals")));
//			Verify(driver.getTitle().contains("Ski Utah"));
        	System.out.println("Clicked on Deals");
	        driver.close();
	}
}
