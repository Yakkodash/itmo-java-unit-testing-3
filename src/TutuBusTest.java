import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.*;
import java.io.File;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.FirefoxBinary;

public class TutuBusTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    String os = System.getProperty("os.name");
    if (os.equals("Linux")) {
        File pathToBinary = new File("/usr/bin/firefox");
        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        driver = new FirefoxDriver(ffBinary,firefoxProfile);
    }
    else {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("load-extension=C:/Users/Fedor/Documents/itmo-java-unit-testing-3/lib/uBlock0.chromium");
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability(ChromeOptions.CAPABILITY, options);
      driver = new ChromeDriver(capabilities);
    }

    baseUrl = "https://www.tutu.ru/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testTutuBus() throws Exception {
    driver.get(baseUrl + "");
    driver.findElement(By.xpath("//*[@class='l-page_wrapper']/div[4]/div/a[3]")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (driver.findElement(By.xpath("//*")).getText().matches("^[\\s\\S]*Укажите маршрут и дату поездки, чтобы узнать расписание и купить билеты на автобус[\\s\\S]*$")) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("//html/body/div[2]/div/div[2]/section/div/div[2]/form/div[1]/div[1]/input")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//html/body/div[2]/div/div[2]/section/div/div[2]/form/div[1]/div[1]/div[2]/div/div[2]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("//html/body/div[2]/div/div[2]/section/div/div[2]/form/div[1]/div[1]/div[2]/div/div[2]")).click();
    driver.findElement(By.xpath("//html/body/div[2]/div/div[2]/section/div/div[2]/form/div[3]/div[1]/input")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//html/body/div[2]/div/div[2]/section/div/div[2]/form/div[3]/div[1]/div[2]/div/div[1]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("//html/body/div[2]/div/div[2]/section/div/div[2]/form/div[3]/div[1]/div[2]/div/div[1]")).click();
    driver.findElement(By.xpath("//html/body/div[2]/div/div[2]/section/div/div[2]/form/div[4]/div[2]/a[2]/span")).click();
    driver.findElement(By.xpath("//html/body/div[2]/div/div[2]/section/div/div[2]/form/div[6]/button")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//html/body/div[2]/div/section/div/div/div/div/div[1]/div/div[4]/div[2]/div[1]/div[1]/div[5]/div[1]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("//html/body/div[2]/div/section/div/div/div/div/div[1]/div/div[4]/div[2]/div[1]/div[1]/div[5]/div[1]")).click();
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresent(By.xpath("//html/body/div[2]/div/section/div/div/div/div[3]/div[2]/div/div[2]"))) break; } catch (Exception e) {}
    	Thread.sleep(1000);
    }

  }


  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
