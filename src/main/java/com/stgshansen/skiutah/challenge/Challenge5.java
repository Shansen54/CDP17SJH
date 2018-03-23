package com.stgshansen.skiutah.challenge;

import org.openqa.selenium.firefox.FirefoxDriver;

public class Challenge5 {

	public static void main(String[] args) {

	
	    System.setProperty("webdriver.gecko.driver", "C:/Dev/Tools/geckodriver.exe");
	    FirefoxDriver driver = new FirefoxDriver();
//		Opening SkiUtah.com
	    driver.get("https://www.skiutah.com/members/listing");
/**		
		//		setting the menu items to click on.
	    final By TOPNAV = By.xpath("//a[contains(.,'Plan Your Trip')]");
	    final By SUBNAV = By.linkText("Compare Resorts");
	    
//		Wait for page to get totally loaded.
        try {
//		Find the Plan Your Trip menu item.
        WebElement hoverMenuItem = driver.findElement(TOPNAV);
        	System.out.println("Trying to click on Plan Your Trip");
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
//		Hover over Plan Your Trip                
                action.moveToElement(hoverMenuItem).perform();
                try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//		Submenu should be visible, so click on Compare Resorts menu item.
                WebElement subElement = driver.findElement(SUBNAV);
                subElement.click();
	    		System.out.println("Clicked to go to Compare Resorts page");

        try {
			Thread.sleep(4000);
//	   		WebElement resort = driver.findElementByPartialLinkText(skiPlace);
//	  		System.out.println((resort.getText()));
//	   		WebElement miles = resort.findElement(By.className("ResortComparison-value"));
//	   		System.out.println("The resort " + (skiPlace) + " is " + (miles.getText()) +" miles from the closest airport." );
	 		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 
  		
        }
        finally{;
   		//		Close the browser and test.
	    driver.close();
 **/      
	}
	
	}	
	


/** AUTOMATION CHALLENGE 5 (USE SEARCH AND GET INFO):
1. Go to https://www.skiutah.com/members/listing

2. Write a method that will allow me to pass in 3 string parameters (What, By Resort, Sub Category)

3. Return the list of the search results by looking ListingResults-item
**/



