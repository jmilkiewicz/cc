package pl.jmilkiewicz.fas.application;

import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.application.model.DocumentStorage;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 12:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationController {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    private DocumentStorage documentStorage;

    public ApplicationController(DocumentStorage documentStorage) {
        this.documentStorage = documentStorage;
    }

    public void addFile(InputStream fileBody, FileMetaData fileMetaData, Navigator navigator) throws ParseException {
        //validate

        Date documentDate = new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(fileMetaData.getDocumentDate());

        //TODO withMethods would be more readable
        Document document = new Document(fileMetaData.getFileName(), fileMetaData.getUsername(),documentDate, new Date(),fileBody);
        documentStorage.addDocument(document);
        //TODO we shall use HMAC for Success
        navigator.goTo(ViewReference.DEFAULT.withArgument(new Argument("user",fileMetaData.getUsername())).withContextDate("Success"));
    }
}
