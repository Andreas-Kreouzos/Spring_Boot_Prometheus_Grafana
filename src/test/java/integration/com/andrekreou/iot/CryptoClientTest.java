package integration.com.andrekreou.iot;

import com.andrekreou.iot.IotApplication;
import com.andrekreou.iot.client.CryptoClient;
import com.andrekreou.iot.entity.CryptoNews;
import com.andrekreou.iot.util.TestContainersConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = IotApplication.class)
public class CryptoClientTest extends TestContainersConfig {

    @Autowired
    private CryptoClient cryptoClient;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestClient.Builder restClientBuilder;

    @BeforeEach
    void setup() {
        cryptoClient = new CryptoClient(restClientBuilder, mapper);
    }

    @Test
    @DisplayName("Client successfully returned the real crypto news")
    void fetchCryptoNews_shouldReturnParsedNews_onValidResponse() {
        // when: calling the client
        List<CryptoNews> newsList = cryptoClient.fetchCryptoNews();

        // then: the list has ten records
        assertEquals(10, newsList.size());
    }
}
