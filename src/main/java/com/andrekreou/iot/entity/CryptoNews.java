package com.andrekreou.iot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoNews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("news_ID")
    private Integer newsId;

    private String news_provider_name;

    @JsonProperty("HEADLINE")
    private String headline;

    private String news_link;

    private String related_image;

    public CryptoNews() {
    }

    public CryptoNews(
            Integer newsId,
            String news_provider_name,
            String headline,
            String news_link,
            String related_image) {
        this.newsId = newsId;
        this.news_provider_name = news_provider_name;
        this.headline = headline;
        this.news_link = news_link;
        this.related_image = related_image;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public String getNews_provider_name() {
        return news_provider_name;
    }

    public String getHeadline() {
        return headline;
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
        return Objects.equals(newsId, that.newsId)
                && Objects.equals(news_provider_name, that.news_provider_name)
                && Objects.equals(headline, that.headline)
                && Objects.equals(news_link, that.news_link)
                && Objects.equals(related_image, that.related_image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, news_provider_name, headline, news_link, related_image);
    }

    @Override
    public String toString() {
        return "CryptoNews{" +
                "newsId=" + newsId +
                ", news_provider_name='" + news_provider_name + '\'' +
                ", HEADLINE='" + headline + '\'' +
                ", news_link='" + news_link + '\'' +
                ", related_image='" + related_image + '\'' +
                '}';
    }
}
