package com.stgshansen.skiutah.challenge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Challenge1 {

	public static void main(String[] args) {
		
	
		 System.setProperty("webdriver.gecko.driver", "C:/Dev/Tools/geckodriver.exe");
		 WebDriver driver = new FirefoxDriver();

		 driver.get("https:\\www.skiutah.com");
		 driver.getTitle().contains("Ski Utah");

		 driver.close();
	}
}
