import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.file.Files;
import java.nio.file.Path;

public class PreflightPluginService {
    private WebDriver _driver;
    private String _preflightApiKey;
    private String _currentStore;

    public PreflightPluginService(WebDriver driver, String preflightApiKey) {
        _driver = driver;
        _preflightApiKey = preflightApiKey;
    }

    private void initializeScript() throws PreflightException {
        try {
            JavascriptExecutor js = (JavascriptExecutor) _driver;
            try {
                js.executeScript("return pfAutohealInitialized");
                applyStore();
            } catch(Exception e) {
                Path filePath = Path.of("src\\AutohealScript.js");
                String script = Files.readString(filePath);
                js.executeScript(script);
                js.executeScript("return preflightAutoheal.initialize(arguments[0])", _preflightApiKey);
                saveStore();
            }
        } catch (Exception e) {
            throw new PreflightException("Unable to initialize script on page");
        }
    }

    public WebElement findElement(String selector, String elementId, String testId) throws PreflightException {
        initializeScript();
        WebElement we = (WebElement) executeAsyncScript("return preflightAutoheal.findElement(arguments[4], arguments[0], arguments[1], arguments[2], arguments[3])", selector, elementId, "NonameTest", testId);
        return we;
    }

    public String replaceVariables(String input) throws PreflightException {
        initializeScript();
        String result = (String) executeAsyncScript("return preflightAutoheal.replaceVariables(arguments[1], arguments[0])", input);
        saveStore();
        return result;
    }

    public void openEmail(String subject) throws PreflightException {
        initializeScript();
        Object result = executeAsyncScript("return preflightAutoheal.openEmail(arguments[1], arguments[0])", subject);
        if(result == null || !((boolean)result)){
            throw new PreflightTestFailException("Email with subject " + subject + " was not found in timeout.");
        }
    }

    public void closeEmail() throws PreflightException {
        initializeScript();
        _driver.switchTo().defaultContent();
        executeAsyncScript("return preflightAutoheal.closeEmail(arguments[0])");
    }

    public void saveStore() {
        _currentStore = (String) executeScript("return preflightAutoheal.getStore()");
    }

    public void applyStore() {
        if(_currentStore == null) {
            return;
        }
        executeScript("preflightAutoheal.setStore(arguments[0])", _currentStore);
    }

    public Object executeAsyncScript(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        Object result = null;
        try {
            result = js.executeAsyncScript(script, args);

        } catch(Exception e) {
            result = null;
        }

        return result;
    }

    public Object executeScript(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) _driver;
        Object result = null;
        try {
            result = js.executeScript(script, args);

        } catch(Exception e) {
            result = null;
        }

        return result;
    }
}
