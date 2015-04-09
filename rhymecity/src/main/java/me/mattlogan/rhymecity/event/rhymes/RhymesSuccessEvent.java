package me.mattlogan.rhymecity.event.rhymes;

import java.util.List;

public class RhymesSuccessEvent {

    private final List<String> rhymes;

    public RhymesSuccessEvent(List<String> rhymes) {
        this.rhymes = rhymes;
    }

    public List<String> getRhymes() {
        return rhymes;
    }
}
