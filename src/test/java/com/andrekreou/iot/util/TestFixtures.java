package com.andrekreou.iot.util;

import com.andrekreou.iot.entity.CryptoNews;

import java.util.ArrayList;
import java.util.List;

public class TestFixtures {

    public static List<CryptoNews> createCryptoNews() {
        List<CryptoNews> cryptoNews = new ArrayList<>();

        CryptoNews firstNews = CryptoNews.builder()
                .withNewsId(101)
                .withNewsProviderName("Crypto Times")
                .withHeadline("Bitcoin surges past $50,000")
                .withNewsLink("https://example.com/bitcoin-news")
                .withRelatedImage("https://example.com/images/bitcoin.jpg")
                .build();

        CryptoNews secondNews = CryptoNews.builder()
                .withNewsId(102)
                .withNewsProviderName("Ethereum Daily")
                .withHeadline("Ethereum update news")
                .withNewsLink("https://example.com/ethereum-news")
                .withRelatedImage("https://example.com/images/ethereum.jpg")
                .build();

        cryptoNews.add(firstNews);
        cryptoNews.add(secondNews);
        return cryptoNews;

    }
}
