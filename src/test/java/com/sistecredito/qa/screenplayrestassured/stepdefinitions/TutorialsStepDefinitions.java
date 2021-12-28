package com.sistecredito.qa.screenplayrestassured.stepdefinitions;

import com.sistecredito.qa.screenplayrestassured.models.Tutorial;
import com.sistecredito.qa.screenplayrestassured.questions.TheTutorial;
import com.sistecredito.qa.screenplayrestassured.tasks.CreateTutorialModels;
import com.sistecredito.qa.screenplayrestassured.tasks.GetTutorial;
import com.sistecredito.qa.screenplayrestassured.tasks.builders.CreateTutorial;
import com.sistecredito.qa.screenplayrestassured.utils.MySqlConnection;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class TutorialsStepDefinitions {

    @Before
    public void setUp(){

        OnStage.setTheStage(Cast.ofStandardActors());
        OnStage.theActorCalled("Juan")
                .can(CallAnApi.at("http://localhost:8080/api"));
    }

    @When("I search a tutorial with id {int}")
    public void iSearchATutorialWithId(Integer tutorialId) {
        OnStage.theActorInTheSpotlight().attemptsTo(GetTutorial.withId(tutorialId));
        OnStage.theActorInTheSpotlight().remember("tutorialId", tutorialId);
        OnStage.theActorInTheSpotlight().remember("tutorial", new Tutorial( "My third tutorial","Second tutorial for CRUD on Java With SpringBoot",
                true));
    }


    @When("I create a tutorial")
  public void iCreateATutorial(List<Map<String,Object>> tutorialData) {
//    public void iCreateATutorial(List<Tutorial> tutorialData) {
       /* OnStage.theActorCalled("Juan").attemptsTo(CreateTutorial.withData(tutorialData.get(0).get("title").toString(),
                tutorialData.get(0).get("description").toString(),
                Boolean.parseBoolean(tutorialData.get(0).get("published").toString())));*/
        Tutorial tutorial = new Tutorial(tutorialData.get(0).get("title").toString(),
                tutorialData.get(0).get("description").toString(),
                Boolean.parseBoolean(tutorialData.get(0).get("published").toString()));
        OnStage.theActorInTheSpotlight().remember("tutorial", tutorial);
        OnStage.theActorInTheSpotlight().attemptsTo(CreateTutorial.withTutorial(tutorial));

    }

    @Then("I should see the status code is OK with status code {int}")
    public void iShouldSeeTheStatusCodeIsOK(Integer statusCode) {
        OnStage.theActorInTheSpotlight().should(ResponseConsequence.seeThatResponse(response -> response.statusCode(statusCode)));
        //SerenityRest.lastResponse().then().statusCode(200);
        SerenityRest.lastResponse().prettyPrint();
    }
    @Then("I should see the tutorial information")
    public void iShouldSeeTheTutorialInformation() {
        Tutorial tutorial = OnStage.theActorInTheSpotlight().recall("tutorial");
        int tutorialId = OnStage.theActorInTheSpotlight().recall("tutorialId");
        MySqlConnection mySqlConnection = new MySqlConnection("jdbc:mysql://localhost:3306/testdb?allowPublicKeyRetrieval=true&useSSL=false",
                "usuario", "123456");
        Map<String, Object> tutorialBd = mySqlConnection.getTutorialById(tutorialId);

        /*OnStage.theActorInTheSpotlight().should(seeThat(TheTutorial.inResponse(), hasProperty("title",
                is(tutorial.getTitle()))));*/
        OnStage.theActorInTheSpotlight().should(seeThat(TheTutorial.inResponse(), hasProperty("title",
                is(tutorialBd.get("title")))));
    }
}
