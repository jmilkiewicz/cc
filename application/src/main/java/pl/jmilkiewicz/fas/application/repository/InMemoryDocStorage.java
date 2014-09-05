package pl.jmilkiewicz.fas.application.repository;

import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.application.model.DocumentStorage;

import java.util.LinkedList;
import java.util.List;

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
    public void addDocument(Document doc) {
        docs.add(doc);
    }
}
