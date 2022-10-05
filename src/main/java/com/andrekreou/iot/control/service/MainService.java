package com.andrekreou.iot.control.service;

import com.andrekreou.iot.operations.crypto.model.CryptoNews;
import com.andrekreou.iot.operations.crypto.repository.CryptoNewsRepo;
import com.andrekreou.iot.operations.movies.model.Movies;
import com.andrekreou.iot.operations.movies.repository.MoviesRepo;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MainService {

    private final CryptoNewsRepo cryptoNewsRepo;
    private final MoviesRepo moviesRepo;

    @Autowired
    public MainService(CryptoNewsRepo cryptoNewsRepo,
                       MoviesRepo moviesRepo) {
        this.cryptoNewsRepo = cryptoNewsRepo;
        this.moviesRepo = moviesRepo;
    }

    public List<CryptoNews> showAllRates(){
        return new ArrayList<>(cryptoNewsRepo.findAll());
    }

    @Timed(value = "show.time", description = "Time taken to return showAllMovies")
    public List<Movies> showAllMovies(){
        return new ArrayList<>(moviesRepo.findAll());
    }

    public CryptoNews saveOneCryptoItem(CryptoNews cryptoNews){
        CryptoNews saveCrypto = CryptoNews.builder()
                .news_provider_name(cryptoNews.getNews_provider_name())
                .HEADLINE(cryptoNews.getHEADLINE())
                .news_link(cryptoNews.getNews_link())
                .related_image(cryptoNews.getRelated_image())
                .build();
        return this.cryptoNewsRepo.save(saveCrypto);
    }

    public Movies saveOneMovieItem(Movies movies){
        Movies saveMovie = Movies.builder()
                .title(movies.getTitle())
                .worldwideLifetimeGross(movies.getWorldwideLifetimeGross())
                .year(movies.getYear())
                .build();
        return this.moviesRepo.save(saveMovie);
    }
}