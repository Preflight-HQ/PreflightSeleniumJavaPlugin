import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class PreflightDriver implements WebDriver {
	public static String PreflightApiKey = null;
	private final PreflightPluginService _pfPluginService;
	private final PreflightLogger _logger;
	public WebDriver _driver;
	private String _currentTestId = null;

	public PreflightDriver(WebDriver driver) throws PreflightApiKeyMissingException {
		this(driver, PreflightDriver.PreflightApiKey, new PreflightLogger());
	}

	public PreflightDriver(WebDriver driver, String preflightApiKey) throws PreflightApiKeyMissingException {
		this(driver, preflightApiKey, new PreflightLogger());
	}

	public PreflightDriver(WebDriver driver, PreflightLogger logger) throws PreflightApiKeyMissingException {
		this(driver, PreflightDriver.PreflightApiKey, logger);
	}

	public PreflightDriver(WebDriver driver, String preflightApiKey, PreflightLogger logger) throws PreflightApiKeyMissingException {
		if(preflightApiKey == null) {
			throw new PreflightApiKeyMissingException("PreflightApiKey not set. Please set it in constructor parameter or as static property like 'PreflightDriver.PreflightApiKey = YOUR_API_KEY'");
		}
		_driver = driver;
		_logger = logger;
		_pfPluginService = new PreflightPluginService(driver, preflightApiKey, _logger);
	}

	public void openEmail(String subject) throws PreflightException {
		_pfPluginService.openEmail(subject);
	}

	public void closeEmail() throws Exception {
		_pfPluginService.closeEmail();
	}

	public String replaceVariables(String input) {
		try {
			return _pfPluginService.replaceVariables(input);
		} catch (Exception e) {
			_logger.error("Unable to replace variable in string " + input, e);
		}
		return input;
	}
	
	public void initializeAutohealTest(String testId) {
		_currentTestId = testId;
	}

	public PreflightWebElement findElement(By by, String elementId) throws Exception {
		return findElement(by, elementId, null);
	}

	public PreflightWebElement findElement(By by, String elementId, By byIframeSelector) throws Exception {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {

		}

		if(byIframeSelector != null) {
			var iFrameWebElement = seleniumFindElement(byIframeSelector);
			if(iFrameWebElement == null) {
				throw new PreflightTestFailException("iFrame not found with selector " + byIframeSelector.toString());
			}
			_driver.switchTo().frame(iFrameWebElement);
		}
		var webElement = seleniumFindElement(by);
		if(webElement != null) {
			return new PreflightWebElement(this, webElement, _logger);
		}

		_logger.log("Element not found by " + by);
		_logger.log("Applying autoheal.");
		var selector = getSelectorFromBy(by);
		var result = _pfPluginService.findElement(selector, elementId, _currentTestId);
		if(result == null) {
			throw new PreflightTestFailException("Element not found with autoheal data.");
		}
		if(result.isFoundByAutoheal) {
			_logger.log("Autoheal successful. Found element: " + result.elementSimplePath);
			_logger.log("Please update selector from '" + selector + "' to '" + result.selector + "'");
		} else {
			_logger.log("Element fond without autoheal data. Please check if element was on page when you searched it.");
		}
		return new PreflightWebElement(this, result.webElement, _logger);
	}

	@Override
	public void close() {
		_driver.close();
	}
	
	@Override
	public WebElement findElement(By by) {
		return _driver.findElement(by);
	}

	@Override
	public List<WebElement> findElements(By by) {
		return _driver.findElements(by);
	}

	@Override
	public void get(String url) {
		_driver.get(url);
		new WebDriverWait(_driver, Duration.ofSeconds(10)).until(
				webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

		}
	}

	@Override
	public String getCurrentUrl() {
		return _driver.getCurrentUrl();
	}

	@Override
	public String getPageSource() {
		return _driver.getPageSource();
	}

	@Override
	public String getTitle() {
		return _driver.getTitle();
	}

	@Override
	public String getWindowHandle() {
		return _driver.getWindowHandle();
	}

	@Override
	public Set<String> getWindowHandles() {
		return _driver.getWindowHandles();
	}

	@Override
	public Options manage() {
		return _driver.manage();
	}

	@Override
	public Navigation navigate() {
		return _driver.navigate();
	}

	@Override
	public void quit() {
		_driver.quit();		
	}

	@Override
	public TargetLocator switchTo() {
		return _driver.switchTo();
	}

	private String getSelectorFromBy(By by) throws PreflightException {
		Pattern r = Pattern.compile(": ([^\\s]+)");
		var m = r.matcher(by.toString());
		if(m.find()) {
			return m.group(1);
		}
		throw new PreflightException("Unable to parse selector");
	}
	private WebElement seleniumFindElement(By by) {
		try {
			return this.findElement(by);
		}
		catch(Exception ex) {
			_logger.warning("Element not found by selenium. " + ex.toString());
		}
		return null;
	}

}
