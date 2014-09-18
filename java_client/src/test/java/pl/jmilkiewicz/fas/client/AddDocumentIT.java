package pl.jmilkiewicz.fas.client;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.jmilkiewicz.fas.client.support.NotFollowRedirectionStrategy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddDocumentIT {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    private FasClient fasClient;

    @Before
    public void setUp(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new NotFollowRedirectionStrategy()).build();
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        fasClient = new FasClient(restTemplate, new Credentials("user1", "password"));
    }

    @Test
    public void shouldBeAbleToAddDocument() throws IOException {
        File file = folder.newFile("testFile.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.write("some text abc");
        writer.flush();
        writer.close();

        URI uri = fasClient.addFile(file, "2011-02-02");


        assertThat(uri, notNullValue());

        List<NameValuePair> parse = URLEncodedUtils.parse(uri, "utf-8");
        assertThat(parse, Matchers.<NameValuePair>hasItem(new NameValuePairMatcher("user","user1")));
        assertThat(parse, Matchers.<NameValuePair>hasItem(new NameValuePairMatcher("message","Success")));
    }

    private static class NameValuePairMatcher extends TypeSafeDiagnosingMatcher<NameValuePair>{
        private Matcher<? super NameValuePair> nameMatcher;
        private Matcher<? super NameValuePair> valueMatcher;

        public NameValuePairMatcher(String paramName, String paramValue) {
            nameMatcher = hasProperty("name", equalTo(paramName));
            valueMatcher = hasProperty("value", equalTo(paramValue));
        }

        @Override
        protected boolean matchesSafely(NameValuePair item, Description mismatchDescription) {
            boolean matches = true;
            if (!nameMatcher.matches(item)) {
                reportMismatch(nameMatcher, item, mismatchDescription);
                matches = false;
            }
            if(!valueMatcher.matches(item)){
                reportMismatch(valueMatcher, item, mismatchDescription);
                matches = false;
            }

            return matches;
        }

        static void reportMismatch(Matcher<?> matcher, Object item, Description mismatchDescription){
            //mismatchDescription.appendText(name).appendText(" ");
            matcher.describeMismatch(item, mismatchDescription);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("NameValuePair ").appendDescriptionOf(nameMatcher).appendText("and ").appendDescriptionOf(valueMatcher);
        }
    }

}
