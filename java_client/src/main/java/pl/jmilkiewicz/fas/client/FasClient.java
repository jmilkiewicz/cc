package pl.jmilkiewicz.fas.client;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
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
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/18/14
 * Time: 8:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class FasClient {
    public static final String BASE_URL = "http://localhost:8080/documents";
    public static final String USER_DOCUMENTS_URL = BASE_URL +"?user={userName}";
    public static final String DOCUMENT_METADATA_URL = BASE_URL +"/{documentId}";
    public static final String DOCUMENT_BODY_URL = BASE_URL +"/{documentId}/body";
    private final RestOperations restOperations;
    private final HttpHeaders headers;

    public FasClient(RestOperations restOperations, Credentials credentials) {
        this.restOperations = restOperations;
        this.headers = buildHeaders(credentials);
    }

    private static HttpHeaders buildHeaders(Credentials credentials) {
        HttpHeaders tmpHeaders = new HttpHeaders();
        tmpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        tmpHeaders.add("Authorization", getAuthorizationValue(credentials));
        return HttpHeaders.readOnlyHttpHeaders(tmpHeaders);
    }

    public URI addFile(File document, String documentDate){
        MultiValueMap<String, Object> parts = new
                LinkedMultiValueMap<String, Object>();
        parts.add("fileName", document.getName());
        parts.add("documentDate", documentDate);
        parts.add("file", new
                FileSystemResource(document));

        return restOperations.postForLocation(BASE_URL, new HttpEntity(parts, headers));
    }

    private static final String getAuthorizationValue(Credentials credentials) {
        String auth = credentials.getName() + ":" + credentials.getPassword();
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String( encodedAuth );
    }

    public Map<String,Object> getDocumentsOf(final String userName) {
        //TODO clear duplication: act and if error throw - extract it to template

        ResponseEntity<Map> exchange = restOperations.exchange(USER_DOCUMENTS_URL, HttpMethod.GET, new HttpEntity<>(headers), Map.class, userName);
        if(exchange.getStatusCode()!= HttpStatus.OK){
            throw new OperationFailedException(exchange.getStatusCode().value());
        }
        return  exchange.getBody();
    }


    public Map<String, Object> getDocumentMetadata(long documentId) {
        ResponseEntity<Map> exchange = restOperations.exchange(DOCUMENT_METADATA_URL, HttpMethod.GET, new HttpEntity<>(headers), Map.class, documentId);
        if(exchange.getStatusCode()!= HttpStatus.OK){
            throw new OperationFailedException(exchange.getStatusCode().value());
        }
        return  exchange.getBody();
    }


    public byte[] getDocumentBody(long documentId) {
        ResponseEntity<byte[]> exchange = restOperations.exchange(DOCUMENT_BODY_URL, HttpMethod.GET, new HttpEntity<>(setAcceptOctetStreamHeader(headers)), byte[].class, documentId);
        if(exchange.getStatusCode()!= HttpStatus.OK){
            throw new OperationFailedException(exchange.getStatusCode().value());
        }
        return  exchange.getBody();

    }

    private static HttpHeaders setAcceptOctetStreamHeader(HttpHeaders baseHeaders) {
        HttpHeaders customHeader = new HttpHeaders();
        customHeader.putAll(baseHeaders);
        customHeader.setAccept(Arrays.asList(MediaType.APPLICATION_OCTET_STREAM));
        return customHeader;
    }
}
