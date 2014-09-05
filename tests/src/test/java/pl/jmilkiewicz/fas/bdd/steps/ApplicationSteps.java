package pl.jmilkiewicz.fas.bdd.steps;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import pl.jmilkiewicz.fas.application.ApplicationController;
import pl.jmilkiewicz.fas.application.Argument;
import pl.jmilkiewicz.fas.application.Navigator;
import pl.jmilkiewicz.fas.application.ViewReference;
import pl.jmilkiewicz.fas.bdd.support.AssetExample;
import pl.jmilkiewicz.fas.bdd.support.DocumentExample;
import pl.jmilkiewicz.fas.bdd.support.SystemDocuments;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ContextConfiguration("classpath:cucumber.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class ApplicationSteps {
    private String userGivenName;
    private Navigator navigator;

    @Autowired
    private SystemDocuments systemDocuments;

    @Autowired
    private ApplicationController controller;

    @Given("^\"([^\"]*)\" is an authenticated user$")
    public void is_an_authenticated_user(String userGivenName) throws Throwable {
        this.userGivenName = userGivenName;
    }

    @When("^\"([^\"]*)\" adds an asset:$")
    public void adds_an_asset(String userGivenName, List<AssetExample> assetExamples) throws Throwable {
        AssetExample assetExample = assetExamples.get(0);
        navigator = Mockito.mock(Navigator.class);
        controller.addFile(assetExample.content(), assetExample.metadata(userGivenName), navigator);
    }

    @Then("^\"([^\"]*)\" will see a message indicating success$")
    public void will_see_a_message_indicating_success(String userGivenName) throws Throwable {
        assertThat(capturedViewReference().getContextData(), is("Success"));
    }

    @Then("^the following documents will exist in the System$")
    public void the_following_documents_will_exist_in_the_System(List<DocumentExample> documents) throws Throwable {
       assertThat(new LinkedList<>(systemDocuments.getAll()), containsInAnyOrder(documents.toArray(new DocumentExample[0])));
    }

    @Then("^\"([^\"]*)\" will see his files$")
    public void will_see_his_files(String userGivenName) throws Throwable {
        //TODO sprawdzmy type
        assertThat(capturedViewReference().getArguments(), contains(new Argument("user", userGivenName)));
    }

    private ViewReference capturedViewReference() {
        ArgumentCaptor<ViewReference> argument = ArgumentCaptor.forClass(ViewReference.class);
        verify(navigator, only()).goTo(argument.capture());
        return argument.getValue();
    }

}
