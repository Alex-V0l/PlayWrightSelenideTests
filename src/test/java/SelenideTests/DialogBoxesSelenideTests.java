package SelenideTests;

import SelenidePages.DialogBoxesSelenidePage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.AlertNotFoundError;
import org.junit.jupiter.api.*;


import static com.codeborne.selenide.Selenide.open;

public class DialogBoxesSelenideTests {

    DialogBoxesSelenidePage dialogBoxesPage;

    @BeforeEach
    void setUp(){
        Configuration.browserSize = "1920x1020";
        dialogBoxesPage = new DialogBoxesSelenidePage();
        open("https://bonigarcia.dev/selenium-webdriver-java/");
        dialogBoxesPage.clickDialogBoxesLink();
    }

    @DisplayName("check that you've got right url and subtitle of dialog boxes page")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void DialogBoxesStartTest(){
        String expectedSubtitle = "Dialog boxes";
        String expectedURL = "https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html";

        Assertions.assertEquals(expectedSubtitle, dialogBoxesPage.getSubtitleText(), "Values must be equal");
        Assertions.assertEquals(expectedURL, dialogBoxesPage.getCurrentURL(), "Values must be equal");
    }

    @DisplayName("check that alert appears after clicking on launch alert button, text of the alert and that it disappears after accepting it")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void LaunchAlertTest(){
        String alertExpectedText = "Hello world!";

        dialogBoxesPage.clickLaunchAlertButton();

        Assertions.assertEquals(alertExpectedText, dialogBoxesPage.getAlertsText(), "Values must be equal");

        dialogBoxesPage.acceptAlert();

        Assertions.assertThrows(AlertNotFoundError.class, ()-> dialogBoxesPage.switchToAlert());
    }

    @DisplayName("check that confirm appears after clicking on launch confirm button and text of the confirm")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void LaunchConfirmAcceptTest(){
        String confirmExpectedText = "Is this correct?";
        String expectedAfterConfirmText = "You chose: true";

        dialogBoxesPage.clickLaunchConfirm();

        Assertions.assertEquals(confirmExpectedText, dialogBoxesPage.getAlertsText(), "Values must be equal");

        dialogBoxesPage.acceptAlert();

        Assertions.assertEquals(expectedAfterConfirmText, dialogBoxesPage.getMessageAfterConfirm(), "Values must be equal");
        Assertions.assertThrows(AlertNotFoundError.class, ()-> dialogBoxesPage.switchToAlert());
    }

    @DisplayName("check that confirm disappears after dismissing it")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void LaunchConfirmDismissTest(){
        String expectedAfterConfirmText = "You chose: false";

        dialogBoxesPage.clickLaunchConfirm();
        dialogBoxesPage.dismissAlert();

        Assertions.assertEquals(expectedAfterConfirmText, dialogBoxesPage.getMessageAfterConfirm(), "Values must be equal");
        Assertions.assertThrows(AlertNotFoundError.class, ()-> dialogBoxesPage.switchToAlert());
    }

    @DisplayName("check that prompt appears after clicking on launch prompt button and text of the prompt")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void LaunchPromptTest(){
        String expectedPromptText = "Please enter your name";

        dialogBoxesPage.clickLaunchPrompt();

        Assertions.assertEquals(expectedPromptText, dialogBoxesPage.getAlertsText(), "Values must be equal");
    }

    @DisplayName("check text typed into prompt")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void LaunchPromptInputTextTest(){
        String textForPrompt = "Good day";
        String expectedTextAfterTyping = "You typed: " + textForPrompt;

        dialogBoxesPage.clickLaunchPrompt();
        dialogBoxesPage.sendTextToPrompt(textForPrompt);
        dialogBoxesPage.acceptAlert();

        Assertions.assertEquals(expectedTextAfterTyping, dialogBoxesPage.getTypedText(), "Values must be equal");
        Assertions.assertThrows(AlertNotFoundError.class, ()-> dialogBoxesPage.switchToAlert());
    }

    @DisplayName("check that prompt has disappeared after dismissing it")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void LaunchPromptDismissTest(){
        String expectedTextAfterDismiss = "You typed: null";

        dialogBoxesPage.clickLaunchPrompt();
        dialogBoxesPage.dismissAlert();

        Assertions.assertEquals(expectedTextAfterDismiss, dialogBoxesPage.getTypedText(), "Values must be equal");
        Assertions.assertThrows(AlertNotFoundError.class, ()-> dialogBoxesPage.switchToAlert());
    }

    @DisplayName("check that modal appears after clicking on launch modal button, it's text and that it disappears after closing")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void LaunchModalCloseTest(){
        String expectedModalText = "This is the modal body";
        String expectedTextAfterModal = "You chose: Close";

        dialogBoxesPage.clickLaunchModal();

        Assertions.assertTrue(dialogBoxesPage.isModalVisible(), "Modal should be visible");
        Assertions.assertEquals(expectedModalText, dialogBoxesPage.getModalsText(), "Values must be equal");

        dialogBoxesPage.clickCloseButton();

        Assertions.assertEquals(expectedTextAfterModal, dialogBoxesPage.getTextAfterModal(), "Values must be equal");
        Assertions.assertThrows(AlertNotFoundError.class, ()-> dialogBoxesPage.switchToAlert());
    }

    @DisplayName("check that modal disappears after clicking on save changes button and it's text")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void LaunchModalSaveChangesTest(){
        String expectedAfterModalText = "You chose: Save changes";

        dialogBoxesPage.clickLaunchModal();
        dialogBoxesPage.clickSaveChanges();

        Assertions.assertEquals(expectedAfterModalText, dialogBoxesPage.getTextAfterModal(), "Values must be equal");
        Assertions.assertThrows(AlertNotFoundError.class, ()-> dialogBoxesPage.switchToAlert());
    }

    @DisplayName("check  text of the footer")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void CopyRightTextTest(){
        String expectedFooterText = "Copyright © 2021-2025 Boni García";

        Assertions.assertEquals(expectedFooterText, dialogBoxesPage.getCopyrightText(), "Values must be equal");
    }

    @DisplayName("check text of the footer")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void developersLinkTest(){
        String expectedURL = "https://bonigarcia.dev/";

        dialogBoxesPage.getDevelopersPageLink();

        Assertions.assertEquals(expectedURL, dialogBoxesPage.getCurrentURL(), "Values must be equal");
    }
}
