package br.ce.wcaquino.tasks.prod;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HealthCheckIT {
	@Test
	public void healthCheck() throws MalformedURLException {
		WebDriver driver = new ChromeDriver();
//		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//		WebDriver driver = new RemoteWebDriver(new URL("http://10.0.2.15:4444/wd/hub"), capabilities);
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.navigate().to("http://10.0.2.15:9999/tasks/");
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));

		} finally {
			driver.quit();

		}
	}
}
