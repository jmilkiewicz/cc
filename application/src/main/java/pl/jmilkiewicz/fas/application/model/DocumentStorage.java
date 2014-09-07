package pl.jmilkiewicz.fas.application.model;

import pl.jmilkiewicz.fas.application.FileMetaData;

import java.io.InputStream;
import java.util.Collection;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DocumentStorage {
    Document addDocument(String name, String uploadPerson, Date documentDate, Date uploadDate, InputStream data);

    Collection<Document> getByUserName(String userName);

    Collection<Document> getByUploadTimePeriod(Date from, Date to);

    Document getDocumentById(long documentId);
}
