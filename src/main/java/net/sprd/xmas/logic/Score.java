package net.sprd.xmas.logic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Score {

    @Id
    private String id;

    private String name;
    private Double error;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Score setName(String name) {
        this.name = name;
        return this;
    }

    public Double getError() {
        return error;
    }

    public Score setError(Double error) {
        this.error = error;
        return this;
    }
}
