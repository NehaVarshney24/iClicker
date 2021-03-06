import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class InstructorSignIn {
    WebDriver driver;

    @BeforeClass
    public void before() {
        System.setProperty("webdriver.chrome.driver", "D:\\Neha Workspace\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.iclicker.com");
        driver.manage().window().maximize();
    }

    @AfterClass
    public void After() {
//        driver.quit();
    }

    @Test(priority = 1)
    public void SignIn() {
        driver.findElement(By.linkText("Sign In")).click();
        driver.findElement(By.cssSelector("#modal-sign-in > div > ul > li:nth-child(1) > a")).click();

    }

    @Test(enabled = false,priority = 2)
    public void signUp()
    {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        String MainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        while (it.hasNext()) {
            String ChildWindow = it.next();
            driver.switchTo().window(ChildWindow);
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.findElement(By.id("login-signup-link")).click();
                break;
            }
        }
    }

    @Test(priority = 3)
    public void logInIdPassword()
    {
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        String MainWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        while (it.hasNext()) {
            String ChildWindow = it.next();
            driver.switchTo().window(ChildWindow);
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("nehaqa@reef-education.com");
                driver.findElement(By.id("password")).sendKeys("Qait@12345");
                driver.findElement(By.className("btn")).click();
                break;
            }
        }
    }

    @Test(enabled = false ,priority = 4)
    public void signOut() throws Exception
    {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"user-dropdown\"]/img")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button[ng-click=\"profileDropdown.logout()\"]")).click();
    }

    @Test(enabled=true,priority = 5)
    public void createCourse() throws Exception
    {
        Thread.sleep(5000);
        driver.findElement(By.id("open-create-course-modal-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("button[ng-click=\"createCourse.cancel()\"]")).click();
    }

}
