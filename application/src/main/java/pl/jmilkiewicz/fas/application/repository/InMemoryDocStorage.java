package pl.jmilkiewicz.fas.application.repository;

import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.application.model.DocumentStorage;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class InMemoryDocStorage implements DocumentStorage{
    private List<Document> docs = new LinkedList();

    @Override
    public Document addDocument(String name, String uploadPerson, Date documentDate, Date uploadDate, InputStream data) {
        long id = docs.size();
        try {
            Document document =
                                 new Document().
                                         withId(id).
                                         withDocumentDate(documentDate).
                                         withUploadDate(uploadDate).
                                         withName(name).
                                         withContent(toByteArray(data)).
                                         withUploadPerson(uploadPerson);
            docs.add(document);
            return document;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] toByteArray(InputStream data) throws IOException {
        byte[] targetArray = new byte[data.available()];
        data.read(targetArray);
        return targetArray;
    }

    @Override
    public Collection<Document> getByUserName(String userName) {
        List<Document> result = new ArrayList<>();
        for (Document document : docs) {
            if(document.getUploadPerson().equals(userName)){
                result.add(document);
            }
        }
        return result;
    }

    @Override
    public Collection<Document> getByUploadTimePeriod(Date from, Date to) {
        //fake implementation
        return new LinkedList<>(docs);
    }

    @Override
    public Document getDocumentById(long documentId) {
        if(documentId >= docs.size()){
            return null;
        }
        return docs.get((int) documentId);
    }
}
