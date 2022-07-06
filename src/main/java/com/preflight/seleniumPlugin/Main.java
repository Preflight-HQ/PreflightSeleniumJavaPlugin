package com.preflight.seleniumPlugin;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver","C:\\Selenium\\WebDrivers\\chromedriver.exe");
        PreflightDriver.PreflightApiKey = "NDZiZTRkOWUtMDljNC00YmRhLTliNTAtMzhlZjVmNDNlNDE3OjIyZmRhODc4LWNmNDAtNDU3Yy05OGI4LWE2MzEwOWQ0MmNiNg==";
        CreateAccountPreFlight();
    }
    public static void CreateAccountPreFlight() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-web-security");
        PreflightDriver driver = new PreflightDriver(new ChromeDriver(options));
        driver.initializeAutohealTest("Td8ONMndelin");
        driver.get("https://appdev.preflightdev.com/login");
        driver.findElement(By.cssSelector(".form-footer>div>a"), "2").click();
        driver.findElement(By.cssSelector(".entry-page-message>a"), "3").click();
        driver.findElement(By.xpath("//*[@placeholder='e.g. John']"), "4").sendKeys("{{name.firstName}}");
        driver.findElement(By.xpath("//*[@placeholder='e.g. S]"), "5").sendKeys("{{name.lastName}}");
        driver.findElement(By.cssSelector(".form-box>div:nth-child(3)>span>input"), "6").sendKeys("test@test.com");
        driver.findElement(By.cssSelector("[type='password']"), "7").sendKeys("test");
        driver.findElement(By.cssSelector(".data-box-content"), "8").click();
        driver.findElement(By.cssSelector(".inline-error"), "9").checkValue("Passwords must be at least 8 characters.");
    }
}