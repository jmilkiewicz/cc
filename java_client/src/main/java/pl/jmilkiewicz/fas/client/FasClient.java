package pl.jmilkiewicz.fas.client;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import pl.jmilkiewicz.fas.client.support.NotFollowRedirectionStrategy;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/18/14
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class FasClient {
    private final RestOperations restOperations;
    private final MultiValueMap<String, Object> headers;

    public FasClient(RestOperations restOperations, Credentials credentials) {
        this.restOperations = restOperations;
        this.headers = new LinkedMultiValueMap();
        this.headers.add("Authorization", getAuthorizationValue(credentials));
    }

    public URI addFile(File document, String documentDate){


        MultiValueMap<String, Object> parts = new
                LinkedMultiValueMap<String, Object>();
        parts.add("fileName", document.getName());
        parts.add("documentDate", documentDate);
        parts.add("file", new
                FileSystemResource(document));

        return restOperations.postForLocation("http://localhost:9090/documents", new HttpEntity(parts, new LinkedMultiValueMap<>(headers)));
    }

    private final String getAuthorizationValue(Credentials credentials) {
        String auth = credentials.getName() + ":" + credentials.getPassword();
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String( encodedAuth );
    }
}
