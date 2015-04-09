package me.mattlogan.rhymecity.event.rhymes;

import java.util.List;

public class RhymesSuccessEvent {

    private final String word;
    private final List<String> rhymes;

    public RhymesSuccessEvent(String word, List<String> rhymes) {
        this.word = word;
        this.rhymes = rhymes;
    }

    public String getWord() {
        return word;
    }

    public List<String> getRhymes() {
        return rhymes;
    }
}
