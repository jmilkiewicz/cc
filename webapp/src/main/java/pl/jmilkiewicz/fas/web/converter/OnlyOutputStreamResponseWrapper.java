package pl.jmilkiewicz.fas.web.converter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/7/14
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class OnlyOutputStreamResponseWrapper extends HttpServletResponseWrapper{
    private PrintWriter myPrintWriter;

    public OnlyOutputStreamResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
       if(myPrintWriter == null){
           myPrintWriter = new PrintWriter(getOutputStream());
       }
       return  myPrintWriter;
    }
}
