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

    @JsonProperty("news_provider_name")
    private String newsProviderName;

    @JsonProperty("HEADLINE")
    private String headline;

    @JsonProperty("news_link")
    private String newsLink;

    @JsonProperty("related_image")
    private String relatedImage;

    public CryptoNews() {
    }

    public CryptoNews(
            Integer newsId,
            String newsProviderName,
            String headline,
            String newsLink,
            String relatedImage) {
        this.newsId = newsId;
        this.newsProviderName = newsProviderName;
        this.headline = headline;
        this.newsLink = newsLink;
        this.relatedImage = relatedImage;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public String getNewsProviderName() {
        return newsProviderName;
    }

    public String getHeadline() {
        return headline;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public String getRelatedImage() {
        return relatedImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CryptoNews that = (CryptoNews) o;
        return Objects.equals(newsId, that.newsId)
                && Objects.equals(newsProviderName, that.newsProviderName)
                && Objects.equals(headline, that.headline)
                && Objects.equals(newsLink, that.newsLink)
                && Objects.equals(relatedImage, that.relatedImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, newsProviderName, headline, newsLink, relatedImage);
    }

    @Override
    public String toString() {
        return "CryptoNews{" +
                "newsId=" + newsId +
                ", newsProviderName='" + newsProviderName + '\'' +
                ", HEADLINE='" + headline + '\'' +
                ", news_link='" + newsLink + '\'' +
                ", related_image='" + relatedImage + '\'' +
                '}';
    }
}
