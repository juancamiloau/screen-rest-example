package com.sistecredito.qa.screenplayrestassured.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.thucydides.core.annotations.Step;

public class GetTutorial implements Task {
    private int tutorialId;

    public GetTutorial(int tutorialId) {
        this.tutorialId = tutorialId;
    }

    @Step("{0} gets a tutorial with id #tutorialId")
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource("/tutorials/{id}")
                .with(req -> req.contentType(ContentType.JSON)
                        .pathParam("id",tutorialId)));
    }

    public static GetTutorial withId(int tutorialId){
        return Tasks.instrumented(GetTutorial.class,tutorialId);
    }
}
