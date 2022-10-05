package com.andrekreou.iot.movies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//This class is responsible for mapping the key variables from
//the JSON array to be imported. The name keys from JSON have
//to be exactly the same as here, in order for data to be fetched.


//Don't forget to apply Lombok, at the end of the project as appendix!!!
@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movies {
    @Id
    private String id;
    private String title;
    private String worldwideLifetimeGross;
    private Integer year;


    public Movies() {
    }

    public Movies(String id, String title, Long worldwideLifetimeGross, Integer year) {
        this.id = id;
        this.title = title;
        this.worldwideLifetimeGross = String.valueOf(worldwideLifetimeGross);
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
