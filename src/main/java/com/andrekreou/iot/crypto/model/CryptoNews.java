package com.andrekreou.iot.crypto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
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

    public CryptoNews(
            Integer news_id,
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

    public String getNews_provider_name() {
        return news_provider_name;
    }

    public String getHEADLINE() {
        return HEADLINE;
    }

    public String getNews_link() {
        return news_link;
    }

    public String getRelated_image() {
        return related_image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoNews that = (CryptoNews) o;
        return Objects.equals(news_id, that.news_id)
                && Objects.equals(news_provider_name, that.news_provider_name)
                && Objects.equals(HEADLINE, that.HEADLINE)
                && Objects.equals(news_link, that.news_link)
                && Objects.equals(related_image, that.related_image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(news_id, news_provider_name, HEADLINE, news_link, related_image);
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
