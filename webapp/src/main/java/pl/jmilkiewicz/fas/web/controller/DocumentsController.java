package pl.jmilkiewicz.fas.web.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/documents")
public class DocumentsController {



    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> displayPage() throws IOException {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
