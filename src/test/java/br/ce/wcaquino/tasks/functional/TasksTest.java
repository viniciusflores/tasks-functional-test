package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	public WebDriver setupDriver() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("http://localhost:8001/tasks/");
		return driver;
	}

	@Test
	public void shouldSaveTaskWithSuccess() {
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
	public void shouldNotBeenPossibleToSaveTaskWithoutDescription() {
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
	public void shouldNotBeenPossibleToSaveTaskWithoutDate() {
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
	public void shouldNotBeenPossibleToSaveTaskWithPastDate() {
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
