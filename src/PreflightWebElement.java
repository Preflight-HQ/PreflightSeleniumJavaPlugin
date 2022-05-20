import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

public class PreflightWebElement implements WebElement {

	private PreflightDriver _pfDriver = null;
	public WebElement webElement = null;

	public PreflightWebElement(PreflightDriver pfDriver, WebElement element) {
		_pfDriver = pfDriver;
		webElement = element;
	}

	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		return webElement.getScreenshotAs(target);
	}

	public void checkValue(String expectedValue) throws PreflightException {
		if(expectedValue == null){
			throw new PreflightException("Checkpoint expected value cannot be null");
		}
		expectedValue = _pfDriver.replaceVariables(expectedValue).toLowerCase();
		var possibleValues = new ArrayList<String>();
		var innerText = webElement.getAttribute("innerText");
		if(innerText != null) {
			innerText = innerText.toLowerCase();
			possibleValues.add(innerText);
			for (String s: innerText.split("\n")){
				if(s != null && !s.isEmpty()){
					possibleValues.add(s.toLowerCase());
				}
			}
		}

		if(webElement.getTagName().equals("input") || webElement.getTagName().equals("textarea")){
			var value = webElement.getAttribute("value");
			if(value != null) {
				possibleValues.add(value.toLowerCase());
			}
		}

		if(!possibleValues.contains(expectedValue)){
			throw new PreflightTestFailException("Checkpoint value not match element value");
		}
	}

	@Override
	public void clear() {
		webElement.clear();
	}

	@Override
	public void click() {		
		webElement.click();
	}

	@Override
	public WebElement findElement(By by) {
		return webElement.findElement(by);
	}

	@Override
	public List<WebElement> findElements(By by) {
		return webElement.findElements(by);
	}

	@Override
	public String getAttribute(String name) {
		return webElement.getAttribute(name);
	}

	@Override
	public String getCssValue(String propertyName) {
		return webElement.getCssValue(propertyName);
	}

	@Override
	public Point getLocation() {
		return webElement.getLocation();
	}

	@Override
	public Rectangle getRect() {
		return webElement.getRect();
	}

	@Override
	public Dimension getSize() {
		return webElement.getSize();
	}

	@Override
	public String getTagName() {
		return webElement.getTagName();
	}

	@Override
	public String getText() {
		return webElement.getText();
	}

	@Override
	public boolean isDisplayed() {
		return webElement.isDisplayed();
	}

	@Override
	public boolean isEnabled() {
		return webElement.isEnabled();
	}

	@Override
	public boolean isSelected() {
		return webElement.isSelected();
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		CharSequence[] newKeysToSend = new CharSequence[keysToSend.length];
		for(int i=0; i < keysToSend.length; i++) {
			String inputString = keysToSend[i].toString();
			if(inputString.contains("{{") && inputString.contains("}}")) {
				newKeysToSend[i] = _pfDriver.replaceVariables(inputString);
			} else {
				newKeysToSend[i] = keysToSend[i];
			}
		}
		webElement.sendKeys(newKeysToSend);
	}

	@Override
	public void submit() {
		webElement.submit();
	}

	public void attachFile(String filePath) {
		webElement.sendKeys(filePath);
	}
}
