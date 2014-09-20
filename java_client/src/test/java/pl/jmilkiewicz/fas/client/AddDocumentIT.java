package pl.jmilkiewicz.fas.client;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.client.*;
import org.springframework.web.client.RestTemplate;
import pl.jmilkiewicz.fas.client.support.NotFollowRedirectionStrategy;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AddDocumentIT extends  AbstractBase{
    @Before
    public void setUp(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new NotFollowRedirectionStrategy()).build();
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        fasClient = new FasClient(restTemplate, credentials);
    }

    @Test
    public void shouldBeAbleToAddDocument() throws IOException {
        URI uri = fasClient.addFile(uploadedFile, "2011-02-02");

        assertRedirectionWithSuccessAndUserName(uri);
    }

    private void assertRedirectionWithSuccessAndUserName(URI uri) {
        assertThat(uri, notNullValue());
        List<NameValuePair> parse = URLEncodedUtils.parse(uri, "utf-8");
        assertThat(parse, Matchers.<NameValuePair>hasItem(new NameValuePairMatcher("user", credentials.getName())));
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
