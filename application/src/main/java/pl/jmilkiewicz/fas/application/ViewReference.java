package pl.jmilkiewicz.fas.application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 12:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class ViewReference {
    public static ViewReference DEFAULT = new ViewReference("documents");
    private String type;
    private String dataRef;
    private List<Argument> arguments = new LinkedList<Argument>();
    private String contextData;

    public ViewReference(String type) {
        this.type = type;
    }

    public ViewReference(ViewReference viewReference) {
        this.type = viewReference.type;
        this.contextData = viewReference.contextData;
        this.arguments.addAll(viewReference.arguments);
        this.dataRef = viewReference.dataRef;
    }

    public ViewReference withType(String newType){
        ViewReference result = new ViewReference(this);
        result.type = newType;
        return result;
    }

    public ViewReference withContextDate(String newContextData){
        ViewReference result = new ViewReference(this);
        result.contextData = newContextData;
        return result;
    }

    public ViewReference withDataRef(String newDataRef){
        ViewReference result = new ViewReference(this);
        result.dataRef = newDataRef;
        return result;
    }

    public ViewReference withArgument(Argument newArgument){
        ViewReference result = new ViewReference(this);
        result.arguments.add(newArgument);
        return result;
    }

    public String getType() {
        return type;
    }

    public List<Argument> getArguments() {
        return new LinkedList<Argument>(arguments);
    }

    public String getContextData() {
        return contextData;
    }


    public String getDataRef() {
        return dataRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewReference that = (ViewReference) o;

        if (arguments != null ? !arguments.equals(that.arguments) : that.arguments != null) return false;
        if (contextData != null ? !contextData.equals(that.contextData) : that.contextData != null) return false;
        if (dataRef != null ? !dataRef.equals(that.dataRef) : that.dataRef != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (dataRef != null ? dataRef.hashCode() : 0);
        result = 31 * result + (arguments != null ? arguments.hashCode() : 0);
        result = 31 * result + (contextData != null ? contextData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ViewReference{" +
                "type='" + type + '\'' +
                ", dataRef='" + dataRef + '\'' +
                ", arguments=" + arguments +
                ", contextData='" + contextData + '\'' +
                '}';
    }
}
