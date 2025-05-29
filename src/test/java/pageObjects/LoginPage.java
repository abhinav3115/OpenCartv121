package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
@FindBy(xpath="//input[@id='input-email']")	
WebElement txtEmailAdress ;
@FindBy(xpath="//input[@id='input-password']")
WebElement txtPassword;
@FindBy(xpath="//input[@value='Login']")
WebElement btnLogin;

public void setEmail(String email) {
	txtEmailAdress.sendKeys(email);
}

public void setpassword(String Password) {
	txtPassword.sendKeys(Password);
}

public void clickLogin() {
	btnLogin.click();
}
	
	

}
