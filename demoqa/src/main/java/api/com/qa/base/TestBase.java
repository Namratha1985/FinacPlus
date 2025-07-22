package api.com.qa.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.restassured.RestAssured;

public class TestBase {

    public static String userId;
    public static Properties prop;
    
    /**
	 * This method is used to initialize the driver on the basis of given browser
	 * 
	 * @param browser
	 * @return This returns the driver
	 */
    
	public TestBase() {
			
			try {
				prop = new Properties();
				InputStream fis = getClass().getClassLoader().getResourceAsStream("com/qa/properties/config.properties");
				prop.load(fis);
				
			}catch(IOException e) {
				e.getMessage();
			}
	}
	    
    public void setup() {
        RestAssured.baseURI = prop.getProperty("serviceurl");
    }
}

