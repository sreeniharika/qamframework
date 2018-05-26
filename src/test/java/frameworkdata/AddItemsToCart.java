/**
 * 
 */
package frameworkdata;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.apache.logging.log4j.*;

import com.relevantcodes.extentreports.LogStatus;

import uiActions.util;

/**
 * @author Niharika
 *
 * 
 */
public class AddItemsToCart extends testbase.base {
	SoftAssert softAssert = new SoftAssert();
    private static Logger log = LogManager.getLogger(AddItemsToCart.class.getName());
	
    @BeforeTest
	public void driverinitialize() throws IOException{
		test = rep.startTest("AddItemsToCart");
		test.log(LogStatus.INFO, "starting the test AddItemsToCart");
		log.debug("starting test");
		initializeDriver();
		//driver.get("http://shop.thetestingworld.com/");
		
	}
	
	@Test
	public void cartItems() throws IOException, InterruptedException{
		
		testbase.Config config = new testbase.Config(prop);
	     driver.get(config.getUrl());
		
		test.log(LogStatus.PASS, "opened url");
		log.debug("opened URL");
		util u = new util();
		
		Actions a=new Actions(driver);
		a.moveToElement(getElement("desktop_navbar_xpath")).build().perform();
		test.log(LogStatus.PASS, "mouseover performed");
		
		u.click("mac_menuitem_xpath");
		test.log(LogStatus.PASS, "Clicked on mac option");
		u.waitToLoad();
		u.click("mac_addtocart_xpath");
		test.log(LogStatus.PASS, "Clicked on add to cart button");
		u.waitToLoad();
		u.isElementPresent(getElement("1_item_button_xpath"));
		test.log(LogStatus.PASS, "Verify 1 item  is updated");
		u.click("1_item_button_xpath");
		test.log(LogStatus.PASS, "Clicked on cart button");
		u.waitToLoad();
		u.click("remove_button_xpath");
		test.log(LogStatus.PASS, "Clicked on Remove button");
		u.waitToLoad();
		u.isElementPresent(getElement("0_item_txtbtn_xpath"));
		test.log(LogStatus.PASS, "Verify 0 item  is updated");
		test.log(LogStatus.INFO, "Test passed");
	
		
	}

	
		
		
	@AfterTest
	public void closeBrowser(){
		driver.close();
		driver = null; 
		rep.endTest(test);
		rep.flush();
	}
	}

