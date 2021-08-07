package com.example.spaceapp.selenium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpaceAppGuiTest {
    @LocalServerPort
    private int port;

    private WebDriver driver;

    private String base;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        this.base = "http://localhost:" + port;
    }

    @Test
    public void test() {
        driver.get(base + "/gui/master/getAll");
        Assert.assertNotNull(driver.getCurrentUrl());
    }
}
