package org.example;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class Test {

public static void finalExam(){
    open("https://www.ltu.se");
    $("#search").setValue("selenium").pressEnter();
}




    public static void main(String[] args) {
        finalExam();
    }

}
