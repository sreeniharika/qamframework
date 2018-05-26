
package testbase;



import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;



import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
//import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import frameworkdata.ExtentManager;
import uiActions.util;


/**
 * @author Niharika
 *
 * 
 */



public class base {
	
	public static WebDriver driver;
	public static Properties prop;
	public static Properties OR;
	
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	 private static Logger log = LogManager.getLogger(base.class.getName());
	public  SoftAssert softAssert = new SoftAssert();
	
	public static class LoginSauce { 
		public static final String USERNAME = "niharikasree";
	}
    public static final String ACCESS_KEY = "480a6e7e-33b3-4679-9acf-c7eec29e5710";
    public static final String URL = "https://" + "niharikasree" + ":" + "480a6e7e-33b3-4679-9acf-c7eec29e5710" + "@ondemand.saucelabs.com:443/wd/hub";
    public static WebDriver getDriver() {
        return driver;
    }
// initialize driver
	public WebDriver initializeDriver() throws IOException{
		
		//data properties
		 prop = new Properties();
		 try{
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\config\\data.properties");
		prop.load(fs);
		}catch(Exception e){
		e.printStackTrace();
		 }
	
		//OR properties
		OR = new Properties();
		try{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\config\\OR.properties");
		OR.load(fis);
		}catch(Exception e){
			e.printStackTrace();
		}
		String browserName = prop.getProperty("browser");
	
		if(browserName.equals("chrome")){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			options.addArguments("disable-infobars");
			//options.setHeadless(true);
			//options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
			//execute in chrome driver
			System.setProperty("webdriver.chrome.driver",".\\drivers\\chromedriver.exe");
		 driver = new ChromeDriver(options);
		 test.log(LogStatus.PASS, "Executing in Chrome Browser");
		}
		else if(browserName.equals("firefox")){
			//execute in firefox driver
			
			System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
			
			driver=new FirefoxDriver();
			 test.log(LogStatus.PASS, "Executing in Firefox Browser");
		}
		else if(browserName.equals("IE")){
			//execute in ie
			System.setProperty("webdriver.ie.driver", ".\\drivers\\IEDriverServer.exe");
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver();
			 test.log(LogStatus.PASS, "Executing in IE Browser");
		}
		
		else if (browserName.equals("sauce"))
        {    
			DesiredCapabilities caps = DesiredCapabilities.safari();
			caps.setCapability("platform", "macOS 10.12");
			caps.setCapability("version", "11.0");
                WebDriver driver = new RemoteWebDriver(new URL(URL),caps);
                driver.get("http://shop.thetestingworld.com/");
                
        }
        
   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
   driver.manage().window().maximize();
		return driver;
		
	}

	
	//zoho login

public boolean zohoLogin(String username,String password) {
	test.log(LogStatus.INFO, "Trying to login with "+ username+","+password);
	
	
	// wait for page to load
	//waitForPageToLoad();
	// switch to frame
	util u = new util();
	u.click("zoho_login_xpath");
	u.type("email_textbox_id", username);
	u.type("password_textbox_id",password);
	u.click("signin_xpath");
	test.log(LogStatus.PASS, "Test Passed");
	
	if(u.isElementPresent("signin_xpath")){
		test.log(LogStatus.INFO, "Login Success");
		return true;
	}
	else{
		test.log(LogStatus.INFO, "Login Failed");
		return false;
	}
	
}

// get element
public  WebElement getElement(String locatorKey){
	WebElement e=null;
	try{
	if(locatorKey.endsWith("_id"))
		e = driver.findElement(By.id(OR.getProperty(locatorKey)));
	else if(locatorKey.endsWith("_name"))
		e = driver.findElement(By.name(OR.getProperty(locatorKey)));
	else if(locatorKey.endsWith("_xpath"))
		e = driver.findElement(By.xpath(OR.getProperty(locatorKey)));
	else if(locatorKey.endsWith("_class"))
		e = driver.findElement(By.className(OR.getProperty(locatorKey)));
	else if(locatorKey.endsWith("_css"))
		e = driver.findElement(By.cssSelector(OR.getProperty(locatorKey)));
	else{
		reportFailure("Locator not correct - " + locatorKey);
		
		Assert.assertFalse(false, "Locator not correct - " + locatorKey);
		
	}
	
	}catch(Exception ex){
		// fail the test and report the error
		reportFailure(ex.getMessage());
		ex.printStackTrace();
		
		Assert.assertFalse(false, "Failed the test - "+ex.getMessage());
	}
	return e;
}





// is element present
public boolean isElementPresent(String locatorKey){
	List<WebElement> elementList=null;
	try{
	if(locatorKey.endsWith("_id"))
		elementList = driver.findElements(By.id(OR.getProperty(locatorKey)));
	else if(locatorKey.endsWith("_name"))
		elementList = driver.findElements(By.name(OR.getProperty(locatorKey)));
	else if(locatorKey.endsWith("_xpath"))
		elementList = driver.findElements(By.xpath(OR.getProperty(locatorKey)));
	else if(locatorKey.endsWith("_class"))
		elementList = driver.findElements(By.className(OR.getProperty(locatorKey)));
	else if(locatorKey.endsWith("_css"))
		elementList = driver.findElements(By.cssSelector(OR.getProperty(locatorKey)));
	else{
		reportFailure("Locator not correct - " + locatorKey);
		
		Assert.assertFalse(false, "Locator not correct - " + locatorKey);
	}
	
	if(elementList.size()==0)
		return false;	
	else
		return true;
	}
	catch(Exception e){
		reportFailure(e.getMessage());
		e.printStackTrace();
	}
	return false;
}

public boolean isElementNotPresent(String locatorkey) {
    try {
      if(locatorkey.isEmpty())
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      
    }
	return false;
}





/********************************Read data from Excel ***************************/

@Test
public static String testdata(String testCaseName,String string) throws IOException {
    
	  String content="";
	//  File file=new File (System.getProperty("user.dir") + "\\src\\main\\java\\data\\createaccount.xlsx");
	  File file=new File (System.getProperty("user.dir") + "\\src\\main\\java\\data\\TestData1.xlsx");
		 FileInputStream fs=new FileInputStream(file);
		 XSSFWorkbook wk = new XSSFWorkbook(fs);
		  XSSFSheet s1 = wk.getSheet(testCaseName);
		  
		 for(int i=0;i>=0;i++) {
			 XSSFRow r1 = s1.getRow(i);
			  XSSFCell c0 = r1.getCell(0);
		 if (string.equals(c0.getStringCellValue())) {
			 XSSFCell c1 = r1.getCell(1);
			  content= c1.getStringCellValue();
			 break;
		 }
		 if(c0.getStringCellValue().isEmpty()) {
			 break;
		 }
		 }
		return content;
	}	



/*****************************Reporting********************************/

public void reportPass(String msg){
test.log(LogStatus.PASS, msg);
}

public  void reportFailure(String msg){
	test.log(LogStatus.FAIL, msg);
	//takeScreenShot(msg);
	takeScreenShot();
	Assert.fail(msg);
}



public  void takeScreenShot(){
	// fileName of the screenshot
	Date d=new Date();
	String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
	// store screenshot in that file
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try {
		FileHandler.copy(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
		//FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//put screenshot file in reports
	test.log(LogStatus.INFO,"Screenshot-> "+ test.addScreenCapture(System.getProperty("user.dir")+"//screenshots//"+screenshotFile));
	
}


public void getScreenshot(String name) {
	// TODO Auto-generated method stub
	
}
}

	

