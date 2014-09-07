package pl.jmilkiewicz.fas.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.jmilkiewicz.fas.application.ApplicationController;
import pl.jmilkiewicz.fas.application.FileMetaData;

import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/documents")
public class DocumentsController {
    @Autowired
    private ApplicationController applicationController;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getAllDocuments(@RequestParam("file") MultipartFile file) throws IOException, ParseException {
        SpringNavigator springNavigator = new SpringNavigator(ServletUriComponentsBuilder.fromCurrentServletMapping());
        applicationController.addFile(file.getInputStream(), new FileMetaData("fake","2000-01-01","fakeFileName"), springNavigator);
        return springNavigator.getResult();
    }

}
