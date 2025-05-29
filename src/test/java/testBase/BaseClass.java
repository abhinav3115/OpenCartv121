package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
public static WebDriver driver;
public Logger logger;
public Properties prop;
	
	@BeforeClass(groups= {"Sanity","Regression","Master","datadriven"})
	@Parameters({"browser","os"})
	public void setup(String br,String os) throws IOException {
		
		logger=LogManager.getLogger(this.getClass());

		FileReader file=new FileReader("./src//test//resources//config.properties");
		prop=new Properties();
		prop.load(file);
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cap=new DesiredCapabilities();
			//os
		if(os.equalsIgnoreCase("windows")) {
			cap.setPlatform(Platform.WIN11);
		}
		else if(os.equalsIgnoreCase("linux"))
		{
			cap.setPlatform(Platform.LINUX);
			
		}
		else {
			System.out.println("OS MISMATCH");
		return;
		}
		//browser
		switch (br.toLowerCase()) {
		
		case "chrome": cap.setBrowserName("chrome");break;
		case "edge" : cap.setBrowserName("MicrosoftEdge");break;
		case "firefox" :driver=new FirefoxDriver();break;
		default: System.out.println("no browser found");return;
		
		}
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
		if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {
			
		
		
		switch(br.toLowerCase()) {
		case "chrome" :System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\Downloads\\chromedriver-win64 (7)\\chromedriver-win64\\chromedriver.exe");driver=new ChromeDriver();break;
		case "firefox" :driver=new FirefoxDriver();break;
		case "edge" : driver=new EdgeDriver();break;
		default : System.out.println("Invalid browser"); return;
		}
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		
		driver.get(prop.getProperty("appurl"));
		driver.manage().window().maximize();
		
	}
	@SuppressWarnings("deprecation")
	public String randomeAlphaNumberic() {
		
		return RandomStringUtils.randomAlphanumeric(10);
	}
	@SuppressWarnings("deprecation")
	public String randomeNumber() {
		return RandomStringUtils.randomNumeric(10);
	}
	@SuppressWarnings("deprecation")
	public String randomeString() {
		return RandomStringUtils.randomAlphabetic(5);
	}
	@AfterClass(groups= {"Sanity","Regression","Master","datadriven"})
	public void teardown() {
		driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	

}
