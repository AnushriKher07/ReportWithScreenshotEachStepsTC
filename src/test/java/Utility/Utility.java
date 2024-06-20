package Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {
	public static String captureScreenshot(WebDriver driver) {
        /*File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "./Screenshots/" + System.currentTimeMillis() + ".png";
        try {
            org.apache.commons.io.FileUtils.copyFile(screenshotFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotPath;
    }*/
		{
			TakesScreenshot ts=(TakesScreenshot) driver;

			File src=ts.getScreenshotAs(OutputType.FILE);

			String path=System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()+".png";

			File destination=new File(path);

			try
			{
			FileUtils.copyFile(src, destination);
			} catch (IOException e)
			{
			System.out.println("Capture Failed "+e.getMessage());
			}

			return path;
			}


}
}
