package pl.jmilkiewicz.fas.application.model;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 2:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Document {
    private final DocumentMetaData documentMetaData;
    private final byte[] content;


    public Document(DocumentMetaData documentMetaData, byte[] content) {
        this.documentMetaData = documentMetaData;
        this.content = content;
    }

    public DocumentMetaData getDocumentMetaData() {
        return documentMetaData;
    }

    public byte[] getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (!Arrays.equals(content, document.content)) return false;
        if (documentMetaData != null ? !documentMetaData.equals(document.documentMetaData) : document.documentMetaData != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = documentMetaData != null ? documentMetaData.hashCode() : 0;
        result = 31 * result + (content != null ? Arrays.hashCode(content) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentMetaData=" + documentMetaData +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
