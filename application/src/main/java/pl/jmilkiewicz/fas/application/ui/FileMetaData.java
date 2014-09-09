package pl.jmilkiewicz.fas.application.ui;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileMetaData {
    private final String username;
    private final String documentDate;
    private final String fileName;

    public FileMetaData(String username, String documentDate, String fileName) {
        this.username = username;
        this.documentDate = documentDate;
        this.fileName = fileName;
    }

    public String getUsername() {
        return username;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public String getFileName() {
        return fileName;
    }
}
