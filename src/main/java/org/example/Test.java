package org.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import com.codeborne.selenide.ex.ElementNotFound;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class Test {

private static String email;
private static String password;




public static void readFile(){
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

public static void finalExam(){
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
    $("button.CybotCookiebotDialogBodyButton").click();


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


    // Användarnamn
    $("input[id='username']").shouldBe(visible).setValue(email);
    $("input[id='password']").setValue(password);

    // Click
    $("input[class='btn-submit']").click();


}


public static void loop(){
    while (true);
}


    public static void main(String[] args) {
        readFile();
        finalExam();
        loop();
    }

}
