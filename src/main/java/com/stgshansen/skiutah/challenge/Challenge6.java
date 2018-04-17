package com.stgshansen.skiutah.challenge;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Challenge6 {

	public static void main(String[] args) throws Exception {

		String url ="";
		String homePage = "https://www.skiutah.com/";
		String xPathLinks = "//a[contains(@href,'https://www.skiutah.com')]";
		List <WebElement> linksOnPage;
		ArrayList <String> foundOnPage = new ArrayList <String>();
		HttpURLConnection huc = null;
        int respCode = 200;
			
	    System.setProperty("webdriver.gecko.driver", "C:/Dev/Tools/geckodriver.exe");
	    FirefoxDriver driver = new FirefoxDriver();
	    
	    //Opening SkiUtah.com
	    driver.get(homePage);

	    // Find links on this page.
		linksOnPage = driver.findElementsByXPath(xPathLinks);	//Ensures the link has the domain in it.  Adds to linksOnPage
		
		Iterator <WebElement> it = linksOnPage.iterator();
		while(it.hasNext()) {
			url = it.next().getAttribute("href");
	           try {
	                huc = (HttpURLConnection)(new URL(url).openConnection());
	                huc.setRequestMethod("HEAD");
	                huc.connect();
	                respCode = huc.getResponseCode();
	                if(respCode >= 400){
	                    System.out.println(url+" is a broken link");
	                }
	                else{
	                    System.out.println(url+" is a valid link");
	                    foundOnPage.add(url);
	                }
	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		
			
		foundOnPage.sort(null);
        System.out.println(foundOnPage.size());
        LinkedHashSet <String> lhs = new LinkedHashSet<String>();
        lhs.addAll(foundOnPage);
        foundOnPage.clear();
        foundOnPage.addAll(lhs);
        System.out.println(foundOnPage.size());
// At this point, I have all the valid links on the homepage once each.  I now need to save the list and start going through each one in turn.
// Will put it here.
		
	    driver.close();
	    
	}
}

/** AUTOMATION CHALLENGE 6 (CRAWLER):
Write a crawler that will automatically navigate to every page on the site.

1. Go to https://www.skiutah.com/

2. Build a crawler that will start at www.skiutah.com and finds every link/page and goes to that 
page and finds other pages it needs to visit.  Remember to not visit the same page twice and to only visit the pages on the domain.
**/
