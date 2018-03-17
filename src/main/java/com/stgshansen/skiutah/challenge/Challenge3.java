package com.stgshansen.skiutah.challenge;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Challenge3 {

	public static void main(String[] args) {
		
	    System.setProperty("webdriver.gecko.driver", "C:/Dev/Tools/geckodriver.exe");
	    FirefoxDriver driver = new FirefoxDriver();
//		Opening SkiUtah.com
	    driver.get("https:\\www.skiutah.com");
//		setting the menu items to click on.
	    final By TOPNAV = By.xpath("//a[contains(.,'Deals')]");
	    final By SUBNAV = By.linkText("Beginner");

//		Wait for page to get totally loaded.
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		Find the Deals menu item.
        WebElement hoverMenuItem = driver.findElement(TOPNAV);
        	System.out.println("Trying to click on Deals");
                try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//		Create an Action
                Actions action = new Actions(driver);
                try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//		Hover over Deals                
                action.moveToElement(hoverMenuItem).perform();
                try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//		Submenu should be visible, so click on Beginner menu item.
                WebElement subElement = driver.findElement(SUBNAV);
                subElement.click();
	    		System.out.println("Clicked to go to Beginner page");

//		Assert that the Beginner Deals page has come up.
        driver.getTitle().contains("Beginner");
   		System.out.println("Got to the Beginner page");
//		Close the browser and test.
	    driver.close();
	}
}
