package com.andrekreou.iot.client;

import com.andrekreou.iot.entity.CryptoNews;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WireMockTest(httpPort = 8181)
public class CryptoClientTest {

    private CryptoClient cryptoClient;

    @BeforeEach
    void setUp() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        cryptoClient = new CryptoClient(objectMapper);

        var restClientField = CryptoClient.class.getDeclaredField("restClient");
        restClientField.setAccessible(true);

        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8181")
                .build();
        restClientField.set(cryptoClient, restClient);
    }

    @Test
    @DisplayName("Client successfully returned the crypto news")
    void fetchCryptoNews_shouldReturnParsedNews_onValidResponse() {
        // given: a valid response
        String validResponse = """
        {
            "data": [
                {
                    "screen_data": {
                        "news": [
                            {
                                "news_ID": 123,
                                "news_provider_name": "Provider Name",
                                "HEADLINE": "Breaking Crypto News",
                                "news_link": "https://example.com/news",
                                "related_image": "https://example.com/image.jpg"
                            }
                        ]
                    }
                }
            ]
        }
        """;

        // and: the client will return that response
        stubFor(get(urlEqualTo("/coins/get-news?pair_ID=1057391"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(validResponse)));

        // when: calling the client
        List<CryptoNews> newsList = cryptoClient.fetchCryptoNews();

        // then: the result should not be null
        assertNotNull(newsList);

        // and: should have the correct size
        assertEquals(1, newsList.size());

        // and: with the correct values
        CryptoNews news = newsList.get(0);
        assertEquals(123, news.getNewsId());
        assertEquals("Provider Name", news.getNewsProviderName());
        assertEquals("Breaking Crypto News", news.getHeadline());
        assertEquals("https://example.com/news", news.getNewsLink());
        assertEquals("https://example.com/image.jpg", news.getRelatedImage());
    }

    @Test
    @DisplayName("Client fails to return the crypto news")
    void fetchCryptoNews_shouldThrowRuntimeException_onInvalidResponse() {
        // given: the client will return an invalid response
        stubFor(get(urlEqualTo("/coins/get-news?pair_ID=1057391"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("Invalid JSON")));

        // when: calling the client, an exception is expected
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cryptoClient.fetchCryptoNews();
        });

        // then: the exception message is correct
        assertTrue(exception.getMessage().contains("Error parsing CryptoNews data"));
    }
}
