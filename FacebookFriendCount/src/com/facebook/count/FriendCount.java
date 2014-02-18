package com.facebook.count;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
public class FriendCount {
	public static void main(String[] args) throws InterruptedException {

		FirefoxProfile profile = new FirefoxProfile();
		profile.setEnableNativeEvents(true);
		WebDriver driver = new FirefoxDriver(profile);
		driver.manage().window().maximize();
		driver.get("http://facebook.com");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("<<your facebook login email_id>>");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("<<your password>>");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		
		driver.get("https://www.facebook.com/<<your facebook id>>/friends?ft_ref=mni");
		while(true){
			System.out.println("***************");
			Long y=(Long)((JavascriptExecutor) driver).executeScript("return window.pageYOffset");
		     long y1 = y.longValue();
			Thread.sleep(1000);
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,90)");
			 y=(Long)((JavascriptExecutor) driver).executeScript("return window.pageYOffset");
		     long y2 = y.longValue();
		     if(y1-y2 ==0){
		    	 System.out.println(y1);
		    	 System.out.println(y2);
		    	 break;
		     }
			
		}
		
		ArrayList<Integer> friendCount = new ArrayList<Integer>();
		List<WebElement> allNames = driver.findElements(By.xpath("//a[@class='uiLinkSubtle']"));
		System.out.println(allNames.size());
		for(int i=0;i<allNames.size();i++){
			String friendPhrase = allNames.get(i).getText();
			if(friendPhrase.contains("friends") && !friendPhrase.contains("mutual")){
				StringTokenizer st = new StringTokenizer(friendPhrase);
				friendCount.add(Integer.parseInt(((String)st.nextElement()).replace(",", "")));
			}
		}
		
		Iterator<Integer> i = friendCount.iterator();
		int sum = 0;
		while(i.hasNext()){
			sum+=(Integer)i.next();
		}
		
		System.out.println("Total Friends "+friendCount.size());
		System.out.println("Average "+((float)sum)/(float)friendCount.size());
	}
}


