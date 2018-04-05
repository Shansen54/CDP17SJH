package com.stgshansen.skiutah.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Challenge5 {

	public static void main(String[] args) {
			
		String WhatToSearchFor = "Transportation";
		String SubCategory = "Group Shuttles";
		String ByResort = "Nordic Valley";
	
	    System.setProperty("webdriver.gecko.driver", "C:/Dev/Tools/geckodriver.exe");
	    FirefoxDriver driver = new FirefoxDriver();
	    
//		Opening SkiUtah.com
	    driver.get("https://www.skiutah.com/members/listing");

	    // Inputting the first option of what to search for.
	    Select drpSearchForWhat = new Select(driver.findElementByName("filter-category-select"));
// 		System.out.println("Got Activation of the Search for What input field");

        try {
			Thread.sleep(1000);
	  		System.out.println("Searching for - " + WhatToSearchFor);
        	drpSearchForWhat.selectByVisibleText(WhatToSearchFor);
	        } catch (InterruptedException e) {
				e.printStackTrace();
			}
//		Inputting the sub category.
	    Select drpSearchForSubCat = new Select(driver.findElementByName("filter-sub-category-select"));
// 		System.out.println("Got Activation of the Search for Sub Category input field");

        try {
			Thread.sleep(1000);
	  		System.out.println("Using Sub Category - " + SubCategory);
        	drpSearchForSubCat.selectByVisibleText(SubCategory);
	        } catch (InterruptedException e) {
				e.printStackTrace();
			}
//		Inputting by resort.
	    Select drpSearchByResort = new Select(driver.findElementByName("filter-resort-select"));
// 		System.out.println("Got Activation of the Search By Resort input field");

        try {
			Thread.sleep(1000);
	  		System.out.println("And Filtering by this resort: " + ByResort);
        	drpSearchByResort.selectByVisibleText(ByResort);
	        } catch (InterruptedException e) {
				e.printStackTrace();
			}
        
//		Clicking the OK submit button.
        WebElement okButton = driver.findElementByName("filter-form-submit");
        okButton.click();
         
//		Listing of results.
        try {
			Thread.sleep(2000);
			WebElement ListResults = driver.findElementByCssSelector(".ListingResults-count>h2");
			String ListOutput = ListResults.getText();
//			System.out.println(ListOutput);
			String fileName = "SearchResponses.csv";
			File file = new File(fileName);
			try {
				Scanner inputStream = new Scanner(file);
				inputStream.useDelimiter(",");
				while (inputStream.hasNext()) {
					String data = inputStream.nextLine();
					String[] fields = data.split(",");
					if (fields[1].contains(ListOutput) & fields[0].contains(SubCategory)) {
						System.out.println("You selected " + WhatToSearchFor + " along with " 
					+ SubCategory + " and "+ ByResort +  " and got " +  ListOutput);
					}
				}
				inputStream.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
//			System.out.println("The first " + ListOutput + " are shown below");
	        } catch (InterruptedException e) {
				e.printStackTrace();
			}
        
   		//		Close the browser and test.
	    driver.close();
	    
        }
	}
	



/** AUTOMATION CHALLENGE 5 (USE SEARCH AND GET INFO):
1. Go to https://www.skiutah.com/members/listing

2. Write a method that will allow me to pass in 3 string parameters (What, By Resort, Sub Category)

3. Return the list of the search results by looking ListingResults-item
**/



