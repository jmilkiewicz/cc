package pl.jmilkiewicz.fas.application;

import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.application.model.DocumentData;
import pl.jmilkiewicz.fas.application.model.DocumentStorage;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
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
        Date documentDate = toDate(fileMetaData.getDocumentDate());


        //TODO withMethods would be more readable
        DocumentData documentData = new DocumentData(fileMetaData.getFileName(), fileMetaData.getUsername(),documentDate, new Date(),fileBody);
        documentStorage.addDocument(documentData);


        //TODO we shall use HMAC for Success
        navigator.goTo(ViewReference.DEFAULT.withArgument(new Argument("user",fileMetaData.getUsername())).withContextDate("Success"));
    }

    private Date toDate(String toParse) throws ParseException {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(toParse);
    }


    public void filesUploadedBy(String userName, String message, Navigator navigator) {
        Collection<Document> documentsFound = documentStorage.getByUserName(userName);
        navigator.display(new View(ViewReference.DEFAULT,documentsFound, message));
    }


    public void filesUploadedBetween(String startDate, String endDate, Navigator navigator) throws ParseException {
        //TODO a może lepiej ustawić datę mocno w tył
        Date from = parseOrReturnDefaultOnEmpty(startDate, null);
        //TODO a może lepiej ustawić datę mocno w przód
        Date to = parseOrReturnDefaultOnEmpty(endDate, null);

        Collection<Document> documentsFound = documentStorage.getByUploadTimePeriod(from, to);
        navigator.display(new View(ViewReference.DEFAULT,documentsFound));
    }

    private Date parseOrReturnDefaultOnEmpty(String date, Date defaultReturn) throws ParseException {
        Date result = defaultReturn;
        if(date != null){
            result = toDate(date);
        }
        return result;
    }
}
