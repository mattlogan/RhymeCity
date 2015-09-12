package me.mattlogan.rhymecity.data.model;

import java.util.ArrayList;
import java.util.List;

public final class Rhymes {

    private final List<String> all;

    public Rhymes(List<String> rhymeList) {
        this.all = rhymeList;
    }

    public List<String> list() {
        return all != null ? all : new ArrayList<String>();
    }
}
