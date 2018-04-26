package com.stgshansen.skiutah.challenge;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Challenge6 {

	static String crawlingThisPage = "https://www.skiutah.com/"; // this is good and works.
	static String homePage = "'https://www.skiutah.com/'";
	static String xPathLinks = "//a[contains(@href, "+ homePage +")]";
	static String url ="";
	static PriorityQueue <WebElement> linksOnPage = new PriorityQueue<WebElement>();
//	static Queue <WebElement> linksOnPage = null;
//	static List <WebElement> linksOnPage = null;
	static PriorityQueue <String> foundOnPage = new PriorityQueue<String>();
	static PriorityQueue <String> allValidUrls = new PriorityQueue<String>();
	static PriorityQueue <String> alreadyCrawledUrls = new PriorityQueue<String>();
	static PriorityQueue <String> leftToCrawlUrls = new PriorityQueue<String>();
	static HttpURLConnection huc = null;
    static int respCode = 200;
	
	public static void main(String[] args) throws Exception {
	    System.setProperty("webdriver.gecko.driver", "C:/Dev/Tools/geckodriver.exe");
	    FirefoxDriver driver = new FirefoxDriver();
	    //Opening SkiUtah.com
	    driver.get(crawlingThisPage);
	    
	    findUrls(crawlingThisPage, url, xPathLinks, linksOnPage, foundOnPage,
	    		allValidUrls, alreadyCrawledUrls, leftToCrawlUrls, huc, respCode, driver);
	    
	    while (leftToCrawlUrls != null) {
	    		findUrls(crawlingThisPage, url, xPathLinks, linksOnPage, foundOnPage, allValidUrls, alreadyCrawledUrls, 
	    		leftToCrawlUrls, huc, respCode, driver);
	    }
	    driver.close();
	}

	@SuppressWarnings({ "unlikely-arg-type", "unchecked" })
	private static void findUrls(String crawlingThisPage, String url, String xPathLinks,
			PriorityQueue<WebElement> linksOnPage, PriorityQueue<String> foundOnPage,
			PriorityQueue<String> allValidUrls, PriorityQueue<String> alreadyCrawledUrls,
			PriorityQueue<String> leftToCrawlUrls, HttpURLConnection huc, int respCode, FirefoxDriver driver) {
		

		// Find links on this page.
		PriorityQueue <WebElement> linksOnPage1 = (PriorityQueue<WebElement>) driver.findElementsByXPath(xPathLinks);	//Ensures the link has the domain in it.  Adds to linksOnPage
        System.out.println(xPathLinks +" is being crawled.");
		
		Iterator <WebElement> it = linksOnPage1.iterator();
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
		
			
		//(PriorityQueue<String> foundOnPage).sort(null);  // Sorts the valid Urls found on this page.
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
        //leftToCrawlUrls.sort(null);
        lhs.clear();
        lhs.addAll(leftToCrawlUrls);
        lhs.remove(alreadyCrawledUrls);
        leftToCrawlUrls.clear();
        leftToCrawlUrls.addAll(lhs);  // The left to be crawled list is created or added to.
        System.out.println("This many URLs are in the queue to be crawled " +leftToCrawlUrls.size());
        System.out.println("Running Total of All URLs on Domain after the last was removed. " + leftToCrawlUrls); // This prints out the list of all found URLs so far on the domain.
        System.out.println("Next URL to crawl - " + leftToCrawlUrls.poll()); // This prints out the list of all found URLs so far on the domain.
        leftToCrawlUrls.remove(0);
         
	}
	

}

/** AUTOMATION CHALLENGE 6 (CRAWLER):
Write a crawler that will automatically navigate to every page on the site.

1. Go to https://www.skiutah.com/

2. Build a crawler that will start at www.skiutah.com and finds every link/page and goes to that 
page and finds other pages it needs to visit.  Remember to not visit the same page twice and to only visit the pages on the domain.
**/
