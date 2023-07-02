package lib.selenium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import lib.listeners.WebDriverListener;

/**
 * @author dell
 *
 */
public class WebDriverServiceImpl extends WebDriverListener implements WebDriverService {
	private WebDriverWait wait;
	private Actions builder;

	/*
	 * It will be used while using property file (multi-language) - else How.How =
	 * id will be used
	 * 
	 * @see lib.selenium.WebDriverService#locateElement(lib.selenium.Locators,
	 * java.lang.String)
	 */
	@Override
	public WebElement locateElement(Locators locator, String locValue) {
		try {
			switch (locator) {
			case CLASSNAME:
				return driver.findElement(By.className(locValue));
			case CSS:
				return driver.findElement(By.cssSelector(locValue));
			case ID:
				return driver.findElement(By.id(locValue));
			case LINKTETXT:
				return driver.findElement(By.linkText(locValue.trim()));
			case NAME:
				return driver.findElement(By.name(locValue));
			case XPATH:
				return driver.findElement(By.xpath(locValue));
			case PARTIALLINK:
				return driver.findElement(By.partialLinkText(locValue));
			case TAGNAME:
				return driver.findElement(By.tagName(locValue));
			case BUTTONTEXT:
				return driver.findElement(By.xpath("//button[text()='" + locValue + "']"));
			case PARTIALBUTTONTEXT:
				return driver.findElement(By.xpath("//button[contains(text(),'" + locValue + "')]"));
			default:
				break;
			}
		} catch (NoSuchElementException e) {
			throw new RuntimeException();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * lib.selenium.WebDriverService#locateMultipleElements(lib.selenium.Locators,
	 * java.lang.String)
	 */
	@Override
	public List<WebElement> locateMultipleElements(Locators locator, String locValue) {
		try {
			switch (locator) {
			case CLASSNAME:
				return driver.findElements(By.className(locValue));
			case CSS:
				return driver.findElements(By.cssSelector(locValue));
			case ID:
				return driver.findElements(By.id(locValue));
			case LINKTETXT:
				return driver.findElements(By.linkText(locValue));
			case NAME:
				return driver.findElements(By.name(locValue));
			case XPATH:
				return driver.findElements(By.xpath(locValue));
			case PARTIALLINK:
				return driver.findElements(By.partialLinkText(locValue));
			case TAGNAME:
				return driver.findElements(By.tagName(locValue));
			case BUTTONTEXT:
				return driver.findElements(By.xpath("//button[text()='" + locValue + "']"));
			default:
				break;
			}
		} catch (NoSuchElementException e) {
			throw new RuntimeException();
		}
		return null;
	}

	/**
	 * To check whether the drop down is sorted or not
	 * 
	 * @param WebElement
	 */
	public void checkDropDownSorting(WebElement source) {
		Select sel = new Select(source);
		List<WebElement> options = sel.getOptions();
		List<String> originalDropDownValues = new ArrayList<>();
		for (WebElement dd : options) {
			originalDropDownValues.add(dd.getText());
		}
		List<String> sortedValues = new ArrayList<>(originalDropDownValues);
		Collections.sort(sortedValues);
		if (originalDropDownValues.equals(sortedValues)) {
			System.out.println("matched");
			reportStep("DropDown are sorted", "pass");
		} else {
			System.out.println("not matched");
			reportStep("DropDown are not sorted", "fail");
		}
		originalDropDownValues.clear();
		sortedValues.clear();
	}

	@Override
	public void type(WebElement ele, String data) {

		try {
			ele.sendKeys(data);
		} catch (Exception e) {

		}

	}

	public void clearAndType(WebElement ele, String data) {

		try {
			driver.unregister(this);
			ele.clear();
			driver.register(this);
			ele.sendKeys(data);
		} catch (Exception e) {

		}

	}

	@Override
	public void click(WebElement ele) {
		ele.click();
	}

	public void click(Locators locator, String value) {
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(locateElement(locator, value))).click();
	}

	public void clickWithNoListener(Locators locator, String value) {
		driver.unregister(this);
		wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(locateElement(locator, value))).click();
		driver.register(this);
	}

	@Override
	public String getText(WebElement ele) {
		return ele.getText();
	}

	/**
	 * @param dropDown - DropDown.VISIBLETEXT or DropDown.VALUE
	 * @param ele      - WebElement
	 * @param value    - To select
	 */
	public void selectDropDown(DropDown dropDown, WebElement ele, String value) {
		Select sel = new Select(ele);
		switch (dropDown) {
		case VALUE:
			sel.selectByValue(value);
			reportStep(sel.getFirstSelectedOption().getText(), "pass");
			break;
		case VISIBLETEXT:
			sel.selectByVisibleText(value);
			reportStep(sel.getFirstSelectedOption().getText(), "pass");
			break;
		default:
			break;
		}

	}

	/**
	 * @param dropDown - DropDown.INDEX
	 * @param ele
	 * @param index
	 */
	public void selectDropDown(DropDown dropDown, WebElement ele, int index) {
		Select sel = new Select(ele);
		switch (dropDown) {
		case INDEX:
			sel.selectByIndex(index);
			reportStep(sel.getFirstSelectedOption().getText(), "pass");
			break;
		default:
			break;
		}

	}

	@Override
	public boolean verifyExactTitle(String expectedTitle) {
		if (driver.getTitle().equals(expectedTitle)) {
			System.out.println("title equal matched");
			return true;
		}
		return false;
	}

	@Override
	public boolean verifyPartialTitle(String expectedTitle) {
		if (driver.getTitle().contains(expectedTitle)) {
			System.out.println("title partially matched");
			return true;
		}
		return false;
	}

	@Override
	public void verifyExactText(WebElement ele, String expectedText) {
		String text = ele.getText();
		if (text.equals(expectedText)) {
			System.out.println("Expected text " + expectedText + " matched with the actual text" + text);
		} else {
			System.out.println("Expected text " + expectedText + " does not match with the actual text" + text);
		}
	}

	@Override
	public void verifyPartialText(WebElement ele, String expectedText) {
		String text = ele.getText();
		if (text.contains(expectedText)) {
			System.out.println("Expected text " + expectedText + " matched with the actual text" + text);
		} else {
			System.out.println("Expected text " + expectedText + " does not match with the actual text" + text);
		}

	}

	@Override
	public void verifyExactAttribute(WebElement ele, String attribute, String value) {
		String actualAtb = ele.getAttribute(attribute);
		if (actualAtb.equals(value)) {
			System.out.println("Attribute is matched exactly");
		} else {
			System.out.println("Attribute is not matched exactly");
		}
	}

	@Override
	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		String actualAtb = ele.getAttribute(attribute);
		if (actualAtb.equals(value)) {
			System.out.println("Attribute is partially matched");
		} else {
			System.out.println("Attribute is not matched");
		}

	}

	@Override
	public void verifySelected(WebElement ele) {
		if (ele.isSelected()) {
			System.out.println(ele + " is Selected");
		} else {
			System.out.println(ele + " is not Selected");
		}

	}

	@Override
	public boolean verifyDisplayed(WebElement ele) {
		if (ele.isDisplayed()) {
			return true;
		}
		return false;
	}

	@Override
	public void switchToWindow(int index) {
		try {
			Set<String> allwindowHandles = driver.getWindowHandles();
			List<String> list = new ArrayList<String>();
			System.out.println(list.size());
			list.addAll(allwindowHandles);
			driver.switchTo().window(list.get(index));
		} catch (IndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException");
			throw new RuntimeException();
		}

	}

	@Override
	public void switchToFrame(WebElement ele) {
		driver.switchTo().frame(ele);
	}

	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}

	public void switchToFrame(int franeNumber) {
		driver.switchTo().frame(franeNumber);
	}

	public void switchToFDefaultContent() {
		driver.switchTo().defaultContent();
	}

	@Override
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void acceptAlert(String data) {
		driver.switchTo().alert().sendKeys(data);
		driver.switchTo().alert().accept();
	}

	@Override
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}

	@Override
	public String getAlertText() {
		return driver.switchTo().alert().getText();
	}

	@Override
	public void closeActiveBrowser() {
		driver.close();

	}

	@Override
	public void closeAllBrowsers() {
		driver.quit();
	}

	public void actions(WebElement ele, String actionToePerformed) {

		Actions act = new Actions(driver);
		switch (actionToePerformed) {
		case "moveToElement":
			act.moveToElement(ele).perform();
			break;
		case "click":
			act.click(ele).perform();
			break;
		default:
			break;
		}
	}

}
