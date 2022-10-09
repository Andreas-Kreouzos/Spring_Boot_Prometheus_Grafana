package com.andrekreou.iot.operations.crypto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoNews {
    @Id
    private Integer news_id;
    private String news_provider_name;
    @JsonProperty("headline")
    private String HEADLINE;
    private String news_link;
    private String related_image;


    public CryptoNews() {
    }

    public CryptoNews(Integer news_id,
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

    @Override
    public String toString() {
        return "CryptoNews{" +
                "news_id=" + news_id +
                ", news_provider_name='" + news_provider_name + '\'' +
                ", HEADLINE='" + HEADLINE + '\'' +
                ", news_link='" + news_link + '\'' +
                ", related_image='" + related_image + '\'' +
                '}';
    }
}
