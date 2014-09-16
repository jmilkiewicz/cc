package pl.jmilkiewicz.fas.client;

import org.apache.commons.codec.binary.Base64;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.Charset;

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

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> parts = new
                LinkedMultiValueMap<String, Object>();
        //parts.add("field 1", "value 1");
        parts.add("file", new
                FileSystemResource(file));
        MultiValueMap headers = new LinkedMultiValueMap();
        headers.add("Authorization", getAuthorizationValue());


        URI uri = restTemplate.postForLocation("http://localhost:8080/documents", new HttpEntity<>(parts, headers));

        System.out.println(uri);
    }

    private String getAuthorizationValue() {
        String auth = "user1" + ":" + "password";
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String( encodedAuth );
    }

}
