package pl.jmilkiewicz.fas.application;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/5/14
 * Time: 12:49 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Navigator {
    void goTo(ViewReference viewReference);
    void display(View view);

}
