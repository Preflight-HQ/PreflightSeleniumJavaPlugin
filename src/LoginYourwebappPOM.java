import org.openqa.selenium.By;

public class LoginYourwebappPOM {
    private PreflightDriver driver;

    public LoginYourwebappPOM(PreflightDriver driver) {
        this.driver = driver;
    }

    public void visit() {
        driver.get("https://yourweb.app/#/login");
    }


    /**
     "Sign in to your account" Box  > "Signup" Button
     href: ? | tag: a | purpose: navigation | class: font-medium
     */
    public PreflightWebElement clickSignup() throws Exception {
        var el = driver.findElement(By.cssSelector(".max-w>a"), "pom76iioovBDJz4");
        el.click();
        return el;
    }

    /**
     "Sign in to your account" Form  > "Email address" Input
     purpose: email | id: pf-lesson-login-0 | inputType: email | tag: input | class: shadow-sm, appearance-none, block, w-full, border, rounded-md
     */
    public PreflightWebElement typeEmailAddress(String emailAddress) throws Exception {
        var el = driver.findElement(By.cssSelector("[type=\"email\"]"), "pommVl5VeA1b1uH");
        el.clear();
        el.sendKeys(emailAddress);
        return el;
    }

    /**
     "Sign in to your account" Form  > "Password" Input
     purpose: password | id: pf-lesson-login-1 | inputType: password | tag: input | class: shadow-sm, appearance-none, block, w-full, border, rounded-md
     */
    public PreflightWebElement typePassword(String password) throws Exception {
        var el = driver.findElement(By.cssSelector("[type=\"password\"]"), "pomH7hexArUNhEA");
        el.clear();
        el.sendKeys(password);
        return el;
    }

    /**
     "Sign in to your account" Form  > Checkbox
     id: remember_me | tag: input | class: rounded
     */
    public PreflightWebElement clickCheckboxOption() throws Exception {
        var el = driver.findElement(By.cssSelector(".rounded"), "pomwM1qHVS7wGGN");
        el.click();
        return el;
    }

    /**
     "Sign in to your account" Form  > "Forgot your password?" Button
     href: ? | tag: a | purpose: navigation | class: font-medium
     */
    public PreflightWebElement clickForgotYourPassword() throws Exception {
        var el = driver.findElement(By.xpath("//*[contains(text(),\"Forgot your password?\")]"), "pomNbJJ6XJteO3e");
        el.click();
        return el;
    }

    /**
     "Sign in to your account" Form  > "Sign in" Button
     purpose: submit | id: pf-lesson-login-2 | tag: button | class: text-white, flex, justify-center, border, border-transparent, rounded-md, shadow-sm, text-sm, font-medium
     */
    public PreflightWebElement clickSignIn() throws Exception {
        var el = driver.findElement(By.cssSelector(".text-white"), "pomhbcLG8yiDny9");
        el.click();
        return el;
    }

    public PreflightWebElement getLoginErrorMessage() throws Exception {
        return driver.findElement(By.cssSelector("form>.text-sm"), "pomvhyA9wAmUoDp");
    }

}