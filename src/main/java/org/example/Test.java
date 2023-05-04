package org.example;

import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class Test {



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
        finalExam();
    }

}
