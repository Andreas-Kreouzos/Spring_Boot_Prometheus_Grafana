package com.andrekreou.iot.client;

import com.andrekreou.iot.configuration.MonitoringConfiguration;
import com.andrekreou.iot.entity.CryptoNews;
import com.andrekreou.iot.repository.CryptoRepository;
import com.andrekreou.iot.util.TestFixtures;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(value = CryptoClient.class, excludeAutoConfiguration = {MonitoringConfiguration.class, CryptoRepository.class})
public class CryptoClientTest {

    @Autowired
    private CryptoClient cryptoClient;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestClient.Builder restClientBuilder;

    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        mockServer = MockRestServiceServer.bindTo(restClientBuilder).build();
        cryptoClient = new CryptoClient(restClientBuilder, mapper);
    }

    @AfterEach
    void teardown() {
        mockServer.reset();
    }

    @Test
    @DisplayName("Client successfully returned the crypto news")
    void fetchCryptoNews_shouldReturnParsedNews_onValidResponse() throws Exception {
        // given: a valid response
        Map<String, Object> screenData = Map.of("news", TestFixtures.createCryptoNews());
        Map<String, Object> responseBody = Map.of("data", List.of(Map.of("screen_data", screenData)));

        // and: convert it to JSON string
        String response = mapper.writeValueAsString(responseBody);

        // and: configure the client to return that response
        mockServer.expect(requestTo("https://investing-cryptocurrency-markets.p.rapidapi.com/coins/get-news?pair_ID=1057391"))
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));

        // when: calling the client
        List<CryptoNews> newsList = cryptoClient.fetchCryptoNews();

        // then: the list has two records
        assertEquals(2, newsList.size());

        // and: the expected values for the first record are returned
        CryptoNews firstNews = newsList.get(0);
        assertNotNull(firstNews);
        assertEquals(101, firstNews.getNewsId());
        assertEquals("Crypto Times", firstNews.getNewsProviderName());
        assertEquals("Bitcoin surges past $50,000", firstNews.getHeadline());
        assertEquals("https://example.com/bitcoin-news", firstNews.getNewsLink());
        assertEquals("https://example.com/images/bitcoin.jpg", firstNews.getRelatedImage());

        // and: the expected values for the second record are returned
        CryptoNews secondNews = newsList.get(1);
        assertNotNull(secondNews);
        assertEquals(102, secondNews.getNewsId());
        assertEquals("Ethereum Daily", secondNews.getNewsProviderName());
        assertEquals("Ethereum update news", secondNews.getHeadline());
        assertEquals("https://example.com/ethereum-news", secondNews.getNewsLink());
        assertEquals("https://example.com/images/ethereum.jpg", secondNews.getRelatedImage());
    }

    @Test
    @DisplayName("Client fails to return the crypto news")
    void fetchCryptoNews_shouldThrowRuntimeException_onInvalidResponse() {
        // given: a mock server returning an invalid response
        mockServer.expect(requestTo("https://investing-cryptocurrency-markets.p.rapidapi.com/coins/get-news?pair_ID=1057391"))
                .andRespond(withSuccess("Invalid JSON", MediaType.APPLICATION_JSON));

        // when: calling the client, an exception is expected
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cryptoClient.fetchCryptoNews();
        });

        // then: the exception message is correct
        assertTrue(exception.getMessage().contains("Error parsing CryptoNews data"));
    }
}
