package com.stgshansen.skiutah.challenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Challenge7 {

	static String homePage = "https://www.skiutah.com/";
	static PriorityQueue<String> foundOnPage = new PriorityQueue<String>();
	static String thisUrl, currentUrl, allText = "";
	static ArrayList <String> alreadyCrawledUrls = new ArrayList <String>();
	static WebElement webEle, pageBody = null;
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
			System.out.println("The foundOnPage queue has " + foundOnPage.size() + " links to check.");

			addUrls(wait, driver);
			foundOnPage.remove(thisUrl);
	    }
	    
		System.out.println("alreadyCrawledURLs has " + alreadyCrawledUrls.size() + " total links in it." );
	    driver.close();
	 }

	private static void addUrls(WebDriverWait wait, FirefoxDriver driver) {
		driver.get(thisUrl);

		List<WebElement> allUrls = driver.findElements(By.tagName("a"));
		System.out.println("There are " +allUrls.size() +" links on " + thisUrl);
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
		processText(wait, driver);
		System.out.println("Have crawled  " + alreadyCrawledUrls.size() + " pages so far");

	
	}


private static void processText(WebDriverWait wait, FirefoxDriver driver) {
		
//		Get the text from this page. Check for each unique word and add to word list.
//		Send the completed list or document to Matt at the end.
	
		System.out.println("Collecting words from this Url - " + thisUrl);
		pageBody = driver.findElementByTagName("body");
		allText = pageBody.getText();
		System.out.println("The words on this page - " + allText); // change to only getting unique words on page.
		
			

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
	

/** AUTOMATION CHALLENGE 7 (CRAWLER AND GET TEXT):
Extension of last project.  Use the crawler and catalog every word used and the number of instance each word occurs.

1. Go to https://www.skiutah.com/

2. Use the crawler written in the past challenges and write a method to keep track of all the words on each page on the site
 and return to me a list or dictionary of words that exists on the site.  Also send me the list or dictionary in a text document.
**/
