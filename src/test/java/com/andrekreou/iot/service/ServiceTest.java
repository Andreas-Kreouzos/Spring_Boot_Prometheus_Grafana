package com.andrekreou.iot.service;

import com.andrekreou.iot.control.service.MainService;
import com.andrekreou.iot.crypto.model.CryptoNews;
import com.andrekreou.iot.crypto.repository.CryptoNewsRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    CryptoNewsRepo cryptoNewsRepo;

    @InjectMocks
    MainService mainService;

    @Test
    @DisplayName("Saving one item of CryptoNews to the repository")
    public void saveOneInstanceOfCryptoNews() {
        CryptoNews saveCrypto = getCrypto();
        when(cryptoNewsRepo.save(any(CryptoNews.class))).thenReturn(saveCrypto);

        CryptoNews actual = mainService.saveOneCryptoItem(new CryptoNews());

        assertThat(actual).usingRecursiveComparison().isEqualTo(saveCrypto);
    }

    @Test
    @DisplayName("Saving a News item and test if it has a specific string in its whole sequence")
    public void testContentOfNewsList() {
        CryptoNews saveCrypto = getCrypto();

        List<CryptoNews> rates = new ArrayList<>();
        rates.add(saveCrypto);

        assertThat(rates.size()).isEqualTo(1);
        assertTrue(rates.toString().contains("Finds Severe"));
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
