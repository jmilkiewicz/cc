package pl.jmilkiewicz.fas.application.ff;

import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.application.model.DocumentMetaData;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/8/14
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentListEntry {
    private final long id;
    private final String name;
    private final String uploadPerson;
    private final Date documentDate;

    public DocumentListEntry(long id, String name, String uploadPerson, Date documentDate) {
        this.id = id;
        this.name = name;
        this.uploadPerson = uploadPerson;
        this.documentDate = documentDate;
    }

    public static DocumentListEntry fromDocument(DocumentMetaData documentMetadata){
        return new DocumentListEntry(
                                documentMetadata.getId(),
                                documentMetadata.getName(),
                                documentMetadata.getUploadPerson(),
                                documentMetadata.getDocumentDate());
    }

    public long getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentListEntry that = (DocumentListEntry) o;

        if (id != that.id) return false;
        if (documentDate != null ? !documentDate.equals(that.documentDate) : that.documentDate != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (uploadPerson != null ? !uploadPerson.equals(that.uploadPerson) : that.uploadPerson != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uploadPerson != null ? uploadPerson.hashCode() : 0);
        result = 31 * result + (documentDate != null ? documentDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DocumentListEntry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uploadPerson='" + uploadPerson + '\'' +
                ", documentDate=" + documentDate +
                '}';
    }
}
