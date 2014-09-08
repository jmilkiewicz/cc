package pl.jmilkiewicz.fas.bdd.support;

import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.application.model.DocumentMetaData;

import java.io.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentExample {
    public long id;
    public String name;
    public Date documentDate;
    public Date uploadDate;
    public String file;
    public String uploadPerson;

    public Document asDocument()  {
        try {
            byte[] bytes = file.getBytes("UTF-8");
            return new Document(new DocumentMetaData().
                                            withId(id).
                                            withDocumentDate(documentDate).
                                            withName(name).
                                            withUploadDate(uploadDate).
                                            withUploadPerson(uploadPerson)
                                        , bytes);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentExample that = (DocumentExample) o;

        if (id != that.id) return false;
        if (documentDate != null ? !documentDate.equals(that.documentDate) : that.documentDate != null) return false;
        if (file != null ? !file.equals(that.file) : that.file != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (uploadDate != null ? !uploadDate.equals(that.uploadDate) : that.uploadDate != null) return false;
        if (uploadPerson != null ? !uploadPerson.equals(that.uploadPerson) : that.uploadPerson != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (documentDate != null ? documentDate.hashCode() : 0);
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        result = 31 * result + (file != null ? file.hashCode() : 0);
        result = 31 * result + (uploadPerson != null ? uploadPerson.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DocumentExample{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", documentDate=" + documentDate +
                ", uploadDate=" + uploadDate +
                ", file='" + file + '\'' +
                ", uploadPerson='" + uploadPerson + '\'' +
                '}';
    }
}
