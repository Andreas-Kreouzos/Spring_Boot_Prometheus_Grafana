package com.andrekreou.iot.operations.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Builder
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movies {
    @Id
    private String id;
    private String title;
    private String worldwideLifetimeGross;
    private Integer year;


    public Movies() {
    }

    public Movies(String id,
                  String title,
                  String worldwideLifetimeGross,
                  Integer year) {
        this.id = id;
        this.title = title;
        this.worldwideLifetimeGross = worldwideLifetimeGross;
        this.year = year;
    }
}
