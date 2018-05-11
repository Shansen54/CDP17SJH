package com.stgshansen.skiutah.challenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Challenge6 {

	static String homePage = "https://www.skiutah.com/";
	static PriorityQueue<String> foundOnPage = new PriorityQueue<String>();
	static String thisUrl, currentUrl = "";
	static ArrayList <String> alreadyCrawledUrls = new ArrayList <String>();
	static WebElement webEle = null;
	static HashSet <WebElement> webEles;
	
	public static void main(String[] args) throws Exception {
	    System.setProperty("webdriver.gecko.driver", "src\\geckodriver.exe");
	    FirefoxDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		
	    //Opening SkiUtah.com
	    driver.get(homePage);
	    foundOnPage.add(homePage);
		
	    while ((thisUrl = foundOnPage.poll()) != null) {
			System.out.println("foundOnPage is now " + foundOnPage.size() + " size");
			addUrls(wait, driver);
			processUrl(wait, driver);
	    }
	    
		System.out.println("alreadyCrawledURLs has " + alreadyCrawledUrls.size() + " links in it." );
	    driver.close();
	 }

	private static void addUrls(WebDriverWait wait, FirefoxDriver driver) {
  
		List<WebElement> allUrls = driver.findElements(By.tagName("a"));
		System.out.println("there are a total of " +allUrls.size() +" on this page ");
		for (int i = 1; i < allUrls.size(); i++){
			webEle = allUrls.get(i);
			currentUrl = webEle.getAttribute("href");  //https://stackoverflow.com/questions/20579007/get-href-value-webdriver

		if (!alreadyCrawledUrls.contains(currentUrl)){
			 alreadyCrawledUrls.add(currentUrl);
			foundOnPage.add(currentUrl);
			// once this for loop is done, you are back in the while loop and the next URL in the queue is polled.  
		}
		}

	}
	

	private static void processUrl(WebDriverWait wait, FirefoxDriver driver) {

		System.out.println("foundOnPage size is now ********** " + foundOnPage.size());
		System.out.println("foundOnPage - " + foundOnPage);
		thisUrl = foundOnPage.peek();
		if (!thisUrl.contains(homePage)) {
			thisUrl = foundOnPage.poll();
			processUrl(wait,driver);
			}
		if(thisUrl.contains("https://www.skiutah.com") && !alreadyCrawledUrls.contains(thisUrl)) {
			driver.get(thisUrl);
			System.out.println("Checking this Url - " + thisUrl);
			addUrls(wait,driver);
		
//				Actions action = new Actions(driver);
//				action.moveToElement(webEle).build().perform();
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}

//				webEle.click();
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e1) {
//						if(driver.switchTo().alert() != null)
//						{
//						    Alert alert = driver.switchTo().alert();
//						    alert.dismiss(); // alert.accept();
//						}
	
//						e1.printStackTrace();
//						}
				
				alreadyCrawledUrls.add(thisUrl);
				System.out.println("I just added - " + thisUrl + " to alreadyCrawledURLs");
				foundOnPage.remove(thisUrl);
				System.out.println("foundOnPage has " + foundOnPage.size() + " links in it." );
		}
		return;
		}
	}

/*	The queue is all the URLs that needs to be visited.  The list has all the URLs that been visited.  

public void main(){
priorityqueue.add(homePage);
while ((currentpage = queue.poll()) != null){
processAllUrls found on page(currentpage);
}
once you exit out of the while loop, you can do a:
alreadyCrawledUrls.length for a count of all the URLs.
}

processAllUrls(currentpage){
driver.open(currentpage)
allUrls = driver.findElements(By.tagName("a"));
system.println("there are a total of " +allUrls+" on this page")  
for (i=1; i<allUrls; i++){
href=allUrls[i].getAttributes(href);  //https://stackoverflow.com/questions/20579007/get-href-value-webdriver
if (!alreadyCrawledUrls.containsKey(href)){
 alreadyCrawledUrls.put(href);
priorityqueue.add(href);
// once this for loop is done, you are back in the while loop and the next URL in the queue is polled.  
}
}
}


/** AUTOMATION CHALLENGE 6 (CRAWLER):
Write a crawler that will automatically navigate to every page on the site.

1. Go to https://www.skiutah.com/

2. Build a crawler that will start at www.skiutah.com and finds every link/page and goes to that 
page and finds other pages it needs to visit.  Remember to not visit the same page twice and to only visit the pages on the domain.
**/
