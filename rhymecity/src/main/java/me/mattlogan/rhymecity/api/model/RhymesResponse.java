package me.mattlogan.rhymecity.api.model;

public class RhymesResponse {

    private final RhymesObject rhymes;

    public RhymesResponse(RhymesObject rhymesObject) {
        this.rhymes = rhymesObject;
    }

    public RhymesObject getRhymesObject() {
        return rhymes;
    }
}
