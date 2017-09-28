package APIs;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class Chrome {


	private static WebDriver driver;
	private static Chrome chrome=null;
	/*
	 * It will return chrome browser instance
	 * */
	public static Chrome getChromeInstance(){
		return getChromeBrowserInstance();
		
	}
	
	private static Chrome getChromeBrowserInstance(){
		if(chrome==null){
			chrome=new Chrome();	
		}
		System.setProperty("webdriver.chrome.driver", "D:/MyWorkSpace/BugHistory/src/resources/chromedriver.exe");
		 DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		 ChromeOptions options = new ChromeOptions();
		 options.addArguments("test-type");  //to prevent error on chrome start up page
		 options.addArguments("--disable-notifications"); //to disable browser notifications
		 options.addArguments("start-maximized");
		 LoggingPreferences logPrefs = new LoggingPreferences();
		 logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
		 capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
		 capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		 driver=new ChromeDriver(capabilities);
//		 chromeDriver.manage().window().setPosition(new Point(-2000, 0)); //to start chrome in invisible mode
		return chrome;
	}
	
	/*
	 * Restricting creation of Chrome object
	 */
	private Chrome(){
		//no content
	}
	
	/*
	 * get URL
	 * */
	public void get(String url){
		getURL(url);
	}
	
	private void getURL(String url){
		driver.get(url);
	}
	
	public String getcurrentURL(){
		return getURL();
	}
	
	private String getURL(){
		return driver.getCurrentUrl();
	}
	
	
	/*
	 * Get current driver object
	 */
	public WebDriver getDriver(){
		return getWebDriver();
	}
	
	private WebDriver getWebDriver(){
		return driver;
	}

	public WebElement findElementby(String type, String value){
		return findElementByAttribute(type, value);
	}
	
	private WebElement findElementByAttribute(String type, String value){
		if(type.equalsIgnoreCase(Constants.ID)){
			return driver.findElement(By.id(value));
		}else if(type.equalsIgnoreCase(Constants.NAME)){
			return driver.findElement(By.name(value));
		}else if(type.equalsIgnoreCase(Constants.CLASSNAME)){
			return driver.findElement(By.className(value));
		}else if(type.equalsIgnoreCase(Constants.CSS)){
			return driver.findElement(By.cssSelector(value));
		}else if(type.equalsIgnoreCase(Constants.LINKTEXT)){
			return driver.findElement(By.linkText(value));
		}else if(type.equalsIgnoreCase(Constants.PARTIAL_LINKTEXT)){
			return driver.findElement(By.partialLinkText(value));
		}else if(type.equalsIgnoreCase(Constants.TAGNAME)){
			return driver.findElement(By.tagName(value));
		}else if(type.equalsIgnoreCase(Constants.XPATH)){
			return driver.findElement(By.xpath(value));
		}
		return null;
	}
	
	private static String[] splitString(String str){
		Pattern strPattern=Pattern.compile("[\t\n, ]");
		String[] strArray=strPattern.split(str);
		return strArray;
	}
	
	public WebElement selectElement(String type,String value){
		return selectElementByAttribute(type, value);
	}
	
	private WebElement selectElementByAttribute(String type,String value){
		WebElement ele=null;
		if(type.equals("id")){
			ele=driver.findElement(By.id(value));	
		}else if(type.equals("css")){
			ele=driver.findElement(By.cssSelector(value));
		}else if(type.equals("xpath")){
			ele=driver.findElement(By.xpath(value));
		}
		return ele;
	}
	
	public void setInputAny(String type,String value,String key){
		setInput(type, value, key);
	}
	
	private void setInput(String type,String value,String key){
		WebElement ele=null;
		if(type.equals("id")){
			ele=driver.findElement(By.id(value));	
		}else if(type.equals("css")){
			ele=driver.findElement(By.cssSelector(value));
		}else if(type.equals("xpath")){
			ele=driver.findElement(By.xpath(value));
		}
		ele.sendKeys(key);
	}
	
	
	public String getValueFromTextBox(String type,String value){
		return getValueFromTextbox(type, value);
	}
	
	private String getValueFromTextbox(String type,String value){
		WebElement ele=null;
		if(type.equals("id")){
			ele=driver.findElement(By.id(value));	
		}else if(type.equals("css")){
			ele=driver.findElement(By.cssSelector(value));
		}else if(type.equals("xpath")){
			ele=driver.findElement(By.xpath(value));
		}else{
			return null;
		}
		String text=ele.getAttribute("value");
		return text;
	}
	
	public String getInnerText(String type,String value){
		return getInnerTextOfElement(type, value);
	}
	private String getInnerTextOfElement(String type,String value){
		WebElement ele=null;
		if(type.equals("id")){
			ele=driver.findElement(By.id(value));	
		}else if(type.equals("css")){
			ele=driver.findElement(By.cssSelector(value));
		}else if(type.equals("xpath")){
			ele=driver.findElement(By.xpath(value));
		}else{
			return null;
		}
		String text=ele.getText();
		return text;
	}
	
	
	public String getSelectedOption(String type,String value){
		return getSelectedOptionfromSelect(type, value);

	}
	private String getSelectedOptionfromSelect(String type,String value){
		WebElement ele=null;
		if(type.equals("id")){
			ele=driver.findElement(By.id(value));	
		}else if(type.equals("css")){
			ele=driver.findElement(By.cssSelector(value));
		}else if(type.equals("xpath")){
			ele=driver.findElement(By.xpath(value));
		}else{
			return null;
		}
	Select selected=new Select(ele);
	String agentStatus=selected.getFirstSelectedOption().getText();
	return agentStatus;
	}
	
	
	public String alertDismiss(){
		return dismissAlert();
	}
	
	private String dismissAlert(){
		String alertText=getAlertText();
		driver.switchTo().alert().dismiss();
		return alertText;
	}
	
	public void alertAccept(){
		acceptAlert();
	}
	
	private void acceptAlert(){
		driver.switchTo().alert().accept();
	}
	
	public void alertSetInput(String input){
		SetInputInPopUp(input);
	}
	private void SetInputInPopUp(String input){
		driver.switchTo().alert().sendKeys(input);
	}
	
	public void sleep(int wait){
		sleepInSec(wait);
	}
	
	private void sleepInSec(int wait){
		wait=wait*1000;
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public String getAlertText(){
		return getAlertTextMessage();
	}
	
	private String getAlertTextMessage(){
		return driver.switchTo().alert().getText();
	}
	
	public Object executeScript(String script){
		return executeJavaScript(script);
	}
	
	private Object executeJavaScript(String script){
		JavascriptExecutor js;
		js = (JavascriptExecutor)driver;
		return js.executeScript(script);	
	}
	
	/*
	 * copy and paste using Robot class for windows based application/pop-ups
	 */
	
	public void pasteStringInField(String text) throws AWTException{
		pasteStringInInputField(text);
		
	}
	
	private void pasteStringInInputField(String text) throws AWTException {
		Robot rb=new Robot();
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    StringSelection stringSelection = new StringSelection( text);
	    clipboard.setContents(stringSelection, stringSelection);
	    rb.keyPress(KeyEvent.VK_CONTROL);
	    rb.keyPress(KeyEvent.VK_V);
	    rb.keyRelease(KeyEvent.VK_V);
	    rb.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	
	public void submitForm(String action, String method, String target,
			Hashtable values) {
		
		submitDynamicForm(action, method, target, values);
		
	}
	
	private void submitDynamicForm(String action, String method, String target,
			Hashtable values) {
		String params = "";
		for (Enumeration keys = values.keys(); keys.hasMoreElements();) {

			String key = (String) keys.nextElement();
			String value = (String) values.get(key);
			params = params
					+ "var hiddenField = document.createElement(\"input\");hiddenField.setAttribute(\"type\", \"hidden\");hiddenField.setAttribute(\"name\", \""
					+ key + "\");hiddenField.setAttribute(\"value\", \""
					+ value + "\");form.appendChild(hiddenField);";

		}
		String javaScript = "var form = document.createElement(\"form\");form.setAttribute(\"method\", \""
				+ method
				+ "\");form.setAttribute(\"action\", \""
				+ action
				+ "\");"
				+ params
				+ "document.body.appendChild(form);form.submit();";
		executeScript(javaScript);
	}
	
//	public List<LogEntry> getAllEntries(){
//		return this.getDriver().manage().logs().get(LogType.PERFORMANCE).getAll();
//	}
	
	public boolean setValueInLocalStorage(String key, String value){
		String localStorageScript="window.localStorage.setItem('"+key+"',"+value+");";
		executeJavaScript(localStorageScript);
		if(getValueFromLocalStorage(key)!=null){
			return true;
		}else{
			return false;
		}
	}
	
	public String getValueFromLocalStorage(String key){
		String localStorageScript="return window.localStorage.getItem('"+key+"');";
		String value=(String)executeScript(localStorageScript);
		if(null==value||value.equals("null")){
			return null;
		}else{
			return value;
		}
		
	}
	
	public int getScriptCount(){
		int currCount=-1;
		try {
			String javascript = "window.count=new Array(); for(var temporary in window){ if(temporary) window.count.push(temporary) ;}";
			executeScript(javascript);
			String a = executeScript("return window.count.toString()").toString();
			currCount=Integer.parseInt(executeScript("return window.count.length").toString());
		} catch (Exception ex) {
			System.out.println("^^^^^^^^^^^^^^^^^ caught exception here:" + ex.getMessage());
		}
		return currCount;
	}
	
	public String getAllScripts(){
		String a=null;
		try {
			String javascript = "window.count=new Array(); for(var temporary in window){ if(temporary) window.count.push(temporary) ;}";
			executeScript(javascript);
			a = executeScript("return window.count.toString()").toString();
		} catch (Exception ex) {
			System.out.println("^^^^^^^^^^^^^^^^^ caught exception here:" + ex.getMessage());
			
		}
		return a;
	}

}
