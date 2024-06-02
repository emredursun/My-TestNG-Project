package techproed.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {
    // Create a private static WebDriver object
    private static WebDriver driver;

    // Private constructor to prevent instantiation
    private Driver() {
    }

    // Create getDriver method to create and initiate the driver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            String browserType = ConfigReader.getProperty("browser").toLowerCase(); // Ensure browser type is case-insensitive
            switch (browserType) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless"); // Correctly configure Chrome in headless mode
                    driver = new ChromeDriver(options);
                    break;
                default:
                    throw new IllegalStateException("Unsupported browser type: " + ConfigReader.getProperty("browser"));
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    } // getDriver ends here

    // Create a closeDriver method to close the driver
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
