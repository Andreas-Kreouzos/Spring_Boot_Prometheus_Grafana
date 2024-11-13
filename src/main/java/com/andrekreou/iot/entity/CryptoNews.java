package com.andrekreou.iot.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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

    public CryptoNews(Builder builder) {
        this.id = builder.id;
        this.newsId = builder.newsId;
        this.newsProviderName = builder.newsProviderName;
        this.headline = builder.headline;
        this.newsLink = builder.newsLink;
        this.relatedImage = builder.relatedImage;
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

    public static class Builder {
        private Long id;
        private Integer newsId;
        private String newsProviderName;
        private String headline;
        private String newsLink;
        private String relatedImage;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder newsId(Integer newsId) {
            this.newsId = newsId;
            return this;
        }

        public Builder newsProviderName(String newsProviderName) {
            this.newsProviderName = newsProviderName;
            return this;
        }

        public Builder headline(String headline) {
            this.headline = headline;
            return this;
        }

        public Builder newsLink(String newsLink) {
            this.newsLink = newsLink;
            return this;
        }

        public Builder relatedImage(String relatedImage) {
            this.relatedImage = relatedImage;
            return this;
        }

        public CryptoNews build() {
            return new CryptoNews(this);
        }
    }
}
