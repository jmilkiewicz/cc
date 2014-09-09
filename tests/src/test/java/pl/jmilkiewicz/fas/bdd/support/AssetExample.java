package pl.jmilkiewicz.fas.bdd.support;

import pl.jmilkiewicz.fas.application.ui.FileMetaData;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssetExample {
    public String fileName;
    public String documentDate;
    public String file;

    public AssetExample() {
    }

    public InputStream content() {
        try {
            return new ByteArrayInputStream(file.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public FileMetaData metadata(String userGivenName) {
        return new FileMetaData(userGivenName, documentDate, fileName);
    }
}
