package pl.jmilkiewicz.fas.client;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.jmilkiewicz.fas.client.support.NotFollowRedirectionStrategy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Arrays;

public class AddDocumentIT {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();


    @Test
    public void shouldBeAbleToAddDocument() throws IOException {
        File file = folder.newFile("testFile.txt");
        PrintWriter writer = new PrintWriter(file);
        writer.write("some text abc");
        writer.flush();
        writer.close();

        CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new NotFollowRedirectionStrategy()).build();

        RestTemplate restTemplate = new RestTemplate(new InterceptingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient), Arrays.<ClientHttpRequestInterceptor>asList(new ClientHttpRequestInterceptor(){
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                request.getHeaders().add("Authorization", getAuthorizationValue());
                ClientHttpResponse execute = execution.execute(request, body);
                return  execute;
            };
        })));

        MultiValueMap<String, Object> parts = new
                LinkedMultiValueMap<String, Object>();
        parts.add("fileName", file.getName());
        parts.add("documentDate", "2011-02-02");
        parts.add("file", new
                FileSystemResource(file));
        //MultiValueMap headers = new LinkedMultiValueMap();
        //headers.add();


        URI uri = restTemplate.postForLocation("http://localhost:9090/documents", parts);

        System.out.println(uri);
    }

    private String getAuthorizationValue() {
        String auth = "user1" + ":" + "password";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String( encodedAuth );
    }

}
