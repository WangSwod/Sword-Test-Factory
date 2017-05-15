package com.sword.keyword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.sword.data.PropertyMap;
import com.sword.util.LogUtil;
import com.sword.util.Util;

public class Keywords {
	
	private WebDriver driver;
	private PropertyMap map = new PropertyMap();
	
//	public Keywords(WebDriver driver) {
//		this.driver = driver;
//	}
	
	
	//The first argument does not have any meaning, just for the method reflection 
	public boolean  openBrowser(String nothing, String browserName) {
		
		switch (browserName) {
		case "ie":
			
			break;
		case "chrome":
			System.setProperty("webdriver.chrome.driver", map.getProperty("chromeDriver_Path"));
			driver = new ChromeDriver();
			LogUtil.info("Create chrome driver successfully");
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", map.getProperty("fireFoxDriver_Path"));
			driver = new FirefoxDriver();
			LogUtil.info("Create fireFox driver successfully");
			break;
		default:
			break;
		}
		
		return true;
	}
	
	
	
	public boolean visitURL(String nothing , String url) {
		driver.get(url);
		LogUtil.info("Visit URL:" + url);
		return true;
		
	}
	
	public boolean pause(String noting, String millisecond){
		try {
			Util.pause(new Double(millisecond).intValue());
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
		
		
	}
//
//	public WebDriver visitURLTwo(String url) {
//		driver.navigate().to(url);
//		return driver;
//	}
//
//	public WebDriver visitPreviousPage() {
//		driver.navigate().back();
//		return driver;
//	}
//
//	public WebDriver visitNextPage() {
//		driver.navigate().forward();
//		return driver;
//	}
//
//	public WebDriver refreshPage() {
//		driver.navigate().refresh();
//		return driver;
//	}
//
//	public WebDriver setWindowSize(int width, int height) {
//
//		driver.manage().window().setSize(new Dimension(width, height));
//		return driver;
//	}
//
//	public Dimension getWindowSize() {
//		return driver.manage().window().getSize();
//	}
//
//	public WebDriver setWindowPostion(int x_coordinate, int y_coordinate) {
//		driver.manage().window().setPosition(new Point(x_coordinate, y_coordinate));
//		return driver;
//	}
//
//	public Point getWindowPosition() {
//		return driver.manage().window().getPosition();
//	}
//
//	public WebDriver maximizeWindow() {
//		driver.manage().window().maximize();
//
//		return driver;
//	}
//	
//	public String getPageTitle() {
//		return driver.getTitle();
//	}
//	
//	
//	public String getPageSource() {
//		return driver.getPageSource();
//	}
//	
//	public String getCurrentURL() {
//		return driver.getCurrentUrl();
//	}
//	
//	public WebDriver clearText(By by) {
//		driver.findElement(by).clear();;
//		
//		return driver;
//	}
//	
	public boolean input(String expression,String text) {
		
		
		
		WebElement element;
		try {
			element = driver.findElement(map.getLocator(expression));
			element.clear();
			LogUtil.info("Clear all the text in the element:" + expression);
			element.sendKeys(text);
			LogUtil.info("input text:" + text + " in the element:" +expression);
			return true;
			
		} catch (Exception e) {
			
			LogUtil.info("Fail to input in the element:" +expression);
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
	public boolean click(String expression , String noting) {
		try {
			driver.findElement(map.getLocator(expression)).click();
			LogUtil.info("Click on the element:" + expression);
			return true;
		} catch (Exception e) {
			
			LogUtil.info("Fail to click on the element:" + expression);
			e.printStackTrace();
			return false;
		}
		
	}
//	
//	public void setAttributeOfElement(WebElement element, String attr, String value) {
//		
//		try {
//			
//			String script = "arguments[0].setAttribute(arguments[1],arguments[2]);";
//			((JavascriptExecutor)driver).executeScript(script, element,attr, value);
//		}catch(NoSuchElementException e){ 
//			Util.print("No such Element");
//			e.printStackTrace();
//		}catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public void removeAttributeOfElement(WebElement element, String attr) {
//		
//		try {
//			
//			String script = "arguments[0].removeAttribute(arguments[1]);";
//			((JavascriptExecutor)driver).executeScript(script, element,attr);
//		}catch(NoSuchElementException e){ 
//			Util.print("No such Element");
//			e.printStackTrace();
//		}catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public void clickOnElement(WebElement element){
//		
//		try {
//			if(element.isDisplayed() && element.isEnabled()){
//				Util.print("click on the element: "+ element);
//				element.click();
//			}else {
//				Util.print("Element is not displayed or enabled");
//			}
//		} catch (NoSuchElementException e) {
//			
//			Util.print("There is no such element");
//			e.printStackTrace();
//		}catch (Exception e) {
//
//			Util.print("Can not click on the element");
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public void  scrollToBottom() {
//		String script = "window.scrollTo(0, document.body.scrollHeight);";
//		((JavascriptExecutor)driver).executeScript(script);
//		Util.pause(1000);
//	}
//	
//	public void scrollToElement(WebElement element) {
//		String script = "arguments[0].scrollIntoView()";
//		((JavascriptExecutor)driver).executeScript(script,element);
//		
//		Util.pause(1000);
//	}
//	
//	public void scrollBy(int width, int height) {
//		String script = "window.scrollBy(arguments[0],argument[1]";
//		((JavascriptExecutor)driver).executeScript(script,width,height);
//		Util.pause(1000);
//	}
//	
//	public WebDriver doubleClickOnElement(By by) {
//		
//		Actions actions = new Actions(driver);
//		
//		WebElement element = driver.findElement(by);
//		
//		actions.doubleClick(element).build().perform();
//		
//		 return driver;
//	}
//	
//	public WebDriver rightClickOnElement(By by) {
//		Actions actions = new Actions(driver);
//		WebElement element = driver.findElement(by);
//		actions.contextClick(element).perform();
//		
//		return driver;
//		
//	}
//	
//	public void hoverOnElement(By by) {
//		Actions actions = new Actions(driver);
//		WebElement element = driver.findElement(by);
//		
//		actions.moveToElement(element).perform();;
//		
//		try {
//			Thread.sleep(3000);
//		} catch (Exception e) {
//			
//		}	
//		
//	}
//	
//	public void longPressOnElement(By by, int millisecond) {
//		
//		WebElement element = driver.findElement(by);
//		Actions actions  = new Actions(driver);
//		
//		actions.clickAndHold(element).perform();
//		
//		try {
//			Thread.sleep(millisecond);
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		
//		actions.release(element).perform();
//	}
//	
//	public String getElementAttribute(By by, String attr) {
//		
//		return driver.findElement(by).getAttribute(attr);
//	}
//	
//	public String getElementCSSValue(By by,String property) {
//		return driver.findElement(by).getCssValue(property);
//	}
//	
//	public Select getDropListElement(By by) {
//		return new Select(driver.findElement(by));
//	}
//	
//	public String getTextFromDropListByIndex(By by, int index) {
//		
//		Select droplist = getDropListElement(by);
//		droplist.deselectByIndex(index);
//		return  droplist.getFirstSelectedOption().getText();
//	}
//	
//	public String getTextFromDropListByValue(By by, String value) {
//		Select droplist = getDropListElement(by);
//		droplist.selectByValue(value);
//		return droplist.getFirstSelectedOption().getText();
//		
//	}
//	
//	
//	
//	public String getTextFromDropListByVisibleText(By by, String text) {
//		
//		Select droplist = getDropListElement(by);
//		droplist.selectByVisibleText(text);
//		
//		return droplist.getFirstSelectedOption().getText();
//	}
//	
//	public List<String> getAllOptionsFromDropList(By by) {
//		
//		Select droplist = getDropListElement(by);
//		
//		List<String> result = new ArrayList<String>();
//		
//		for(WebElement option: droplist.getOptions()){
//			
//			result.add(option.getText());
//		}
//		
//		return result;
//		
//	}
//	
//	public WebElement getOptionFromDropListByIndex(By by, int index) {
//		
//		Select droplist = getDropListElement(by);
//		 droplist.selectByIndex(index);
//		 
//		 return droplist.getFirstSelectedOption();
//		 
//		 
//	}
//	
//	public WebElement getOptionFromDropListByValue(By by, String value) {
//		Select droplist = getDropListElement(by);
//		droplist.selectByValue(value);
//		
//		return droplist.getFirstSelectedOption();
//	}
//	
//	public WebElement getOptionFromDropListByVisibleText(By by, String text) {
//		Select droplist = getDropListElement(by);
//		droplist.selectByVisibleText(text);
//		return droplist.getFirstSelectedOption();
//		
//	}
//	
//	public WebDriver checkElement(By by) {
//		
//		WebElement element = driver.findElement(by);
//		if(!element.isSelected()){
//			element.click();
//			assertTrue(element.isSelected());
//		}
//		
//		return driver;
//		
//	}
//	
//	public boolean takeScreenShot() {
//		Date date = new Date();
//		
//		//create directory for the screenshot
//		String dir = "/Users/sword-wang/desktop/"+ Util.getDate(date, "-");
//		if(!Util.isDirExist(dir)){
//			Util.createDir(dir);
//		}
//		
//		
//		String pathToFile = dir +"/"+Util.getTime(date, ":")+".png";
//		
//		return this.takeScreenShot(pathToFile);
//	}
//	
//	public boolean takeScreenShot(String pathToFile) {
//		
//		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		
//		try {
//			FileUtils.copyFile(src, new File(pathToFile));
//			Util.print("create screenshot successfully");
//			return true;
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//			return false;
//		}
//		
//	}
//	
//	public Object excuteJS(String script) {
//		JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
//		
//		return javascriptExecutor.executeScript(script);
//	}
//	
//	public WebDriver dragElement(By by, int x_Pixel, int y_Pixel) {
//		
//		WebElement element = driver.findElement(by);
//		new Actions(driver).dragAndDropBy(element, x_Pixel,y_Pixel).build().perform();
//		
//		return driver;
//	}
//	
//	public WebDriver pressDownKeyboard(CharSequence key) {
//		new Actions(driver).keyDown(key).perform();
//		return driver;
//	}
//	
//	public WebDriver releaseKeyboard(CharSequence key) {
//		new Actions(driver).keyUp(key).perform();
//		return driver;
//	}

	public  boolean  closeBrowser(String nothingOne, String nothingTwo) {
		try {
			driver.quit();
			LogUtil.info("Close browser successfully");
			return true;
		} catch (Exception e) {
			LogUtil.info("Fail to close browser");
			e.printStackTrace();
			return false;
		}
		
	}
	
	

//	public void waitForElementToBeClickable(By by){
//
//		wait.until(ExpectedConditions.elementToBeClickable(by));
//	}
//	
//	public void waitForElementToBeSelected(By by) {
//		
//		wait.until(ExpectedConditions.elementToBeSelected(by));
//	}
//	
//	public void waitForElementToBePresent(By by) {
//		wait.until(ExpectedConditions.presenceOfElementLocated(by));
//	}
//	
//	@SuppressWarnings("deprecation")
//	public void  waitForElementToContainText(By by ,String text) {
//		wait.until(ExpectedConditions.textToBePresentInElement(by, text));
//	}
//	
//	public void  waitForTitleToBePresent(String title) {
//		wait.until(ExpectedConditions.titleContains(title));
//		
//	}
//	
//	public void  waitForElementValueToContainText(By by, String text) {
//		wait.until(ExpectedConditions.textToBePresentInElementValue(by, text));
//	}
//	
//	
//	public boolean isElementPresent(By by) {
//		try {
//			driver.findElement(by);
//			return true;
//		} catch (NoSuchElementException e) {
//			
//			return false;
//		}
//	}
//	
//	public void switchToAlert() {
//		driver.switchTo().alert();
//	}
//	
//	
//	//Can only be used in the Window PC
//	public void copyTextToElement(WebElement element, String text) {
//		
//		StringSelection content = new StringSelection(text);
//		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(content,null);
//		
//		Robot robot = null;
//		
//		try {
//			robot = new Robot();
//		} catch (AWTException e) {
//			
//			e.printStackTrace();
//		}
//		
//		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_V);
//		robot.keyRelease(KeyEvent.VK_K);
//		robot.keyRelease(KeyEvent.VK_CONTROL);
//		
//	}
//	
//	public void  highlightElement(By by) {
//		WebElement element = driver.findElement(by);		
//		
//		String attr = "style";
//		String value = "background:yellow ; border:2px solid black";
//		
//		setAttributeOfElement(element, attr, value);
//	}
	
	public boolean assertString (String nothing, String text){
		try {
			Assert.assertTrue(driver.getPageSource().contains(text));
			LogUtil.info("Assert successfully");
			return true;
		} catch (Exception e) {
			
			LogUtil.info("Fail to assert");
			e.getStackTrace();
			return false;
			
		}
	}
	
	public boolean assertStringNotExisting(String nothing, String text) {
		try {
			Assert.assertTrue(!driver.getPageSource().contains(text));
			LogUtil.info("Assert successfully");
			return true;
		} catch (Exception e) {
			
			LogUtil.info("Fail to assert");
			e.getStackTrace();
			return false;
			
		}
	}
	
}
