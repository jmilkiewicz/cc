package pl.jmilkiewicz.fas.web.controller;

import java.net.URI;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/7/14
 * Time: 3:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResponseBody {
    private final URI selfReference;
    private final String type;
    private final Object data;


    public ResponseBody(URI selfReference, String type, Object data) {
        this.selfReference = selfReference;
        this.type = type;
        this.data = data;
    }

    public URI getSelfReference() {
        return selfReference;
    }

    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
