package pl.jmilkiewicz.fas.web.controller;

public class AddFileForm {
    private String documentDate;
    private String fileName;

    public String getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(String documentDate) {
        this.documentDate = documentDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
