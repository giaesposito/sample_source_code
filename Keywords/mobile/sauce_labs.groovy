package mobile

import org.openqa.selenium.MutableCapabilities

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver



public class sauce_labs {
	
	/**
	 * Method Name: start_demo_app
	 *
	 * Description: sets the capabilities to start the requested demo application on the device and os of choice in sauce labs
	 *
	 * @param os_name - (String, required) - 'iOS' or 'Android' os
	 * @param app_id - (String, required) - ID of the application uploaded to sauce labs
	 * @param device_name - (String, required) - Name of the exact device you want to run a test on
	 * @param os_version - (String, required) - The version of the os you want to run on
	 * @param test_case_name - (String, required) - The name of the test case as it will be uploaded in sauce labs
	 *
	 * Return: void
	 */

	public void start_device(String os_name, String app_id, String device_name, String os_version, String test_case_name) {

		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("platformName", os_name);
		caps.setCapability("appium:app", app_id);
		caps.setCapability("appium:deviceName", device_name);
		caps.setCapability("appium:deviceOrientation", "portrait");
		caps.setCapability("appium:platformVersion", os_version);
		caps.setCapability("appium:automationName", "CPU and Memory Build");
		caps.setCapability("noReset", true);
		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("username", System.getenv('sauce_labs_username'));
		sauceOptions.setCapability("accessKey", System.getenv('sauce_labs_key'));
		sauceOptions.setCapability("build", "CPU and Memory Build");
		sauceOptions.setCapability("name", test_case_name);
		caps.setCapability("sauce:options", sauceOptions);

		URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");

		if (os_name == 'iOS') {
			caps.setCapability("appium:automationName", "XCUITest");
			IOSDriver driver = new IOSDriver(url, caps);
			MobileDriverFactory.setDriver(driver)
		}

		if (os_name == 'Android') {
			caps.setCapability("appium:automationName", "UiAutomator2");
			AndroidDriver driver = new AndroidDriver(url, caps);
			MobileDriverFactory.setDriver(driver)
		}
	}
}
