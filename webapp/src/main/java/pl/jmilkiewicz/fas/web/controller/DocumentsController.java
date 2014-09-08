package pl.jmilkiewicz.fas.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.jmilkiewicz.fas.application.ApplicationController;
import pl.jmilkiewicz.fas.application.FileMetaData;

import java.io.IOException;
import java.security.Principal;
import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/documents")
public class DocumentsController {
    @Autowired
    private ApplicationController applicationController;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getAllDocuments(@RequestParam("file") MultipartFile file, Principal principal) throws IOException, ParseException {
        SpringNavigator springNavigator = springNavigator();
        applicationController.addFile(file.getInputStream(), new FileMetaData(principal.getName(), "2000-01-01", "fakeFileName"),new Date(), springNavigator);
        return springNavigator.getResult();
    }

    @RequestMapping(method = RequestMethod.GET, params = "user")
    public ResponseEntity<?> getDocumentsByUserName(@RequestParam("user") String userName,@RequestParam(value="message", required = false) String message) throws IOException, ParseException {
        SpringNavigator springNavigator = springNavigator();
        applicationController.filesUploadedBy(userName, message, springNavigator);
        return springNavigator.getResult();
    }

    @RequestMapping(method = RequestMethod.GET, params = "!user")
    public ResponseEntity<?> getDocumentsByDates(@RequestParam(value ="from", required = false) String from, @RequestParam(value="to", required = false) String to) throws IOException, ParseException {
        SpringNavigator springNavigator = springNavigator();
        applicationController.filesUploadedBetween(from, to, springNavigator);
        return springNavigator.getResult();
    }

    @RequestMapping(method = RequestMethod.GET, value= "/{docId}")
    public ResponseEntity<?> getDocumentBody(@PathVariable("docId") String documentId) throws IOException, ParseException {
        SpringNavigator springNavigator = springNavigator();
        applicationController.getDocumentById(documentId, springNavigator);
        return springNavigator.getResult();
    }


    private SpringNavigator springNavigator() {
        return new SpringNavigator(ServletUriComponentsBuilder.fromCurrentServletMapping());
    }

}
