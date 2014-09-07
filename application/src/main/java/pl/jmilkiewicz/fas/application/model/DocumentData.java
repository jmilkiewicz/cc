package pl.jmilkiewicz.fas.application.model;

import java.io.InputStream;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 8:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentData {
    private final String name;
    private final String uploadPerson;
    private final Date documentDate;
    private final Date uploadDate;
    private final InputStream data;

    public DocumentData(String name, String uploadPerson, Date documentDate, Date uploadDate, InputStream data) {
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
        return documentDate;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public InputStream getData() {
        return data;
    }

    @Override
    public String toString() {
        return "DocumentData{" +
                "name='" + name + '\'' +
                ", uploadPerson='" + uploadPerson + '\'' +
                ", documentDate=" + documentDate +
                ", uploadDate=" + uploadDate +
                ", data=" + data +
                '}';
    }
}
