package Mytests;

import static org.testng.Assert.assertTrue;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FirstTest extends BaseTest {
	@Test
	public void blogPageValidation() throws Exception {
		SoftAssert soft = new SoftAssert();
		Reporter.log(String.format("BlogPage Test Started %s", Thread.currentThread().getId()), true);
		getDriver().navigate().to("https://www.aumni.fund");
		getDriver().findElement(By.xpath("//*[@id=\"comp-kd5ryhsn2label\"]")).click();
		soft.assertTrue(getDriver().getTitle().contains("Automated inve"));
		Reporter.log(String.format("Blog Page Validation Test Ended %s", Thread.currentThread().getId()), true);
		soft.assertAll();
	}

	@Test
	public void resourcePageValidation() throws Exception {
		Reporter.log(String.format("resource Page Validation Test Started %s", Thread.currentThread().getId()), true);
		getDriver().navigate().to("https://www.aumni.fund");
		getDriver().findElement(By.xpath("//*[@id='comp-kd5ryhsn1label']")).click();
		assertTrue(getDriver().getTitle().contains("Automated investment analyt"));
		Reporter.log(String.format("resource Page Validation Test Ended %s", Thread.currentThread().getId()), true);
	}

}
