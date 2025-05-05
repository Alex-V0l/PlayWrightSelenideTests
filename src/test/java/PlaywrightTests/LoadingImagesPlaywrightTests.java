package PlaywrightTests;

import PlaywrightPages.LoadingImagesPlaywrightPage;
import com.microsoft.playwright.assertions.LocatorAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoadingImagesPlaywrightTests extends BasePlaywrightTest{

    LoadingImagesPlaywrightPage loadingImagesPage;

    @BeforeEach
    public void setUp(){
        page.navigate("https://bonigarcia.dev/selenium-webdriver-java/");
        loadingImagesPage = new LoadingImagesPlaywrightPage(page);
        loadingImagesPage.goToLoadingImagesPage();
    }

    @DisplayName("go to loading images and check url and subtitle")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void getLoadingImagesURLAndSubtitleTest(){
        String expectedSubtitle = "Loading images";
        String expectedURL = "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html";

        Assertions.assertEquals(expectedSubtitle, loadingImagesPage.getSubtitleText(), "Values must be equal");
        Assertions.assertEquals(expectedURL, loadingImagesPage.getCurrentURL(), "Values must be equal");
    }

    @DisplayName("check that amount of appearing images changing with time")
    @Tags({@Tag("smoke"), @Tag("UI")})
    @Test
    void loadingImagesAmountTest(){
        String expectedTextAfterLoading = "Done!";
        int initAmountOfAppearingImages = loadingImagesPage.getAmountOfAppearingImages();

        Assertions.assertTrue(loadingImagesPage.isSpinnerVisible(), "Spinner must be visible while all images are not appeared");
        assertThat(loadingImagesPage.getAllAppearingImages()).hasCount(4, new LocatorAssertions.HasCountOptions().setTimeout(10_000));

        int newAmountOfAppearingImages = loadingImagesPage.getAmountOfAppearingImages();

        Assertions.assertTrue(initAmountOfAppearingImages<newAmountOfAppearingImages, "Amount of images must have changed");
        Assertions.assertEquals(expectedTextAfterLoading, loadingImagesPage.getTextAfterLoading(), "Values must be equal");
        Assertions.assertFalse(loadingImagesPage.isSpinnerVisible(), "Spinner must not be visible");
    }

    @DisplayName("Проверка соответствия загруженных картинок")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @ParameterizedTest
    @CsvSource({
            "compass, 0, 4000, 1",
            "calendar, 1, 6000, 2",
            "award, 2, 8000, 3",
            "landscape, 3, 10000, 4"
    })
    void loadingImagesValuesTest(String imageId, int expectedIndex, int timeoutTime, int amountOfLoadedImages) {

        assertThat(loadingImagesPage.getAllAppearingImages()).hasCount(amountOfLoadedImages, new LocatorAssertions.HasCountOptions().setTimeout(timeoutTime));

        String expectedAlt = loadingImagesPage.getAltOfAppearedImage(imageId);
        String actualAlt = loadingImagesPage.getAltFromImageOfCollection(expectedIndex);

        Assertions.assertEquals(expectedAlt, actualAlt, "Values must be equal");
    }
}
