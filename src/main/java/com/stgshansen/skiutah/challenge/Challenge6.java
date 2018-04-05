package com.stgshansen.skiutah.challenge;

import org.openqa.selenium.firefox.FirefoxDriver;

public class Challenge6 {

	public static void main(String[] args) {
			
	
	    System.setProperty("webdriver.gecko.driver", "C:/Dev/Tools/geckodriver.exe");
	    FirefoxDriver driver = new FirefoxDriver();
	    
//		Opening SkiUtah.com
	    driver.get("https://www.skiutah.com/");

	    // Find links on this page.
	    
	    
 
   		//		Close the browser and test.
	    driver.close();
	    
        }
	}
	



/** AUTOMATION CHALLENGE 6 (CRAWLER):
Write a crawler that will automatically navigate to every page on the site.

1. Go to https://www.skiutah.com/

2. Build a crawler that will start at www.skiutah.com and finds every link/page and goes to that 
page and finds other pages it needs to visit.  Remember to not visit the same page twice and to only visit the pages on the domain.
**/



