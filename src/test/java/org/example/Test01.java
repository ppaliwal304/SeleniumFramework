package org.example;

import java.time.Duration;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

    public class Test01 {

        WebDriver driver;
        @BeforeTest
        public void browser(){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
        }
        @Description("Perform Actions select city from dropdown list")
        @Test
        public void makemytrip() throws InterruptedException{
            driver.get("https://www.makemytrip.com");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-cy=\"closeModal\"]"))).click();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //driver.findElement(By.xpath("//span[@data-cy=\"closeModal\"]")).click();

            WebElement moveElememt =driver.findElement(By.id("fromCity"));
            Actions actions = new Actions(driver);
            actions.moveToElement(moveElememt).click().perform();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            actions.sendKeys("New Delhi").perform();

            actions.keyDown(Keys.ARROW_DOWN).keyDown(Keys.ENTER).build().perform();
            String currenturl = driver.getCurrentUrl();
            Assert.assertTrue(currenturl.contains("https://www.makemytrip.com/"));
            String pageTitle = driver.getTitle();
            Assert.assertTrue(pageTitle.contains("MakeMyTrip"), "Page title is incorrect!");
            System.out.println(pageTitle);
         //driver.quit();
        }
    }

