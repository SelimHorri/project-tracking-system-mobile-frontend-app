package com.selimhorri.pack.model.collection;

import java.util.List;

public class DtoCollection<T> {

    private List<T> collection;

    public DtoCollection() {

    }

    public DtoCollection(List<T> collection) {
        this.collection = collection;
    }

    @Override
    public String toString() {
        return "DtoCollection{" +
                "collection=" + collection +
                '}';
    }

    public List<T> getCollection() {
        return collection;
    }

    public void setCollection(List<T> collection) {
        this.collection = collection;
    }
}
