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

    /**
     * @return The id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of id
     *
     * @param id The new value to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The newsId
     */
    public Integer getNewsId() {
        return newsId;
    }

    /**
     * Sets the value of newsId
     *
     * @param newsId The new value to set
     */
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    /**
     * @return The newsProviderName
     */
    public String getNewsProviderName() {
        return newsProviderName;
    }

    /**
     * Sets the value of newsProviderName
     *
     * @param newsProviderName The new value to set
     */
    public void setNewsProviderName(String newsProviderName) {
        this.newsProviderName = newsProviderName;
    }

    /**
     * @return The headline
     */
    public String getHeadline() {
        return headline;
    }

    /**
     * Sets the value of headline
     *
     * @param headline The new value to set
     */
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    /**
     * @return The newsLink
     */
    public String getNewsLink() {
        return newsLink;
    }

    /**
     * Sets the value of newsLink
     *
     * @param newsLink The new value to set
     */
    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }

    /**
     * @return The relatedImage
     */
    public String getRelatedImage() {
        return relatedImage;
    }

    /**
     * Sets the value of relatedImage
     *
     * @param relatedImage The new value to set
     */
    public void setRelatedImage(String relatedImage) {
        this.relatedImage = relatedImage;
    }

    /**
     * Creates a new Builder instance.
     *
     * @return the created Builder instance.
     */
    public static Builder builder() {
        return new Builder();
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

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withNewsId(Integer newsId) {
            this.newsId = newsId;
            return this;
        }

        public Builder withNewsProviderName(String newsProviderName) {
            this.newsProviderName = newsProviderName;
            return this;
        }

        public Builder withHeadline(String headline) {
            this.headline = headline;
            return this;
        }

        public Builder withNewsLink(String newsLink) {
            this.newsLink = newsLink;
            return this;
        }

        public Builder withRelatedImage(String relatedImage) {
            this.relatedImage = relatedImage;
            return this;
        }

        /**
         * Builds the audit record instance.
         *
         * @return the newly built audit record instance.
         */
        public CryptoNews build() {
            CryptoNews cryptoNews = new CryptoNews();
            cryptoNews.setId(id);
            cryptoNews.setNewsId(newsId);
            cryptoNews.setNewsProviderName(newsProviderName);
            cryptoNews.setHeadline(headline);
            cryptoNews.setNewsLink(newsLink);
            cryptoNews.setRelatedImage(relatedImage);
            return cryptoNews;
        }
    }
}
