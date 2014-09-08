package pl.jmilkiewicz.fas.bdd.support;

import pl.jmilkiewicz.fas.application.model.Document;

import java.util.Collection;
import java.util.List;

public interface SystemDocuments {
    Collection<DocumentExample> getAll();

    void add(List<DocumentExample> documents);
}


