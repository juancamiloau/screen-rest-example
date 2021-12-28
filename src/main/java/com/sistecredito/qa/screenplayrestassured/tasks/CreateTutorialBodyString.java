package com.sistecredito.qa.screenplayrestassured.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class CreateTutorialBodyString implements Task {
    private String title;
    private String description;
    private boolean published;

    public CreateTutorialBodyString(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to("/tutorials")
                .with(res -> res.contentType(ContentType.JSON)
                        .body("{" +
                                "\"title\" : \""+title+"\"," +
                                "\"description\" : \""+description+"\"," +
                                "\"published\":"+published +
                                "}")
                ));
    }
}
