package net.sprd.xmas.logic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Document
public class Score {

    @Id
    private String id;

    private String name;
    private BigDecimal error;
    private ZonedDateTime dateCreated;

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

    public BigDecimal getError() {
        return error;
    }

    public Score setError(BigDecimal error) {
        this.error = error;
        return this;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public Score setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }
}
