package pl.jmilkiewicz.fas.bdd.support;

import pl.jmilkiewicz.fas.application.model.Document;
import pl.jmilkiewicz.fas.application.model.DocumentData;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentExample {
    public Long id;
    public String fileName;
    public Date documentDate;
    public String content;
    public String uploadPerson;


    static DocumentExample fromDocument(Document doc){
        DocumentExample result =  new DocumentExample();
        result.uploadPerson = doc.getDocumentData().getUploadPerson();
        result.fileName = doc.getDocumentData().getName();
        result.documentDate = doc.getDocumentData().getDocumentDate();
        result.content = asString(doc.getDocumentData().getData());
        result.id = doc.getId();
        return result;
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


    public Document asDocument() throws UnsupportedEncodingException {
        //TODO uploadDate
        return new Document(id, new DocumentData(fileName, uploadPerson, documentDate, null, new ByteArrayInputStream(content.getBytes("UTF-8"))));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentExample that = (DocumentExample) o;

        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (documentDate != null ? !documentDate.equals(that.documentDate) : that.documentDate != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uploadPerson != null ? !uploadPerson.equals(that.uploadPerson) : that.uploadPerson != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (documentDate != null ? documentDate.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (uploadPerson != null ? uploadPerson.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DocumentExample{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", documentDate=" + documentDate +
                ", content='" + content + '\'' +
                ", uploadPerson='" + uploadPerson + '\'' +
                '}';
    }
}
