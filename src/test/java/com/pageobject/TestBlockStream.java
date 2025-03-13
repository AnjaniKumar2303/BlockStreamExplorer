package com.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;

public class TestBlockStream {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\AnjKumar\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void validateTransactions() {
        driver.get("https://blockstream.info/block/000000000000000000076c036ff5119e5a5a74df77abf64203473364509f7732");
        WebElement transactionElement = driver.findElement(By.xpath("//h3[@class='font-h3']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", transactionElement);
        String text = transactionElement.getText();
        Assert.assertEquals("25 of 2875 Transactions", text);
    }

    @Test
    public void printTransactionhash() {
        driver.get("https://blockstream.info/block/000000000000000000076c036ff5119e5a5a74df77abf64203473364509f7732");
        WebElement transactionElement = driver.findElement(By.xpath("//h3[@class='font-h3']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", transactionElement);
        List<WebElement> elementList = driver.findElements(By.id("transaction-box"));
        for(WebElement element : elementList) {
            WebElement insAndOut = element.findElement(By.className("ins-and-outs"));
            WebElement inElement = insAndOut.findElement(By.className("vin"));
            List<WebElement> inElementList = inElement.findElements(By.className("vins"));
            WebElement outElement = insAndOut.findElement(By.className("vout"));
            List<WebElement> outElementList = outElement.findElements(By.className("vouts"));
            if(inElementList.size()==1 && outElementList.size()==2) {
                WebElement transactionHash = element.findElement(By.xpath("//div[@class='txn font-p2']"));
                System.out.println(transactionHash.getText());
            }
        }
    }

    @BeforeTest
    public void tearDown() {
        driver.close();
    }

}
