package com.sistecredito.qa.screenplayrestassured.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/tutorials.feature",
        glue = "com.sistecredito.qa.screenplayrestassured.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE)
public class Tutorials {
}
