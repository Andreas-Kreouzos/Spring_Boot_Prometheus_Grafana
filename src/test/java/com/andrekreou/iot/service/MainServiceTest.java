package com.andrekreou.iot.service;

import com.andrekreou.iot.CryptoClient;
import com.andrekreou.iot.entity.CryptoNews;
import com.andrekreou.iot.repository.CryptoNewsRepo;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MainServiceTest {

    @Mock
    private CryptoNewsRepo cryptoNewsRepo;

    @Mock
    private CryptoClient cryptoClient;

    @InjectMocks
    private MainService service;

    private CryptoNews sampleCrypto;

    @BeforeEach
    void setUp() {
        sampleCrypto = getCrypto();
    }

    @Test
    @DisplayName("Successfully persist a list of crypto news to the repository")
    public void persistListOfCryptoNews() {
        // given: a list with a simple crypto
        List<CryptoNews> cryptoNews = List.of(sampleCrypto);

        // and: the client will return this list
        when(cryptoClient.fetchCryptoNews()).thenReturn(cryptoNews);

        // when: calling the service
        service.persistCrypto();

        // then: the client was called once
        verify(cryptoClient, times(1)).fetchCryptoNews();

        // and: the repository was called once with this list
        verify(cryptoNewsRepo, times(1)).saveAll(cryptoNews);
    }

    @Test
    @DisplayName("Persist a list of crypto news fails due to error in client")
    public void persistListOfCryptoNewsFailsDueToErrorInClient() {
        // given: the client will return an exception
        when(cryptoClient.fetchCryptoNews()).thenThrow(new RuntimeException("Unexpected error"));

        // when: calling the service
        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.persistCrypto());

        // then: the correct exception message gets returned
        assertEquals("Unexpected error", exception.getMessage());

        // and: no interactions with the repository are taking place
        verifyNoInteractions(cryptoNewsRepo);
    }

    @Test
    @DisplayName("Persist a list of crypto news fails due to error in repository")
    public void persistListOfCryptoNewsFailsDueToErrorInRepository() {
        // given: a list with a simple crypto
        List<CryptoNews> cryptoNews = List.of(sampleCrypto);

        // and: the client will return this list
        when(cryptoClient.fetchCryptoNews()).thenReturn(cryptoNews);

        // and: the repository could not persist
        when(cryptoNewsRepo.saveAll(cryptoNews)).thenThrow(new PersistenceException("Could not persist"));

        // when: calling the service
        RuntimeException exception = assertThrows(PersistenceException.class, () -> service.persistCrypto());

        // then: the correct exception message gets returned
        assertEquals("Could not persist", exception.getMessage());

        // and: the client was called once
        verify(cryptoClient, times(1)).fetchCryptoNews();

        // and: the repository was called once with this list
        verify(cryptoNewsRepo, times(1)).saveAll(cryptoNews);
    }

    private CryptoNews getCrypto() {
        return new CryptoNews(
                1,
                "CoinEdition",
                "Crypto Finds Severe Falls in 2022; Analyses Crypto IRL",
                "https://www.investing.com/news/cryptocurrency-news/crypto-finds-severe-falls-in-2022-analyses-crypto-irl-2904122",
                "https://i-invdn-com.investing.com/news/Cryptocurrencies_150x108_S_1556527948.jpg"
        );
    }
}
