package pl.jmilkiewicz.fas.application;

public interface Navigator {
    void goTo(ViewReference viewReference);
    void display(View view);
    void notFound();
    void display(byte[] bytes);

}
