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
import org.openqa.selenium.remote.RemoteWebDriver;

public class Challenge6 {

	static String crawlingThisPage = "https://www.skiutah.com/";
	
	static String xPathLinks = "//a[contains(@href, crawlingThisPage )]";
//	private static final String xPathLinks = "//a[contains(@href, crawlingThisPage )]";
	
	public static void main(String[] args) throws Exception {
//		String crawlingThisPage = "https://www.skiutah.com/";
	    System.setProperty("webdriver.gecko.driver", "C:/Dev/Tools/geckodriver.exe");
	    FirefoxDriver driver = new FirefoxDriver();
	    //Opening SkiUtah.com
	    driver.get(crawlingThisPage);

		String url ="";
//		String homePage = "https://www.skiutah.com/";
		List <WebElement> linksOnPage = null;
		ArrayList <String> foundOnPage = new ArrayList <String>();
		ArrayList <String> allValidUrls = new ArrayList <String>();
		ArrayList <String> alreadyCrawledUrls = new ArrayList <String>();
		ArrayList <String> leftToCrawlUrls = new ArrayList <String>();
		HttpURLConnection huc = null;
        int respCode = 200;
	    
	    findUrls(crawlingThisPage, url, xPathLinks, linksOnPage, foundOnPage, allValidUrls, alreadyCrawledUrls, 
	    		leftToCrawlUrls, huc, respCode, driver);
	    
	    while (leftToCrawlUrls != null) {
	    		findUrls(crawlingThisPage, url, xPathLinks, linksOnPage, foundOnPage, allValidUrls, alreadyCrawledUrls, 
	    		leftToCrawlUrls, huc, respCode, driver);
	    		
	    }
	    
	    
	    driver.close();

	}

	@SuppressWarnings("unlikely-arg-type")
	public static void findUrls(String crawlingThisPage, String url, String homePage, List<WebElement> linksOnPage, ArrayList<String> foundOnPage, 
			ArrayList<String> allValidUrls, ArrayList<String> alreadyCrawledUrls,
			ArrayList<String> leftToCrawlUrls, HttpURLConnection huc, int respCode, RemoteWebDriver driver) {

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
//	                    System.out.println(url+" is a valid link");
	                    foundOnPage.add(url);
	                }
	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		
			
		foundOnPage.sort(null);  // Sorts the valid Urls found on this page.
        System.out.println("Total urls found on this page " + foundOnPage.size()); // Prints the total number of valid links found on this page.
        LinkedHashSet <String> lhs = new LinkedHashSet<String>(); // Create the Linked Hash Set
        lhs.addAll(foundOnPage);  // Adds all the links into the hash.  The duplicates are stripped as they come in.
        foundOnPage.clear();  // Clears the ArrayList.
        foundOnPage.addAll(lhs);  //  Adds all the hash entries back into the array.
        System.out.println("Total urls with duplicates removed " + foundOnPage.size());  //  Prints the total number of unique valid with no duplicates
        alreadyCrawledUrls.add(crawlingThisPage); // Adds the currently crawled page to the Array of crawled URLs.
        System.out.println("This URL was crawled " + crawlingThisPage);  // Prints out the name of the crawled page.
        System.out.println("Running total of Crawled sites " + alreadyCrawledUrls);  // Prints out the name of the crawled page.
        allValidUrls.addAll(lhs);  // All valid urls get added to this list.
        System.out.println("Running Total of All URLs on Domain " + allValidUrls); // This prints out the list of all found URLs so far on the domain. 
        System.out.println("Running Size of All URLs on Domain " + allValidUrls.size()); // This prints out the size of all found URLs so far on the domain. 
        lhs.remove(alreadyCrawledUrls);
        System.out.println("This many URLs are in lhs after already crawled is taken out " + lhs.size());
        System.out.println("Running Total of All Crawled Urls " + alreadyCrawledUrls); // This prints out the list of all crawled URLs so far on the domain. 
        leftToCrawlUrls.addAll(lhs);  // The left to be crawled list is created or added to.
        leftToCrawlUrls.sort(null);
        lhs.clear();
        lhs.addAll(leftToCrawlUrls);
        lhs.remove(alreadyCrawledUrls);
        leftToCrawlUrls.clear();
        leftToCrawlUrls.addAll(lhs);  // The left to be crawled list is created or added to.
        System.out.println("This many URLs are in the queue to be crawled " +leftToCrawlUrls.size());
        System.out.println("Running Total of All URLs on Domain after the last was removed. " + leftToCrawlUrls); // This prints out the list of all found URLs so far on the domain.
        System.out.println("Next URL to crawl - " + leftToCrawlUrls.get(0)); // This prints out the list of all found URLs so far on the domain.
        if ( crawlingThisPage == leftToCrawlUrls.get(0)) 
        	crawlingThisPage = leftToCrawlUrls.get(1);
        leftToCrawlUrls.remove(0);
        System.out.println("Next URL to crawl - " + leftToCrawlUrls.get(0)); // This prints out the next URL crawled.
        leftToCrawlUrls.remove(0);
        
	}
	

}

/** AUTOMATION CHALLENGE 6 (CRAWLER):
Write a crawler that will automatically navigate to every page on the site.

1. Go to https://www.skiutah.com/

2. Build a crawler that will start at www.skiutah.com and finds every link/page and goes to that 
page and finds other pages it needs to visit.  Remember to not visit the same page twice and to only visit the pages on the domain.
**/
