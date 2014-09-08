package pl.jmilkiewicz.fas.application.model;

import java.util.Date;


public class DocumentMetaData {
    private long id;
    private String name;
    private String uploadPerson;
    private Date documentDate;
    private Date uploadDate;

    public DocumentMetaData() {
    }

    private DocumentMetaData(DocumentMetaData other){
        this.id = other.id;
        this.name = other.name;
        this.uploadPerson = other.uploadPerson;
        this.uploadDate = copyOrReturnNullIfNotSet(other.uploadDate);
        this.documentDate = copyOrReturnNullIfNotSet(other.documentDate);
    }

    private Date copyOrReturnNullIfNotSet(Date other) {
        if(other != null){
            return new Date(other.getTime());
        }
        return null;
    }

    public DocumentMetaData withId(long newId){
        DocumentMetaData newDocument = new DocumentMetaData(this);
        newDocument.id = newId;
        return newDocument;
    }

    public DocumentMetaData withName(String newName){
        DocumentMetaData newDocument = new DocumentMetaData(this);
        newDocument.name = newName;
        return newDocument;
    }

    public DocumentMetaData withUploadDate(Date newUploadDate){
        DocumentMetaData newDocument = new DocumentMetaData(this);
        newDocument.uploadDate =  new Date(newUploadDate.getTime());
        return newDocument;
    }

    public DocumentMetaData withUploadPerson(String newUploadPerson){
        DocumentMetaData newDocument = new DocumentMetaData(this);
        newDocument.uploadPerson = newUploadPerson;
        return newDocument;
    }


    public DocumentMetaData withDocumentDate(Date newDocumentDate){
        DocumentMetaData newDocument = new DocumentMetaData(this);
        newDocument.documentDate=  new Date(newDocumentDate.getTime());
        return newDocument;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentMetaData that = (DocumentMetaData) o;

        if (id != that.id) return false;
        if (documentDate != null ? !documentDate.equals(that.documentDate) : that.documentDate != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (uploadDate != null ? !uploadDate.equals(that.uploadDate) : that.uploadDate != null) return false;
        if (uploadPerson != null ? !uploadPerson.equals(that.uploadPerson) : that.uploadPerson != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (uploadPerson != null ? uploadPerson.hashCode() : 0);
        result = 31 * result + (documentDate != null ? documentDate.hashCode() : 0);
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DocumentMetaData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uploadPerson='" + uploadPerson + '\'' +
                ", documentDate=" + documentDate +
                ", uploadDate=" + uploadDate +
                '}';
    }
}

