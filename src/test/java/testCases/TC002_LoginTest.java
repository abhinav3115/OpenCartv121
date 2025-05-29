package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	
	@Test(groups= {"Sanity","Master"})
	public void verify_login() {
		
		logger.info("******Starting TC002_loginTest ******");
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(prop.getProperty("email"));
		lp.setpassword(prop.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage mcc=new MyAccountPage(driver);
		boolean target=mcc.isMyAccountPageExists();
		
		Assert.assertTrue(target);
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		
		
	}

}
