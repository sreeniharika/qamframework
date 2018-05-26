/**
 * 
 */
package frameworkdata;

/**
 * @author Niharika
 *
 * 
 */

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import frameworkdata.ExtentManager;
import uiActions.util;

public class shopNavigate extends testbase.base{
SoftAssert softAssert = new SoftAssert();
private static Logger log = LogManager.getLogger(shopNavigate.class.getName());
util u = new util();
testbase.Config config = new testbase.Config(prop);

@BeforeTest
public void driverinitialize() throws IOException{
	test = rep.startTest("shopNavigate");
	test.log(LogStatus.INFO, "starting the test shopNavigate");
	initializeDriver();
	
}
@Test
public void Verifynavigation() throws IOException, InterruptedException {
	
	driver.navigate().to(prop.getProperty("sauceurl"));
	test.log(LogStatus.PASS, "opened url");
	
	u.click("wishlist_link_xpath");
	test.log(LogStatus.PASS, "Clicked on wishlist");
	
	u.waitToLoad();
	
	u.click("shoppingcart_link_xpath");
	test.log(LogStatus.PASS, "Clicked on shoppingcart");
	
	u.waitToLoad();
	
	//driver.navigate().back();
	util.navigate_back(driver);
	test.log(LogStatus.PASS, "Navigating Back");
	
	u.isElementPresent(getElement("accountlogin_text_xpath"));
	test.log(LogStatus.PASS, "Account Login text is displayed");
	
	
	//driver.navigate().forward();
	util.navigate_forward(driver);
	test.log(LogStatus.PASS, "Navigating Forward");
	
	
	u.isElementPresent(getElement("shoppingcart_text_xpath"));
	test.log(LogStatus.PASS, "Account Login text is displayed");
	
	
	//driver.navigate().refresh();
	
	util.refresh(driver);
	test.log(LogStatus.PASS, "Page Refreshed");
	
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
