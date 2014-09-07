package pl.jmilkiewicz.fas.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import pl.jmilkiewicz.fas.application.Argument;
import pl.jmilkiewicz.fas.application.Navigator;
import pl.jmilkiewicz.fas.application.View;
import pl.jmilkiewicz.fas.application.ViewReference;

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

    private ResponseBody map(UriComponentsBuilder uriComponentsBuilder, View view) {
        String type = view.getSelfReference().getType();
        return new ResponseBody(computeUri(uriComponentsBuilder, view.getSelfReference()), type, view.getData());
    }


}
