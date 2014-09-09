package pl.jmilkiewicz.fas.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import pl.jmilkiewicz.fas.application.ui.Argument;
import pl.jmilkiewicz.fas.application.ui.Navigator;
import pl.jmilkiewicz.fas.application.ui.View;
import pl.jmilkiewicz.fas.application.ui.ViewReference;

import java.net.URI;


public class SpringNavigator implements Navigator {
    private ResponseEntity result;
    private final UriComponentsBuilder uriComponentsBuilder;

    public SpringNavigator(UriComponentsBuilder uriComponentsBuilder) {
        this.uriComponentsBuilder = uriComponentsBuilder;
    }

    public ResponseEntity getResult() {
        return result;
    }

    @Override
    public void goTo(ViewReference viewReference) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(computeUri(uriComponentsBuilder, viewReference));
        result = new ResponseEntity(responseHeaders, HttpStatus.SEE_OTHER);
    }

    private URI computeUri(UriComponentsBuilder uriComponentsBuilder, ViewReference viewReference) {
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromUri(uriComponentsBuilder.build().toUri()).pathSegment(new String[]{viewReference.getType()});

        if (viewReference.getDataRef()!= null) {
            componentsBuilder = componentsBuilder.pathSegment(new String[] { viewReference.getDataRef() });
        }

        if(!viewReference.getArguments().isEmpty()){
            for (Argument argument : viewReference.getArguments()) {
                componentsBuilder = componentsBuilder.queryParam(argument.getName(), argument.getValue());
            }
        }
        if(viewReference.getContextData() != null){
            componentsBuilder = componentsBuilder.queryParam("message", viewReference.getContextData());
        }
        return componentsBuilder.build().toUri();

    }

    @Override
    public void display(View view) {
        result = new ResponseEntity(map(uriComponentsBuilder, view), HttpStatus.OK);
    }

    @Override
    public void notFound() {
        result = new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Override
    public void display(byte[] bytes) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        result = new ResponseEntity(bytes,responseHeaders, HttpStatus.OK);
    }

    private ResponseBody map(UriComponentsBuilder uriComponentsBuilder, View view) {
        ViewReference selfReference = view.getSelfReference();
        return new ResponseBody(computeUri(uriComponentsBuilder, selfReference), selfReference.getType(), selfReference.getDataRef(), view.getData());
    }

}
