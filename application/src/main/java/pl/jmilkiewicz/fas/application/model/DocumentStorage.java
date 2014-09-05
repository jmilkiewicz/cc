package pl.jmilkiewicz.fas.application.model;

import pl.jmilkiewicz.fas.application.FileMetaData;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface DocumentStorage {
    void addDocument(Document doc);
}
