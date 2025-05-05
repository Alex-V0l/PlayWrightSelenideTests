package SelenidePages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Selenide.$;

public class BaseSelenidePage {

    @Step("get current url")
    public String getCurrentURL(){
        return WebDriverRunner.url();
    }

    @Step ("get subtitle")
    public String getSubtitleText(){
        return $("h1.display-6").text();
    }

    @Step("get text of copyright part")
    public String getCopyrightText(){
        return $(By.xpath("//span[@class='text-muted' and normalize-space(text())='Copyright © 2021-2025']")).text();
    }

    @Step("click to link, that leads to bonigarcia")
    public void getDevelopersPageLink(){
        SelenideElement devpglink = $(By.xpath("//a[@href='https://bonigarcia.dev/']"));
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("arguments[0].click();", devpglink);
    }
}
