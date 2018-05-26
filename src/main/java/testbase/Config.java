package testbase;

import java.util.Properties;

public class Config extends base{
	
	private Properties prop;
	
	public Config(Properties prop){
		this.prop = prop;
	}
	public String getUserName() {
		return prop.getProperty("Username");
	}

	public String getPassword() {
		return prop.getProperty("Password");
	}

	public String getUrl() {
	return prop.getProperty("url");
		//return prop.getProperty("sauceurl");
	}

	public int getPageLoadTimeOut() {
		return Integer.parseInt(prop.getProperty("PageLoadTimeOut"));
	}

	public int getImplicitWait() {
		return Integer.parseInt(prop.getProperty("ImplcitWait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(prop.getProperty("ExplicitWait"));
	}

	
	public String getBrowser() {
		return prop.getProperty("browser");
	}

}
