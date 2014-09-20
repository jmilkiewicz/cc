package pl.jmilkiewicz.fas.client;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/20/14
 * Time: 5:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class AbstractBase {
    protected final Credentials credentials = new Credentials("user1", "password");
    protected FasClient fasClient;
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    protected File uploadedFile;
    @Before
    public void createNonEmptyFile() throws IOException {
         uploadedFile = createTempFile();
    }

    private File createTempFile() throws IOException {
        String uniqueFileName = "ABC" + System.currentTimeMillis();
        File file = folder.newFile(uniqueFileName);
        PrintWriter writer = new PrintWriter(file);
        writer.write("some text abc");
        writer.flush();
        writer.close();
        return file;
    }
}
