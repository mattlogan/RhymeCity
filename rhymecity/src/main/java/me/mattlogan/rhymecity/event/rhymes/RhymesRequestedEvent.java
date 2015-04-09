package me.mattlogan.rhymecity.event.rhymes;

public class RhymesRequestedEvent {

    private final String word;

    public RhymesRequestedEvent(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }
}
