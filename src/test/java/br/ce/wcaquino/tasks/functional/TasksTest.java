package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TasksTest {
	public WebDriver setupDriver() throws Exception {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		WebDriver driver = new ChromeDriver(options);
		// DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		// WebDriver driver = new RemoteWebDriver(new URL("http://10.0.2.15:4444/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("http://10.0.2.15:8001/tasks/");
		return driver;
	}

	@Test
	public void shouldSaveTaskWithSuccess() throws Exception {
		WebDriver driver = setupDriver();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("task description");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			driver.findElement(By.id("saveButton")).click();

			Assert.assertEquals("Success!", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}
	}

	@Test
	public void shouldNotBeenPossibleToSaveTaskWithoutDescription() throws Exception {
		WebDriver driver = setupDriver();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			driver.findElement(By.id("saveButton")).click();

			Assert.assertEquals("Fill the task description", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}
	}

	@Test
	public void shouldNotBeenPossibleToSaveTaskWithoutDate() throws Exception {
		WebDriver driver = setupDriver();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("task description");
			driver.findElement(By.id("saveButton")).click();

			Assert.assertEquals("Fill the due date", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}
	}

	@Test
	public void shouldNotBeenPossibleToSaveTaskWithPastDate() throws Exception {
		WebDriver driver = setupDriver();
		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("task description");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			driver.findElement(By.id("saveButton")).click();

			Assert.assertEquals("Due date must not be in past", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}
	}
}
