package com.stgshansen.skiutah.challenge;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Challenge6 {

	static String homePage = "https://www.skiutah.com/";
	static PriorityQueue<String> foundOnPage = new PriorityQueue<String>();
	static String thisUrl = "";
	static ArrayList <String> alreadyCrawledUrls = new ArrayList <String>();
	static WebElement webEle = null;
	static List<WebElement> webEles;
	static LinkedHashSet<WebElement> SortedList = new LinkedHashSet<WebElement>();
    static int index;
	
	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) throws Exception {
	    System.setProperty("webdriver.gecko.driver", "src\\geckodriver.exe");
	    FirefoxDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
	    //Opening SkiUtah.com
	    driver.get(homePage);
	    foundOnPage.add(homePage);
 
	    while (foundOnPage != null) {
	    	webEles = driver.findElements(By.tagName("a"));
	    	webEle = webEles.get(index);
			for(WebElement webEle : webEles) { SortedList.add(webEle);	}
	    	webEles.removeAll(webEles);
	    	webEles.addAll(SortedList);
	    	webEles.remove(alreadyCrawledUrls);
	    	
	    	System.out.println("Total Links found on this crawled page -------------------------------------- " + webEles.size());
			for(WebElement webEle : webEles) {	
				if(webEle.getAttribute("href").contains("https://www.skiutah.com") && webEle.isDisplayed()) {
					thisUrl = webEle.getAttribute("href");
					System.out.println("Adding " + thisUrl + " to foundOnPage");
					foundOnPage.add(thisUrl);
					System.out.println("foundOnPage is now " + foundOnPage.size() + " size");
				}
 	    }
	    	goToUrl(alreadyCrawledUrls, foundOnPage, webEle, wait, driver);
	    }
	    driver.close();
	}

	private static void goToUrl(ArrayList<String> alreadyCrawledUrls, PriorityQueue<String> foundOnPage, 
			WebElement webEle, WebDriverWait wait, FirefoxDriver driver) {
//		for(WebElement webEle : driver.findElements(By.tagName("a"))) {	
//			wait.until(driver.findElementsByTagName() isTrue)
			System.out.println("I am now going to this page - " + webEle.getAttribute("href"));
//			if(webEle.getAttribute("href").contains("https://www.skiutah.com") && webEle.isDisplayed()) {
				Actions action = new Actions(driver);
				action.moveToElement(webEle).build().perform();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			
//			if(!alreadyCrawledUrls.contains(webEle.getText()) && webEle.isDisplayed()){
//				if(webEle.getAttribute("href").contains("https://www.skiutah.com")) {
					alreadyCrawledUrls.add(webEle.getAttribute("href"));
					System.out.println("I just added - " + webEle.getAttribute("href") + " to alreadyCrawledURLs");
					webEle.click();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				System.out.println("alreadyCrawledURLs has " + alreadyCrawledUrls.size() + " links in it." );
				foundOnPage.remove(thisUrl);
				System.out.println("foundOnPage has " + foundOnPage.size() + " links in it." );
				
				}
	

}

/*	The queue is all the URLs that needs to be visited.  The list has all the URLs that been visited.  

The while loop would be:
While queue != empty{
driver.findElements(find all anchor tags),
Process the list elements.
}

ProcessElements(){
for i=0, i < webelements.size(), i++{
if (!alreadyListed.containsKey(element[i].getURL){
  alreadyLIsted.put(url);
queue.add(url)  
}
}

}


/** AUTOMATION CHALLENGE 6 (CRAWLER):
Write a crawler that will automatically navigate to every page on the site.

1. Go to https://www.skiutah.com/

2. Build a crawler that will start at www.skiutah.com and finds every link/page and goes to that 
page and finds other pages it needs to visit.  Remember to not visit the same page twice and to only visit the pages on the domain.
**/
