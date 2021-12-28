package com.sistecredito.qa.screenplayrestassured.tasks;

import com.sistecredito.qa.screenplayrestassured.models.Tutorial;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class CreateTutorialModels implements Task {
    private Tutorial tutorial;
    public CreateTutorialModels(Tutorial tutorial) {
        this.tutorial = tutorial;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to("/tutorials")
                .with(res -> res.contentType(ContentType.JSON)
                        .body(tutorial)));
    }

}
