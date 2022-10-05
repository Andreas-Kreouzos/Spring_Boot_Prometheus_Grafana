package com.andrekreou.iot.bitpay.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

//This class is responsible for mapping the key variables from
//the JSON array to be imported. The name keys from JSON have
//to be exactly the same as here, in order for data to be fetched.


//Don't forget to apply Lombok, at the end of the project as appendix!!!
@Entity
@Table
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitPayRates {
    @Id
/*    @SequenceGenerator(
            name = "bitpay_sequence",
            sequenceName = "bitpay_sequence",
            allocationSize = 1
            )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bitpay_sequence"
    )*/
    private Integer news_id;
    private String news_provider_name;
    @JsonProperty("headline")
    private String HEADLINE;
    private String news_link;
    private String related_image;


    public BitPayRates() {
    }

    public BitPayRates(Integer news_id,
                       String news_provider_name,
                       String HEADLINE,
                       String news_link,
                       String related_image) {
        this.news_id = news_id;
        this.news_provider_name = news_provider_name;
        this.HEADLINE = HEADLINE;
        this.news_link = news_link;
        this.related_image = related_image;
    }

    public Integer getNews_id() {
        return news_id;
    }

    public void setNews_id(Integer news_id) {
        this.news_id = news_id;
    }

    public String getNews_provider_name() {
        return news_provider_name;
    }

    public void setNews_provider_name(String news_provider_name) {
        this.news_provider_name = news_provider_name;
    }

    @JsonProperty("headline")
    public String getHEADLINE() {
        return HEADLINE;
    }

    @JsonProperty("headline")
    public void setHEADLINE(String HEADLINE) {
        this.HEADLINE = HEADLINE;
    }

    public String getNews_link() {
        return news_link;
    }

    public void setNews_link(String news_link) {
        this.news_link = news_link;
    }

    public String getRelated_image() {
        return related_image;
    }

    public void setRelated_image(String related_image) {
        this.related_image = related_image;
    }

    @Override
    public String toString() {
        return "BitPayRates{" +
                "news_id=" + news_id +
                ", news_provider_name='" + news_provider_name + '\'' +
                ", HEADLINE='" + HEADLINE + '\'' +
                ", news_link='" + news_link + '\'' +
                ", related_image='" + related_image + '\'' +
                '}';
    }
}
