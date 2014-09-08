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
    private long id;
    private String name;
    private String uploadPerson;
    private Date documentDate;
    private Date uploadDate;
    private byte[] content = new byte[]{};

    public Document(Document other){
        this.id = other.id;
        this.name = other.name;
        this.uploadPerson = other.uploadPerson;
        this.uploadDate = copyOrReturnNullIfNotSet(other.uploadDate);
        this.documentDate = copyOrReturnNullIfNotSet(other.documentDate);
        this.content = Arrays.copyOf(other.content, other.content.length);
    }

    private Date copyOrReturnNullIfNotSet(Date other) {
        if(other != null){
            return new Date(other.getTime());
        }
        return null;
    }

    public Document() {
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

    public Date getUploadDate() {
        return uploadDate;
    }

    public byte[] getContent() {
        return content;
    }

    public Document withId(long newId){
         Document newDocument = new Document(this);
         newDocument.id = newId;
         return newDocument;
    }

    public Document withName(String newName){
        Document newDocument = new Document(this);
        newDocument.name = newName;
        return newDocument;
    }

    public Document withUploadDate(Date newUploadDate){
        Document newDocument = new Document(this);
        newDocument.uploadDate =  new Date(newUploadDate.getTime());
        return newDocument;
    }

    public Document withUploadPerson(String newUploadPerson){
        Document newDocument = new Document(this);
        newDocument.uploadPerson = newUploadPerson;
        return newDocument;
    }


    public Document withDocumentDate(Date newDocumentDate){
        Document newDocument = new Document(this);
        newDocument.documentDate=  new Date(newDocumentDate.getTime());
        return newDocument;
    }

    public Document withContent(byte[] newContent){
        Document newDocument = new Document(this);
        newDocument.content = Arrays.copyOf(newContent, newContent.length);
        return newDocument;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uploadPerson='" + uploadPerson + '\'' +
                ", documentDate=" + documentDate +
                ", uploadDate=" + uploadDate +
                ", content=" + Arrays.toString(content) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (id != document.id) return false;
        if (!Arrays.equals(content, document.content)) return false;
        if (documentDate != null ? !documentDate.equals(document.documentDate) : document.documentDate != null)
            return false;
        if (name != null ? !name.equals(document.name) : document.name != null) return false;
        if (uploadDate != null ? !uploadDate.equals(document.uploadDate) : document.uploadDate != null) return false;
        if (uploadPerson != null ? !uploadPerson.equals(document.uploadPerson) : document.uploadPerson != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uploadPerson != null ? uploadPerson.hashCode() : 0);
        result = 31 * result + (documentDate != null ? documentDate.hashCode() : 0);
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        result = 31 * result + (content != null ? Arrays.hashCode(content) : 0);
        return result;
    }
}
