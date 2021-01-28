package Mytests;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SecondTest extends BaseTest {

	@Test(enabled=false)
	public void verifyPartnersLogo() throws Exception {
		Reporter.log(String.format("verify Partners Logo Test Started %s", Thread.currentThread().getId()), true);
		getDriver().navigate().to("https://www.aumni.fund/");
		Thread.sleep(3000);
		List<WebElement> Images = getDriver().findElements(By.tagName("img"));

		for (WebElement image : Images) {

			Boolean ImagePresent = (Boolean) ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					image);
			if (!ImagePresent) {
				System.out.println(image.getAttribute("alt") + " Image not displayed.");
			} else {
				System.out.println("Image displayed.");
			}
		}

		Reporter.log(String.format("verify Partners Logo Test Ended %s", Thread.currentThread().getId()), true);
	}

	@Test
	public void brokenLinksValidation() throws Exception {
		Reporter.log(String.format("broken Links Validation Test Started %s", Thread.currentThread().getId()), true);
		getDriver().navigate().to("https://www.aumni.fund/");

		SoftAssert soft = new SoftAssert();

		String url;
		HttpURLConnection huc;
		int respCode;
		List<WebElement> links = getDriver().findElements(By.tagName("a"));
		for (WebElement link : links) {

			url = link.getAttribute("href");

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			huc = (HttpURLConnection) (new URL(url).openConnection());
			huc.setRequestMethod("HEAD");
			huc.connect();
			respCode = huc.getResponseCode();
			soft.assertNotNull(url);
			if (respCode >= 400) {
				System.out.println(url + " is a broken link");
			} else {
				System.out.println(url + " is a valid link");
			}
		}
		soft.assertAll();
		Reporter.log(String.format("Broken Linkss Test Ended %s", Thread.currentThread().getId()), true);
	}

}
