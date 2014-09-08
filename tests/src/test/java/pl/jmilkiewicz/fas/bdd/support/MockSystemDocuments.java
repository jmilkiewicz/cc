package pl.jmilkiewicz.fas.bdd.support;

import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.application.model.DocumentStorage;

import java.io.*;
import java.util.Collection;
import java.util.Date;
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
    private Collection<DocumentExample> docs = new LinkedList<DocumentExample>();

    @Override
    public Collection<DocumentExample> getAll() {
        return  docs;
    }

    @Override
    public void add(List<DocumentExample> documentExamples) {
        docs.addAll(documentExamples);
    }

    @Override
    public Document addDocument(String name, String uploadPerson, Date documentDate, Date uploadDate, InputStream data) {
        DocumentExample documentExample = new DocumentExample();
        documentExample.name = name;
        documentExample.id = docs.size();
        documentExample.documentDate = documentDate;
        documentExample.uploadPerson = uploadPerson;
        documentExample.file = asString(data);
        documentExample.uploadDate = uploadDate;
        docs.add(documentExample);
        return documentExample.asDocument();
    }

    private static String asString(InputStream data) {
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(data, "UTF-8"));
            //we do not care about closing
            return bufferedReader.readLine();
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
        List<Document> documents = new LinkedList<>();
        for (DocumentExample doc : docs) {
            if(doc.uploadPerson.equals(userName)){
                documents.add(doc.asDocument());
            }
        }
        return documents;
    }



    @Override
    public Collection<Document> getByUploadTimePeriod(Date from, Date to) {
        //fake
        List<Document> result = new LinkedList<>();
        for (DocumentExample doc : docs) {
            result.add(doc.asDocument());
        }
        return result;
    }

    @Override
    public Document getDocumentById(long documentId) {
        throw new UnsupportedOperationException();
    }
}
