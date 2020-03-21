package Page_Class_ForTC;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login_Page_ObjectRepository {

public WebElement iconProfile(WebDriver driver){
	return driver.findElement(By.id("i-icon-profile"));
	
}

public WebElement signInLink(WebDriver driver){
	 return driver.findElement(By.id("signInLink"));
}

public WebElement clickGoogleButton(WebDriver driver){
	return driver.findElement(By.xpath("//*[@id='customBtn']"));
}

public WebElement usernameTextBox(WebDriver driver){
	return driver.findElement(By.id("identifierId"));
}

public WebElement nextBtninUserNamePage(WebDriver driver){
	return driver.findElement(By.xpath("//*[@id='identifierNext']/span"));
}

public WebElement passwordtextbox(WebDriver driver){
	return driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input"));
}

public WebElement clicknextBtninPwdPage(WebDriver driver){
	return driver.findElement(By.xpath("//*[@id='passwordNext']/span/span"));
}

public WebElement closetheIFrameWindow(WebDriver driver){
	return driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/div/div[2]/i"));
}

public WebElement fromPlace(WebDriver driver){
	return driver.findElement(By.id("src"));
}

public WebElement toPlace(WebDriver driver){
	return driver.findElement(By.id("dest"));
}

public WebElement clicktheDestContainsTEXT(WebDriver driver){
	return driver.findElement(By.xpath("//li[contains(text(),'Madiwala, Bangalore')]"));
}

public WebElement clickOnwardbutton(WebDriver driver){
	return driver.findElement(By.xpath("//*[@id='search']/div/div[3]/div/label"));
}

public WebElement picktheDayinDatePickerforOnward(WebDriver driver){
	return driver.findElement(By.xpath("//*[@id='rb-calendar_onward_cal']/table/tbody/tr[6]/td[5]"));
}

public WebElement clickDestbutton(WebDriver driver){
	return driver.findElement(By.xpath("//*[@id='search']/div/div[4]/div/label"));
}

public WebElement picktheDayinDatePickerforDest(WebDriver driver){
	return driver.findElement(By.xpath("//*[@id='rb-calendar_return_cal']/table/tbody/tr[6]/td[1]"));
}

public WebElement SearchButtonInMainPage(WebDriver driver){
	return driver.findElement(By.id("search_btn"));
}

















}
