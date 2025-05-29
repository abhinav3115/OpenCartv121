package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;


public class TC001_AccountRegistrationTest extends BaseClass {
	@Test(groups= {"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("****** Starting TC001_AccountRegistrationTest**** ");
		try {
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on my Account link");
			hp.clickRegister();
			logger.info("Clicked on Register link");
			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString()+"@gmail.com");// randomly generated the email
			regpage.setTelephone(randomeNumber());

			String password=randomeAlphaNumberic();
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);

			regpage.setPrivacyPolicy();
			regpage.clickContinue();
			logger.info("clicked on Continue");

			String confmsg = regpage.getConfirmationMsg();
			if(confmsg.equals("Your Account Has Been Created!")){
				Assert.assertTrue(true);			
			}
			else {
				logger.error("Test failed: ");
				Assert.assertTrue(false);
		}
		}
		catch(Exception e) {
			Assert.fail();
		}

		//logger.info("***** finished TC001_AccountRegistrationTest ******" );




	}






}