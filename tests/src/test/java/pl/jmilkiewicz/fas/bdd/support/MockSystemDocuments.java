package pl.jmilkiewicz.fas.bdd.support;

import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.application.model.DocumentStorage;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockSystemDocuments implements SystemDocuments, DocumentStorage {
    private Collection<DocumentExample> docs = new LinkedList<DocumentExample>();

    @Override
    public Collection<DocumentExample> getAll() {
        return  docs;
    }

    @Override
    public void addDocument(Document doc) {
        docs.add(DocumentExample.fromDocument(doc));
    }
}
