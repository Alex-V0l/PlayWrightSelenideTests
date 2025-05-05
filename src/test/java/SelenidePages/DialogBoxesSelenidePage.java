package SelenidePages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DialogBoxesSelenidePage extends BaseSelenidePage{

    @Step("click on dialog boxes button on home page")
    public void clickDialogBoxesLink(){
        $(By.xpath("//a[@class='btn btn-outline-primary mb-2' and @href='dialog-boxes.html']")).click();
    }

    @Step("click on launch alert button")
    public void clickLaunchAlertButton(){
        $("#my-alert").click();
    }

    @Step("get alert's text")
    public String getAlertsText(){
        Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
        return alert.getText();
    }

    @Step("accept alert")
    public void acceptAlert(){
        Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
        alert.accept();
    }

    @Step("switch to alert")
    public void switchToAlert(){
        WebDriverRunner.getWebDriver().switchTo().alert();
    }

    @Step("click on launch confirm button")
    public void clickLaunchConfirm(){
        $("#my-confirm").click();
    }

    @Step("get message after confirm")
    public String getMessageAfterConfirm(){
        return $("#confirm-text").text();
    }

    @Step("dismiss alert")
    public void dismissAlert(){
        Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
        alert.dismiss();
    }

    @Step("click on launch prompt button")
    public void clickLaunchPrompt(){
        $("#my-prompt").click();
    }

    @Step("send text to prompt")
    public void sendTextToPrompt(String textForPrompt){
        Alert alert = WebDriverRunner.getWebDriver().switchTo().alert();
        alert.sendKeys(textForPrompt);
    }

    @Step("get text of typed message")
    public String getTypedText(){
        return $("#prompt-text").text();
    }

    @Step("click on launch modal button")
    public void clickLaunchModal(){
        $("#my-modal").click();
    }

    @Step("get modals text")
    public String getModalsText(){
        return $(By.xpath("//div[@class='modal-content']//div[@class='modal-body' and normalize-space(text())='This is the modal body']")).text();
    }

    @Step("click on close for modal button")
    public void clickCloseButton(){
        $(By.xpath("//button[@class='btn btn-secondary model-button']")).click();
    }

    @Step("get text after choosing something in modal")
    public String getTextAfterModal(){
        return $("#modal-text").text();
    }

    @Step("click on save changes button in modal")
    public void clickSaveChanges(){
        $(By.xpath("//button[@class='btn btn-primary model-button']")).click();
    }

    @Step("check that modal body is visible")
    public SelenideElement isModalVisible(){
        return $(By.xpath("//div[@class='modal-content']//div[@class='modal-body' and normalize-space(text())='This is the modal body']")).shouldBe(visible);
    }
}
