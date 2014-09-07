package pl.jmilkiewicz.fas.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpResponse;
import pl.jmilkiewicz.fas.web.controller.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HttpMessageConverter extends AbstractHttpMessageConverter<ResponseBody> {
    @Autowired
    private HttpServletRequest request;

    public HttpMessageConverter() {
        super(MediaType.TEXT_HTML);
    }

    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    protected boolean supports(Class<?> clazz)
    {
        return true;
    }

    protected ResponseBody readInternal(Class<? extends ResponseBody> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void writeInternal(ResponseBody responseBody, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

        request.setAttribute("it", responseBody);
        try {
            HttpServletResponse servletResponse = getServletResponse(outputMessage);
            request.getRequestDispatcher("/WEB-INF/jsp/" + responseBody.getType() + ".jsp").forward(request, servletResponse);
            System.out.println("ASDSADa");
        } catch (ServletException e) {
            throw new IOException(e);
        }
    }

    private HttpServletResponse getServletResponse(HttpOutputMessage outputMessage) {
        ServletServerHttpResponse servletServerHttpResponse = (ServletServerHttpResponse)outputMessage;
        return new OnlyOutputStreamResponseWrapper(servletServerHttpResponse.getServletResponse());
    }
}
