package pl.jmilkiewicz.fas.client;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import pl.jmilkiewicz.fas.client.support.NotFollowRedirectionStrategy;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReadDocumentsIT {
    private final Credentials credentials = new Credentials("user1", "password");
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private FasClient fasClient;

    @Before
    public void setUp(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new NotFollowRedirectionStrategy()).build();
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        fasClient = new FasClient(restTemplate, credentials);
    }

    @Test
    public void shallReturnDocumentsOfParticularUser() throws IOException {
        File file = folder.newFile("testFile.txt");
        fasClient.addFile(file,"2011-02-02");

        Map<String, Object> documents = fasClient.getDocumentsOf(credentials.getName());
        assertContainsAtLeastOneDocument(documents);
    }

    private void assertContainsAtLeastOneDocument(Map<String,Object> documents) {
        assertThat(documents, notNullValue());
        assertThat(documents, Matchers.<String>hasKey("data"));
        List<?> documentEntries = (List<?>) documents.get("data");
        assertThat("at least a single document must be returned", documentEntries, hasSize(greaterThan(0)));
    }


}
