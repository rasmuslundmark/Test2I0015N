package org.example;

import com.codeborne.selenide.ex.ElementNotFound;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
     
}




    public static void main(String[] args) {
        readFile();
        finalExam();
    }

}
