package com.stgshansen.skiutah.challenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Challenge6 {

	static String homePage = "https://www.skiutah.com/";
	static PriorityQueue<String> foundOnPage = new PriorityQueue<String>();
	static String thisUrl, currentUrl = "";
	static ArrayList <String> alreadyCrawledUrls = new ArrayList <String>();
	static WebElement webEle = null;
	static HashSet <WebElement> allUrls = new HashSet<WebElement>();
	
	public static void main(String[] args) throws Exception {
	    System.setProperty("webdriver.gecko.driver", "src\\geckodriver.exe");
	    FirefoxDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		
	    //Opening SkiUtah.com
	    driver.get(homePage);
	    foundOnPage.add(homePage);
	    alreadyCrawledUrls.add(homePage);
		
	    while ((thisUrl = foundOnPage.poll()) != null) {
			System.out.println("foundOnPage is now " + foundOnPage.size() + " size");

			addUrls(wait, driver);
			foundOnPage.remove(thisUrl);
//			processUrl(wait, driver);
	    }
	    
		System.out.println("alreadyCrawledURLs has " + alreadyCrawledUrls.size() + " links in it." );
		System.out.println("alreadyCrawledURLs - " + alreadyCrawledUrls );
		System.out.println("foundOnPage - " + foundOnPage );
	    driver.close();
	 }

	private static void addUrls(WebDriverWait wait, FirefoxDriver driver) {
		driver.get(thisUrl);

		List<WebElement> allUrls = driver.findElements(By.tagName("a"));
		System.out.println("there are a total of " +allUrls.size() +" on " + thisUrl);
		for (int i = 1; i < allUrls.size(); i++){
			webEle = allUrls.get(i);
			currentUrl = webEle.getAttribute("href");  //https://stackoverflow.com/questions/20579007/get-href-value-webdriver

			if ((currentUrl!=null) && currentUrl.contains("https://www.skiutah.com/") && !alreadyCrawledUrls.contains(currentUrl)){
			if (!foundOnPage.contains(currentUrl)) {
				foundOnPage.add(currentUrl);
			}
			// once this for loop is done, you are back in the while loop and the next URL in the queue is polled.  
		}
		}
		alreadyCrawledUrls.add(thisUrl);
		foundOnPage.removeAll(alreadyCrawledUrls);

	
	}
}

/*	private static void processUrl(WebDriverWait wait, FirefoxDriver driver) {
		
//		System.out.println("foundOnPage size is now ********** " + foundOnPage.size());
		thisUrl = foundOnPage.poll();
		System.out.println("Checking this Url - " + thisUrl);
		
		if (alreadyCrawledUrls.contains(thisUrl)) {
			foundOnPage.remove(thisUrl);
			processUrl(wait,driver);
		}
			
		if(!alreadyCrawledUrls.contains(thisUrl)) {
			driver.get(thisUrl);
//			System.out.println("Should be on this Url - " + thisUrl);
//			alreadyCrawledUrls.add(thisUrl);
//			System.out.println("Already Crawled Urls size " + alreadyCrawledUrls.size() );
			addUrls(wait,driver);
			foundOnPage.remove(thisUrl);
//			System.out.println("foundOnPage has " + foundOnPage.size() + " links in it." );
			
//			System.out.println("I just added - " + thisUrl + " to alreadyCrawledURLs");
		}
		return;
		}
	}
*/
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

I didn't want to loose this actions part.

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
	

/** AUTOMATION CHALLENGE 6 (CRAWLER):
Write a crawler that will automatically navigate to every page on the site.

1. Go to https://www.skiutah.com/

2. Build a crawler that will start at www.skiutah.com and finds every link/page and goes to that 
page and finds other pages it needs to visit.  Remember to not visit the same page twice and to only visit the pages on the domain.
**/
