/**
 * 
 */
package uiActions;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;

//import com.hybridFramework.helper.Javascript.JavaScriptHelper;
//import com.hybridFramework.helper.Logger.LoggerHelper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import frameworkdata.ExtentManager;
import testbase.base;

/**
 * @author Niharika
 *
 * 
 */
@Test
public class util extends base{

	public static WebDriver driver;
	public static Properties prop;
	public static Properties OR;
	public File f1;
	public File f2;
	public FileInputStream file1;
	public FileInputStream file2;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	//private Logger Log = LoggerHelper.getLogger(util.class);
	 private static Logger log = LogManager.getLogger(util.class.getName());
	
	
	//close browser

	public void closeBrowser(){
		driver.close();
		driver = null;
	}
	
	
	public static String fetchLocatorValue(String key) throws IOException
	{
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\config\\Elements.properties");
		
		Properties property = new Properties();
		property.load(file);
		return property.getProperty(key).toString();
	}


	/****************click,wait,type **********************/

	//click
	public  void click(String locatorKey){
		try{
		getElement(locatorKey).click();
		}catch(Exception e){
			reportFailure(e.getMessage());
			e.printStackTrace();
		}
		
	}


	//wait
	public void waitToLoad() throws InterruptedException{
		Thread.sleep(5000);
	}
	//pageloadtimeout
	public void timeouts(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	//type
	public void type(String locatorKey,String data){
		try{
		getElement(locatorKey).sendKeys(data);
		}catch(Exception e){
			reportFailure(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void waitForElement(By locator) {

		WebDriverWait wait = new WebDriverWait(driver,30);
		   wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			
		} 
	
	 /* public static void waitForElement(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		} */

		public static void waitTillElementFound(WebDriver driver,By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} 
		
		
		/* public static void waitForElement(By locatorKey) {

				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(locatorKey));
				} */
		
	
	/*	 public static void waitTillElementFound(WebElement element,int seconds) {
					WebDriverWait wait = new WebDriverWait(driver, seconds);
					wait.until(ExpectedConditions.visibilityOf(element));
					} */
		 
		 public static void waitForElementFound(By element,int seconds) {
				WebDriverWait wait = new WebDriverWait(driver, seconds);
				//wait.until(ExpectedConditions.visibilityOf(element));
				wait.until(ExpectedConditions.visibilityOfElementLocated(element));
				}
	
		 public static void waitForPresence(By element,int seconds){
		 WebDriverWait wait = new WebDriverWait(driver, seconds);
		 wait.until(ExpectedConditions.presenceOfElementLocated(element)); 
		 }
	
	
	
	
	/********************Browser Navigation***********************/
	public static void navigate_forward(WebDriver driver) {
		driver.navigate().forward();
		}

		public static void navigate_back(WebDriver driver) {
		driver.navigate().back();
		}

		public static void refresh(WebDriver driver) {
		driver.navigate().refresh();
		}
	
	/***********************Validations***************************/

	
	
	//click and wait
	public void clickAndWait(String locatorKey) throws InterruptedException{
		try{
		test.log(LogStatus.INFO, "Clicking and waiting on - "+locatorKey);
		int count=5;
		for(int i=0;i<count;i++){
			getElement(locatorKey).click();
			wait(5000);
			if(isElementPresent(locatorKey))
				break;
			else{
				reportFailure("Locator not correct - " + locatorKey);
			Assert.assertFalse(false, "Locator not correct - " + locatorKey);
			}
		}
		}
		catch(Exception e){
			reportFailure(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	public boolean verifyTextEquals(WebElement element,String expectedText){
		try {
	Assert.assertTrue(element.getText().matches(expectedText));
		}catch (Error e) {
			reportFailure(e.toString());
			Assert.assertFalse(false, "Text didn't match");
		}
	return false;
	}
	
	//wait for element
	public WebElement waitForElement(WebDriver driver,long time,WebElement element){
		WebDriverWait wait = new WebDriverWait(driver,time);
		//
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		//return wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
		
		
	}
		



	

	/************************App functions*****************************/

	//flipkart login
	public boolean doLogin(String username,String password) {
		test.log(LogStatus.INFO, "Trying to login with "+ username+","+password);
		type("ENTER_EMAIL_xpath", username);
		type("ENTER_PASSWORD_xpath",password);
		click("LOGIN_BUTTON_xpath");
		test.log(LogStatus.PASS, "Test Passed");
		
		if(isElementPresent("LOGIN_BUTTON_xpath")){
			test.log(LogStatus.INFO, "Login Success");
			return true;
		}
		else{
			test.log(LogStatus.INFO, "Login Failed");
			return false;
		}
		
	}

	//zoho login
   public boolean zohoLogin(String username,String password) {
		test.log(LogStatus.INFO, "Trying to login with "+ username+","+password);
		click("zoho_login_xpath");
		type("email_textbox_id", username);
		type("password_textbox_id",password);
		click("signin_xpath");
		test.log(LogStatus.PASS, "Test Passed");
		
		if(isElementPresent("signin_xpath")){
			test.log(LogStatus.INFO, "Login Success");
			return true;
		}
		else{
			test.log(LogStatus.INFO, "Login Failed");
			return false;
		}
		
	}


	/********************************Read data from Excel ***************************/

	public static String testdata(String testCaseName,String string) throws IOException {
	    
		  String content="";
		   File file=new File ("C:\\Users\\nihar\\workspace\\frameworkdd\\src\\main\\java\\resources\\TestData1.xlsx");
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

	/*****************************Alerts***********************************/
	//alert
	      public static boolean alert(){
			try{
				Alert a = driver.switchTo().alert();
				String str = a.getText();
				a.accept();
				return true;
			}catch(Exception e){
			
			return false;
			}
			}
	
		
	      public Alert getAlert() {
		 driver.switchTo().alert();
		 return getAlert();
		  }
		
		
		public void AcceptAlert() {
			 getAlert().accept();
			 test.log(LogStatus.PASS, "Alert Accepted");
		}
		
		public void DismissAlert() {
			
			getAlert().dismiss();
			 test.log(LogStatus.PASS, "Alert Dismissed");
		}
		
		public String getAlertText() {
			String text = getAlert().getText();
			 test.log(LogStatus.PASS, "Text retrieved");
			return text;
		}
		
		public boolean isAlertPresent() {
			try {
				driver.switchTo().alert();
				test.log(LogStatus.PASS, "Alert Found");
				
				return true;
			} catch (NoAlertPresentException e) {
				// Ignore
				test.log(LogStatus.FAIL, "Alert not found");
				return false;
			}
		}
		
		public void AcceptAlertIfPresent() {
			if (!isAlertPresent())
				return;
			AcceptAlert();
			test.log(LogStatus.PASS, "Alert Accepted");
		}
		
		public void DismissAlertIfPresent() {

			if (!isAlertPresent())
				return;
			DismissAlert();
			test.log(LogStatus.PASS, "Alert Dismissed");
		}
		
		public void AcceptPrompt(String text) {
			
			if (!isAlertPresent())
				return;
			
			Alert alert = getAlert();
			alert.sendKeys(text);
			alert.accept();
			test.log(LogStatus.PASS, "Prompt Alert Accepted");
		}

	    /******************* window handling ********************/
	
		public static void switchToNewWindow() {
			Set s = driver.getWindowHandles();
			Iterator itr = s.iterator();
			String w1 = (String) itr.next();
			String w2 = (String) itr.next();
			driver.switchTo().window(w2);
			}

			public static void switchToOldWindow() {
			Set s = driver.getWindowHandles();
			Iterator itr = s.iterator();
			String w1 = (String) itr.next();
			String w2 = (String) itr.next();
			driver.switchTo().window(w1);
			}

			public static void switchToParentWindow() {
			driver.switchTo().defaultContent();
			}

		
		
		/*****************Verify element present / element not present ***************/
	
		
		
		public boolean isElementPresent(WebElement element) {
			   try {
			       element.getText();
			       return true;
			      } catch (NoSuchElementException e) {
			       return false;
			      }
		          }

	
	   public  boolean verifyElementNotPresent( WebElement element) {
			boolean isDispalyed = false;
			try {
				 element.isDisplayed();
				 test.log(LogStatus.FAIL,element.getText()+" is dispalyed");
				 
			}
			catch(Exception ex) {
				test.log(LogStatus.PASS,"Element not found " + ex);
				isDispalyed = true;
			}
			
			return isDispalyed;
		} 

		public static boolean isElementNoPresent( WebElement element) {
		
			try{
		//element.getSize().equals(0);
				//driver.findElements().size()>0 
			if(driver.findElements((By) element).size()>0)
			return false;
		}
		catch(Exception ex) {
			return true;
		}
			return false;
			}
		
		public String isElementNotPresent(WebElement element){

			try
			{
		    WebDriverWait d = new WebDriverWait(driver,10);
		    d.until(ExpectedConditions.visibilityOf(element));
			}
		    catch(Exception e){
		    	
		    	return "Element not displayed";
			}
			return "Element Displayed";
		}	
		
		

		/*************************Dropdown*********************************/
		
	   public String getSelectedValue(WebElement element) {
			String value = new Select(element).getFirstSelectedOption().getText();
		 	return value;
		}
		
		public void SelectUsingIndex(WebElement element,int index) {
			Select select = new Select(element);
			select.selectByIndex(index);
			
		}
		
		public void SelectUsingVisibleText(WebElement element,String text) {
			Select select = new Select(element);
			select.selectByVisibleText(text);
			}
		
		public void SelectByVisibleValue(WebElement element,String text) {
			Select select = new Select(element);
			select.selectByValue(text);
		}
		
		
		public List<String> getAllDropDownValues(WebElement locator) {
			Select select = new Select(locator);
			List<WebElement> elementList = select.getOptions();
			List<String> valueList = new LinkedList<String>();
			
			for (WebElement element : elementList) {
				test.log(LogStatus.PASS,element.getText());
				valueList.add(element.getText());
			}
			return valueList;
		}
		
// is selected
		
		public static  boolean verifyisChecked( WebElement element) {
	
			//method1
	/*		boolean isSelected = false;
			try {
				 element.isSelected();
				 isSelected = true;
				//test.log(LogStatus.PASS,element.getText()+" is dispalyed");
				 log.info(element.getText()+" is dispalyed");
			}
			catch(Exception ex) {
			//test.log(LogStatus.FAIL,"Element not found " + ex);
			log.info("Element not found " + ex);
				isSelected = false;
				ex.printStackTrace();
			}
			
			return isSelected;
		} */
	
			//method2
			boolean result = false;
			if(element.isSelected()){
				
					result = true;
			}
				else{
					result = false;
				}
				return result;
			} 

		/************************scroll*******************************/
		//scroll to bottom
		public static void scrollToBottom(WebDriver driver) {
	        ((JavascriptExecutor) driver)
	                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    }
		
		//scroll to element
		public static void scrollTo(WebDriver driver, WebElement element) {
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView();", element);
	    }
		
		//scroll by coordinates
		public void scrollingByCoordinatesofAPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)");
		}

		
		}
