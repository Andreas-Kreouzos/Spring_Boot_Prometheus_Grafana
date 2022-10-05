package com.andrekreou.iot.operations.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Builder
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWorldwideLifetimeGross() {
        return worldwideLifetimeGross;
    }

    public void setWorldwideLifetimeGross(String worldwideLifetimeGross) {
        this.worldwideLifetimeGross = worldwideLifetimeGross;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Movies{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", worldwideLifetimeGross=" + worldwideLifetimeGross +
                ", year=" + year +
                '}';
    }
}
