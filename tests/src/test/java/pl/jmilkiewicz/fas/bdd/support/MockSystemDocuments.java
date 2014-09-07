package pl.jmilkiewicz.fas.bdd.support;

import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.application.model.DocumentData;
import pl.jmilkiewicz.fas.application.model.DocumentStorage;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 1:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class MockSystemDocuments implements SystemDocuments, DocumentStorage {
    private Collection<Document> docs = new LinkedList<Document>();

    @Override
    public Collection<DocumentExample> getAll() {
        List<DocumentExample> result = new LinkedList<>();
        for (Document doc : docs) {
            result.add(DocumentExample.fromDocument(doc));
        }
        return  result;
    }

    @Override
    public void add(List<DocumentExample> documentExamples) {
        for (DocumentExample documentExample : documentExamples) {
            try{
                docs.add(documentExample.asDocument());
            }catch (UnsupportedEncodingException e){
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Collection<Document> getByIds(Collection<Long> ids) {
        List<Document> documents = new LinkedList<>();
        for (Document doc : docs) {
            if(ids.contains(doc.getId())){
                documents.add(doc);
            }
        }
        return documents;
    }

    @Override
    public Document addDocument(DocumentData documentData) {
        Document document = new Document(docs.size(), documentData);
        docs.add(document);
        return document;
    }

    @Override
    public Collection<Document> getByUserName(String userName) {
        List<Document> documents = new LinkedList<>();
        for (Document doc : docs) {
            if(doc.getDocumentData().getUploadPerson().equals(userName)){
                documents.add(doc);
            }
        }
        return documents;
    }
}
