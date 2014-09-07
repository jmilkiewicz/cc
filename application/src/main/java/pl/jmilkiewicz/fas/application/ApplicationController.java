package pl.jmilkiewicz.fas.application;

import pl.jmilkiewicz.fas.application.model.Document;
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
        //TODO validate
        Date documentDate = toDate(fileMetaData.getDocumentDate());


        //TODO na razie upload Date jest zle
        documentStorage.addDocument(fileMetaData.getFileName(), fileMetaData.getUsername(), documentDate, new Date(), fileBody);


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
        Date from = parseOrReturnDefaultOnNull(startDate, null);
        //TODO a może lepiej ustawić datę mocno w przód
        Date to = parseOrReturnDefaultOnNull(endDate, null);

        Collection<Document> documentsFound = documentStorage.getByUploadTimePeriod(from, to);
        navigator.display(new View(ViewReference.DEFAULT,documentsFound));
    }

    private Date parseOrReturnDefaultOnNull(String date, Date defaultReturn) throws ParseException {
        Date result = defaultReturn;
        if(date != null){
            result = toDate(date);
        }
        return result;
    }

    public void getDocumentById(String documentId, Navigator navigator) {
        Document documentById = documentStorage.getDocumentById(Long.parseLong(documentId));
        if(documentById == null){
            navigator.notFound();
        }
    }
}
