package Kod;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

import java.io.File;

import static com.codeborne.selenide.Condition.*;

public class LabTest {
    public String email;
    public String password;

    public int loginCounter;


    @BeforeEach
    public void readFile() {
        try {
            File jsonFile = new File("C:\\temp\\Login.json");
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonFile);

            email = jsonNode.get("Credentials").get("email").asText();
            password = jsonNode.get("Credentials").get("password").asText();
        } catch (Exception e) {
            System.out.println("Error reading file, " + e.getMessage());
        }
    }

    @BeforeEach
    public void setUp(){
        open("https://www.ltu.se");
        try{
            if(title().isEmpty()){
                System.out.println("Title is empty");
            }
            else {
                System.out.println("Page loaded");
            }
        } catch(Exception e) {
            System.out.println("Failed to load Page: " + e.getMessage());

        }
        try{
            if($("button.CybotCookiebotDialogBodyButton").isDisplayed()){
                $("button.CybotCookiebotDialogBodyButton").click();
                System.out.println("Cookie accepted");
            }

        } catch(ElementNotFound e) {
            System.out.println("Element not found");

        }
        if ($("button.CybotCookiebotDialogBodyButton").isDisplayed() ) {
            $("button.CybotCookiebotDialogBodyButton").click();
        }


        // Student knapp
        $("html > body > header > div:nth-of-type(2) > div:nth-of-type(1) > div:nth-of-type(1) > div:nth-of-type(3) > div > a:nth-of-type(1)").click();

        // Registerutdrag
        $("html > body > main > div > div > div:nth-of-type(1) > div > div:nth-of-type(2) > div > div > div > div > ul > li:nth-of-type(1) > a > div").click();

        // Välj lärosäte
        $("a[class$='btn-ladok-inloggning']").click();

        // Prompt
        $("input[id='searchinput']").setValue("LTU").click();

        // TRÖCK
        $("li").shouldBe(visible).click();

        if (loginCounter < 1) {
            // Användarnamn
            $("input[id='username']").shouldBe(visible).setValue(email);
            $("input[id='password']").setValue(password);

            // Click
            $("input[class='btn-submit']").click();
        }
        $x("/html/body/ladok-root/ladok-cookie-banner/div/div/div/div/div/div[2]/button[1]").click();

        loginCounter++;


    }
    @Test
    public void finalExam(){
        Configuration.reportsFolder = "screenshots";
        $x("/html/body/ladok-root/ladok-cookie-banner/div/div/div/div/div/div[2]/button[1]").click();
        $("html > body > ladok-root > div > main > div > ladok-startsida > ladok-examinationstillfallen > ladok-examinationstillfalle-kort:nth-of-type(2) > ladok-card > div > div > div:nth-of-type(1) > ladok-visa-mer").click();
        screenshot("final_examination");

    }


    @Test
    public void createTranscript() {

        $("button[aria-label='Menu']").click();
        $("html > body > ladok-root > div > ladok-sido-meny > nav > div:nth-of-type(2) > ul:nth-of-type(1) > li:nth-of-type(3) > ladok-behorighetsstyrd-nav-link > a").shouldBe(visible).click();

        // Test för att se att knappen för att skapa intyg finns
        SelenideElement createTranscriptButton = $("button[class$='btn-ladok-brand']");
        $(createTranscriptButton).shouldBe(visible);
        $(createTranscriptButton).shouldBe(enabled);
        $("button[class$='btn-ladok-brand']").click();
        $("select[id='intygstyp']").click();
        $x("//option[@value='1: Object']").click();
        $x("//*[@id='allaRegistreringarGrupperdePaProgramRadio']").click();
        $("button[class$='me-lg-3']").shouldBe(visible).click();

    }
}
