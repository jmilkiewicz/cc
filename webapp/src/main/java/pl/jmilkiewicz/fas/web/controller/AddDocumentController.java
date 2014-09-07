package pl.jmilkiewicz.fas.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
@RequestMapping("/documentAddForm")
public class AddDocumentController {

    @RequestMapping(method = RequestMethod.GET)
    public String showForm() throws IOException {
        return "addDocForm";
    }
}
