# Preflight Selenium autoheal plugin 

## Create an account on [Preflight.com](https://app.preflight.com/get-started?afmc=cypressPlugin)
- The API key will be generated for you automatically after creating account.
- Alternatively you can go to [Account Settings / API](https://app.preflight.com/account/api) to generate a new one.

## Download JAR file and add it to project dependencies
- You can find the [JAR file here](https://github.com/Preflight-HQ/PreflightSeleniumJavaPlugin/raw/main/build/libs/PreflightSeleniumJavaAutohealPlugin-1.0.jar)

## Set API key in your test environment
- You can set key in code like  
```
public static void main(String[] args) throws Exception {
    PreflightDriver.PreflightApiKey = [YOUR_API_KEY];
}
```

## Creating test
- Once you have the plugin installed and set up with the API key you can create your first test.
- Download Chrome extension [Recorder from Preflight](https://chrome.google.com/webstore/detail/lpfigbkckbojbjnmhapmfekljbhclhhj)
- Log in and follow instructions on the video below to create your first test.

<img src="https://preflightuploads.blob.core.windows.net/uploads/PreflightCypressCodeGenerator.gif" alt="Create test">

### Generated code
- Please note that in the current version **you need to disable web security ("--disable-web-security")** in the driver for all functionality to work. 
```
    public static void signUpTest() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security");
        PreflightDriver driver = new PreflightDriver(new ChromeDriver(options));
        
        driver.initializeAutohealTest("dUzJ8QDi7tvs");
        driver.get("https://yourweb.app/#/signup");
        driver.findElement(By.cssSelector("#pf-lesson-signup-0"), "2").sendKeys("{{name.firstName}}");
        driver.findElement(By.cssSelector("#pf-lesson-signup-1"), "3").sendKeys("{{name.lastName}}");
        driver.findElement(By.cssSelector("#pf-lesson-signup-2"), "4").sendKeys("{{generate.email}}");
        driver.findElement(By.cssSelector("#pf-lesson-signup-3"), "5").sendKeys("123456");
        driver.findElement(By.cssSelector(".text-white"), "6").click();
        driver.openEmail("Verify your email");
        driver.findElement(By.cssSelector("a"), "8", By.cssSelector("iframe#pf-email-iframe")).click();
        driver.closeEmail();
        driver.findElement(By.cssSelector(".leading-tight"), "10").checkValue("Email verified successfully!");

    }
```
