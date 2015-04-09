package me.mattlogan.rhymecity.api.model;

import java.util.ArrayList;
import java.util.List;

public class RhymesObject {

    private final List<String> all;

    public RhymesObject(List<String> rhymeList) {
        this.all = rhymeList;
    }

    public List<String> getAll() {
        return all != null ? all : new ArrayList<String>();
    }
}
