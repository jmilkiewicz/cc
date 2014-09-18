package pl.jmilkiewicz.fas.client;

/**
 * Created with IntelliJ IDEA.
 * User: milus
 * Date: 9/18/14
 * Time: 8:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class Credentials {
    private final String name;
    private final String password;

    public Credentials(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
