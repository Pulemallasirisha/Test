package Fitpeo;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Assignment {

	public static void main(String[] args) {
		 WebDriver driver = new ChromeDriver();
	        
	        // Step 1: Navigate to FitPeo Homepage
	        driver.get("https://www.fitpeo.com/");
	        driver.manage().window().maximize();
	        
	        // Step 2: Navigate to the Revenue Calculator Page
	        WebElement revenueCalculatorLink = driver.findElement(By.xpath("//div[contains(text(),'Revenue Calculator')]"));
	        revenueCalculatorLink.click();
	        
	        Thread.sleep(3000);
	        
	        // Step 3: Scroll to the Slider Section
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0,200)");
	        
	        // Step 4: Locate the slider thumb element
	        WebElement sliderThumb = driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-thumb')]"));
	        WebElement sliderInput = driver.findElement(By.xpath("//input[@type='range']")); // Hidden range input
	        
	        // Step 5: Get slider properties
	        int min = Integer.parseInt(sliderInput.getAttribute("aria-valuemin")); // Get min value
	        int max = Integer.parseInt(sliderInput.getAttribute("aria-valuemax")); // Get max value
	        int step = Integer.parseInt(sliderInput.getAttribute("step")); // Get step value (if defined)
	        int currentValue = Integer.parseInt(sliderInput.getAttribute("aria-valuenow")); // Current value
	        int targetValue = 820; // Desired value
	        
	        // Adjust targetValue to match the nearest step
	        targetValue = Math.round((float) targetValue / step) * step;
	        
	        // Calculate the movement in pixels
	        WebElement sliderTrack = driver.findElement(By.xpath("//span[contains(@class, 'MuiSlider-rail')]"));
	        int sliderWidth = sliderTrack.getSize().getWidth(); // Track width in pixels
	        
	        // Calculate percentage to move
	        double percentageToMove = (double) (targetValue - currentValue) / (max - min);
	        int pixelsToMove = (int) (percentageToMove * sliderWidth);
	        System.out.println("Pixels to move: " + pixelsToMove);
	        
	        // Step 6: Use Actions to drag the slider thumb
	        Actions actions = new Actions(driver);
	        actions.clickAndHold(sliderThumb).moveByOffset(pixelsToMove, 0).release().perform();
	        
	        Thread.sleep(2000); // Wait for UI to reflect the change
	        
	        // Step 7: Verify if the value is updated
	        String updatedValue = sliderInput.getAttribute("aria-valuenow");
	        System.out.println("Updated slider value: " + updatedValue);
	        
	        // Step 8: Update the text field to 560
	        WebElement textField = driver.findElement(By.xpath("//input[@type='number']"));
	        textField.clear(); // Clear the current value
	        Thread.sleep(1000); // Allow some delay for any page actions to complete
	        
	        // Set the value using JavaScript
	        js.executeScript("arguments[0].value = '560';", textField);
	        System.out.println("Entered value in the text field: 560");
	        
	        // Trigger input and change events to ensure synchronization
	        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", textField);
	        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", textField);
	        
	        Thread.sleep(2000); // Wait for the UI to reflect the changes
	        
	        // Step 9: Validate the Slider Value after text field update
	        String sliderValueAfterTextUpdate = sliderInput.getAttribute("aria-valuenow");
	        if (sliderValueAfterTextUpdate.equals("560")) {
	            System.out.println("Slider successfully moved to position 560 after updating the text field.");
	        } else {
	            System.out.println("Slider value mismatch. Current value: " + sliderValueAfterTextUpdate);
	        }
	        
	        // Step 10: Scroll down further to make checkboxes visible
	        js.executeScript("window.scrollBy(0,700);"); // Adjust the scroll height if needed
	        
	        
	        List<String> cptCodes = new ArrayList<String>();
	        cptCodes.add("99091");
	        cptCodes.add("99453");
	        cptCodes.add("99454");
	        cptCodes.add("99474");
	        
	        List<WebElement> allCheckboxes = driver.findElements(By.xpath("//span[contains(@class, 'MuiButtonBase')]"));
	        
	        // Select the first four checkboxes
	        for (int i = 0; i < 4; i++) {
	            WebElement checkbox = allCheckboxes.get(i);
	            System.out.println(checkbox);
	 
	            if (!checkbox.isSelected()) {
	                checkbox.click(); // Select the checkbox
	                System.out.println("Checkbox " + (i + 1) + " selected.");
	            } else {
	                System.out.println("Checkbox " + (i + 1) + " is already selected.");
	            }
	       }
	        
	     // Step 11: Verify the header value
	        
	        
	        
	        Thread.sleep(3000);
	        WebElement header = driver.findElement(By.xpath("//div[@class='MuiToolbar-root MuiToolbar-gutters MuiToolbar-regular css-1lnu3ao']//p[4]"));
	        
	        String headerText = header.getText().trim(); // Remove leading and trailing whitespace
	        headerText = headerText.replace("\n", "").replace("\r", ""); // Remove any line breaks
	        
	        String expectedValue = "$139,536";

	        if (headerText.contains(expectedValue)) {
	            System.out.println("Header value is correct: " + headerText);
	        } else {
	            System.out.println("Header value is incorrect. Actual value: " + headerText);
	        }
	        

	}

}
