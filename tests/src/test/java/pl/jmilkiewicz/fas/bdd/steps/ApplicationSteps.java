package pl.jmilkiewicz.fas.bdd.steps;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matcher;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import pl.jmilkiewicz.fas.application.*;
import pl.jmilkiewicz.fas.application.ff.DocumentListEntry;
import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.bdd.support.AssetExample;
import pl.jmilkiewicz.fas.bdd.support.DocumentExample;
import pl.jmilkiewicz.fas.bdd.support.SystemDocuments;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Matchers.eq;
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

    private String message;
    private Date now;

    @Given("^\"([^\"]*)\" is an authenticated user$")
    public void is_an_authenticated_user(String userGivenName) throws Throwable {
        this.userGivenName = userGivenName;
    }

    @When("^\"([^\"]*)\" adds an asset:$")
    public void adds_an_asset(String userGivenName, List<AssetExample> assetExamples) throws Throwable {
        AssetExample assetExample = assetExamples.get(0);
        navigator = Mockito.mock(Navigator.class);
        controller.addFile(assetExample.content(), assetExample.metadata(userGivenName), now, navigator);
    }

    @Then("^will receive a feedback that his operation was succesfull$")
    public void will_receive_a_feedback_that_his_operation_was_succesfull() throws Throwable {
        assertThat(capturedViewReference().getContextData(), is("Success"));
    }

    @Then("^the following documents will exist in the System$")
    public void the_following_documents_will_exist_in_the_System(List<DocumentExample> documents) throws Throwable {
       assertThat(systemDocuments.getAll(), containsInAnyOrder(documents.toArray(new DocumentExample[0])));
    }

    @Then("^\"([^\"]*)\" will go to files uploaded by \"([^\"]*)\"$")
    public void will_go_to_files_uploaded_by(String userGivenName, String uploadedBy) throws Throwable {
        assertThat(capturedViewReference().getType(), is(ViewReference.DEFAULT.getType()));
        assertThat(capturedViewReference().getArguments(), contains(new Argument("user", uploadedBy)));
    }

    private ViewReference capturedViewReference() {
        ArgumentCaptor<ViewReference> argument = ArgumentCaptor.forClass(ViewReference.class);
        verify(navigator, only()).goTo(argument.capture());
        return argument.getValue();
    }

    @Given("^the following documents alread exist in the System$")
    public void the_following_documents_alread_exist_in_the_System(List<DocumentExample> documents) throws Throwable {
        systemDocuments.add(documents);
    }


    @When("^\"([^\"]*)\" goes to files uploaded by \"([^\"]*)\"$")
    public void goes_to_files_uploaded_by(String userGivenName, String uploadUserName) throws Throwable {
        //TODO czy nie lepiej w BeforeScenario
        navigator = Mockito.mock(Navigator.class);
        controller.filesUploadedBy(uploadUserName, message, navigator);
    }

    private View capturedView() {
        ArgumentCaptor<View> argument = ArgumentCaptor.forClass(View.class);
        verify(navigator, only()).display(argument.capture());
        return argument.getValue();
    }

    @Then("^\"([^\"]*)\" will see following document list entries:$")
    public void will_see_following_document_list_entries(String userGivenName,
                                                            List<DocumentListEntry> expectedDocumentListEntries) throws Throwable {
        assertThat(capturedView().getData(), instanceOf(Collection.class));
        assertThat((Collection<DocumentListEntry>) capturedView().getData(), containsInAnyOrder(expectedDocumentListEntries.toArray(new DocumentListEntry[0])));
    }

    @Given("^\"([^\"]*)\" is a pending message$")
    public void is_a_pending_message(String messageBody) throws Throwable {
        this.message = messageBody;
    }

    @Then("^\"([^\"]*)\" will see the \"([^\"]*)\" message$")
    public void will_see_the_message(String userGivenName, String messageBody) throws Throwable {
        assertThat(capturedView().getMessages(), containsInAnyOrder(new String[]{messageBody}));
    }

    @Given("^now is \"([^\"]*)\"$")
    public void now_is(Date now) throws Throwable {
        this.now = now;
    }
}
