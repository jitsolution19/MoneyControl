package scenario;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserLaunchTest {

//	@Test
	public void launch_chrome()
	{
		System.out.println("Launch Chrome Browser");
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		WebDriver driver= new ChromeDriver(option);		
		driver.get("https://github.com/bonigarcia/webdrivermanager/blob/master/README.md");
	}
	
	@Test
	public void launch_firefox()
	{
		System.out.println("Launch Firefox Browser");
		WebDriverManager.firefoxdriver().setup();
		WebDriver driver = new FirefoxDriver();
//		driver.get("https://timesofindia.indiatimes.com/");
		driver.get("https://economictimes.indiatimes.com/");
		
		List<WebElement> newslink = driver.findElements(By.tagName("a"));
		Iterator<WebElement> counter = newslink.iterator();
		while(counter.hasNext())
		{
			WebElement items = counter.next();
			if(items.isDisplayed()) {
				System.out.println(items.getText());
			}
		}
	}
	
	
	
	
}
