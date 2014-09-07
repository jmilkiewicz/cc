package pl.jmilkiewicz.fas.application.repository;

import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.application.model.DocumentData;
import pl.jmilkiewicz.fas.application.model.DocumentStorage;

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
    public Document addDocument(DocumentData doc) {
        Document document = new Document(docs.size(), doc);
        docs.add(document);
        return document;
    }

    @Override
    public Collection<Document> getByUserName(String userName) {
        List<Document> result = new ArrayList<>();
        for (Document document : docs) {
            if(document.getDocumentData().getUploadPerson().equals(userName)){
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
}
