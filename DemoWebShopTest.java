package Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoWebShopTest {
	public static void main(String[]args) {
		WebDriver driver = new ChromeDriver();
		//Open the Demo Web Shop
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		//Search for "Laptop"
		WebElement searchbox = driver.findElement(By.id("small-searchterms"));
		searchbox.sendKeys("Laptop");
		driver.findElement(By.cssSelector("input[value='Search']")).click();
		//Validate search results
		WebElement firstProduct = driver.findElement(By.cssSelector(".product-item h2.product-title a"));
		if(firstProduct.isDisplayed()) {
			System.out.println("Search results found");
			//Click the first product
			firstProduct.click();
			//Add the product to the cart
			WebElement addToCartButton = driver.findElement(By.cssSelector("#add-to-cart-button-2"));
			addToCartButton.click();
			//Wait for cart updates
			try {
				Thread.sleep(3000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			//Verify Cart Update
			WebElement cartCount = driver.findElement(By.cssSelector("span.cart-qty"));
			if(!cartCount.getText().equals("(0)")) {
				System.out.println("Product added to Cart successfully. Cart:" + cartCount.getText());
			}else {
				System.out.println("Product addition to cart failed.");
			}
		}else {
			System.out.println("No relevant product found.");
		}
		//Close the browser
		driver.quit();
	}

}
