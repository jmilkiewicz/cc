package pl.jmilkiewicz.fas.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pl.jmilkiewicz.fas.client.support.NotFollowRedirectionStrategy;

import javax.xml.transform.Source;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ReadDocumentsIT extends AbstractBase{

    private BigInteger sampleDocumentId;


    @Before
    public void setUp() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new ISO8601DateFormat());
        objectMapper.enable(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS);

        CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new NotFollowRedirectionStrategy()).build();
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);

        restTemplate.setMessageConverters(Arrays.<HttpMessageConverter<?>>asList(
                                                    new ByteArrayHttpMessageConverter(),
                                                    new StringHttpMessageConverter(),
                                                    new ResourceHttpMessageConverter(),
                                                    new SourceHttpMessageConverter<Source>(),
                                                    new AllEncompassingFormHttpMessageConverter(),
                                                    mappingJackson2HttpMessageConverter));

        fasClient = new FasClient(restTemplate, credentials);

        sampleDocumentId = uploadDocument(uploadedFile);
    }

    @Test
    public void shallReturnDocumentsOfParticularUser() throws IOException {
        Map<String, Object> documents = fasClient.getDocumentsOf(credentials.getName());

        assertContainsDocumentOf(documents, credentials.getName());
    }

    private void assertContainsDocumentOf(Map<String, Object> response, String name) {
        assertDataNotEmpty(response);
        List<Map<String,Object>> documentEntries = (List<Map<String,Object>>) response.get("data");
        assertThat("at least a single document must be returned", documentEntries, hasSize(greaterThan(0)));

        for (Map<? extends  String, ? extends  Object> documentEntry : documentEntries) {
            assertThat("invalid upload Person", documentEntry, Matchers.<String, Object>hasEntry("uploadPerson", name));
        }
    }


    private void assertDataNotEmpty(Map<String, Object> response) {
        assertThat(response, notNullValue());
        assertThat(response, Matchers.<String>hasKey("data"));
    }

    @Test
    public void shallReturnDocumentMetadataById() throws IOException {
        Map<String, Object> response = fasClient.getDocumentMetadata(sampleDocumentId.longValue());

        assertDocumentMetadataReturned(response);

    }

    @Test
    public void shallReturnDocumentBody() throws IOException {
        byte[] expected = asByteArray(uploadedFile);

        byte[] response = fasClient.getDocumentBody(sampleDocumentId.longValue());

        assertThat("invalid byte retrieved",Arrays.equals(response, expected), is(true));
    }

    private byte[] asByteArray(File uploadedFile) throws IOException {
        return Files.readAllBytes(Paths.get(uploadedFile.toURI()));
    }

    private BigInteger uploadDocument(File file) throws IOException {
        fasClient.addFile(file,"2011-02-02");
        Map<String, Object> stringObjectMap = filterByDocumentName(fasClient.getDocumentsOf(credentials.getName()), file.getName());
        return (BigInteger) stringObjectMap.get("id");
    }


    private void assertDocumentMetadataReturned(Map<String, Object> response) {
        assertDataNotEmpty(response);
        Map<String,Object> docMetadata = (Map<String, Object>) response.get("data");
        assertThat(docMetadata, hasKey("id"));
        assertThat(docMetadata, hasKey("name"));
        assertThat(docMetadata, hasKey("documentDate"));
        assertThat(docMetadata, hasKey("uploadPerson"));
        assertThat(docMetadata, hasKey("uploadDate"));
        assertThat(docMetadata, hasKey("name"));
    }

    private Map<String, Object> filterByDocumentName(Map<String, Object> documentsOf, String name) {
        List<Map<String,Object>> data = (List<Map<String, Object>>) documentsOf.get("data");
        for (Map<String, Object> stringObjectMap : data) {
            if(stringObjectMap.get("name").equals(name)){
                return stringObjectMap;
            }
        };
        throw new RuntimeException("document with name" + name + " NOT FOUND");
    }

}
