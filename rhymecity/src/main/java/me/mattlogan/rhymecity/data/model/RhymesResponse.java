package me.mattlogan.rhymecity.data.model;

public final class RhymesResponse {

    private final Rhymes rhymes;

    public RhymesResponse(Rhymes rhymes) {
        this.rhymes = rhymes;
    }

    public Rhymes rhymesObject() {
        return rhymes;
    }
}
