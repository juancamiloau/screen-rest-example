package com.sistecredito.qa.screenplayrestassured.tasks.builders;

import com.sistecredito.qa.screenplayrestassured.models.Tutorial;
import com.sistecredito.qa.screenplayrestassured.tasks.CreateTutorialBodyString;
import com.sistecredito.qa.screenplayrestassured.tasks.CreateTutorialModels;
import net.serenitybdd.screenplay.Tasks;

public class CreateTutorial {
    private CreateTutorial(){}

    public static CreateTutorialBodyString withData(String title, String description, boolean published){
        return Tasks.instrumented(CreateTutorialBodyString.class, title, description, published);
    }

    public static CreateTutorialModels withTutorial(Tutorial tutorial){
        return Tasks.instrumented(CreateTutorialModels.class, tutorial);
    }
}
