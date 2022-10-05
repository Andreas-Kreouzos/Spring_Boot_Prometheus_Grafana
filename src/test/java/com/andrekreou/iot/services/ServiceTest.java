package com.andrekreou.iot.services;

import com.andrekreou.iot.control.service.MainService;
import com.andrekreou.iot.operations.crypto.model.CryptoNews;
import com.andrekreou.iot.operations.crypto.repository.CryptoNewsRepo;
import com.andrekreou.iot.operations.movies.model.Movies;
import com.andrekreou.iot.operations.movies.repository.MoviesRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceTest {

    @Mock
    CryptoNewsRepo cryptoNewsRepo;

    @Mock
    MoviesRepo moviesRepo;

    @InjectMocks
    MainService mainService;

    @Test
    @DisplayName("Saving one item of CryptoNews to the repository")
    public void saveOneInstanceOfCryptoNews() {
        CryptoNews saveCrypto = getCrypto();

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
        CryptoNews saveCrypto = CryptoNews.builder()
                .news_provider_name("CoinEdition")
                .HEADLINE("Crypto Finds Severe Falls in 2022; Analyses Crypto IRL")
                .news_link("https://www.investing.com/news/cryptocurrency-news/crypto-finds-severe-falls-in-2022-analyses-crypto-irl-2904122")
                .related_image("https://i-invdn-com.investing.com/news/Cryptocurrencies_150x108_S_1556527948.jpg")
                .build();
        when(cryptoNewsRepo.save(any(CryptoNews.class))).thenReturn(saveCrypto);
        return saveCrypto;
    }

    @Test
    @DisplayName("Saving one item of Movies to the repository")
    public void saveOneInstanceOfMovies() {
        Movies saveMovie = getMovie();

        Movies actual = mainService.saveOneMovieItem(new Movies());

        assertThat(actual).usingRecursiveComparison().isEqualTo(saveMovie);
    }

    @Test
    @DisplayName("Saving a Movie item and test if it has a specific string in its whole sequence")
    public void testMoviesList() {
        Movies saveMovie = getMovie();

        List<Movies> movies = new ArrayList<>();
        movies.add(saveMovie);

        assertThat(movies.size()).isEqualTo(1);
        assertTrue(movies.toString().contains("Avatar"));
    }

    private Movies getMovie() {
        Movies saveMovie = Movies.builder()
                .title("Avatar")
                .worldwideLifetimeGross("$2,905,490,102")
                .year(2009)
                .build();
        when(moviesRepo.save(any(Movies.class))).thenReturn(saveMovie);
        return saveMovie;
    }
}
