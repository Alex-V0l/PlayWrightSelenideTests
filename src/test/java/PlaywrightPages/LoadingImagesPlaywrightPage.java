package PlaywrightPages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

public class LoadingImagesPlaywrightPage extends BasePlaywrightPage{
    Locator loadingImagesLink;
    Locator appearingImages;
    Locator spinner;
    Locator resultText;

    public LoadingImagesPlaywrightPage(Page page) {
        super(page);
        this.loadingImagesLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Loading images"));
        this.appearingImages = page.locator("#image-container").getByRole(AriaRole.IMG);
        this.spinner = page.locator("#spinner");
        this.resultText = page.locator("#text");
    }

    @Step("get amount of images")
    public int getAmountOfAppearingImages(){
        return appearingImages.count();
    }

    @Step("get appearing images")
    public Locator getAllAppearingImages(){
        return appearingImages;
    }

    @Step("get loading images page")
    public void goToLoadingImagesPage(){
        loadingImagesLink.click();
    }

    @Step("check that spinner is visible")
    public boolean isSpinnerVisible(){
        return spinner.isVisible();
    }

    @Step("get text after loading all images")
    public String getTextAfterLoading(){
        return resultText.innerText();
    }

    @Step("get alt of a current image from the page")
    public String getAltOfAppearedImage(String imageID){
        return page.locator("#"+imageID).getAttribute("alt");
    }

    @Step("get alt of an image from collection")
    public String getAltFromImageOfCollection(int expectedIndex){
        return appearingImages.nth(expectedIndex).getAttribute("alt");
    }
}
