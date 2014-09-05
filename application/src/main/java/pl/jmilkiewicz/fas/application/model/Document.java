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
    private final String name;
    private final String uploadPerson;
    private final Date documentDate;
    private final Date uploadDate;
    private final InputStream data;

    public Document(String name, String uploadPerson, Date documentDate, Date uploadDate, InputStream data) {
        this.name = name;
        this.uploadPerson = uploadPerson;
        this.documentDate = documentDate;
        this.uploadDate = uploadDate;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public String getUploadPerson() {
        return uploadPerson;
    }

    public Date getDocumentDate() {
        return new Date(documentDate.getTime());
    }

    public Date getUploadDate() {
        return new Date(uploadDate.getTime());
    }

    public InputStream getData() {
        return data;
    }
}
