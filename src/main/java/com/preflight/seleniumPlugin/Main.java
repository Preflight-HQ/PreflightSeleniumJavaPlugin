package com.preflight.seleniumPlugin;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver","C:\\Selenium\\WebDrivers\\chromedriver.exe");
        PreflightDriver.PreflightApiKey = "OGQxM2ZmNjEtYWUxMC00OTFiLTgwNjQtYWM2ZTM4YzAxYzljOjIwYzA2YmU4LTg2MzItNGU1OS05MTc1LTNiOGQxODIxZTgxMA==";
        signUpTest();
    }

    /* check the documentation if getting errors: https://bit.ly/3LKWTXR */
    public static void signUpTest() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security");
        PreflightDriver driver = new PreflightDriver(new ChromeDriver(options));

//        var yourWebAppPom = new LoginYourwebappPOM(driver);
//        yourWebAppPom.visit();
//        yourWebAppPom.typeEmailAddress("test123@test.com");
//        yourWebAppPom.typePassword("123456");
//        yourWebAppPom.clickSignIn();
//        var errorMessage = yourWebAppPom.getLoginErrorMessage();
//        errorMessage.checkValue("Invalid email or password");


        driver.initializeAutohealTest("dUzJ8QDi7tvs");
        driver.get("https://yourweb.app/#/signup");
        driver.findElement(By.cssSelector("#pf-lesson-signup"), "2").sendKeys("{{name.firstName}}");
        driver.findElement(By.cssSelector("#pf-lesson-signup-1"), "3").sendKeys("{{name.lastName}}");
        driver.findElement(By.cssSelector("#pf-lesson-signup-2"), "4").sendKeys("{{generate.email}}");
        driver.findElement(By.cssSelector("#pf-lesson-signup-3"), "5").sendKeys("123456");
        driver.findElement(By.cssSelector(".text-white"), "6").click();
        driver.openEmail("Verify your email");
        driver.findElement(By.cssSelector("a"), "8", By.cssSelector("iframe#pf-email-iframe")).click();
        driver.closeEmail();
        driver.findElement(By.cssSelector(".leading-tight"), "10").checkValue("Email verified successfully!");

    }
}