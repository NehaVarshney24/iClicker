import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class StudentCreateAccount {
    WebDriver driver;

    @BeforeClass
    public void before() {
        System.setProperty("webdriver.chrome.driver", "D:\\Neha Workspace\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.iclicker.com");
    }

    @AfterClass
    public void After() {
        driver.quit();
    }

    @Test(enabled = false, priority = 1)
    public void SignIn() {
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.cssSelector("#modal-sign-in > div > ul > li:nth-child(2) > a")).click();

    }

    @Test(enabled = false, priority = 2)
    public void switchToWindow() {
        String MainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        while (it.hasNext()) {
            String ChildWindow = it.next();
            driver.switchTo().window(ChildWindow);
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.findElement(By.id("userEmail")).sendKeys("nehaqa@reef-education.com");
                driver.findElement(By.id("userPassword")).sendKeys("Qait@12345");
                driver.findElement(By.id("sign-in-button")).submit();
                break;
            }
        }
    }

    @Test(enabled = false, priority = 3)
    public void addCourse() {
        driver.findElement(By.cssSelector("css,button[ng-click=\"routes.addCourse()\"]")).click();
        Assert.assertEquals("https://app.reef-education.com/#/courses", "https://app.reef-education.com/#/courses");
    }

    @Test(priority = 4)
    public void createAccount() {
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Create an Account")).click();
        driver.findElement(By.className("student")).click();
    }

    @Test(priority = 5)
    public void findInstitution() {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        WebDriverWait wait=new WebDriverWait(driver, 20);
        String MainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        while (it.hasNext()) {
            String ChildWindow = it.next();
            driver.switchTo().window(ChildWindow);
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.findElement(By.cssSelector("#searchInput")).sendKeys("REEF Education");
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//*[@id=\"-search-results\"]/li/button"))).click();
                driver.findElement(By.cssSelector("button[ng-click=\"pickInstitution()\"]")).click();
                break;
            }
        }
    }

    @Test(priority = 6)
    public void signUp()
    {
       driver.findElement(By.id("firstName")).sendKeys("Neha");
       driver.findElement(By.id("lastName")).sendKeys("Varshney");
       driver.findElement(By.id("email")).sendKeys("abbwc@reef-education.com");
       driver.findElement(By.id("studentAgreement")).click();
       driver.findElement(By.cssSelector("button[ng-click=\"validationFirstStep()\"]")).click();

       driver.findElement(By.id("password")).sendKeys("Neh@1234");
       driver.findElement(By.id("confirmPassword")).sendKeys("Neh@1234");
       driver.findElement(By.cssSelector("button[ng-click=\"createAccount()\"]")).click();

    }

    @Test(priority = 7,dependsOnMethods = {"signUp"})
    public void signInWhenAccountCreated() throws Exception
    {
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button[ng-click=\"toLoginPage()\"]")).click();
        driver.findElement(By.id("userEmail")).sendKeys("abbwc@reef-education.com");
        driver.findElement(By.id("userPassword")).sendKeys("Neh@1234");
        driver.findElement(By.id("sign-in-button")).submit();

    }

}
