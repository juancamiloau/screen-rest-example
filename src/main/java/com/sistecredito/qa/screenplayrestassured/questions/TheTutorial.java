package com.sistecredito.qa.screenplayrestassured.questions;

import com.sistecredito.qa.screenplayrestassured.models.Tutorial;
import io.restassured.common.mapper.TypeRef;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class TheTutorial implements Question<Tutorial> {
    @Override
    public Tutorial answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Tutorial.class);
    }

    public static TheTutorial inResponse(){
        return new TheTutorial();
    }
}
