package pl.jmilkiewicz.fas.application.model;

import java.io.InputStream;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 2:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Document {
    private final long id;
    private final DocumentData documentData;

    public Document(long id, DocumentData documentData) {
        this.id = id;
        this.documentData = documentData;
    }

    public long getId() {
        return id;
    }

    public DocumentData getDocumentData() {
        return documentData;
    }
}
