package com.stgshansen.skiutah.challenge;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class Challenge4 {

	public static void main(String[] args) {

	
	    System.setProperty("webdriver.gecko.driver", "C:/Dev/Tools/geckodriver.exe");
	    FirefoxDriver driver = new FirefoxDriver();
//		Opening SkiUtah.com
	    driver.get("https:\\www.skiutah.com");
		
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

//		Assert that the Beginner Deals page has come up.
        driver.getTitle().contains("Compare");
   		System.out.println("Got to the Compare Resorts page");

   		WebElement compareResorts = driver.findElementByClassName("js-resort-comparison-select");
   		System.out.println("Got Activation of the Selection");
   		compareResorts.sendKeys(Keys.ARROW_DOWN);
   		compareResorts.click();
   		compareResorts.sendKeys(Keys.ENTER);
   		System.out.println("Clicked Miles to Closest Major Airport");

   		String skiPlace = "Alta Ski Area";
        try {
			Thread.sleep(4000);
	   		WebElement resort = driver.findElementByPartialLinkText(skiPlace);
	  		System.out.println((resort.getText()));
	   		WebElement miles = resort.findElement(By.className("ResortComparison-value"));
	   		System.out.println("The resort " + (skiPlace) + " is " + (miles.getText()) +" miles from the closest airport." );
	 		} catch (InterruptedException e) {
			e.printStackTrace();
		}
 
  		
        }
        finally{;
   		//		Close the browser and test.
	    driver.close();
       
	}
	}
}	
	


/** AUTOMATION CHALLENGE 4 (GET INFO):
Write a method to return the time each ski resort is from the Airport.

1. Go to https://www.skiutah.com/

2. Click on the link that is on the home page to compare resorts.

3. The method should be able to return a time based on the name of the ski resort that I want to pass in.  You can return the value either w/ a string or int.

@test
public void automation1(){
    System.out.println(timeFromAirport(airportName))  
}
**/



