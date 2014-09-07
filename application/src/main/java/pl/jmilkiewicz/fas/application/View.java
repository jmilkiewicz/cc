package pl.jmilkiewicz.fas.application;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 7:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class View {
    private final ViewReference selfReference;
    private final Object data;
    private List<String> messages;

    public View(ViewReference selfReference, Object data, String... messages) {
        this.selfReference = selfReference;
        this.data = data;
        this.messages = Arrays.asList(messages);
    }

    public ViewReference getSelfReference() {
        return selfReference;
    }

    public Object getData() {
        return data;
    }

    public Collection<String> getMessages() {
        return Collections.unmodifiableCollection(messages);
    }
}

