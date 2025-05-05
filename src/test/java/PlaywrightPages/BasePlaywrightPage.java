package PlaywrightPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class BasePlaywrightPage {
    protected Page page;

    Locator subTitle;
    Locator copyrightText;
    Locator developersPageLink;

    public BasePlaywrightPage(Page page){
        this.page = page;
        this.subTitle = page.locator(".display-6");
        this.copyrightText = page.locator("xpath=//span[@class='text-muted' and normalize-space(text())='Copyright © 2021-2025']");
        this.developersPageLink = page.locator("xpath=//a[@href='https://bonigarcia.dev/']");
    }

    @Step("get current URL")
    public String getCurrentURL (){
        return page.url();
    }

    @Step ("get subtitle")
    public String getSubtitleText(){
        return subTitle.innerText();
    }

    @Step("get text of copyright part")
    public String getCopyrightText(){
        return copyrightText.innerText();
    }

    @Step("click to link, that leads to bonigarcia")
    public void getDevelopersPageLink(){
        developersPageLink.evaluate("el => el.click()");
    }
}
