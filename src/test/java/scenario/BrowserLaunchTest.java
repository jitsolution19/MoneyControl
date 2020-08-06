package scenario;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserLaunchTest {

	@Test
	public void launch_chrome() {
		System.out.println("Launch Chrome Browser");
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(option);	
		driver.get("https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		List<WebElement> newslink = driver.findElements(By.tagName("a"));
		Iterator<WebElement> counter = newslink.iterator();
		while (counter.hasNext()) {
			WebElement items = counter.next();
			if (items.isDisplayed()&&items.isEnabled()) {
				System.out.println(items.getText());
			}

		}
		driver.quit();
	}

	@Test
	public void launch_firfox() {
		System.out.println("Launch firefox Browser");
		WebDriverManager.firefoxdriver().arch64().setup();
		FirefoxOptions options= new FirefoxOptions();
//		options.addArguments("headless");
		WebDriver driver = new FirefoxDriver(options);
//		driver.get("https://github.com/bonigarcia/webdrivermanager/blob/master/README.md");
		driver.get("https://economictimes.indiatimes.com/");
//		driver.get("https://www.moneycontrol.com/");

		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		int attempt = 0;
		while (attempt < 3) {
			try {
				List<WebElement> newslink = driver.findElements(By.tagName("a"));
				Iterator<WebElement> counter = newslink.iterator();
				while (counter.hasNext()) {
					WebElement items = counter.next();
					if (items.isDisplayed()&&items.isEnabled()) {
						System.out.println(items.getText());
					}

				}
			} catch (StaleElementReferenceException e) {

			}
			attempt++;
		}
System.out.println("Execution complete");
		driver.quit();
	}

}
